import com.fasterxml.jackson.databind.JsonNode;
import com.yienx.utils.JsonUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        // 读取第一组的，get(0)
        System.out.println(jsonNode.get(0).get("label")); // "电脑"
        System.out.println(jsonNode.get(0).get("label").asText()); // 电脑
        System.out.println(jsonNode.get(0).asText("label")); // 电脑
    }
}