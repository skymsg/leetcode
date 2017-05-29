#include <cstdio>
#include <cstdlib>
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
class Solution {
	public :
		int *result;
		int solve(int idx,vector<int>& nums){
			if(idx < 0){
				return 0;	
			}	
			if(result[idx]>=0){
				return result[idx];	
			}
			result[idx]= max(nums[idx]+solve(idx-2,nums),solve(idx-1,nums));
			return result[idx];
		}		
		int rob(vector<int>& nums){
			result=new int[nums.size()]; 
			for(int i=nums.size()-1;i>=0;--i){
				result[i]=-1;	
			}
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
