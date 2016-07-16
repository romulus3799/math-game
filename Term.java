
public class Term {
	public int coeff;
	public String base;
	public int exp;
	
	public Term(String s, int num){
		coeff = 1;
		base = s;
		exp = num;
	}
	
	public Term(int n, String s, int num){
		coeff = n;
		base = s;
		exp = num;
	}

	public Term(String s){
		coeff = 1;
		base = s;
		exp = 1;
	}
	

	public Term(int num){
		coeff = num;
		base = "null";
		exp = 1;
	}
	
	public Term add(Term t){
		if(base == t.base && exp == t.exp)
			return new Term(coeff+t.coeff,base,exp);
		return null;
	}
	
	public Term subtract(Term t){
		if(base == t.base && exp == t.exp)
			return new Term(coeff-t.coeff,base,exp);
		return null;
	}
	
	public Term multiply(Term t){
		if(base == t.base)
			return new Term(coeff*t.coeff,base,exp+t.exp);
		return null;
	}
	
	public Term divide(Term t){
		if(base == t.base)
			return new Term(coeff/t.coeff,base,exp-t.exp);
		return null;
	}
	
	public String toString(){
		if(coeff == 1){
			if(exp == 1)
				return base;
			return base + "^" + exp;
		}
		if(exp == 1)
			return coeff + base;
		if(coeff == 0)
			return "0";
		if(exp == 0)
			return coeff + "";
		//System.out.println("Term name: " + coeff + base + "^" + exp);
		return coeff + base + "^" + exp;
	}
}