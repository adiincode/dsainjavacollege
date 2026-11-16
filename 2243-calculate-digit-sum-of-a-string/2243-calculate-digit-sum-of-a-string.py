
class Solution:
    def digitSum(self, s: str, k: int) -> str:
        while len(s) > k:
            res = ""
            for i in range(0, len(s), k):
                res += str(sum(map(int, s[i:i+k])))
            s = res
        return s
