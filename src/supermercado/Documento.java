package supermercado;

import java.util.Random;

public class Documento {

	private String CPF;
	private String RG;
	private static Random numbersGenerator = new Random();
	
	public Documento () {
		
		StringBuilder sb1 = new StringBuilder();
		for (int i = 0 ; i < 10 ; i++){
			sb1.append(numbersGenerator.nextInt(9));
		}
		RG = sb1.toString();
		
		StringBuilder sb2 = new StringBuilder();
		for (int i = 0 ; i < 11 ; i++){
			sb2.append(numbersGenerator.nextInt(9));
		}
		CPF = sb2.toString();
		
	}
	
	public String getCPF (){
		return CPF;
	}
	
	public String RG (){
		return RG;
	}
	
}
