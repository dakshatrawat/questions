import java.util.*;

class ComputeHash {
    private long[] hash;
    private long[] invMod;
    private long mod;
    private long p;

    public ComputeHash(String s, long p, long mod) {
        int n = s.length();
        this.hash = new long[n];
        this.invMod = new long[n];
        this.mod = mod;
        this.p = p;

        long pPow = 1;
        long hashValue = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            c = (char) (c - 'A' + 1);
            hashValue = (hashValue + c * pPow) % this.mod;
            this.hash[i] = hashValue;
            this.invMod[i] = powMod(pPow, this.mod - 2, this.mod);
            pPow = (pPow * this.p) % this.mod;
        }
    }

    private long powMod(long base, long exp, long mod) {
        long result = 1;
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp /= 2;
        }
        return result;
    }

    public long getHash(int l, int r) {
        if (l == 0) {
            return this.hash[r];
        }

        long window = (this.hash[r] - this.hash[l - 1] + this.mod) % this.mod;
        return (window * this.invMod[l]) % this.mod;
    }
}

public class solution {
    static int mod = (int) (1e9 + 7);

    public static boolean exists(int k, String X, String Y, ComputeHash hashX1, ComputeHash hashX2, ComputeHash hashY1, ComputeHash hashY2) {
        for (int i = 0; i <= X.length() - k; i++) {
            for (int j = 0; j <= Y.length() - k; j++) {
                if (X.substring(i, i + k).equals(Y.substring(j, j + k))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int longestCommonSubstr(String X, String Y) {
        int n = X.length();
        int m = Y.length();

        long p1 = 31;
        long p2 = 37;
        long m1 = (long) (Math.pow(10, 9) + 9);
        long m2 = (long) (Math.pow(10, 9) + 7);
        ComputeHash hashX1 = new ComputeHash(X, p1, m1);
        ComputeHash hashX2 = new ComputeHash(X, p2, m2);

        ComputeHash hashY1 = new ComputeHash(Y, p1, m1);
        ComputeHash hashY2 = new ComputeHash(Y, p2, m2);

        int low = 0, high = Math.min(n, m);
        int answer = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (exists(mid, X, Y, hashX1, hashX2, hashY1, hashY2)) {
                answer = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        List<Long> v = new ArrayList<>();

        while (q-- > 0) {
            int n = sc.nextInt();
            String s = sc.next();
            String t = sc.next();
            v.add((long) longestCommonSubstr(s, t)); // calculating longest common substring (standard method)
        }

        Collections.sort(v);
        long prev = -1, ct = 1, ans = 1;
        for (long i : v) {
            if (prev == i) {
                ct++;
            } else {
                ct = 1;
                prev = i;
            }
            ans *= ct;
            ans %= mod;
        }
        System.out.println(ans);
        sc.close();
    }
}
