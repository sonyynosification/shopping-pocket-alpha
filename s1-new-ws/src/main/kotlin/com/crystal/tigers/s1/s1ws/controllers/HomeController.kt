package com.crystal.tigers.s1.s1ws.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    @GetMapping("/")
    fun defaultHome() =
            "S1 Web Services"
}