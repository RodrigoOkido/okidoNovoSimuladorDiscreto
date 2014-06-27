package simulator;

import ProgramInterfaces.QueueTAD;
import ProgramInterfaces.SimuladorInterface;
import ProgramInterfaces.StackTAD;
import ProgramListTADs.QueueLinked;
import ProgramListTADs.StackLinked;

/**
 * Classe com a lógica da simulacao passo-a-passo. Esta classe gera uma simulação
 * de uma auto-escola, possui diversos atributos necessários para que se ocorra  
 * a simulação. Inclui um caixa, um caixa de devolução de documentos, duas filas, 
 * gerador de clientes, documentos, uma pilha e documentos e diversas variáveis da 
 * classe Acumulador para executar diversos tipos de cálculos diferentes para exibir
 * no relatório final os resultados da simulação (desvio padrão, média, mediana, entre
 * outros). 
 * 
 * @author Rodrigo Okido
 * @version 1.0 
 */
public class SimulacaoAutoEscola implements SimuladorInterface 
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
	 * Atributo para a criação de um caixa.
	 */
	private Caixa guiche1;
	
	
	/**
	 * Atributo para a criação de um caixa de devolução.
	 */
	private CaixaDevolucao guiche2;
	
	
	/**
	 * Atributo para criação de documentos.
	 */
	private Documento docs;
	
	
	/**
	 * Atributo para geração de clientes.
	 */
	private GeradorClientes geradorClientes;
	
	
	/**
	 * Atributo que será utilizado para cálculos aritméticos.
	 * Neste caso, este acumulador calculará dados estatísticos em relação
	 * ao tempo de espera na primeira fila.
	 */
	private Acumulador statTemposEsperaFila;
	
	
	/**
	 * Atributo que será utilizado para cálculos aritméticos.
	 * Neste caso, este acumulador calculará dados estatísticos em relação
	 * ao tempo de espera na segunda fila.
	 */
	private Acumulador statTemposEsperaFila2;
	
	
	/**
	 * Atributo que será utilizado para cálculos aritméticos.
	 * Neste caso, este acumulador calculará dados estatísticos em relação
	 * ao comprimento da primeira fila.
	 */
	private Acumulador statComprimentosFila;
	
	
	/**
	 * Atributo que será utilizado para cálculos aritméticos.
	 * Neste caso, este acumulador calculará dados estatísticos em relação
	 * ao comprimento da segunda fila.
	 */
	private Acumulador statComprimentosFila2;
	
	
	/**
	 * Atributo que será utilizado para cálculos aritméticos.
	 * Neste caso, este acumulador calculará dados estatísticos em relação
	 * ao tempo de atendimento no primeiro caixa (guichê).
	 */
	private Acumulador statTempoAtendimentoCaixa;
	
	
	/**
	 * Atributo que será utilizado para cálculos aritméticos.
	 * Neste caso, este acumulador calculará dados estatísticos em relação
	 * ao tempo de atendimento no segundo caixa (guichê).
	 */
	private Acumulador statTempoAtendimentoCaixa2;
	
	
	/**
	 * Atributo para verificar o tempo total em que a primeira fila não possuia nenhum
	 * cliente. Ou seja, o tempo total em que não existiu fila, para ser atendido no
	 * primeiro caixa.
	 */
	private Acumulador statTempoFilaVazia1;
	
	
	/**
	 * Atributo para verificar o tempo total em que a segunda fila não possuia nenhum
	 * cliente. Ou seja, o tempo total em que não existiu fila, para ser atendido no
	 * segundo caixa.
	 */
	private Acumulador statTempoFilaVazia2;
	
	
	/**
	 * Verifica a quantidade de atendimentos em que não houve espera do cliente para
	 * ser atendido no primeiro caixa.
	 */
	private Acumulador statAtendimentoSemEspera1;
	
	
	/**
	 * Verifica a quantidade de atendimentos em que não houve espera do cliente para
	 * ser atendido no segundo caixa.
	 */
	private Acumulador statAtendimentoSemEspera2;
	
	
	/**
	 * Atributo que indica o passo-a-passo da simulação. 
	 */
	private boolean trace; // valor indica se a simulacao ira imprimir
							// passo-a-passo os resultados

	
	/**
	 * Construtor para instanciar uma simulação de uma auto-escola. 
	 * Instancia todos os atributos tendo apenas que definir por parâmetro
	 * o atributo "trace". 
	 * 
	 * @param t Recebe por parâmetro "true" para que a simulação possa ser
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
		statAtendimentoSemEspera1 = new Acumulador();
		statAtendimentoSemEspera2 = new Acumulador();
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
				statAtendimentoSemEspera1.atendimentoSemEspera(guiche1.estaVazio(), fila.size());

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
									+ " foi chamado no guichê 1 e o cliente se dirige ao caixa.");
				
				}
				
			else {
				// se o caixa ja esta ocupado, diminuir de um em um o tempo de
				// atendimento ate chegar a zero
				
			
				if (!guiche1.estaVazio() && guiche1.getClienteAtual().getTempoAtendimento() == 0) {
					if (trace) {
						System.out.println(tempo + ": cliente "
								+ guiche1.getClienteAtual().getNumero()
								+ " entrega o documento e deixa o guichê 1.");
					
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
							statAtendimentoSemEspera2.atendimentoSemEspera(guiche2.estaVazio(), fila2.size());

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
												+ " foi chamado no guichê 2 e o cliente se dirige ao caixa.");
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
     * Método responsável por limpar toda a simulação corrente e gerar uma nova
     * simulação.
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
		statTempoFilaVazia1 = new Acumulador();
		statTempoFilaVazia2 = new Acumulador();
		statAtendimentoSemEspera1 = new Acumulador();
		statAtendimentoSemEspera2 = new Acumulador();
	}

	
	/**
	 * Imprime os resultados da simulação gerada. Gera um relatório básico
	 * contendo todas as informações básicas, estatísticas e estatística 
	 * da simulação. 
	 * 
	 * @return retorna um relatório básico da simulação
	 */
	public String imprimirResultados() {
		String x = "##### Resultados da Simulação da Auto-Escola #####"
		+"\n-----------------------------------------------------------------------------------------"
		+"\n******* Informações Básicas: *******"
		+"\nDuração:" + duracao
		+"\nProbabilidade de chegada de clientes:"
				+ probabilidadeChegada
		+"\nNúmero total de clientes com \natendimento completo (guichê 1 + guichê 2) : "
				+ guiche2.getNumeroAtendidos()
		+"\nTotal de clientes gerados : "
				+ geradorClientes.getQuantidadeGerada()
		
		
		// INFORMAÇÕES EM RELAÇÃO A FILA E GUICHÊ 1:
		
		+"\n\n******* Estatísticas Fila 1 *******"
		+"\nTempo médio de espera na fila 1 : "
				+ statTemposEsperaFila.getMedia()
		+"\nTamanho máximo da fila 1: " +	
				statComprimentosFila.tamanhoMaximoFila(fila.size())
		+"\nComprimento médio da fila 1 : "
				+ statComprimentosFila.getMedia()
		+"\nAtendimentos que ocorreram sem espera: " 
				+ statAtendimentoSemEspera1.atendimentoSemEspera(guiche1.estaVazio(), fila.size())
		+"\nTempo total em que a fila 1 ficou vazia: "
				+ statTempoFilaVazia1.getContagem() + " segundos"
		+"\nClientes ainda na fila 1 : " + fila.size()

		
		+"\n\n******* Estatísticas Guichê 1 *******"
		+"\nTempo de atendimento mínimo no guichê 1 : "
				+ ClienteTipo2.tempoMinAtendimento
		+"\nTempo de atendimento máximo no ghichê 1 : "
				+ ClienteTipo2.tempoMaxAtendimento
		+"\nTempo médio de atendimento no guichê 1 : "
				+ statTempoAtendimentoCaixa.getMedia()
		+("\nNúmero de clientes atendidos no guichê 1 : "
				+ guiche1.getNumeroAtendidos())
		+("\nCliente ainda no guiche 1 : "
				+ (guiche1.getClienteAtual() != null)

		
	    // INFORMAÇÕES EM RELAÇÃO A FILA E GUICHÊ 2:	
		
		+("\n\n******* Estatísticas Fila 2 *******")
		+("\nTempo médio de espera na fila 2 : "
				+ statTemposEsperaFila2.getMedia())
		+("\nTamanho máximo da fila 2 : " +	
				statComprimentosFila2.tamanhoMaximoFila(fila2.size()))
		+("\nComprimento médio da fila 2 : "
				+ statComprimentosFila2.getMedia())
		+("\nAtendimentos que ocorreram sem espera: " 
				+ statAtendimentoSemEspera2.atendimentoSemEspera(guiche2.estaVazio(), fila2.size()))
		+("\nTempo total em que a fila 2 ficou vazia: "
				+ statTempoFilaVazia2.getContagem() + " segundos")
		+("\nClientes ainda na fila 2 : " + fila2.size())

		
		+("\n\n******* Estatísticas Guichê 2 (Devolução) *******")
		+("\nTempo de atendimento mínimo no guichê 2 : " 
				+ ClienteTipo2.tempoMinAtendimento2)
		+("\nTempo de atendimento máximo no guichê 2 : "
				+ ClienteTipo2.tempoMaxAtendimento2)
		+("\nTempo médio de atendimento no guichê 2 : "
				+ statTempoAtendimentoCaixa2.getMedia())
		+("\nDocumentos ainda empilhados : "+pilhaDocumentos.size())
		+("\nNúmero de clientes atendidos no guichê 2 : "
				+ guiche2.getNumeroAtendidos())
		+("\nCliente ainda no guiche 2 : " 
				+ (guiche2.getClienteAtual() != null)))
		+"\n---------------------------------------------------------------------------------------------";
		return x;
	}
	
        @Override
        /**
         * Método responsável por mostrar as estatísticas avançadas da simulação. Oferece
         * maiores detalhes sobre a simulação gerada.
         * 
         * @return retorna um relatório avançado da simulação
         */
    	public String imprimirEstatisticasAvancadas() {	
		// ESTATÍSTICAS AVANÇADAS
		String x ="##### Estatísticas avançadas da Auto-Escola #####"
		+"\n---------------------------------------------------------------------------------------------"
		+"\n******* Estatísticas Avançadas *******"
		+("\nTamanho mínimo obtido na fila 1 : 1")
		+("\nTamanho máximo obtido na fila 1 : "+statComprimentosFila.tamanhoMaximoFila(fila.size()))
		+("\nMediana da fila 1 : "+ statTemposEsperaFila.getMediana(1,statComprimentosFila.tamanhoMaximoFila(fila.size())))
	
		+("\n\nTamanho mínimo obtido na fila 2 : 1")
		+("\nTamanho máximo obtido na fila 2 : "+statComprimentosFila2.tamanhoMaximoFila(fila2.size()))
		+("\nMediana da fila 2 : "+ statTemposEsperaFila2.getMediana(1,statComprimentosFila2.tamanhoMaximoFila(fila2.size())))
		
		+("\n\nAtendimento mínimo ocorrido no guichê 1 : 1")
		+("\nAtendimento máximo ocorrido no guichê 1 : "+guiche1.getNumeroAtendidos())
		+("\nMediana do atendimento no guichê 1 : "+ statTempoAtendimentoCaixa.getMediana(1,guiche1.getNumeroAtendidos()))
		
		+("\n\nAtendimento mínimo ocorrido no guichê 2 : 1")
		+("\nAtendimento máximo ocorrido no guichê 2 : "+guiche2.getNumeroAtendidos())
		+("\nMediana do atendimento no guichê 2 : "+ statTempoAtendimentoCaixa2.getMediana(1,guiche2.getNumeroAtendidos()))
		
		+("\n\nDesvio Padrão da fila 1 : "+ statTemposEsperaFila.getDesvioPadrao())
		+("\nDesvio Padrão da fila 2 : "+ statTemposEsperaFila2.getDesvioPadrao())
		+("\nDesvio Padrão de atendimento no guichê 1 : "+ statTempoAtendimentoCaixa.getDesvioPadrao())
		+("\nDesvio Padrão de atendimento no guichê 2 : "+ statTempoAtendimentoCaixa2.getDesvioPadrao())

		+"\n---------------------------------------------------------------------------------------------";		
		return x;
	}

	}

