package simulator;

import ProgramInterfaces.QueueTAD;
import ProgramInterfaces.SimuladorInterface;
import ProgramInterfaces.StackTAD;
import ProgramListTADs.QueueLinked;
import ProgramListTADs.StackLinked;

/*
 * Classe com a logica da simulacao passo-a-passo
 */
public class SimulacaoAutoEscola implements SimuladorInterface {
	
	
	private static int duracao = 0;
	private static final double probabilidadeChegada = 0.1;
	private QueueTAD<Cliente> fila;
	private QueueTAD<Cliente> fila2;
	private StackTAD<Documento> pilhaDocumentos;
	private Caixa guiche1;
	private CaixaDevolucao guiche2;
	private Documento docs;
	private GeradorClientes geradorClientes;
	private Acumulador statTemposEsperaFila;
	private Acumulador statTemposEsperaFila2;
	private Acumulador statComprimentosFila;
	private Acumulador statComprimentosFila2;
	private Acumulador statTempoAtendimentoCaixa;
	private Acumulador statTempoAtendimentoCaixa2;
	private boolean trace; // valor indica se a simulacao ira imprimir
							// passo-a-passo os resultados

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
		trace = t;
	}
	
	
	public void setTempoAtendimento (int min, int max){
		ClienteTipo2.tempoMinAtendimento = min;
		ClienteTipo2.tempoMaxAtendimento = max;
	}

	private static void setDuracao(int x) {
		 duracao = x;
	}
    
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
			// verificar se o caixa esta vazio
			if (guiche1.estaVazio() && !fila.isEmpty()) {
				// se o caixa esta vazio, atender o primeiro cliente da fila se
				// ele existir
					// tirar o cliente do inicio da fila e atender no caixa
					guiche1.atenderNovoCliente(fila.remove());
					statTemposEsperaFila.adicionar(tempo
							- guiche1.getClienteAtual().getInstanteChegada());
					statTemposEsperaFila.adicionarDobro(tempo
							- guiche1.getClienteAtual().getInstanteChegada());

					if (trace)
						statTempoAtendimentoCaixa.adicionar(guiche1
								.getClienteAtual().getTempoAtendimento());
					statTempoAtendimentoCaixa.adicionarDobro(guiche1
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
						
						if (guiche2.estaVazio() && !fila2.isEmpty()) {
							// se o caixa esta vazio, atender o primeiro cliente da fila se
							// ele existir
								// tirar o cliente do inicio da fila e atender no caixa
								guiche2.atenderNovoCliente(fila2.remove());
								statTemposEsperaFila2.adicionar(tempo
										- guiche2.getClienteAtual().getInstanteChegada());
								statTemposEsperaFila2.adicionarDobro(tempo
										- guiche2.getClienteAtual().getInstanteChegada());

								if (trace)
									statTempoAtendimentoCaixa2.adicionar(((ClienteTipo2) guiche2
											.getClienteAtual()).getTempoAtendimento2());
								    statTempoAtendimentoCaixa2.adicionarDobro(((ClienteTipo2) guiche2
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
			statComprimentosFila2.adicionar(fila2.size());
		}
	}

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

	public void imprimirResultados() {
		System.out.println();
		System.out.println("##### Resultados da Simulacao da Auto-Escola #####");
		System.out.println("\n-----------------------------------------------------------");
		System.out.println("******* Informações Básicas: *******");
		System.out.println("Duracao:" + duracao);
		System.out.println("Probabilidade de chegada de clientes:"
				+ probabilidadeChegada);
		System.out.println("Tempo de atendimento minimo no guichê 1 : "
				+ ClienteTipo2.tempoMinAtendimento);
		System.out.println("Tempo de atendimento maximo no ghichê 1 : "
				+ ClienteTipo2.tempoMaxAtendimento);
		System.out.println("Tempo de atendimento minimo no guichê 2 : " 
				+ ClienteTipo2.tempoMinAtendimento2);
		System.out.println("Tempo de atendimento maximo no ghichê 2 : "
				+ ClienteTipo2.tempoMaxAtendimento2);
		System.out.println("Numero total de clientes atendidos : "
				+ guiche1.getNumeroAtendidos());
		System.out.println("Clientes ainda na fila : " + fila.size());
		System.out.println("Clientes ainda na fila 2 : " + fila2.size());
		System.out.println("Cliente ainda no guiche 1 : "
				+ (guiche1.getClienteAtual() != null));
		System.out.println("Cliente ainda no guiche 2 : " 
				+ (guiche2.getClienteAtual() != null));
		System.out.println("Total de clientes gerados : "
				+ geradorClientes.getQuantidadeGerada());
		
		System.out.println("\n******* Estatísticas *******");
		System.out.println("Tempo médio de espera na fila 1 : "
				+ statTemposEsperaFila.getMedia());
		System.out.println("Tempo médio de espera na fila 2 : "
				+ statTemposEsperaFila2.getMedia());
		System.out.println("Tempo médio de atendimento no guichê 1 : "
				+ statTempoAtendimentoCaixa.getMedia());
		System.out.println("Tempo médio de atendimento no guichê 2 : "
				+ statTempoAtendimentoCaixa2.getMedia());
		System.out.println("Numero de clientes atendidos no guichê 1 : "
				+ guiche1.getNumeroAtendidos());
		System.out.println("Numero de clientes atendidos no guichê 2 : "
				+ guiche2.getNumeroAtendidos());
		System.out.println("Comprimento medio da fila : "
				+ statComprimentosFila.getMedia());
		System.out.println("Comprimento medio da fila 2 : "
				+ statComprimentosFila2.getMedia());
		
		
		System.out.println("\n******* Estatísticas Avançadas *******");
		System.out.println("Mediana da fila 1 : "+ statTemposEsperaFila.getMediana());
		System.out.println("Mediana da fila 2 : "+ statTemposEsperaFila2.getMediana());
		System.out.println("Mediana do guichê 1 : "+ statTempoAtendimentoCaixa.getMediana());
		System.out.println("Mediana da guichê 2 : "+ statTempoAtendimentoCaixa2.getMediana());

		System.out.println("Desvio Padrão da fila 1 : "+ statTemposEsperaFila.getDesvioPadrao());
		System.out.println("Desvio Padrão da fila 2 : "+ statTemposEsperaFila2.getDesvioPadrao());
		System.out.println("Desvio Padrão de atendimento no guichê 1 : "+ statTempoAtendimentoCaixa.getDesvioPadrao());
		System.out.println("Desvio Padrão de atendimento no guichê 2 : "+ statTempoAtendimentoCaixa2.getDesvioPadrao());

		System.out.println("\n-----------------------------------------------------------");
	}



}
