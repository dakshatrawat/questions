#include <bits/stdc++.h>
using namespace std;
int main()
{
    int t;
    cin >> t;
    while (t--)
    {
        int n;    // length of string
        string s; // string
        cin >> n >> s;

        map<char, char> next; // map to mark current next element of each character
        map<char, char> prev; // map to mark current previous element of each character
        prev[s[0]] = '$';     // no previous element of 1st element $=trash value
        next[s[n - 1]] = '$'; // no next element of last element
        prev['$'] = s[n - 1];
        next['$'] = s[0];
        // maps are used like linked list here.. to make deletion and merging easy

        int ans = 0;
        // marking next and previous character to every character
        for (int i = 0; i < n - 1; i++)
        {
            next[s[i]] = s[i + 1];
            prev[s[i + 1]] = s[i];
        }

        int i = 97; // starting from character 'a'.

        while (i < 97 + n)
        {
            int u = i;
            while (next[char(u)] == char(u + 1))
            {
                u++;
            } // select substring with consecutive sorted characters

            // merge non selected substring .. as selected will be appended at the end of string p
            next[prev[char(i)]] = next[char(u)];
            prev[next[char(u)]] = prev[char(i)];

            i = u + 1; // next iteration will start from next character
            ans++;
        }

        cout << ans << endl;
    }
}