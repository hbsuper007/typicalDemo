/**
 * @author zhaojian
 * @date 2019/10/11
 */
public class Deadlock {
    private static String A = "A";
    private static String B = "B";
    public static void main(String[] args){
        new Deadlock().deadLock();
    }
    private void deadLock(){
        Thread t1 = new Thread(()->{
            synchronized (A) {
                System.out.println(Thread.currentThread()+" have obtain A lock,sloop 2s");
                try{
                    Thread.currentThread().sleep(2000);
                    System.out.println("thread a wake up,try to obtaon lock b");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                synchronized (B){
                    System.out.println("1");
                }
            }


        });
        Thread t2 = new Thread(()->{
            synchronized (B){
                System.out.println(Thread.currentThread()+"have obtain B lock,and try to obtain lock a");
                synchronized (A){
                    System.out.println("2");
                }
            }
        });
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();
    }
}
