package library;import java.awt.*;import java.awt.event.*;import java.sql.*;import java.text.SimpleDateFormat;import javax.swing.*;
public class IssueBook extends JFrame implements ActionListener {
    JButton resetB = new JButton("Reset");JButton IssueB = new JButton("Issue");ImageIcon icon = new ImageIcon("title.png");
    JTextField TextF1 = new JTextField();JTextField TextF2 = new JTextField();JTextField TextF3 = new JTextField();JTextField TextF4 = new JTextField();
    IssueBook() {
        JPanel panel = new JPanel();panel.setBounds(0, 40, 350, 380); panel.setBackground(Color.white); panel.setLayout(null);
        JPanel header = new JPanel();header.setBounds(0, 0, 350, 40);header.setBackground(new Color(10, 150, 10));header.setLayout(null);
        JLabel Label1 = new JLabel("Book ID (BID)");JLabel Label2 = new JLabel("User ID (UID)");
        JLabel Label3 = new JLabel("Period (DAYS)");JLabel Label4 = new JLabel("Issued Date(YYYY-MM-DD)");
        Font labelFont = new Font("Segoe UI", Font.BOLD, 16);
        Label1.setFont(labelFont);Label2.setFont(labelFont); Label3.setFont(labelFont);Label4.setFont(new Font("Segoe UI", Font.BOLD, 13));
        Color fontColor = new Color(10, 160, 10);
        Label1.setForeground(fontColor); Label2.setForeground(fontColor); Label3.setForeground(fontColor);Label4.setForeground(fontColor);
        Label1.setBounds(18, 15, 120, 22); Label2.setBounds(18, 60, 120, 22);Label3.setBounds(18, 105, 120, 22); Label4.setBounds(18, 150, 180, 22);
        IssueB.setBounds(175, 200, 85, 30);resetB.setBounds(65, 200, 85, 30);
        TextF1.setBounds(140, 15, 170, 25);TextF2.setBounds(140, 60, 170, 25);TextF3.setBounds(140, 105, 170, 25);TextF4.setBounds(200, 150, 110, 25);
        TextF1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(10, 160, 10)));
        TextF2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(10, 160, 10)));
        TextF3.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(10, 160, 10)));
        TextF4.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(10, 160, 10)));
        resetB.setFont(new Font("Segoe UI", Font.BOLD, 18)); resetB.addActionListener(this); resetB.setForeground(new Color(10, 160, 10));
        resetB.setBackground(Color.WHITE); resetB.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(10, 160, 10))); resetB.setFocusable(false);
        IssueB.setFont(new Font("Segoe UI", Font.BOLD, 18)); IssueB.setBackground(new Color(10, 160, 10)); IssueB.setForeground(Color.WHITE);
        IssueB.addActionListener(this); IssueB.setFocusable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); setSize(350, 370); setIconImage(icon.getImage()); setLocationRelativeTo(null); setResizable(false);
        add(panel); add(header);
        panel.add(Label1); panel.add(Label2);panel.add(Label3);panel.add(Label4);
        panel.add(IssueB);panel.add(resetB); panel.add(TextF1);panel.add(TextF2);panel.add(TextF3);panel.add(TextF4);  
        setVisible(true);   
        TextF4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fillCurrentDate();}
        });
    } private void fillCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date(System.currentTimeMillis());
        TextF4.setText(dateFormat.format(currentDate));
    }@Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == IssueB) {
            issueBook();
            setVisible(false);
        } else if (e.getSource() == resetB) {
            TextF1.setText("");
            TextF2.setText("");
            TextF3.setText("");
            TextF4.setText("");}}
    private void issueBook() {
        try {
            Connection connection = Config.getConnection();
            String insertQuery = "INSERT INTO issue (BID, UID, Period, IssueDate) VALUES (?, ?, ?, ?)";
            int bookId = Integer.parseInt(TextF1.getText());
            int userId = Integer.parseInt(TextF2.getText());
            int days = Integer.parseInt(TextF3.getText());
            Date issueDate = Date.valueOf(TextF4.getText());
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, bookId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, days);
            preparedStatement.setDate(4, issueDate);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Book issued successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to issue the book.");
            }preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.");
        }}}