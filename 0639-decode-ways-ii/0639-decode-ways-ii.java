
class Solution {
    private static final long MOD = 1000000007;

    public int numDecodings(String s) {
        int n = s.length();

        long prev2 = 1;
        long prev1 = ways1(s.charAt(0));

        for (int i = 1; i < n; i++) {
            char first = s.charAt(i - 1);
            char second = s.charAt(i);

            long curr = (ways1(second) * prev1) % MOD;

            curr = (curr + ways2(first, second) * prev2) % MOD;

            prev2 = prev1;
            prev1 = curr;
        }

        return (int) prev1;
    }

    private long ways1(char c) {
        if (c == '*') return 9;
        if (c == '0') return 0;
        return 1;
    }

    private long ways2(char a, char b) {
        if (a == '*' && b == '*') return 15;

        if (a == '*') {
            if (b >= '0' && b <= '6') return 2;
            return 1;
        }

        if (b == '*') {
            if (a == '1') return 9;
            if (a == '2') return 6;
            return 0;
        }

        int num = (a - '0') * 10 + (b - '0');

        if (num >= 10 && num <= 26) return 1;
        return 0;
    }
}
