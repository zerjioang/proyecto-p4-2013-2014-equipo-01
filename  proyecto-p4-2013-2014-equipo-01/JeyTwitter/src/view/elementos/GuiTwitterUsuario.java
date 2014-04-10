package view.elementos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;

import util.Util;
import view.elementos.botones.BotonSeguir;
import view.models.tablasPrincipal.TablaTweetsUsuarios;

public class GuiTwitterUsuario extends JPanel implements ObjetoCelda{

	private static final int ALTO = 70;
	
	private ObjetoCelda o;
	JLabel lblImagenUsuario;
	BotonSeguir btnSeguir;
	JLabel lblNombreReal;
	JTextArea txtrBiografia;
	JLabel lblusuario;
	
	public GuiTwitterUsuario(){
		super();
		init();
	}

	private void init() {
		//setBounds(10, 11, 476, 110);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelmagen = new JPanel();
		add(panelmagen, BorderLayout.WEST);
		panelmagen.setLayout(new BorderLayout(0, 0));
		
		lblImagenUsuario = new JLabel("");
		lblImagenUsuario.setSize(ALTO, ALTO);
		lblImagenUsuario.setIcon(Util.getImagenRedondeada(new ImageIcon(GuiTwitterUsuario.class.getResource("/res/images/userTest.jpg")), 20));
		lblImagenUsuario.setIcon(Util.escalarImagen(lblImagenUsuario));
		panelmagen.add(lblImagenUsuario, BorderLayout.CENTER);
		
		JPanel panelTexto = new JPanel();
		add(panelTexto, BorderLayout.CENTER);
		panelTexto.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_inferior = new JPanel();
		panelTexto.add(panel_inferior, BorderLayout.SOUTH);
		panel_inferior.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_Superior = new JPanel();
		panelTexto.add(panel_Superior, BorderLayout.NORTH);
		panel_Superior.setLayout(new BorderLayout());
		
		JPanel panel_supDer = new JPanel();
		panel_Superior.add(panel_supDer, BorderLayout.EAST);
		panel_supDer.setLayout(new BorderLayout(0, 0));
		
		btnSeguir = new BotonSeguir();
		btnSeguir.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(1.0f,1.0f,1.0f,0.0f)));
		panel_supDer.add(btnSeguir, BorderLayout.CENTER);
		
		JPanel panel_supIzq = new JPanel();
		panel_Superior.add(panel_supIzq, BorderLayout.WEST);
		panel_supIzq.setLayout(new BorderLayout(0, 0));
		
		lblusuario = new JLabel("@Usuario");
		lblusuario.setBorder(new MatteBorder(4, 4, 4, 0, (Color) new Color(1.0f,1.0f,1.0f,0.0f)));
		lblusuario.setFont(Util.getFont("mirda", Font.BOLD, 14));
		panel_supIzq.add(lblusuario, BorderLayout.NORTH);
		
		lblNombreReal = new JLabel("Nombre Real");
		lblNombreReal.setBorder(new MatteBorder(0, 4, 4, 0, (Color) new Color(1.0f,1.0f,1.0f,0.0f)));
		lblNombreReal.setFont(Util.getFont("mirda", Font.PLAIN, 14));
		panel_supIzq.add(lblNombreReal, BorderLayout.SOUTH);
		
		txtrBiografia = new JTextArea();
		txtrBiografia.setBorder(new MatteBorder(0,5,5,0, new Color(1.0f,1.0f,1.0f,0.0f)));
		txtrBiografia.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		txtrBiografia.setOpaque(false);
		txtrBiografia.setEditable(false);
		txtrBiografia.setWrapStyleWord(true);
		txtrBiografia.setLineWrap(true);
		txtrBiografia.setFocusable(false);
		txtrBiografia.setFont(Util.getFont("mirda", Font.PLAIN, 12));
		txtrBiografia.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam et felis vestibulum, laoreet ipsum vel. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam et felis vestibulum, laoreet ipsum vel");
		panelTexto.add(txtrBiografia, BorderLayout.CENTER);
	}

	@Override
	public int tipoObjeto() {
		return TablaTweetsUsuarios.SOLO_USUARIOS;
	}

	@Override
	public String getNombreReal() {
		return lblNombreReal.getText();
	}

	@Override
	public String getNombreUsuario() {
		return lblusuario.getText();
	}

	@Override
	public Icon getImagenUsuario() {
		return lblImagenUsuario.getIcon();
	}

	@Override
	public String getMensaje() {
		return txtrBiografia.getText();
	}

	@Override
	public String getTiempo() {
		return "";
	}

	/**
	 * @return the lblImagenUsuario
	 */
	public JLabel getLblImagenUsuario() {
		return lblImagenUsuario;
	}

	/**
	 * @param lblImagenUsuario the lblImagenUsuario to set
	 */
	public void setLblImagenUsuario(JLabel lblImagenUsuario) {
		this.lblImagenUsuario = lblImagenUsuario;
	}

	/**
	 * @return the btnSeguir
	 */
	public BotonSeguir getBtnSeguir() {
		return btnSeguir;
	}

	/**
	 * @param btnSeguir the btnSeguir to set
	 */
	public void setBtnSeguir(BotonSeguir btnSeguir) {
		this.btnSeguir = btnSeguir;
	}

	/**
	 * @return the lblNombreReal
	 */
	public JLabel getLblNombreReal() {
		return lblNombreReal;
	}

	/**
	 * @param lblNombreReal the lblNombreReal to set
	 */
	public void setLblNombreReal(JLabel lblNombreReal) {
		this.lblNombreReal = lblNombreReal;
	}

	/**
	 * @return the txtrBiografia
	 */
	public JTextArea getTxtrBiografia() {
		return txtrBiografia;
	}

	/**
	 * @param txtrBiografia the txtrBiografia to set
	 */
	public void setTxtrBiografia(JTextArea txtrBiografia) {
		this.txtrBiografia = txtrBiografia;
	}

	/**
	 * @return the lblusuario
	 */
	public JLabel getLblusuario() {
		return lblusuario;
	}

	/**
	 * @param lblusuario the lblusuario to set
	 */
	public void setLblusuario(JLabel lblusuario) {
		this.lblusuario = lblusuario;
	}
}
