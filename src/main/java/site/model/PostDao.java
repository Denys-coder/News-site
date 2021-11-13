package site.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;

// do not confuse java.util.Date and java.sql.Date
@Component
public class PostDao
{
    public static String databaseUrl;
    public static String user;
    public static String password;
    
    @Value("${url}")
    public void setUrlStatic(String url)
    {
        PostDao.databaseUrl = url;
    }
    
    @Value("${user}")
    public void setUserStatic(String user)
    {
        PostDao.user = user;
    }
    
    @Value("${password}")
    public void setPasswordStatic(String password)
    {
        PostDao.password = password;
    }
    
    public static ArrayList<Post> getAllPosts()
    {
        ArrayList<Post> posts = new ArrayList<>();
        
        String sqlQuery = "SELECT * FROM news ORDER BY id;";
        try (Connection connection = DriverManager.getConnection(databaseUrl, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)
        )
        {
            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String header = resultSet.getString("header");
                String image_filename = resultSet.getString("image_filename");
                String text = resultSet.getString("text");
                java.util.Date creationDate = resultSet.getDate("creation_date");
                
                posts.add(new Post(id, header, image_filename, text, creationDate));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        
        return posts;
    }
    
    public static void addPost(String header, String imageFileName, String text, Date creationDate)
    {
        String sqlQuery = "INSERT INTO news VALUES (DEFAULT, ?, ?, ?, ?);";
        
        try (Connection connection = DriverManager.getConnection(databaseUrl, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)
        )
        {
            preparedStatement.setString(1, header);
            preparedStatement.setString(2, imageFileName);
            preparedStatement.setString(3, text);
            preparedStatement.setDate(4, new java.sql.Date(creationDate.getTime()));
            
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public static void deletePost(int postId)
    {
        String sqlQuery = "DELETE FROM news WHERE id = ?;";
        
        try (Connection connection = DriverManager.getConnection(databaseUrl, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)
        )
        {
            preparedStatement.setInt(1, postId);
            
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public static void updateHeader(String header, int postId)
    {
        String sqlQuery = "UPDATE news SET header = ? WHERE id = ?;";
        
        try (Connection connection = DriverManager.getConnection(databaseUrl, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)
        )
        {
            preparedStatement.setString(1, header);
            preparedStatement.setInt(2, postId);
            
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public static void updateText(String text, int postId)
    {
        String sqlQuery = "UPDATE news SET text = ? WHERE id = ?;";
        
        try (Connection connection = DriverManager.getConnection(databaseUrl, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)
        )
        {
            preparedStatement.setString(1, text);
            preparedStatement.setInt(2, postId);
            
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public static void updateImageName(String imageFilename, int postId)
    {
        String sqlQuery = "UPDATE news SET image_filename = ? WHERE id = ?;";
        
        try (Connection connection = DriverManager.getConnection(databaseUrl, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)
        )
        {
            preparedStatement.setString(1, imageFilename);
            preparedStatement.setInt(2, postId);
            
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public static void updateDate(java.util.Date date, int postId)
    {
        String sqlQuery = "UPDATE news SET creation_date = ? WHERE id = ?;";
        
        try (Connection connection = DriverManager.getConnection(databaseUrl, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)
        )
        {
            preparedStatement.setDate(1, new java.sql.Date(date.getTime()));
            preparedStatement.setInt(2, postId);
            
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
