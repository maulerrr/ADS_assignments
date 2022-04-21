import java.util.Scanner;
import static java.lang.Integer.parseInt;
//to make sort method look not so boring

public class MyLinkedList<T extends Comparable<T>> implements MyList<T>{
    private static class MyNode<T>{
        T data;
        MyNode<T> next, prev;

        MyNode(T data) {
            this.data = data;
        }
    }

    ///
    Scanner sc = new Scanner(System.in);
    ///

    private int length = 0;
    private MyNode<T> head, tail;

    public int size() {
        return length;
    }

    public T get(int index) {
        if (index >= length || index < 0)
            throw new IndexOutOfBoundsException("index should be positive and less than size");

        MyNode<T> temp = head;

        while (index != 0) {
            temp = temp.next;
            index--;
        }

        return temp.data;
    }
    public MyLinkedList() {}

    public void add(T item) {
        MyNode<T> newNode = new MyNode<>(item);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        length++;
    }
    
    @Override
    public void add(T item, int index) {
        length++;
        MyNode<T> newNode = new MyNode<>(item);
        int counter = 0;

        if(index==0){
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else {
            for (MyNode<T> currentNode = head; currentNode != null; currentNode = currentNode.next) {
                if (counter == index - 1) {
                    newNode.next = currentNode.next;
                    newNode.prev = currentNode;
                    currentNode.next = newNode;
                }
                counter++;
            }
        }
    }

    @Override
    public T remove(int index) {
        MyNode<T> currentNode = head, previousNode = null;

        while (currentNode != null) {
            if ((get(index)).compareTo(currentNode.data) == 0) {

                if (currentNode == head) {
                    T deletedData = head.data;
                    if (head.next == null) {
                        head = null;
                        length--;
                    } else {
                        head = head.next;
                        head.prev = null;
                        length--;
                    }
                    System.out.println("Head has been successfully removed!");
                    return deletedData;
                }
                if (currentNode == tail){
                    MyNode<T> removeNode = head;
                    MyNode<T> prevNode = null;
                    while (removeNode != tail) {
                        prevNode = removeNode;
                        removeNode = removeNode.next;
                    }
                    prevNode.next = null;
                    tail =prevNode;
                    length--;
                    System.out.println("Tail has been successfully removed!");
                    return removeNode.data;
                }

                length--;
                previousNode.next = currentNode.next;
                (previousNode.next).prev = previousNode;
                System.out.println("Element " + currentNode.data + " has been successfully removed!");
                return currentNode.data;
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        return null;
    }
    @Override
    public boolean remove(T item) {
        MyNode<T> currentNode = head, previousNode = null;

        while (currentNode != null) {
            if ((item).compareTo(currentNode.data) == 0) {

                if (currentNode == head) {
                    T deletedData = head.data;
                    if (head.next == null) {
                        head = null;
                        length--;
                    } else {
                        head = head.next;
                        head.prev = null;
                        length--;
                    }
                    System.out.println("Head has been successfully removed!");
                    return true;
                }
                if (currentNode == tail){
                    MyNode<T> removeNode = head;
                    MyNode<T> prevNode = null;
                    while (removeNode != tail) {
                        prevNode = removeNode;
                        removeNode = removeNode.next;
                    }
                    prevNode.next = null;
                    tail =prevNode;
                    length--;
                    System.out.println("Tail has been successfully removed!");
                    return true;
                }

                length--;
                previousNode.next = currentNode.next;
                (previousNode.next).prev = previousNode;
                System.out.println("Element " + currentNode.data + " has been successfully removed!");
                return true;
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        System.out.println("There is not such element as " + item + " in LinkedList");
        return false;
    }

    @Override
    public void sort() {
        System.out.println("In which order sort LinkedList? " +
                "[ 1 - Ascending " +
                "| 2 - Descending | anything else - cancel sorting ]");
        String response = sc.next();
        if(parseInt(response)==1){
            sortList(length);
        } else if(parseInt(response)==2){
            sortListDesc(length);
        }
    }

    private  void  sortListDesc(int n){
        if(n == 1) return;
        MyNode<T> currentNode = head;
        int i = 0;
        while(i != n-1){
            if(((currentNode.data)).compareTo((currentNode.next).data) < 0){
                T temp = currentNode.data;
                currentNode.data = (currentNode.next).data;
                (currentNode.next).data = temp;
            }
            i++;
            currentNode = currentNode.next;
        }
        sortListDesc(n-1);
    }
    private void sortList(int n){
        if(n == 1) return;
        MyNode<T> currentNode = head;
        int i = 0;
        while(i != n-1){
            if(((currentNode.data)).compareTo((currentNode.next).data) > 0){
                T temp = currentNode.data;
                currentNode.data = (currentNode.next).data;
                (currentNode.next).data = temp;
            }
            i++;
            currentNode = currentNode.next;
        }
        sortList(n-1);
    }

    @Override
    public void clear() {
        MyNode<T> currentNode = head;
        while (currentNode != null){
            currentNode.prev = null;
            currentNode = currentNode.next;
        }
        System.out.println("LinkedList has been successfully cleared!");
        length = 0;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        for(MyNode<T> currentNode = head; currentNode != null; currentNode = currentNode.next){
            index += 1;
            if(((Comparable<T>)o).compareTo(currentNode.data) == 0){
                return index - 1;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = length;
        for(MyNode<T> currentNode = tail; currentNode != null; currentNode = currentNode.prev){
            index -= 1;
            if(((Comparable<T>)o).compareTo(currentNode.data) == 0){
                return index;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(Object o) {
        for(MyNode<T> currentNode = head; currentNode != null; currentNode = currentNode.next){
            if(((Comparable<T>)o).compareTo(currentNode.data) == 0) {
                return true;
            }        System.out.println(currentNode.data);
        }
        return false;
    }
}
