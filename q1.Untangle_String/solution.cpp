#include <bits/stdc++.h>
using namespace std;
int main()
{
    int t;
    cin >> t;
    while (t--)
    {
        int n;       // length of string
        string s, r; // strings
        cin >> n >> s >> r;

        map<char, int> hash;     // map to store hash value of each character
        map<int, char> antihash; // map to get character from hash
        // hash value= each characters position in final string R
        for (int i = 0; i < n; i++)
        {
            hash[r[i]] = i + 1;
            antihash[i + 1] = r[i];
        }

        map<char, char> next; // map to mark current next element of each character
        map<char, char> prev; // map to mark current previous element of each character
        prev[s[0]] = '$';     // no previous element of 1st element $=trash value
        next[s[n - 1]] = '$'; // no next element of last element
        prev['$'] = s[n - 1];
        next['$'] = s[0];
        hash['$'] = -1;
        antihash[-1] = '$';
        // maps are used like linked list here.. to make deletion and merging easy

        int ans = 0;
        // marking next and previous character to every character
        for (int i = 0; i < n - 1; i++)
        {
            next[s[i]] = s[i + 1];
            prev[s[i + 1]] = s[i];
        }

        int i = 1; // strting from first character
        while (i <= n)
        {
            int u = i;
            while (hash[next[antihash[u]]] == u + 1)
            {
                u++;
            } // select substring with consecutive sorted characters

            // merge non selected substring .. as selected will be appended at the end of string p
            next[prev[antihash[i]]] = next[antihash[u]];
            prev[next[antihash[u]]] = prev[antihash[i]];

            i = u + 1; // next iteration will start from next character
            ans++;
        }

        cout << ans << " " << t << endl;
    }
}