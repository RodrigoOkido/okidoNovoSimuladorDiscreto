package simulator;

/*
 * Classe utilitaria que realiza calculos de media aritmetica
 */
public class Acumulador {
	private double valor;
	private double somaValorQuadrado;
	private int contador;

	public Acumulador() {
		valor = 0;
		somaValorQuadrado = 0;
		contador = 0;
	}

	public double getValor() {
		return (valor);
	}

	public int getContagem() {
		return contador;
	}

	public void adicionarQuadrado(int n) {
		somaValorQuadrado = somaValorQuadrado + Math.pow(n, 2);
	}

	public void adicionar(int n) {
		valor = valor + n;
		contador++;
	}

	public double getMedia() {
		if (contador != 0)
			return valor / contador;
		else
			return 0;
	}

	public double getMediana() {
		if (contador != 0) {
			return (contador + 1) / 2;
		} else
			return 0;
	}

	public double getDesvioPadrao() {
		if (contador != 0 && contador > 1)
			;
		double variancia = somaValorQuadrado
				- (contador * (Math.pow(getMedia(), 2))) / contador - 1;
		double desvioPadrao = Math.sqrt(variancia);
		return desvioPadrao;
	}
}