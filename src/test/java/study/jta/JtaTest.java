package study.jta;

import org.junit.Test;
import study.BaseTest;
import study.service.UserMsgService;

import javax.annotation.Resource;

/**
 * @author denny
 * @Description 分布式事务管理测试类
 * @date 2018/5/11 下午6:35
 */
public class JtaTest extends BaseTest {

    @Resource
    private UserMsgService userMsgService;

    @Test
    public void jtaTest() {
        this.userMsgService.addUserMsg(115, "test115", "备注");
    }

}
