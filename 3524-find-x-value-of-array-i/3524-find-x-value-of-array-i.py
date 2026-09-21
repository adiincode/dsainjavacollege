from typing import List

class Solution:
    def resultArray(self, nums: List[int], k: int) -> List[int]:
        ans = [0] * k

        # dp[r] = number of subarrays ending at previous index
        # whose product % k == r
        dp = [0] * k

        for num in nums:
            x = num % k

            new_dp = [0] * k

            # Start a new subarray [num]
            new_dp[x] += 1

            # Extend all previous subarrays
            for r in range(k):
                if dp[r]:
                    new_r = (r * x) % k
                    new_dp[new_r] += dp[r]

            # Every subarray ending here is one valid operation
            for r in range(k):
                ans[r] += new_dp[r]

            dp = new_dp

        return ans