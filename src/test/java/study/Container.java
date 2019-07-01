package study;

import java.util.HashSet;
import java.util.Set;

class Container {
    // 定义本地的result
    public Set<Double> set;

    public Container() {
        this.set = new HashSet<>();
    }

    // 本地计算并把结果添加进容器
    public Container accumulate(int num) {
        this.set.add(Compute.compute(num));
        return this;
    }

    // 组合
    public Container combine(Container container) {
        this.set.addAll(container.set);
        return this;
    }

    // 返回结果
    public Set<Double> getResult() {
        return this.set;
    }
}