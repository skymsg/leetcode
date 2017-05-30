#include <cstdio>
#include <cstdlib>
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
class Solution {
	public :
		int *result;
		int rob(vector<int>& nums){
			if(nums.size() == 0) return 0;
			if(nums.size() == 1) return nums[0];
			result=new int[nums.size()]; 
			for(int i=nums.size()-1;i>=0;--i){
				result[i]=0;	
			}
			result[0]=nums[0];
			result[1]=max(nums[1],nums[0]);
			for(int idx=2;idx < nums.size();++idx)
				result[idx]= max(nums[idx]+result[idx-2],result[idx-1]);
			return result[nums.size()-1];
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
