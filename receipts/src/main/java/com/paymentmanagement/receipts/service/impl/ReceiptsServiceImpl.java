package com.paymentmanagement.receipts.service.impl;

import com.paymentmanagement.receipts.entity.Receipts;
import com.paymentmanagement.receipts.exception.ReceiptsException;
import com.paymentmanagement.receipts.repository.ReceiptsRepository;
import com.paymentmanagement.receipts.schemaobject.PaymentResponseObject;
import com.paymentmanagement.receipts.schemaobject.ReceiptsSo;
import com.paymentmanagement.receipts.service.ReceiptsService;
import com.paymentmanagement.receipts.utility.CacheUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReceiptsServiceImpl implements ReceiptsService {
    private final ReceiptsRepository receiptsRepository;
    private CacheUtility<List<ReceiptsSo>> receiptsSoCacheUtility;
    @Value("${cache.size}")
    private Integer size;
    @Value("${cache.duration}")
    private Integer duration;

    @Override
    public List<ReceiptsSo> getReceipts(String studentUniqueId) {
        List<ReceiptsSo> receiptsSos;
        if(receiptsSoCacheUtility==null) {
            receiptsSoCacheUtility = new CacheUtility(size, duration);
        }
        receiptsSos = receiptsSoCacheUtility.get(studentUniqueId);
        if(!CollectionUtils.isEmpty(receiptsSos)){
            log.info("key present in cache {}",studentUniqueId);
            return receiptsSos;
        }else{
            List<Receipts> receipts = receiptsRepository.findByStudentUniqueId(studentUniqueId);
            receiptsSos = new ArrayList<>();
            if(!CollectionUtils.isEmpty(receipts)) {
                for (Receipts receipts1 : receipts) {
                    ReceiptsSo receiptsSo = new ReceiptsSo();
                    BeanUtils.copyProperties(receipts1, receiptsSo);
                    receiptsSos.add(receiptsSo);
                }
            }else{
                throw new ReceiptsException("ERR_EMPTY_RECEIPTS","No Receipts available");
            }
        }
        receiptsSoCacheUtility.add(studentUniqueId,receiptsSos);
        return receiptsSos;
    }

    @Override
    public void addReceipt(PaymentResponseObject paymentResponseObject) {
        Receipts receipts = new Receipts();
        BeanUtils.copyProperties(paymentResponseObject,receipts);
        log.info("Adding Details to receipts {}", receipts.toString());
        receiptsRepository.save(receipts);
        log.info("Successfully added receipts");
    }
}
