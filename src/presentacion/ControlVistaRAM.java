package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlVistaRAM implements ActionListener, ComponentListener, ChangeListener {

	private final VistaRAM ventanaRAM;
	private final Modelo modelo;

	public ControlVistaRAM(VistaRAM ventana) {
		this.ventanaRAM = ventana;
		this.modelo = ventanaRAM.getModelo();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() instanceof JButton) {
			JButton boton = (JButton) arg0.getSource();
			if (boton == ventanaRAM.getBtnAceptar()) {
				ventanaRAM.dispose();
				this.setMemoria();
				this.modelo.getVentanaGeneral().getBtnPlay().setEnabled(true);
			} else if (boton == ventanaRAM.getBtnBorrar()) {				
				for(int i =0;i<16;i++) {
	        		for(int j = 0;j<8;j++) {
	        			ventanaRAM.getRamButtons()[i][j].setText("0");
	        		}
	        	}
				getModelo().getSistema().restaurarRAM();
			} else if (boton == ventanaRAM.getBtnCargar()) {				
				Object[] possibilities = {"1 - Profesor Serrano", "2 - Profesor Oswaldo"};  
		        Integer i = (Integer) JOptionPane.showOptionDialog(null,   
		                null,  "ShowInputDialog",   
		                JOptionPane.PLAIN_MESSAGE, 1,  null, possibilities, 0);
		        if (i==0) {
		        	getModelo().cargarProgramaDefecto(1);
		        }else {
		        	getModelo().cargarProgramaDefecto(2);
		        }
		        int[][] aux = getModelo().getSistema().getRam().getDatos();
	        	for(int in =0;in<16;in++) {
	        		for(int j = 0;j<8;j++) {
	        			ventanaRAM.getRamButtons()[in][j].setText(String.valueOf(aux[in][j]));
	        		}
	        	}
				
			} else {				
				int x = 0;
				for(int in =0;in<16;in++) {
	        		for(int j = 0;j<8;j++) {
	        			if (boton == ventanaRAM.getRamButtons()[in][j]) {
	        				x = in;
	        				if(ventanaRAM.getRamButtons()[in][j].getText().equals("0")) {
	        					ventanaRAM.getRamButtons()[in][j].setText("1");
	        				}else {
	        					ventanaRAM.getRamButtons()[in][j].setText("0");
	        				}
	        			}
	        		}
	        	}
				//Conseguimos la línea de de la ram modificada
				int[] aux = new int[8];
				for(int i =0;i<8;i++) {
					aux[i] = Integer.valueOf(ventanaRAM.getRamButtons()[x][i].getText());
				}				
				getModelo().getSistema().setRegistroRAM(x, aux);				
				
			}
		} else {
		}

	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	public Modelo getModelo() {
		return modelo;
	}
	
	public void setMemoria() {		
		JButton[][] ramButtons = ventanaRAM.getRamButtons();
		int[][] memoria = new int[ramButtons.length][ramButtons[0].length];
		for(int i =0;i<ramButtons.length;i++) {
    		for(int j = 0;j<ramButtons[i].length;j++) {
    			memoria[i][j]= Integer.parseInt(ramButtons[i][j].getText());    			
    		}    		
		}
		//getModelo().setMemoria(memoria);		
		getModelo().getSistema().cargarProgramaRAM(memoria);		
	}
}
