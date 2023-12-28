package com.cinema.repertoire.web.services;

import com.cinema.repertoire.db.instance.InstanceTemp;
import com.cinema.repertoire.db.instance.InstanceTempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstanceTempService {
    private final InstanceTempRepository repository;

    @Autowired
    public InstanceTempService(InstanceTempRepository repository) {
        this.repository = repository;
    }

    public List<InstanceTemp> getAll() {
        return repository.findAll();
    }
}
