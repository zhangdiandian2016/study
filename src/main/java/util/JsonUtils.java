package util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/***
 * @Description Json序列化工具(初始化好objectMapper后性能优于fastJson)
 * @author denny.zhang
 * @date 2020/1/14 5:40 下午
 */
public class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static final ObjectMapper objectMapper = new ObjectMapper() {
        {
            // 未知的字段忽略
            disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            // 空对象不序列化
            setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        }
    };

    /**
     * 将json字符串反序列化成JsonNode
     *
     * @param json
     * @return JsonNode
     */
    public static JsonNode fromJson(String json) {
        try {
            return objectMapper.readTree(json);
        } catch (IOException e) {
            logger.error("反序列化JSON出现异常，json={}", json, e);
        }
        return null;
    }

    /**
     * 将json字符串反序列化成指定类型
     *
     * @param json  json格式的字符串
     * @param clazz 需要反序列化的类型对象
     * @param <T>   需要反序列化的类型
     * @return
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            logger.error("反序列化JSON出现异常，class={},json={}", clazz, json, e);
        }
        return null;
    }

    /**
     * 将json字符串反序列化成指定类型，常用于集合类型的反序列化
     *
     * @param json  json格式的字符串
     * @param clazz 需要反序列化的类型的包装类型对象
     * @param <T>   需要反序列化的类型对象
     * @return
     */
    public static <T> T fromJson(String json, TypeReference clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            logger.error("反序列化JSON出现异常，class={},json={}", clazz, json, e);
        }
        return null;
    }

    /**
     * 将json字符串反序列化成指定类型，常用于集合类型的反序列化
     *
     * @param json  json格式的字符串
     * @param clazz 需要反序列化的类型的包装类型对象
     * @param <T>   需要反序列化的类型对象
     * @return
     * @see JsonUtils#type(Class, Class[])
     */
    public static <T> T fromJson(String json, JavaType clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            logger.error("反序列化JSON出现异常，class={},json={}", clazz, json, e);
        }
        return null;
    }

    /**
     * 構造泛型的Collection Type，如:
     * ArrayList<MyBean>, 则调用constructCollectionType(ArrayList.class,MyBean.class)；
     * HashMap<String,MyBean>, 则调用(HashMap.class,String.class, MyBean.class)
     *
     * @param collectionClass
     * @param elementClasses
     * @return
     */
    public static JavaType type(Class<?> collectionClass, Class<?>... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * 将指定的对象序列化成json字符串
     *
     * @param value
     * @param <T>
     * @return json字符串
     */
    public static <T> String toJson(T value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (IOException e) {
            logger.error("序列化JSON出现异常，value={}", value, e);
        }
        return null;
    }
}
