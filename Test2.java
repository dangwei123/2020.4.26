给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int max=0;
        int[] windows=new int[128];
        int left=0;
        for(int right=0;right<s.length();right++){
            int index=s.charAt(right);
            windows[index]++;
            while(windows[index]>1){
                windows[s.charAt(left)]--;
                left++;
            }
            max=Math.max(max,right-left+1);
        }
        return max;
    }
}

编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 ""。
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0) return "";
        StringBuilder sb=new StringBuilder();
        int len=strs[0].length();
        for(String str:strs){
            len=Math.min(len,str.length());
        }
        for(int i=0;i<len;i++){
            char c=strs[0].charAt(i);
            int j=1;
            for(;j<strs.length;j++){
                char t=strs[j].charAt(i);
                if(t!=c){
                    break;
                }
            }
            if(j==strs.length){
                sb.append(c);
            }else{
                break;
            }
        }
        
        return new String(sb);
    }
}

给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。

换句话说，第一个字符串的排列之一是第二个字符串的子串。

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int[] needs=new int[26];
        int[] windows=new int[26];
        for(int i=0;i<s1.length();i++){
            needs[s1.charAt(i)-'a']++;
        }
        int count=0;
        int left=0;
        int right=0;
        while(right<s2.length()){
            int index=s2.charAt(right)-'a';
            windows[index]++;
            if(needs[index]>0&&needs[index]>=windows[index]){
                count++;
            }
            right++;
            while(count==s1.length()){
                if(right-left==s1.length()){
                    return true;
                }
                int l=s2.charAt(left)-'a';
                windows[l]--;
                if(needs[l]>0&&needs[l]>windows[l]){
                    count--;
                }
                
                left++;
            }
        }
        return false;
    }
}

给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
class Solution {
    public String multiply(String num1, String num2) {
        if("0".equals(num1)||"0".equals(num2)) return "0";
        int m=num1.length();
        int n=num2.length();
        int[] arr=new int[m+n];
        for(int i=m-1;i>=0;i--){
            int a=num1.charAt(i)-'0';
            for(int j=n-1;j>=0;j--){
                int b=num2.charAt(j)-'0';
                int sum=arr[i+j+1]+a*b;
                arr[i+j+1]=sum%10;
                arr[i+j]+=sum/10;
            }
        }
        StringBuilder sb=new StringBuilder();
        if(arr[0]==0){
            for(int i=1;i<m+n;i++){
                sb.append(arr[i]);
            }
        }else{
            for(int i=0;i<m+n;i++){
                sb.append(arr[i]);
            }
        }
        return new String(sb);
    }
}

给定一个字符串，逐个翻转字符串中的每个单词。
class Solution {
    public String reverseWords(String s) {
        char[] chars=myTrim(s).toCharArray();
        reverse(chars,0,chars.length-1);
        int left=0;
        for(int i=0;i<=chars.length;i++){
            if(i==chars.length||chars[i]==' '){
                reverse(chars,left,i-1);
                left=i+1;
            }
        }
        return new String(chars);
    }
    private void reverse(char[] chars,int left,int right){
        while(left<right){
            char c=chars[left];
            chars[left++]=chars[right];
            chars[right--]=c;
        }
    }
    private String myTrim(String s){
        int i=0;
        StringBuilder sb=new StringBuilder();
        while(i<s.length()){
            while(i<s.length()&&s.charAt(i)==' '){
                i++;
            }
            while(i<s.length()&&s.charAt(i)!=' '){
                sb.append(s.charAt(i++));
            }
            while(i<s.length()&&s.charAt(i)==' '){
                i++;
            }
            if(i!=s.length()){
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
class Solution {
    private List<String> res=new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        int len=s.length();
        if(len>12||len<4) return res;
        back(s,0,len,4,new ArrayList<>());
        return res;
    }
    private void back(String s,int begin,int len,int count,List<String> tmp){
        if(begin==len){
            if(count==0){
                res.add(String.join(".",tmp));
            }
            return;
        }
        for(int i=begin;i<begin+3&&i<len;i++){
            if(len-i-1>3*count){
                continue;
            }
            if(isValid(s,begin,i)){
                tmp.add(s.substring(begin,i+1));
                back(s,i+1,len,count-1,tmp);
                tmp.remove(tmp.size()-1);
            }
        }
    }
    private boolean isValid(String s,int left,int right){
        if(right-left>0&&s.charAt(left)=='0'){
            return false;
        }
        return Integer.parseInt(s.substring(left,right+1))<=255;
    }
}

以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。

在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。更多信息请参阅：Linux / Unix中的绝对路径 vs 相对路径

请注意，返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜杠 /。最后一个目录名（如果存在）不能以 / 结尾。此外，规范路径必须是表示绝对路径的最短字符串。
class Solution {
    public String simplifyPath(String path) {
        String[] strs=path.split("/");
        Stack<String> stack=new Stack<>();
        for(String s:strs){
            if("".equals(s)||".".equals(s)) continue;
            if(s.equals("..")){
                if(!stack.isEmpty()){
                    stack.pop();
                }
            }else{
                stack.push(s);
            }
        }
        if(stack.isEmpty()) return "/";
        StringBuilder sb=new StringBuilder();
        while(!stack.isEmpty()){
            sb.insert(0,stack.pop());
            sb.insert(0,"/");
        }
        return new String(sb);
    }
}