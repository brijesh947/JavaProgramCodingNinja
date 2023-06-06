import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EulerTotientFunction {

    private static int MAX = 1000001;

    private static long[] getTotient() {
        long[] phi = new long[MAX];
        for (long i = 0; i < MAX; i++) {
            phi[(int) i] = i;
        }

        for (long i = 2; i < MAX; i++) {
            if (phi[(int) i] == i) {
                phi[(int) i] = i - 1;
                for (long j = 2 * i; j < MAX; j += i) {
                    phi[(int) j] = ((phi[(int) j] * (i - 1)) / i);
                }
            }
        }
        return phi;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine().trim());
        long[] ans = getTotient();

        while (t-- > 0) {
            int n = Integer.parseInt(reader.readLine().trim());
            System.out.println(ans[n]);
        }

    }
}
