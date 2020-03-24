import java.util.concurrent.TimeUnit;

/**
 * @author zhaojian
 * @date 2019/10/15
 */
public class ThreadState {

    public static final void sleepSecond(long seconds){
        try{
            TimeUnit.SECONDS.sleep(seconds);
        }catch(InterruptedException e){

        }
    }

    public static void main(String[] args){
        new Thread(new TimeWaiting(),"TimeWaitingThread").start();
        //使用两个Blocked线程，一个成功获得锁，另一个被阻塞
        new Thread(new Blocked(),"BlockedThread-1").start();
        new Thread(new Blocked(),"BlockedThread-2").start();
    }
    static class TimeWaiting implements Runnable{
        @Override
        public void run(){
            sleepSecond(100);
        }
    }
    //线程waiting状态：线程拿到锁后，在锁监视器上wait
    static class Waiting implements Runnable{
        @Override
        public void run(){
            while(true){
                synchronized (Waiting.class){
                    try {
                        Waiting.class.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                }
            }
        }
    }
    //线程阻塞状态：加锁后，sleep不会释放锁。第一个线程为timewaing，第二个就被阻塞了
    static class Blocked implements Runnable{
        @Override
        public void run(){
            synchronized (Blocked.class){
                while(true){
                    sleepSecond(100);
                }
            }
        }
    }
}

//jps得到进程id xxx，jstack xxx查看各线程状态
