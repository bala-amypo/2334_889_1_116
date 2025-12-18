package com.example.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.entity.Contract;
import com.example.project.service.ContractServies;

@RestController
public class ContractController {
    @Autowired
    ContractServies src;
    @PostMapping("/post")
    public Contract postdata(@RequestBody Contract ct){
        return src.savedata(ct);
    }
    @GetMapping("/get")
    public List<Contract>getdata(){
        return src.retdata();
    }
    @GetMapping("/get/{id")
    public Contract updata(@PathVariable int id){
        return src.id(id);
    }
}
