package ventana;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import logo.PanelLogo;
import net.sf.jasperreports.engine.JRException;
/**
 * Clase Main
 * 
 * Clase que contiene el main del programa.
 * 
 * Se encarga de dimensionar la ventana principal
 * y de hacerla visible.
 * 
 * @author Nicolás Honorato
 *
 */
public class Main {
	private static VentanaJFrame vent;		//Ventana principal del programa.
	private static boolean flag=false;
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Método main del programa.
	 * 
	 * @param args
	 * Argumento obligatorio del método main.
	 * 
	 * @throws JRException
	 * Se debe manejar la excepción ya que se crean
	 * reportes jasper en la ejecución del programa.
	 */
	public static void main(String[] args)throws JRException {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				vent=VentanaJFrame.getInstance();
				if(!flag)
					procesar();
			}
		});
	}
	
	
	
	
	
	
	
	
	
	
	
	private static void procesar()   {
		PanelCarga panelCarga=new PanelCarga();
		panelCarga.progreso.setValue(1);
		PanelLogo logo=new PanelLogo();
		logo.salir.setVisible(false);
		logo.volver.setVisible(false);
		logo.undo.setVisible(false);
		vent.getContentPane().add(logo);
		vent.getContentPane().add(panelCarga);
		ActionListener ac=new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				panelCarga.x=panelCarga.x+1;
				int x=panelCarga.x;
				if(x==5 || x==10 || x==30 || x==40 || x==55 || x==63 || x==75 || x==95 || x==100) {
					panelCarga.progreso.setValue(panelCarga.x);
				}
				if(panelCarga.progreso.getValue()==1) {
					panelCarga.remove(panelCarga.frase);
					(vent).setVisible(true);
				}
				else if(panelCarga.progreso.getValue()==5) {
					panelCarga.remove(panelCarga.frase);
					panelCarga.frase=new JLabel("Conectando con la base de datos...");
					panelCarga.add(panelCarga.frase);
					panelCarga.frase.setBounds(200,400,1000,30);
					panelCarga.frase.setFont(new Font("Tahoma",0,24));
					SwingUtilities.updateComponentTreeUI(vent);
				}
				else if(panelCarga.progreso.getValue()==10) {
					panelCarga.remove(panelCarga.frase);
					panelCarga.frase=new JLabel("Cargando datos...");
					panelCarga.add(panelCarga.frase);
					panelCarga.frase.setBounds(300,400,1000,30);
					panelCarga.frase.setFont(new Font("Tahoma",0,24));
					SwingUtilities.updateComponentTreeUI(vent);
				}
				else if(panelCarga.progreso.getValue()==30) {
					panelCarga.remove(panelCarga.frase);
					panelCarga.frase=new JLabel("Generando ventanas...");
					panelCarga.add(panelCarga.frase);
					panelCarga.frase.setBounds(300,400,1000,30);
					panelCarga.frase.setFont(new Font("Tahoma",0,24));
					SwingUtilities.updateComponentTreeUI(vent);
				}
				else if(panelCarga.progreso.getValue()==40) {
					panelCarga.remove(panelCarga.frase);
					panelCarga.frase=new JLabel("Generando ventanas...");
					panelCarga.add(panelCarga.frase);
					panelCarga.frase.setBounds(300,400,1000,30);
					panelCarga.frase.setFont(new Font("Tahoma",0,24));
					SwingUtilities.updateComponentTreeUI(vent);
				}
				else if(panelCarga.progreso.getValue()==55) {
					panelCarga.remove(panelCarga.frase);
					panelCarga.frase=new JLabel("Activando componentes gráficos 1...");
					panelCarga.add(panelCarga.frase);
					panelCarga.frase.setBounds(200,400,1000,30);
					panelCarga.frase.setFont(new Font("Tahoma",0,24));
					SwingUtilities.updateComponentTreeUI(vent);
				}
				else if(panelCarga.progreso.getValue()==63) {
					panelCarga.remove(panelCarga.frase);
					panelCarga.frase=new JLabel("Activando componentes gráficos 2...");
					panelCarga.add(panelCarga.frase);
					panelCarga.frase.setBounds(200,400,1000,30);
					panelCarga.frase.setFont(new Font("Tahoma",0,24));
					SwingUtilities.updateComponentTreeUI(vent);
				}
				else if(panelCarga.progreso.getValue()==75) {
					panelCarga.remove(panelCarga.frase);
					panelCarga.frase=new JLabel("Activando componentes gráficos 3...");
					panelCarga.add(panelCarga.frase);
					panelCarga.frase.setBounds(200,400,1000,30);
					panelCarga.frase.setFont(new Font("Tahoma",0,24));
					SwingUtilities.updateComponentTreeUI(vent);
				}
				else if(panelCarga.progreso.getValue()==100) {
					vent.stage7();
					flag=true;
					panelCarga.t.stop();
					
				}
			}
		};
		panelCarga.t=new Timer(30,ac);
		panelCarga.t.start();
	}
}
