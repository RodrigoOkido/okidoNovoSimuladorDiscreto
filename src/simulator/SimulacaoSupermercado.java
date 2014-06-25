package simulator;

import ProgramInterfaces.QueueTAD;
import ProgramInterfaces.SimuladorInterface;
import ProgramListTADs.QueueLinked;

/**
 * Classe com a l�gica da simulacao passo-a-passo. Esta classe gera uma simula��o
 * de um supermercado (simples), possui diversos atributos necess�rios para que se ocorra  
 * a simula��o. Inclui um caixa, uma fila, gerador de clientes e diversas vari�veis da 
 * classe Acumulador para executar diversos tipos de c�lculos diferentes para exibir
 * no relat�rio final os resultados da simula��o (desvio padr�o, m�dia, mediana, entre
 * outros). 
 * 
 * @author Rodrigo Okido
 * @version 1.0
 */
public class SimulacaoSupermercado implements SimuladorInterface
{
	
	/**
	 * Atributo que define o tempo de dura��o de uma simula��o. 
	 */
	private static int duracao = 0;
	
	
	/**
	 * Atributo que define a probabilidade de chegada dos clientes
	 * dentro da simula��o.
	 */
    private static final double probabilidadeChegada = 0.1;
    
    
    /**
	 * Define a fila de clientes.
	 */
    private QueueTAD<Cliente> fila;
    
    
	/**
	 * Atributo para a cria��o de um caixa.
	 */
    private Caixa caixa;
    
    
    /**
	 * Atributo para gera��o de clientes.
	 */
    private GeradorClientes geradorClientes;
    
    
	/**
	 * Atributo que ser� utilizado para c�lculos aritm�ticos.
	 * Neste caso, este acumulador calcular� dados estat�sticos em rela��o
	 * ao tempo de espera na fila.
	 */
    private Acumulador statTemposEsperaFila;
    
    
	/**
	 * Atributo que ser� utilizado para c�lculos aritm�ticos.
	 * Neste caso, este acumulador calcular� dados estat�sticos em rela��o
	 * ao comprimento da fila.
	 */
    private Acumulador statComprimentosFila;
    
    
    /**
	 * Atributo que ser� utilizado para c�lculos aritm�ticos.
	 * Neste caso, este acumulador calcular� dados estat�sticos em rela��o
	 * ao tempo de atendimento no caixa.
	 */
    private Acumulador statTempoAtendimentoCaixa;
    
    
    /**
	 * Atributo para verificar o tempo total em que a fila n�o possuia nenhum
	 * cliente. Ou seja, o tempo total em que n�o existiu fila, para ser atendido no
	 * caixa.
	 */
    private Acumulador statTempoFilaVazia;
    
    
    /**
	 * Verifica a quantidade de atendimentos em que n�o houve espera do cliente para
	 * ser atendido no caixa.
	 */
    private Acumulador statAtendimentoSemEspera;
    
    
	/**
	 * Atributo que indica o passo-a-passo da simula��o. 
	 */
    private boolean trace; //valor indica se a simulacao ira imprimir passo-a-passo os resultados
    
    
    /**
     * Construtor para instanciar uma simula��o de um supermercado.
	 * Instancia todos os atributos tendo apenas que definir por par�metro
	 * o atributo "trace". 
	 * 
     * @param t Recebe por par�metro "true" para que a simula��o possa ser
	 * executada passo-a-passo.
     */
    public SimulacaoSupermercado(boolean t)
    {
        fila = new QueueLinked<Cliente>();
        caixa = new Caixa();
        geradorClientes = new GeradorClientes(probabilidadeChegada);
        statTemposEsperaFila = new Acumulador();
        statComprimentosFila = new Acumulador();
    	statTempoAtendimentoCaixa = new Acumulador();
    	statTempoFilaVazia = new Acumulador();
		statAtendimentoSemEspera = new Acumulador();
        trace = t;
    }
    
    
    /**
     * M�todo para definir o tempo de dura��o da simula��o.
     * 
     * @param x recebe por par�metro um inteiro x para definir o tempo
     * em que ocorrer� a simula��o 
     */
	private static void setDuracao(int x) {
		 duracao = x;
	}
    
	
	/**
	 * M�todo chave e principal para a execu��o do programa. � nela que toda a 
	 * simula��o ocorre. Para ela ocorrer, devem ser definidos quatro par�metros 
	 * do tipo inteiro, para assim, ela ser gerada e apresentar os seus resultados.
	 * 
	 * @param min recebe um inteiro onde ser� definido o tempo de atendimento m�nimo dos
	 * clientes
	 * @param max recebe um inteiro onde ser� definido o tempo de atendimento m�ximo dos
	 * clientes
	 * @param qwt recebe um inteiro onde ser� definido o tempo de espera na fila 
	 * @param duration recebe um inteiro onde ser� definido o tempo de dura��o em que a
	 * simula��o ir� ocorrer
	 */
    public void simular(int min, int max, int qwt, int duration)
    {
    	
    	setDuracao(duration);
		Cliente.setMinMaxTimeFila1(min,max);
		statTemposEsperaFila.adicionar(qwt);
		
        //realizar a simulacao por um certo numero de passos de duracao
        for(int tempo=0; tempo<duracao; tempo++)
        {
            //verificar se um cliente chegou
            if(geradorClientes.gerar())
            {
                //se cliente chegou, criar um cliente e inserir na fila do caixa
                Cliente c = new Cliente(geradorClientes.getQuantidadeGerada(),tempo);
                fila.add(c);
                if(trace)
                    System.out.println(tempo + ": cliente " + c.getNumero() + " ("+c.getTempoAtendimento()+" min) entra na fila - " + fila.size() + " pessoa(s)");
            }
            statComprimentosFila.tamanhoMaximoFila(fila.size());

            //verificar se o caixa esta vazio
            if(caixa.estaVazio())
            {
                //se o caixa esta vazio, atender o primeiro cliente da fila se ele existir
                if(!fila.isEmpty())
                {
                    //tirar o cliente do inicio da fila e atender no caixa
                	statAtendimentoSemEspera.atendimentoSemEspera(caixa.estaVazio(), fila.size());
                    caixa.atenderNovoCliente(fila.removeFromHead());
                    statTemposEsperaFila.adicionar(tempo - caixa.getClienteAtual().getInstanteChegada());
            		statTemposEsperaFila.adicionarQuadrado(tempo
							- caixa.getClienteAtual().getInstanteChegada());
                    if(trace)
                    	statTempoAtendimentoCaixa.adicionar(caixa
								.getClienteAtual().getTempoAtendimento());
                	statTempoAtendimentoCaixa.adicionarQuadrado(caixa
							.getClienteAtual().getTempoAtendimento());
                        System.out.println(tempo + ": cliente " + caixa.getClienteAtual().getNumero() + " chega ao caixa.");
                }
            }
            else
            {
                //se o caixa ja esta ocupado, diminuir de um em um o tempo de atendimento ate chegar a zero
                if(caixa.getClienteAtual().getTempoAtendimento() == 0)
                {
                    if(trace)
                        System.out.println(tempo + ": cliente " + caixa.getClienteAtual().getNumero() + " deixa o caixa.");
                    caixa.dispensarClienteAtual();
                }
                else
                {
                    caixa.getClienteAtual().decrementarTempoAtendimento();
                }
            }
            statTempoFilaVazia.tempoSemFila(fila.size());
            statComprimentosFila.adicionar(fila.size());
        }
    }
    
    
    /**
     * M�todo respons�vel por limpar toda a simula��o corrente e gerar uma nova
     * simula��o.
     */
    public void limpar()
    {
        fila = new QueueLinked<Cliente>();
        caixa = new Caixa();
        geradorClientes = new GeradorClientes(probabilidadeChegada);
        statTemposEsperaFila = new Acumulador();
        statComprimentosFila = new Acumulador();
    	statTempoAtendimentoCaixa = new Acumulador();
    	statTempoFilaVazia = new Acumulador();
		statAtendimentoSemEspera = new Acumulador();
    }
    
    
	/**
	 * Imprime os resultados da simula��o gerada. Gera um relat�rio completo
	 * contendo todas as informa��es b�sicas, estat�sticas e estat�stica 
	 * avan�adas da simula��o. 
	 */
    public String imprimirResultados()
    {
        String x = "##### Resultados da Simula��o do Supermercado #####"
        +"\n-----------------------------------------------------------------------------------------"
		+"\n******* Informa��es B�sicas: *******"
        +"\nDura��o:" + duracao
        +"\nProbabilidade de chegada de clientes:" + probabilidadeChegada

        +"\nTotal de clientes gerados:" + geradorClientes.getQuantidadeGerada()

        
        +"\n\n******* Estat�sticas Fila 1 *******"
        +"\nTempo m�dio de espera:" + statTemposEsperaFila.getMedia()
		+"\nTamanho m�ximo da fila : " +	
				statComprimentosFila.tamanhoMaximoFila(fila.size())
       +"\nComprimento m�dio da fila :" + statComprimentosFila.getMedia()
      +"\nAtendimentos que ocorreram sem espera: " 
				+ statAtendimentoSemEspera.atendimentoSemEspera(caixa.estaVazio(), fila.size())
		+"\nTempo total em que a fila 1 ficou vazia: "
				+ statTempoFilaVazia.getContagem() + " segundos"
      +"\nClientes ainda na fila 1 : " + fila.size()
        
        
		+"\n\n******* Estat�sticas Caixa 1 *******"
        +"\nTempo de atendimento m�nimo:" + Cliente.tempoMinAtendimento
        +"\nTempo de atendimento m�ximo:" + Cliente.tempoMaxAtendimento
		+"\nTempo m�dio de atendimento no caixa 1 : "
				+ statTempoAtendimentoCaixa.getMedia()
        +"\nCliente atendidos:" + caixa.getNumeroAtendidos()
        +"\nCliente ainda no caixa:" + (caixa.getClienteAtual() != null);
        return x;
    }
    
    
        public String imprimirEstatisticasAvancadas() {	
        	
        String x = "##### Estat�sticas avan�adas do Supermercado #####"
        +"\n-----------------------------------------------------------------------------------------" 
        +"\n******* Estat�sticas Avan�adas *******"
		+"\nTamanho m�nimo obtido na fila 1 : 1"
		+"\nTamanho m�ximo obtido na fila 1 : "+statComprimentosFila.tamanhoMaximoFila(fila.size())
		+"\nMediana da fila 1 : "+ statTemposEsperaFila.getMediana(1,statComprimentosFila.tamanhoMaximoFila(fila.size()))
		
		+"\n\nAtendimento m�nimo ocorrido no caixa 1 : 1"
		+"\nAtendimento m�ximo ocorrido no caixa 1 : "+caixa.getNumeroAtendidos()
		+"\nMediana do atendimento no caixa 1 : "+ statTempoAtendimentoCaixa.getMediana(1,caixa.getNumeroAtendidos())
	   
		+"\n\nDesvio Padr�o da fila 1 : "+ statTemposEsperaFila.getDesvioPadrao()
		+"\nDesvio Padr�o de atendimento no caixa 1 : "+ statTempoAtendimentoCaixa.getDesvioPadrao()

	    +"\n-----------------------------------------------------------------------------------------";
    
    return x;
    
    }
}
