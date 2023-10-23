/*
You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them, causing the left and the right side of the deleted substring to concatenate together.
We repeatedly make k duplicate removals on s until we no longer can.
Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.
Example 1:
Input: s = "abcd", k = 2 
Output: "abcd"
Explanation: nothing to delete.
Example 2:
Input: s = "deeedbbcccbdaa", k = 3 
Output: "aa"
Explanation: First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"
Example 3:
Input: s = "pbbcggttciiippooaais", k = 2 
Output: "ps"
Explanation: 

*/

class Solution {
    public String removeDuplicates(String s, int k) {
        Stack<Character> charStack = new Stack<>();
        Stack<Integer> countStack = new Stack<>();
        
        for (char ch : s.toCharArray()) {
            if (!charStack.isEmpty() && charStack.peek() == ch) {
                int count = countStack.pop() + 1;
                if (count == k) {
                    charStack.pop();
                } else {
                    countStack.push(count);
                }
            } else {
                charStack.push(ch);
                countStack.push(1);
            }
        }
        
        StringBuilder result = new StringBuilder();
        while (!charStack.isEmpty()) {
            char ch = charStack.pop();
            int count = countStack.pop();
            for (int i = 0; i < count; i++) {
                result.append(ch);
            }
        }
        
        return result.reverse().toString();
    }
}
