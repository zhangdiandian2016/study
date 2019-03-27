package util;

import com.google.common.collect.Range;

/**
 * @Description 
 * @author denny
 * @date 2019/1/22 上午9:48
 */
public class RangeUtils {
    public static void main(String[] args) {
        Range<Double> range1 = Range.openClosed(0D, 0D);
        Range<Double> range2 = Range.closedOpen(0D, 0D);
        Range<Double> range3 = Range.closed(0D, 0D);
        System.out.println(range3.lowerBoundType());
    }

}
