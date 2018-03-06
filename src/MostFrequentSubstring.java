import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import dataStructures.ClosedBucketHashTable;

/**
 * Daden e string. Treba da se najde najcestiot pod-string koj e del od nego
 * i da se ispecati. Dokolku dva pod-stringa se isto frekventni,
 * togas se pecati podolgiot. Dokolku i ovoj uslov go ispolnuvaat togas
 * se pecati leksikografski pomaliot.
 * <p>
 * Pr. za stringot "abc" pod-stringovi se "a", "b", "c", "ab", "bc", "abc".
 * Site imaat ista cestota pa zatoa se pecati najdolgiot "abc".
 */
public class MostFrequentSubstring {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder;
        ClosedBucketHashTable<String, Integer> map
                = new ClosedBucketHashTable<>(300);
        // Store the sub-strings in a list to avoid searching
        // the whole map when looking for the most frequent one
        List<String> substrings = new ArrayList<>();

        String str = br.readLine().trim();

        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j <= str.length(); j++) {

                String subStr = str.substring(i, j);
                substrings.add(subStr);

                if (map.search(subStr) == null)
                    map.insert(subStr, 1);
                else {
                    Integer freq = map.search(subStr);
                    map.insert(subStr, freq + 1);
                }
            }
        }


        String mostFrequent = substrings.stream()
                .max((s1, s2) -> compareSubstrings(s1, s2, map))
                .orElse(null);

        System.out.println(mostFrequent);
    }

    public static int compareSubstrings(String s1, String s2,
                                        ClosedBucketHashTable<String, Integer> table) {
        int freq1 = table.search(s1);
        int freq2 = table.search(s2);
        int res;

        if (freq1 > freq2)
            return 1;
        else if (freq1 < freq2)
            return -1;
        else {
            if (s1.length() > s2.length())
                return 1;
            else if (s1.length() < s2.length())
                return -1;
            else {
                return s2.compareTo(s1); // see String::compareTo documentation
            }
        }
    }
}