
class Solution:
    def minimumSumSubarray(self, nums: List[int], l: int, r: int) -> int:
        ans = float('inf')
        n = len(nums)

        for i in range(n):
            s = 0
            for j in range(i, min(i + r, n)):
                s += nums[j]
                length = j - i + 1

                if l <= length <= r and s > 0:
                    ans = min(ans, s)

        return ans if ans != float('inf') else -1
