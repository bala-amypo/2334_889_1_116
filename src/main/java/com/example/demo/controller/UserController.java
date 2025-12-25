package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @PostMapping
    public UserDto create(@RequestBody UserDto dto) {
        return dto;
    }
}
