class Solution {
    public boolean isPalindrome(SinglyLinkedListNode head) {
        if (head == null || head.next == null) {
            return true; // Empty or single-node list is always a palindrome.
        }

        SinglyLinkedListNode slow = head;
        SinglyLinkedListNode fast = head;
        SinglyLinkedListNode prev = null;

        // Find the middle of the linked list and reverse the first half.
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            SinglyLinkedListNode temp = slow.next;
            slow.next = prev;
            prev = slow;
            slow = temp;
        }

        // Handle odd-sized lists.
        if (fast != null) {
            slow = slow.next;
        }

        // Compare the reversed first half and the second half.
        while (prev != null) {
            if (prev.data != slow.data) {
                return false; // Not a palindrome.
            }
            prev = prev.next;
            slow = slow.next;
        }

        return true; // It's a palindrome.
    }
}