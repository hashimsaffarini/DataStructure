package datastructureclasses;

public class DataStructureClasses {

    public static void main(String[] args) {
        MyQueue<Integer>q=new MyQueue<>();
        q.offer(1);
        q.offer(2);
        q.offer(3);
        q.offer(4);
        q.offer(5);
        q.offer(6);
        System.out.println(q);
        System.out.println(q.peek());
        System.out.println(q.poll());
        System.out.println(q);
        
    }

}
