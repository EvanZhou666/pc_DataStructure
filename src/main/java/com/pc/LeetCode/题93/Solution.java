package com.pc.LeetCode.题93;

import java.util.*;

public class Solution {

    public List<String> restoreIpAddresses(String s) {
        int len = s.length();
        List<String> collection = new ArrayList<>();
        List<String> ipAddress = new ArrayList<>(4);
        int segmentCount = 0;

        dfs(s, 0, len, segmentCount, ipAddress, collection);

        for (String s1 : collection) {
            System.out.println(s1);
        }
        return collection;
    }

    private void dfs(String s, int start, int end, int segmentCount, List<String> ipAddress, List<String> collection) {

        if (segmentCount == 4) {
            if (start >= end) {
                StringBuilder builder = new StringBuilder();
                for (String address : ipAddress) {
                    builder.append(".").append(address);
                }
                collection.add(builder.substring(1));
                return;
            } else {
                return;
            }
        }

        if (segmentCount >= 4) {
            return;
        }

        for (int i = start; i < end && i < start + 3; i++) {
            // 左闭右闭
            boolean validIpSegment = isValidIpSegment(s, start, i);

            if (validIpSegment) {
                segmentCount++;
                ipAddress.add(s.substring(start, i + 1));
                dfs(s, i + 1, end, segmentCount, ipAddress, collection);

                segmentCount--;
                ipAddress.remove(ipAddress.size() - 1);
            }
        }

    }

    private boolean isValidIpSegment(String s, int start, int end) {

        if (end - start == 0 && "0".equals(s.substring(start, end + 1))) {
            return true;
        } else if (end - start >= 0 && end - start < 3 && s.charAt(start) != '0') {
            String substring = s.substring(start, end + 1);
            int ipcode = Integer.parseInt(substring);
            return ipcode > 0 && ipcode <= 255;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.restoreIpAddresses("25525511135");
        System.out.println();
        solution.restoreIpAddresses("0000");
        System.out.println();
        solution.restoreIpAddresses("101023");
        System.out.println();

//        "255.255.11.135","255.255.111.35"
//        255 2 551 1 135
    }

}
