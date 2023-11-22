import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

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

    @Test
    public void test02() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int n = 3; // 每次获取的数据个数
        IntStream.range(0, (list.size() + n - 1) / n)
                .mapToObj(i -> list.subList(i * n, Math.min((i + 1) * n, list.size())))
                .forEach(sublist -> {
                    // 处理sublist中的数据
                    System.out.println(sublist);
                });
    }


    @Test
    public void test03() {
        StringBuilder sb1 = new StringBuilder();
        sb1.append(new BigDecimal("0.01").multiply(new BigDecimal(10)).stripTrailingZeros().toPlainString());
        System.out.println(sb1.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(new BigDecimal("9").multiply(new BigDecimal(10)).stripTrailingZeros().toPlainString());
        System.out.println(sb2.toString());
    }
}
