package library;
import java.awt.*;import java.awt.event.*;import java.sql.*; import java.util.Arrays;
import javax.swing.*;
public class RemoveB extends JFrame implements ActionListener {
	JButton deleteB=new JButton("Delete");ImageIcon icon = new ImageIcon("title.png");JTextField BIDT = new JTextField(16);
	RemoveB(){setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); setSize(260, 220); setIconImage(icon.getImage()); setLocationRelativeTo(null);
		setResizable(false); setLayout(null);getContentPane().setBackground(new Color(10, 160, 10));
	    JPanel panel = new JPanel();panel.setBounds(0, 40, 300, 400); panel.setBackground(Color.white); panel.setLayout(null);
        JPanel header = new JPanel(); header.setBounds(0, 0, 300, 40);header.setBackground(new Color(10, 150, 10));header.setLayout(null);JLabel BIDL = new JLabel("BOOK ID");
        BIDL.setFont(new Font("Segoe UI", Font.BOLD, 20));BIDL.setForeground(new Color(10, 160, 10));BIDL.setBounds(18, 25, 100, 22); BIDT.setBounds(120, 25, 100, 25);
        BIDT.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(10, 160, 10)));
         deleteB.setBounds(70, 80, 90, 30);deleteB.setFont(new Font("Segoe UI", Font.BOLD, 18));deleteB.setBackground(new Color(10, 160, 10));
        deleteB.setForeground(Color.WHITE);deleteB.addActionListener(this);deleteB.setFocusable(false);
        panel.add(BIDT); panel.add(BIDL); panel.add(deleteB); add(panel); add(header);setVisible(true);}
	@Override
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == deleteB) { String bookID = BIDT.getText().trim();
	        if (bookID.isEmpty()) { JOptionPane.showMessageDialog(this, "Please enter a valid Book ID.", "Delete Error", JOptionPane.ERROR_MESSAGE);
	        } else { JPasswordField passwordField = new JPasswordField();
	            int passwordDialogResult = JOptionPane.showConfirmDialog(this, passwordField, "Enter password to confirm deletion:", JOptionPane.OK_CANCEL_OPTION);
	            if (passwordDialogResult == JOptionPane.OK_OPTION) {
	                char[] enteredPasswordChars = passwordField.getPassword();
	                String enteredPassword = new String(enteredPasswordChars);
	                if (enteredPassword.equals(UserSession.getPassword())) { String bookTitle = getBookTitle(bookID);
	                    if (bookTitle != null) {
  int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the book titled '" + bookTitle + "'?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
	                        if (choice == JOptionPane.YES_OPTION) { deleteBook(bookID); } else {
	                            JOptionPane.showMessageDialog(this, "Deletion canceled.", "Deletion Canceled", JOptionPane.INFORMATION_MESSAGE); } } else {
	                        JOptionPane.showMessageDialog(this, "Book with ID " + bookID + " does not exist.", "Delete Error", JOptionPane.ERROR_MESSAGE); } } else {
	                    JOptionPane.showMessageDialog(this, "Password is incorrect. Deletion canceled.", "Password Error", JOptionPane.ERROR_MESSAGE);
	                } Arrays.fill(enteredPasswordChars, ' '); } } }}
	private String getBookTitle(String bookID) {
	    String bookTitle = null;
	    try {
	        Connection connection = Config.getConnection();
	        String query = "SELECT BookName FROM books WHERE BID = ?";
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, bookID);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        if (resultSet.next()) {
	            bookTitle = resultSet.getString("BookName");
	        }

	        resultSet.close();
	        preparedStatement.close();
	        connection.close();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return bookTitle;
	}


	private void deleteBook(String bookID) {
	    try {
	        Connection connection = Config.getConnection();
	        String deleteQuery = "DELETE FROM books WHERE BID = ?";
	        PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
	        preparedStatement.setString(1, bookID);

	        int rowsDeleted = preparedStatement.executeUpdate();
	        preparedStatement.close();
	        connection.close();

	        if (rowsDeleted > 0) {
	            JOptionPane.showMessageDialog(this, "Book has been deleted.", "Delete Success", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            JOptionPane.showMessageDialog(this, "Book with ID " + bookID + " does not exist.", "Delete Error", JOptionPane.ERROR_MESSAGE);
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}

}
