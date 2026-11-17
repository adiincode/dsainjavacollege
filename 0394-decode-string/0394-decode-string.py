
class Solution:
    def decodeString(self, s: str) -> str:
        stack = []
        num = 0
        curr = ""

        for ch in s:
            if ch.isdigit():
                num = num * 10 + int(ch)

            elif ch == '[':
                stack.append((curr, num))
                curr = ""
                num = 0

            elif ch == ']':
                prev, k = stack.pop()
                curr = prev + curr * k

            else:
                curr += ch

        return curr
