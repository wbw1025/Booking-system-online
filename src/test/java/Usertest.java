import cn.edu.cuit.service.HospitalService;
import cn.edu.cuit.service.UserService;
import cn.edu.cuit.service.impl.HospitalServiceImpl;
import cn.edu.cuit.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * Created by admin on 2017/2/22.
 */
public class Usertest {


    private ApplicationContext applicationContext = null;
//    @Before
//    public void init() throws IOException {
//        applicationContext = new ClassPathXmlApplicationContext(
//                "classpath:spring/spring-con*.xml");
//    }

    @Test
    public void md5Test(){
        String password = "123";
        byte[] passmd5 = password.getBytes();
        String m = DigestUtils.md5DigestAsHex(passmd5);
    }

    @Test
    public void uuidTest() throws Exception{
        long now = new Date().getTime();
        Thread.sleep(5000);
        long now1 = new Date().getTime();

        System.out.println(now1-now);
    }

    @Test
    public void addressTest(){
        Integer a = null;
        int a1 = a;
        System.out.println(a1);
    }

    @Test
    public void forTest(){
        List<String> aa = new ArrayList<String>();
        aa.add("a");
        aa.add("b");
        aa.add("c");
        aa.add("d");


//        for (int i = 0;i < aa.size();i++){
//            System.out.println(aa.get(i));
//        }

        for (String i : aa){
            System.out.println(i);
        }
    }


}
