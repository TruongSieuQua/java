package generation;

import java.util.Scanner;

public class S_Bai1 {

    static int input(){
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        sc.close();
        return n;
    }

    static void solution(int n){
        String binary = "0".repeat(n);
        int i;
        System.out.println(binary);
        while((i=binary.lastIndexOf('0'))!=-1){
            // Sinh phan tu ke tiep
            binary =  binary
                        .substring(0,i)
                        .concat("1")
                    .concat("0".repeat(n-i-1));
            System.out.println(binary);
        }
    }

    public static void main(String[] args) {
      int n = input();
      solution(n);
    }
}
