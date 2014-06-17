package simulator;

import java.util.Random;

/**
 * A classe Documento possui a possibilidade de criar informa��es necess�rias 
 * que s�o exigidas para um cliente x qualquer. Neste caso, a classe pode criar 
 * um CPF e um RG para qualquer pessoa.
 * 
 * @author Rodrigo Okido
 * @version 1.0
 */
public class Documento 
{

     /**
      * Atributo do tipo String para cria��o de um CPF. 	
      */
	private String CPF;
	
	
	/**
	 * Atributo do tipo String para cria��o de um RG.
	 */
	private String RG;
	
	
	/**
	 * Gerador de n�meros aleat�rios utilizando a classe Random.
	 */
	private static Random numbersGenerator = new Random();
	
	
	/**
	 * Construtor para instanciar um Documento. O RG � feito a partir de um
	 * la�o onde vai de 0 a 10, onde a cada vez que o la�o � executado ele gera
	 * n�meros aleat�rios de 0 a 9. Tendo no final, um longo String de n�meros de
	 * nove d�gitos. E o CPF � feito da mesma forma que o RG, a �nica diferen�a � 
	 * o fato de um CPF possuir 11 n�meros, e n�o 10 como em um RG.
	 */
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
	
	
	/**
	 * M�todo respons�vel por retornar o CPF
	 * 
	 * @return retorna o CPF
	 */
	public String getCPF (){
		return CPF;
	}
	
	
	/**
	 * M�todo respons�el por retornar o RG
	 * 
	 * @return retorna o RG
	 */
	public String RG (){
		return RG;
	}
}
