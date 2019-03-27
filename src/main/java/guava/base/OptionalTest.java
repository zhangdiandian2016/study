package guava.base;

import com.google.common.base.Optional;

/**
 * @Description Optional:一个指向值对象引用的对象实例，使得构造对象时就明确申明是否支持null
 * @author denny
 * @date 2018/7/24 下午2:23
 */
public class OptionalTest {
    public static void main(String[] args) {
        Integer a = null;
        Integer b = 1;
        // 支持null、非null
        Optional<Integer> optionalA1 = Optional.fromNullable(a);
        Optional<Integer> optionalA2 = Optional.fromNullable(b);
        // 不支持null,参数为null报错
        Optional<Integer> optionalB = Optional.of(b);
        // 不包含引用对象的实例()
        Optional<Integer> optionalC = Optional.absent();

        // 不存在实例，不进入
        if(optionalA1.isPresent()){
            System.out.println(" A1 get="+optionalA1.get());
        }
        // 存在实例，进入
        if(optionalA2.isPresent()){
            System.out.println(" A2 get="+optionalA2.get());
        }
        // 存在实例，进入
        if(optionalB.isPresent()){
            System.out.println(" B get="+optionalB.get());
        }
        // 不存在实例，不进入
        if(optionalC.isPresent()){
            System.out.println(" C get="+optionalC.get());
        }

    }
}
