#include <vector>
#include <cstdlib>
#include <cstdio>
#define INF 0xffffff
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x): val(x),left(NULL),right(NULL) {}
};
class Solution {
public:
    std::vector<int> middle_order_sequence;
    void middle_order_traval(TreeNode* root) {
        if(NULL != root) {
            middle_order_traval(root->left);
            middle_order_sequence.push_back(root->val);
            middle_order_traval(root->right);
        }
    }
    bool isValidBST(TreeNode* root) {
        middle_order_sequence = std::vector<int>();
        middle_order_traval(root);
        if(middle_order_sequence.size()<=1){
               return true;
        }
        std::vector<int>::iterator iter = middle_order_sequence.begin()+1;
        while(iter < middle_order_sequence.end() && *iter>*(iter-1)) {
            ++iter;
        }
        return iter==middle_order_sequence.end();
    }
};
int main(int argc,char **argv) {
    TreeNode* root =new  TreeNode(5);
    (*root).left = new TreeNode(3);
    (*root).right = new TreeNode(6);
    (*root).left->left=new TreeNode(1);
    (*root).left->right=new TreeNode(4);


    Solution solution = Solution();
    printf("%d",solution.isValidBST(root));

}
