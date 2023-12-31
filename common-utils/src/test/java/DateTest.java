import com.yienx.utils.DateTimeUtil;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;


/**
 * @Author wangyanbo29
 * @Date 2023/8/4
 * @Description
 */
public class DateTest {
    /*
    * LocalDateTime = 2017-06-16T15:38:48.580
    * Date = Fri Jun 16 15:38:48 CST 2017
    * */

    @Test
    public void test1() throws ParseException {
        Date start = DateTimeUtil.strToDate("2023-07-26 15:23:00");
        Date end = DateTimeUtil.strToDate("2023-09-26 12:23:00");
        long days = DateTimeUtil.differenceDays(start, end);
        System.out.println(days);
    }
}
