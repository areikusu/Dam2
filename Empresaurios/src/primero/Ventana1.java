package primero;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Ventana1 extends JFrame implements ActionListener{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel etiqueta = new JLabel("");
	JLabel label = new JLabel("Introduce Nº de Departamento:");
	JTextField depar = new JTextField(2);
	JButton boton = new JButton("Consultar");
	
	private JFrame frame;

	
	public Ventana1() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(142, 106, 217, 132);
		frame.getContentPane().add(textPane);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	//Localizar los datos del departamento
	void VisualizarDep(int Dep){
		//pinto los datos del dept

	}
}
