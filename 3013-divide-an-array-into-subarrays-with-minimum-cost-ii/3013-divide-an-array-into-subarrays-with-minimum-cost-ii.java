
import java.util.*;

class Solution {
    TreeMap<Integer, Integer> low = new TreeMap<>();
    TreeMap<Integer, Integer> high = new TreeMap<>();

    int lowSize = 0;
    int highSize = 0;
    long sum = 0;

    public long minimumCost(int[] nums, int k, int dist) {
        int need = k - 1;

        // Initial window: indices 1 to dist + 1
        for (int i = 1; i <= dist + 1; i++) {
            add(nums[i]);
        }

        rebalance(need);

        long ans = nums[0] + sum;

        // Sliding window
        for (int i = dist + 2; i < nums.length; i++) {
            remove(nums[i - dist - 1]);
            add(nums[i]);

            rebalance(need);

            ans = Math.min(ans, nums[0] + sum);
        }

        return ans;
    }

    void add(int x) {
        if (lowSize == 0 || x <= low.lastKey()) {
            low.merge(x, 1, Integer::sum);
            lowSize++;
            sum += x;
        } else {
            high.merge(x, 1, Integer::sum);
            highSize++;
        }
    }

    void remove(int x) {
        if (low.containsKey(x)) {
            erase(low, x);
            lowSize--;
            sum -= x;
        } else {
            erase(high, x);
            highSize--;
        }
    }

    void erase(TreeMap<Integer, Integer> map, int x) {
        if (map.get(x) == 1) {
            map.remove(x);
        } else {
            map.put(x, map.get(x) - 1);
        }
    }

    void rebalance(int need) {
        // Maintain exactly need smallest elements in low
        while (lowSize > need) {
            int x = low.lastKey();
            erase(low, x);
            lowSize--;

            sum -= x;

            high.merge(x, 1, Integer::sum);
            highSize++;
        }

        while (lowSize < need) {
            int x = high.firstKey();
            erase(high, x);
            highSize--;

            low.merge(x, 1, Integer::sum);
            lowSize++;

            sum += x;
        }

        // Ensure low contains the smallest values
        while (!low.isEmpty() && !high.isEmpty()
                && low.lastKey() > high.firstKey()) {

            int a = low.lastKey();
            int b = high.firstKey();

            erase(low, a);
            erase(high, b);

            low.merge(b, 1, Integer::sum);
            high.merge(a, 1, Integer::sum);

            sum += (long) b - a;
        }
    }
}
