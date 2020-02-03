package com.github.pq.algorithm.link;

import org.junit.Test;

public class Reverse {

    public ListNode reverseList(ListNode head) {
       ListNode prev = null;
       ListNode curr = head;
       while(curr != null){
           ListNode nextTemp = curr.next;
           //改变指针
           curr.next = prev;
           //移动节点
           prev =curr;
           curr = nextTemp;
       }
       return prev;
    }

    public ListNode reverseList2(ListNode head) {
        System.out.println("---------------being-------------");
        if (head == null || head.next == null) {
            System.out.println("    ---head or head.next is null");
            return head;
        }
        System.out.println("    -reverse---val="+head.var+",next="+head.next.var);
        ListNode p = reverseList2(head.next);
        System.out.println("    --end---return:"+p.var);
        head.next.next = head;
        head.next = null;
        System.out.println("---------------end-------------");
        return p;
    }



    @Test
    public void test1(){
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
        a.next = b;b.next = c;c.next=d;d.next = e;

        ListNode head = a;
        while (head !=null){
            System.out.println(head.var);
            head = head.next;

        }
        ListNode rev = reverseList2(a);
        while (rev !=null){
            System.out.println(rev.var);
            rev = rev.next;

        }

    }





}