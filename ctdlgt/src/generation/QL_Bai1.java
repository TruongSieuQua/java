package generation;

import java.util.Scanner;

public class QL_Bai1 {
    static int input(){
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        sc.close();
        return n;
    }

    static void backtrack(char[] binary, int idx, int n){
        if(idx == n){
            System.out.println(String.valueOf(binary));
            return;
        }
        for (char i = '0'; i <= '1'; i++) {
            binary[idx] = i;
            backtrack(binary, idx+1, n);
            binary[idx] = '\0';
        }
    }

    public static void main(String[] args) {
        int n = input();
        char[] binary = new char[n];
        backtrack(binary, 0, n);
    }
}
