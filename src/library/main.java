package library;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.JButton;

public class main {
   public static void main(String[]args) {

	   try {
           Connection connection = Config.getConnection();
           if (connection != null) {
               System.out.println("Connected to the database!");
           }
       } catch (SQLException e) {
           e.printStackTrace();
           System.err.println("Error: Unable to connect to the database.");
       }
	   new login();
}
   public static void setButtonHoverCursor(JButton button) {
	        button.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	            }
	            @Override
	            public void mouseExited(MouseEvent e) {
	                button.setCursor(Cursor.getDefaultCursor());
	            }
	        });
}
}
