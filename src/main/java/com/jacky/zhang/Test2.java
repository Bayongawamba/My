package com.jacky.zhang;

public class Test2 {
    public static boolean isPalindrome(int x) {
        if(x>=0){
            int s = x;
            int res = 0;
            while (x>0) {
                int pop = x % 10;
                res = res * 10 + pop;
                x=x/10;
            }
            if(s==res){
                return true;
            }
            return false;
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(Test2.isPalindrome(121));
    }
}
