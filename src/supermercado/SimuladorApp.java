package supermercado;

/*
 * Programa principal da simulacao
 */
public class SimuladorApp
{
    public static void main(String[] args)
    {
        SimulacaoSupermercado sim = new SimulacaoSupermercado(true);
        sim.simular();
        sim.imprimirResultados();
    }
}
