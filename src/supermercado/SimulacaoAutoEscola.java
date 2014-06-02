package supermercado;

/*
 * Classe com a logica da simulacao passo-a-passo
 */
public class SimulacaoAutoEscola implements SimuladorInterface {
	private static final int duracao = 200;
	private static final double probabilidadeChegada = 0.1;
	private QueueTAD<Cliente> fila;
	private StackTAD<Cliente> fila2;
	private Caixa guiche1;
	private Caixa guiche2;
	private CaixaDevolucao guiche3;
	private Documento docs;
	private GeradorClientes geradorClientes;
	private Acumulador statTemposEsperaFila;
	private Acumulador statComprimentosFila;
	private Acumulador statTempoAtendimentoCaixa;
	private Acumulador statTempoAtendimentoCaixa2;
	private Acumulador statTempoAtendimentoCaixa3;
	private boolean trace; // valor indica se a simulacao ira imprimir
							// passo-a-passo os resultados

	public SimulacaoAutoEscola(boolean t) {
		fila = new QueueLinked<Cliente>();
		guiche1 = new Caixa();
		guiche2 = new Caixa();
		guiche3 = new CaixaDevolucao();
		docs = new Documento();
		geradorClientes = new GeradorClientes(probabilidadeChegada);
		statTemposEsperaFila = new Acumulador();
		statComprimentosFila = new Acumulador();
		statTempoAtendimentoCaixa = new Acumulador();
		statTempoAtendimentoCaixa2 = new Acumulador();
		statTempoAtendimentoCaixa3 = new Acumulador();
		trace = t;
	}

	public void simular() {
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

					if (trace)
						statTempoAtendimentoCaixa.adicionar(guiche1
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
				
			
				if (guiche2.estaVazio() && !fila.isEmpty()) {
					// se o caixa esta vazio, atender o primeiro cliente da fila se
					// ele existir
						// tirar o cliente do inicio da fila e atender no caixa
						guiche2.atenderNovoCliente(fila.remove());
						statTemposEsperaFila.adicionar(tempo
								- guiche2.getClienteAtual().getInstanteChegada());

						if (trace)
							statTempoAtendimentoCaixa2.adicionar(guiche2
									.getClienteAtual().getTempoAtendimento());
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

				
					

			else {
				// se o caixa ja esta ocupado, diminuir de um em um o tempo de
				// atendimento ate chegar a zero
				
			
				if (!guiche1.estaVazio() && guiche1.getClienteAtual().getTempoAtendimento() == 0) {
					if (trace) {
						System.out.println(tempo + ": cliente "
								+ guiche1.getClienteAtual().getNumero()
								+ " entrega o documento e deixa o caixa.");
						guiche1.dispensarClienteAtual();
					}
				}
				
				
				
				if (!guiche2.estaVazio() && guiche2.getClienteAtual().getTempoAtendimento() == 0) {
					if (trace) {
						System.out.println(tempo + ": cliente "
								+ guiche2.getClienteAtual().getNumero()
								+ " entrega o documento e deixa o caixa.");
						guiche2.dispensarClienteAtual();
					}
				}
				
				
				else{
			      if (guiche1.estaVazio()==false){
					if (guiche1.getClienteAtual().getTempoAtendimento() != 0)
					guiche1.getClienteAtual().decrementarTempoAtendimento();
					}
			      
			      if (guiche2.estaVazio()==false){
					if (guiche2.getClienteAtual().getTempoAtendimento() != 0)
					guiche2.getClienteAtual().decrementarTempoAtendimento();
				}
				}
				
			}

			statComprimentosFila.adicionar(fila.size());
		}
	}

	public void limpar() {

		fila = new QueueLinked<Cliente>();
		guiche1 = new Caixa();
		guiche2 = new Caixa();
		guiche3 = new CaixaDevolucao();
		docs = new Documento();
		geradorClientes = new GeradorClientes(probabilidadeChegada);
		statTemposEsperaFila = new Acumulador();
		statComprimentosFila = new Acumulador();
		statTempoAtendimentoCaixa = new Acumulador();
	}

	public void imprimirResultados() {
		System.out.println();
		System.out.println("### Resultados da Simulacao da Auto-Escola ###");
		System.out.println("Duracao:" + duracao);
		System.out.println("Probabilidade de chegada de clientes:"
				+ probabilidadeChegada);
		System.out.println("Tempo de atendimento minimo:"
				+ ClienteTipo2.tempoMinAtendimento);
		System.out.println("Tempo de atendimento maximo:"
				+ ClienteTipo2.tempoMaxAtendimento);
		System.out.println("Numero total de clientes atendidos:"
				+ guiche1.getNumeroAtendidos());
		System.out.println("Clientes ainda na fila:" + fila.size());
		System.out.println("Cliente ainda no caixa:"
				+ (guiche1.getClienteAtual() != null));
		System.out.println("Total de clientes gerados:"
				+ geradorClientes.getQuantidadeGerada());
		System.out.println("Tempo médio de espera:"
				+ statTemposEsperaFila.getMedia());
		System.out.println("Tempo médio de atendimento no guichê 1:"
				+ statTempoAtendimentoCaixa.getMedia());
		System.out.println("Tempo médio de atendimento no guichê 2:"
				+ statTempoAtendimentoCaixa2.getMedia());
		System.out.println("Numero de clientes atendidos no guichê 1: "
				+ guiche1.getNumeroAtendidos());
		System.out.println("Numero de clientes atendidos no guichê 2: "
				+ guiche2.getNumeroAtendidos());
		System.out.println("Comprimento medio da fila:"
				+ statComprimentosFila.getMedia());
	}
}
