package site.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import site.model.Post;
import site.model.PostDao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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
    protected String getCreatedPost(@RequestParam("heading") String header,
                                    @RequestParam("image") MultipartFile image,
                                    @RequestParam("text") String text,
                                    @RequestParam("date") java.sql.Date sqlDate) throws IOException
    {
        int insertedId = PostDao.addPost(header, null, text, sqlDate);
        
        if (!image.isEmpty())
        {
            String imageName = "img" + insertedId;
            PostDao.updateImageName(imageName, insertedId);
            
            File file = new File("src/main/resources/static/images/" + imageName);
            try (OutputStream os = new FileOutputStream(file))
            {
                os.write(image.getBytes());
            }
        }
        
        return "redirect:/admin";
    }
    
    @GetMapping("admin/update-or-delete")
    protected String updateOrDelete()
    {
        return "forward:/posts?update=true";
    }
    
    @PostMapping("/admin/delete")
    protected String deletePost(@RequestParam("id") int id)
    {
        PostDao.deletePost(id);
        
        File file = new File("src/main/resources/static/images/img" + id);
        file.delete();
        
        return "redirect:/admin/update-or-delete";
    }
    
    @GetMapping("/admin/update")
    protected String updateForm(@RequestParam("id") int id, Model model)
    {
        Post postToUpdate = PostDao.getPostById(id);
        model.addAttribute("postToUpdate", postToUpdate);
        return "update";
    }
    
    @PostMapping("/admin/update")
    protected String update(@RequestParam(name = "id", required = false) String id,
                            @RequestParam(name = "heading", required = false) String header,
                            @RequestParam(name = "image", required = false) MultipartFile image,
                            @RequestParam(name = "text", required = false) String text,
                            @RequestParam(name = "date", required = false) java.sql.Date sqlDate,
                            @RequestParam(name = "delete-previous-image", required = false) boolean deletePreviousImage
    ) throws IOException
    {
        int idInteger = Integer.parseInt(id);
        
        // header
        PostDao.updateHeader(header, idInteger);
        
        // image
        if (deletePreviousImage) // delete image file
        {
            File file = new File("src/main/resources/static/images/img" + id);
            file.delete();
        }
        if (image != null && !deletePreviousImage) // update image file
        {
            File file = new File("src/main/resources/static/images/img" + id);
            try (OutputStream os = new FileOutputStream(file))
            {
                os.write(image.getBytes());
            }
        }
        
        // image name
        if (deletePreviousImage) // delete image
        {
            PostDao.updateImageName(null, idInteger);
        }
        if (image != null && !deletePreviousImage && PostDao.getPostById(idInteger).getImageFileName() == null) // add image
        {
            PostDao.updateImageName("img" + idInteger, idInteger);
        }
        
        // text
        PostDao.updateText(text, idInteger);
        
        // date
        PostDao.updateDate(sqlDate, idInteger);
        
        return "admin";
    }
}
