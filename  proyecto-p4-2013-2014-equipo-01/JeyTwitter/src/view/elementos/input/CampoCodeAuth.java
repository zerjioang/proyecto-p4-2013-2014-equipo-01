package view.elementos.input;

public class CampoCodeAuth extends InputField{

	public CampoCodeAuth(){
		super();
	}
	
	@Override
	public boolean evaluate() {
		//Este metodo hace que cuando detecta un codigo de 7 digitos 
		//lo establece como correcto
		if(getInputField().getText().length()==7)
			setModoCorrecto();
		else
			setModoError();
		// TODO Auto-generated method stub
		return getInputField().getText().length()==7;
	}
}
