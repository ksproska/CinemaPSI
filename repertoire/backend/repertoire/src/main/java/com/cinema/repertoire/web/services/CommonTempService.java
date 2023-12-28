package com.cinema.repertoire.web.services;

import com.cinema.repertoire.db.common.CommonTemp;
import com.cinema.repertoire.db.common.CommonTempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonTempService {
    private final CommonTempRepository repository;

    @Autowired
    public CommonTempService(CommonTempRepository repository) {
        this.repository = repository;
    }

    public List<CommonTemp> getAll() {
        return repository.findAll();
    }
}
