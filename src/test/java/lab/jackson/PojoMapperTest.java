package lab.jackson;

import lab.jackson.domain.MyPojo;
import lab.jackson.mapper.PojoMapper;
import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Frank
 * Date: 13-12-20
 */
public class PojoMapperTest {

    @Test
    public void pojoMapperTest() {

        // Create a POJO
        MyPojo pojo = new MyPojo();
        // Populate it with some data
        pojo.setName("Lucky");
        pojo.setOn(true);
        Map<String,Date> map = new HashMap<String,Date>();
        map.put("now", new Date());
        pojo.setMap(map);

        // Map it to JSON and then back again
        try {
            String pojoAsString = PojoMapper.toJson(pojo, true);
            System.out.println("POJO as string:\n" + pojoAsString + "\n");
            MyPojo pojo2 = (MyPojo) PojoMapper.fromJson(pojoAsString, MyPojo.class);
            System.out.println("POJO toString():\n" + pojo2 + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create another POJO
        MyPojo pojo3 = new MyPojo();
        pojo3.setName("Baldwin");
        pojo3.setOn(false);
        Map<String,Date> map2 = new HashMap<String,Date>();
        map2.put("now", new Date());
        pojo3.setMap(map2);

        // Map it to JSON, store it on disk and then read it back
        try {
            FileWriter fw = new FileWriter("pojo.txt");
            PojoMapper.toJson(pojo3, fw, true);

            FileReader fr = new FileReader("pojo.txt");

            MyPojo pojo4 = (MyPojo) PojoMapper.fromJson(fr, MyPojo.class);
            System.out.println("POJO read from file:\n" + pojo4);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
