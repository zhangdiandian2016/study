package study.guava.collect;

import com.google.common.collect.ImmutableSet;

/**
 * @author denny
 * @Description 不可变集合
 * @date 2018/7/26 下午3:16
 */
public class ImmutableCollectionsTest {
    /**
     * 1.直接申明静态集合
     */
    public static final ImmutableSet<String> COLOR_NAMES_1 = ImmutableSet.of(
        "red",
        "orange",
        "yellow",
        "green");
    /**
     * 2.防御式copy
     */
    public static final ImmutableSet<String> COLOR_NAMES_2 = ImmutableSet.copyOf(COLOR_NAMES_1);

    /**
     * 3.builder建造者模式
     */
    public static final ImmutableSet<String> COLOR_NAMES_3 = ImmutableSet.<String>builder().addAll(COLOR_NAMES_2).add("blue").build();


    public static void main(String[] args) {
        System.out.println("of："+COLOR_NAMES_1);
        System.out.println("防御式copy："+COLOR_NAMES_2);
        System.out.println("建造者模式："+COLOR_NAMES_3);
        System.out.println("转换成list："+COLOR_NAMES_3.asList());
    }
}
