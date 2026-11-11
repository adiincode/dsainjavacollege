
class Solution:
    def minimumCost(self, cost1: int, cost2: int, costBoth: int, need1: int, need2: int) -> int:
        return min(need1 * cost1 + need2 * cost2,
                   min(need1, need2) * costBoth +
                   max(need1 - need2, 0) * cost1 +
                   max(need2 - need1, 0) * cost2,
                   max(need1, need2) * costBoth)
