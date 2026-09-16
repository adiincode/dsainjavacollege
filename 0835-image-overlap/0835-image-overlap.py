class Solution:
    def largestOverlap(self, img1, img2):
        n = len(img1)

        ones1 = []
        ones2 = []

        # Store coordinates of all 1s
        for i in range(n):
            for j in range(n):
                if img1[i][j] == 1:
                    ones1.append((i, j))

                if img2[i][j] == 1:
                    ones2.append((i, j))

        count = {}
        ans = 0

        # Try every possible translation
        for x1, y1 in ones1:
            for x2, y2 in ones2:

                dx = x2 - x1
                dy = y2 - y1

                count[(dx, dy)] = count.get((dx, dy), 0) + 1

                ans = max(ans, count[(dx, dy)])

        return ans