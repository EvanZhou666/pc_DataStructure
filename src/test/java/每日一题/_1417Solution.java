package 每日一题;

public class _1417Solution {

    public static String reformat(String s) {
        int sumDigit = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                sumDigit++;
            }
        }
        int sumAlpha = s.length() - sumDigit;
        if (Math.abs(sumDigit - sumAlpha) > 1) {
            return "";
        }
        // 为true，数字比字母多
        // 为false，数字比字母少
        boolean flag = sumDigit > sumAlpha;
        char[] arr = s.toCharArray();
        for (int i = 0, j = 1; i < s.length(); i += 2) {
            // 为true的情况：
            // 1. arr[i]是数字，但是数字个数比字母个数少。
            // 2. arr[i]是字母，但是字母个数比数字个数少。
            // --> 上面两句话综合起来等价于i是偶数位置，并且如果arr[i]就是个数少的，条件为真
            if (Character.isDigit(arr[i]) != flag) {
                // 1.如果arr[j]是数字，并且数字个数比字母少，这个时候要找arr[j]是字母的位置，交换arr[i]和arr[j]
                // 2.如果arr[j]是字母，并且字母个数比数字个数少，这个时候要找arr[j]是数字的位置，交换arr[i]和arr[j]
                // 上面两句话综合起来等价于 j是奇数位置，并且arr[j]就是个数少的，那么说明位置j的元素不需要变化，继续往后检查下一个奇数位置。
                while (Character.isDigit(arr[j]) != flag) {
                    j += 2;
                }
                swap(arr, i, j);
            }
        }
        return new String(arr);
    }

    public static void swap(char[] arr, int i, int j) {
        char c = arr[i];
        arr[i] = arr[j];
        arr[j] = c;
    }

    public static String reformat2(String s) {
        char[] chars = s.toCharArray();
        int countAlpha = 0;
        for (char c : chars) {
            if (Character.isAlphabetic(c)) {
                countAlpha++;
            }
        }
        int countDigit = chars.length - countAlpha;
        if (Math.abs(countAlpha - countDigit) > 1) {
            return "";
        }

        // 假定数字个数多余字母个数
        boolean flag = countDigit >= countAlpha;
        // 字符串中只会存在两种类型的字符，要么数字，要么字母。为了使数字和字母能够间隔排列，那么个数多的需要排在偶数位置(0，2，4，6)；个数少的需要排在奇数位置（1，3，5，7）
        // 如果当前偶数位置排的是个数少的，那就需要从奇数位置中找一个个数多的，把它俩交换，重复这一步骤，知道数组遍历结束。
        int j = 1;
        for (int i = 0; i < chars.length; i = i+2) {
            // 如果偶数位置排的是个数少的
            if (!isBigger(chars, i, flag)) {
                while (!isBigger(chars, j, flag)) {
                    j = j + 2;
                }
                swap(chars, i, j);
            }

        }

        return String.valueOf(chars);

    }

    /**
     * 判断character[x]是属于个数多的类型，还是属于个数少的类型
     * @param character
     * @param x
     * @param flag
     * @return
     */
    public static boolean isBigger(char[] character, int x, boolean flag) {
        // flag 为true，假定数字比字母多
        if (flag) {
            return Character.isDigit(character[x]);
        } else {
            return Character.isAlphabetic(character[x]);
        }
    }
    public static void main(String[] args) {
        String ans = reformat2("123ab");
        System.out.println(ans);
        ans = reformat2("ab123");
        System.out.println(ans);
        ans = reformat2("abcd123");
        System.out.println(ans);
    }
}





