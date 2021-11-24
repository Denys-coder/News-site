package site.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import site.model.Post;
import site.dao.PostDao;
import site.model.PostOperations;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class PostsController
{
    @GetMapping("/posts")
    public String getShowPostsPage(HttpServletRequest request, Model model, @RequestParam(value = "update", required = false) boolean update)
    {
        if (update && request.isUserInRole("ADMIN"))
        {
            model.addAttribute("update", true);
        }
        
        // truncate text to 150 characters and add " ..."
        ArrayList<Post> posts = PostOperations.truncateEachPostTextTo150Symbols(PostDao.getAllPosts());
        model.addAttribute("posts", posts);
        
        return "posts";
    }
    
    @GetMapping("/posts/{id}")
    public String getShowPostByIdPage(Model model, @PathVariable("id") int id)
    {
        model.addAttribute("post", PostDao.getPostById(id));
        return "post";
    }
}
