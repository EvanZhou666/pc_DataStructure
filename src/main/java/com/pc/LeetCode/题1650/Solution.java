package com.pc.LeetCode.题1650;

import java.util.ArrayList;
import java.util.List;

//1650. 二叉树的最近公共祖先 III
public class Solution {

//    “诡技”解法
    //    作者：dao-zu-qie-chang-k
//    链接：https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree-iii/solution/wei-zhuang-de-lian-biao-jiao-dian-by-dao-awod/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
//    public:
//    Node* lowestCommonAncestor(Node* p, Node * q) {
//        Node* point_p = p;
//        Node* point_q = q;
//
//        while (point_p != point_q) {
//            point_p = point_p->parent ? point_p->parent : q;
//            point_q = point_q->parent ? point_q->parent : p;
//        }
//
//        return point_p;
//    }


    // 普通思路
    public Node lowestCommonAncestor(Node p, Node q) {
        List<Node> list1 = new ArrayList();
        List<Node> list2 = new ArrayList();
        Node pp = p;
        while (pp != null) {
            list1.add(0, pp);
            pp = pp.parent;
        }

        Node qq = q;
        while (qq != null) {
            list2.add(0, qq);
            qq = qq.parent;
        }

        int i = 0;
        Node common = null;
        while (i < list1.size() && i < list2.size()) {
            if (list1.get(i) != list2.get(i)) {
                break;
            }
            common = list1.get(i);
            i++;
        }
        return common;
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

}
