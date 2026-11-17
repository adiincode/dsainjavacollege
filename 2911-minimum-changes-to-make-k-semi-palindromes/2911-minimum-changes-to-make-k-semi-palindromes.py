class Solution:
    def minimumChanges(self, s: str, k: int) -> int:
        n = len(s)
        INF = float("inf")

        # Proper divisors for each possible substring length.
        divisors = [[] for _ in range(n + 1)]
        for d in range(1, n // 2 + 1):
            for length in range(2 * d, n + 1, d):
                divisors[length].append(d)

        # cost[start][end]: changes needed for s[start:end].
        cost = [[INF] * (n + 1) for _ in range(n)]

        for length in range(2, n + 1):
            for start in range(n - length + 1):
                end = start + length

                for d in divisors[length]:
                    changes = 0

                    for offset in range(d):
                        left = start + offset
                        right = end - d + offset

                        while left < right:
                            changes += s[left] != s[right]
                            left += d
                            right -= d

                    cost[start][end] = min(
                        cost[start][end], changes
                    )

        # prev[end]: minimum changes to partition s[:end]
        # into the previously computed number of parts.
        prev = [INF] * (n + 1)
        prev[0] = 0

        for parts in range(1, k + 1):
            curr = [INF] * (n + 1)

            # Every part must contain at least two characters.
            for end in range(2 * parts, n + 1):
                for start in range(2 * (parts - 1), end - 1):
                    curr[end] = min(
                        curr[end],
                        prev[start] + cost[start][end]
                    )

            prev = curr

        return prev[n]