package com.cinema.repertoire.web.controllers;

import com.cinema.repertoire.db.instance.InstanceTemp;
import com.cinema.repertoire.web.services.InstanceTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class InstanceTempController {
    private final InstanceTempService service;

    @Autowired
    public InstanceTempController(InstanceTempService service) {
        this.service = service;
    }
    @GetMapping("/instance-test")
    public ResponseEntity<List<InstanceTemp>> commonListAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
