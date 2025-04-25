package dataproject;

import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author osman
 */
public class BinarySearchTree {

    TreeNode root;
    
    public void insert(String username, int level, int score) {
        TreeNode newNode = new TreeNode(username, score, level);
        
        if (root == null) {
            root = newNode;
            return;
        }
        
        TreeNode current = root;
        
        while(true) {
            if (score > current.score) {
                if (current.right == null) {
                    current.right = newNode;
                    break;
                }
                current = current.right;
            } 
            else if (score < current.score) {
                if (current.left == null) {
                    current.left = newNode;
                    break;
                }
                current = current.left;
            }
            else {
                break;
            }
        }        
    } 
    
    public TreeNode getBestScore() {
        if (root == null){
            return null;
        }
        
        TreeNode current = root;
        
        while(current.right != null) {
            current = current.right;
        }
        return current;
    }
    
    public TreeNode getWorstScore() {
        if (root == null){
            return null;
        }
        
        TreeNode current = root;
        
        while(current.left != null) {
            current = current.left;
        }
        return current;
    }
    
     public ArrayList<TreeNode> getAllScores() {
        ArrayList<TreeNode> scores = new ArrayList<>();
        inOrderTraversal(root, scores);
        return scores;
    }
    
    private void inOrderTraversal(TreeNode node, ArrayList<TreeNode> scores) {
        if (node != null) {
            inOrderTraversal(node.left, scores);
            scores.add(node);
            inOrderTraversal(node.right, scores);
        }
    }
}
