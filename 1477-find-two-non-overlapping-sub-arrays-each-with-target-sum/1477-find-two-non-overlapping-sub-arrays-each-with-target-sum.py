class Solution:
    def minSumOfLengths(self, arr: list[int], target: int) -> int:
        n = len(arr)
        INF = float('inf')
        # dp[i] = min length of a valid subarray ending within arr[0..i-1]
        dp = [INF] * (n + 1)
        
        left = 0
        curr_sum = 0
        ans = INF
        
        for right in range(n):
            curr_sum += arr[right]
            
            while curr_sum > target:
                curr_sum -= arr[left]
                left += 1
            
            if curr_sum == target:
                length = right - left + 1
                if dp[left] != INF:
                    ans = min(ans, dp[left] + length)
                dp[right + 1] = min(dp[right], length)
            else:
                dp[right + 1] = dp[right]
        
        return ans if ans != INF else -1