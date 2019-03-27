import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import study.StudyDemoApplication;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudyDemoApplication.class)
public class BaseTest {
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args)  {

        //System.out.println("线程："+Thread.currentThread().getName()+"，count="+count);
        //for(int i=0;i<100;i++){
        //    new Thread(new Runnable() {
        //        @Override
        //        public void run() {
        //            try {
        //                Thread.sleep(1000);
        //            } catch (InterruptedException e) {
        //                e.printStackTrace();
        //            }
        //            System.out.println("线程："+Thread.currentThread().getName()+"，count="+count.incrementAndGet());
        //        }
        //    }).start();
        //}
        //System.out.println("线程："+Thread.currentThread().getName()+"，count="+count);


        //Set<Integer> set = Arrays.stream(StringUtils.split("1000,20,30", ",")).map(x -> Integer.parseInt(x)).collect(Collectors.toSet());
        ////System.out.println(set.contains(1000));
        //Integer a=10;
        //Integer a1=10;
        //Integer b=10000;
        //Integer b1=10000;
        //System.out.println(a.equals(a1));
        //System.out.println(b.equals(b1));
        //System.out.println(b==b1);
        //System.out.println(a.hashCode());
        //System.out.println(a1.hashCode());
        //System.out.println(b.hashCode());
        //System.out.println(b1.hashCode());
        //List<Integer> list= Lists.newArrayList(1,2,3,4,5,6,7,8,9,10);
        //list.remove(7);
        //list.add(0,8);
        //System.out.println(JSON.toJSON(list));

        String str = "AAA,bbb,";
        System.out.println(str.split(",").length);
    }

}
