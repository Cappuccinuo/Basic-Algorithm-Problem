public class Word {
    public String str = new String();

    public Word(String s) {
        str = s;
    }

    // FNV-1a Hash.
    // For each byte of the input, XOR it with the byte from the input, then multiply hash by the FNV prime.
    public int hashCode() {
        int PRIME = 0x01000193;
        int hash = 0x811c9dc5;
        for (int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            hash ^= (c & 0xff);
            hash *= PRIME;
        }
        if (hash < 0) hash *= -1; // convert negatives to positive
        return hash;
    }
    /*
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = 31 * hash + str.charAt(i);
        }
        if (hash < 0) {
            hash *= -1;
        }
        return hash;
    }*/
    /*
    public int hashCode() {
        int hash = 0;
        int n = str.length();
        for (int i = 0; i < n; i++) {
            hash += str.charAt(i) * ((int)Math.pow(31, n - i - 1));
        }
        if (hash < 0) {
            hash *= -1;
        }
        return hash;
    }*/

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
