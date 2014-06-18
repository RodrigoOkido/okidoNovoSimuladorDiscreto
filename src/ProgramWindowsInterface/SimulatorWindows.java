package ProgramWindowsInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JSplitPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;

public class SimulatorWindows extends JFrame {

	private JPanel contentPane;
	private final JPanel panel_1 = new JPanel();
	private final JPanel panel_2 = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimulatorWindows frame = new SimulatorWindows();
					frame.setVisible(true);
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
		setBounds(100, 100, 697, 657);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 331, 326, 254);
		contentPane.add(panel);
		panel.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel_1.setBounds(10, 11, 659, 222);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		panel_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(358, 331, 311, 254);
		contentPane.add(panel_2);
		
		
		JLabel lblRelatorio = new JLabel("<html><b>> Report & Statistic ( Relat\u00F3rio & Estat\u00EDstica )</b>");
		lblRelatorio.setBounds(10, 306, 294, 14);
		contentPane.add(lblRelatorio);
		
		JLabel lblResultados = new JLabel("<html><b>>> RESULTS ( RESULTADOS )</b>");
		lblResultados.setBounds(10, 281, 171, 14);
		contentPane.add(lblResultados);
		
		JLabel lblAdvancedStatistic = new JLabel("<html><b>> Advanced Statistic ( Estat\u00EDstica Avan\u00E7ada )</b>");
		lblAdvancedStatistic.setBounds(358, 306, 294, 14);
		contentPane.add(lblAdvancedStatistic);
		
		JLabel lblSimulation = new JLabel("<html><b><font color = \"red\">Simulation</font></b>");
		lblSimulation.setFont(new Font("Verdana", Font.BOLD, 16));
		lblSimulation.setBounds(10, 244, 171, 14);
		contentPane.add(lblSimulation);
		
		JButton btnNewButton = new JButton("Begin Simulation");
		btnNewButton.setBounds(492, 244, 177, 23);
		contentPane.add(btnNewButton);
	}
}
