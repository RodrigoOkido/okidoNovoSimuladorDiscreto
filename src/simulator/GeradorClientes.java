package simulator;

import java.util.Random;

/**
 * Esta classe indica se um cliente será gerado de acordo com a probabilidade indicada
 * no construtor.
 * 
 * @author Rodrigo Okido
 * @version 1.0
 */
public class GeradorClientes
{
	
	/**
	 * Atributo do tipo double para definir uma probabilidade de criação de um
	 * cliente.
	 */
    private double probabilidade;
    
    
    /**
     * Atributo para contabilizar a quantidade de clientes gerados.
     */
    private int quantidadeGerada;
    
    
    /**
	 * Gerador de números aleatórios utilizando a classe Random.
	 */
    private static final Random gerador = new Random(); //gerador de numeros aleatorios de Java
    
    
    /**
     * Construtor para instanciar um objeto da classe GeradorClientes
     * 
     * @param p recebe por parâmetro um double qualquer, o qual será a atribuido ao
     * atributo "probabilidade"
     */
    public GeradorClientes(double p)
    {
        probabilidade = p;
        quantidadeGerada = 0;
    }
    
    
    /**
     * Método responsável pela geração de um Cliente, se o gerador de 
     * números aleatórios for menor que a probabilidade, então o cliente será
     * gerado. Adicionando assim, mais um para o atributo "quantidadeGerada" e 
     * alterando o atributo boolean "gerado" para "true".
     * 
     * @return retorna "true" caso a houver a geração do cliente, e "false" caso
     * contrário
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
