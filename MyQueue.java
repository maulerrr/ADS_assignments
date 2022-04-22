import java.util.NoSuchElementException;

public class MyQueue<T extends Comparable<T>>{
    MyLinkedList<T> list;
    private final int strict_capacity = 10;

    public MyQueue(){
        list = new MyLinkedList<>();
    }

    public boolean add(T item){
        if (item != null && list.size() != strict_capacity) {
            list.add(item);
            return true;
        } else if(item != null){
            System.out.println("Null element cannot be added to the QUEUE!");
            throw new NullPointerException();
        } else if(list.size()==strict_capacity){
            System.out.println("NO Capacity in the QUEUE!");
            throw new IllegalStateException();
        }
        return false;
    }

    public boolean offer(T item){
        if (list.size() != strict_capacity && item != null){
            list.add(item);
            return true;
        } else if(item == null){
            System.out.println("Null element cannot be added to the QUEUE!");
            throw new NullPointerException();
        }
        return false;
    }

    public T element(){
        if (list.size() != 0) {
            return list.get(0);
        }
        else {
            System.out.println("QUEUE is empty!");
            throw new NoSuchElementException();
        }
    }

    public T peek(){
        if (list.size() != 0)
            return list.get(0);
        return null;
    }

    public T remove(){
        if (list.size() != 0) {
            return list.remove(0);
        }
        else {
            System.out.println("QUEUE is empty!");
            throw new NoSuchElementException();
        }
    }

    public T poll() {
        if (list.size() != 0) {
            return list.remove(0);
        }
        return null;
    }

    public int size(){
        return list.size();
    }

    public boolean empty(){
        return list.size() == 0;
    }

    public void printQueue(){
        System.out.println("Queue: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i)+" ");
        }
    }
}
