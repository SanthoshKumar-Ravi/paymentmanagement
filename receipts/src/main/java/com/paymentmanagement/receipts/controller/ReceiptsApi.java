package com.paymentmanagement.receipts.controller;

import com.paymentmanagement.receipts.schemaobject.ReceiptsSo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Api(value="/receipts")
@RequestMapping("/receipts")
public interface ReceiptsApi {

    @ApiOperation(value = "Get Payment Receipt", nickname = "Payment", notes = "Receipts API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Receipts fetched successfully"),
            @ApiResponse(code = 404, message = "No data found")
    })
    @GetMapping("/getReceipts/{studentUniqueId}")
    ResponseEntity<List<ReceiptsSo>> getReceipts(@ModelAttribute @PathVariable("studentUniqueId") String studentUniqueId);
}
