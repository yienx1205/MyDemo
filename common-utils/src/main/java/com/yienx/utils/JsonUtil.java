package com.yienx.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author wangyanbo29
 * @Date 2023/7/20
 * @Description
 */

public class JsonUtil {
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    private static ObjectMapper mapper = new ObjectMapper();
    // static {
    //     mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    //     mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    //     //配置忽略多余字段
    //     mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    //     mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    //     mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE).setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    // }
    private JsonUtil() {
    }

    public static synchronized ObjectMapper getObjectMapper() {
        return mapper;
    }

    public static String write2JsonStr(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException var2) {
            log.error("write2JsonStr, JsonUtil exception", var2);
            return "";
        }
    }

    public static <T> T json2Object(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception var3) {
            log.error("json2Object, JsonUtil exception, json is:" + json, var3);
            return null;
        }
    }

    public static Map<String, Object> json2Map(String json) {
        try {
            return (Map)(StringUtils.isBlank(json) ? new HashMap() : (Map)mapper.readValue(json, Map.class));
        } catch (Exception var2) {
            log.error("json2Map, JsonUtil exception, json is:" + json, var2);
            return new HashMap();
        }
    }

    public static List<Map<String, Object>> jsonArray2List(String jsonArray) {
        try {
            return (List)mapper.readValue(jsonArray, List.class);
        } catch (Exception var2) {
            log.error("jsonArray2List, JsonUtil exception, json is:" + jsonArray, var2);
            return new ArrayList();
        }
    }

    public static <T> List<T> jsonStr2ListObject(String jsonStr, Class<T> clazz) throws Exception {
        if (StringUtils.isBlank(jsonStr)) {
            return null;
        } else {
            JavaType javaType = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
            List<T> result = (List)mapper.readValue(jsonStr, javaType);
            return result;
        }
    }

    public static JsonNode json2Node(String json) {
        try {
            return (JsonNode)mapper.readValue(json, JsonNode.class);
        } catch (Exception var2) {
            log.error("json2Node, JsonUtil exception, json is:" + json, var2);
            return null;
        }
    }

    public static void node2Writer(Writer writer, JsonNode node) {
        try {
            mapper.writeValue(writer, node);
        } catch (Exception var3) {
            log.error("node2Writer, JsonUtil exception, json is:" + node, var3);
        }

    }

    public static String readJson(String path) {
        File file = new File(path);
        BufferedReader reader = null;
        StringBuffer data = new StringBuffer();
        if (file.exists()) {
            try {
                reader = new BufferedReader(new FileReader(file));
                String temp = null;

                while((temp = reader.readLine()) != null) {
                    data.append(temp);
                }
            } catch (FileNotFoundException var15) {
                var15.printStackTrace();
            } catch (Exception var16) {
                var16.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException var14) {
                        var14.printStackTrace();
                    }
                }

            }

            log.info("#readJson-->>" + data.toString());
        }

        return data.toString();
    }

    public static void writeJson(String path, Object json, String fileName) {
        BufferedWriter writer = null;
        File file = new File(path + fileName + ".json");
        if (!file.exists() && file.canWrite()) {
            try {
                file.createNewFile();
            } catch (IOException var17) {
                var17.printStackTrace();
                log.error("#writeJson 创建文件失败-->>" + path + fileName + ".json " + var17.getMessage());
            }
        }

        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(json.toString());
        } catch (Exception var15) {
            var15.printStackTrace();
            log.error("#writeJson 写入文件失败-->>" + path + fileName + ".json " + var15.getMessage());
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException var14) {
                var14.printStackTrace();
            }

        }

        log.info("#writeJson -->>文件写入成功！");
    }

    public static boolean existsWriteJsonPath(String path) {
        File dir = new File(path);
        if (dir.exists()) {
            log.info("#existsWriteJsonPath -->>文件目录已存在！");
            return true;
        } else if (dir.mkdirs()) {
            log.info("#existsWriteJsonPath -->>文件目录创建成功！");
            return true;
        } else {
            log.info("#existsWriteJsonPath -->>文件目录创建失败！");
            return false;
        }
    }




}
