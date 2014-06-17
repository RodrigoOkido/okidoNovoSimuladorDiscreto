package simulator;

/**
 * Classe utilitaria que realiza c�lculos aritmeticos diversos. Esta classe
 * possuem m�todos que fazem c�lculos de m�dia, mediana e desvio padr�o. 
 * 
 *  @author Rodrigo Okido
 *  @version 1.0
 */
public class Acumulador 
{
	
	/**
	 * Atributo para atribuir um valor x no c�lculo.
	 */
	private double valor;
	
	
	/**
	 * Atributo para atribuir o quadrado do do n�mero atribuido no atributo 
	 * valor.
	 */
	private double somaValorQuadrado;
	
	
	/**
	 * Atributo para contar a quantidade de n�meros ja adicionados.
	 */
	private int contador;

	
	/**
	 * Construtor para instanciar um Acumulador. 
	 */
	public Acumulador() {
		valor = 0;
		somaValorQuadrado = 0;
		contador = 0;
	}

	
	/**
	 * Retorna o numero armazenado no atributo valor.
	 * 
	 * @return retorna o numero armazenado no atributo valor.
	 */
	public double getValor() {
		return (valor);
	}

	
	/**
	 * Retorna a quantidade de n�meros ja adicionados em uma opera��o.
	 * 
	 * @return retorna a quantidade de n�meros adicionados.
	 */
	public int getContagem() {
		return contador;
	}

	
	/**
	 * Eleva ao quadrado algum valor adicionado por par�metro. Este
	 * m�todo pode ser usado para c�lculos de desvio padr�o por exemplo,
	 * onde precisamos somar todos os n�meros dispon�veis ao quadrado.
	 * (verificar a f�rmula descrita no pr�prio m�todo contido nesta classe).
	 * 
	 * @param n recebe um inteiro n qualquer por par�metro para realizar 
	 * o quadrado deste valor.
	 */
	public void adicionarQuadrado(int n) {
		somaValorQuadrado = somaValorQuadrado + Math.pow(n, 2);
	}

	
	/**
	 * Adiciona um inteiro n qualquer dentro de um "acumulador", o qual a cada vez 
	 * que este m�todo � chamado, ele faz a soma de todos os n�meros adicionados.
	 * Atualizando tamb�m o contador sempre que o m�todo � chamado.
	 * 
	 * @param n recebe um inteiro n qualquer por par�metro para adicionar dentro 
	 * do acumulador
	 */
	public void adicionar(int n) {
		valor = valor + n;
		contador++;
	}

	
	/**
	 * Realiza a m�dia aritm�tica pegando a soma de todos os n�meros adicionados
	 * dentro do acumulador divido pelo n�mero de valores adicionados.
	 * 
	 * @return retorna o resultado da m�dia, obtendo zero caso contador tamb�m for zero. 
	 */
	public double getMedia() {
		if (contador != 0)
			return valor / contador;
		else
			return 0;
	}

	
	/**
	 * Realiza o c�lculo da mediana. Pega a quantidade de n�meros inclu�dos no acumulador,
	 * soma este valor por um, e divide no final por dois.
	 * 
	 * @return retorna o resultado da mediana, obtendo zero caso contador tamb�m for zero. 
	 */
	public double getMediana() {
		if (contador != 0) {
			return (contador + 1) / 2;
		} else
			return 0;
	}

	
	/**
	 * Realiza o c�lculo do desvio padr�o. O desvio padr�o para ser calculado, devemos 
	 * calcular antes a variancia, composta pela soma ao quadrado de todos os n�meros 
	 * adicionados dentro do acumulador menos a multiplica��o do n�mero de valores 
	 * adicionados e o resultado da m�dia ao quadrado, para enfim, dividir o resultado
	 * pelo n�mero de valores adicionados menos um. Ap�s realizado o c�lculo da variancia,
	 * o desvio padr�o � obtido fazendo a ra�z quadrada da variancia.
	 *
	 * @return retorna o resultado do desvio padr�o, obtendo zero caso contador for zero 
	 * ou um. 
	 */
	public double getDesvioPadrao() {
		if (contador != 0 && contador > 1){
			
		double variancia = somaValorQuadrado
				- (contador * (Math.pow(getMedia(), 2))) / contador - 1;
		double desvioPadrao = Math.sqrt(variancia);
			
		return desvioPadrao;
		}
		else
			return 0;
	
}
}