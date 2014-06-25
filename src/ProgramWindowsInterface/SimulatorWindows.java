package ProgramWindowsInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JSplitPane;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.FlowLayout;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.io.File;

import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import simulator.SimulacaoAutoEscola;
import simulator.SimulacaoSupermercado;
import ProgramInterfaces.SimuladorInterface;

import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;

import java.awt.Scrollbar;
import java.awt.ScrollPane;

import javax.swing.JScrollBar;
import javax.swing.JTextArea;


/**
 * Classe responsável pela execução do programa. 
 * 
 * @author Rodrigo Okido
 * @version 1.0
 */
public class SimulatorWindows extends JFrame {

	private JPanel contentPane;
	private final JPanel panel_1 = new JPanel();
	private final JPanel panel_2 = new JPanel();
	private final Action action = new SwingAction();
    private JLabel lblSimulationView;
    private JLabel errorWarning;

    private JCheckBoxMenuItem aechoice;
    private JCheckBoxMenuItem schoice;
    
    private JTextArea ReportBasic,ReportAdvanced ;
    private static int min, max, fila,duration;

	/**
	 * Método responsável pela execução do programa. Executa fazendo a leitura 
	 * de dados de um arquivo xml, e a partir da leitura desses dados, a simulação
	 * é gerada. O xml deve conter as seguintes informações para que ocorra a simulação: 
	 * Tempo de atendimento (mínimo e máximo), Tempo de Espera na Fila e Duração.
	 * 
	 * @param args recebe um vetor de String por parâmetro
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimulatorWindows frame = new SimulatorWindows();
					frame.setVisible(true);
					
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
							min = Integer.parseInt(eElement
									.getElementsByTagName("tempoAtendimentoMinimo")
									.item(0).getTextContent());
							max = Integer.parseInt(eElement
									.getElementsByTagName("tempoAtendimentoMaximo")
									.item(0).getTextContent());
							fila = Integer.parseInt(eElement
									.getElementsByTagName("tempoEsperaFila").item(0)
									.getTextContent());
							duration = Integer.parseInt(eElement
									.getElementsByTagName("duracao").item(0)
									.getTextContent());

							SimuladorInterface sm = new SimulacaoSupermercado(true);
							sm.simular(min, max, fila, duration);
						
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SimulatorWindows() {
		setTitle("OkidoDiscreteSimulator 1.0 build 180614 Beta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 720);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 343, 430, 295);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollpane = new JScrollPane(ReportBasic);
		scrollpane.setBounds(403, 10, 17, 274);
		panel.add(scrollpane);
		
		JScrollBar scrollBar_1 = new JScrollBar();
		scrollpane.setViewportView(scrollBar_1);
		
	    ReportBasic = new JTextArea();
		ReportBasic.setBounds(10, 10, 389, 274);
		panel.add(ReportBasic);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 11, 864, 222);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblSimulationView = new JLabel("");
		lblSimulationView.setBounds(10, 11, 528, 200);
		panel_1.add(lblSimulationView);
		panel_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(450, 343, 424, 295);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
	    ReportAdvanced = new JTextArea();
		ReportAdvanced.setBounds(10, 11, 389, 273);
		panel_2.add(ReportAdvanced);
		
		
		JLabel lblRelatorio = new JLabel("<html><b>> Report & Statistic ( Relat\u00F3rio & Estat\u00EDstica )</b>");
		lblRelatorio.setBounds(10, 318, 294, 14);
		contentPane.add(lblRelatorio);
		
		JLabel lblResultados = new JLabel("<html><b>>> RESULTS ( RESULTADOS )</b>");
		lblResultados.setBounds(10, 293, 171, 14);
		contentPane.add(lblResultados);
		
		JLabel lblAdvancedStatistic = new JLabel("<html><b>> Advanced Statistic ( Estat\u00EDstica Avan\u00E7ada )</b>");
		lblAdvancedStatistic.setBounds(450, 318, 294, 14);
		contentPane.add(lblAdvancedStatistic);
		
		JLabel lblSimulation = new JLabel("<html><b><font color = \"red\">>> Simulation</font></b>");
		lblSimulation.setFont(new Font("Verdana", Font.BOLD, 16));
		lblSimulation.setBounds(10, 244, 171, 14);
		contentPane.add(lblSimulation);
		
		JButton btnNewButton = new JButton("Begin Simulation");
		btnNewButton.setAction(action);
		btnNewButton.setBounds(697, 244, 177, 23);
		contentPane.add(btnNewButton);
		
		aechoice = new JCheckBoxMenuItem("Auto-Escola");
		aechoice.setBounds(429, 236, 129, 22);
		contentPane.add(aechoice);
		
		schoice = new JCheckBoxMenuItem("Supermercado");
		schoice.setBounds(259, 236, 129, 22);
		contentPane.add(schoice);
		JLabel lblChooseToBegin = new JLabel("Choose to begin:");
		lblChooseToBegin.setBounds(160, 244, 101, 14);
		contentPane.add(lblChooseToBegin);
		
		errorWarning = new JLabel("");
		errorWarning.setBounds(292, 269, 286, 23);
		contentPane.add(errorWarning);
		

		

	}
	private class SwingAction extends AbstractAction {
	
	
		public SwingAction() {
			putValue(NAME, "Begin Simulation");
			putValue(SHORT_DESCRIPTION, "Begin the simulation according with the establishment chosen by you");
		}
		public void actionPerformed(ActionEvent e) {
			if (schoice.isSelected()){
					if(aechoice.isSelected()){
						ReportBasic.setText("");
						ReportAdvanced.setText("");
				errorWarning.setText("<html><b><font color = \"red\">ITS NOT POSSIBLE TO BEGIN A SIMULATION WITH THE TWO OPTIONS MARKED.</font></b>");				
			}
			}
			
			if (!schoice.isSelected() && !aechoice.isSelected()){
				ReportBasic.setText("");
				ReportAdvanced.setText("");
				errorWarning.setText("<html><b><font color = \"red\">CHOOSE A ESTABLISHMENT TO BEGIN.</font></b>");				
			}
			
			else{
			
			if (schoice.isSelected()){
				errorWarning.setText("");
				SimuladorInterface sm = new SimulacaoSupermercado(true);
				sm.simular(min, max, fila, duration);
				ReportBasic.setText("");
				ReportAdvanced.setText("");
				ReportBasic.setText(sm.imprimirResultados());
				ReportAdvanced.setText(sm.imprimirEstatisticasAvancadas());
			
			}
			if (aechoice.isSelected()){
				errorWarning.setText("");
				SimuladorInterface sm = new SimulacaoAutoEscola(true);
				sm.simular(min, max, fila, duration);
				ReportBasic.setText("");
				ReportAdvanced.setText("");
				ReportBasic.setText(sm.imprimirResultados());
				ReportAdvanced.setText(sm.imprimirEstatisticasAvancadas());
			}
			}
		}
	}
}
