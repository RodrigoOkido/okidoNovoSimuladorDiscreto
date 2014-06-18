package simulator;

/**
 * Classe utilitaria que realiza cálculos aritmeticos diversos. Esta classe
 * possuem métodos que fazem cálculos de média, mediana e desvio padrão, e diversos
 * outros tipos de cálculos. 
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
	 * Atributo para atribuir o quadrado do número atribuido no atributo 
	 * valor.
	 */
	private double somaValorQuadrado;
	
	
	/**
	 * Atributo para contar a quantidade de números ja adicionados.
	 */
	private int contador;

	
	/**
	 * Atributo que define o tamanho de uma fila. Será usado caso seja
	 * desejado obter o tamanho máximo que uma fila obteve durante uma 
	 * simulação.
	 */
	private int tamanho = 0;
	
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
	
	
	/**
	 * Método responsável por verificar o tamanho máximo obtido na fila 
	 * dentro da simulação. Para garantir seu uso correto, deve ser usada 
	 * dentro de um laço e ser atualizada sempre que o laço finalizar e 
	 * recomeçar.
	 * 
	 * @param a1 Recebe o tamanho atual da fila por parâmetro e vai 
	 * comparando o parâmetro recebido com o atributo tamanho
	 * @return Retorna o tamanho máximo da fila
	 */
	public int tamanhoMaximoFila (int a1){		
		if (tamanho < a1){
			tamanho = a1;
		}
		return tamanho;
	}
	
	
	/**
	 * Método para contar o quanto tempo ficou sem gerar uma fila para o atendimento. O
	 * tempo é calculado em segundos.
	 * 
	 * @param tam recebe o tamanho da fila por parâmetro e compara seu tamanho com 1
	 */
	public int tempoSemFila (int tam){
		if (tam == 0){
			contador++;
		}
		return contador;
	}
	
	
	/**
	 * Método para contabilizar a quantidade de atendimentos em que não foi necessária a
	 * espera do cliente, isto é, o atendimento ocorreu no momento em que ele chegou. É
	 * pedido dois parâmetros, um para verificar se o caixa em que o cliente será atendido 
	 * está vazio, e o outro para verificar se não havia fila no instante de chegada dele.
	 * 
	 * @param v Recebe um boolean para certificar se o caixa está vazio ou não
	 * @param tam Recebe um tamanho para verificar se existe a possibilidade do cliente
	 * ser atendido no momento em que chegou
	 * @return Retorna a quantidade de atendimentos realizados sem espera
	 */
	public int atendimentoSemEspera (boolean v, int tam){
		if (v == true && (tam == 0 || tam == 1)){
			contador++;
		}
		return contador;
	}

}