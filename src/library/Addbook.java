package library; import java.awt.*; import java.awt.event.*; import java.sql.*;import javax.swing.*;
public class Addbook extends JFrame implements ActionListener {
    JButton addB = new JButton("Add");JButton resetB = new JButton("Reset");ImageIcon icon = new ImageIcon("title.png");
    JTextField titlet = new JTextField(16);JTextField genret = new JTextField(16); JTextField authort = new JTextField(16); JTextField pricet = new JTextField(16);
    Addbook() { setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); setSize(300, 350); setIconImage(icon.getImage());setLocationRelativeTo(null);
        setResizable(false);setLayout(null);getContentPane().setBackground(new Color(10, 160, 10));
        JPanel panel = new JPanel(); panel.setBounds(0, 40, 300, 400); panel.setBackground(Color.white); panel.setLayout(null); JPanel header = new JPanel();
        header.setBounds(0, 0, 300, 40); header.setBackground(new Color(10, 150, 10)); header.setLayout(null);
        JLabel headerl = new JLabel("New Book"); JLabel titlel = new JLabel("Title"); JLabel genrel = new JLabel("Genre");       
        JLabel authorl = new JLabel("Author"); JLabel pricel = new JLabel("Price");
        Font labelFont = new Font("Segoe UI", Font.BOLD, 20);
        titlel.setFont(labelFont);genrel.setFont(labelFont);authorl.setFont(labelFont); pricel.setFont(labelFont);  titlel.setForeground(new Color(10, 160, 10));
        genrel.setForeground(new Color(10, 160, 10));authorl.setForeground(new Color(10, 160, 10));pricel.setForeground(new Color(10, 160, 10));
        titlel.setBounds(18, 15, 70, 22);genrel.setBounds(18, 60, 70, 22);authorl.setBounds(18, 105, 70, 22);pricel.setBounds(18, 150, 70, 22);
        addB.setBounds(150, 200, 85, 30);resetB.setBounds(40, 200, 85, 30); 
        headerl.setFont(new Font("Segoe UI", Font.BOLD, 28));headerl.setForeground(Color.white);headerl.setBounds(0, 0, 300, 38);
        headerl.setVerticalAlignment(SwingConstants.CENTER);headerl.setHorizontalAlignment(SwingConstants.CENTER);
        titlet.setBounds(100, 15, 160, 25); genret.setBounds(100, 60, 160, 25); authort.setBounds(100, 105, 160, 25); pricet.setBounds(100, 150, 160, 25);
        titlet.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(10, 160, 10)));
        genret.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(10, 160, 10)));
        authort.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(10, 160, 10)));
        pricet.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(10, 160, 10))); 
        resetB.setFont(new Font("Segoe UI", Font.BOLD, 18)); resetB.addActionListener(this);resetB.setForeground(new Color(10, 160, 10));resetB.setBackground(Color.WHITE);
       resetB.setFocusable(false); resetB.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(10, 160, 10)));
        addB.setFont(new Font("Segoe UI", Font.BOLD, 18)); addB.setBackground(new Color(10, 160, 10)); addB.setForeground(Color.WHITE);
        addB.addActionListener(this); addB.setFocusable(false);
        header.add(headerl);
        panel.add(titlel); panel.add(titlet); panel.add(genrel); panel.add(genret); panel.add(authorl);
        panel.add(authort); panel.add(pricel); panel.add(pricet); panel.add(resetB); panel.add(addB);
        add(panel); add(header); setVisible(true);}
    @Override public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addB) { insertbook(); setVisible(false);
        }else if(e.getSource()==resetB) { titlet.setText(""); genret.setText(""); authort.setText("");pricet.setText("");}}
        private void insertbook(){try {
        		Connection connection= Config.getConnection();
        		String insertq = "INSERT INTO books (BookName, Genre, Author, Price) VALUES (?, ?, ?, ?)";
        		String Title= titlet.getText();
        		String genre= genret.getText();
        		String author= authort.getText();
        		Double price = Double.parseDouble(pricet.getText());
        		PreparedStatement prepstate= connection.prepareStatement(insertq);
        		prepstate.setString(1,Title);
        		prepstate.setString(2, genre);
        		prepstate.setString(3, author);
        		prepstate.setDouble(4, price);
        		int rowsaffected= prepstate.executeUpdate();
        		if (rowsaffected>0) {
        			JOptionPane.showMessageDialog(this, "Book added successfully.");
        		}else {
        			JOptionPane.showMessageDialog(this, "Failed to add the book.");
        		}
        		prepstate.close();
        		connection.close();
        }catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid price format. Please enter a valid number.");}}}