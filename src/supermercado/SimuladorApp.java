package supermercado;

import ProgramInterfaces.SimuladorInterface;

/*
 * Programa principal da simulacao
 */


public class SimuladorApp{


    public static void main(String[] args)
    {
        SimuladorInterface sim = new SimulacaoAutoEscola(true);
        sim.simular();
        sim.imprimirResultados();
    }
}



