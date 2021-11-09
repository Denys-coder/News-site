package site.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import site.model.Post;
import site.model.PostDao;

import java.util.ArrayList;

@Controller
public class PostsController
{
    @GetMapping("/posts")
    public String showPosts(Model model)
    {
        // truncate text to 150 characters and add " ..."
        ArrayList<Post> posts = PostDao.getAllPosts();
        for (int i = 0; i < posts.size(); i++)
        {
            String text = posts.get(i).getText();
            if (text.length() > 150)
            {
                text = text.substring(0, 151);
                text = text.trim().concat(" ...");
            }
            posts.get(i).setText(text);
        }
        
        model.addAttribute("posts", posts);
        return "posts";
    }
}
