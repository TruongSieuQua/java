package generation;

import java.util.Arrays;
import java.util.Scanner;

public class QL_Bai3 {

    static int input(){
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        sc.close();
        return n;
    }

    static void backtrack(char[] chars, char[] permutation, int idx, boolean[] used){
        if(idx == permutation.length){
            printPermutation(permutation);
            return;
        }else{
            for(int j=0; j < chars.length; j++){
                if(used[j]){
                   continue;
                }
                used[j]=true;
                permutation[idx] = chars[j];

                backtrack(chars, permutation, idx+1, used);

                permutation[idx] = '\0';
                used[j]=false;
            }
        }
    }

    static void solution(char[] chars, int n){
        boolean[] used = new boolean[chars.length];
        char[] permutation = new char[n];
        backtrack(chars, permutation, 0, used);
    }

    static void printPermutation(char[] permutation){
        int n = permutation.length;
        for (int i = 0; i < n; i++) {
            System.out.printf("  %s", permutation[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int n = input();
        char[] chars = new char[n];
        char j='1';
        for(int i=0; i<n; i++){
            chars[i] = (char) (j+n-1-i);
        }
        solution(chars, 3);
    }

}
