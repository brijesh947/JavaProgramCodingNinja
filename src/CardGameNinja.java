import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class CardGameNinja {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static HashMap<Integer, Integer> getPrimeFactors(long k) {
        HashMap<Integer, Integer> list = new HashMap<>();
        for (int i = 2; i * i <= k; i++) {
            int j = i;
            int count = 0;
            while (k % j == 0) {
                k /= j;
                count++;
            }
            if (count > 0)
                list.put(j, count);
        }
        if (k > 0) {
            list.put((int) k, 1);
        }
        return list;
    }

    private static HashMap<Integer, Integer> getCommonPrimeFactors(HashMap<Integer, Integer> map, HashMap<Integer, Integer> secondMap) {
        HashMap<Integer, Integer> commonFactor = new HashMap<>();
        map.forEach((key, value) -> {
            if (secondMap.containsKey(key)) {
                commonFactor.put(key, secondMap.get(key));
            }
        });
        return commonFactor;
    }

    private static HashMap<Integer, Integer> preCalculateTheCommonPrimeFactor(int i, HashMap<Integer, HashMap<Integer, Integer>> finalList, HashMap<Integer, Integer> map) {
        if (i == 0) {
            return map;
        } else {
            HashMap<Integer, Integer> listMap = new HashMap<>(finalList.get(i - 1));
            map.forEach((key, value) -> {
                int preVal = 0;
                if (listMap.containsKey(key)) {
                    preVal = listMap.get(key);
                }
                preVal += value;
                listMap.put(key, preVal);
            });
            return listMap;
        }
    }

    private static HashMap<Integer, HashMap<Integer, Integer>> getPreCalculatedList(long[] arr, long k) {
        HashMap<Integer, Integer> map = getPrimeFactors(k);
        HashMap<Integer, HashMap<Integer, Integer>> finalList = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            HashMap<Integer, Integer> primeFactor = getPrimeFactors(arr[i]);
            HashMap<Integer, Integer> commonFactor = getCommonPrimeFactors(map, primeFactor);
            HashMap<Integer, Integer> temp = preCalculateTheCommonPrimeFactor(i, finalList, commonFactor);
            finalList.put(i, temp);
        }
        return finalList;
    }

    private static boolean isPossible(int s, int e, HashMap<Integer, Integer> minFactor, HashMap<Integer, HashMap<Integer, Integer>> finalList,int n) {
        if(s<0||s>=n ||e<0||e>=n)
            return false;
        HashMap<Integer, Integer> first = new HashMap<>();
        HashMap<Integer, Integer> second = new HashMap<>();
        if (s > 0)
            first = finalList.get(s - 1);

        second = finalList.get(e);
        HashMap<Integer, Integer> finalFirst = first;
        HashMap<Integer, Integer> finalSecond = second;
        second.forEach((key, value) -> {
            int val = value;
            if (s != 0 && finalFirst.containsKey(key))
                val = value - finalFirst.get(key);
            finalSecond.put(key, val);
        });
        AtomicBoolean isFound = new AtomicBoolean(true);
        minFactor.forEach((key, value) -> {
            if (!finalSecond.containsKey(key) || finalSecond.get(key) < value) {
                isFound.set(false);
            }
        });
        return isFound.get();
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

        HashMap<Integer, HashMap<Integer, Integer>> finalPreList = getPreCalculatedList(arr, k);
//        finalPreList.forEach((key, value) -> {
//            System.out.print("for key = " + key + "\n");
//            value.forEach((x, v) -> {
//                System.out.print("key = " + x + "value = " + v + "   ");
//            });
//            System.out.print("\n");
//        });
        HashMap<Integer, Integer> mp = getPrimeFactors(k);
        long count = 0;
        for (int i = 0; i < n; i++) {
            long s = i;
            long e = (int) n-1;
            int temp = 0;
            while (e>=s && (e-s)<n) {
                long mid = (e+s) / 2;
                if (isPossible((int)s, (int)mid, mp, finalPreList,(int)n)) {
                    System.out.print("start is " + s + " and end is " + mid + "\n");
                    temp = (int) (n - mid);
                    e = Math.min((mid-s)/2,mid-1);
                } else {
                    e = Math.max((mid+e)/2,mid+1);
                }
            }
            count += temp;
        }

        System.out.println("\ncount is " + count);


    }
}
