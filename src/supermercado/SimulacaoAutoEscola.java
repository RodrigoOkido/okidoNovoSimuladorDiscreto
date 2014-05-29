package supermercado;


public class SimulacaoAutoEscola implements SimuladorInterface{

	private static final int duracao = 200;
    private static final double probabilidadeChegada = 0.1;
    private QueueTAD<Cliente> fila;
    private QueueTAD<Cliente> fila2;
    private Caixa guiche1;
    private Caixa guiche2;
    private Caixa caixaEntregaDocumentacao;
    private GeradorClientes geradorClientes;
    private Acumulador statTemposEsperaFila;
    private Acumulador statComprimentosFila;
    private boolean trace;
    
    
	@Override
	public void simular() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void limpar() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void imprimirResultados() {
		// TODO Auto-generated method stub
		
	}
    
    
}
