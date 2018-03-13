import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileWriter;
import java.util.List;
import java.util.LinkedList;


public class MyHashMap<K, Integer> implements Iterable<MyHashMap.HashNode<K, Integer>> {
    private ArrayList<HashNode<K, Integer>> bucketArray;
    private int numBuckets;
    private int size;
    String formatStr = "Word: %-20s Frequency: %-15s%n";

    public MyHashMap(int numBucket) {
        bucketArray = new ArrayList<>();
        numBuckets = numBucket;
        size = 0;
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
                    HashNode<K, Integer> head = bucketArray.get(i);
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

    public void insert(K key, Integer value) {
        int bucketIndex = getBucketIndex(key);
        HashNode<K, Integer> head = bucketArray.get(bucketIndex);

        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        size++;
        head = bucketArray.get(bucketIndex);
        HashNode<K, Integer> newNode = new HashNode<K, Integer>(key, value);
        newNode.next = head;
        bucketArray.set(bucketIndex, newNode);

        if ((1.0 * size) / numBuckets >= 0.7) {
            ArrayList<HashNode<K, Integer>> temp = bucketArray;
            bucketArray = new ArrayList<>();
            numBuckets = 2 * numBuckets;
            size = 0;

            for (int i = 0; i < numBuckets; i++) {
                bucketArray.add(null);
            }
            for (HashNode<K, Integer> headNode : temp) {
                while (headNode != null) {
                    insert(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }

    public void delete(K key) {
        int bucketIndex = getBucketIndex(key);
        HashNode<K, Integer> head = bucketArray.get(bucketIndex);
        HashNode<K, Integer> prev = null;
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
        int bucketIndex = getBucketIndex(key);
        HashNode<K, Integer> head = bucketArray.get(bucketIndex);
        while (head != null) {
            if (key.equals(head.key)) {
                System.out.println(head.value);
                return;
            }
            head = head.next;
        }
        System.out.println("No such key.");
        return;
    }

    public int countAllCollisions() {
        int count = -1;
        int slot = 0;
        int result = 0;
        for (int i = 0; i < bucketArray.size(); i++) {
            if (bucketArray.get(i) != null) {
                HashNode<K, Integer> head = bucketArray.get(i);
                while (head != null) {
                    count++;
                    head = head.next;
                }

                if (count != 0) {
                    result += count;
                    slot++;
                    count = -1;
                }
                else {
                    count = -1;
                }
            }
        }
        return result;
    }

    public int countCollisions(K key) {
        int bucketIndex = getBucketIndex(key);
        int count = 0;
        HashNode<K, Integer> head = bucketArray.get(bucketIndex);
        while (head != null) {
            if (!key.equals(head.key)) {
                count++;
            }
        }
        return count;
    }

    public Integer find(K key) {
        int bucketIndex = getBucketIndex(key);
        HashNode<K, Integer> head = bucketArray.get(bucketIndex);
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public List<K> list_all_keys() {
        List<K> list = new LinkedList<>();
        for (int i = 0; i < bucketArray.size(); i++) {
            if (bucketArray.get(i) != null) {
                HashNode<K, Integer> head = bucketArray.get(i);
                while (head != null) {
                    list.add(head.key);
                    head = head.next;
                }
            }
        }
        return list;
    }

    @Override
    public Iterator<HashNode<K, Integer>> iterator() {
        return null;
    }

    public static class HashNode<K, Integer> {
        K key;
        Integer value;
        HashNode<K, Integer> next;
        HashNode(K key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }
}
