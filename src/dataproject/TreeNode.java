package dataproject;

/**
 *
 * @author osman
 */
public class TreeNode {
    String username;
    int score;
    int level;
    TreeNode left;
    TreeNode right;
    
    public TreeNode(String username, int score, int level) {
        this.username = username;
        this.score = score;
        this.level = level;
        this.left = null;
        this.right = null;
    }
    
}
