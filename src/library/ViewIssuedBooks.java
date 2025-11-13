package library;import java.awt.*;import java.awt.event.*;import java.sql.*;import javax.swing.*;import javax.swing.table.*;
public class ViewIssuedBooks extends JFrame implements ActionListener {
	ImageIcon icon = new ImageIcon("title.png"); DefaultTableModel issueTableModel = new DefaultTableModel();
	JTable table=new JTable(issueTableModel);JButton back = new JButton("Back");
    ViewIssuedBooks(){
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setVisible(true); setSize(1000, 650); setLayout(null); setIconImage(icon.getImage()); setLocationRelativeTo(null);
        setResizable(false); getContentPane().setBackground(Color.white); setTitle("User Record");
        JPanel title = new JPanel();
        title.setBounds(0, 40, 1000, 60);title.setLayout(new FlowLayout(FlowLayout.CENTER));title.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel("Issue Record");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 35));titleLabel.setForeground(new Color(0, 140, 0));
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);title.add(titleLabel);
        JPanel BackPanel= new JPanel();
        BackPanel.setBounds(0, 0, 1000, 40);BackPanel.add(back);
        BackPanel.setBackground(Color.white); BackPanel.setLayout(null);
        back.setBounds(20 , 10, 68, 30);back.setForeground(Color.white);back.setBackground(new Color(0, 160, 10)); back.setFont(new Font("Segoe UI", Font.BOLD, 15));
        back.setFocusable(false);main.setButtonHoverCursor(back);back.addActionListener(this);
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.WHITE); panel1.setBounds(0, 110, 1000, 650);panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        issueTableModel.addColumn("IID");issueTableModel.addColumn("UID");issueTableModel.addColumn("Username");issueTableModel.addColumn("Book ID");
        issueTableModel.addColumn("Book Name");issueTableModel.addColumn("Issued Date"); issueTableModel.addColumn("Period");
        issueTableModel.addColumn("Return Date"); issueTableModel.addColumn("Fine PHP");
        JScrollPane scroll = new JScrollPane(table); scroll.setPreferredSize(new Dimension(800, 420));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        table.getTableHeader().setBackground(new Color(0, 160, 10));table.getTableHeader().setForeground(Color.white);
        table.getTableHeader().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0, 100, 10)));
        table.getTableHeader().setPreferredSize(new Dimension(0, 30));
        table.getColumnModel().getColumn(0).setPreferredWidth(30); 
        table.getColumnModel().getColumn(1).setPreferredWidth(30); 
        table.getColumnModel().getColumn(2).setPreferredWidth(100); 
        table.getColumnModel().getColumn(3).setPreferredWidth(50); 
        table.getColumnModel().getColumn(4).setPreferredWidth(130); 
        table.getColumnModel().getColumn(5).setPreferredWidth(70); 
        table.getColumnModel().getColumn(6).setPreferredWidth(50); 
        table.getColumnModel().getColumn(7).setPreferredWidth(70); 
        table.getColumnModel().getColumn(8).setPreferredWidth(60); 
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(issueTableModel);
        table.setRowSorter(sorter);table.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 2, Color.black));table.setRowHeight(26);
        sorter.toggleSortOrder(0); // 0 is the column index for IID
        table.setFont(new Font("Segoe UI", Font.PLAIN, 15)); table.setBackground(new Color(245, 250, 240));
        add(title);add(panel1);title.add(titleLabel);panel1.add(scroll);add(BackPanel);loadData();}
	@Override public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
        	new admin();
        	setVisible(false);}}
	 private void loadData() {
	        try {
	            Connection connection = Config.getConnection();
	            String query = "SELECT i.IID, i.UID, i.BID, b.BookName, i.IssueDate, i.Period, i.ReturnDate, i.Fine, u.Username "
	                    + "FROM issue i " + "INNER JOIN users u ON i.UID = u.UserID " + "INNER JOIN books b ON i.BID = b.BID";
	            PreparedStatement preparedStatement = connection.prepareStatement(query);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            issueTableModel.setRowCount(0);
	            while (resultSet.next()) {
	                int IID = resultSet.getInt("IID");
	                int UID = resultSet.getInt("UID");
	                String username = resultSet.getString("Username");
	                int BID = resultSet.getInt("BID");
	                String Bookname=resultSet.getString("BookName");
	                Date idate  = resultSet.getDate("IssueDate");
	                int period  = resultSet.getInt("Period");
	                Date returndate = resultSet.getDate("ReturnDate");
	                Double fine = resultSet.getDouble("Fine");
	                issueTableModel.addRow(new Object[]{IID, UID, username, BID,Bookname, idate, period, returndate,fine});
	            }resultSet.close();
	            preparedStatement.close();
	            connection.close();
	            issueTableModel.fireTableDataChanged();
	        } catch (SQLException e) {
	            e.printStackTrace();}}}
