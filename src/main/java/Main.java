import entity.Corp;
import entity.Employee;
import entity.Person;

import java.util.LinkedList;

public class Main {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    static class Solution {

        int count = 0;

        private ListNode walk(ListNode node, int n){
            if(null == node)
                return null;

            ListNode nextNode = walk(node.next,n);
            count++;
            if(n!=count){
                node.next = nextNode;
                return node;
            } else {
                return nextNode;
            }
        }

        public ListNode removeNthFromEnd(ListNode head, int n) {
            /*int totalElCount = walk(head);
            ListNode el = head;
            while(totalElCount>0){
                totalElCount--;
                if(n-1 == totalElCount && null != el.next){
                    el.next = el.next.next;
                    el = el.next;
                }
            }
            return head;*/
            return walk(head,n);
            // implement this
        }
    }

    public static void main(String[] args) {
        ListNode five = new ListNode(5);
        ListNode four = new ListNode(4, five);
        ListNode tree = new ListNode(3, four);
        ListNode two = new ListNode(2, tree);
        ListNode head = new ListNode(1, two);
        Solution solution = new Solution();
        ListNode newHead = solution.removeNthFromEnd(head,2);
        while(null != newHead){
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }

    private static void print(String s) {
        System.out.println(s);
    }

    private static void failed(Person person) {
        System.out.println(person.getName() + " was not hired");
    }

    private static void succeed(Person person, Employee boss) {
        System.out.println(person.getName() + " was hired as " + boss.getName() + "'s subordinate");
    }

//    public static void main(String[] args) {
//        Corp corp = Corp.getInstance();
//        Person john = new Person(40, "John (CEO)");
//        Person mary = new Person(24, "Mary");
//        Person peter = new Person(42, "Peter");
//        Person michael = new Person(38, "Michael");
//        Person sam = new Person(38, "Sam");
//        Person will = new Person(38, "Will");
//        Person jackie = new Person(38, "Jackie");
//        Person frank = new Person(38, "Frank");
//
//        Employee eJohn = corp.hireAsBoss(john);
//        if (null == eJohn) {
//            failed(john);
//            return;
//        } else {
//            print("John was hired as Corp CEO");
//        }
//
//        Employee eMary = corp.hireAsNewSubordinate(mary, eJohn.getMyEmployeeId());
//        if (null == eMary) {
//            failed(mary);
//            return;
//        } else {
//            succeed(eMary, eJohn);
//        }
//
//        if (null == corp.hireAsNewSubordinate(peter, eJohn.getMyEmployeeId())) {
//            failed(peter);
//        } else {
//            succeed(peter, eJohn);
//        }
//
//        if (null == corp.hireAsNewSubordinate(michael, eJohn.getMyEmployeeId())) {
//            failed(michael);
//        } else {
//            succeed(michael, eJohn);
//        }
//
//        if (null == corp.hireAsNewSubordinate(sam, eMary.getMyEmployeeId())) {
//            failed(sam);
//        } else {
//            succeed(sam, eMary);
//        }
//
//        Employee eWill = corp.hireAsNewSubordinate(will, eMary.getMyEmployeeId());
//        if(null == eWill){
//            failed(will);
//            return;
//        } else {
//            succeed(eWill,eMary);
//        }
//
//        if (null == corp.hireAsNewSubordinate(jackie, eWill.getMyEmployeeId())) {
//            failed(jackie);
//        } else {
//            succeed(jackie, eWill);
//        }
//
//        if (null == corp.hireAsNewSubordinate(frank, eWill.getMyEmployeeId())) {
//            failed(frank);
//        } else {
//            succeed(sam, eWill);
//        }
//        print("Corp is not hiring anymore. Proper implementation for taking employee by name for read/write access " +
//                "can be checked inside the test");
//    }
}
