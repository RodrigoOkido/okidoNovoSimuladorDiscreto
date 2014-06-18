package simulator;

/**
 * Classe utilitaria que realiza c�lculos aritmeticos diversos. Esta classe
 * possuem m�todos que fazem c�lculos de m�dia, mediana e desvio padr�o, e diversos
 * outros tipos de c�lculos. 
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
	 * Atributo para atribuir o quadrado do n�mero atribuido no atributo 
	 * valor.
	 */
	private double somaValorQuadrado;
	
	
	/**
	 * Atributo para contar a quantidade de n�meros ja adicionados.
	 */
	private int contador;

	
	/**
	 * Atributo que define o tamanho de uma fila. Ser� usado caso seja
	 * desejado obter o tamanho m�ximo que uma fila obteve durante uma 
	 * simula��o.
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
	
	
	/**
	 * M�todo respons�vel por verificar o tamanho m�ximo obtido na fila 
	 * dentro da simula��o. Para garantir seu uso correto, deve ser usada 
	 * dentro de um la�o e ser atualizada sempre que o la�o finalizar e 
	 * recome�ar.
	 * 
	 * @param a1 Recebe o tamanho atual da fila por par�metro e vai 
	 * comparando o par�metro recebido com o atributo tamanho
	 * @return Retorna o tamanho m�ximo da fila
	 */
	public int tamanhoMaximoFila (int a1){		
		if (tamanho < a1){
			tamanho = a1;
		}
		return tamanho;
	}
	
	
	/**
	 * M�todo para contar o quanto tempo ficou sem gerar uma fila para o atendimento. O
	 * tempo � calculado em segundos.
	 * 
	 * @param tam recebe o tamanho da fila por par�metro e compara seu tamanho com 1
	 */
	public int tempoSemFila (int tam){
		if (tam == 0){
			contador++;
		}
		return contador;
	}
	
	
	/**
	 * M�todo para contabilizar a quantidade de atendimentos em que n�o foi necess�ria a
	 * espera do cliente, isto �, o atendimento ocorreu no momento em que ele chegou. �
	 * pedido dois par�metros, um para verificar se o caixa em que o cliente ser� atendido 
	 * est� vazio, e o outro para verificar se n�o havia fila no instante de chegada dele.
	 * 
	 * @param v Recebe um boolean para certificar se o caixa est� vazio ou n�o
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