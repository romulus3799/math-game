
public class Fraction {
	public int num;
	public int denom;
	
	public Fraction(int n, int d){
		num = n;
		denom = d;
	}
	
	public Fraction(String s){
		num = Integer.parseInt(s.substring(0, s.indexOf("/")));
		denom = Integer.parseInt(s.substring(s.indexOf("/")+1));
	}
	
	
	public int gcd(int n1, int n2){
		return (n1*n2)/gcf(n1,n2);
	}
	
	public int gcf(int n1, int n2){
		int gcf = 1;
		for(int i = 1; i <= min(n1,n2); i++)
			if(n1%i==0 && n2%i==0)
				gcf = i;
		return gcf;
	}
	
	private int min(int n1, int n2){
		if(n1>n2)return n2;
		else return n1;
	}
	
	public Fraction add(Fraction frac){
		return new Fraction(num*(gcd(denom,frac.denom)/denom) 
							+ frac.num*(gcd(denom,frac.denom)/frac.denom)
							,gcd(denom,frac.denom));
	}
	
	public Fraction subtract(Fraction frac){
		return new Fraction(num*(gcd(denom,frac.denom)/denom) 
							- frac.num*(gcd(denom,frac.denom)/frac.denom)
							,gcd(denom,frac.denom));
	}
	
	public Fraction multiply(Fraction frac){
		return new Fraction(num*frac.num
				,denom*frac.denom);
	}
	
	public Fraction divide(Fraction frac){
		return new Fraction(num*frac.denom
				,denom*frac.num);
	}
	
	public Fraction simplify(){
		return new Fraction(num/gcf(num,denom),denom/gcf(num,denom));
	}
	
	public String toString(){
		return num + "/" + denom;
	}
	
	public double toDouble(){
		return (double)(num)/denom;
	}
}