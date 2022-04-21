public class MyStack<T extends Comparable<T>> {
    private MyArrayList<T> list;

    public MyStack(){
        list = new MyArrayList<>();
    }

    public void add(T item){
        list.add(item);
    }

    public void remove(){
        list.remove(list.size()-1);
    }

    public boolean isEmpty(){
        return list.size() > 0;
    }

    public T peek(){
        return list.get(list.size()-1);
    }

    public T push(T item){
        list.add(item, list.size()-1);
        return list.get(list.size()-1);
    }

    public T pop(){
        return list.remove(list.size()-1);
    }

    public T search(T item){
        return list.get(list.indexOf(item));
    }
}
