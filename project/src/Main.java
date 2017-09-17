
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import controller.login.CLogin;

//import admin.JLibrary;

public class Main extends JFrame {

	public static void main(String[] args) {
	    SwingUtilities.invokeLater(new Runnable(){
	      public void run(){
	    	  CLogin controller = new CLogin();
	      }
	    });
	}
}