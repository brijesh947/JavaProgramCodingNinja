import java.util.Scanner;

public class Main {
    static boolean isPossible(long n, long k) {
        long ans = 0;
        long t = n;
        while (t > 0) {
            if (t >= k) {
                ans += k;
                t -= k;
            } else {
                ans += t;
                t = 0;
            }
            t -= (t / 10);
        }
        if (n % 2 == 0) {
            return ans >= (n / 2);
        }
        return ans >= ((n / 2) + 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt();
        while (t-- > 0) {
            long n = scanner.nextLong();
            long s = 1;
            long e = Long.MAX_VALUE-1;
            long ans = -1;
            while (e >= s) {
                long mid = (e + s) / 2;
                if (isPossible(n, mid)) {
                    ans = mid;
                    e = mid - 1;
                } else {
                    s = mid + 1;
                }
            }
            System.out.println(ans);
        }
    }
}
