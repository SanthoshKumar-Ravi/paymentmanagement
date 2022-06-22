package com.schoolmanagement.receipts.controller;

import com.schoolmanagement.receipts.schemaobject.ReceiptsSo;
import com.schoolmanagement.receipts.service.ReceiptsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("receipts")
public class ReceiptsController {
    private final ReceiptsService receiptsService;

    @GetMapping("/getReceipts/{studentUniqueId}")
    public List<ReceiptsSo> getReceipts(@ModelAttribute @PathVariable("studentUniqueId") String studentUniqueId) {
        return receiptsService.getReceipts(studentUniqueId);
    }
}
