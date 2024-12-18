package org.example;

public class task1 {
    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        int[] mass = new int[n];
        for (int i = 0; i < n; i++) {
            mass[i] = i + 1;
        }

        int temp = 0;

        do {
            System.out.print(mass[temp]);
            temp = (temp + m - 1) % n;
        } while (temp !=0);
    }
}

