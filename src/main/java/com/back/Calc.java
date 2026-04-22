package com.back;

public class Calc {
    public static int run(String num) {
        num = num.trim();

        if (num.startsWith("(") && num.endsWith(")")) {
            int depth = 0;
            boolean isBracket = true;
            for (int i = 0; i < num.length(); i++) {
                char c = num.charAt(i);
                if (c == '(') depth++;
                else if (c == ')') depth--;

                if (depth == 0 && i < num.length() - 1) {
                    isBracket = false;
                    break;
                }
            }
            if (isBracket) {
                return run(num.substring(1, num.length() - 1).trim());
            }
        }

        int index = -1;
        int depth = 0;
        for (int i = num.length() - 1; i >= 0; i--) {
            char c = num.charAt(i);
            if (c == ')') depth++;
            else if (c == '(') depth--;

            if (depth == 0) {
                if (num.startsWith(" + ", i) || num.startsWith(" - ", i)) {
                    index = i;
                    break;
                }
            }
        }

        if (index != -1) {
            String left = num.substring(0, index).trim();
            String right = num.substring(index + 3).trim();
            char op = num.charAt(index + 1);

            if (op == '+') return run(left) + run(right);
            else return run(left) - run(right);
        }

        depth = 0;
        for (int i = num.length() - 1; i >= 0; i--) {
            char c = num.charAt(i);
            if (c == ')') depth++;
            else if (c == '(') depth--;

            if (depth == 0 && num.startsWith(" * ", i)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            String left = num.substring(0, index).trim();
            String right = num.substring(index + 3).trim();
            return run(left) * run(right);
        }

        if (num.startsWith("-")) {
            return -1 * run(num.substring(1).trim());
        }

        return Integer.parseInt(num);
    }
}
