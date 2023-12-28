package com.cinema.repertoire.web.controllers;

import com.cinema.repertoire.db.common.CommonTemp;
import com.cinema.repertoire.web.services.CommonTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CommonTempController {
    private final CommonTempService service;

    @Autowired
    public CommonTempController(CommonTempService service) {
        this.service = service;
    }
    @GetMapping("/common-test")
    public ResponseEntity<List<CommonTemp>> commonListAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
