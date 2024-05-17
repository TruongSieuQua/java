package generation;

import java.util.Scanner;

public class QL_Bai7_1 {

    static int[] input(){
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        sc.close();
        return new int[]{n, k};
    }
    /*
     *  0 0 0 0 0 0
        + + + +
        i: 0 -> 5 4 3 >= 3
        i: 1 -> 4 3 2 >= 2
     => in :  <= 6 - 4 - idx
     */
    static void backtrack(int[] chars, int[] permutation, int idx){
        if(idx == permutation.length){
            printPermutation(permutation);
        }else{
            // 4 2 => idx=0 range [1, 3]; idx=1 range [0, 2]
            for(int j=idx; j >= idx; j--){

            }
        }
    }

    static void solution(int[] chars, int n){
        int[] permutation = new int[n];
        backtrack(chars, permutation, permutation.length-1);
    }

    static void printPermutation(int[] permutation){
        int n = permutation.length;
        for(int i=0; i<n-1; i++) {
            System.out.printf("%d ", permutation[i]);
        }
        System.out.println(permutation[n-1]);
    }

    public static void main(String[] args) {
        int[] ins = input();

        int[] chars = new int[ins[0]];
        for (int i=0; i < ins[0]; i++){
            chars[i] = 1+i;
        }
        solution(chars, ins[1]);
    }

}
