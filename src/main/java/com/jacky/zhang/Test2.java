package com.jacky.zhang;


public class Test2 {
    public static int lengthOfLastWord(String s) {
        int end = s.length() - 1;
        if (end < 0) {
            return 0;
        }
        while (end >= 0 && s.charAt(end) == ' ') {
            end--;

        }

        int start = end;
        while (start >= 0 && s.charAt(start) != ' ') {
            start--;
        }
        return end - start;


    }


    public static void main(String[] args) {

        new Thread(()->{

        }).start();
    }
}
