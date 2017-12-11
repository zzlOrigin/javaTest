package com.kaishengit;

import java.util.List;
import java.util.Scanner;

public class text {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("请输入数列的长度");
        int length = new Integer(scanner.nextInt());
        scanner = new Scanner(System.in);
        System.out.println("请输入数列,每个数以空格分开");
        String num = scanner.nextLine();
        System.out.println(saytrue(order(length,num)));
        System.out.println(num);
    }

    public static String saytrue(Integer[] nums){
        int length = nums.length;
        if (length < 3){
            return "Impossible";
        }
        for (int i = 0;i < length -2; i++){
            if (nums[i+1] - nums[i] != nums[i+2] - nums[i+1]){
                return "Impossible";
            }
        }
        return "Possible";
    }
    public static Integer[] tranNum(Integer ln,String str){
        Integer[] integers = new Integer[ln];
        String[] strs = str.split(" ");
        for (int a = 0;a < ln;a++){
            integers[a] = new Integer(strs[a]);
        }
        return integers;
    }
    public static Integer[] order(Integer ln,String str){
        Integer[] nums = tranNum(ln,str);
        for (int i = 1;i <= nums.length;i++){
            for(int j = 0;j < nums.length -1;j++){
                if (nums[j] > nums[j+1]){
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }
        return nums;
    }
}
