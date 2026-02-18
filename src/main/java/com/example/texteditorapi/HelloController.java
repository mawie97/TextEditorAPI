package com.example.texteditorapi; // <-- replace with your actual package

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "ok";
    }
}
