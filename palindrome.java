/*
Given a linked list with a loop, the task is to find whether it is palindrome or not. You are not allowed to remove the loop.
Example 1:
Input: head = [1,2,3,2,1] 
Output: true
Explanation: ```plain
1 -> 2 -> 3 -> 2
         /|\           \|/
              --------- 1
```
Linked list is 1 2 3 2 1 which is a palindrome.
Example 2:
Input: head = [1, 2, 3, 4, 1] 
Output: false
Explanation: ```javascript
1 -> 2 -> 3 -> 4 
         /|\           \|/
              --------- 1
```
Linked list is 1 2 3 4 1 which is not a palindrome.
Constraints:
	• 1<=length of Linked List <=105105
1<= SinglyLinkedList.data<=1000
*/

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
