package site.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
    
    @GetMapping("admin/modify-or-delete")
    protected String modifyOrDelete()
    {
        return "forward:/posts?modify=true";
    }
}
