import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhaojian
 * @date 2019/10/12
 */
public class Cas {
    private volatile int j;
    private int i;
    private AtomicInteger atomicI = new AtomicInteger(0);

    public static void main(String[] args){
        double a = -0.1;
        if(a==-0.1){
            int i =5;
        }

        List<Thread> li = new ArrayList<Thread>(100);
        Cas cas = new Cas();
        for(int i=0;i<100;i++){
            Thread t = new Thread(()->{
                for(int j=0;j<10000;j++){
                    cas.casCount();
                    cas.volatileCount();
                    cas.count();
                }
            });
            li.add(t);
        }
        li.stream().forEach(e->e.start());
        //等待所有线程执行结束
        for(Thread t:li){
            try{
                t.join();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        //打印结果
        System.out.println("casCount res: "+cas.atomicI.get());
        System.out.println("volatileCount res: "+cas.j);
        System.out.println("Count res: "+cas.i);
    }

    //线程安全计数器
    private void casCount(){
        for(;;){
            int i = atomicI.get();
            boolean suc = atomicI.compareAndSet(i,++i);
            if(suc){
                break;
            }
        }
    }
    private void count(){
        i++;
    }
    // 为啥不是线程安全的？因为volatile只能保证内存的可见性，不能保证原子性：对旧值i的读取和i+1的写入一起发生。
    //上句话中对i的读取，是读到了线程的栈帧里，而不是cpu缓存。volitile不能做到a线程读取i值，b线程也读取i值，a将i+1写入后，b线程中栈帧保存的i值失效
    private void volatileCount(){
        j++;
    }


}
