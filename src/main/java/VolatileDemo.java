/**
 * @author zhaojian
 * @date 2019/10/14
 */
public class VolatileDemo {
    private static volatile boolean isOver = false;
    private static boolean isOver2 = false;
    public static void main(String[] args){
        Thread thread1 = new Thread(()->
        {while(!isOver);System.out.println(Thread.currentThread().getName()+" is over");}
        );
        thread1.setName("volatile thread");
        Thread thread2 = new Thread(()->
        {while(!isOver2);System.out.println(Thread.currentThread().getName()+" is over");}
        );
        thread2.setName("nonvolatile thread");
        System.out.println("two thread is running");
        thread1.start();
        thread2.start();
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("try to activate end flag");
        isOver = true;
        isOver2 = true;


    }

}
