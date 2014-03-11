package view.elementos.input;

public class CampoCodeAuth extends InputField{

	public CampoCodeAuth(){
		super();
	}
	
	@Override
	public boolean evaluate() {
		//Este metodo hace que cuando detecta un codigo de 7 digitos 
		//lo establece como correcto
		boolean condicion = getInputField().getText().length()==7;
		if(condicion)
			setModoCorrecto();
		else
			setModoError();
		return condicion;
	}
}
