package site.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import site.model.Post;
import site.model.PostDao;
import site.model.PostOperations;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

// do not confuse java.util.Date and java.sql.Date
@Controller
public class AdminController
{
    @GetMapping("/admin")
    protected String getAdminPage()
    {
        return "admin";
    }
    
    @GetMapping("/admin/create")
    protected String getCreationPage()
    {
        return "create";
    }
    
    @PostMapping("/admin/create")
    protected String saveCreatedPost(@RequestParam("header-input") String header,
                                    @RequestParam("image-input") MultipartFile image,
                                    @RequestParam("text-input") String text,
                                    @RequestParam("date-input") java.sql.Date sqlDate)
    {
        PostOperations.saveCreatedPost(header, image, text, sqlDate);
        return "redirect:/admin";
    }
    
    @GetMapping("admin/update-or-delete")
    protected String getUpdateAndDeletePage()
    {
        return "forward:/posts?update=true";
    }
    
    @PostMapping("/admin/delete")
    protected String deletePost(@RequestParam("id") int id)
    {
        PostOperations.deletePost(id);
        return "redirect:/admin/update-or-delete";
    }
    
    @GetMapping("/admin/update")
    protected String getUpdatePage(@RequestParam("id") int id, Model model)
    {
        Post postToUpdate = PostDao.getPostById(id);
        model.addAttribute("postToUpdate", postToUpdate);
        return "update";
    }
    
    @PostMapping("/admin/update")
    protected String updatePost(@RequestParam(name = "id", required = false) String id,
                            @RequestParam(name = "header-input", required = false) String header,
                            @RequestParam(name = "image-input", required = false) MultipartFile image,
                            @RequestParam(name = "text-input", required = false) String text,
                            @RequestParam(name = "date-input", required = false) java.sql.Date sqlDate,
                            @RequestParam(name = "delete-previous-image", required = false) boolean deletePreviousImage
    )
    {
        PostOperations.updatePost(id, header, image, text, sqlDate, deletePreviousImage);
        return "admin";
    }
}
