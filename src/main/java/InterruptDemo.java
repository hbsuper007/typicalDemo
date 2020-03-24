/**
 * @author zhaojian
 * @date 2019/10/14
 */
public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        final Thread sleepThread = new Thread(()->{
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        Thread busyThread = new Thread(()->{
            while(true);
        });
        sleepThread.setName("sleepThread");
        busyThread.setName("busyThread");

        sleepThread.start();
        busyThread.start();

        System.out.println("sleepThread isInterrupted: " + sleepThread.isInterrupted());//中断标志位被清空
        System.out.println("busyThread isInterrupted: " + busyThread.isInterrupted());

        //尝试中断
        sleepThread.interrupt();
        busyThread.interrupt();

        while(sleepThread.isInterrupted());
        //while(busyThread.isInterrupted());

        System.out.println("sleepThread isInterrupted: " + sleepThread.isInterrupted());//中断标志位被清空
        System.out.println("busyThread isInterrupted: " + busyThread.isInterrupted());



    }
}
