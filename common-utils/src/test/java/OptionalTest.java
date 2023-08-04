import org.junit.Test;

import java.util.Optional;

/**
 * @Author wangyanbo29
 * @Date 2023/8/4
 * @Description
 */

public class OptionalTest {
    @Test
    public void test1() {
        // Optional.of(), 如果内部为空，则会抛空指针异常，不会执行orElse
        Integer tempOf = (Integer) Optional.of(1).orElse(0);
        // Optional.ofNullable(), 如果内部为空，则会执行orElse, 取其中的other
        Integer tempOfNullable = (Integer) Optional.ofNullable(null).orElse(0);
        System.out.println(tempOfNullable);
    }
}
