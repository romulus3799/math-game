
public class Equation {
	private int coeff;
	private String var;
	private int addend;
	private int result;
	
	public Equation(int c, String v, int a, int r){
		coeff = c;
		var = v;
		addend = a;
		result = r;
	}
	
	public int getCoeff(){return coeff;}
	public String getVar(){return var;}
	public int getAddend(){return addend;}
	public int getResult(){return result;}
	
	public void setCoeff(int i){coeff = i;}
	public void setVar(String i){var = i;}
	public void setAddend(int i){addend = i;}
	public void setResult(int i){result = i;}
	
	public int solve(){
		return (result - addend)/coeff;
	}
	
	public String toString(){
		if(addend >= 0){
			if(coeff == 1)
				return var + " + " + addend + " = " + result;
			return coeff + "" + var + " + " + addend + " = " + result;
		}
		if(coeff == 1)
			return var + " - " + -1*addend + " = " + result;
		return coeff + "" + var + " - " + -1*addend + " = " + result;
	}
}

/*
 * 3x + 5 = 17
 * 3x = 12
 * x = 4
 * x = (17 - 5)/3
 * var = (result - addend)/coeff;
 * */