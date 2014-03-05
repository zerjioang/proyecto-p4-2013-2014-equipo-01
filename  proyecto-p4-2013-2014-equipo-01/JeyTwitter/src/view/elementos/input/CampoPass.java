package view.elementos.input;

public class CampoPass extends InputField{

	public CampoPass(){
		super();
	}

	@Override
	public boolean evaluate() {
		boolean estado = getInputField().getText().equals("ComicSans");
		if(estado)
			setModoCorrecto();
		else
			setModoError();
		return estado;
	}
}
