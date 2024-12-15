package org.example;

import java.io.*;

public class task2 {

    public static void main(String[] args) throws IOException {

        File file1 = new File(args[0]);
        File file2 = new File(args[1]);

        BufferedReader reader1 = new BufferedReader(new FileReader(file1));
        BufferedReader reader2 = new BufferedReader(new FileReader(file2));

        String temp1;
        String file1Str = "";
        float[] coordinatesOfCenterCircInt = new float[2];
        int radius;

        while ((temp1 = reader1.readLine()) != null) {
            file1Str = file1Str + temp1 + " ";
        }

        String[] file1SepStr = file1Str.split(" ");
        for (int i = 0; i < 2; i++) {
            coordinatesOfCenterCircInt[i] = Integer.parseInt(file1SepStr[i]);
        }
        radius = Integer.parseInt(file1SepStr[2]);

        String temp2;
        String[] tempPointStr; //  = new String[2];
        float[] tempPointInt = new float[2];

        while ((temp2 = reader2.readLine()) != null) {
            tempPointStr = temp2.split(" ");

            for (int i = 0; i < 2; i++) {
                tempPointInt[i] = Integer.parseInt(tempPointStr[i]);
            }

            tempPointInt[0] = tempPointInt[0] - coordinatesOfCenterCircInt[0];
            tempPointInt[1] = tempPointInt[1] - coordinatesOfCenterCircInt[1];

            double hipotenuse = Math.sqrt(tempPointInt[0] * tempPointInt[0] + tempPointInt[1] * tempPointInt[1]);

            if (hipotenuse < radius) {
                System.out.println("1");
            } else if (hipotenuse == radius) {
                System.out.println("0");
            } else if (hipotenuse > radius) {
                System.out.println("2");
            }
        }
        reader1.close();
        reader2.close();
    }
}

