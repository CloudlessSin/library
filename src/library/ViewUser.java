package library;
import java.awt.*; import java.awt.event.*; import java.sql.*;import javax.swing.*;import javax.swing.table.DefaultTableModel;
public class ViewUser extends JFrame implements ActionListener {
	private ImageIcon icon = new ImageIcon("title.png"); private DefaultTableModel booksTableModel = new DefaultTableModel();
    private JTable table=new JTable(booksTableModel); JButton back= new JButton("Back");
    ViewUser(){ setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setVisible(true); setSize(1000, 650); setLayout(null);
         setIconImage(icon.getImage()); setLocationRelativeTo(null); setResizable(false); getContentPane().setBackground(Color.white);  setTitle("User Record");
         JPanel title = new JPanel(); JPanel BackPanel= new JPanel(); BackPanel.setBounds(0, 0, 1000, 30); BackPanel.add(back); BackPanel.setBackground(Color.white);
         back.setBounds(20 , 10, 68, 30); back.setForeground(Color.white);back.setBackground(new Color(0, 160, 10));back.setFont(new Font("Segoe UI", Font.BOLD, 15));
         back.setFocusable(false);main.setButtonHoverCursor(back);back.addActionListener(this);back.setPreferredSize(new Dimension(200, 30));
         title.setBounds(0, 30, 1000, 60); title.setLayout(new FlowLayout(FlowLayout.CENTER)); title.setBackground(Color.WHITE);
         JLabel titleLabel = new JLabel("User Record");titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 35));titleLabel.setForeground(new Color(0, 140, 0));
         titleLabel.setVerticalAlignment(SwingConstants.CENTER); title.add(titleLabel);
         JPanel panel1 = new JPanel(); panel1.setBackground(Color.WHITE);panel1.setBounds(0, 110, 1000, 650);panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
         booksTableModel.addColumn("User ID");booksTableModel.addColumn("Username");booksTableModel.addColumn("First Name");booksTableModel.addColumn("Last Name");
         booksTableModel.addColumn("Password");booksTableModel.addColumn("Admin");
         JScrollPane scroll = new JScrollPane(table); scroll.setPreferredSize(new Dimension(800, 420));
         table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15)); table.getTableHeader().setBackground(new Color(0, 160, 10));
         table.getTableHeader().setForeground(Color.white); table.getTableHeader().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0, 100, 10)));
         table.getTableHeader().setPreferredSize(new Dimension(0, 30)); table.getColumnModel().getColumn(0).setPreferredWidth(70); 
         table.getColumnModel().getColumn(1).setPreferredWidth(150);  table.getColumnModel().getColumn(2).setPreferredWidth(120); 
         table.getColumnModel().getColumn(3).setPreferredWidth(120);   table.getColumnModel().getColumn(4).setPreferredWidth(140); 
         table.getColumnModel().getColumn(5).setPreferredWidth(60);  table.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 2, Color.black));
         table.setRowHeight(26); table.setFont(new Font("Segoe UI", Font.PLAIN, 15)); table.setBackground(new Color(245, 250, 240));
    	 add(back); add(title); add(panel1); add(BackPanel); title.add(titleLabel); panel1.add(scroll); loadData();}
    @Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) { new admin(); setVisible(false);}}
	 private void loadData() { try {
	            Connection connection = Config.getConnection();
	            String query = "SELECT USERID, Username, FirstName, LastName, Password, Admin FROM users";
	            PreparedStatement preparedStatement = connection.prepareStatement(query);ResultSet resultSet = preparedStatement.executeQuery(); booksTableModel.setRowCount(0);
	            while (resultSet.next()) { int uid = resultSet.getInt("USERID");
	                String Username = resultSet.getString("Username"); String FirstName = resultSet.getString("FirstName"); String LastName = resultSet.getString("LastName");
	                String Password = resultSet.getString("Password"); Boolean Admin = resultSet.getBoolean("Admin");
	                booksTableModel.addRow(new Object[]{uid, Username, FirstName, LastName, Password,Admin}); }
	            resultSet.close(); preparedStatement.close(); connection.close();
	            booksTableModel.fireTableDataChanged(); } catch (SQLException e) { e.printStackTrace(); }}}
