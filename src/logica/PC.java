package logica;

public class PC extends Registro{
	/*
	 * Constructor
	 * */
	public PC(int numBits) {
		super(numBits);
		this.CE = false;
	}

	/**
	 * Este atributo representa el contador
	 */
	private boolean CE;

	public boolean isCE() {
		return CE;
	}

	public void setCE() {
		CE = !CE;
	}
	
	/**
	 * Este método devuelve la instrucción que se ha de ejecutar
	 * @return
	 */
	public int[] getInstruccion() {
		return this.getDatos();
	}
	
	
	/**
	 * Este metodo permite aumentar la el CE de tal manera que se registre el paso en el que se va
	 */
	public void aumentar() {
		int acarreo = 0;
		int[] aux = new int[4];
		for(int i = this.getNumBits()-1;i>=0;i--) {
			if(i==3) {
				if(this.getDatos()[i]+1+acarreo==3) {
					aux[i] = 1;
					acarreo = 1;
				}else if (this.getDatos()[i]+1+acarreo==2) {
					aux[i] = 0;
					acarreo = 1;
				}else if(this.getDatos()[i]+1+acarreo==1) {
					aux[i] = 1;
					acarreo = 0;
				}else {
					aux[i] = 0;
					acarreo = 0;
				}
			}else {
				if(this.getDatos()[i]+acarreo==3) {
					aux[i] = 1;
					acarreo = 1;
				}else if (this.getDatos()[i]+acarreo==2) {
					aux[i] = 0;
					acarreo = 1;
				}else if(this.getDatos()[i]+acarreo==1) {
					aux[i] = 1;
					acarreo = 0;
				}else {
					aux[i] = 0;
					acarreo = 0;
				}
			}
		}
		this.setDatos(aux);
	}	
}