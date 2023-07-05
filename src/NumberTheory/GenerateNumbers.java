package NumberTheory;

import java.util.*;

class OutputList{
    int a,b,c,d;
    OutputList(int a ,int b,int c,int d){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public String toString() {
         return (a + " " + b + " " + c+ " "+ d);
    }
}
public class GenerateNumbers {
    public static void main(String[] args) {
        int count =0;
        Random random=  new Random();
        while(count<1){
            int a = random.nextInt(10);
            int b = random.nextInt(10);
            int c = random.nextInt(10);
            int d = random.nextInt(10);
            count++;
            OutputList op = new OutputList(a,b,c,d);
            System.out.println(op);
        }
    }
}
