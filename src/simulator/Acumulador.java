package simulator;

/**
 * Classe utilitaria que realiza cálculos aritmeticos diversos. Esta classe
 * possuem métodos que fazem cálculos de média, mediana e desvio padrão. 
 * 
 *  @author Rodrigo Okido
 *  @version 1.0
 */
public class Acumulador 
{
	
	/**
	 * Atributo para atribuir um valor x no cálculo.
	 */
	private double valor;
	
	
	/**
	 * Atributo para atribuir o quadrado do do número atribuido no atributo 
	 * valor.
	 */
	private double somaValorQuadrado;
	
	
	/**
	 * Atributo para contar a quantidade de números ja adicionados.
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
	 * Retorna a quantidade de números ja adicionados em uma operação.
	 * 
	 * @return retorna a quantidade de números adicionados.
	 */
	public int getContagem() {
		return contador;
	}

	
	/**
	 * Eleva ao quadrado algum valor adicionado por parâmetro. Este
	 * método pode ser usado para cálculos de desvio padrão por exemplo,
	 * onde precisamos somar todos os números disponíveis ao quadrado.
	 * (verificar a fórmula descrita no próprio método contido nesta classe).
	 * 
	 * @param n recebe um inteiro n qualquer por parâmetro para realizar 
	 * o quadrado deste valor.
	 */
	public void adicionarQuadrado(int n) {
		somaValorQuadrado = somaValorQuadrado + Math.pow(n, 2);
	}

	
	/**
	 * Adiciona um inteiro n qualquer dentro de um "acumulador", o qual a cada vez 
	 * que este método é chamado, ele faz a soma de todos os números adicionados.
	 * Atualizando também o contador sempre que o método é chamado.
	 * 
	 * @param n recebe um inteiro n qualquer por parâmetro para adicionar dentro 
	 * do acumulador
	 */
	public void adicionar(int n) {
		valor = valor + n;
		contador++;
	}

	
	/**
	 * Realiza a média aritmética pegando a soma de todos os números adicionados
	 * dentro do acumulador divido pelo número de valores adicionados.
	 * 
	 * @return retorna o resultado da média, obtendo zero caso contador também for zero. 
	 */
	public double getMedia() {
		if (contador != 0)
			return valor / contador;
		else
			return 0;
	}

	
	/**
	 * Realiza o cálculo da mediana. Pega a quantidade de números incluídos no acumulador,
	 * soma este valor por um, e divide no final por dois.
	 * 
	 * @return retorna o resultado da mediana, obtendo zero caso contador também for zero. 
	 */
	public double getMediana() {
		if (contador != 0) {
			return (contador + 1) / 2;
		} else
			return 0;
	}

	
	/**
	 * Realiza o cálculo do desvio padrão. O desvio padrão para ser calculado, devemos 
	 * calcular antes a variancia, composta pela soma ao quadrado de todos os números 
	 * adicionados dentro do acumulador menos a multiplicação do número de valores 
	 * adicionados e o resultado da média ao quadrado, para enfim, dividir o resultado
	 * pelo número de valores adicionados menos um. Após realizado o cálculo da variancia,
	 * o desvio padrão é obtido fazendo a raíz quadrada da variancia.
	 *
	 * @return retorna o resultado do desvio padrão, obtendo zero caso contador for zero 
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