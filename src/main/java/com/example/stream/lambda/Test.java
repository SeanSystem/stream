package com.example.stream.lambda;

/**
 * @author by Sean
 * @date 2020/4/9 22:01
 */
public class Test {

    public static int[][] merge(int[][] intervals) {
        int len = intervals.length;
        int max = 0;
        int index = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            index = i;
            max = intervals[i][1];
            while (((i + 1) < len) && (max >= intervals[i + 1][0])) {
                max = intervals[i + 1][1];
                i++;
            }
            sb.append(intervals[index][0]).append(",").append(intervals[i][1]).append(",");
        }
        String[] split = sb.toString().split(",");
        int arrLen = split.length / 2;
        int[][] result = new int[arrLen][2];
        for (int i = 0, j = 0; i < split.length; i = i + 2, j++) {
            result[j][0] = Integer.parseInt(split[i]);
            result[j][1] = Integer.parseInt(split[i + 1]);
        }
        return result;
    }

    public static boolean valid(String word, String abbr) {
        char[] abbrChars = abbr.toCharArray();
        char[] wordChars = word.toCharArray();
        int index = 0;
        for (int i = 0; i < abbrChars.length; i++) {
            StringBuilder sb = new StringBuilder();
            int step = 0;
            if (Character.isDigit(abbrChars[i])) {
                sb.append(abbrChars[i]);
                step++;
                index++;
                while (Character.isDigit(abbrChars[++i])) {
                    sb.append(abbrChars[i]);
                    step++;
                    index++;
                }
                int n = Integer.parseInt(sb.toString());
                if (n > word.length() - index) {
                    return false;
                }
                if (wordChars[index = index + n - step] == abbrChars[i]) {
                    index++;
                    continue;
                } else {
                    return false;
                }
            } else {
                if (wordChars[index] == abbrChars[i]) {
                    index++;
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "internationalization";
        String abbr = "i12iz5n";
        String s1 = "apple";
        String abbr2 = "a2e";
        // boolean valid = valid(s, abbr);
        int[][] arr = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] arr2 = {{1, 4}, {4, 5}};
        int[][] merge = merge(arr2);
        for (int i = 0; i < merge.length; i++) {
            for (int j = 0; j < merge[i].length; j++) {
                System.out.println(merge[i][j]);
            }
        }
        //System.out.println(valid);
    }
}
