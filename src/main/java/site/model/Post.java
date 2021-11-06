package site.model;

public class Post
{
    private int id;
    private String header;
    private String text;
    private String imageFileName;
    private String data;
    
    public Post(int id, String header, String text, String imageFileName, String data)
    {
        this.id = id;
        this.header = header;
        this.text = text;
        this.imageFileName = imageFileName;
        this.data = data;
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
    
    public String getText()
    {
        return text;
    }
    
    public void setText(String text)
    {
        this.text = text;
    }
    
    public String getImageFileName()
    {
        return imageFileName;
    }
    
    public void setImageFileName(String imageFileName)
    {
        this.imageFileName = imageFileName;
    }
    
    public String getData()
    {
        return data;
    }
    
    public void setData(String data)
    {
        this.data = data;
    }
}
