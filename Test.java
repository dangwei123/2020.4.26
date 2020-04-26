合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0) return null;
        return mergeSort(lists,0,lists.length-1);
    }
    private ListNode mergeSort(ListNode[] lists,int left,int right){
        if(left==right){
            return lists[left];
        }
        int mid=left+(right-left)/2;
        ListNode l=mergeSort(lists,left,mid);
        ListNode r=mergeSort(lists,mid+1,right);
        return merge(l,r);
    }
    private ListNode merge(ListNode l1,ListNode l2){
        if(l1==null) return l2;
        if(l2==null) return l1;
        if(l1.val<l2.val){
            l1.next=merge(l1.next,l2);
            return l1;
        }else{
            l2.next=merge(l1,l2.next);
            return l2;
        }
    }
}

给定一个非空数组，数组中元素为 a0, a1, a2, … , an-1，其中 0 ≤ ai < 231 。

找到 ai 和aj 最大的异或 (XOR) 运算结果，其中0 ≤ i,  j < n 。

你能在O(n)的时间解决这个问题吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    private static class Node{
        Node[] child=new Node[2];
         
    }
    private Node root=new Node();
    
    public int findMaximumXOR(int[] nums) {
        insert(nums);
        int max=0;
        for(int num:nums){
            Node cur=root;
            int sum=0;
            for(int i=31;i>=0;i--){
                int b=(num>>>i)&1;
                int a=b^1;
                if(cur.child[a]!=null){
                    sum+=(1<<i);
                    cur=cur.child[a];
                }else{
                    cur=cur.child[b];
                }
                
            }
            max=Math.max(max,sum);
        }
        return max;
    }
    
    
    private void insert(int[] nums){
        for(int num:nums){
            Node cur=root;
            for(int i=31;i>=0;i--){
                int index=(num>>>i)&1;
                if(cur.child[index]==null){
                    cur.child[index]=new Node();
                }
                cur=cur.child[index];
            }
        }
    }
}


给定一组唯一的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res=new ArrayList<>();
        Map<String,Integer> map=new HashMap<>();
        for(int i=0;i<words.length;i++){
            map.put(new StringBuilder(words[i]).reverse().toString(),i);
        }
        for(int i=0;i<words.length;i++){
            String word=words[i];
            for(int j=0;j<word.length();j++){
                if(isPalind(word,j,word.length()-1)){
                    String after=word.substring(0,j);
                    if(map.containsKey(after)){
                        if(map.get(after)!=i){
                            res.add(Arrays.asList(i,map.get(after)));
                        }
                    }
                }
            }

            for(int j=0;j<word.length();j++){
                if(isPalind(word,0,j)){
                    String before=word.substring(j+1);
                    if(map.containsKey(before)){
                        if(map.get(before)!=i){
                            res.add(Arrays.asList(map.get(before),i));
                        }
                    }
                }
            }

            if(map.containsKey(word)){
                if(map.get(word)!=i){
                    res.add(Arrays.asList(i,map.get(word)));
                    }
            }
        }
        return res;
        
    }
    private boolean isPalind(String s,int left,int right){
        while(left<right&&s.charAt(left)==s.charAt(right)){
            left++;
            right--;
        }
        return left>=right;
    }
}

