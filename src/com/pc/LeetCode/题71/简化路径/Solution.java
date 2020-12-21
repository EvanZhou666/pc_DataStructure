package com.pc.LeetCode.题71.简化路径;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。
 * <p>
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。更多信息请参阅：Linux / Unix中的绝对路径 vs 相对路径
 * <p>
 * 请注意，返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜杠 /。最后一个目录名（如果存在）不能以 / 结尾。此外，规范路径必须是表示绝对路径的最短字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/simplify-path
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    static Stack<String> stack = new Stack<>();

    /**
     * 不要存储路径分割符，不然会很难搞，各种判断 容易出错的
     * @param path
     * @return
     */
    public String simplifyPath(String path) {

        String[] paths = path.split("/");
        for (String p : paths) {

            if (!"".equals(p) && !".".equals(p)) {
                if ("..".equals(p)) {
                    pop();
                } else {
                    stack.push(p);
                }
            }

        }

        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            String pop = stack.pop();
            builder.insert(0, pop).insert(0, "/");
        }
        return "".equals(builder.toString()) ? "/" : builder.toString();
    }

    public static String pop() {

        if (stack.isEmpty()) {
            return null;
        } else {
            return stack.pop();
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String path = solution.simplifyPath("/home//foo/");
//        String path = solution.simplifyPath("/a/./b/../../c/");
//        String path = solution.simplifyPath("/a/../../b/../c//.//");
//        String path = solution.simplifyPath("/../");
//        String path = solution.simplifyPath("/a//b////c/d//././/..");
        System.out.println(path);
    }

}