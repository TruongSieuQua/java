package recursion;

import java.util.Scanner;

public class Bai8_1 {
    static int n;
    static int count;
    static int[] row = new int[100000];

    public static void input(Scanner sc){
        n = sc.nextInt();
        count = 0;
    }

    public static boolean colcheck(int i){
        int tx = i;
        while (tx > 0){
            if(row[tx-1] == row[i]){
                return false;
            }
            tx--;
        }
        return true;
    }

    // row1-col1 != row2 - col2 va row1+col1 != row2+col1
    public static boolean dia_linecheck(int i){
        int ty = i;
        while (ty>0){
            if(Math.abs(row[i] - row[ty-1]) == Math.abs(i - (ty-1)))
                return false;
            ty--;
        }
        return true;
    }

    public static void backtrack(int i){
        for (int j = 0; j < n; j++) {
            row[i] = j+1;
            if(colcheck(i) && dia_linecheck(i)){
                if(i == n-1){
                    count++;
                    output();
                }
                else
                    backtrack(i+1);
            }
        }
    }

    public static void solve(){
        backtrack(0);
    }

    public static void output(){
        if(count == 0){
            System.out.println("No solution!");
        }else {
            System.out.print("Solution "+count+":");
            for (int i = 0; i < n; i++) {
                System.out.print(" "+row[i]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        input(sc);
        solve();
    }
}
