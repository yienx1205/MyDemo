import org.junit.Test;

import java.util.UUID;

/**
 * @Author wangyanbo29
 * @Date 2023/10/30
 * @Description
 */
public class MyTest {

    @Test
    public void test01() {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString().replace("-", "").toUpperCase();
        System.out.println(uuidString);
    }
}
