public class MyHeap<T extends Comparable<T>>{
    private MyArrayList<T> list;
    final private int root = 0;

    public MyHeap(){
        list = new MyArrayList<>();
    }


    public void add(T item) {
        list.add(item);
        traverseUp(list.indexOf(item));
    }

    public boolean remove(T item){
        if (list.contains(item)){
            swap(list.indexOf(item), size()-1);
            boolean removed = list.remove(item);
            heapify(0);
            return removed;
        } else return false;
    }

    private void heapify(int index)
    {
        int min   = index;
        int left  = 2 * index + 1;
        int right = 2 * index + 2;

        T minEl = list.get(min);
        T leftEl = leftChildOf(index);
        T rightEl = rightChildOf(index);

        if (right <= (size()-1) && rightEl.compareTo(minEl) < 0)
            min = right;

        if (left <= (size()-1) && leftEl.compareTo(minEl) < 0)
            min = left;

        if (min != index) {
            swap(index, min);
            heapify(min);
            traverseUp(index);
        }
    }

    public T removeRoot(){
        if (list.size() != 0) {
            T removed = list.remove(root);
            heapify(0);
            return removed;
        }
        return null;
    }

    private void traverseUp(int index){
        if (list.indexOf(parentOf(index)) == root){
            if(list.get(index).compareTo(min()) < 0){
                swap(root, index);
                return;
            }
        } else {
            int current = index;
            int parent = list.indexOf(parentOf(index));

            while ((list.get(index)).compareTo(parentOf(current)) < 0) {
                swap(current, parent);
            }
            traverseUp(parent);
        }
    }

    private void swap(int post, int dest) {
        list.swap(post, dest);
    }

    private T rightChildOf(int index) {
        return list.get(index*2 + 2);
    }

    private T leftChildOf(int index) {
        return list.get(index*2 + 1);
    }

    private T parentOf(int index){
        return list.get((index-1)/2);
    }

    public T min(){
        if(list.size() != 0)
            return list.get(root);
        return null;
    }

    public int size(){
        if(list.size() != 0)
            return list.size();
        return -1;
    }

    public boolean isEmpty(){
        return list.size() == 0;
    }

    ///////////////////////////////////////////// to check if it works
    public void printHeap(){
        System.out.println("");
        System.out.println("Array of heap:");
        System.out.println("");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i)+" ");
        }
    }
}
