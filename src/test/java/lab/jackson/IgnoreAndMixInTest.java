package lab.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lab.jackson.domain.Dept;
import lab.jackson.domain.User;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Author: Frank
 * Date: 13-12-20
 *
 * 忽略属性不转换有两种方法：
 * 方法一：在属性上加 @JsonIgnore 注解，对该类的所有转换都起作用
 * 方法二：使用混入类，只在某个转换中起作用，比较灵活。
 *
 */
public class IgnoreAndMixInTest {

    private String path = this.getClass().getResource("/").getPath() + "/" ;

    /**
     * 双向关联序列化测试
     *
     * 双向关联序列化会死循环，报错
     *
     * 解决方法：忽略某个属性，打断循环。
     *
     * 通过对属性加 @JsonIgnore 忽略属性，不进行转换
     */
    @Test
    public void bidirectionTest() {
        lab.jackson.domain.bidirectional.Dept dept = new lab.jackson.domain.bidirectional.Dept(1L, "IT部");
        lab.jackson.domain.bidirectional.User user = new lab.jackson.domain.bidirectional.User(1L, "frank", "1", dept);

        // 建立双向关联关系
        dept.getUsers().add(user);

        // 如果没有断开双向关联，序列化会导致死循环，报以下异常
        // com.fasterxml.jackson.databind.JsonMappingException: Infinite recursion

        // 解决办法，在dept上使用 @JsonIgnore 忽略转换

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(path + "userBidirectional.json"), user);
            objectMapper.writeValue(System.out, user);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 方法二：采用混入忽略某些属性
     *
     */
    @Test
    public void ignoreMaxInTest() {

        lab.jackson.domain.bidirectional.Dept dept = new lab.jackson.domain.bidirectional.Dept(1L, "IT部");
        lab.jackson.domain.bidirectional.User user = new lab.jackson.domain.bidirectional.User(1L, "frank", "1", dept);

        // 建立双向关联关系
        dept.getUsers().add(user);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // 使用混入器，忽略属性id, dept的转换
            objectMapper.addMixInAnnotations(lab.jackson.domain.bidirectional.User.class, IgnoreMixIn.class);
            objectMapper.writeValue(new File(path + "userBidirectional.json"), user);
            objectMapper.writeValue(System.out, user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 混入类
     * 忽略id、dept属性不转换
     */
    private abstract class IgnoreMixIn {
        @JsonIgnore
        private Long id;
        @JsonIgnore
        private Dept dept;

    }

    /**
     * 使用混入改变属性名
     */
    @Test
    public void changePropertyMixInTest () {
        User user = new User(1L, "Frank", "1", null);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.addMixInAnnotations(User.class, ChangePropertyMinIn.class);
        try {
            objectMapper.writeValue(System.out, user);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 混入类
     * 将id属性名改为userId
     */
    private abstract class ChangePropertyMinIn {
        @JsonProperty("userId")
        private Long id;
    }

}
