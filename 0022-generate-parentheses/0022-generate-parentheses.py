
class Solution:
    def generateParenthesis(self, n: int) -> list[str]:
        result = []

        def backtrack(current, open_count, close_count):
            # Base case: all parentheses are used
            if len(current) == 2 * n:
                result.append(current)
                return

            # Add opening bracket if available
            if open_count < n:
                backtrack(current + "(", open_count + 1, close_count)

            # Add closing bracket only if valid
            if close_count < open_count:
                backtrack(current + ")", open_count, close_count + 1)

        backtrack("", 0, 0)
        return result
