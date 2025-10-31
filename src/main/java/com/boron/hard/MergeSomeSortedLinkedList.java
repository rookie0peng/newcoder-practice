package com.boron.hard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <pre>
 *  @description:
 *  @author: BruceBoron
 *  @date: 2025/10/28
 * </pre>
 */
public class MergeSomeSortedLinkedList {
}


class MergeSomeSortedLinkedListSolution {
    static class ListNode {
        int val;
        ListNode next = null;
        public ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode mergeKLists (ArrayList<ListNode> lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return Integer.compare(o1.val, o2.val);
            }
        });
        for (ListNode node : lists) {
            if (node == null) {
                continue;
            }
            queue.add(node);
        }
        ListNode sentry = new ListNode(0);
        ListNode tmp = sentry;
        while (!queue.isEmpty()) {
            ListNode poll = queue.poll();
            if (poll == null) {
                continue;
            }
            tmp.next = poll;
            if (poll.next != null) {
                queue.add(poll.next);
                poll.next = null;
            }
            tmp = tmp.next;
        }
        // write code here
        return sentry.next;
    }
}
