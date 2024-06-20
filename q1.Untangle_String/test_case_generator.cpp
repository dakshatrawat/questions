#include <bits/stdc++.h>
using namespace std;

string generateRandomString(int n, vector<char> chars)
{
    random_shuffle(chars.begin(), chars.end());
    return string(chars.begin(), chars.begin() + n);
}

int main()
{
    freopen("input.txt", "w", stdout);
    srand(time(0));
    int t;
    cin >> t; // number of test cases needed
    cout << t << endl;

    for (int testCase = 0; testCase < t; testCase++)
    {
        int n = rand() % 10 + 1;
        cout << n << endl;

        vector<char> chars;
        for (char c = 'a'; c < 'a' + n; c++)
        {
            chars.push_back(c);
        }

        string s = generateRandomString(n, chars);
        string r = generateRandomString(n, chars);

        cout << s << endl;
        cout << r << endl;
    }

    return 0;
}