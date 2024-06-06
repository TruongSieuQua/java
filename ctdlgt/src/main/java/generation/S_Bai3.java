package generation;

import java.util.Scanner;

public class S_Bai3 {

    static int[] input(){
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        sc.close();
        return new int[]{n, k};
    }

    static void printCombination(int[] rs){
        for (int i = 0; i < rs.length-1; i++) {
            System.out.printf("%-3d", rs[i]);
        }
        System.out.println(rs[rs.length-1]);
    }

    static void solution(int n, int k){
        int[] rs = new int[k];
        for (int i = 0; i < k; i++) {
            rs[i] = i+1;
        }
        while(true){
            printCombination(rs);

            // Xet n=5, k=3
            // Can 1 2 5 => 1 3 4 hoac 1 4 5 => 2 3 4
            // => voi i=2 => Nhan 3 4 5 <= 5
            // => voi i=1 => Nhan 2 3 4 <= 4
            // => Voi i=0 => Nhan 1 2 3 <= 3
            int i;
            for(i=k-1; i >= 0 ; i--){
                if(rs[i] < n-k+1+i){
                    rs[i]+=1;
                    for (int j = i + 1; j < k; j++) {
                        rs[j] = rs[j-1]+1;
                    }
                    break;
                }
            }
            if(i==-1){
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] in = input();
        solution(in[0], in[1]);
    }
}
