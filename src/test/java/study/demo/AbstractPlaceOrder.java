package study.demo;

/**
 * 类 名 称：AbstractPlaceOrder
 * 类 描 述：下订单抽象类
 * 创建时间：2020/3/6 7:58 下午
 * 创 建 人：zyn
 */
public abstract class AbstractPlaceOrder<T extends BaseOrder> implements OrderAction<T>{

    /**
     * 订单
     */
    private T t;

    /**
     * 构造器
     * @param t
     */
    public AbstractPlaceOrder(T t) {
        this.t = t;
    }

    /**
     * 下订单流程
     * @return
     */
     public int placeOrder(){
         // 数据准备
        this.dataReady(t);
         // 校验价格
        this.checkPrice(t);
         // 返回ID
        return this.addDBU(t);
    }

    @Override
    public void dataReady(T t) {
        // 这里只能对BaseOrder数据进行处理
        System.out.println("第一步：通用数据转换:name={}"+t.getName());
    }

    @Override
    public boolean checkPrice(T t) {
        System.out.println("第二部：校验通用订单价格");
        return false;
    }

    @Override
    public int addDBU(T t) {
        System.out.println("第三部：通用订单入库");
        return 10;
    }
}
