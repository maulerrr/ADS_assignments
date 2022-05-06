import java.util.ArrayList;
import java.util.Objects;

public class MyHashTable<K extends Comparable<K>, V> {

    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        @Override
        public String toString() {
            return "["+ key + " | " + value + "]";
        }

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private ArrayList<HashNode<K, V>> chainArray;
    private int M = 11;
    private int size;
    private final float loadfactor = 0.5f; //I think, 50% is happy medium to hold O(1)

    public MyHashTable(){
        size = 0;
        chainArray = new ArrayList<>(M);

        for (int i = 0; i < M; i ++){
            chainArray.add(null);
        }
    }

    public MyHashTable(int M){
        size = 0;
        chainArray = new ArrayList<>(M);

        for (int i = 0; i < M; i ++){
            chainArray.add(null);
        } // to avoid NullPointerException
    }

    private void loadFactor(){
        M *= 2; //increasing number of buckets
        ArrayList<HashNode<K, V>> oldChainArray = chainArray;

        chainArray = new ArrayList<>();

        for (int i = 0; i < M; i ++){
            chainArray.add(null);
        }
        size = 0;

        for (int i = 0; i < oldChainArray.size(); i++){
            HashNode<K, V> cursor = oldChainArray.get(i);

            while(cursor != null){
                put(cursor.key, cursor.value);
                cursor = cursor.next;
            }
        }
        System.out.println("Buckets have been redistributed!");
    }

    public void put(K key, V value) {
        int index = getIndex(key);

        HashNode<K, V> newNode = new HashNode<>(key, value);
        HashNode<K, V> cursor = chainArray.get(index);

        while (cursor != null){
            if ((cursor.key).equals(key)){
                cursor.value = value;
                return;
            } cursor = cursor.next;
        }

        cursor = chainArray.get(index); // if we dont have such key already existing in buckets, we put it
        newNode.next = cursor;          // since node does not have prev reference i considered, it would be more convenient to do this way
        chainArray.set(index, newNode); // replacing head
        System.out.println("Node " + newNode + " has been added to the bucket number " + index);

        size++;
        if ((double)(size/M) >= loadfactor) loadFactor();
    }



    public V remove(K key){
        int index = getIndex(key);

        HashNode<K, V> cursor = chainArray.get(index);
        HashNode<K, V> prev = null;

        if (cursor == null) {
            System.out.println("Nothing to delete!");
            return null;
        }

        while (cursor != null){
            if ((cursor.key).equals(key)) break;
            prev = cursor;
            cursor = cursor.next;
        }

        size--;
        if(prev != null) {
            if (cursor != null) { //NullPointerException
                prev.next = cursor.next;
                System.out.println("Node " + cursor + " has been removed from bucket number - " + index);
            }
        } else chainArray.set(index, cursor.next);

        return cursor.value;
    }

    private int getIndex(K key){
        int hash = hash(key);
        return Math.abs(hash % M);
    }

    public boolean contains(V value){
        for (int i = 0; i < M; i++) {
            if (chainArray.get(i) != null){
                HashNode<K, V> cursor = chainArray.get(i);

                while(cursor != null){
                    if((cursor.value).equals(value)){
                        return true;
                    } cursor = cursor.next;
                }
            }
        }
        return false;
    }

    public V get(K key){
        int index = getIndex(key);

        HashNode<K, V> cursor = chainArray.get(index);

        while (cursor != null){ //intended to emphasize on number of buckets so it wont ruin time complexity O(1), as mentioned on practice
                                // 18M buckets with O(1) is faster than 18M elements in one bucket
            if ((cursor.key).equals(key)){
                return cursor.value;
            } cursor = cursor.next;
        }

        return null;
    }

    private int hash(K key){
        return Objects.hashCode(key); //using built-in class features
    }


    public K getKey(V value){
        for (int i = 0; i < M; i++) {
            HashNode<K, V> cursor = chainArray.get(i);

            while (cursor != null) {
                if ((cursor.value).equals(value)) {
                    return cursor.key;
                }
                cursor = cursor.next;
            }
        }
        return null;
    }

    public int size(){
        return size;
    }

    public void print(){
        for (int i = 0; i < M; i ++){
            HashNode<K, V> cursor = chainArray.get(i);
            System.out.println();
            System.out.println("Bucket " + i + " contains:");
            if (cursor != null){
                while(cursor != null){
                    System.out.print(cursor + "->");
                    cursor = cursor.next;
                }
            }
        }
    }
}
