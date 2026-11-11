
class Solution:
    def nextGreaterElement(self, n: int) -> int:
        s = list(str(n))
        i = len(s) - 2

        while i >= 0 and s[i] >= s[i + 1]:
            i -= 1

        if i < 0:
            return -1

        j = len(s) - 1
        while s[j] <= s[i]:
            j -= 1

        s[i], s[j] = s[j], s[i]
        s[i + 1:] = reversed(s[i + 1:])

        ans = int(''.join(s))
        return ans if ans < 2**31 else -1
