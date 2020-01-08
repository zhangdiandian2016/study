package study.jdk.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * 
 * @ClassName: ExchangerDemo
 * @Description: 用于两个线程之间进行数据交换，先执行exchanger.exchange()的线程等待后来的线程到达，然后交换数据，最后再继续向下执行。
 * @author denny.zhang
 * @date 2016年11月4日 下午1:27:29
 *
 */
public class ExchangerDemo {
	public static void main(String[] args) {
		final Exchanger<List<Integer>> exchanger = new Exchanger<List<Integer>>();
		
		new Thread(){
			public void run(){
				List<Integer> list = new ArrayList<Integer>();
				list.add(1);
				list.add(2);
				try {
					list = exchanger.exchange(list);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Thread1"+list);
			}
		}.start();
		
		new Thread(){
			public void run(){
				List<Integer> list = new ArrayList<Integer>();
				list.add(3);
				list.add(4);
				try {
					list = exchanger.exchange(list);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Thread2"+list);
			}
		}.start();
	}
}
