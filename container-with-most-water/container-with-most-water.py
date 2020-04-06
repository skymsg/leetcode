from typing import List
import random
class Solution:
    def maxArea(self, height: List[int]) -> int:
        i=0;
        j=len(height)-1
        if j<=i:
            raise Exception("illegal parameters")
        area = 0
        while i<j:
            area = max(area,(j-i)*min(height[j],height[i]))
            if height[i]<=height[j]:
                i+=1
            else:
                j-=1
        return area

    def robust(self, height: List[int]) -> int:
        l = len(height)
        if l<=1:
            raise Exception("illegal parameters")
        area = 0
        for i in range(l):
            for j in range(i+1,l):
                area = max(area,(j-i)*min(height[j],height[i]))
        return area


if __name__  == "__main__":

    solution = Solution()
    t= [1,8,6,2,5,4,8,3,7]
    print(solution.maxArea(t))
    print(solution.robust(t))
    for x in range(100):
        t = [ random.randint(0,10)  for i in range(10) ]
        print(t)
        a = solution.maxArea(t)
        b = solution.robust(t)
        print(a,b)
