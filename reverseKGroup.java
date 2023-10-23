class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }
        
        // Calculate the length of the linked list
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }
        
        // If the remaining nodes are less than k, return the current head as is
        if (length < k) {
            return head;
        }
        
        // Reverse the first k nodes
        ListNode prev = null;
        ListNode next = null;
        current = head;
        for (int i = 0; i < k; i++) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        
        // Recursively reverse the remaining part of the linked list
        head.next = reverseKGroup(current, k);
        
        // The new head is the previous node after reversal
        return prev;
    }
}
