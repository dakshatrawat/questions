import java.util.*;

public class solution{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt(); // number of test cases
        while (t-- > 0) {
            int n = scanner.nextInt(); // length of string
            String s = scanner.next(); // string

            String s2 = s;
            char[] s2Array = s2.toCharArray();
            Arrays.sort(s2Array); // sort the string
            s2 = new String(s2Array);

            Map<Character, Integer> hash = new HashMap<>(); // map to store hash value of each character
            Map<Integer, Character> antihash = new HashMap<>(); // map to get character from hash

            // hash value = each character's position in sorted string
            for (int i = 0; i < n; i++) {
                hash.put(s2.charAt(i), i + 1);
                antihash.put(i + 1, s2.charAt(i));
            }

            Map<Character, Character> next = new HashMap<>(); // map to mark current next element of each character
            Map<Character, Character> prev = new HashMap<>(); // map to mark current previous element of each character
            prev.put(s.charAt(0), '$'); // no previous element of 1st element $=trash value
            next.put(s.charAt(n - 1), '$'); // no next element of last element
            prev.put('$', s.charAt(n - 1));
            next.put('$', s.charAt(0));
            hash.put('$', -1);
            antihash.put(-1, '$');
            // maps are used like linked list here to make deletion and merging easy

            int ans = 0;

            // marking next and previous character to every character
            for (int i = 0; i < n - 1; i++) {
                next.put(s.charAt(i), s.charAt(i + 1));
                prev.put(s.charAt(i + 1), s.charAt(i));
            }

            int i = 1; // starting from first character
            while (i <= n) {
                int u = i;
                while (hash.get(next.get(antihash.get(u))) == u + 1) {
                    u++;
                } // select substring with consecutive sorted characters

                // merge non-selected substring, as selected will be appended at the end of string p
                next.put(prev.get(antihash.get(i)), next.get(antihash.get(u)));
                prev.put(next.get(antihash.get(u)), prev.get(antihash.get(i)));

                i = u + 1; // next iteration will start from next character
                ans++;
            }

            System.out.println(ans);
        }
        scanner.close(); // closing the scanner
    }
}
