package site.model;

import java.sql.*;
import java.util.ArrayList;

// do not confuse java.util.Date and java.sql.Date
public class PostDao
{
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/news";
    static final String USER = "postgres";
    static final String PASSWORD = "123";
    
    public static ArrayList<Post> getAllPosts()
    {
        ArrayList<Post> posts = new ArrayList<>();
        
        String sqlQuery = "SELECT * FROM news ORDER BY id;";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
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
    
    public static void addPost(Post newPost)
    {
        String sqlQuery = "INSERT INTO news VALUES (?, ?, ?, ?, ?);";
    
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)
        )
        {
            preparedStatement.setInt(1, newPost.getId());
            preparedStatement.setString(2, newPost.getHeader());
            preparedStatement.setString(3, newPost.getImageFileName());
            preparedStatement.setString(4, newPost.getText());
            preparedStatement.setDate(5, new java.sql.Date(newPost.getCreationDate().getTime()));
            
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
        
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
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
    
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
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
    
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
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
    
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
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
    
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
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
