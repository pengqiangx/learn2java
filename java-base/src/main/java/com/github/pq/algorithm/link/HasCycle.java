package com.github.pq.algorithm.link;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个链表，判断链表中是否有环
 */
public class HasCycle {
    /**
     * hash解法
     * @param head
     * @return
     */
    public boolean hashCycle(ListNode head) {
        Set<ListNode> nodesSeen = new HashSet<>();
        while (head != null) {
            if (nodesSeen.contains(head)) {
                return true;
            } else {
                nodesSeen.add(head);
            }
            head = head.next;
        }
        return false;
    }

    public boolean doublePinpoint(ListNode head){
        if(head == null || head.next == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while(slow != fast){
            if(fast == null || fast.next == null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
            System.out.println("slow:"+slow.var+",fast:"+fast.var);
        }
        return true;

    }

    /**
     * 返回环形入口
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        // If there is a cycle, the fast/slow pointers will intersect at some
        // node. Otherwise, there is no cycle, so we cannot find an e***ance to
        // a cycle.
        //得到交叉点
        ListNode intersect = getIntersect(head);
        if (intersect == null) {
            return null;
        }
        // To find the e***ance to the cycle, we have two pointers traverse at
        // the same speed -- one from the front of the list, and the other from
        // the point of intersection.
        ListNode ptr1 = head;
        ListNode ptr2 = intersect;
        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        return ptr1;
    }

    private ListNode getIntersect(ListNode head) {
        ListNode tortoise = head;
        ListNode hare = head;
        // A fast pointer will either loop around a cycle and meet the slow
        // pointer or reach the `null` at the end of a non-cyclic list.
        while (hare != null && hare.next != null) {
            tortoise = tortoise.next;
            hare = hare.next.next;
            if (tortoise == hare) {
                return tortoise;
            }
        }

        return null;
    }





    @Test
    public void test1(){
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
        a.next = b;b.next = c;c.next=d;d.next = e;d.next=b;
        System.out.println(doublePinpoint(a));
    }

    @Test
    public void test2(){
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
        a.next = b;b.next = c;c.next=d;d.next = e;d.next=b;
        System.out.println(detectCycle(a).var);
    }


}
