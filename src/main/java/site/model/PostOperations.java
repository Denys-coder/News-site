package site.model;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class PostOperations
{
    public static void saveCreatedPost(String header, MultipartFile image, String text, java.sql.Date sqlDate)
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
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public static void deletePost(int id)
    {
        PostDao.deletePost(id);
        File file = new File("src/main/resources/static/images/img" + id);
        file.delete();
    }
    
    public static void updatePost(String id, String header, MultipartFile image, String text, java.sql.Date sqlDate, boolean deletePreviousImage)
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
            catch (IOException e)
            {
                e.printStackTrace();
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
    }
    
    public static ArrayList<Post> truncateEachPostTextTo150Symbols(ArrayList<Post> posts)
    {
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
        
        return posts;
    }
}
