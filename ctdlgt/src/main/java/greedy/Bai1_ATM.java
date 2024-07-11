package greedy;

import java.util.Scanner;

public class Bai1_ATM {

    public static void atmAlgorithm(long w, int c) {
        int[] menhGia = {1000, 2000, 3000, 5000};
        if (w % 1000 != 0) {
            System.out.println("0");
            return;
        }
        int count = 0;
        long money = w;
        int l = 1;
        for (int j = c; j >= 0; j--) {
            int[] arrtmp = new int[]{0, 0, 0, 0};
            for (int k = menhGia.length - 1; k >= 0; k--) {
                int tmp = menhGia[k] * (int)Math.pow(10, j);

                long paperNeeded = money / tmp;

                count += (int)paperNeeded;

                if(paperNeeded != 0){
                    arrtmp[k] = 1;
                }
                money %= tmp;
            }
            if (arrtmp[3] == 1 && arrtmp[2] == 1 && arrtmp[0] == 1) l *= 3;
            else if (arrtmp[3] == 1 && arrtmp[0] == 1) l *= 2;
            else if(arrtmp[3] == 0 && arrtmp[2] == 1 && arrtmp[0] == 1) l*=2;
        }
        System.out.println(count + " " + l);
    }

    static class Input{
        long w;
        int c;

        public Input(long w, int c){
            this.w = w;
            this.c = c;
        }
    }

    static Input[] input(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Input[] inputs = new Input[n];
        for(int i=0; i < n; i++){
            inputs[i] = new Input(sc.nextLong(), sc.nextInt());
        }
        return inputs;
    }

    public static void main(String[] args) {
        Input[] inputs = input();
        for(int i = 0; i < inputs.length; i++) {
            long w = inputs[i].w;
            int c = inputs[i].c;
            atmAlgorithm(w, c);
        }
    }
}
