import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaojian
 * @date 2019/10/18
 */
public class SemaphoreDemo {
    private static Semaphore semaphore = new Semaphore(5);
    public static void  main(String[] args){
        ExecutorService es = Executors.newFixedThreadPool(10);
        for(int i=0;i<10;i++){
            es.execute(()->{
                try{
                  semaphore.acquire();
                  System.out.println(Thread.currentThread().getName()+"同学拿到了粉笔");
                  System.out.println(Thread.currentThread().getName()+"同学填写表格ing。。");
                  TimeUnit.SECONDS.sleep(3);
                  semaphore.release();
                  System.out.println(Thread.currentThread().getName()+"同学填写表格完毕，归还了笔");
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }
        es.shutdown();
    }
}
