import cn.edu.cuit.utils.MailUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 2017/3/2.
 */
public class MailTest {

    @Test
    public void mailTest() throws Exception{
        MailUtils.sendEmail("563921891@qq.com","hello");
    }

    @Test
    public void dateTest(){
        String time = "2017-5-29";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(time);
            Date date1 = new Date();

            if (date.getTime() > date1.getTime()){
                System.out.println("大");
            }else {
                System.out.println("小");

            }

        } catch (ParseException e) {
            System.out.println("转换失败");
            e.printStackTrace();
        }
    }
}
