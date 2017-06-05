#include <cstdio>
#include <cstdlib>
#include <iostream>
#include <vector>
using namespace std;
class Solution {
public:
    int coinChange(vector<int>& coins, int amount) {
        if(amount == 0 ) return 0;
    	
		const int maxValue=10000000;
		int f[10000];
    	
    	for(int i=0;i<10000;++i){
    		f[i] = maxValue;
		}
		for(int i=0;i<=amount;++i){
			for(int j=0;j<coins.size();++j){
				if(i-coins[j] == 0){
				    f[coins[j]]=1;
				    f[i]=1;
				}
				if(i-coins[j] >0 ){
					f[i] = min(f[i-coins[j]]+1,f[i]);
				}
			}
			
		}
		if(f[amount] >= maxValue) {
			return -1;
		}else {
			return f[amount];
		}
    }
};
int main(){
	return 0;
}
