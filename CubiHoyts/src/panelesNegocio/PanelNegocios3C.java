package panelesNegocio;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelNegocios3C extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel titulo;
	public JLabel totalGanancia;
	public JLabel numeroCliente;
	public JLabel numeroNegocios;
	public JTextField campoTotalGanancia;
	public JTextField campoNumeroCliente;
	public JTextField campoNumeroNegocios;
	
	public JButton grafCantPel;
	public JButton grafCantVAClientes;
	public JButton grafVAMeses;
	public JButton grafTipoClientes;
	public JButton grafGananciaSemanal;
	
	public PanelNegocios3C() {
		titulo=new JLabel("Información de ganancias");
		titulo.setFont(new Font("Tahoma",0,18));
		add(titulo);
		titulo.setBounds(100, 200, 250, 25);
		setSize(800,600);
		setLayout(null);
		totalGanancia=new JLabel("Ganancia total: ");
		numeroCliente=new JLabel("Numero de clientes: ");
		numeroNegocios=new JLabel("Numero de V/A: ");
		campoTotalGanancia=new JTextField();
		campoTotalGanancia.setEditable(false);
		campoNumeroCliente=new JTextField();
		campoNumeroCliente.setEditable(false);
		campoNumeroNegocios=new JTextField();
		campoNumeroNegocios.setEditable(false);
		grafCantPel=new JButton("Gráfico por cantidad");
		add(grafCantPel);
		grafCantPel.setBounds(525, 220, 200, 50);
		grafCantVAClientes=new JButton("Gráfico V/A clientes");
		add(grafCantVAClientes);
		grafCantVAClientes.setBounds(525, 280, 200, 50);
		grafVAMeses=new JButton("Gráfico V/A por mes");
		add(grafVAMeses);
		grafVAMeses.setBounds(525, 340, 200, 50);
		grafTipoClientes=new JButton("Gráfico tipo de clientes");
		add(grafTipoClientes);
		grafTipoClientes.setBounds(525, 400, 200, 50);
		grafVAMeses.setBounds(525, 340, 200, 50);
		grafGananciaSemanal=new JButton("Gráfico ganancia semanal");
		add(grafGananciaSemanal);
		grafGananciaSemanal.setBounds(525, 460, 200, 50);
		add(totalGanancia);
		add(numeroCliente);
		add(numeroNegocios);
		add(campoTotalGanancia);
		add(campoNumeroCliente);
		add(campoNumeroNegocios);
		totalGanancia.setBounds(100,275,125,25);
		numeroCliente.setBounds(100, 325, 125, 25);
		numeroNegocios.setBounds(100, 375, 125, 25);
		campoTotalGanancia.setBounds(250, 275, 150, 25);
		campoNumeroCliente.setBounds(250, 325, 150, 25);
		campoNumeroNegocios.setBounds(250, 375, 150, 25);
	}
}
