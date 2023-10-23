/*
Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
You may not alter the values in the list's nodes, only nodes themselves may be changed.
 
Example 1:
<"./assets/reverse-k-group-1.png">
Input: head = [1,2,3,4,5], k = 2
Output: [2,1,4,3,5]

Example 2:
<"./assets/reverse-k-group-2.png">
Input: head = [1,2,3,4,5], k = 3
Output: [3,2,1,4,5]
 
Constraints:
	• The number of nodes in the list is n.
	• 1 <= k <= n <= 5000
	• 0 <= Node.val <= 1000
 
Follow-up: Can you solve the problem in O(1) extra memory space?
*/

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
