class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] count1 = new int[26];
        int[] count2 = new int[26];

        // Frequency of characters in s1
        for (char c : s1.toCharArray()) {
            count1[c - 'a']++;
        }

        int window = s1.length();

        // First window
        for (int i = 0; i < window; i++) {
            count2[s2.charAt(i) - 'a']++;
        }

        if (Arrays.equals(count1, count2)) {
            return true;
        }

        // Slide the window
        for (int i = window; i < s2.length(); i++) {
            count2[s2.charAt(i) - 'a']++;

            // Remove leftmost character
            count2[s2.charAt(i - window) - 'a']--;

            if (Arrays.equals(count1, count2)) {
                return true;
            }
        }

        return false;
    }
}