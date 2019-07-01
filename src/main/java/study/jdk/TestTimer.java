package study.jdk;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class TestTimer {

    public static void main(String args[]) {
        System.out.println("About to schedule task.");
        new Reminder(2);
        System.out.println("Task scheduled.");
    }

    public static class Reminder {
        Timer timer;

        public Reminder(int sec) {
            timer = new Timer();

            Map<String, Integer> map = new HashMap<>();
            map.put("a", 0);
            // delay 2s 执行，每次
            timer.schedule(new TimerTask() {
                @Override
                public void run() {

                    System.out.println("Time's up! threadName=" + Thread.currentThread().getName() + ",a=" + map.get("a"));
                    if (map.get("a") >= 3) {
                        System.out.println("end!");
                        timer.cancel();
                    }
                    map.put("a", map.get("a") + 1);
                    System.out.println("threadName=" + Thread.currentThread().getName() + ",a=" + map.get("a"));

                }
            }, 0 * 1000, 2 * 1000);
        }
    }
}