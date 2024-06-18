import java.util.*;

public class solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t-- > 0) {
            int n = scanner.nextInt(); // length of string
            String s = scanner.next(); // string

            Map<Character, Character> next = new HashMap<>(); // map to mark current next element of each character
            Map<Character, Character> prev = new HashMap<>(); // map to mark current previous element of each character
            prev.put(s.charAt(0), '$'); // no previous element of 1st element $=trash value
            next.put(s.charAt(n - 1), '$'); // no next element of last element
            prev.put('$', s.charAt(n - 1));
            next.put('$', s.charAt(0));
            // maps are used like linked list here.. to make deletion and merging easy

            int ans = 0;
            // marking next and previous character to every character
            for (int i = 0; i < n - 1; i++) {
                next.put(s.charAt(i), s.charAt(i + 1));
                prev.put(s.charAt(i + 1), s.charAt(i));
            }

            int i = 97; // starting from character 'a'

            while (i < 97 + n) {
                int u = i;
                while (next.get((char) u) != null && next.get((char) u) == (char) (u + 1)) {
                    u++;
                } // select substring with consecutive sorted characters

                // merge non-selected substring .. as selected will be appended at the end of string p
                next.put(prev.get((char) i), next.get((char) u));
                prev.put(next.get((char) u), prev.get((char) i));

                i = u + 1; // next iteration will start from next character
                ans++;
            }

            System.out.println(ans);
        }

        scanner.close();
    }
}
