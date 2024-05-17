//package generation;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class QL_Bai3 {
//
//    static int input(){
//        Scanner sc = new Scanner(System.in);
//        int n=sc.nextInt();
//        sc.close();
//        return n;
//    }
//
//    static void backtrack(ArrayList<Integer> hv, int idx, int n, List<List<Integer>> kq){
//        hv = new ArrayList<>(hv);
//        if(idx==n){
//            kq.add(hv);
//        }else{
//            for(int j=n-1; j >= 0; j--){
//                if(used[j]){
//                   continue;
//                }
//                used[j]=true;
//                rs[idx] = j+1;
//                backtrack(rs, idx+1, n, k, used);
//                used[j]=false;
//            }
//        }
//    }
//
//    static void solution(int n, int k){
//        int[] rs = new int[k];
//
//        backtrack(rs, 0, n, k, used);
//    }
//
//    static void printPermutation(int[] permutation){
//        int n = permutation.length;
//        for(int i = 0; i < n; i++) {
//            System.out.printf("%3d", permutation[i]);
//        }
//        System.out.println();
//    }
//
//    public static void main(String[] args) {
//        int n = input();
//        int k = 3;
//        solution(n, k);
//    }
//}
