import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NinjaAndFlowers {


    private static ArrayList<Integer> sieve(int n) {
        boolean[] prime = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            prime[i] = true;
        }
        prime[0] = prime[1] = false;

        for (int i = 2; i * i <= n; i++) {
            for (int j = i * i; j <= n; j += i) {
                prime[j] = false;
            }
        }
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            if (prime[i]) {
                arrayList.add(i);
            }
        }
        return arrayList;
    }

    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int n = Integer.parseInt(reader.readLine().trim());
            ArrayList<Integer> temp = sieve(n+1);
            int a1 = temp.size();
            int a2 = n - a1;
            System.out.println(2);
            System.out.println(a1 + " " + a2);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
