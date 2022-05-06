import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BST<K extends Comparable<K>,V> implements Iterable<K> {
    private Node root;

    public BST(){
        root = null;
    }

    private class Node{
        private K key;
        private V value;
        private Node left, right;

        private Node(K key, V value){
            this.key = key;
            this.value= value;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return "[" +
                    "key=" + key +
                    " | value=" + value + "]";
        }
    }

    public void put(K key, V value){
        root = put(root, key, value);
    }
    private Node put(Node cursor, K key, V value){
        if (cursor == null){
            cursor = new Node(key, value);
            return cursor;
        } //base case

        int compareRes = key.compareTo(cursor.key);

        if (compareRes < 0) cursor.left = put(cursor.left, key, value);
        else if(compareRes > 0) cursor.right = put(cursor.right, key, value);
        else System.out.println("Already exists!");

        return cursor;
    }


    public V get(K key){
        return get(root, key);
    }
    private V get(Node cursor, K key){
        if (cursor == null) return null;
        if (cursor.key.compareTo(key) == 0) return cursor.value;
        if (cursor.key.compareTo(key) > 0) return get(cursor.left, key);

        return get(cursor.right, key);
    }

    public void delete(K key){
        delete(root, key);
    }
    private Node delete(Node cursor, K key){
        if (cursor == null) return cursor;

        int compareRes = key.compareTo(cursor.key);

        if (compareRes < 0) cursor.left = delete(cursor.left, key);
        else if (compareRes > 0) cursor.right = delete(cursor.right, key);
        else {
            if (cursor.left == null) return cursor.right;
            else if (cursor.right == null) return cursor.left;

            cursor.key = minNode(cursor.right); //as shown on practice, tnx for idea
            cursor.right = delete(cursor.right, cursor.key);
        }
        return cursor;
    }

    private K minNode(Node cursor){
        K min = cursor.key;

        while(cursor.left != null){
            min = (cursor.left).key;
            cursor = cursor.left;
        }

        return min;
    }
    private K maxNode(Node cursor){
        K max = cursor.key;

        while(cursor.right != null){
            max = (cursor.right).key;
            cursor = cursor.right;
        }

        return max;
    }

//    private int compare(K key, K target){
//        return key.compareTo(target);
//    }

    public Iterator<K> iterator(){
        return new BSTIterator(root);
    }

    private class BSTIterator implements Iterator<K>{
        List<K> keys = new ArrayList<>();
        Iterator<K> iterator;

        public BSTIterator(Node root) {
            traverse(root);
            iterator = keys.iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public K next() {
            return iterator.next();
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }

        private void traverse(Node cursor) {
            if (cursor == null) return;

            if (cursor.left != null) traverse(cursor.left);

            keys.add(cursor.key);

            if (cursor.right != null) traverse(cursor.right);
        }
    }


    public void inOrder(){
        inOrder(root);
    }

    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node cursor){
        if (cursor != null){
            postOrder(cursor.right); // first output is always the rightmost node

            System.out.println(cursor);

            postOrder(cursor.left); // if cursor does not have right child, works with left subtree and finally output
        }
    }

    private void inOrder(Node cursor){
        if (cursor != null){
            inOrder(cursor.left); // first output is always the leftmost node

            System.out.println(cursor);

            inOrder(cursor.right); // if cursor does not have left child, works with right subtree and finally outputs
        }
    }

    private int size = 0;

    public int size(){
        size(root, 0);
        return size;
    }
    private void size(Node cursor, int size){
        this.size = size;
        if (cursor != null){
            size(cursor.left, this.size);
            size(cursor.right, this.size + 1); //ticks on every right entry
        }
    }

}
