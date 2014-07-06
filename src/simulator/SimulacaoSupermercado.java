package simulator;

import java.awt.Color;

import ProgramInterfaces.QueueTAD;
import ProgramInterfaces.SimuladorInterface;
import ProgramListTADs.QueueLinked;
import ProgramWindowsInterface.SimulatorWindows;

/**
 * Classe com a lógica da simulacao passo-a-passo. Esta classe gera uma simulação
 * de um supermercado (simples), possui diversos atributos necessários para que se ocorra  
 * a simulação. Inclui um caixa, uma fila, gerador de clientes e diversas variáveis da 
 * classe Acumulador para executar diversos tipos de cálculos diferentes para exibir
 * no relatório final os resultados da simulação (desvio padrão, média, mediana, entre
 * outros). 
 * 
 * @author Rodrigo Okido
 * @version 1.0
 */
public class SimulacaoSupermercado implements SimuladorInterface
{
	
	/**
	 * Atributo que define o tempo de duração de uma simulação. 
	 */
	private static int duracao = 0;
	
	
	/**
	 * Atributo que define a probabilidade de chegada dos clientes
	 * dentro da simulação.
	 */
    private static final double probabilidadeChegada = 0.1;
    
    
    /**
	 * Define a fila de clientes.
	 */
    private QueueTAD<Cliente> fila;
    
    
	/**
	 * Atributo para a criação de um caixa.
	 */
    private Caixa caixa;
    
    
    /**
	 * Atributo para geração de clientes.
	 */
    private GeradorClientes geradorClientes;
    
    
	/**
	 * Atributo que será utilizado para cálculos aritméticos.
	 * Neste caso, este acumulador calculará dados estatísticos em relação
	 * ao tempo de espera na fila.
	 */
    private Acumulador statTemposEsperaFila;
    
    
	/**
	 * Atributo que será utilizado para cálculos aritméticos.
	 * Neste caso, este acumulador calculará dados estatísticos em relação
	 * ao comprimento da fila.
	 */
    private Acumulador statComprimentosFila;
    
    
    /**
	 * Atributo que será utilizado para cálculos aritméticos.
	 * Neste caso, este acumulador calculará dados estatísticos em relação
	 * ao tempo de atendimento no caixa.
	 */
    private Acumulador statTempoAtendimentoCaixa;
    
    
    /**
	 * Atributo para verificar o tempo total em que a fila não possuia nenhum
	 * cliente. Ou seja, o tempo total em que não existiu fila, para ser atendido no
	 * caixa.
	 */
    private Acumulador statTempoFilaVazia;
    
    
    /**
	 * Verifica a quantidade de atendimentos em que não houve espera do cliente para
	 * ser atendido no caixa.
	 */
    private Acumulador statAtendimentoSemEspera;
    
    
	/**
	 * Atributo que indica o passo-a-passo da simulação. 
	 */
    private boolean trace; //valor indica se a simulacao ira imprimir passo-a-passo os resultados
    
    
    /**
     * Construtor para instanciar uma simulação de um supermercado.
	 * Instancia todos os atributos tendo apenas que definir por parâmetro
	 * o atributo "trace". 
	 * 
     * @param t Recebe por parâmetro "true" para que a simulação possa ser
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
     * Método para definir o tempo de duração da simulação.
     * 
     * @param x recebe por parâmetro um inteiro x para definir o tempo
     * em que ocorrerá a simulação 
     */
	private static void setDuracao(int x) {
		 duracao = x;
	}
    
	
	/**
	 * Método chave e principal para a execução do programa. É nela que toda a 
	 * simulação ocorre. Para ela ocorrer, devem ser definidos quatro parâmetros 
	 * do tipo inteiro, para assim, ela ser gerada e apresentar os seus resultados.
	 * 
	 * @param min recebe um inteiro onde será definido o tempo de atendimento mínimo dos
	 * clientes
	 * @param max recebe um inteiro onde será definido o tempo de atendimento máximo dos
	 * clientes
	 * @param qwt recebe um inteiro onde será definido o tempo de espera na fila 
	 * @param duration recebe um inteiro onde será definido o tempo de duração em que a
	 * simulação irá ocorrer
	 * 
	 * @return retorna toda a ocorrência da simulação
	 */
    public String simular(int min, int max, int qwt, int duration)
    {
    	StringBuilder x1 = new StringBuilder();
    	setDuracao(duration);
		Cliente.setMinMaxTimeFila1(min,max);
		statTemposEsperaFila.adicionar(qwt);
		
		x1.append ("SIMULATION BEGIN : SUPERMERCADO");
		x1.append ("\n----------------------------------------------------------------------------------------------------------------------------");
		
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
                   x1.append("\n"+tempo + ": cliente " + c.getNumero() + " ("+c.getTempoAtendimento()+" min) entra na fila - " + fila.size() + " pessoa(s)");
				  if(SimulatorWindows.primeiro.getBackground() == Color.BLACK){
					  SimulatorWindows.primeiro.setBackground(Color.RED);
					  }
				  if(SimulatorWindows.segundo.getBackground() == Color.BLACK){
					  SimulatorWindows.segundo.setBackground(Color.RED);
					  }
				  if(SimulatorWindows.terceiro.getBackground() == Color.BLACK){
					  SimulatorWindows.terceiro.setBackground(Color.RED);
					  } 
            }
            statComprimentosFila.tamanhoMaximoFila(fila.size());

            //verificar se o caixa esta vazio
            if(caixa.estaVazio() && !fila.isEmpty())
            {
                //se o caixa esta vazio, atender o primeiro cliente da fila se ele existir
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
                	x1.append("\n"+tempo + ": cliente " + caixa.getClienteAtual().getNumero() + " chega ao caixa.");
                	
                	
                	//MUDANÇA NO GRAFICO DA FILA DA INTERFACE
    					SimulatorWindows.primeiro.setBackground(Color.GREEN);
                
            }
            else
            {
                //se o caixa ja esta ocupado, diminuir de um em um o tempo de atendimento ate chegar a zero
            	if (!caixa.estaVazio() && caixa.getClienteAtual().getTempoAtendimento() == 0)
                {
                    if(trace)
                    	x1.append("\n"+tempo + ": cliente " + caixa.getClienteAtual().getNumero() + " deixa o caixa.");
                    caixa.dispensarClienteAtual();
                    
                  //MUDANÇA NO GRAFICO DA FILA DA INTERFACE
                    if (fila.size() < 4){	
						if(SimulatorWindows.segundo.getBackground() == Color.RED && 
								SimulatorWindows.terceiro.getBackground() == Color.RED){
							
						        SimulatorWindows.primeiro.setBackground(Color.RED);	
						        SimulatorWindows.segundo.setBackground(Color.RED);
						        SimulatorWindows.terceiro.setBackground(Color.BLACK);	
						}
						
						if(SimulatorWindows.segundo.getBackground() == Color.RED &&
								SimulatorWindows.terceiro.getBackground() == Color.BLACK){
							
							   SimulatorWindows.primeiro.setBackground(Color.RED);
					           SimulatorWindows.segundo.setBackground(Color.BLACK);	
						}
						
						if(SimulatorWindows.segundo.getBackground() == Color.BLACK &&
								SimulatorWindows.terceiro.getBackground() == Color.BLACK){
							
							   SimulatorWindows.primeiro.setBackground(Color.BLACK);
						}
					 } else
					      {
							SimulatorWindows.terceiro.setBackground(Color.RED);
						    SimulatorWindows.primeiro.setBackground(Color.RED);
						    SimulatorWindows.segundo.setBackground(Color.RED);				 				 
					 }
                }
                else
                {
                	 if (caixa.estaVazio()==false){
                		 if (caixa.getClienteAtual().getTempoAtendimento() != 0)
                	 
                    caixa.getClienteAtual().decrementarTempoAtendimento();
                	 }
                }
            }
            statTempoFilaVazia.tempoSemFila(fila.size());
            statComprimentosFila.adicionar(fila.size());
        }
		x1.append ("\n----------------------------------------------------------------------------------------------------------------------------");
        return x1.toString();
    }
    
    
	/**
	 * Método capaz de calcular a porcentagem de x, de um total y.
	 * 
	 * @param x recebe um double x qualquer, deve ser menor ou igual a y
	 * @param y recebe um double y, que representará o total
	 * @return retorna a porcentagem que representa x em relação ao total y
	 */
	public double porcentagem (double x, double y){
		if (x>y){
			return 0;
		}
		return (x/y)*100;
	}
    
    
    /**
     * Método responsável por limpar toda a simulação corrente e gerar uma nova
     * simulação.
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
	 * Imprime os resultados da simulação gerada. Gera um relatório básico
	 * contendo todas as informações básicas, estatísticas e estatística 
	 * da simulação. 
	 * 
	 * @return retorna um relatório básico da simulação
	 */
    public String imprimirResultados()
    {
        String x = "##### Resultados da Simulação do Supermercado #####"
    	+"\n---------------------------------------------------------------------------------------------"
        +"\n******* Informações Básicas *******"
        +"\nDuração : " + duracao
        +"\nProbabilidade de chegada de clientes : " + probabilidadeChegada

        +"\nTotal de clientes gerados : " + geradorClientes.getQuantidadeGerada()

        
        +"\n\n******* Estatísticas Fila 1 *******"
        +"\nTempo médio de espera : " + statTemposEsperaFila.getMedia()
		+"\nTamanho máximo da fila : " +	
				statComprimentosFila.tamanhoMaximoFila(fila.size())
       +"\nComprimento médio da fila : " + statComprimentosFila.getMedia()
      +"\nAtendimentos que ocorreram sem espera : " 
				+ statAtendimentoSemEspera.atendimentoSemEspera(caixa.estaVazio(), fila.size())
		+"\nTempo total em que a fila 1 ficou vazia : "
				+ statTempoFilaVazia.getContagem() + " segundos"
      +"\nClientes ainda na fila 1 : " + fila.size()
        
        
		+"\n\n******* Estatísticas Caixa 1 *******"
        +"\nTempo de atendimento mínimo : " + Cliente.tempoMinAtendimento
        +"\nTempo de atendimento máximo : " + Cliente.tempoMaxAtendimento
		+"\nTempo médio de atendimento no caixa 1 : "
				+ statTempoAtendimentoCaixa.getMedia() +"  ( "+ porcentagem(statTempoAtendimentoCaixa.getMedia() , statTempoAtendimentoCaixa.getValor())+ "% )"
        +"\nCliente atendidos : " + caixa.getNumeroAtendidos() +" ( "+ porcentagem(caixa.getNumeroAtendidos() , geradorClientes.getQuantidadeGerada())+ "% )"
        +"\nCliente ainda no caixa : " + (caixa.getClienteAtual() != null)
		+"\n---------------------------------------------------------------------------------------------";
        return x;
    }
    
    
       /**
        * Método responsável por mostrar as estatísticas avançadas da simulação. Oferece
        * maiores detalhes sobre a simulação gerada.
        * 
        * @return retorna um relatório avançado da simulação
        */
        public String imprimirEstatisticasAvancadas() {	
        	
        String x = "##### Estatísticas avançadas do Supermercado #####"
        +"\n-----------------------------------------------------------------------------------------" 
        +"\n******* Estatísticas Avançadas *******"
		+"\nTamanho mínimo obtido na fila 1 : 1"
		+"\nTamanho máximo obtido na fila 1 : "+statComprimentosFila.tamanhoMaximoFila(fila.size())
		+"\nMediana da fila 1 : "+ statTemposEsperaFila.getMediana(1,statComprimentosFila.tamanhoMaximoFila(fila.size()))
		
		+"\n\nAtendimento mínimo ocorrido no caixa 1 : 1"
		+"\nAtendimento máximo ocorrido no caixa 1 : "+caixa.getNumeroAtendidos()
		+"\nMediana do atendimento no caixa 1 : "+ statTempoAtendimentoCaixa.getMediana(1,caixa.getNumeroAtendidos())
	   
		+"\n\nDesvio Padrão da fila 1 : "+ statTemposEsperaFila.getDesvioPadrao()
		+"\nDesvio Padrão de atendimento no caixa 1 : "+ statTempoAtendimentoCaixa.getDesvioPadrao()

	    +"\n-----------------------------------------------------------------------------------------";
    
    return x;
    
    }
}
