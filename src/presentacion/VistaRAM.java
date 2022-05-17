package presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class VistaRAM extends JFrame {
				
	private ControlVistaRAM control;
    private final Modelo modelo;
    private JTable table;
    private JButton btnAceptar;
    private JButton btnBorrar;
    private JButton btnCargar;    
    private JButton[][] ramButtons;

	
	public VistaRAM(Modelo modelo) {		
		this.modelo = modelo;
        initComponents();
        capturaEventos();		
	}
	
	public void initComponents() {
		setBounds(600, 100, 600, 700);
		setTitle("RAM");
		this.setResizable(false);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		int x = 80;
		int y = 10;
		JLabel[] columnas = new JLabel[8];
		JLabel[] filas = new JLabel[16];
		
		for(int i=0;i<columnas.length;i++) {
			columnas[i] = new JLabel(String.valueOf(i));
			columnas[i].setBounds(x,y,50,30);
			columnas[i].setHorizontalAlignment(SwingConstants.CENTER);
			contentPane.add(columnas[i]);
			x+=55;
		}
		x=50;
		y=40;
		for(int i=0;i<filas.length;i++) {
			filas[i] = new JLabel(String.valueOf(i));
			filas[i].setBounds(x,y,50,30);
			contentPane.add(filas[i]);
			y+=35;
		}
		
		ramButtons = new JButton[16][8];
		x = 80;
		y = 40;
		int[][] aux = getModelo().getSistema().getRam().getDatos();
		for(int i=0;i<16;i++) {
			for(int j =0;j<8;j++) {
				ramButtons[i][j]  = new JButton(String.valueOf(aux[i][j]));
				ramButtons[i][j].setBounds(x,y,50,30);
				contentPane.add(ramButtons[i][j]);
				x+=55;
			}
			x = 80;
			y+=35;
		}
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(15, 620, 80, 23);
		contentPane.add(btnAceptar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(470, 620, 80, 23);
		contentPane.add(btnBorrar);
		
		btnCargar = new JButton("Cargar Programa");
		btnCargar.setBounds(205, 620, 160, 23);
		contentPane.add(btnCargar);
	}
	
	public ControlVistaRAM getControl() {
        if(control == null){
            control = new ControlVistaRAM(this);
        }
        return control;
    }
	
	private void capturaEventos() { 
    	for(int i =0;i<16;i++) {
    		for(int j = 0;j<8;j++) {
    			ramButtons[i][j].addActionListener(getControl());
    		}
    	}
        btnAceptar.addActionListener(getControl());
        btnBorrar.addActionListener(getControl());        
        btnCargar.addActionListener(getControl());           
    }

	/**
	 * @return the ramButtons
	 */
	public JButton[][] getRamButtons() {
		return ramButtons;
	}

	/**
	 * @param ramButtons the ramButtons to set
	 */
	public void setRamButtons(JButton[][] ramButtons) {
		this.ramButtons = ramButtons;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public JButton getBtnCargar() {
		return btnCargar;
	}
	
}
