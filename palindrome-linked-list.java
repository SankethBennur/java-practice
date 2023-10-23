/*
Given the head of a singly linked list, return true if it is a palindromeor false otherwise.
 
Example 1:

Input: head = [1,2,2,1]
Output: true
Example 2:

Input: head = [1,2]
Output: false
 
Constraints:
	• The number of nodes in the list is in the range [1, 105].
	• 0 <= Node.val <= 9
 
Follow up: Could you do it in O(n) time and O(1) space?
*/

class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true; // Empty list or single node is considered a palindrome.
        }
        
        // Step 1: Find the middle of the linked list.
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Step 2: Reverse the second half of the linked list.
        ListNode prev = null;
        ListNode curr = slow;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        
        // Step 3: Compare the first half with the reversed second half.
        ListNode firstHalf = head;
        ListNode secondHalf = prev;
        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                return false; // Not a palindrome.
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        
        return true; // It's a palindrome.
    }
}

