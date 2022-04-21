import java.util.Arrays;
import java.util.Scanner;
import static java.lang.Integer.parseInt;
//to make sort method look not so boring

public class MyArrayList<T extends Comparable<T>> implements MyList<T>{
    private Object[] arr;
    private int length = 0;
    private int capacity = 3;
    ///
    Scanner sc = new Scanner(System.in);
    ///
///////////////////////////////////////////////////
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
///////////////////////////////////////////////////
    public MyArrayList() {
        arr = new Object[capacity];
    }

    public int size() {
        return length;
    }

    private void increaseCapacity() {
        capacity = 2 * capacity;
        Object[] old = arr;
        arr = new Object[capacity];

        for (int i = 0; i < old.length; i++)
            arr[i] = old[i];
    }

    public T get(int index) {
        return (T)arr[index];
    }


    public void swap(int index1, int index2){
        T temp = (T)arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public void add(T item) {
        if (length == capacity)
            increaseCapacity();

        arr[length++] = item;
    }

    @Override
    public void sort() {
        System.out.println("In which order sort LinkedList? " +
                "[ 1 - Ascending " +
                "| 2 - Descending | anything else - cancel sorting ]");
        String response = sc.next();
        if(parseInt(response)==1){
            sortArr(arr, length);
        } else if(parseInt(response)==2){
            sortArrDesc(arr, length);
        }
    }
    private void sortArrDesc(Object[] arr, int n){
        if (n == 1) return;
        for (int i = 0; i < n-1; i++)
            if (((Comparable<T>)arr[i]).compareTo((T) arr[i+1]) < 0){
                Object temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        sortArrDesc(arr, n-1);
    }
    private void sortArr(Object[] arr, int n){
        if (n == 1) return;
        for (int i = 0; i < n-1; i++)
            if (((Comparable<T>)arr[i]).compareTo((T) arr[i+1]) > 0){
                Object temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        sortArr(arr, n-1);
    }


    @Override
    public void add(T item, int index) {
        if(size() == capacity) increaseCapacity();
        if (length - 1 - index >= 0)
            System.arraycopy(arr, index, arr, index+1, length - index);
        arr[index] = item;
        ++length;
        System.out.println("Element " + item + " has been successfully added to position " + index + "!");
    }


    @Override
    public boolean remove(T item) {
        int index = indexOf(item);
        if (index >= 0) {
            System.arraycopy(arr, index + 1, arr, index, length - index - 1);
            length = length - 1;
            arr[length] = null;
            System.out.println("Element " + item + " has been successfully deleted!");
            return true;
        }
        return false;
    }

    @Override
    public T remove(int index) {
        if (index > length) {
            System.out.println("There is not such element with " + index + " index!");
            throw new IndexOutOfBoundsException();
        }
        T removeItem = (T) arr[index];
        if (length - 1 - index >= 0)
            System.arraycopy(arr, index + 1, arr, index, length - index - 1);
        length--;
        return removeItem;
    }

    @Override
    public void clear() {
        for (int i = 0; i < length; i++) {
            arr[i] = null;
        } length = 0;
        System.out.println("ArrayList has been cleared!");
    }


    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < length; i++) {
            if(arr[i] == o){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = length - 1; i >= 0; i--) {
                if (arr[i] == null) {
                    return i;
                }
            }
        }
        else {
            for (int i = length - 1; i >= 0; i--) {
                if(arr[i] == o){
                    return i;
                }
            }
        }
        return -1;
    }


    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }




    //just to check if it works or not
    @Override
    public String toString() {
        return "Array elements: "+ Arrays.toString(arr);
    }
}
