import com.fasterxml.jackson.databind.JsonNode;
import com.yienx.utils.JsonUtil;
import lombok.Data;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author wangyanbo29
 * @Date 2023/7/20
 * @Description
 */
public class JsonUtilTest {
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    static final String jsonStr = "[{\"value\":123,\"label\":\"电脑\",\"subList\":[{\"value\":321," +
            "\"label\":\"笔记本\",\"subList\":[{\"value\":333,\"label\":\"Mac\"}]}]}," +
            "{\"value\":456,\"label\":\"家具\",\"subList\":[{\"value\":654,\"label\":" +
            "\"衣柜\",\"subList\":[{\"value\":666,\"label\":\"胡桃木双开门\",\"subList\":[]}]}]}," +
            "{\"value\":789,\"label\":\"电器\",\"subList\":[]}]\n";


    @Test
    public void test1() {
        JsonNode jsonNode = JsonUtil.json2Node(JsonUtilTest.jsonStr);
        System.out.println(jsonNode);
        System.out.println("jsonNode是数组？" + jsonNode.isArray());
        log.info("jsonNode是数组? {}", jsonNode.isArray());
        log.isInfoEnabled();
        // 读取第一组的，get(0)
        System.out.println(jsonNode.get(0).get("label")); // "电脑"
        System.out.println(jsonNode.get(0).get("label").asText()); // 电脑
        System.out.println(jsonNode.get(0).asText("label")); // 电脑
    }

    @Test
    public void test2() {
        // [{"NewStoreType":3, "SupportedSkuIdList":["333333", "444444"]}]
        NewStoreTypeInfo newStoreTypeInfo = new NewStoreTypeInfo();
        newStoreTypeInfo.setNewStoreType(3);
        List<Long> skuIds = Arrays.asList(33333L, 44444L);
        newStoreTypeInfo.setSupportedSkuIdList(Arrays.asList(33333L, 44444L));
        System.out.println("Arrays.asList: " + JsonUtil.write2JsonStr(Arrays.asList(newStoreTypeInfo)));
        List<NewStoreTypeInfo> newStoreTypeInfos = new ArrayList<>();
        newStoreTypeInfos.add(newStoreTypeInfo);
        System.out.println("List: " + JsonUtil.write2JsonStr(Arrays.asList(newStoreTypeInfo)));

    }

    @Test
    public void test3()  {
        Integer[] arrs = new Integer[]{1,2,3,4,5};
        System.out.println(JsonUtil.write2JsonStr(arrs));
        List<Integer> list1 = Arrays.asList(arrs);
        // list1.add(1);
        System.out.println(JsonUtil.write2JsonStr(list1));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(arrs));
        list2.add(1);
        System.out.println(JsonUtil.write2JsonStr(list2));
    }
}

@Data
class NewStoreTypeInfo {
    private int newStoreType;
    private List<Long> supportedSkuIdList;
}