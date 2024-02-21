import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Random;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @Author wangyanbo29
 * @Date 2024/2/21
 * @Description @Mock 注解可以理解为对 mock 方法的一个替代。
 */
@RunWith(MockitoJUnitRunner.class)
public class MockAnnotationTest {

    // 使用该注解时, 要使用MockitoAnnotations.initMocks 方法，
    // 让注解生效, 比如放在@Before方法中初始化。
    // 比较优雅优雅的写法是用MockitoJUnitRunner, 它可以自动执行MockitoAnnotations.initMocks 方法.
    // @Before
    // public void init(){
    //     MockitoAnnotations.initMocks(this);
    // }

    // 代替 Random mockRandom = mock(Random.class);
    @Mock
    private Random random;

    @Test
    public void test() {
        when(random.nextInt()).thenReturn(100);
        Assert.assertEquals(100, random.nextInt());
    }


}
