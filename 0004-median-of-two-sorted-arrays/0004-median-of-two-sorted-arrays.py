class Solution:
    def findMedianSortedArrays(self, nums1, nums2):
        # Always binary search on the smaller array
        if len(nums1) > len(nums2):
            nums1, nums2 = nums2, nums1

        m = len(nums1)
        n = len(nums2)

        left = 0
        right = m

        while left <= right:
            # Partition nums1
            partition1 = (left + right) // 2

            # Partition nums2
            partition2 = (m + n + 1) // 2 - partition1

            # Boundary values
            left1 = float('-inf') if partition1 == 0 else nums1[partition1 - 1]
            right1 = float('inf') if partition1 == m else nums1[partition1]

            left2 = float('-inf') if partition2 == 0 else nums2[partition2 - 1]
            right2 = float('inf') if partition2 == n else nums2[partition2]

            # Correct partition
            if left1 <= right2 and left2 <= right1:

                # Odd total length
                if (m + n) % 2 == 1:
                    return max(left1, left2)

                # Even total length
                return (max(left1, left2) + min(right1, right2)) / 2

            # Move partition in nums1
            elif left1 > right2:
                right = partition1 - 1

            else:
                left = partition1 + 1