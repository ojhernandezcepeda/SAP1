package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlVistaGeneral implements ActionListener, ComponentListener, ChangeListener {

	private final VistaGeneral ventanaGeneral;
	private final Modelo modelo;

	public ControlVistaGeneral(VistaGeneral ventana) {
		this.ventanaGeneral = ventana;
		modelo = ventana.getModelo();
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

	@Override
	public void actionPerformed(ActionEvent arg0) {		
		if (arg0.getSource() instanceof JButton) {
			JButton boton = (JButton) arg0.getSource();
			if (boton == ventanaGeneral.getBtnPlay()) {
				getModelo().iniciarAnimacion();
			} else if (boton == ventanaGeneral.getBtnReiniciar()) {
				try {					
					getModelo().reiniciarAnimacion();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (boton == ventanaGeneral.getBtnPausar()) {
				try {
					getModelo().detenerAnimacion();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				getModelo().modificarRAM();
			}
		} else {
		}

	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		getModelo().controlarVelocidad();

	}

	public Modelo getModelo() {
		return modelo;
	}
}
