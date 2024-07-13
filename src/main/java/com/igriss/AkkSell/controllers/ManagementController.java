package com.igriss.AkkSell.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/management")
@Hidden
public class ManagementController {

    @GetMapping
    public String get(){
        return "GET:: management controller";
    }

    @PostMapping
    public String post(){
        return "POST:: management controller";
    }

    @PutMapping
    public String put(){
        return "PUT:: management controller";
    }

    @DeleteMapping
    public String delete(){
        return "DELETE:: management controller";
    }
}
