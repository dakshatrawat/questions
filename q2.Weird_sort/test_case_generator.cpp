#include <bits/stdc++.h>
#define int long long
using namespace std;
const int N = 1e9 + 1, mod = 1e9 + 7;
mt19937 rng(chrono::steady_clock::now().time_since_epoch().count());
string gen_random(const int len)
{
    static const char alphanum[] =
        "abcdefghijklmnopqrstuvwxyz";
    std::string tmp_s;
    tmp_s.reserve(len);

    for (int i = 0; i < len; ++i)
    {
        tmp_s += alphanum[rand() % (sizeof(alphanum) - 1)];
    }

    return tmp_s;
}
int gen(int n)
{
    int x = rng() % n;
    return x;
}
signed main()
{
    ios::sync_with_stdio(false);
    cin.tie(0);
    // freopen("input.txt", "r", stdin);
    freopen("input.txt", "w", stdout);

    // while(tc == 0)
    // {                                 // let it be commented out
    //     tc = gen(100);
    // }

    int tc;
    cin >> tc; // include this if you want to include testcase;
    cout << tc << endl;
    while (tc--)
    {
        int n = gen(26) + 1;
        cout << n << endl;
        string s = gen_random(n), r = gen_random(n);
        cout << s << endl;
        cout << r << endl;
    }
    return 0;
}

// freopen("input.txt", "r", stdin);
// freopen("output.txt", "w", stdout);