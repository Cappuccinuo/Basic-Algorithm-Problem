**Primitive Data types and their Corresponding Wrapper class**

| Primitive Data Type | Wrapper Class |
| :-----------------: | :-----------: |
|        char         |   Character   |
|         int         |    Integer    |
|       boolean       |    Boolean    |
|        float        |     Float     |
|        short        |     Short     |
|        byte         |     Byte      |
|       double        |    Double     |
|        long         |     Long      |

1. **What is a wrapper class?**

   A wrapper class is a class whose object **wraps or contains a primitive data types**. In other words, we can wrap a primitive value into a wrapper class object.

2. **Why we need wrapper classes?**

   The 8 primitive data type do not support the object oriented machenism. They can't call methods, they don't have member variables. They can't use as an **Object**.

   The reason we need wrapper classes:

   a.**Wrapper classes convert primitive data types into objects.** Objects are needed when we wish to modify the arguments passed into a method, cause primitive types are passed by value.

   b. The classes in java.util package handles only objects.

   c. Data structures in **Collection framework**, such as ArrayList, **store only objects** (reference type), not primitive types which passed by value.

   d. An object is needed to support synchronization in multithreading.

3. **Autoboxing and Unboxing**

   Autoboxing: **Primitive types -> object of their corresponding wrapper classes**

   e.g. 

   ```
   char ch = 'a';
   // Autoboxing - Primitive to Character object conversion
   Character a = ch;

   ArrayList<Integer> list = new ArrayList<Integer>();
   // Autoboxing - Collections store only objects
   list.add(100);
   ```

   Unboxing: **object of a wrapper class -> Corresponding primitive types**

   e.g.

   ```
   Character ch = 'a';
   // Unboxing - Character object to primitive conversion
   char a = ch;

   ArrayList<Integer> list = new ArrayList<Integer>();
   list.add(100);
   // Unboxing - get method returns an Integer object
   int num = list.get(0);
   ```

4. **Special Note**: 

   Integer can be null, while int cannot. 

   ```
   Integer a = null;
   int num = a;
   ```

   This will raise java.lang.NullPointerException.

5. Some useful method:

   ```
   Integer.valueOf(String str)   // returns an Integer object
   Integer.parseInt(String str)  // returns an int primitive
   Integer.bitCount(int i)       // returns # of one-bits in binary representation
   							  // of int value
   Character.isLetter(char ch) && Character.isDigit(char ch)
   ...
   ```

   ​