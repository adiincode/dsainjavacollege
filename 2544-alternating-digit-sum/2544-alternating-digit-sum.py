
class Solution:
    def alternateDigitSum(self, n: int) -> int:
        s = str(n)
        return sum(int(s[i]) * (1 if i % 2 == 0 else -1) for i in range(len(s)))
