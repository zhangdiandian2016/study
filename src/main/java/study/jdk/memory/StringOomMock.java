package study.jdk.memory;

import com.alibaba.fastjson.JSON;
import com.carrotsearch.sizeof.RamUsageEstimator;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试字符串常量池内存溢出
 */
public class StringOomMock {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i=0;i< Integer.MAX_VALUE;i++){
            String str = String.valueOf(i*100000);
            // intern()是为了加进字符串常量池(i不同-》字符串不同-》添加新字符串对象进池子)，加入list是为了不让gc回收str
            list.add(str.intern());
            //System.out.println("str="+str);
            String string = JSON.toJSONString(list).toString();
            System.out.println("插件=" + RamUsageEstimator.sizeOf(string) + ",json=" + string.getBytes().length);
        }
    }
}