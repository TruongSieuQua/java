package generation;

import java.util.Arrays;
import java.util.Scanner;

public class QL_Bai4 {

    static int[] input(){
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int[] chars = new int[n];
        for(int i=0; i<n; i++) {
            chars[i] = sc.nextInt();
        }
        sc.close();
        return chars;
    }

    static void backtrack(int[] chars, int[] permutation, int idx, boolean[] used){
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

    static void solution(int[] chars, int n){
        Arrays.sort(chars);
        boolean[] used = new boolean[chars.length];
        int[] permutation = new int[n];
        backtrack(chars, permutation, 0, used);
    }

    static void printPermutation(int[] permutation){
        int n = permutation.length;
        for (int i = 0; i < n; i++) {
            System.out.printf(" %d", permutation[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] chars = input();
        solution(chars, chars.length);
    }
}
