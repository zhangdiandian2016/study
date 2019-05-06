package study.guava.collect;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description 
 * @author denny
 * @date 2018/7/27 下午2:46
 */
public class UtilityClassesTest {

    public static void main(String[] args) {
        /** 1.Iterables 迭代器工具集 */
        System.out.println("=========Iterables=========");
        Iterable<Integer> concate = Iterables.concat(Ints.asList(1,2,3),Ints.asList(2,3,4));
        System.out.println("链接："+concate);
        System.out.println("元素2出现次数："+Iterables.frequency(concate,2));
        System.out.println("按照指定长度拆分集合："+Iterables.partition(concate,2));
        System.out.println("取第一个元素，为空返回默认："+Iterables.getFirst(concate,99));
        System.out.println("取最后元素："+Iterables.getLast(concate));

        /** 2.Lists 列表工具集 */
        System.out.println("=========Lists=========");
        List list = Lists.newArrayList(1,2,3,4,5);
        System.out.println("反转："+Lists.reverse(list));
        System.out.println("拆分:"+Lists.partition(list,2));

        /** 3.Sets 集合工具集 */
        System.out.println("=========Sets=========");
        Set<Integer> set1 = Sets.newHashSet(1,2,3);
        Set<Integer> set2 = Sets.newHashSet(3,4,5);
        System.out.println("并集："+Sets.union(set1,set2));
        System.out.println("交集："+Sets.intersection(set1,set2));
        System.out.println("差集（set1有set2没有）："+Sets.difference(set1,set2));
        System.out.println("并集-交集："+Sets.symmetricDifference(set1,set2));
        System.out.println("笛卡尔积："+Sets.cartesianProduct(set1,set2));
        System.out.println("全部子集：");
        Sets.powerSet(set1).forEach(System.out::println);

        /** 4.Maps 集合工具集 */
        System.out.println("=========Maps=========");
        Map<String,Integer> map1 = Maps.newHashMap();
        map1.put("a",1);
        map1.put("b",2);
        map1.put("d",5);
        Map<String,Integer> map2 = Maps.newHashMap();
        map2.put("a",1);
        map2.put("b",3);
        map2.put("c",4);
        MapDifference<String,Integer> mapDifference = Maps.difference(map1,map2);
        System.out.println("共有的："+mapDifference.entriesInCommon());
        System.out.println("key相同，value不同："+mapDifference.entriesDiffering());
        System.out.println("左边独有的："+mapDifference.entriesOnlyOnLeft());
        System.out.println("右边独有的："+mapDifference.entriesOnlyOnRight());
        // 字符串长度
        ImmutableSet<String> allColors = ImmutableSet.of("red", "green", "blue");
        ImmutableMap<Integer,String> immutableMap = Maps.uniqueIndex(allColors, input -> input.length());
        System.out.println("字符串长度作为唯一key:"+immutableMap);
    }
}
