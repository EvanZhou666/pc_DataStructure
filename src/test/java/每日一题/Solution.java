package 每日一题;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    // 求区间交集
    public static List<Pair> getOverLapping(List<Pair> dataList) {

        List<Pair> overlapping = new ArrayList<>(8);

        List<Pair> noOverLapping = new ArrayList<>(8);

        dataList.sort((pair1, pair2) -> pair1.start.equals(pair2.start) ? Long.compare(pair1.end, pair2.end) : Long.compare(pair1.start, pair2.start));

        Pair pre = dataList.get(0);

        for (int i = 1; i < dataList.size(); i++) {
            Pair cur = dataList.get(i);
            // 出现交集
            if (cur.start <= pre.end) {
                Pair overLap = Pair.of(cur.start, Math.min(cur.end, pre.end));

                if (!overlapping.isEmpty()) {
                    Pair lastOverLap = overlapping.get(overlapping.size()-1);
                    if (overLap.start <= lastOverLap.end ) {
                        lastOverLap.end = overLap.end;
                    } else {
                        overlapping.add(overLap);
                    }
                } else {
                    overlapping.add(overLap);
                }
            } else { // 没有出现交集
                noOverLapping.add(pre);
            }
            pre = cur;
        }
        System.out.println("有重叠的区域："+Arrays.toString(overlapping.toArray()));
//        System.out.println("无重叠的区域："+Arrays.toString(noOverLapping.toArray()));
        return overlapping;
    }

    public static void main(String[] args) {
        long beginDate = 0l, endDate = 100l;
        List<Pair> dataList = new ArrayList<>();
        dataList.add(Pair.of(-1l, 4l));
        dataList.add(Pair.of(5l, 30l));
        dataList.add(Pair.of(6l, 101l));
        dataList.add(Pair.of(20l, 120l));
        getOverLapping(dataList);

        dataList = new ArrayList<>();
        dataList.add(Pair.of(2l, 6l));
        dataList.add(Pair.of(1l, 3l));
        dataList.add(Pair.of(15l, 18l));
        dataList.add(Pair.of(8l, 10l));
        dataList.add(Pair.of(12l, 20l));

        getOverLapping(dataList);

    }

}

class Pair {

    Long start;
    Long end;

    Pair(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    public static Pair of(Long start, Long end) {
        return new Pair(start, end);
    }

    public String toString() {
        return "[" + start + "," + end + "]";

    }

}
