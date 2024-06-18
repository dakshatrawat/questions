#include <bits/stdc++.h>
using namespace std;
vector<pair<int, string>> generateTestCases(int numCases)
{
    vector<pair<int, string>> testCases;
    random_device rd;
    mt19937 gen(rd());
    uniform_int_distribution<> dis(1, 26);

    for (int i = 0; i < numCases; ++i)
    {
        int n = dis(gen);
        string s = "abcdefghijklmnopqrstuvwxyz";
        shuffle(s.begin(), s.end(), gen);
        s = s.substr(0, n);
        testCases.push_back({n, s});
    }

    return testCases;
}

int main()
{
    int numCases;
    cin >> numCases;
    auto testCases = generateTestCases(numCases);

    cout << numCases << endl;
    for (const auto &tc : testCases)
    {
        cout << tc.first << endl;
        cout << tc.second << endl;
    }

    return 0;
}
