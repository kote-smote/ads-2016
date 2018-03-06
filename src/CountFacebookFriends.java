import dataStructures.DUGraph;

import java.util.Scanner;

/**
 * Created by martin on 12/15/16.
 * Dadena e edna socijalna mreza (Faceboook) vo koja za korisnicite se cuvaat
 * podatoci za reden broj (celobrojna vrednost), ime i prezime. Isto taka,
 * za sekoj korisnik se poznati negovite prijateli vo socijalnata mreza.
 * Da se napise algoritam koj za dadeni redni broevi na dvajca korisnici go
 * odreduva stepenot na razdelenost megju korisnicite t.e preku kolku
 * najmalku korisnici se povrzani. (preku kolku najmalku korisnici moze da
 * se stigne od edniot do drugiot korisnik) vo socijalnata mreza.
 * Socijalnata mreza e pretstavena kako netezinski graf so lista na sosedstvo.
 * Vo prviot red e daden brojot na korisnicite. Potoa, vo sledniot red e daden
 * brojot na prijatelite na prviot korisnil (so reden broj 0), i vo slednite
 * redovi se navedeni negovite prijateli so reden broj, ime i prezime.
 * Ponatamy se dadeni na istion nacin informaciite za site korisnici.
 * Na kraj vo poslednite dva reda se dadeni rednite broevi na dvata korisnici
 * za koi treba da se odredi stepenot na razdelenost.
 */
public class CountFacebookFriends {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.nextLine());
        DUGraph<String> facebookGraph = new DUGraph<>(N);

        for (int i = 0; i < N; i++) {
            int numFriends = Integer.parseInt(scanner.nextLine());
            for (int j = 0; j < numFriends; j++) {
                String[] parts = scanner.nextLine().split("\\s+");
                int index = Integer.parseInt(parts[0]);
                String name = parts[1];
                facebookGraph.setInfo(index, name);
                facebookGraph.addEdge(i, index);
            }
        }

        int start = scanner.nextInt();
        int end = scanner.nextInt();

        System.out.println(facebookGraph.distance(start, end));
    }
}

/*
SAMPLE INPUT 1:
13
1
9 Slobodan Kalajdziski
1
9 Slobodan Kalajdziski
2
9 Slobodan Kalajdziski
11 Ivan Kitanovski
1
9 Slobodan Kalajdziski
1
9 Slobodan Kalajdziski
1
10 Ana Madevska
2
10 Ana Madevska
11 Ivan Kitanovski
1
10 Ana Madevska
1
10 Ana Madevska
6
0 Ilinka Ivanoska
1 Igor Kulev
2 Magdalena Kostoska
3 Vladimir Trajkovik
4 Anastas Misev
10 Ana Madevska
5
5 Igor Trajkovski
6 Aleksandra Bogojeska
7 Tomce Delev
8 Aleksandra Kanevce
9 Slobodan Kalajdziski
3
2 Magdalena Kostoska
6 Aleksandra Bogojeska
12 Vesna Kirandziska
1
11 Ivan Kitanovski
12
0

SAMPLE OUTPUT 1:
4

SAMPLE INPUT 2:
4
2
1 Igor Trajkovski
2 Ilinka Ivanoska
2
0 Magdalena Kostoska
3 Igor Kulev
2
0 Magdalena Kostoska
3 Igor Kulev
2
1 Igor Trajkovski
2 Ilinka Ivanoska
0
3

SAMPLE OUTPUT 2:
2
 */