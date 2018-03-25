import java.io.*;
import java.nio.Buffer;
import java.util.Scanner;
import java.util.StringTokenizer;

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
        MyHashMap<Word, Integer> map = new MyHashMap<>(2000);
        String regex = "[【】、.。,，\"!--;:?\'\\]\\[\\/_@]";

        int wordCount = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("alice.txt"));
            String value;
            while ((value = br.readLine()) != null) {
                value = value.replaceAll(regex, " ");
                StringTokenizer tokenizer = new StringTokenizer(value);
                while (tokenizer.hasMoreTokens()) {
                    String word = tokenizer.nextToken();
                    wordCount++;
                    Word newWord = new Word(word);
                    if (map.find(newWord) == null) {
                        map.insert(newWord, 1);
                    }
                    else {
                        map.increase(newWord);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(map.size());
        System.out.println(wordCount);
        System.out.println(map.countAllCollisions());
        System.out.println(map.list_all_keys());

        //Word a = new Word("will");
        //map.increase(a);
        Word a = new Word("copyright");
        for (int i = 0; i < 5; i++) {
            map.increase(a);
        }
        map.delete(a);
        Object b = map.find(a);
        System.out.println(b == null);

        map.printBucket();
    }
}
