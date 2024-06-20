import java.util.*;

public class solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt(); // number of test cases

        while (t-- > 0) {
            int n = scanner.nextInt(); // length of strings
            String s = scanner.next(); // input string S
            String r = scanner.next(); // input string R

            // Map to store the hash value of each character of final string R
            Map<Character, Integer> hash = new HashMap<>();
            // Map to get the character from hash value
            Map<Integer, Character> antihash = new HashMap<>();

            // Assign hash value to each character in R based on its position
            for (int i = 0; i < n; i++) {
                hash.put(r.charAt(i), i + 1);
                antihash.put(i + 1, r.charAt(i));
            }

            // Map to mark the current next element of each character
            Map<Character, Character> next = new HashMap<>();
            // Map to mark the current previous element of each character
            Map<Character, Character> prev = new HashMap<>();

            prev.put(s.charAt(0), '$'); // no previous element for the first element
            next.put(s.charAt(n - 1), '$'); // no next element for the last element
            prev.put('$', s.charAt(n - 1));
            next.put('$', s.charAt(0));
            hash.put('$', -1);
            antihash.put(-1, '$');

            // Marking next and previous character for every character in S
            for (int i = 0; i < n - 1; i++) {
                next.put(s.charAt(i), s.charAt(i + 1));
                prev.put(s.charAt(i + 1), s.charAt(i));
            }

            int ans = 0;
            int i = 1; // starting from the first character

            while (i <= n) {
                int u = i;
                // Select substring with consecutive sorted characters
                while (hash.get(next.get(antihash.get(u))) == u + 1) {
                    u++;
                }

                // Merge non-selected substring as selected will be appended at the end of string P
                next.put(prev.get(antihash.get(i)), next.get(antihash.get(u)));
                prev.put(next.get(antihash.get(u)), prev.get(antihash.get(i)));

                i = u + 1; // next iteration will start from the next character
                ans++;
            }

            System.out.println(ans);
        }
        scanner.close();
    }
}
