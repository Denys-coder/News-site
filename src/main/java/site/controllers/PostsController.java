package site.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostsController
{
    @GetMapping("/posts")
    public String showPosts(Model model)
    {
        // вытащит заголовоки и даты из базы данных
        
        // засунуть заголовки и даты в модель для представления в виде объекта Iterable
        
        return "posts";
    }
}
