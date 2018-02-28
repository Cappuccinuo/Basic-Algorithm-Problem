**String, StringBuffer and StringBuilder**

1. Objects of **String are immutable**, and objects of **StringBuffer and StringBuilder are mutable**.

   ![stringtest](/Users/cappuccinuo/Desktop/stringtest.png)

2. StringBuffer and StringBuilder are similar, but StringBuilder is faster and preferred over StringBuffer for **single threaded program**. If thread safety is needed, than StringBuffer is used as it is synchronous.

3. Some method of StringBuilder:[https://www.javatpoint.com/StringBuilder-class]

   1. **public StringBuilder append(String s)**
   2. public StringBuilder insert(int offset, String s)
   3. public StringBuilder replace(int startIndex, int endIndex, String str)
   4. public StringBuilder delete(int startIndex, int endIndex)
   5. **public StringBuilder reverse()**
   6. **public String toString()**

4. **Conversion between String and StringBuilder, StringBuffer**

   String -> StringBuilder, StringBuffer

   ```
   String str = "Leetcode";
   StringBuffer sbf = new StringBuffer(str);
   StringBuilder sbl = new StringBuilder(str);
   ```

   StringBuilder, StringBuffer -> String

   ```
   String str1 = sbf.toString();
   String str2 = sbl.toString();
   ```

