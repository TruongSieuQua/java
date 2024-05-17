package generation;

import java.util.*;
import java.util.stream.IntStream;

public class QL_Bai3_1 {


        static int input(){
            Scanner sc = new Scanner(System.in);
            int n=sc.nextInt();
            sc.close();
            return n;
        }

        static void backtrack(Stack<Integer> rs, int idx, int n){
            if(rs.size()==n){
                printPermutation(rs);
            }else{
                for(int i=)
            }
        }

        static void solution(int n){
            Stack<Integer> rs = new Stack<>();
            Queue<Integer> nums = new ArrayDeque<>();
            for(int i=1; i<=n; i++){
                nums.add(i);
            }
            backtrack(rs, 0, n, list);
        }

        static void printPermutation(Stack<Integer> rs){
            rs.forEach((num -> {
                System.out.printf("%3d", num);
            }));
            System.out.println();
        }

        public static void main(String[] args) {
            int n = input();
            solution(n);
        }
    }

