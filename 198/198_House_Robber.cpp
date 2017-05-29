#include <cstdio>
#include <cstdlib>
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
class Solution {
	public :
		int solve(int idx,vector<int>& nums){
			if(idx < 0){
				return 0;	
			}	
			return max(nums[idx]+solve(idx-2,nums),solve(idx-1,nums));
		}		
		int rob(vector<int>& nums){
			return solve(nums.size()-1,nums);
		}
};
int main(){
	Solution s;
	vector<int> a;
	a.push_back(1);
	a.push_back(2);
	a.push_back(3);
	cout<<s.rob(a)<<endl;
return 0;
}
