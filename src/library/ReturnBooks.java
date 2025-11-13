package library;import java.awt.*;import java.awt.event.*;import java.sql.Connection;
import java.text.SimpleDateFormat;import java.sql.*;import javax.swing.*;
public class ReturnBooks extends JFrame implements ActionListener {
	JButton ReturnB=new JButton("Return");ImageIcon icon= new ImageIcon("title.png");SpinnerNumberModel numberModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
	JSpinner finet= new JSpinner(numberModel);JTextField TextF1 = new JTextField();JTextField TextF2 = new JTextField();
	ReturnBooks(){
		 JPanel panel = new JPanel(); panel.setBounds(0, 40, 250, 320);panel.setBackground(Color.white); panel.setLayout(null);
	        JPanel header = new JPanel(); header.setBounds(0, 0, 250, 40); header.setBackground(new Color(10, 150, 10)); header.setLayout(null);
	        JLabel Label1 = new JLabel("Issue ID (IID)");JLabel Label2 = new JLabel("Return Date");JLabel Label3 = new JLabel("Fine (PHP)");
	         Font labelFont = new Font("Segoe UI", Font.BOLD, 16);
	        Label1.setFont(labelFont);Label2.setFont(labelFont); Label3.setFont(labelFont);
	        Color fontColor = new Color(10, 160, 10);
	        Label1.setForeground(fontColor);Label2.setForeground(fontColor); Label3.setForeground(fontColor);
	        Label1.setBounds(18, 15, 120, 22);Label2.setBounds(18, 60, 120, 22);Label3.setBounds(18, 105, 120, 22);
	        TextF1.setBounds(140, 15, 80, 25); TextF2.setBounds(140, 60, 80, 25);finet.setBounds(140, 105, 80, 25); ReturnB.setBounds(65, 150, 120, 32);
	        TextF1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(10, 160, 10)));
	        TextF2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(10, 160, 10)));
	        finet.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(10, 160, 10))); 
	        ReturnB.setFont(new Font("Segoe UI", Font.BOLD, 18));ReturnB.setBackground(new Color(10, 160, 10)); ReturnB.setForeground(Color.WHITE);
	        ReturnB.addActionListener(this); ReturnB.setFocusable(false);
	        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); setSize(250, 300); setIconImage(icon.getImage()); setLocationRelativeTo(null); setResizable(false);
	        add(panel); add(header);
	        panel.add(Label1); panel.add(Label2); panel.add(Label3); panel.add(TextF1); panel.add(TextF2); panel.add(finet); panel.add(ReturnB); setVisible(true);
	        TextF2.addFocusListener(new java.awt.event.FocusAdapter() {
	            public void focusGained(java.awt.event.FocusEvent evt) { fillCurrentDate();}});
	}private void fillCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date(System.currentTimeMillis());
        TextF2.setText(dateFormat.format(currentDate));
    }@Override
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == ReturnB) {
	        returnBook();
	        setVisible(false); }}
	private void returnBook() {
	    try {
	        Connection connection = Config.getConnection();
	        String updateQuery = "UPDATE issue SET ReturnDate = ?, Fine = ? WHERE IID = ?";
	        int issueID = Integer.parseInt(TextF1.getText());
	        String returnDateText = TextF2.getText();
	        double fine;
	        try {
	            fine = Double.parseDouble(finet.getValue().toString());
	            } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(this, "Invalid fine amount. Please enter a valid number.");
	            return;  }
	        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
	        preparedStatement.setDate(1, Date.valueOf(returnDateText));
	        preparedStatement.setDouble(2, fine);
	        preparedStatement.setInt(3, issueID);
	        int rowsAffected = preparedStatement.executeUpdate();
	        if (rowsAffected > 0) {
	            JOptionPane.showMessageDialog(this, "Book returned successfully.");
	        } else {
	            JOptionPane.showMessageDialog(this, "Failed to return the book.");}
	        preparedStatement.close();
	        connection.close();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(this, "Invalid input format. Please enter valid data.");}}}
