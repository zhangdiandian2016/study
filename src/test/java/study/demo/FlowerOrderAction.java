package study.demo;

/***
 * @Description 实体店订单操作集合类
 * @author denny.zhang
 * @date 2020/3/6 7:54 下午
 */
public class FlowerOrderAction<T extends BaseOrder> extends AbstractPlaceOrder<BaseOrder> {

    /**
     * 实体店订单
     */
    private FlowerOrder flowerOrder;

    /**
     * 构造器
     *
     * @param flowerOrder
     */
    public FlowerOrderAction(FlowerOrder flowerOrder) {
        super(flowerOrder);
        this.flowerOrder = flowerOrder;
    }

    // 这里是覆盖了父类
    public void dataReady(FlowerOrder flowerOrder) {
        System.out.println("第一步：花店数据转换,merchantId="+flowerOrder.getMerchantId());
    }

//    public boolean checkPrice(FlowerOrder flowerOrder) {
//        System.out.println("第二部：校验花店订单价格");
//        return false;
//    }

    public int addDBU(FlowerOrder flowerOrder) {
        System.out.println("第三部：花店订单入库");
        return 10;
    }

    /**
     * 自定义特殊流程:下订单流程
     * @return
     * @param
     */
    @Override
    public int placeOrder(){
        // 数据准备
        this.dataReady(flowerOrder);
        // 校验价格
        this.checkPrice(flowerOrder);

        // TODO 自定义特殊流程
        System.out.println("***********自定义花店特殊流程***********");

        // 返回ID
        return this.addDBU(flowerOrder);
    }
}
