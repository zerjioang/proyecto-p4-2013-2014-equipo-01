package monitorizacion;

/**
 * Clase que almacena los Datos a monitorizar
 * 
 * @author sergiers
 *
 */
public class Dato {
	
	int dia;
	int mes;
	int ano;
	
	String nom;
	
	public Dato(){
		
	}
	
	public Dato(int dia, int mes, int ano,  String nom){
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		this.nom = nom;
		
	}
	
	
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	

}
