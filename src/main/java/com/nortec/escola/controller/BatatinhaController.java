package com.nortec.escola.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nortec.escola.repository.BatatinhaRepository;

@RestController
@RequestMapping(value = "/batatinha")
public class BatatinhaController {

    @Autowired
    private BatatinhaRepository batatinhaRepository;
    
}