package presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JSlider;

public class VistaGeneral extends JFrame {

	/**
	 * Este atributo establece la comunicación con el controlador correspondiente.
	 */
	private ControlVistaGeneral control;

	/**
	 * Este atributo establece la comunicación con el modelo.
	 */
	private final Modelo modelo;

	// atributos donde se almacenan el estado de los componenteses
	private JLabel lblPC;
	private JLabel lblMAR;
	private JButton btnRAM;
	private JLabel lblRI;
	private JLabel lblCS;
	private JLabel lblAcumulador;
	private JLabel lblRegistroB;
	private JLabel lblALU;
	private JLabel lblOUT;
	private JSlider slider;
	private JButton btnPausar;
	private JButton btnPlay;
	private JButton btnReiniciar;


	/** Creates new form CarritoAnimacion */
	public VistaGeneral(Modelo aThis) {
		modelo = aThis;
		initComponents();
		capturaEventos();
	}

	public ControlVistaGeneral getControl() {
		if (control == null) {
			control = new ControlVistaGeneral(this);
		}
		return control;
	}

	public Modelo getModelo() {
		return this.modelo;
	}

	/**
	 * Create the frame.
	 */
	public void initComponents() {
		this.setTitle("Vista General");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBounds(100, 100, 495, 455);
		

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);

		lblPC = new JLabel("0 0 0 0");
		lblPC.setBorder(UIManager.getBorder("Tree.editorBorder"));
		lblPC.setHorizontalAlignment(SwingConstants.CENTER);
		lblPC.setBounds(45, 15, 125, 45);

		lblMAR = new JLabel("0 0 0 0");
		lblMAR.setBorder(UIManager.getBorder("Tree.editorBorder"));
		lblMAR.setHorizontalAlignment(SwingConstants.CENTER);
		lblMAR.setBounds(45, 80, 125, 45);

		btnRAM = new JButton("0 0 0 0 0 0 0 0");
		btnRAM.setBorder(UIManager.getBorder("Tree.editorBorder"));
		btnRAM.setBounds(45, 145, 125, 45);

		lblRI = new JLabel("0 0 0 0 0 0 0 0");
		lblRI.setBorder(UIManager.getBorder("Tree.editorBorder"));
		lblRI.setHorizontalAlignment(SwingConstants.CENTER);
		lblRI.setBounds(45, 210, 125, 45);

		lblCS = new JLabel("INSTRUCCION");
		lblCS.setBorder(UIManager.getBorder("Tree.editorBorder"));
		lblCS.setHorizontalAlignment(SwingConstants.CENTER);
		lblCS.setBounds(45, 275, 125, 45);

		lblAcumulador = new JLabel("0 0 0 0 0 0 0 0");
		lblAcumulador.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcumulador.setBorder(UIManager.getBorder("Tree.editorBorder"));
		lblAcumulador.setBounds(310, 15, 125, 45);

		lblALU = new JLabel("000 + 000 = 000");
		lblALU.setHorizontalAlignment(SwingConstants.CENTER);
		lblALU.setBorder(UIManager.getBorder("Tree.editorBorder"));
		lblALU.setBounds(310, 80, 125, 45);

		lblRegistroB = new JLabel("0 0 0 0 0 0 0 0 0");
		lblRegistroB.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroB.setBorder(UIManager.getBorder("Tree.editorBorder"));
		lblRegistroB.setBounds(310, 145, 125, 45);

		lblOUT = new JLabel("OUT: 000");
		lblOUT.setHorizontalAlignment(SwingConstants.CENTER);
		lblOUT.setBorder(UIManager.getBorder("Tree.editorBorder"));
		lblOUT.setBounds(310, 210, 125, 45);

		slider = new JSlider();
		slider.setBounds(35, 364, 222, 26);

		btnPausar = new JButton("||");
		btnPausar.setBounds(335, 364, 45, 35);
		btnPausar.setEnabled(false);

		btnPlay = new JButton(">");
		btnPlay.setBounds(280, 364, 45, 35);
		btnPlay.setEnabled(false);
		
		btnReiniciar = new JButton("X");
		btnReiniciar.setBounds(390, 364, 45, 35);
		btnReiniciar.setEnabled(false);
		
		JLabel lblPC1 = new JLabel("PC");
		lblPC1.setBounds(10, 30, 34, 14);
		contentPane.add(lblPC1);
		
		JLabel lblMAR1 = new JLabel("MAR");
		lblMAR1.setBounds(10, 95, 34, 14);
		contentPane.add(lblMAR1);
		
		JLabel lblRAM1 = new JLabel("RAM");
		lblRAM1.setBounds(10, 160, 34, 14);
		contentPane.add(lblRAM1);
		
		JLabel lblRI1 = new JLabel("RI");
		lblRI1.setBounds(10, 225, 34, 14);
		contentPane.add(lblRI1);
		
		JLabel lblCS1 = new JLabel("CS");
		lblCS1.setBounds(10, 290, 34, 14);
		contentPane.add(lblCS1);
		
		JLabel lblPA1 = new JLabel("A");
		lblPA1.setBounds(445, 30, 34, 14);
		contentPane.add(lblPA1);
		
		JLabel lblALU1 = new JLabel("ALU");
		lblALU1.setBounds(445, 95, 34, 14);
		contentPane.add(lblALU1);
		
		JLabel lblB = new JLabel("B");
		lblB.setBounds(445, 160, 34, 14);
		contentPane.add(lblB);
		
		JLabel lblOUT1 = new JLabel("OUT");
		lblOUT1.setBounds(445, 225, 34, 14);
		contentPane.add(lblOUT1);

		contentPane.setLayout(null);
		contentPane.add(lblRI);
		contentPane.add(lblPC);
		contentPane.add(lblMAR);
		contentPane.add(btnRAM);
		contentPane.add(lblRI);
		contentPane.add(lblCS);
		contentPane.add(lblAcumulador);
		contentPane.add(lblALU);
		contentPane.add(lblRegistroB);
		contentPane.add(lblOUT);
		contentPane.add(btnPlay);
		contentPane.add(btnPausar);
		contentPane.add(btnReiniciar);
		contentPane.add(slider);
	}

	public void capturaEventos() {
		btnPlay.addActionListener(getControl());
		btnPausar.addActionListener(getControl());
		btnReiniciar.addActionListener(getControl());
		btnRAM.addActionListener(getControl());
		slider.addChangeListener(getControl());
		this.addComponentListener(getControl());
	}

	public JLabel getLblPC() {
		return lblPC;
	}

	public void setLblPC(JLabel lblPC) {
		this.lblPC = lblPC;
	}

	public JLabel getLblMAR() {
		return lblMAR;
	}

	public void setLblMAR(JLabel lblMAR) {
		this.lblMAR = lblMAR;
	}

	public JButton getBtnRAM() {
		return btnRAM;
	}

	public void setBtnRAM(JButton btnRAM) {
		this.btnRAM = btnRAM;
	}

	public JLabel getLblRI() {
		return lblRI;
	}

	public void setLblRI(JLabel lblRI) {
		this.lblRI = lblRI;
	}

	public JLabel getLblCS() {
		return lblCS;
	}

	public void setLblCS(JLabel lblCS) {
		this.lblCS = lblCS;
	}

	public JLabel getLblAcumulador() {
		return lblAcumulador;
	}

	public void setLblAcumulador(JLabel lblAcumulador) {
		this.lblAcumulador = lblAcumulador;
	}

	public JLabel getLblRegistroB() {
		return lblRegistroB;
	}

	public void setLblRegistroB(JLabel lblRegistroB) {
		this.lblRegistroB = lblRegistroB;
	}

	public JLabel getLblALU() {
		return lblALU;
	}

	public void setLblALU(JLabel lblALU) {
		this.lblALU = lblALU;
	}

	public JLabel getLblOUT() {
		return lblOUT;
	}

	public void setLblOUT(JLabel lblOUT) {
		this.lblOUT = lblOUT;
	}

	public JSlider getSlider() {
		return slider;
	}

	public void setSlider(JSlider slider) {
		this.slider = slider;
	}

	public JButton getBtnPausar() {
		return btnPausar;
	}

	public void setBtnPausar(JButton btnPausar) {
		this.btnPausar = btnPausar;
	}

	public JButton getBtnPlay() {
		return btnPlay;
	}

	public void setBtnPlay(JButton btnPlay) {
		this.btnPlay = btnPlay;
	}

	public JButton getBtnReiniciar() {
		return btnReiniciar;
	}

	public void setBtnReiniciar(JButton btnReiniciar) {
		this.btnReiniciar = btnReiniciar;
	}

}