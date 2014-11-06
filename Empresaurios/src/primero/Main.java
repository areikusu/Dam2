/**
 * 
 */
package primero;

import java.awt.EventQueue;

/**
 * @author Aleix
 *
 */
public class Main {

	/**
	 * 
	 */
	public Main() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana1 window = new Ventana1();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
