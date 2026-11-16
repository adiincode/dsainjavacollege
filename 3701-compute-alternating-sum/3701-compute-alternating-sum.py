
    
class Solution:
    def alternatingSum(self, nums: List[int]) -> int:
        return sum(x if i % 2 == 0 else -x for i, x in enumerate(nums))
