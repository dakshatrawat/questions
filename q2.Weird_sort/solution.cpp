#include <bits/stdc++.h>
using namespace std;
#define int long long
int mod = 1e9 + 7;
class ComputeHash
{
private:
    std::vector<long> hash;
    std::vector<long> invMod;
    long mod;
    long p;

public:
    ComputeHash(std::string s, long p, long mod)
    {
        int n = s.length();
        this->hash.resize(n);
        this->invMod.resize(n);
        this->mod = mod;
        this->p = p;

        long pPow = 1;
        long hashValue = 0;

        for (int i = 0; i < n; i++)
        {
            char c = s[i];
            c = static_cast<char>(c - 'A' + 1);
            hashValue = (hashValue + c * pPow) % this->mod;
            this->hash[i] = hashValue;
            this->invMod[i] = static_cast<long>(std::pow(pPow, this->mod - 2)) % this->mod;
            pPow = (pPow * this->p) % this->mod;
        }
    }

    long getHash(int l, int r)
    {
        if (l == 0)
        {
            return this->hash[r];
        }

        long window = (this->hash[r] - this->hash[l - 1] + this->mod) % this->mod;
        return (window * this->invMod[l]) % this->mod;
    }
};

bool exists(int k, std::string X, std::string Y, ComputeHash &hashX1,
            ComputeHash &hashX2, ComputeHash &hashY1, ComputeHash &hashY2)
{
    for (int i = 0; i <= X.length() - k; i++)
    {
        for (int j = 0; j <= Y.length() - k; j++)
        {
            if (X.substr(i, k) == Y.substr(j, k))
            {
                return true;
            }
        }
    }
    return false;
}

int longestCommonSubstr(std::string X, std::string Y)
{
    int n = X.length();
    int m = Y.length();

    long p1 = 31;
    long p2 = 37;
    long m1 = static_cast<long>(std::pow(10, 9) + 9);
    long m2 = static_cast<long>(std::pow(10, 9) + 7);
    ComputeHash hashX1(X, p1, m1);
    ComputeHash hashX2(X, p2, m2);

    ComputeHash hashY1(Y, p1, m1);
    ComputeHash hashY2(Y, p2, m2);
    int low = 0, high = std::min(n, m);
    int answer = 0;

    while (low <= high)
    {
        int mid = (low + high) / 2;
        if (exists(mid, X, Y, hashX1, hashX2, hashY1, hashY2))
        {
            answer = mid;
            low = mid + 1;
        }
        else
        {
            high = mid - 1;
        }
    }

    return answer;
}

signed main()
{
    int q;
    cin >> q;
    vector<int> v;
    while (q--)
    {
        int n;
        cin>>n;
        string s, t;
        cin >> s >> t;
        v.push_back(longestCommonSubstr(s, t));//calculating longest common substring(standard method)
    }
    sort(v.begin(), v.end());
    int prev = -1, ct = 1, ans = 1;
    for (auto i : v)
    {
        if (prev == i)
            ct++;
        else
        {
            ct = 1;
            prev = i;
        }
        ans *= ct;
        ans %= mod;
    }
    cout << ans << endl;
}