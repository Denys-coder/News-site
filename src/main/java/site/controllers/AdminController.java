package site.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController
{
    @GetMapping("/admin")
    protected String adminPage()
    {
        return "admin";
    }
}
