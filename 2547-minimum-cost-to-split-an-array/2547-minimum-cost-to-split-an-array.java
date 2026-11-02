
class Solution {
    public int minCost(int[] nums, int k) {
        int n = nums.length;

        int[] dp = new int[n + 1];
        java.util.Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            java.util.HashMap<Integer, Integer> freq =
                new java.util.HashMap<>();

            int trim = 0;

            for (int j = i; j >= 1; j--) {
                int x = nums[j - 1];
                int count = freq.getOrDefault(x, 0);

                if (count == 1) {
                    trim += 2;
                } else if (count > 1) {
                    trim += 1;
                }

                freq.put(x, count + 1);

                dp[i] = Math.min(dp[i], dp[j - 1] + k + trim);
            }
        }

        return dp[n];
    }
}
