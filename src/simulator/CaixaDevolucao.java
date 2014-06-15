package simulator;

import ProgramInterfaces.StackTAD;


public class CaixaDevolucao extends Caixa {
		
	public CaixaDevolucao (){
		super();
	}
	
	public void entrega (StackTAD<Documento> doc){
		doc.pop();
		}
}
	

