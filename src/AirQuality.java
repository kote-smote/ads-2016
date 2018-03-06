import java.text.DecimalFormat;
import java.util.List;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by martin on 12/21/16.
 *
 * Dadeni se merenja na PM10 cesticki za naselbite vo Skopje.
 * Vasa zadaca e za dadena naselba da se najde prosecnata
 * koncentracija na PM10 cesticki.
 *
 * Vlez: Vo prviot red e daden brojot na merenja N, a vo sekoj
 * nareden red e prvo dadena naselbata i koncetracijata na PM10
 * razdeleni so prazno mesto. Vo poseldniot red e dadena naselbata
 * za koja treba da se najde prosecna koncentracija na PM10 cesticki.
 *
 * Izlez: Prosecnata koncentracija na PM10 cesticki za dadenata
 * naselba (zaokrizeni na 2 decimali)
 *
 * Pomos: Za zaokruzuvanje Vo Jaca moze da go koristite sledniot kod:
 *
 * DecimalFormat df = new DecimalFormat("######.##");
 * double a = 335.453333;
 * df.format(a);
 *
 * Primer Vlez:
 * 8
 * Centar 319.61
 * Karposh 296.74
 * Centar 531.98
 * Karposh 316.44
 * GaziBaba 384.05
 * GaziBaba 319.3
 * Karposh 393.18
 * GaziBaba 326.42
 * Karposh
 *
 * Primer Izlez:
 * 335.45
 */
public class AirQuality {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        HashMap<String, Measurement> measures = new HashMap<>();

        int N = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < N; i++) {
            String[] pairs = sc.nextLine().split("\\s+");
            String munincipality = pairs[0];
            Double sample = Double.parseDouble(pairs[1]);

            if (measures.get(munincipality) == null) {
                Measurement m = new Measurement(munincipality);
                m.add(sample);
                measures.put(munincipality, m);
            } else {
                Measurement m = measures.get(munincipality);
                m.add(sample);
            }
        }
        String lookUp = sc.nextLine();
        Measurement m = measures.get(lookUp);
        double avg = m.avgConcentration();
        DecimalFormat df = new DecimalFormat(".##");
        System.out.println(df.format(avg));
    }
}

class Measurement {
    private String munincipality;
    private List<Double> metrics;

    public Measurement(String munincipality) {
        this.munincipality = munincipality;
        metrics = new ArrayList<>();
    }

    public String getName() {
        return munincipality;
    }
    public void add(double k) {
        metrics.add(k);
    }

    public double avgConcentration() {
        return metrics.stream()
                .mapToDouble(Double::valueOf)
                .average()
                .orElse(0);
    }

}