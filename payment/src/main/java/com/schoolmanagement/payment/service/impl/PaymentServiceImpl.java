package com.schoolmanagement.payment.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.schoolmanagement.payment.entity.PaymentDetail;
import com.schoolmanagement.payment.exception.PaymentsException;
import com.schoolmanagement.payment.mapper.PaymentMapper;
import com.schoolmanagement.payment.repository.PaymentRepository;
import com.schoolmanagement.payment.schemaobjects.PaymentRequestSchemaObject;
import com.schoolmanagement.payment.schemaobjects.PaymentResponseObject;
import com.schoolmanagement.payment.schemaobjects.StudentDetailsReponseObject;
import com.schoolmanagement.payment.service.PaymentService;
import com.schoolmanagement.payment.validation.BasicValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final BasicValidation basicValidation;
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;
    private final static String RECEIPT_ENTRY_QUEUE="RECEIPT_ENTRY_QUEUE";

    @Override
    public void addPaymentEntry(StudentDetailsReponseObject studentDetailsReponseObject) {
        PaymentDetail paymentDetail = paymentRepository.findByStudentUniqueId(studentDetailsReponseObject.getStudentUniqueId());
        if(!ObjectUtils.isEmpty(paymentDetail)){
            log.info("Payment Detail already exist for student id {}",studentDetailsReponseObject.getStudentUniqueId());
            BeanUtils.copyProperties(studentDetailsReponseObject,paymentDetail,"cardNumber","cardType","dateOfPayment");
        }else{
            paymentDetail = new PaymentDetail();
            log.info("Payment Detail doesnot already exist for student id {}",studentDetailsReponseObject.getStudentUniqueId());
            BeanUtils.copyProperties(studentDetailsReponseObject,paymentDetail);
        }
        log.info("Adding payment Details {}",paymentDetail.toString());
        paymentRepository.save(paymentDetail);
    }

    @Override
    public PaymentResponseObject doPayment(PaymentRequestSchemaObject paymentRequestSchemaObject) {
        basicValidation.doBasicValidation(paymentRequestSchemaObject);
        PaymentDetail paymentDetail = paymentRepository.findByStudentUniqueId(paymentRequestSchemaObject.getStudentUniqueId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        paymentDetail = ObjectUtils.isEmpty(paymentDetail) ? new PaymentDetail() : paymentDetail;
        Long feesAlreadyPaid = paymentDetail.getFeesPaid()==null ? 0 : paymentDetail.getFeesPaid();
        Long totalFees = paymentDetail.getTotalFees()==null ? 0 : paymentDetail.getTotalFees();
        paymentDetail.setCardNumber(paymentRequestSchemaObject.getCardNumber());
        paymentDetail.setCardType(paymentRequestSchemaObject.getCardType());
        paymentDetail.setDateOfPayment(simpleDateFormat.format(new Date()));
        paymentDetail.setFeesPaid(feesAlreadyPaid + paymentRequestSchemaObject.getAmount());
        paymentDetail.setBalance(totalFees - (feesAlreadyPaid+paymentRequestSchemaObject.getAmount()));
        paymentDetail.setTransactionId(paymentDetail.getCardNumber() + paymentDetail.getStudentUniqueId());
        log.info("Adding payment Details {}",paymentDetail.toString());
        PaymentDetail returnPaymentDetail = paymentRepository.save(paymentDetail);
        PaymentResponseObject paymentResponseObject = PaymentMapper.mapPaymentEntityToSchemaObject(returnPaymentDetail);
        try {
            jmsTemplate.convertAndSend(RECEIPT_ENTRY_QUEUE, objectMapper.writeValueAsString(paymentResponseObject));
        } catch (JsonProcessingException e) {
            throw new PaymentsException("ERR_EXP_PARSING", "Bad data");
        }
        log.info("Successfully saved the payment details {}", paymentResponseObject);
        return paymentResponseObject;
    }
}
