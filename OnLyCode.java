package org.example;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(calc("10+2"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    static String accepted = "-+*/";

    interface Action{
        int compare(Integer a, Integer b);
    }
    public static Integer parseNumber(String number){
        try {
            int i = Integer.parseInt(number);
            return i;
        }catch (Exception e){
            return null;
        }
    }

    public static Integer parseRoman(String number){
        if (number.equals("I"))     return 1;
        if (number.equals("II"))    return 2;
        if (number.equals("III"))   return 3;
        if (number.equals("IV"))    return 4;
        if (number.equals("V"))     return 5;
        if (number.equals("VI"))    return 6;
        if (number.equals("VII"))   return 7;
        if (number.equals("VIII"))  return 8;
        if (number.equals("IX"))    return 9;
        if (number.equals("X"))     return 10;

        return null;
    }
    public static int calc(String input) throws Exception {
        String output = input.replaceAll(" ", "");
        String left = null, right = null;
        int i;
        for(i = 0; i< accepted.length(); ++i){
            int index = output.indexOf(accepted.charAt(i));
            if(index != -1){
                left    =   output.substring(0, index);
                right   =   output.substring(index+1);
                break;
            }
        }

        if(left == null || right == null) throw new Exception("Not a mathematical expression");

        Action a;
        switch (accepted.charAt(i)){
            case '+':
                a = (l, r) -> (int)(l+r);
                break;
            case '-':
                a = (l, r) -> (int)(l-r);
                break;
            case '*':
                a = (l, r) -> (int)(l*r);
                break;
            case '/':
                a = (l, r) -> (int)(l/r);
                break;
            default:
                a=null;
                break;
        }
        Integer leftInt = parseNumber(left);
        if(leftInt != null){
            Integer rightInt = parseNumber(right);
            if(rightInt != null)    return a.compare(leftInt, rightInt);
            else                    throw  new Exception("Parameters of different formats");
        }
        else{
            Integer leftRoman = parseRoman(left);
            if(leftRoman != null){
                Integer rightRoman = parseRoman(right);
                if(rightRoman != null)    return a.compare(leftRoman, rightRoman);
                else                      throw  new Exception("Parameters of different formats");
            }
        }

        throw  new Exception("Incorrect number");
    }

}