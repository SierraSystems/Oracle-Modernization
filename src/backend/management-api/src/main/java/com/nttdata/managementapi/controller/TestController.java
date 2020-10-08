package com.nttdata.managementapi.controller;

import com.nttdata.pocdata.InventoriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class TestController {

    private final InventoriesService inventoriesService;

    public TestController(InventoriesService inventoriesService) {
        this.inventoriesService = inventoriesService;
    }

    @GetMapping(value = "/test/getStuff")
    public ResponseEntity<Integer> getResult() {
        return ResponseEntity.ok(inventoriesService.getStockForLocationAndType(BigInteger.valueOf(223), BigInteger.valueOf(8)));
    }
}
