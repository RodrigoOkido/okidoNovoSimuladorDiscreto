package simulator;

import java.util.Random;

/**
 * Esta classe indica se um cliente ser� gerado de acordo com a probabilidade indicada
 * no construtor.
 * 
 * @author Rodrigo Okido
 * @version 1.0
 */
public class GeradorClientes
{
	
	/**
	 * Atributo do tipo double para definir uma probabilidade de cria��o de um
	 * cliente.
	 */
    private double probabilidade;
    
    
    /**
     * Atributo para contabilizar a quantidade de clientes gerados.
     */
    private int quantidadeGerada;
    
    
    /**
	 * Gerador de n�meros aleat�rios utilizando a classe Random.
	 */
    private static final Random gerador = new Random(); //gerador de numeros aleatorios de Java
    
    
    /**
     * Construtor para instanciar um objeto da classe GeradorClientes
     * 
     * @param p recebe por par�metro um double qualquer, o qual ser� a atribuido ao
     * atributo "probabilidade"
     */
    public GeradorClientes(double p)
    {
        probabilidade = p;
        quantidadeGerada = 0;
    }
    
    
    /**
     * M�todo respons�vel pela gera��o de um Cliente, se o gerador de 
     * n�meros aleat�rios for menor que a probabilidade, ent�o o cliente ser�
     * gerado. Adicionando assim, mais um para o atributo "quantidadeGerada" e 
     * alterando o atributo boolean "gerado" para "true".
     * 
     * @return retorna "true" caso a houver a gera��o do cliente, e "false" caso
     * contr�rio
     */
    public boolean gerar()
    {
        boolean gerado = false;
        if(gerador.nextDouble() < probabilidade)
        {
            quantidadeGerada++;
            gerado = true;
        }
        return gerado;
    }
    
    
    /**
     * Retorna a quantidade de clientes gerada.
     * 
     * @return retorna a quantidade de clientes gerada
     */
    public int getQuantidadeGerada()
    {
        return quantidadeGerada;
    }
}
