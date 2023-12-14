package com.example.api_call;

public class NumberToWord {
    public static final String[] units = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    public static final String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public static String convert(int n) {
        if (n < 0) {
            return "Minus " + convert(-n);
        }
        if (n < 20) {
            return units[n];
        }
        String str = " ";
        if (n < 100) {
            StringBuilder append = new StringBuilder().append(tens[n / 10]);
            if (n % 10 == 0) {
                str = "";
            }
            return append.append(str).append(units[n % 10]).toString();
        } else if (n < 1000) {
            StringBuilder append2 = new StringBuilder().append(units[n / 100]).append(" Hundred");
            if (n % 100 == 0) {
                str = "";
            }
            return append2.append(str).append(convert(n % 100)).toString();
        } else if (n < 100000) {
            StringBuilder append3 = new StringBuilder().append(convert(n / 1000)).append(" Thousand");
            if (n % 10000 == 0) {
                str = "";
            }
            return append3.append(str).append(convert(n % 1000)).toString();
        } else if (n < 10000000) {
            StringBuilder append4 = new StringBuilder().append(convert(n / 100000)).append(" Lakh");
            if (n % 100000 == 0) {
                str = "";
            }
            return append4.append(str).append(convert(n % 100000)).toString();
        } else {
            StringBuilder append5 = new StringBuilder().append(convert(n / 10000000)).append(" Crore");
            if (n % 10000000 == 0) {
                str = "";
            }
            return append5.append(str).append(convert(n % 10000000)).toString();
        }
    }
}
