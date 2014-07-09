package ProgramWindowsInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
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


import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;

import java.awt.Scrollbar;
import java.awt.ScrollPane;

import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTable;

import java.awt.SystemColor;
import java.awt.Button;


/**
 * Classe responsável pela execução do programa. 
 * 
 * @author Rodrigo Okido
 * @version 1.0
 */
public class SimulatorWindows extends JFrame
{
    
	/**
	 * Painel maior da Janela principal do programa.
	 */
	private JPanel contentPane;
	
	
	/**
	 * Painel do cabeçalho da interface. 
	 */
	private final JPanel Header = new JPanel();
	
	
	/**
	 * Painel das Estatísticas Avançadas da interface
	 */
	private final JPanel AdvPanel = new JPanel();
	
	
	/**
	 * Ação da JButton do programa.
	 */
	private final Action action = new SwingAction();
	
	
	/**
	 * Atributos para dar legendas, erros e qualquer informação ao usuário na tela.
	 */
    public static JLabel lblNoSimulationStarted ,errorOrMessagePopup ,Message,atendente1,atendente2, label_1, QueueNumber1,
    QueueNumber2,QueueNumber3;

    
    /**
     * Caixa de escolha para a simulação de uma Auto-Escola.
     */
    private JCheckBoxMenuItem aechoice;
    
    
    /**
     * Caixa de escolha para a simulação de um Supermercado.
     */
    private JCheckBoxMenuItem schoice;
    
    
    /**
     * Áreas onde serão exibidos os resultados da simulação gerada. 
     * ReportBasic e ReportAdvanced exibirá as informações básicas e 
     * avançadas respectivamente da simulação, e a SimulationPage todo
     * o progresso e eventos ocorridos nela.
     */
    public static JTextArea ReportBasic,ReportAdvanced, SimulationPage ;
    
    
    /**
     * Botões onde indicam as filas, e o botão principal que faz a execução do programa.
     */
    public static JButton primeiro,segundo,terceiro,primeiroF2,segundoF2,terceiroF2, startSimulationButton ;
    
    
    /**
     * Informações necessárias para iniciar a execução do programa. Possui um 
     * tamanho mínimo e máximo de atendimento, um Tempo de espera na fila e 
     * a duração que ocorrerá a simulação desejada.
     */
    private static int minS, maxS, minAE,maxAE, fila,duration;
    

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
							minS = Integer.parseInt(eElement
									.getElementsByTagName("tempoAtendimentoMinimoS")
									.item(0).getTextContent());
							maxS = Integer.parseInt(eElement
									.getElementsByTagName("tempoAtendimentoMaximoS")
									.item(0).getTextContent());
							minAE = Integer.parseInt(eElement
									.getElementsByTagName("tempoAtendimentoMinimoAE")
									.item(0).getTextContent());
							maxAE = Integer.parseInt(eElement
									.getElementsByTagName("tempoAtendimentoMaximoAE")
									.item(0).getTextContent());
							fila = Integer.parseInt(eElement
									.getElementsByTagName("tempoEsperaFila").item(0)
									.getTextContent());
							duration = Integer.parseInt(eElement
									.getElementsByTagName("duracao").item(0)
									.getTextContent());

							SimuladorInterface sm = new SimulacaoSupermercado(true);
							sm.simular(minS, maxS, fila, duration);
						
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static boolean keyPressed(int x) {
		if (x == KeyEvent.VK_ENTER)
              return true;
		else
			return false;
	}
	
	    
	

	/**
	 * Cria todo a Interface do programa.
	 */
	public SimulatorWindows() {
		// CRIAÇÃO DE TODA A INTERFACE
		
		setTitle("OkidoDiscreteSimulator 1.0 build 070714 Final");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 720);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel BscPanel = new JPanel();
		BscPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		BscPanel.setBounds(10, 343, 430, 295);
		contentPane.add(BscPanel);
		BscPanel.setLayout(null);
		
		JScrollPane scrollpaneReportBasic = new JScrollPane(ReportBasic);
		scrollpaneReportBasic.setBounds(10, 10, 410, 274);
		scrollpaneReportBasic.setPreferredSize(new Dimension(200,200));
		BscPanel.add(scrollpaneReportBasic);
		
		ReportBasic = new JTextArea();
		scrollpaneReportBasic.setViewportView(ReportBasic);
		ReportBasic.setEditable(false);
		Header.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		Header.setBounds(10, 11, 864, 222);
		contentPane.add(Header);
		Header.setLayout(null);
		
		JScrollPane scrollPaneSimulation = new JScrollPane();
		scrollPaneSimulation.setBounds(10, 11, 547, 200);
		Header.add(scrollPaneSimulation);
		
		SimulationPage = new JTextArea();
		scrollPaneSimulation.setViewportView(SimulationPage);
		SimulationPage.setEditable(false);
		

		terceiro = new JButton("");
		terceiro.setBackground(Color.BLACK);
		terceiro.setVisible(false);
		terceiro.setBounds(639, 141, 52, 23);
		Header.add(terceiro);
		
		segundo = new JButton("");
		segundo.setBackground(Color.BLACK);
		segundo.setVisible(false);
		segundo.setBounds(639, 107, 52, 23);
		Header.add(segundo);
		
		primeiro = new JButton("");
		primeiro.setBackground(Color.BLACK);
		primeiro.setVisible(false);
		primeiro.setBounds(639, 73, 52, 23);
		Header.add(primeiro);
		
		primeiroF2 = new JButton("");
		primeiroF2.setBackground(Color.BLACK);
		primeiroF2.setVisible(false);
		primeiroF2.setBounds(742, 73, 52, 23);
		Header.add(primeiroF2);
		
		segundoF2 = new JButton("");
		segundoF2.setBackground(Color.BLACK);
		segundoF2.setVisible(false);
		segundoF2.setBounds(742, 107, 52, 23);
		Header.add(segundoF2);
		
		terceiroF2 = new JButton("");
		terceiroF2.setBackground(Color.BLACK);
		terceiroF2.setVisible(false);
		terceiroF2.setBounds(742, 141, 52, 23);
		Header.add(terceiroF2);
		
		
		//
		//
		
		atendente1 = new JLabel("Atendente 1 (Fila 1)");
		atendente1.setVisible(false);
		atendente1.setBounds(595, 48, 127, 14);
		Header.add(atendente1);
		
		atendente2 = new JLabel("Atendente 2 (Fila 2)");
		atendente2.setVisible(false);
		atendente2.setBounds(721, 48, 122, 14);
		Header.add(atendente2);
		
		JLabel lblSimulationStatus = new JLabel("SIMULATION STATUS");
		lblSimulationStatus.setForeground(SystemColor.textHighlight);
		lblSimulationStatus.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSimulationStatus.setBounds(619, 15, 194, 14);
		Header.add(lblSimulationStatus);
		
		
		//LEGENDAS DA SIMULAÇÃO 
		
		JButton btnNewButton_7 = new JButton("");
		btnNewButton_7.setBackground(Color.RED);
		btnNewButton_7.setForeground(SystemColor.textHighlight);
		btnNewButton_7.setBounds(575, 197, 22, 14);
		Header.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("");
		btnNewButton_8.setBackground(Color.GREEN);
		btnNewButton_8.setBounds(575, 182, 22, 14);
		Header.add(btnNewButton_8);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setBounds(708, 197, 22, 14);
		Header.add(btnNewButton);
		
		JLabel lblSemCliente = new JLabel("Sem cliente");
		lblSemCliente.setBounds(742, 197, 101, 14);
		Header.add(lblSemCliente);
	
		
		JLabel lblSendoAtendido = new JLabel("Sendo Atendido");
		lblSendoAtendido.setBounds(603, 182, 140, 14);
		Header.add(lblSendoAtendido);
		
		JLabel lblEmEspera = new JLabel("Em espera");
		lblEmEspera.setBounds(603, 197, 71, 14);
		Header.add(lblEmEspera);
		
		QueueNumber1 = new JLabel("1 -");
		QueueNumber1.setVisible(false);
		QueueNumber1.setBounds(589, 73, 40, 14);
		Header.add(QueueNumber1);
		
		QueueNumber2 = new JLabel("2 -");
		QueueNumber2.setVisible(false);
		QueueNumber2.setBounds(589, 107, 40, 14);
		Header.add(QueueNumber2);
		
		QueueNumber3 = new JLabel("3 -");
		QueueNumber3.setVisible(false);
		QueueNumber3.setBounds(589, 141, 40, 14);
		Header.add(QueueNumber3);
		
		label_1 = new JLabel("......");
		label_1.setVisible(false);
		label_1.setBounds(710, 161, 46, 14);
		Header.add(label_1);
		
		lblNoSimulationStarted = new JLabel("NO SIMULATION STARTED YET");
		lblNoSimulationStarted.setForeground(Color.RED);
		lblNoSimulationStarted.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNoSimulationStarted.setBounds(619, 107, 212, 14);
		Header.add(lblNoSimulationStarted);
		
		
		
		
		AdvPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		AdvPanel.setBounds(450, 343, 424, 295);
		contentPane.add(AdvPanel);
		AdvPanel.setLayout(null);
		
		
		//
		//
		
		JScrollPane scrollPaneReportAdvanced = new JScrollPane();
		scrollPaneReportAdvanced.setBounds(10, 11, 404, 273);
		AdvPanel.add(scrollPaneReportAdvanced);
		
		ReportAdvanced = new JTextArea();
		ReportAdvanced.setText("");
		ReportAdvanced.setEditable(false);
		scrollPaneReportAdvanced.setViewportView(ReportAdvanced);
		
		
		JLabel lblRelatorio = new JLabel("<html><b>> Report & Statistic ( Relat\u00F3rio & Estat\u00EDstica )</b>");
		lblRelatorio.setBounds(10, 318, 294, 14);
		contentPane.add(lblRelatorio);
		
		JLabel lblResultados = new JLabel("<html><b>>> RESULTS ( RESULTADOS )</b>");
		lblResultados.setBounds(10, 293, 171, 14);
		contentPane.add(lblResultados);
		
		JLabel lblAdvancedStatistic = new JLabel("<html><b>> Advanced Statistic ( Estat\u00EDstica Avan\u00E7ada )</b>");
		lblAdvancedStatistic.setBounds(450, 318, 294, 14);
		contentPane.add(lblAdvancedStatistic);
		
		JLabel lblSimulation = new JLabel(">>> Simulation");
		lblSimulation.setForeground(new Color(0, 191, 255));
		lblSimulation.setFont(new Font("Verdana", Font.BOLD, 16));
		lblSimulation.setBounds(10, 244, 171, 14);
		contentPane.add(lblSimulation);
		
		startSimulationButton = new JButton("Begin Simulation");
		startSimulationButton.setAction(action);
		startSimulationButton.setBounds(664, 244, 210, 23);
		contentPane.add(startSimulationButton);
		
		aechoice = new JCheckBoxMenuItem("Auto-Escola");
		aechoice.setBounds(525, 236, 129, 22);
		contentPane.add(aechoice);
		
		schoice = new JCheckBoxMenuItem("Supermercado");
		schoice.setBounds(382, 236, 129, 22);
		contentPane.add(schoice);
		
		JLabel lblChooseToBegin = new JLabel("Choose to begin:");
		lblChooseToBegin.setBounds(271, 244, 101, 14);
		contentPane.add(lblChooseToBegin);
		
		errorOrMessagePopup = new JLabel("");
		errorOrMessagePopup.setBounds(292, 269, 265, 23);
		contentPane.add(errorOrMessagePopup);
		
		Message = new JLabel("Read \"LEIAME\" to see how make a different Simulation (Optional)");
		Message.setFont(new Font("Tahoma", Font.BOLD, 11));
		Message.setForeground(new Color(255, 51, 0));
		Message.setBounds(302, 293, 442, 14);
		contentPane.add(Message);
	}
	
	
	/**
	 * Classe interna para execução das ações do programa. Todo e quaquer clique
	 * feito pelo usuário, a resposta vem desta classe.
	 */
	private class SwingAction extends AbstractAction {
	
	    
		/**
		 * Criação de uma SwingAction, isto é, gera uma resposta a ação do usuário.
		 */
		public SwingAction() {
			putValue(NAME, "Begin Simulation");
			putValue(SHORT_DESCRIPTION, "Begin the simulation according with the establishment chosen by you");
		}
		
		
		/**
		 * Método responsável pela execução da ação pedida pelo usuário.
		 * 
		 * @param Recebe uma ActionEvent por parâmetro
		 */
		public void actionPerformed(ActionEvent e) {
			
			if ((schoice.isSelected() && (aechoice.isSelected()))){
				SimulationPage.setText("");
						ReportBasic.setText("");
						ReportAdvanced.setText("");
				errorOrMessagePopup.setText("<html><b><font color = \"red\">JUST PICK ONE TO BEGIN A SIMULATION.</font></b>");				
			
			}
			
			if (!schoice.isSelected() && !aechoice.isSelected()){
				
				//HIDING INTERFACE SIMULATION STATUS
				lblNoSimulationStarted.setText("NO SIMULATION STARTED YET");
				Message.setText("");
				atendente1.setVisible(false);
				atendente2.setVisible(false);
				primeiro.setVisible(false);
				segundo.setVisible(false);
				terceiro.setVisible(false);
				atendente2.setVisible(false);
				primeiroF2.setVisible(false);
				segundoF2.setVisible(false);
				terceiroF2.setVisible(false);
				QueueNumber1.setVisible(false);
				QueueNumber2.setVisible(false);
				QueueNumber3.setVisible(false);
				label_1.setVisible(false);
				//
				//
				SimulationPage.setText("");
				ReportBasic.setText("");
				ReportAdvanced.setText("");
				errorOrMessagePopup.setText("<html><b><font color = \"red\">CHOOSE A ESTABLISHMENT TO BEGIN.</font></b>");				
			}
			
			else{
			
			if (schoice.isSelected()){
				
				SimuladorInterface sm = new SimulacaoSupermercado(true);
				
				//SHOWING INTERFACE SIMULATION STATUS
				lblNoSimulationStarted.setText("");
				atendente1.setVisible(true);
				primeiro.setVisible(true);
				segundo.setVisible(true);
				terceiro.setVisible(true);
				atendente2.setVisible(false);
				primeiroF2.setVisible(false);
				segundoF2.setVisible(false);
				terceiroF2.setVisible(false);
				QueueNumber1.setVisible(true);
				QueueNumber2.setVisible(true);
				QueueNumber3.setVisible(true);
				label_1.setVisible(true);
				//
				//
				
				Message.setText("");
				SimulationPage.setText("");
				ReportBasic.setText("");
				ReportAdvanced.setText("");
				SimulationPage.setText(sm.simular(minS, maxS, fila, duration));
				ReportBasic.setText(sm.imprimirResultados());
				ReportAdvanced.setText(sm.imprimirEstatisticasAvancadas());
				errorOrMessagePopup.setText("<html><b><font color = \"green\">SIMULATION FINISHED!</font></b>");
			}
			
			if (aechoice.isSelected()){

				SimuladorInterface sm = new SimulacaoAutoEscola(true);
				
				//SHOWING INTERFACE SIMULATION STATUS
				lblNoSimulationStarted.setText("");
				atendente1.setVisible(true);
				atendente2.setVisible(true);
				primeiro.setVisible(true);
				segundo.setVisible(true);
				terceiro.setVisible(true);
				atendente2.setVisible(true);
				primeiroF2.setVisible(true);
				segundoF2.setVisible(true);
				terceiroF2.setVisible(true);
				QueueNumber1.setVisible(true);
				QueueNumber2.setVisible(true);
				QueueNumber3.setVisible(true);
				label_1.setVisible(true);
				//
				//
				
		
				Message.setText("");
				SimulationPage.setText("");
				ReportBasic.setText("");
				ReportAdvanced.setText("");
				SimulationPage.setText(sm.simular(minAE, maxAE, fila, duration));
				ReportBasic.setText(sm.imprimirResultados());
				ReportAdvanced.setText(sm.imprimirEstatisticasAvancadas());
				errorOrMessagePopup.setText("<html><b><font color = \"green\">SIMULATION FINISHED!</font></b>");
			}
			}
		}
	}
	}
