package simulator;

import ProgramInterfaces.SimuladorInterface;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

/**
 * Classe responsável pela execução do programa. 
 * 
 * @author Rodrigo Okido
 * @version 1.0
 */
public class SimuladorApp 
{

	/**
	 * Método responsável pela execução do programa. Executa fazendo a leitura 
	 * de dados de um arquivo xml, e a partir da leitura desses dados, a simulação
	 * é gerada. O xml deve conter as seguintes informações para que ocorra a simulação: 
	 * Tempo de atendimento (mínimo e máximo), Tempo de Espera na Fila e Duração.
	 * 
	 * @param args recebe um vetor de String por parâmetro
	 */
	public static void main(String args[]) {

		try {

			File xmlFile = new File("okidoSimulationFile.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);

			doc.getDocumentElement().normalize();

			System.out.println("Begin : "
					+ doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("Simulation");

			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					int min = Integer.parseInt(eElement
							.getElementsByTagName("tempoAtendimentoMinimo")
							.item(0).getTextContent());
					int max = Integer.parseInt(eElement
							.getElementsByTagName("tempoAtendimentoMaximo")
							.item(0).getTextContent());
					int fila = Integer.parseInt(eElement
							.getElementsByTagName("tempoEsperaFila").item(0)
							.getTextContent());
					int duration = Integer.parseInt(eElement
							.getElementsByTagName("duracao").item(0)
							.getTextContent());

					SimuladorInterface sm = new SimulacaoAutoEscola(true);
					sm.simular(min, max, fila, duration);
					sm.imprimirResultados();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
