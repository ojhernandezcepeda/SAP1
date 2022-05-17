package presentacion;

import logica.Microprocesador;

public class Modelo implements Runnable {

	// Atributos
	private boolean animando;
	private Microprocesador sistema;
	private VistaGeneral ventanaGeneral;
	private VistaRAM ventanaRAM;
	private Thread hiloDibujo;

	// Metodos ocultacion de informacion
	public Microprocesador getSistema() {
		if (sistema == null) {
			sistema = new Microprocesador();
		}
		return sistema;
	}
	
	public void reiniciarSistema() {		
		getVentanaGeneral().getSlider().setMinimum(10);
		getVentanaGeneral().getSlider().setMaximum(100);		
		getVentanaGeneral().getLblPC().setText("0 0 0 0");			
		getVentanaGeneral().getLblMAR().setText("0 0 0 0");
		getVentanaGeneral().getBtnRAM().setText("0 0 0 0 0 0 0 0");
		getVentanaGeneral().getLblRI().setText("0 0 0 0 0 0 0 0");
		getVentanaGeneral().getLblCS().setText("");
		getVentanaGeneral().getLblAcumulador().setText("0 0 0 0 0 0 0 0");
		getVentanaGeneral().getLblALU().setText("");
		getVentanaGeneral().getLblRegistroB().setText("0 0 0 0 0 0 0 0");
		getVentanaGeneral().getLblOUT().setText("");		
		sistema = null;	
	}

	public VistaGeneral getVentanaGeneral() {
		if (ventanaGeneral == null) {
			ventanaGeneral = new VistaGeneral(this);
		}
		return ventanaGeneral;
	}
	
	public VistaRAM getVentanaRAM() {
		if (ventanaRAM == null) {
			ventanaRAM = new VistaRAM(this);
		}
		return ventanaRAM;
	}

	// M�todos Punto de vista funcional
	void crearNuevoTablero() {
		if (!isAnimando()) {
			sistema = null; // Liberar memoria para este objeto
			System.gc(); // Invocar al recolector de basura sin espera
			animando = false;
		}
	}

	public void iniciar() {
		crearNuevoTablero();
		getVentanaGeneral().setVisible(true);
	}

	public void iniciarAnimacion() {
		setVelocidad(110 - getVentanaGeneral().getSlider().getValue());		
		getVentanaGeneral().getBtnPlay().setEnabled(false);
		getVentanaGeneral().getBtnPausar().setEnabled(true);
		if(hiloDibujo == null) {
			hiloDibujo = new Thread(this);
			hiloDibujo.start();
		}else {
			hiloDibujo.resume();			
		}
				
	}
	
	public void detenerAnimacion() throws InterruptedException {
		getVentanaGeneral().getBtnPlay().setEnabled(true);
		getVentanaGeneral().getBtnPausar().setEnabled(false);
		getVentanaGeneral().getBtnReiniciar().setEnabled(true);
		getVentanaGeneral().getBtnRAM().setEnabled(false);
		animando = false;
		hiloDibujo.suspend();
		System.gc();
	}
	
	public void reiniciarAnimacion() throws InterruptedException {		
		getVentanaGeneral().getBtnPlay().setEnabled(false);
		getVentanaGeneral().getBtnPausar().setEnabled(false);
		getVentanaGeneral().getBtnReiniciar().setEnabled(false);
		getVentanaGeneral().getBtnRAM().setEnabled(true);
		this.reiniciarSistema();
        animando = false; 
        hiloDibujo = null;        
		System.gc();
	}

	// Metodos correspondientes a la logica de presentacion
	private void animar() throws Exception {
		animando = true;		
		String palabra = "";		
		getSistema();				
		getVentanaGeneral().getBtnRAM().setEnabled(false);
		while (!palabra.equals("HLT")) {
			palabra = ciclo();
		}		
		
	}

	public boolean isAnimando() {
		return animando;
	}

	/**
	 * Pausa el hilo
	 * 
	 * @param tiempo milisegundos
	 */
	public void esperar(int tiempo) {
		try {
			Thread.sleep(tiempo);
		} catch (InterruptedException ex) {
		}
	}

	@Override
	public void run() {
		try {			
			animar();
		} catch (Exception ex) {
			// mensaje de error
		}
		getVentanaGeneral().getBtnPlay().setEnabled(false);
		getVentanaGeneral().getBtnPausar().setEnabled(false);
		getVentanaGeneral().getBtnReiniciar().setEnabled(true);
		getVentanaGeneral().getBtnRAM().setEnabled(false);
		
	}

	/**
	 * Establece la velocidad del sistema
	 * 
	 * @param i constante
	 */
	public void setVelocidad(int i) {
		getSistema().setVelocidad(i);
	}

	public int getVelocidad() {
		return this.getSistema().getVelocidad();
	}
	/**
	 * Controla la velocidad de la animacion con el slider de la vista general
	 */
	public void controlarVelocidad() {
		setVelocidad(110 - getVentanaGeneral().getSlider().getValue());
	}

	public void cargarProgramaDefecto(int programa) {		
		getSistema();		
		this.sistema.cargarProgramaDefecto(programa);
	}

	public String ciclo() {
		String palabra ="";
		// asigna la instruccion al mar
		sistema.setInstruccionMAR(sistema.getInstruccionPC());
		this.esperar(this.getVelocidad());
		this.actualizarEstadosComponentes(palabra);
		sistema.aumentarPC();
		this.esperar(this.getVelocidad());
		this.actualizarEstadosComponentes(palabra);
		// Busca la posición en la ram
		int[] instruccion = sistema.buscarInstruccioRAM(sistema.getMar().getDatos());
		this.esperar(this.getVelocidad());
		this.actualizarEstadosComponentes(palabra);
		// Asigna la instruccion a IR
		sistema.asignarRegistroI(instruccion);
		palabra = sistema.traducir(sistema.toDecimal(sistema.extraerSeccion(instruccion, 1), 0, 3));
		this.esperar(this.getVelocidad());
		this.actualizarEstadosComponentes(palabra);
		int[] datoRegistro = sistema.extraerSeccion(instruccion, 2);
		this.esperar(this.getVelocidad());
		this.actualizarEstadosComponentes(palabra);
		switch (palabra) {
		case "LDA":
			// Asigna la instruccion al MAR
			sistema.setInstruccionMAR(datoRegistro);
			this.esperar(this.getVelocidad());
			this.actualizarEstadosComponentes(palabra);
			// Busca la posición en la ram
			instruccion = sistema.buscarInstruccioRAM(sistema.getMar().getDatos());
			this.esperar(this.getVelocidad());
			this.actualizarEstadosComponentes(palabra);
			sistema.asignarAcumuladorA(instruccion);
			this.esperar(this.getVelocidad());
			this.actualizarEstadosComponentes(palabra);
			break;
		case "ADD":			
			sistema.setInstruccionMAR(datoRegistro);
			this.esperar(this.getVelocidad());
			this.actualizarEstadosComponentes(palabra);
			
			// Busca la posición en la ram			
			instruccion = sistema.buscarInstruccioRAM(sistema.getMar().getDatos());
			this.esperar(this.getVelocidad());
			this.actualizarEstadosComponentes(palabra);
						
			sistema.asignarRegistroB(instruccion);
			this.esperar(this.getVelocidad());
			this.actualizarEstadosComponentes(palabra);
																				
			int suma = sistema.sumarDecimal(sistema.valorDecimalAcumulador(), sistema.valorDecimalRegistro());						
			this.esperar(this.getVelocidad());
			this.actualizarEstadosComponentes(palabra);			
						
			sistema.asignarAcumuladorA(sistema.toBinario(suma, 8));
			this.esperar(this.getVelocidad());
			this.actualizarEstadosComponentes(palabra);
			break;
		case "SUB":
			sistema.setInstruccionMAR(datoRegistro);
			this.esperar(this.getVelocidad());
			this.actualizarEstadosComponentes(palabra);
			
			// Busca la posición en la ram
			instruccion = sistema.buscarInstruccioRAM(sistema.getMar().getDatos());
			this.esperar(this.getVelocidad());
			this.actualizarEstadosComponentes(palabra);
			
			sistema.asignarRegistroB(instruccion);
			this.esperar(this.getVelocidad());
			this.actualizarEstadosComponentes(palabra);
			
			int resta = sistema.restarDecimal(sistema.valorDecimalAcumulador(), sistema.valorDecimalRegistro());
			this.esperar(this.getVelocidad());
			this.actualizarEstadosComponentes(palabra);
			
			sistema.asignarAcumuladorA(sistema.toBinario(resta, 8));
			this.esperar(this.getVelocidad());
			break;
		case "STA":
			sistema.setInstruccionMAR(datoRegistro);
			// Se busca la posicion para guardar en la ram
			int posicion = sistema.toDecimal(datoRegistro, 0, datoRegistro.length - 1);
			sistema.setRegistroRAM(posicion, sistema.obtenerValorAcumulador());			
			break;
		case "LDI":
			// Asigna la instruccion al MAR
			sistema.setInstruccionMAR(datoRegistro);
			this.esperar(this.getVelocidad());
			this.actualizarEstadosComponentes(palabra);
			sistema.asignarAcumuladorA(datoRegistro);
			this.esperar(this.getVelocidad());
			this.actualizarEstadosComponentes(palabra);
			break;
		case "JMP":
			sistema.asignarPC(datoRegistro);
			this.esperar(this.getVelocidad());
			this.actualizarEstadosComponentes(palabra);
			break;
		case "JC":
			if (sistema.valorDecimalAcumulador() >= 0) {
				sistema.asignarPC(datoRegistro);
				this.esperar(this.getVelocidad());
				this.actualizarEstadosComponentes(palabra);
			}
			break;
		case "JZ":
			if (sistema.valorDecimalAcumulador() == 0) {
				sistema.asignarPC(datoRegistro);
				this.esperar(this.getVelocidad());
				this.actualizarEstadosComponentes(palabra);
			}
			break;
		case "HLT":
			break;
		case "OUT":
			sistema.setOut(sistema.valorDecimalAcumulador());
			this.esperar(this.getVelocidad());
			this.actualizarEstadosComponentes(palabra);
			System.out.println("El resultado es: " + sistema.valorDecimalAcumulador());
			break;
		}						
		return palabra;
	}

	void modificarRAM() {		
		VistaRAM ventanaRAM;
		ventanaRAM = new VistaRAM(this);		
		ventanaRAM.setVisible(true);
	}
	
	void actualizarEstadosComponentes(String palabra) {
		String texto = "";
		for (int i : this.sistema.getProgramCounter().getDatos()) {
			texto += " "+i;
		}
		this.ventanaGeneral.getLblPC().setText(texto);
		
		texto = "";
		for (int i : this.sistema.getMar().getDatos()) {
			texto += " "+i;
		}
		this.ventanaGeneral.getLblMAR().setText(texto);
		
		texto = "";
		for (int i : this.sistema.getRegistroInstruccion().getDatos()) {
			texto += " "+i;
		}
		this.ventanaGeneral.getLblRI().setText(texto);
		
		texto = "";
		for (int i : this.sistema.getAcumuladorA().getDatos()) {
			texto += " "+i;
		}
		this.ventanaGeneral.getLblAcumulador().setText(texto);
		
		texto = "";
		for (int i : this.sistema.getRegistroB().getDatos()) {
			texto += " "+i;
		}
		this.ventanaGeneral.getLblRegistroB().setText(texto);
		
		texto = "";
		for (int i : this.sistema.getLecturaRam()) {
			texto += " "+i;
		}
		this.ventanaGeneral.getBtnRAM().setText(texto);
		
		this.ventanaGeneral.getLblALU().setText(this.sistema.getOperacionALU());				
		this.ventanaGeneral.getLblCS().setText(palabra);
		this.ventanaGeneral.getLblOUT().setText(""+this.sistema.getOut());
	}
}