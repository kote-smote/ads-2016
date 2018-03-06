import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.HashSet;

/**
 * Lab 6 / Problem 1 - Passwords
 * Potrebno e da se simulira najava na eden sistem. Pritoa korisnikot vnesuva
 * korisnicko ime i lozinka. Dokolku korisnickoto ime odgovara so lozinkata
 * togas se pecati "Najaven", dokoklu ne, se pecati "Nenajaven" i na
 * kotisnikot mu se dava povtorno sansa da vnese korisnicko ime i lozinka.
 * Vo momentot koga korisnikot ke bide najaven prestanuvaat obidite za najava.
 *
 * Vlez: Prvo se dava broj N na korisnicki iminja i lozinki koi ke bidat
 * vneseni vo sistemot. Vo narednite N reda se dadeni korisnickite iminja
 * i lozinki razdeleni so edno prazno mesto.
 * Potoa se davaat redovi so korisnicki iminja i lozinki na korisnici koi se
 * obiduvaat da se najavat (Pr. ana banana).
 * Za oznacuvanje kraj na obidite vo redicata se dava zborot KRAJ
 *
 * Izlez: Za sekoj od vlezovite koi se obid za najava se pecati "Nenajaven"
 * se dodeka ne dobieme "Najaven" ili dodeka imame obidi za najava.
 *
 * Zabeleska: Rabotete so hash tabela so zatvoreni koficki, sami resavate za
 * goleminata na hash tabelata
 */
public class Passwords {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ClosedBucketHashTable<String, String> table = new ClosedBucketHashTable<>(51);

        for(int i=1;i<=N;i++){
            String imelozinka = br.readLine();
            String[] parts = imelozinka.split(" ");
            table.insert(parts[0], parts[1]);
        }

        String line;
        while (!(line = br.readLine()).equals("KRAJ")) {
            String[] parts = line.split("\\s+");
            String username = parts[0];
            String tryPass = parts[1];
            String pass = table.search(username);
            if (pass != null && pass.equals(tryPass)) {
                System.out.println("Najaven");
                return;
            }
            System.out.println("Nenajaven");
        }
    }
}

class ClosedBucketHashTable<KEY, VALUE> {
    private SLNode<Entry<KEY, VALUE>>[] buckets;
    private int capacity; // hash table capacity
    private int count; // number of key-value pairs

    @SuppressWarnings("unchecked")
    public ClosedBucketHashTable(int m) {
        buckets = (SLNode<Entry<KEY, VALUE>>[]) new SLNode[m];
        capacity = m;
        count = 0;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int hash(KEY key) {
        char[] letters = ((String) key).toCharArray();
        int sum = 0;
        for (char letter : letters)
            sum += (Character.toUpperCase(letter) - 'A');
        return sum % capacity;
    }

    public VALUE search(KEY key) {
        if (key  == null)
            throw new IllegalArgumentException("null argument");
        int i = hash(key);
        SLNode<Entry<KEY, VALUE>> node = buckets[i];
        if (node == null)
            return null;
        while (node != null) {
            Entry<KEY, VALUE> entry = node.data;
            if (key.equals(entry.key))
                return entry.value;
            node = node.link;
        }
        return null;
    }

    public void insert(KEY key, VALUE value) {
        if (key == null || value == null)
            throw new IllegalArgumentException("null argument");
        // TODO implement resizable (if count >= capacity)
        Entry<KEY, VALUE> newEntry = new Entry<>(key, value);
        int i = hash(key);
        SLNode<Entry<KEY, VALUE>> node = buckets[i];
        while (node != null) {
            if (newEntry.equals(node.data)) {
                node.data = newEntry;
                return;
            }
            node = node.link;
        }
        buckets[i] = new SLNode<>(newEntry, buckets[i]);
        count++;
    }

    public boolean delete(KEY key) {
        if (key  == null)
            throw new IllegalArgumentException("null argument");
        int i = hash(key);
        if (i < 0 || i >= capacity)
            return false;
        SLNode<Entry<KEY, VALUE>> node = buckets[i];
        if (key.equals(node.data.key)) {
            node = null;
            count--;
        }
        while (node.link != null) {
            if (key.equals(node.link.data.key)) {
                node.link = node.link.link;
                count--;
                return true;
            }
            node = node.link;
        }
        return false;
    }

    public Set<Entry<KEY, VALUE>> entrySet() {
        Set<Entry<KEY, VALUE>> result = new HashSet<>();
        for (SLNode<Entry<KEY, VALUE>> node : buckets) {
            while (node != null) {
                result.add(node.data);
                node = node.link;
            }
        }
        return result;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (SLNode<Entry<KEY, VALUE>> node : buckets) {
            sb.append(index++);
            sb.append(". ");
            if (node != null) {
                while (node != null) {
                    sb.append(node.data);
                    node = node.link;
                }
            } else
                sb.append("null");
            sb.append("\n");
        }
        return sb.toString();
    }
}
class Entry<KEY, VALUE> {
    public KEY key;
    public VALUE value;

    public Entry(KEY key, VALUE value) {
        this.key = key;
        this.value = value;
    }

    public String toString() {
        return String.format("<%s, %s>", key.toString(), value.toString());
    }

    public boolean equals(Entry<KEY, VALUE> other) {
        return this.key.equals(other.key);
    }

}

class SLNode<E> {
    public E data;
    public SLNode<E> link;

    public SLNode(E data, SLNode<E> link) {
        this.data = data;
        this.link = link;
    }
}