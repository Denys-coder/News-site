package site.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        for (Post post : posts)
        {
            String text = post.getText();
            if (text.length() > 150)
            {
                text = text.substring(0, 151);
                text = text.trim().concat(" ...");
            }
            post.setText(text);
        }
        
        model.addAttribute("posts", posts);
        return "posts";
    }
    
    @GetMapping("/posts/{id}")
    public String showPostById(Model model, @PathVariable("id") int id)
    {
        model.addAttribute("post", PostDao.getAllPosts().get(id - 1));
        return "post";
    }
}
