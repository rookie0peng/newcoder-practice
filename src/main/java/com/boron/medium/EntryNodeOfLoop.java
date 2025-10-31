package com.boron.medium;

/**
 * <pre>
 *  @description: 找出环形链表的入口节点
 *  @author: BruceBoron
 *  @date: 2025/10/28
 * </pre>
 */
public class EntryNodeOfLoop {
}

class EntryNodeOfLoopSolution {


    static class ListNode {
        int val;
        ListNode next = null;
        public ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode EntryNodeOfLoop(ListNode pHead) {
        ListNode step1 = pHead;
        ListNode step2 = pHead;
        int meet = 0;
        boolean hasRing = false;
        while (step1 != null && step2 != null) {
            if (step1 == step2) {
                meet++;
            }
            if (meet == 2) {
                hasRing = true;
                break;
            }
            step1 = step1.next;
            step2 = step2.next;
            if (step2 != null) {
                step2 = step2.next;
            }
        }
        if (!hasRing) {
            return null;
        }
        ListNode step11 = pHead;
        ListNode step12 = step2;
        while (step11 != null && step12 != null) {
            if (step11 == step12) {
                return step11;
            }
            step11 = step11.next;
            step12 = step12.next;
        }
        return null;
    }
}
