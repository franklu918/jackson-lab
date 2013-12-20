package lab.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lab.jackson.domain.Dept;
import lab.jackson.domain.User;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Frank
 * Date: 13-12-20
 */
public class SerializeAndDeserializeTest {

    private String path = this.getClass().getResource("/").getPath() + "/" ;

    /**
     * 序列化
     */
    @Test
    public void serializeTest() {
        Dept dept = new Dept(1L, "技术部");
        User user = new User(1L, "Frank", "1", dept) ;

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(path + "user.json"), user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 序列化列表
     */
    @Test
    public void serializeListTest() {
        List<User> list = new ArrayList<User>();
        Dept dept = new Dept(1L, "技术部");
        list.add(new User(1L, "Frank", "1", dept)) ;
        list.add(new User(2L, "Alien", "2", dept)) ;
        list.add(new User(3L, "Jackson", "3", dept)) ;

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String filePath = this.getClass().getResource("/").getPath() + "/user.json";
            objectMapper.writeValue(new File(path + "userList.json"), list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 序列化Map
     */
    @Test
    public void serializeMapTest() {

        Dept dept = new Dept(1L, "技术部");
        Map<Long, User> map = new HashMap<Long, User>();
        User user1 = new User(1L, "Frank", "1", dept);
        User user2 = new User(2L, "Alien", "2", dept);
        User user3 = new User(3L, "Jackson", "3", dept);
        map.put(user1.getId(), user1);
        map.put(user2.getId(), user2);
        map.put(user3.getId(), user3);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(path + "userMap.json"), map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反序列化
     */
    @Test
    public void deserializeTest() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            User user = objectMapper.readValue(new File(path + "user.json"), User.class);
            System.out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反序列化列表
     */
    @Test
    public void deserializeListTest() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<User> list = objectMapper.readValue(new File(path + "userList.json"),
                    new TypeReference<List<User>>(){});
            System.out.println(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
