package com.firefly.bankapp.controller;

import com.firefly.bankapp.entity.BillEntity;
import com.firefly.bankapp.service.BillService;
import lombok.RequiredArgsConstructor;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bill")
public class BillController {
    private final BillService billService;

    @GetMapping("/{code}")
    public ResponseEntity<Object> transfer(@PathVariable String code, @RequestParam("category") String category) {
        Map<String, Object> responseBody = new HashMap<>();
        try {
            BillEntity billEntity = billService.getBill(code, category);
            return ResponseEntity.ok(billEntity);
        } catch (EmptyResultDataAccessException ee) {
            responseBody.put("error", ee.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
        }
    }

    @PostMapping("/{code}/pay")
    public ResponseEntity<Object> payBill(@PathVariable String code, @RequestHeader("Authorization") String token) {
        Map<String, Object> responseBody = new HashMap<>();
        try {
            billService.payBill(code, token.substring(7));
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException ee) {
            responseBody.put("error", ee.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
        } catch (IllegalArgumentException ie) {
            responseBody.put("error", ie.getMessage());
            return ResponseEntity.badRequest().body(responseBody);
        }
    }
}