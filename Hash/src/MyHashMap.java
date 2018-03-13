import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileWriter;

public class MyHashMap<K, V> implements Iterable<MyHashMap.HashNode<K, V>> {
    private ArrayList<HashNode<K, V>> bucketArray;
    private int numBuckets;
    private int size;
    public int collision;
    String formatStr = "Word: %-20s Frequency: %-15s%n";

    public MyHashMap(int numBucket) {
        bucketArray = new ArrayList<>();
        numBuckets = numBucket;
        size = 0;
        collision = 0;
        for (int i = 0; i < numBuckets; i++) {
            bucketArray.add(null);
        }
    }

    public void printBucket() {
        FileWriter fw = null;
        try {
            fw = new FileWriter("output.txt", false);
            for (int i = 0; i < bucketArray.size(); i++) {
                if (bucketArray.get(i) != null) {
                    fw.write("Slot: " + i);
                    fw.flush();
                    fw.write("\n");
                    HashNode<K, V> head = bucketArray.get(i);
                    while (head != null) {
                        fw.write(String.format(formatStr, head.key, head.value));
                        fw.flush();
                        head = head.next;
                    }
                    fw.write("\n");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private int getBucketIndex(K key) {
        int hashcode = key.hashCode();
        int index = hashcode % numBuckets;
        return index;
    }

    public void insert(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        HashNode<K, V> head = bucketArray.get(bucketIndex);

        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            else {
                collision++;  // Fix me
            }
            head = head.next;
        }

        size++;
        head = bucketArray.get(bucketIndex);
        HashNode<K, V> newNode = new HashNode<K, V>(key, value);
        newNode.next = head;
        bucketArray.set(bucketIndex, newNode);

        if ((1.0 * size) / numBuckets >= 0.7) {
            ArrayList<HashNode<K, V>> temp = bucketArray;
            bucketArray = new ArrayList<>();
            numBuckets = 2 * numBuckets;
            size = 0;
            collision = 0;
            for (int i = 0; i < numBuckets; i++) {
                bucketArray.add(null);
            }
            for (HashNode<K, V> headNode : temp) {
                while (headNode != null) {
                    insert(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }

    public void delete(K key) {
        int bucketIndex = getBucketIndex(key);
        HashNode<K, V> head = bucketArray.get(bucketIndex);
        HashNode<K, V> prev = null;
        while (head != null) {
            if (head.key.equals(key)) {
                break;
            }
            prev = head;
            head = head.next;
        }
        if (head == null) {
            System.out.println("No such key.");
            return;
        }
        size--;
        if (prev != null) {
            prev.next = head.next;
        }
        else {
            bucketArray.set(bucketIndex, head.next);
        }
        System.out.println("Delete Key (" + head.value + ") Success");
    }

    public void increase(K key) {
        return;
    }

    public int countCollisions(K key) {
        int bucketIndex = getBucketIndex(key);
        int count = 0;
        HashNode<K, V> head = bucketArray.get(bucketIndex);
        while (head != null) {
            if (!key.equals(head.key)) {
                count++;
            }
        }
        return count;
    }

    public V find(K key) {
        int bucketIndex = getBucketIndex(key);
        HashNode<K, V> head = bucketArray.get(bucketIndex);
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public void list_all_keys() {
        return;
    }

    @Override
    public Iterator<HashNode<K, V>> iterator() {
        return null;
    }

    public static class HashNode<K, V> {
        K key;
        V value;
        HashNode<K, V> next;
        HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
