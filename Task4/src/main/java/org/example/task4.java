package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class task4 {
    public static void main(String[] args) throws IOException {

        File file = new File(args[0]);

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String temp;
        String numLine = "";

        while ((temp = reader.readLine()) != null) {
            numLine = numLine + temp + " ";
        }

        String[] numLineSep = numLine.split(" ");

        int[] nums = new int[numLineSep.length];
        for (int i = 0; i < numLineSep.length; i++) {
            nums[i] = Integer.parseInt(numLineSep[i]);
        }

        int summ = 0;
        for (int i = 0; i < nums.length; i++) {
            summ = summ + nums[i];
        }

        int averageNum = (int) Math.round((double) ((float) summ / (float) nums.length));

        int count = 0;

        for (int id = 0; id < nums.length; id++) {
            int i = nums[id];
            while (i != averageNum) {
                if (i < averageNum) {
                    i += 1;
                    count += 1;
                } else if (i > averageNum) {
                    i -= 1;
                    count += 1;
                }
                nums[id] = i;
            }
        }
        System.out.println(count);

        reader.close();
    }
}



