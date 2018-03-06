import dataStructures.ClosedBucketHashTable;
import unit.Entry;

class ChemicalElement implements Comparable<ChemicalElement> {

    // A ChemicalElement object represents a chemical element.
    
    // Each element contains the two characters of the chemical symbol.
    // The first character must be an uppercase letter. Where present,
    // the second character must be a lowercase letter. If absent, the
    // second character is a space.
    
    private char sym1, sym2; // The two letters of the chemical symbol.

    
    public ChemicalElement (String symbol) {
        if (symbol.length() >= 1)
            sym1 = Character.toUpperCase(symbol.charAt(0));
        else
            sym1 = ' '; // Should really fail.
        if (symbol.length() >= 2)
            sym2 = Character.toLowerCase(symbol.charAt(1));
        else
            sym2 = ' ';
    }
    
    
    public int hashCode () {
        return sym1 - 'A';
    }


    public int stepCode () {
    	return (sym2 == ' ') ? 1 : sym2 - 'a' + 2;
    }

    
    public String toString () {
        return "" + sym1 + sym2;
    }

    
    public int compareTo (ChemicalElement that) {
        return (this.sym1 < that.sym1) ? -1 :
               (this.sym1 > that.sym1) ? 1  :
               (this.sym2 < that.sym2) ? -1 :
               (this.sym2 > that.sym2) ? 1  :
               0;
    }

	@Override
	public boolean equals (Object that) {
		ChemicalElement other = (ChemicalElement)that;
    	if (other == null || ! (other instanceof ChemicalElement))
    	    return false;
    	else{
    		return this.compareTo(other) == 0;
    	}
    }
}

public class Test {

	public static void main(String[] args) {
		ClosedBucketHashTable<ChemicalElement,Integer> table1 = new ClosedBucketHashTable<>(26);
        table1.insert(new ChemicalElement("F"),  new Integer(9));
        table1.insert(new ChemicalElement("Ne"), new Integer(10));
        table1.insert(new ChemicalElement("Cl"), new Integer(17));
        table1.insert(new ChemicalElement("Ar"), new Integer(18));
        table1.insert(new ChemicalElement("Br"), new Integer(35));
        table1.insert(new ChemicalElement("Kr"), new Integer(36));
        table1.insert(new ChemicalElement("I"),  new Integer(53));
        table1.insert(new ChemicalElement("Xe"), new Integer(54));

        System.out.println ("Tabelata od slajd 5");
        System.out.println(table1);
//        for (Entry<ChemicalElement, Integer> entry : table1.entrySet()) {
//            System.out.println(entry);
//        }

        ClosedBucketHashTable<ChemicalElement,Integer> table2 = new ClosedBucketHashTable<>(26);
        table2.insert(new ChemicalElement("H"),  new Integer(1));
        table2.insert(new ChemicalElement("He"), new Integer(2));
        table2.insert(new ChemicalElement("Li"), new Integer(3));
        table2.insert(new ChemicalElement("Be"), new Integer(4));
        table2.insert(new ChemicalElement("Na"), new Integer(11));
        table2.insert(new ChemicalElement("Mg"), new Integer(12));
        table2.insert(new ChemicalElement("K"),  new Integer(19));
        table2.insert(new ChemicalElement("Ca"), new Integer(20));
        table2.insert(new ChemicalElement("Rb"), new Integer(37));
        table2.insert(new ChemicalElement("Sr"), new Integer(38));
        table2.insert(new ChemicalElement("Cs"), new Integer(55));
        table2.insert(new ChemicalElement("Ba"), new Integer(56));

        System.out.println ("Tabelata od slajd 6");
        System.out.println(table2);
	}

}
