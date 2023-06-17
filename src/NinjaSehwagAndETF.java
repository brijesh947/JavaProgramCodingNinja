import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NinjaSehwagAndETF {

    private static final int MAX = 1000000;
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static ArrayList<Integer> getPrime() {
        boolean[] arr = new boolean[MAX];
        for (int i = 2; i < MAX; i++) {
            arr[i] = true;
        }
        for (int i = 2; i * i < MAX; i++) {
            if (arr[i]) {
                for (int j = i * i; j < MAX; j += i) {
                    arr[j] = false;
                }
            }
        }
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 2; i < MAX; i++) {
            if (arr[i]) {
                temp.add(i);
            }
        }
        return temp;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        int t = Integer.parseInt(reader.readLine().trim());
        ArrayList<Integer> primes = getPrime();
        while (t-- > 0) {
            String[] num = reader.readLine().split(" ");
            long start = Long.parseLong(num[0]);
            long end = Long.parseLong(num[1]);
            long k = Long.parseLong(num[2]);
            long[] arr = new long[(int) (end - start + 1)];
            long[] temp = new long[(int) (end - start + 1)];
            for (long i = start; i <= end; i++) {
                arr[(int) (i - start)] = i;
                temp[(int) (i - start)] = i;
            }

            for (Integer prime : primes) {
                long s = (end / prime) * prime;
                for (long j = s; j >= start; j -= prime) {
                    temp[(int) (j - start)] /= prime;
                    arr[(int) (j - start)] = (arr[(int) (j - start)] * (prime - 1)) / prime;
                }
            }

            long count = 0;

            for (long i = start; i <= end; i++) {
                if (arr[(int) (i - start)] % k == 0) {
                    count++;
                }
            }


            long n = (end - start + 1);
            double result = (double) count / n;
            System.out.printf("%.6f\n", result);

        }
    }
}
