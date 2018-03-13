import java.io.FileWriter;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        MyHashMap<Word, Integer> map = new MyHashMap<>(10);
        Scanner in = new Scanner(new FileReader("log.txt"));
        int val;
        int wordCount = 0;
        while (in.hasNext()) {
            String next = in.next();
            wordCount++;
            if (!Character.isLetter(next.charAt(next.length() - 1))) {
                next = next.substring(0, next.length() - 1);
            }

            Word newWord = new Word(next);
            if (map.find(newWord) == null) {
                val = 1;
            }
            else {
                val = map.find(newWord);
                val++;
            }
            map.insert(newWord, val);
        }

        System.out.println(map.size());
        System.out.println(wordCount);
        System.out.println(map.countAllCollisions());
        System.out.println(map.list_all_keys());

        Word a = new Word("will");
        map.increase(a);
        FileWriter fw = null;

        map.printBucket();
    }
}
