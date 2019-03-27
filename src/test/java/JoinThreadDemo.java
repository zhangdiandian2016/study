public class JoinThreadDemo {
private static String lock="lock";
    public static void main(String[] args) {
        System.out.println("start main thread");
        MyThread thread = new MyThread();

        new MyThread2(lock).start();

        try {
            Thread.sleep(500);
            new MyThread2(lock).start();
            Thread.sleep(500);
            new MyThread2(lock).start();
            Thread.sleep(500);
            new MyThread2(lock).start();
            Thread.sleep(500);
            new MyThread2(lock).start();

            thread.start();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main thread end!!");
    }
}


class MyThread extends Thread {


    @Override
    public void run() {
        try {
            System.out.println("MyThread run()! start sleep！"+ this.getName());
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("5 秒后，唤醒 enter " + this.getName());

//        while(true){
//          为什么这个线程执行完就会唤醒所有的持有该对象锁的线程
//          我尝试用死循环，结果发现只要这个线程不结束，就不会唤醒那些线程
//          我一直以为线程进入等待状态，需要其他的线程去notify或者notifyAll去唤醒
//        }
    }
}

class MyThread2 extends Thread{

    private String myThread;

    public MyThread2(String myThread) {
        this.myThread = myThread;
    }

    @Override
    public void run() {
        synchronized (myThread){
            try {
                System.out.println(this.getName() + " get lock，enter wait");
                myThread.wait();
                System.out.println(this.getName() + " is notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}