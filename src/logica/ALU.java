package logica;

public class ALU {

	/**
	 * Este metodo permite sumar los numeros del acumulador y el registro B, devolviendo su resultado
	 * @param acumulador Valor almacenado en el acumulador
	 * @param registroB Valor almacenado en el registro B
	 * @return el entero resultante de la suma
	 */
	public int sumarBinario(int acumulador, int registroB) {
		int resultadosuma = 0;
		if (acumulador>=0 && registroB>=0) {
			
			resultadosuma= acumulador + registroB; 
		}
		else {
			if (acumulador<0 && registroB<0) {
				resultadosuma= (~acumulador + 1) + (~registroB + 1);	
			}
			else {
				if(acumulador<0 && registroB>=0){
					resultadosuma= (~acumulador + 1) + registroB;	
				}
				else {
					if(acumulador>=0 && registroB<0) {
						resultadosuma= acumulador + (~registroB + 1);
					}
				}
			}
		}	
		return resultadosuma;
	}
	
	/**
	 * Este metodo permite restar los numeros del acumulador y el registro B, devolviendo su resultado
	 * @param acumulador Valor almacenado en el acumulador
	 * @param registroB Valor almacenado en el registro B
	 * @return el entero resultante de la resta
	 */
	public int restarBinario(int acumulador, int registroB) {		
		int resultadoresta = 0;		
		resultadoresta= acumulador + (~registroB + 1);				
		return resultadoresta;
	}
	
	
	/**
	 * Este metodo permite sumar los numeros del acumulador y el registro B, devolviendo su resultado
	 * @param acumulador Valor almacenado en el acumulador
	 * @param registroB Valor almacenado en el registro B
	 * @return el entero resultante de la suma
	 */
	public int sumar(int acumulador, int registroB) {
		int suma = acumulador+registroB;
		if (suma >=-127 && suma <=127) {
			return suma;	
		}else {
			return 0;
		}
		
	}
	
	
	/**
	 * Este metodo permite restar los numeros del acumulador y el registro B, devolviendo su resultado
	 * @param acumulador Valor almacenado en el acumulador
	 * @param registroB Valor almacenado en el registro B
	 * @return el entero resultante de la resta
	 */
	public int restar(int acumulador, int registroB) {
		int resta = acumulador-registroB;
		if (resta >=-127 && resta <=127) {
			return resta;	
		}else {
			return 0;
		}		
	}
}
