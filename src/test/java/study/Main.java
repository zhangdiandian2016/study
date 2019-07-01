package study;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;

/**
 * @author denny
 * @Description
 * @date 2019/6/6 下午6:26
 */
public class Main {

    private List<Integer> nums = new ArrayList<>();
    private Set<Double> result = new HashSet<>();

    public void run() {
        // 填充原始数据，nums中填充0-9 10个数
        IntStream.range(0, 10).forEach(nums::add);
        //实现Collector接口
        result = nums.stream().parallel().collect(new Collector<Integer, Container, Set<Double>>() {

            // 拆分
            @Override
            public Supplier<Container> supplier() {
                return Container::new;
            }

            // 本地计算并把结果添加进容器
            @Override
            public BiConsumer<Container, Integer> accumulator() {
                return Container::accumulate;
            }

            //组合
            @Override
            public BinaryOperator<Container> combiner() {
                return Container::combine;
            }

            @Override
            public Function<Container, Set<Double>> finisher() {
                return Container::getResult;
            }

            @Override
            public Set<Characteristics> characteristics() {
                // 固定写法
                return Collections.emptySet();
            }
        });
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
        System.out.println("原始数据：");
        main.nums.forEach(i -> System.out.print(i + " "));

        System.out.println("\n\ncollect方法加工后的数据：");
        main.result.forEach(i -> System.out.print(i + " "));

        IntStream.range(0, 10000).parallel().forEach(list1::add);
    }

    private static List<Integer> list1 = new ArrayList<>();

}
