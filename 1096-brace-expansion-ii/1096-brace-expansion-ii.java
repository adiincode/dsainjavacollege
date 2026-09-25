import java.util.*;

class Solution {

    public List<String> braceExpansionII(String expression) {
        Result res = parse(expression, 0);

        List<String> ans = new ArrayList<>(res.set);

        Collections.sort(ans);   // ⭐ Missing part

        return ans;
    }

    static class Result {
        Set<String> set;
        int index;

        Result(Set<String> set, int index) {
            this.set = set;
            this.index = index;
        }
    }

    private Result parse(String s, int i) {

        Set<String> result = new HashSet<>();

        Set<String> current = new HashSet<>();
        current.add("");

        while (i < s.length() && s.charAt(i) != '}') {

            char c = s.charAt(i);

            // Union
            if (c == ',') {
                result.addAll(current);

                current = new HashSet<>();
                current.add("");

                i++;
            }

            // Braces
            else if (c == '{') {

                Result inside = parse(s, i + 1);

                current = combine(current, inside.set);

                i = inside.index + 1;
            }

            // Character
            else {

                Set<String> temp = new HashSet<>();

                for (String str : current) {
                    temp.add(str + c);
                }

                current = temp;

                i++;
            }
        }

        result.addAll(current);

        return new Result(result, i);
    }

    private Set<String> combine(Set<String> a, Set<String> b) {

        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}