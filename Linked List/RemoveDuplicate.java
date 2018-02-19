public class RemoveDuplicate {
    public static ListNode removeDuplicate(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = head;
        while (dummy != null) {
            ListNode inner = dummy;
            while (inner.next != null) {
                if (inner.next.val == dummy.val) {
                    inner.next = inner.next.next;
                }
                else {
                    inner = inner.next;
                }
            }
            dummy = dummy.next;
        }
        return head;
    }

    public static void showList(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(3);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        head.next = l1;
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        showList(removeDuplicate(head));
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        this.val = x;
    }
}