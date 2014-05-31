package supermercado;

public class CaixaDevolucao extends Caixa {
	
	StackTAD<Documento> documentosClientes;
	
	
	public CaixaDevolucao (){
		super();
	}
	

	public void entrega (){
	         documentosClientes.pop();
}
	
	
	
    
	

}
