package extractSubtitle;

import java.awt.EventQueue;

public class ExtractSubtitle_Main {
	   public static void main(String[] args) {
	      EventQueue.invokeLater(new Runnable() {
	         public void run() {
	            try {
	               DesignLayout frame = new DesignLayout();
	               frame.setVisible(true);
	            } catch (Exception e) {
	               e.printStackTrace();
	            }
	         }
	      });
	   }
	}