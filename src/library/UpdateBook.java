package library;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class UpdateBook extends JFrame implements ActionListener {
    JButton updateB = new JButton("Update"); JButton resetB = new JButton("Reset"); ImageIcon icon = new ImageIcon("title.png");   JTextField bookID = new JTextField(16); 
    JTextField titlet = new JTextField(16);JTextField genret = new JTextField(16);JTextField authort = new JTextField(16);  JTextField pricet = new JTextField(16);
    UpdateBook() {  setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); setSize(300, 400); setIconImage(icon.getImage()); setLocationRelativeTo(null); setResizable(false);
    setLayout(null);  getContentPane().setBackground(new Color(10, 160, 10));
    JPanel panel = new JPanel(); panel.setBounds(0, 40, 300, 400); panel.setBackground(Color.white); panel.setLayout(null);
        JPanel header = new JPanel(); header.setBounds(0, 0, 300, 40); header.setBackground(new Color(10, 150, 10)); header.setLayout(null);
        JLabel headerl = new JLabel("Update Book"); JLabel bookIDL = new JLabel("Book ID"); JLabel titlel = new JLabel("Title"); JLabel genrel = new JLabel("Genre");
       JLabel authorl = new JLabel("Author");JLabel pricel = new JLabel("Price");
       Font labelFont = new Font("Segoe UI", Font.BOLD, 20);bookIDL.setFont(new Font("Segoe UI", Font.BOLD, 18)); titlel.setFont(labelFont); genrel.setFont(labelFont);
       authorl.setFont(labelFont);pricel.setFont(labelFont);
         bookIDL.setForeground(new Color(10, 160, 10)); titlel.setForeground(new Color(10, 160, 10)); genrel.setForeground(new Color(10, 160, 10));
        authorl.setForeground(new Color(10, 160, 10)); pricel.setForeground(new Color(10, 160, 10));
        bookIDL.setBounds(18, 15, 70, 22); titlel.setBounds(18, 60, 70, 22); genrel.setBounds(18, 105, 70, 22);
        authorl.setBounds(18, 150, 70, 22); pricel.setBounds(18, 195, 70, 22);
        updateB.setBounds(150, 250, 85, 30); resetB.setBounds(40, 250, 85, 30);  
        headerl.setFont(new Font("Segoe UI", Font.BOLD, 28)); headerl.setForeground(Color.white);
        headerl.setBounds(0, 0, 300, 38); headerl.setVerticalAlignment(SwingConstants.CENTER); headerl.setHorizontalAlignment(SwingConstants.CENTER);
        bookID.setBounds(100, 15, 160, 25); titlet.setBounds(100, 60, 160, 25);
        genret.setBounds(100, 105, 160, 25); authort.setBounds(100, 150, 160, 25); pricet.setBounds(100, 195, 160, 25);
   bookID.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(10, 160, 10)));titlet.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(10, 160, 10)));
   genret.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(10, 160, 10))); authort.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(10, 160, 10)));
   pricet.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(10, 160, 10)));
   resetB.setFont(new Font("Segoe UI", Font.BOLD, 18));resetB.addActionListener(this);resetB.setForeground(new Color(10, 160, 10));
   resetB.setBackground(Color.WHITE);resetB.setFocusable(false);resetB.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(10, 160, 10)));    
   updateB.setFont(new Font("Segoe UI", Font.BOLD, 15));updateB.setBackground(new Color(10, 160, 10));updateB.setForeground(Color.WHITE);
   updateB.addActionListener(this);updateB.setFocusable(false);
        header.add(headerl);
        panel.add(bookIDL);panel.add(bookID); panel.add(titlel);panel.add(titlet);panel.add(genrel);panel.add(genret); panel.add(authorl);
        panel.add(authort);panel.add(pricel);panel.add(pricet);panel.add(resetB);panel.add(updateB);
        add(panel);add(header);
        setVisible(true);
    } @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateB) {
            updateBook();
            setVisible(false);
        } else if (e.getSource() == resetB) {
            bookID.setText("");
            titlet.setText("");
            genret.setText("");
            authort.setText("");
            pricet.setText(""); } }
    private void updateBook() {
        try {
            Connection connection = Config.getConnection();
            String updateQuery = "UPDATE books SET BookName=?, Genre=?, Author=?, Price=? WHERE BID=?";
            int bid = Integer.parseInt(bookID.getText()); // Get the Book ID from the input field
            String title = titlet.getText();
            String genre = genret.getText();
            String author = authort.getText();
            Double price = Double.parseDouble(pricet.getText());
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, genre);
            preparedStatement.setString(3, author);
            preparedStatement.setDouble(4, price);
            preparedStatement.setInt(5, bid); // Set the Book ID in the WHERE clause
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Book updated successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update the book.");
            }  preparedStatement.close();
            connection.close();} catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        } catch (NumberFormatException ex) { JOptionPane.showMessageDialog(this, "Invalid price format. Please enter a valid number.");}}}
