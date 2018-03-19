import java.io.FileWriter;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class Test {
    public static String filter(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isLetterOrDigit(str.charAt(i))) {
                continue;
            }
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        MyHashMap<Word, Integer> map = new MyHashMap<>(10);
        Scanner in = new Scanner(new FileReader("log.txt"));
        int val;
        int wordCount = 0;
        while (in.hasNext()) {
            String next = in.next();
            wordCount++;
            next = filter(next);

            Word newWord = new Word(next);
            if (map.find(newWord) == null) {
                map.insert(newWord, 1);
            }
            else {
                //val = map.find(newWord);
                //val++;
                map.increase(newWord);
            }
            //map.insert(newWord, val);
        }

        System.out.println(map.size());
        System.out.println(wordCount);
        System.out.println(map.countAllCollisions());
        System.out.println(map.list_all_keys());

        Word a = new Word("will");
        //map.increase(a);
        FileWriter fw = null;

        map.printBucket();
    }
}
