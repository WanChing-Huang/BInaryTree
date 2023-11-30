import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    // 111. Minimum Depth of Binary Tree

    public int MinimumDepthOfBinaryTree(TreeNode root) {
        return caculator(root);
    }

    private int caculator(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null) {
            return 1 + caculator(root.right);
        } else if (root.right == null) {
            return 1 + caculator(root.left);
        }

        return 1 + Math.min(caculator(root.left), caculator(root.right));
    }

    // 222. Count Complete Tree Nodes

    public int CountCompleteTreeNodes(TreeNode root) {

        return root != null ? 1 + CountCompleteTreeNodes(root.left) + CountCompleteTreeNodes(root.right) : 0;

    }

    // 515. Find Largest Value in Each Tree Row

    private class FindLargestValueInEachTreeRow {
        List<Integer> ans;

        public List<Integer> largestValues(TreeNode root) {
            ans = new ArrayList<Integer>();
            dfs(0, root);

            return ans;
        }

        private void dfs(int depth, TreeNode root) {

            if (root == null) {
                return;
            }

            if (depth == ans.size()) {
                ans.add(root.val);
            } else {
                ans.set(depth, Math.max(ans.get(depth), root.val));
            }

            dfs(depth + 1, root.left);
            dfs(depth + 1, root.right);
        }
    }

    // 872. Leaf-Similar Trees
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> tree1 = new ArrayList<>();
        List<Integer> tree2 = new ArrayList<>();

        dfs(root1, tree1);
        dfs(root2, tree2);

        return tree1.equals(tree2);

    }

    public void dfs(TreeNode root, List<Integer> leavesValue) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                leavesValue.add(root.val);

            }

            dfs(root.left, leavesValue);
            dfs(root.right, leavesValue);
        }

    }
    

    //1302. Deepest Leaves Sum


    public int deepestLeavesSum(TreeNode root) {
      ArrayDeque<TreeNode> nextLevel = new ArrayDeque<>(),
                           currLevel = new ArrayDeque<>();
    
    

    nextLevel.offer(root);
    
    //loop throught every level utill the last one 
       while(!nextLevel.isEmpty()){
         currLevel = nextLevel.clone();
         nextLevel.clear();
    
     //loop thrught currval and add next level node to ArrayDeque 
        for (TreeNode node :currLevel){
           if(node.left != null){
           nextLevel.offer(node.left);
           }
           if(node.right != null){
           nextLevel.offer(node.right);
            }
         }
    }

    int deepSum = 0;

    for (TreeNode node : currLevel){
        deepSum += node.val;
    }

    return deepSum;
    }
    


    //366. Find Leaves of Binary Tree

    private class  FindLeavesOfBinaryTree {
        private List<List<Integer>> solution;
    
        public List<List<Integer>> findLeaves(TreeNode root) {
            solution = new ArrayList<>();
    
            getHeight(root);
    
            return solution;
        }
    
        private int getHeight(TreeNode root){
            
            //so the deepest level hight would be 0 (-1 +1)
            if(root == null){
                return -1; 
            }
    
            int leftHeight = getHeight(root.left);
            int rightHeight = getHeight(root.right);
    
            int currHeight = Math.max(leftHeight, rightHeight) + 1;  //since the deppestnode.left or right would return -1
    
            if (currHeight == solution.size() ){
                solution.add(new ArrayList<Integer>());
            }
    
            solution.get(currHeight).add(root.val);
    
            return currHeight;
    
        }
    }




}
