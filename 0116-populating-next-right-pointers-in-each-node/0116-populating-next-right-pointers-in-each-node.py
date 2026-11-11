
class Solution:
    def connect(self, root: 'Node') -> 'Node':
        if not root:
            return root

        leftmost = root

        while leftmost.left:
            curr = leftmost

            while curr:
                curr.left.next = curr.right

                if curr.next:
                    curr.right.next = curr.next.left

                curr = curr.next

            leftmost = leftmost.left

        return root
