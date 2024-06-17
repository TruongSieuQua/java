package greedy;

import java.util.Scanner;

public class Bai3_ATM {

    private static int[] NOTES = new int[]{500, 100, 50, 10, 5, 1};

   public static int[] input(){
       Scanner sc = new Scanner(System.in);
       int n = sc.nextInt();
       int[] inputs = new int[n];
       for (int i = 0; i < n; i++) {
           inputs[i] = sc.nextInt();
       }
       return inputs;
   }

   static void solution(int[] inputs){
       StringBuilder sb = new StringBuilder();
       for (int input : inputs){
           int money = 1000 - input;
           int noteCount=0;
           for(int note: NOTES){
               int thuong = money/note;
               int du = money % note;

               if(thuong > 0){
                   noteCount+= thuong;
                   money = du;
               }
           }
           sb.append(String.format("%d\n", noteCount));
       }
       System.out.print(sb);
   }

    public static void main(String[] args) {
        int[] inputs = input();
        solution(inputs);
    }
}
