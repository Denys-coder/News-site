package site.model;

import java.util.Date;

public class Post
{
    private int id;
    private String header;
    private String imageFileName;
    private String text;
    private Date creationDate;
    
    public Post(int id, String header, String imageFileName, String text, Date creationDate)
    {
        this.id = id;
        this.header = header;
        this.imageFileName = imageFileName;
        this.text = text;
        this.creationDate = creationDate;
    }
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public String getHeader()
    {
        return header;
    }
    
    public void setHeader(String header)
    {
        this.header = header;
    }
    
    public String getImageFileName()
    {
        return imageFileName;
    }
    
    public void setImageFileName(String imageFileName)
    {
        this.imageFileName = imageFileName;
    }
    
    public String getText()
    {
        return text;
    }
    
    public void setText(String text)
    {
        this.text = text;
    }
    
    public Date getCreationDate()
    {
        return creationDate;
    }
    
    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }
}
