import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * Special Prime is a prime number that can be written as the sum of two neighbouring primes and 1.
 * You are given an integer N and you are supposed to find the number special prime in the range: [1, N].
 * Example of special prime 19 = 7 + 11 + 1
 * Neighbouring primes are prime number such that there is no other prime number between them.*/
public class SpecialPrime {
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

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine().trim());

        ArrayList<Integer> primes = sieve(n);
        HashMap<Integer, Boolean> map = new HashMap<>(primes.size());
        for (Integer integer : primes) {
            map.put(integer, true);
        }
        int l = primes.size() - 1;
        int count = 0;
        for (int i = 0; i < l; i++) {
            int temp = primes.get(i) + primes.get(i + 1) + 1;
            if (map.containsKey(temp) && map.get(temp)) {
               // System.out.println(primes.get(i) + " "+ primes.get(i+1) + " " + temp);
                count++;
            }

        }
        System.out.println(count);


    }
}
