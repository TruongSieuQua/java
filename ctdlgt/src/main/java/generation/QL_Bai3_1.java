package generation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QL_Bai3_1 {

        static int input(){
            Scanner sc = new Scanner(System.in);
            int n=sc.nextInt();
            sc.close();
            return n;
        }

        static void backtrack(List<Integer> arr, int k, int n, List<Integer> rs){
            if(k == n){
                printPermutation(rs);
                return;
            }

            for (int i = 0; i < arr.size(); i++) {
                rs.add(arr.remove(i));

                backtrack(arr, k+1, n, rs);

                arr.add(i, rs.remove(rs.size()-1));
            }
        }

        static void solution(int n){
            ArrayList<Integer> arr = new ArrayList<>(n);
            for(int i=0; i<n; i++){
               arr.add(n-i);
            }
            ArrayList<Integer> rs = new ArrayList<>(n);
            backtrack(arr, 0, n, rs);
        }

        static void printPermutation(List<Integer> arr){
            arr.forEach(i -> System.out.printf("  %d", i));
            System.out.println();
        }

        public static void main(String[] args) {

            long startTime = System.currentTimeMillis();
//            int n = input();
            int n = 6;
            solution(n);
            long endTime = System.currentTimeMillis();
            System.out.println("Time executed: " + (endTime - startTime));
        }
}

