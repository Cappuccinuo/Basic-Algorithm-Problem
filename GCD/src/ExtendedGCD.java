import java.util.Scanner;

public class ExtendedGCD {
    public static int[] gcd(int p, int q) {
        if (q == 0)
            return new int[] { p, 1, 0 };

        int[] vals = gcd(q, p % q);
        int d = vals[0];
        int a = vals[2];
        int b = vals[1] - (p / q) * vals[2];
        return new int[] { d, a, b };
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("Please input a number: ");
            int a = in.nextInt();
            System.out.print("Please input another number: ");
            int b = in.nextInt();

            int[] result = gcd(a, b);
            System.out.println("Coefficients are: " + result[1] + ", " + result[2]);
            System.out.println("GCD is: " + result[0]);
        }
    }
}
