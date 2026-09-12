import java.util.*;

class Solution {

    class State {
        long score;
        List<Integer> indices;

        State(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    int n;
    int[][] arr;
    int[] next;
    State[][] dp;

    public int[] maximumWeight(List<List<Integer>> intervals) {

        n = intervals.size();
        arr = new int[n][4];

        // left, right, weight, original index
        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        // Sort by left, then right, then original index
        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0])
                return Integer.compare(a[0], b[0]);

            if (a[1] != b[1])
                return Integer.compare(a[1], b[1]);

            return Integer.compare(a[3], b[3]);
        });

        // Left endpoints
        int[] starts = new int[n];

        for (int i = 0; i < n; i++) {
            starts[i] = arr[i][0];
        }

        // Find first interval with left > current right
        next = new int[n];

        for (int i = 0; i < n; i++) {
            next[i] = upperBound(starts, arr[i][1]);
        }

        dp = new State[n + 1][5];

        State ans = solve(0, 4);

        int[] result = new int[ans.indices.size()];

        for (int i = 0; i < ans.indices.size(); i++) {
            result[i] = ans.indices.get(i);
        }

        return result;
    }

    // First index where starts[index] > target
    private int upperBound(int[] starts, int target) {

        int left = 0;
        int right = starts.length;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (starts[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private State solve(int i, int k) {

        if (i == n || k == 0) {
            return new State(0, new ArrayList<>());
        }

        if (dp[i][k] != null) {
            return dp[i][k];
        }

        // Option 1: Skip current interval
        State skip = solve(i + 1, k);

        // Option 2: Take current interval
        State after = solve(next[i], k - 1);

        List<Integer> takeList = new ArrayList<>();

        takeList.add(arr[i][3]);
        takeList.addAll(after.indices);

        // IMPORTANT:
        // Keep indices sorted for lexicographical comparison
        Collections.sort(takeList);

        State take = new State(
            arr[i][2] + after.score,
            takeList
        );

        dp[i][k] = better(take, skip);

        return dp[i][k];
    }

    private State better(State a, State b) {

        // Maximum score
        if (a.score > b.score) {
            return a;
        }

        if (a.score < b.score) {
            return b;
        }

        // Same score -> lexicographically smaller indices
        return compare(a.indices, b.indices) <= 0 ? a : b;
    }

    private int compare(List<Integer> a, List<Integer> b) {

        int size = Math.min(a.size(), b.size());

        for (int i = 0; i < size; i++) {

            if (!a.get(i).equals(b.get(i))) {
                return Integer.compare(
                    a.get(i),
                    b.get(i)
                );
            }
        }

        // If one is prefix of the other,
        // shorter one is lexicographically smaller
        return Integer.compare(a.size(), b.size());
    }
}