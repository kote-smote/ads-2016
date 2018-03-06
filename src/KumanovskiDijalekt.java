import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Daden vi e recnik na zborovi na kumanovski dijalekt i kako tie se pisuvaat
 * na makedonski jazik. Potoa daden vi e tekst kojsto e napisan na kumanovski
 * dijalekt. Potrebno e da gi zamnite site pojavuvanja na zborovite na
 * kumanovskiot dijalekt koi se dadeni vo recnikot so soodeventi zborovi
 * na makedonski jazik.
 *
 * Zabeleska: Treba da se ignoriraat iterpunkciskite znaci tocka (.),
 * zapirka (,), izvicnik (!) i prasalnik (?).
 * Isto taka zborovite vo tekstot mozat da se pojavat i so prva golema bukva
 * i vo toj slucaj negoviot sinonim na makedonski jazik isto taka treba da
 * se otpecati so prva golema bukva.
 */
public class KumanovskiDijalekt {

    public static String trim(String str) {
        char last = str.charAt(str.length()-1);
        if (!Character.isAlphabetic(last))
            return str.substring(0, str.length()-1);
        return str;
    }

    public static String capitalize(String word) {
        // word is already capitalized
        if (Character.isUpperCase(word.charAt(0)))
            return word;
        return word.substring(0, 1).toUpperCase()
                + word.substring(1);
    }

    public static void main (String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(
                System.in));
        int N = Integer.parseInt(br.readLine());

        String rechnik[]=new String[N];
        for(int i=0;i<N;i++){
            rechnik[i]=br.readLine();
        }

        String tekst=br.readLine();

        ClosedBucketHashTable<String, String> dictionary = new ClosedBucketHashTable<>(80);

        // fill the dictionary
        for (String word : rechnik) {
            String[] pairs = word.split("\\s+");
            dictionary.insert(pairs[0], pairs[1]);
        }

        StringBuilder result = new StringBuilder();

        Scanner scanner = new Scanner(tekst);
        while (scanner.hasNext()) {
            String word = scanner.next();
            String translation = dictionary.search(trim(word).toLowerCase());
            if (translation == null)
                result.append(word + " ");
            else {
                Character last = word.charAt(word.length()-1);
                if (!Character.isAlphabetic(last))
                    translation = translation + last;
                if (Character.isUpperCase(word.charAt(0))) {
                    translation = capitalize(translation);
                }
                result.append(translation + " ");
            }
        }
        System.out.println(result.toString());
    }
}