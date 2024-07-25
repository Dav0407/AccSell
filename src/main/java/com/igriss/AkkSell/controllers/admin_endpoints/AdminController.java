package com.igriss.AkkSell.controllers.admin_endpoints;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@Hidden
public class AdminController {

    @GetMapping
    public String get(){
        return "GET:: admin controller";
    }

    @PostMapping
    public String post(){
        return "POST:: admin controller";
    }

    @PutMapping
    public String put(){
        return "PUT:: admin controller";
    }

    @DeleteMapping
    public String delete(){
        return "DELETE:: admin controller";
    }
}
