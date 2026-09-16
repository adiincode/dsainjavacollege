class Solution {
public:

    vector<int> solve(string expression) {

        vector<int> ans;

        // Try every operator as the last operation
        for (int i = 0; i < expression.size(); i++) {

            char op = expression[i];

            if (op == '+' || op == '-' || op == '*') {

                // Left side
                string left = expression.substr(0, i);

                // Right side
                string right = expression.substr(i + 1);

                // All possible results of left and right
                vector<int> leftResults = solve(left);
                vector<int> rightResults = solve(right);

                // Combine every possible pair
                for (int x : leftResults) {
                    for (int y : rightResults) {

                        if (op == '+')
                            ans.push_back(x + y);

                        else if (op == '-')
                            ans.push_back(x - y);

                        else
                            ans.push_back(x * y);
                    }
                }
            }
        }

        // No operator means expression is just a number
        if (ans.empty()) {
            ans.push_back(stoi(expression));
        }

        return ans;
    }

    vector<int> diffWaysToCompute(string expression) {
        return solve(expression);
    }
};