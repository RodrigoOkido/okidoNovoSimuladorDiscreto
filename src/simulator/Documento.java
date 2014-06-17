package simulator;

import java.util.Random;

/**
 * A classe Documento possui a possibilidade de criar informações necessárias 
 * que são exigidas para um cliente x qualquer. Neste caso, a classe pode criar 
 * um CPF e um RG para qualquer pessoa.
 * 
 * @author Rodrigo Okido
 * @version 1.0
 */
public class Documento 
{

     /**
      * Atributo do tipo String para criação de um CPF. 	
      */
	private String CPF;
	
	
	/**
	 * Atributo do tipo String para criação de um RG.
	 */
	private String RG;
	
	
	/**
	 * Gerador de números aleatórios utilizando a classe Random.
	 */
	private static Random numbersGenerator = new Random();
	
	
	/**
	 * Construtor para instanciar um Documento. O RG é feito a partir de um
	 * laço onde vai de 0 a 10, onde a cada vez que o laço é executado ele gera
	 * números aleatórios de 0 a 9. Tendo no final, um longo String de números de
	 * nove dígitos. E o CPF é feito da mesma forma que o RG, a única diferença é 
	 * o fato de um CPF possuir 11 números, e não 10 como em um RG.
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
	 * Método responsável por retornar o CPF
	 * 
	 * @return retorna o CPF
	 */
	public String getCPF (){
		return CPF;
	}
	
	
	/**
	 * Método responsáel por retornar o RG
	 * 
	 * @return retorna o RG
	 */
	public String RG (){
		return RG;
	}
}
