public class Word {
    public String str = new String();

    public Word(String s) {
        str = s;
    }

    public int hashCode() {
        int PRIME = 0x01000193;
        int hash = 0x811c9dc5;
        for (int i = 0; i < str.length(); i++){
            char c = str.charAt(i);

            hash ^= c;
            hash *= PRIME;

            String temp = String.valueOf(hash); // convert hash to string

            if(temp.length() > 5) hash = Integer.parseInt(temp.substring(0, 6)); // truncate first 6 digits

            if (hash < 0) hash *= -1; // convert negatives to positive
        }

        return hash;
    }

    public boolean equals(Object o) {
        if (o instanceof Word && o.toString().equals(str)) {
            return true;
        }
        else if (o instanceof String && o.equals(str)) {
            return true;
        }
        else {
            return false;
        }
    }

    public String toString() {
        return str;
    }
}
