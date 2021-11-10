package site.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController
{
    @GetMapping("/admin")
    protected String admin()
    {
        return "admin";
    }
    
    @GetMapping("admin/modify-or-delete")
    protected String modifyOrDelete()
    {
        return "forward:/posts?modify=true";
    }
}
