package NumberTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
*
*  The fibonacci sequence is defined by the following relation:
*  F(0) = 0
*  F(1) = 1
*  F(N) = F(N - 1) + F(N - 2), N >= 2
*  Your task is very simple. Given two non-negative integers N and M (N <= M), you have to calculate and return the sum (F(N) + F(N + 1) + ... + F(M)) mod 1000000007.
* Input Format :
* First line of input will contain T(number of test cases), each test case follows as.
* Two non-negative space-separated integers N and M. (N <= M)
* Output Format :
* A new line containing the answer for each test case.
* Constraints:
* 1 <= T <= 10^3
* 1 <= N <= M <= 10^18
* */

public class FibMatrixExponatiation {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final int MOD  = 1000000007;


    static void getExpo(long[][] arr,long n){
        if(n==0||n==1){
            return;
        }

        getExpo(arr,n/2);
        multiply(arr,arr);
        if((n%2)!=0){
            long[][] temp = {{1,1},{1,0}};
            multiply(arr,temp);
        }
    }

    private static void multiply(long[][] arr, long[][] temp) {
        long firstValue = ((arr[0][0]*temp[0][0]) + (arr[0][1]*(temp[1][0])))%MOD;
        long secondValue = ((arr[0][0]*temp[0][1]) + (arr[0][1]*temp[1][1]))%MOD;
        long thirdValue = ((arr[1][0]*temp[0][0]) + (arr[1][1]*temp[1][0]))%MOD;
        long fourthValue = ((arr[1][0]*temp[0][1]) + (arr[1][1]*temp[1][1]))%MOD;
        arr[0][0] = firstValue;
        arr[0][1] = secondValue;
        arr[1][0] = thirdValue;
        arr[1][1] = fourthValue;

    }


    public static void main(String[] args) throws NumberFormatException, IOException {
          int t = Integer.parseInt(reader.readLine().trim());
          while(t-->0){
              String[] num = reader.readLine().split(" ");
              long n = Long.parseLong(num[0]);
              long m = Long.parseLong(num[1]);

              long[][] arr = {{1,1},{1,0}};
              getExpo(arr,n);
              long[][] arr2= {{1,1},{1,0}};
              getExpo(arr2,m+1);

              long ans = (arr2[0][0] - arr[0][0])%MOD;
              System.out.println(ans);


          }

    }
}
