package logica;

public class ControlSecuencia {

	//atributo usado para saber el registro de instrucciones a usar en la version anterior
	//private int version;
	
	ControlSecuencia(){
		//this.version = 0;
	}
	
	/**
	 * Permite traducir la instruccion con representacion decimal a la palabra en
	 * lenguaje natural
	 * 
	 * @param instruccion entero a traducir
	 * @return String que corresponde a la palabra indicada
	 */
	public String traducir(int instruccion) {		
		String respuesta = "";
		/*
		if (this.version == 1) {			
			switch (instruccion) {
			case 0:
				respuesta = "LDA";
				break;
			case 1:
				respuesta = "ADD";
				break;
			case 2:
				respuesta = "SUB";
				break;
			case 14:
				respuesta = "OUT";
				break;			
			case 15:
				respuesta = "HLT";
				break;
			}
			return respuesta;

		} else {*/
			switch (instruccion) {
			case 1:
				respuesta = "LDA";
				break;
			case 2:
				respuesta = "ADD";
				break;
			case 3:
				respuesta = "SUB";
				break;
			case 4:
				respuesta = "STA";
				break;
			case 5:
				respuesta = "LDI";
				break;
			case 6:
				respuesta = "JMP";
				break;
			case 7:
				respuesta = "JC";
				break;
			case 8:
				respuesta = "JZ";
				break;
			case 14:
				respuesta = "OUT";
				break;
			case 15:
				respuesta = "HLT";
				break;

			}
			return respuesta;
		//}
	}

	/*
	public void cargarVersion(int version) {		
		this.version = version;	
		
	}*/

}