import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CardGameNinja {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static long getGCD(long a, long b) {
        if (b == 0)
            return a;
        return getGCD(b, a % b);
    }

    private static long[] getGCDArray(long[] arr, long k) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = getGCD(arr[i], k);
        }
        return arr;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        String[] nums = reader.readLine().split(" ");
        long n = Long.parseLong(nums[0]);
        long k = Long.parseLong(nums[1]);

        String[] numbers = reader.readLine().split(" ");
        long[] arr = new long[(int) n];
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(numbers[i]);
        }

        long[] ans = getGCDArray(arr, k);
        long count = 0;
        long temp = 1;
        int i = 0;
        while (i<n) {
           int j = i;
           while(temp%k!=0 && j<n){
               temp*=ans[j];
               j++;
           }
           if(temp%k==0){
               count+=Math.max((n-j+1),1);
               temp=1;
           }
           if(j<=n)
               i++;
           else
               break;
        }
        System.out.println(count);
    }
}
