import dataStructures.SinglyLinkedList;

class Monomial implements Comparable<Monomial> {
	protected int coef;
	protected int exp;
	
	public Monomial(int coef, int exp) {
		this.coef = coef;
		this.exp = exp;
	}
	
	@Override 
	public int compareTo(Monomial other) {
		return exp < other.exp ? -1 : exp > other.exp ? 1 : 0;
	}
	
	@Override 
	public String toString() {
		if (exp == 0)
			return String.format("%d", coef);
		if (exp == 1) {
			if (coef == 1)
				return String.format("x");
			return String.format("%dx", coef);
		}
		else {
			if (coef == 1)
				return String.format("x^%d", exp);
			return String.format("%dx^%d", coef, exp);
		}
	}
}

class Polynomial extends SinglyLinkedList<Monomial> {
	public Polynomial() {}
	
	public Polynomial(Polynomial p) {
		if (p != null && !p.isEmpty())
			for (Monomial m : p)
				this.append(m);
	}
	
	public Polynomial(String p) {
		// TODO make it work for x^16 example
		String[] monomials = p.split("\\s*\\+\\s*");
		for (String m : monomials) {
			if (m.length() == 4)
				append(new Monomial(Character.getNumericValue(m.charAt(0)), 
						Character.getNumericValue(m.charAt(3))));
			else if (m.length() == 3)
				append(new Monomial(1, 
						Character.getNumericValue(m.charAt(2))));
			else if (m.length() == 2)
				append(new Monomial(Character.getNumericValue(m.charAt(0)), 
						1));
			else if (m.length() == 1) {
				if (m.charAt(0) == 'x' || m.charAt(0) == 'X')
					append(new Monomial(1, 1));
				else 
					append(new Monomial(Character.getNumericValue(m.charAt(0)),0));
			}	
		}
	}
	
	@Override 
	public String toString() {
		StringBuilder res = new StringBuilder();
		for (Monomial m : this) {
			res.append(m);
			res.append(" + ");
		}
		res.delete(res.length() - 3, res.length());
		return res.toString();
	}
}


public class AddingPolynomials {
	
	public static Polynomial addPolynomials(Polynomial p1, Polynomial p2) {
		Polynomial result = new Polynomial();
		int i = 0, j = 0;
		Monomial m1 = null, m2 = null;
		
		while (i < p1.size() && j < p2.size()) {
			m1 = p1.get(i);
			m2 = p2.get(j);
			
			if (m1.exp < m2.exp) {
				result.append(m2);
				j++;
			} else if (m1.exp > m2.exp) {
				result.append(m1);
				i++;
			} else {
				result.append(new Monomial(m1.coef + m2.coef, m1.exp));
				i++; j++;
			}
		}
		
		if (i == p1.size()) {
			while (j < p2.size()) {
				m2 = p2.get(j);
				result.append(m2);
				j++;
			}
		}
		
		if (j == p2.size()) {
			while (i < p1.size()) {
				m1 = p1.get(i);
				result.append(m1);
				i++;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		Polynomial p1 = new Polynomial("3x^3+6x+2");
		Polynomial p2 = new Polynomial("2x^5+x^4+2x^3+3x^2+5");
//		Polynomial p3 = new Polynomial("x^16+1");
		Polynomial res = addPolynomials(p1, p2);
//		Polynomial res = addPolynomials(p1, p3); does not work correctly
		System.out.println(res);			
	}
}
