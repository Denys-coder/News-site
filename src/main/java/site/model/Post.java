package site.model;

public class Post
{
    private String header;
    private String text;
    private String imageFileName;
    private String data;
    
    public Post(String header, String text, String imageFileName, String data)
    {
        this.header = header;
        this.text = text;
        this.imageFileName = imageFileName;
        this.data = data;
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
