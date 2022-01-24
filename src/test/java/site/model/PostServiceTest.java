package site.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest
{
    
    @Test
    void truncateEachPostTextTo150Symbols()
    {
        // given
        // 160 characters text
        String longText = "123456789\n" + // 1
                "123456789\n" + // 2
                "123456789\n" + // 3
                "123456789\n" + // 4
                "123456789\n" + // 5
                "123456789\n" + // 6
                "123456789\n" + // 7
                "123456789\n" + // 8
                "123456789\n" + // 9
                "123456789\n" + // 10
                "123456789\n" + // 11
                "123456789\n" + // 12
                "123456789\n" + // 13
                "123456789\n" + // 14
                "123456789\n" + // 15
                "123456789\n"; // 16
        ArrayList<Post> inputArray = new ArrayList<>(8);
        inputArray.add(new Post(1, "header", "imageName", null, new Date())); // null
        inputArray.add(new Post(1, "header", "imageName", longText.substring(0, 0), new Date())); // ""
        inputArray.add(new Post(1, "header", "imageName", longText.substring(0, 1), new Date())); // 1
        inputArray.add(new Post(1, "header", "imageName", longText.substring(0, 2), new Date())); // 2
        inputArray.add(new Post(1, "header", "imageName", longText.substring(0, 10), new Date())); // 10
        inputArray.add(new Post(1, "header", "imageName", longText.substring(0, 149), new Date())); // 149
        inputArray.add(new Post(1, "header", "imageName", longText.substring(0, 150), new Date())); // 150
        inputArray.add(new Post(1, "header", "imageName", longText.substring(0, 151), new Date())); // 151
        
        // when
        ArrayList<Post> outputArray = PostService.truncateEachPostTextTo150Symbols(inputArray);
        
        // then
        System.out.println(outputArray.get(0).getText());
        assertNull(outputArray.get(0).getText());
        assertEquals(longText.substring(0, 0), outputArray.get(1).getText());
        assertEquals(longText.substring(0, 1), outputArray.get(2).getText());
        assertEquals(longText.substring(0, 2), outputArray.get(3).getText());
        assertEquals(longText.substring(0, 10), outputArray.get(4).getText());
        assertEquals(longText.substring(0, 149), outputArray.get(5).getText());
        assertEquals(longText.substring(0, 150) + " ...", outputArray.get(6).getText());
        assertEquals(longText.substring(0, 150) + " ...", outputArray.get(7).getText());
    }
}