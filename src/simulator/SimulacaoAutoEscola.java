package simulator;

import ProgramInterfaces.QueueTAD;
import ProgramInterfaces.SimuladorInterface;
import ProgramInterfaces.StackTAD;
import ProgramListTADs.QueueLinked;
import ProgramListTADs.StackLinked;

/**
 * Classe com a l�gica da simulacao passo-a-passo. Esta classe cria uma simula��o
 * em uma auto-escola, possui diversos atributos necess�rios para que se ocorra  
 * a simula��o. Inclui um caixa, um caixa de devolu��o de documentos, duas filas, 
 * gerador de clientes, documentos, uma pilha e documentos e diversos vari�veis da 
 * classe Acumulador para executar diversos tipos de c�lculos diferentes para exibir
 * no relat�rio final os resultados da simula��o (desvio padr�o, m�dia, mediana, entre
 * outros). 
 * 
 * @author Rodrigo Okido
 * @version 1.0 
 */
public class SimulacaoAutoEscola implements SimuladorInterface 
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
	 * Atributo que define a primeira fila de clientes.
	 */
	private QueueTAD<Cliente> fila;
	
	
	/**
	 * Atributo que define a segunda fila de clientes.
	 */
	private QueueTAD<Cliente> fila2;
	
	
	/**
	 * Atributo que gera uma pilha de documentos.
	 */
	private StackTAD<Documento> pilhaDocumentos;
	
	
	/**
	 * Atributo para a cria��o de um caixa.
	 */
	private Caixa guiche1;
	
	
	/**
	 * Atributo para a cria��o de um caixa de devolu��o.
	 */
	private CaixaDevolucao guiche2;
	
	
	/**
	 * Atributo para cria��o de documentos.
	 */
	private Documento docs;
	
	
	/**
	 * Atributo para gera��o de clientes.
	 */
	private GeradorClientes geradorClientes;
	
	
	/**
	 * Atributo que ser� utilizado para c�lculos aritm�ticos.
	 * Neste caso, este acumulador calcular� dados estat�sticos em rela��o
	 * ao tempo de espera na primeira fila.
	 */
	private Acumulador statTemposEsperaFila;
	
	
	/**
	 * Atributo que ser� utilizado para c�lculos aritm�ticos.
	 * Neste caso, este acumulador calcular� dados estat�sticos em rela��o
	 * ao tempo de espera na segunda fila.
	 */
	private Acumulador statTemposEsperaFila2;
	
	
	/**
	 * Atributo que ser� utilizado para c�lculos aritm�ticos.
	 * Neste caso, este acumulador calcular� dados estat�sticos em rela��o
	 * ao comprimento da primeira fila.
	 */
	private Acumulador statComprimentosFila;
	
	
	/**
	 * Atributo que ser� utilizado para c�lculos aritm�ticos.
	 * Neste caso, este acumulador calcular� dados estat�sticos em rela��o
	 * ao comprimento da segunda fila.
	 */
	private Acumulador statComprimentosFila2;
	
	
	/**
	 * Atributo que ser� utilizado para c�lculos aritm�ticos.
	 * Neste caso, este acumulador calcular� dados estat�sticos em rela��o
	 * ao tempo de atendimento no primeiro caixa (guich�).
	 */
	private Acumulador statTempoAtendimentoCaixa;
	
	
	/**
	 * Atributo que ser� utilizado para c�lculos aritm�ticos.
	 * Neste caso, este acumulador calcular� dados estat�sticos em rela��o
	 * ao tempo de atendimento no segundo caixa (guich�).
	 */
	private Acumulador statTempoAtendimentoCaixa2;
	
	
	private Acumulador statTempoFilaVazia1;
	private Acumulador statTempoFilaVazia2;
	
	/**
	 * Atributo que indica o passo-a-passo da simula��o. 
	 */
	private boolean trace; // valor indica se a simulacao ira imprimir
							// passo-a-passo os resultados

	
	/**
	 * Construtor para instanciar uma simula��o de uma auto-escola. 
	 * Instancia todos os atributos tendo apenas que definir por par�metro
	 * o atributo "trace". 
	 * 
	 * @param t Recebe por par�metro "true" para que a simula��o possa ser
	 * executada passo-a-passo. 
	 */
	public SimulacaoAutoEscola(boolean t) {
		fila = new QueueLinked<Cliente>();
		fila2 = new QueueLinked<Cliente>();
		pilhaDocumentos = new StackLinked<Documento>();
		guiche1 = new Caixa();
		guiche2 = new CaixaDevolucao();
		docs = new Documento();
		geradorClientes = new GeradorClientes(probabilidadeChegada);
		statTemposEsperaFila = new Acumulador();
		statTemposEsperaFila2 = new Acumulador();
		statComprimentosFila = new Acumulador();
		statComprimentosFila2 = new Acumulador();
		statTempoAtendimentoCaixa = new Acumulador();
		statTempoAtendimentoCaixa2 = new Acumulador();
		statTempoFilaVazia1 = new Acumulador();
		statTempoFilaVazia2 = new Acumulador();
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
	 * simula��o ocorre. Para a simula��o ocorrer, devem ser definidos quatro 
	 * par�metros para assim, ela ser gerada e apresentar os seus resultados.
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
		ClienteTipo2.setMinMaxTimeFila1(1,3);
		statTemposEsperaFila.adicionar(qwt);
		
		int senha = 1001;
		// realizar a simulacao por um certo numero de passos de duracao
		for (int tempo = 0; tempo < duracao; tempo++) {
			// verificar se um cliente chegou
			if (geradorClientes.gerar()) {
				// se cliente chegou, criar um cliente e inserir na fila do
				// caixa
				Cliente c = new ClienteTipo2(
						geradorClientes.getQuantidadeGerada(), tempo, senha,
						docs);
				fila.add(c);
				senha++;

				if (trace)
					System.out.println(tempo + ": cliente " + c.getNumero()
							+ " com a senha " + senha + " ("
							+ c.getTempoAtendimento()
							+ " min) entra na fila - " + fila.size()
							+ " pessoa(s)");
			}
			statComprimentosFila.tamanhoMaximoFila(fila.size());

			// verificar se o caixa esta vazio
			if (guiche1.estaVazio() && !fila.isEmpty()) {
				// se o caixa esta vazio, atender o primeiro cliente da fila se
				// ele existir
					// tirar o cliente do inicio da fila e atender no caixa
					guiche1.atenderNovoCliente(fila.removeFromHead());
					statTemposEsperaFila.adicionar(tempo
							- guiche1.getClienteAtual().getInstanteChegada());
					statTemposEsperaFila.adicionarQuadrado(tempo
							- guiche1.getClienteAtual().getInstanteChegada());

					if (trace)
						statTempoAtendimentoCaixa.adicionar(guiche1
								.getClienteAtual().getTempoAtendimento());
					statTempoAtendimentoCaixa.adicionarQuadrado(guiche1
							.getClienteAtual().getTempoAtendimento());


					System.out
							.println(tempo
									+ ": cliente "
									+ guiche1.getClienteAtual().getNumero()
									+ " de senha "
									+ guiche1.getClienteAtual()
											.getSenhaCliente(
													guiche1.getClienteAtual())
									+ " foi chamado no guich� 1 e o cliente se dirige ao caixa.");
				
				}
				
			else {
				// se o caixa ja esta ocupado, diminuir de um em um o tempo de
				// atendimento ate chegar a zero
				
			
				if (!guiche1.estaVazio() && guiche1.getClienteAtual().getTempoAtendimento() == 0) {
					if (trace) {
						System.out.println(tempo + ": cliente "
								+ guiche1.getClienteAtual().getNumero()
								+ " entrega o documento e deixa o guich� 1.");
					
						ClienteTipo2 aux = (ClienteTipo2)guiche1.getClienteAtual();
						ClienteTipo2.setMinMaxTimeFila2(min, max);
						ClienteTipo2 c2 = new ClienteTipo2 (guiche1.getClienteAtual().getNumero(),guiche1.getClienteAtual().getInstanteChegada(),guiche1.getClienteAtual().getSenhaCliente((ClienteTipo2)guiche1.getClienteAtual()),aux.getDocumento());						
						pilhaDocumentos.push(aux.getDocumento());
						fila2.add(c2);
						System.out.println(tempo + ": cliente " + c2.getNumero()
								+ " com a senha " + senha + " ("
								+ c2.getTempoAtendimento2()
								+ " min) entra na fila 2 - " + fila2.size()
								+ " pessoa(s)");
						guiche1.dispensarClienteAtual();
						statComprimentosFila2.tamanhoMaximoFila(fila2.size());

						if (guiche2.estaVazio() && !fila2.isEmpty()) {
							// se o caixa esta vazio, atender o primeiro cliente da fila se
							// ele existir
								// tirar o cliente do fim da fila e atender no caixa
								guiche2.atenderNovoCliente(fila2.removeFromTail());
								statTemposEsperaFila2.adicionar(tempo
										- guiche2.getClienteAtual().getInstanteChegada());
								statTemposEsperaFila2.adicionarQuadrado(tempo
										- guiche2.getClienteAtual().getInstanteChegada());

								if (trace)
									statTempoAtendimentoCaixa2.adicionar(((ClienteTipo2) guiche2
											.getClienteAtual()).getTempoAtendimento2());
								    statTempoAtendimentoCaixa2.adicionarQuadrado(((ClienteTipo2) guiche2
										.getClienteAtual()).getTempoAtendimento2());
								System.out
										.println(tempo
												+ ": cliente "
												+ guiche2.getClienteAtual().getNumero()
												+ " de senha "
												+ guiche2.getClienteAtual()
														.getSenhaCliente(
																guiche2.getClienteAtual())
												+ " foi chamado no guich� 2 e o cliente se dirige ao caixa.");
							}
						
						
						
					}
				}
				
				if (!guiche2.estaVazio() && ((ClienteTipo2) guiche2.getClienteAtual()).getTempoAtendimento2() == 0) {
					if (trace) {
						guiche2.entrega(pilhaDocumentos);
						System.out.println(tempo + ": cliente "
								+ guiche2.getClienteAtual().getNumero()
								+ " pegou o documento desejado e deixa o caixa.");
						guiche2.dispensarClienteAtual();
					}
				

				  
				}
				
				else{
			      if (guiche1.estaVazio()==false){
					if (guiche1.getClienteAtual().getTempoAtendimento() != 0)
					guiche1.getClienteAtual().decrementarTempoAtendimento();
					}
			      
			      if (guiche2.estaVazio()==false){
					if (((ClienteTipo2) guiche2.getClienteAtual()).getTempoAtendimento2() != 0)
					((ClienteTipo2) guiche2.getClienteAtual()).decrementarTempoAtendimento2();
				}
				}
				
			}

			statComprimentosFila.adicionar(fila.size());
			statTempoFilaVazia1.tempoSemFila(fila.size());
			statComprimentosFila2.adicionar(fila2.size());
			statTempoFilaVazia2.tempoSemFila(fila2.size());
		}
	}

    
    /**
     * M�todo respons�vel por limpar toda a simula��o corrente e gerar uma nova
     * simula��o.
     */
	public void limpar() {

		fila = new QueueLinked<Cliente>();
		fila2 = new QueueLinked<Cliente>();
		pilhaDocumentos = new StackLinked<Documento>();
		guiche1 = new Caixa();
		guiche2 = new CaixaDevolucao();
		docs = new Documento();
		geradorClientes = new GeradorClientes(probabilidadeChegada);
		statTemposEsperaFila = new Acumulador();
		statTemposEsperaFila2 = new Acumulador();
		statComprimentosFila = new Acumulador();
		statComprimentosFila2 = new Acumulador();
		statTempoAtendimentoCaixa = new Acumulador();
		statTempoAtendimentoCaixa2 = new Acumulador();
	}

	
	/**
	 * Imprime os resultados da simula��o gerada. Gera um relat�rio completo
	 * contendo todas as informa��es b�sicas, estat�sticas e estat�stica 
	 * avan�adas da simula��o. 
	 */
	public void imprimirResultados() {
		System.out.println();
		System.out.println("##### Resultados da Simulacao da Auto-Escola #####");
		System.out.println("\n-----------------------------------------------------------");
		System.out.println("******* Informa��es B�sicas: *******");
		System.out.println("Duracao:" + duracao);
		System.out.println("Probabilidade de chegada de clientes:"
				+ probabilidadeChegada);
		System.out.println("Numero total de clientes com atendimento completo (guich� 1 + guich� 2) : "
				+ guiche2.getNumeroAtendidos());
		System.out.println("Total de clientes gerados : "
				+ geradorClientes.getQuantidadeGerada());
		
		
		// INFORMA��ES EM RELA��O A FILA E GUICH� 1:
		
		System.out.println("\n******* Estat�sticas Fila 1 *******");
		System.out.println("Tempo m�dio de espera na fila 1 : "
				+ statTemposEsperaFila.getMedia());
		System.out.println("Tamanho m�ximo da fila 1: " +	
				statComprimentosFila.tamanhoMaximoFila(fila.size()));
		System.out.println("Comprimento medio da fila 1 : "
				+ statComprimentosFila.getMedia());
		System.out.println("Tempo total em que a fila 1 ficou vazia: "
				+ statTempoFilaVazia1.getContagem() + " segundos");
		System.out.println("Clientes ainda na fila 1 : " + fila.size());

		
		System.out.println("\n******* Estat�sticas Guich� 1 *******");
		System.out.println("Tempo de atendimento minimo no guich� 1 : "
				+ ClienteTipo2.tempoMinAtendimento);
		System.out.println("Tempo de atendimento maximo no ghich� 1 : "
				+ ClienteTipo2.tempoMaxAtendimento);
		System.out.println("Tempo m�dio de atendimento no guich� 1 : "
				+ statTempoAtendimentoCaixa.getMedia());
		System.out.println("Numero de clientes atendidos no guich� 1 : "
				+ guiche1.getNumeroAtendidos());
		System.out.println("Cliente ainda no guiche 1 : "
				+ (guiche1.getClienteAtual() != null));

		
	    // INFORMA��ES EM RELA��O A FILA E GUICH� 2:	
		
		System.out.println("\n******* Estat�sticas Fila 2 *******");
		System.out.println("Tempo m�dio de espera na fila 2 : "
				+ statTemposEsperaFila2.getMedia());
		System.out.println("Tamanho m�ximo da fila 2 : " +	
				statComprimentosFila2.tamanhoMaximoFila(fila2.size()));
		System.out.println("Comprimento medio da fila 2 : "
				+ statComprimentosFila2.getMedia());
		System.out.println("Tempo total em que a fila 2 ficou vazia: "
				+ statTempoFilaVazia2.getContagem() + " segundos");
		System.out.println("Clientes ainda na fila 2 : " + fila2.size());

		
		System.out.println("\n******* Estat�sticas Guich� 2 (Devolu��o) *******");
		System.out.println("Tempo de atendimento minimo no guich� 2 : " 
				+ ClienteTipo2.tempoMinAtendimento2);
		System.out.println("Tempo de atendimento maximo no ghich� 2 : "
				+ ClienteTipo2.tempoMaxAtendimento2);
		System.out.println("Tempo m�dio de atendimento no guich� 2 : "
				+ statTempoAtendimentoCaixa2.getMedia());
		System.out.println("Documentos ainda empilhados : "+pilhaDocumentos.size());	
		System.out.println("Numero de clientes atendidos no guich� 2 : "
				+ guiche2.getNumeroAtendidos());
		System.out.println("Cliente ainda no guiche 2 : " 
				+ (guiche2.getClienteAtual() != null));
		

		// ESTAT�STICAS AVAN�ADAS
		
		System.out.println("\n******* Estat�sticas Avan�adas *******");
		System.out.println("Mediana da fila 1 : "+ statTemposEsperaFila.getMediana());
		System.out.println("Mediana da fila 2 : "+ statTemposEsperaFila2.getMediana());
		System.out.println("Mediana do guich� 1 : "+ statTempoAtendimentoCaixa.getMediana());
		System.out.println("Mediana da guich� 2 : "+ statTempoAtendimentoCaixa2.getMediana());
		System.out.println("Desvio Padr�o da fila 1 : "+ statTemposEsperaFila.getDesvioPadrao());
		System.out.println("Desvio Padr�o da fila 2 : "+ statTemposEsperaFila2.getDesvioPadrao());
		System.out.println("Desvio Padr�o de atendimento no guich� 1 : "+ statTempoAtendimentoCaixa.getDesvioPadrao());
		System.out.println("Desvio Padr�o de atendimento no guich� 2 : "+ statTempoAtendimentoCaixa2.getDesvioPadrao());

		System.out.println("\n-----------------------------------------------------------");
	}



}
