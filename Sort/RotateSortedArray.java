public class RotateSortedArray {
    public static void restoreSortedArray(int[] array) {
        int len = array.length;
        for (int i = 0; i < len - 1 ; i++) {
            if (array[i] > array[i + 1]) {
                reverse(array, 0, i);
                reverse(array, i + 1, len - 1);
                reverse(array, 0, len - 1);
            }
        }
    }

    public static void reverse(int[] array, int start, int end) {
        while (start < end) {
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

    public static void showArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{3, 4, 1, 2};
        restoreSortedArray(array);
        showArray(array);
    }
}