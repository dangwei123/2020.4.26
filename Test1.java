给定一个 N 叉树，返回其节点值的前序遍历。
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
//递归
class Solution {
    private List<Integer> res=new ArrayList<>();
    public List<Integer> preorder(Node root) {
        traversal(root);
        return res;
    }
    private void traversal(Node root){
        if(root==null){
            return;
        }
        res.add(root.val);
        for(Node node:root.children){
            traversal(node);
        }
    }
}
//非递归
class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> res=new ArrayList<>();
        Stack<Node> stack=new Stack<>();
        if(root==null) return res;
        stack.push(root);
        while(!stack.isEmpty()){
            Node node=stack.pop();
            res.add(node.val);
            List<Node> tmp=node.children;
            for(int i=tmp.size()-1;i>=0;i--){
                stack.push(tmp.get(i));
            }
        }
        return res;
    }
}

给定一个 N 叉树，返回其节点值的后序遍历。

//递归
class Solution {
    private List<Integer> res=new ArrayList<>();
    public List<Integer> postorder(Node root) {
        traversal(root);
        return res;
    }
    private void traversal(Node root){
        if(root==null) return ;
        for(Node node:root.children){
            traversal(node);
        }
        res.add(root.val);
    }
}
//非递归
class Solution {
    public List<Integer> postorder(Node root) {
        List<Integer> res=new ArrayList<>();
        Stack<Node> stack=new Stack<>();
        if(root==null) return res;
        Stack<Node> s=new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node node=stack.pop();
            s.push(node);
            for(Node cur:node.children){
                stack.push(cur);
            }
        }
        while(!s.isEmpty()){
            res.add(s.pop().val);
        }
        return res;
    }
}

给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>>res=new ArrayList<>();
        if(root==null) return res;
        Queue<Node> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size=queue.size();
            List<Integer> row=new ArrayList<>();
            while(size--!=0){
                Node node=queue.poll();
                row.add(node.val);
                for(Node cur:node.children){
                    queue.offer(cur);
                }
            }
            res.add(row);
        }
        return res;
    }
}

给定一个 N 叉树，找到其最大深度。

最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
class Solution {
    public int maxDepth(Node root) {
        return depth(root);
    }
    private int depth(Node root){
        if(root==null) return 0;
        int max=0;
        for(Node node:root.children){
            max=Math.max(max,depth(node));
        }
        return 1+max;
    }
}


