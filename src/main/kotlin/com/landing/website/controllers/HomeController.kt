package com.landing.website.controllers

import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping


@Controller
class HomeController {

    @GetMapping("/")
    fun index(): String {
        return "index";
    }

    @GetMapping("/git")
    fun example(request: HttpServletRequest): String {
        return "git";
    }

    @GetMapping("/sidebar")
    fun getSidebar(): String {
        return "fragments/sidebar"
    }

}