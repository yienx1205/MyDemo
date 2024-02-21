import com.yienx.mockito.DemoDao;
import com.yienx.mockito.DemoService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @Author wangyanbo29
 * @Date 2024/2/20
 * @Description
 */
public class MockitoTest {
    @Test
    public void test01() {
        // mock DemoDao instance
        DemoDao mockDemoDao = Mockito.mock(DemoDao.class);

        // 使用 mockito 对 getDemoStatus 方法打桩
        Mockito.when(mockDemoDao.getDemoStatus()).thenReturn(1);

        // 调用 mock 对象的 getDemoStatus 方法，结果永远是 1
        Assert.assertEquals(1, mockDemoDao.getDemoStatus());

        // mock DemoService
        DemoService mockDemoService = new DemoService(mockDemoDao);
        Assert.assertEquals(1, mockDemoService.getDemoStatus() );
    }
}
