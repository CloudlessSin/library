package library;import java.awt.*;import java.awt.event.*;import java.sql.*;import javax.swing.*;
public class AddUser extends JFrame implements ActionListener{JButton resetB = new JButton("Reset");JButton addB=new JButton("Add");
	ImageIcon icon= new ImageIcon("title.png");JRadioButton adminr= new JRadioButton("Admin");JRadioButton userr= new JRadioButton("User");
	ButtonGroup adminstatus = new ButtonGroup(); JTextField usernamet = new JTextField(17); JPasswordField passwordt = new JPasswordField(17);
	 JTextField fnamet = new JTextField(17); JTextField lnamet = new JTextField(17);
	 AddUser(){
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);setVisible(true); setSize(300, 390);setIconImage(icon.getImage()); setLocationRelativeTo(null);setResizable(false);		
		 JPanel panel = new JPanel();   panel.setBounds(0, 40, 300, 400); panel.setBackground(Color.white); panel.setLayout(null);
	        JPanel header = new JPanel(); header.setBounds(0, 0, 300, 40); header.setBackground(new Color(10, 150, 10)); header.setLayout(null);	        
	        JLabel headerl = new JLabel("New User"); JLabel usernamel = new JLabel("Username"); JLabel passwordl = new JLabel("Password");
	        JLabel fnamel = new JLabel("First Name"); JLabel lnamel = new JLabel("Last Name");
	        Font labelFont = new Font("Segoe UI", Font.BOLD, 16);
	       usernamel.setFont(labelFont); passwordl.setFont(labelFont); fnamel.setFont(labelFont); lnamel.setFont(labelFont); 
	       adminr.setFont(new Font("Segoe UI", Font.BOLD, 15)); userr.setFont(new Font("Segoe UI", Font.BOLD, 15));
	       Color fontcolor = new Color(10, 160, 10);
	        usernamel.setForeground(fontcolor); passwordl.setForeground(fontcolor); fnamel.setForeground(fontcolor); lnamel.setForeground(fontcolor);
	        adminr.setForeground(fontcolor); userr.setForeground(fontcolor); adminr.setBackground(Color.WHITE); userr.setBackground(Color.WHITE);
	        usernamel.setBounds(18, 15, 86, 22); passwordl.setBounds(18, 60, 86, 22); fnamel.setBounds(18, 105, 86, 22); lnamel.setBounds(18, 150, 86, 22);
	        usernamet.setBounds(110, 15, 160, 25); passwordt.setBounds(110, 60, 160, 25); fnamet.setBounds(110, 105, 160, 25);
	        lnamet.setBounds(110, 150, 160, 25); adminr.setBounds(50, 185, 75, 25);  userr.setBounds(160,185,75,25);
	        addB.setBounds(150, 225, 85, 30);resetB.setBounds(40, 225, 85, 30); 
	        usernamet.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(10, 160, 10)));
	        passwordt.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(10, 160, 10)));
	        fnamet.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(10, 160, 10)));
	        lnamet.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(10, 160, 10)));
	        headerl.setFont(new Font("Segoe UI", Font.BOLD, 28));headerl.setForeground(Color.white); headerl.setBounds(0, 0, 300, 38);
	        headerl.setVerticalAlignment(SwingConstants.CENTER);headerl.setHorizontalAlignment(SwingConstants.CENTER);
	        resetB.setFont(new Font("Segoe UI", Font.BOLD, 18));resetB.addActionListener(this);resetB.setForeground(new Color(10, 160, 10));resetB.setBackground(Color.WHITE);
	        resetB.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(10, 160, 10)));
	        addB.setFont(new Font("Segoe UI", Font.BOLD, 18));addB.setBackground(new Color(10, 160, 10));addB.setForeground(Color.WHITE); addB.addActionListener(this);
	        adminr.setFocusable(false);userr.setFocusable(false);addB.setFocusable(false);resetB.setFocusable(false);
	        this.add(panel);this.add(header);
	        adminstatus.add(adminr); adminstatus.add(userr);
	        header.add(headerl);panel.add(usernamel);
	         panel.add(passwordl);panel.add(fnamel); panel.add(lnamel);panel.add(adminr);	        
	        panel.add(userr);panel.add(usernamet);panel.add(passwordt); panel.add(fnamet);panel.add(lnamet);panel.add(addB);panel.add(resetB);
	}@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==addB) {
			insertuser();
			setVisible(false);
		}else if(e.getSource()==resetB) {
        	usernamet.setText(""); passwordt.setText(""); fnamet.setText(""); 
        	lnamet.setText("");}}
		private void insertuser() {try {
			Connection connection = Config.getConnection();
				String insertq= "INSERT INTO users(Username, Password, FirstName, LastName, Admin) VALUES (?,?,?,?,?)";
				PreparedStatement prepstate = connection.prepareStatement(insertq);
				
				String username = usernamet.getText();
				String password = new String(passwordt.getPassword()); 
				String firstName = fnamet.getText();
				String lastName = lnamet.getText();
				boolean isAdmin = adminr.isSelected(); 
				
				prepstate.setString(1,username);
				prepstate.setString(2,password);
				prepstate.setString(3,firstName);
				prepstate.setString(4,lastName);
				prepstate.setBoolean(5,isAdmin);
				
				int rowsInserted = prepstate.executeUpdate();
				
				if (rowsInserted > 0) {
		                JOptionPane.showMessageDialog(this, "User added successfully!");
		               
		            } else {
		                JOptionPane.showMessageDialog(this, "User insertion failed.");
		            }
		            
	
		            prepstate.close();
		            connection.close();
		            
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());}}}
