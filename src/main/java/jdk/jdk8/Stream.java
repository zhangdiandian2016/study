package jdk.jdk8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 
 * @author denny
 * @date 2018/8/17 上午9:32
 */
public class Stream {
    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(4, 5, 2, 3, 1);
        List<Integer> collect = integerList.stream().collect(Collectors.toList());

        collect.forEach(System.out::println);
    }
}
