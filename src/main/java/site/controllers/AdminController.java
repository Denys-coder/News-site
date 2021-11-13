package site.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

// do not confuse java.util.Date and java.sql.Date
@Controller
public class AdminController
{
    @GetMapping("/admin")
    protected String admin()
    {
        return "admin";
    }
    
    @GetMapping("/admin/create")
    protected String create()
    {
        return "create";
    }
    
    @PostMapping("/admin/create")
    protected String getCreatedPost(@RequestParam("heading") String heading,
                                    @RequestParam("text") String text,
                                    @RequestParam("date") java.sql.Date sqlDate,
                                    @RequestParam("image") MultipartFile image)
    {
        // записать заголовок в БД
        // записать текст в БД
        // записать дату в БД
        // записать название картинки в БД
        // сохранить картинку на диск
        
        return "redirect:/admin";
    }
    
    @GetMapping("admin/modify-or-delete")
    protected String modifyOrDelete()
    {
        return "forward:/posts?modify=true";
    }
}
