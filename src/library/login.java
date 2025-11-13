package library; import java.awt.*; import java.awt.event.*;
import java.sql.*; import javax.swing.*; import library.Config;
public class login extends JFrame implements ActionListener, KeyListener {
    JButton loginB = new JButton("Login"); ImageIcon icon = new ImageIcon("title.png"); ImageIcon right= new ImageIcon("art.png");
    JTextField usernameT; JPasswordField passwordT; JCheckBox showp = new JCheckBox("Show Password");
    login() { JLabel title = new JLabel("Login"); JLabel usernameL = new JLabel("Username"); JLabel passwordL = new JLabel("Password");
        usernameT = new JTextField("Enter Username");passwordT = new JPasswordField("Password");
        JPanel panel = new JPanel(); panel.setLayout(null); JPanel rightP = new JPanel();
        rightP.setLayout(null); JLabel label = new JLabel(); label.setBounds(30, 80, 200, 200); label.setIcon(right); JLabel motto = new JLabel("Discover, Learn, Grow");
        motto.setForeground(new Color(0, 130, 0)); motto.setFont(new Font("Segoe UI", Font.BOLD, 22)); motto.setBounds(20, 40, 250, 30);
        rightP.add(label);  rightP.add(motto);
        title.setBounds(0, 15, 430, 55); title.setForeground(Color.WHITE); title.setFont(new Font("MV Boli", Font.BOLD, 35));
        title.setVerticalAlignment(SwingConstants.CENTER); title.setHorizontalAlignment(SwingConstants.CENTER);
        usernameL.setBounds(40, 90, 150, 25);
        usernameL.setFont(new Font("Segoe UI", Font.BOLD, 16));
        usernameL.setForeground(Color.WHITE);
        usernameT.setBounds(50, 120, 330, 25); usernameT.setBackground(new Color(0, 130, 0)); usernameT.setForeground(Color.WHITE);
        usernameT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE)); usernameT.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        passwordL.setBounds(40, 165, 150, 25); passwordL.setFont(new Font("Segoe UI", Font.BOLD, 16)); passwordL.setForeground(Color.WHITE);
        passwordT.setBounds(50, 195, 330, 25); passwordT.setBackground(new Color(0, 130, 0)); passwordT.setForeground(Color.WHITE);
        passwordT.setFont(new Font("Segoe UI", Font.PLAIN, 20)); passwordT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        showp.setBounds(45, 225, 150, 30); showp.setBackground(new Color(0, 130, 0)); showp.setForeground(Color.WHITE);
        showp.setFocusable(false); showp.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        loginB.setBounds(50, 265, 330, 32); loginB.setForeground(Color.white); loginB.setFont(new Font("Segoe UI", Font.BOLD, 20));
        loginB.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(20, 160, 20))); loginB.setBackground(new Color(30, 180, 20));
        loginB.setFocusable(false); loginB.addActionListener(this);
        usernameT.addFocusListener(new FocusListener() { @Override
            public void focusGained(FocusEvent e) {if (usernameT.getText().equals("Enter Username")) { usernameT.setText(""); } }
            @Override public void focusLost(FocusEvent e) { if (usernameT.getText().isEmpty()) { usernameT.setText("Enter Username"); } } });
        showp.addItemListener(new ItemListener() { @Override public void itemStateChanged(ItemEvent e) { if (e.getStateChange() == ItemEvent.SELECTED) {                  
                    passwordT.setEchoChar((char) 0);  } else { passwordT.setEchoChar('\u25cf');  }} });
        if (showp.isSelected()) { passwordT.setEchoChar((char) 0);} else { passwordT.setEchoChar('\u25cf'); }passwordT.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                char[] password = passwordT.getPassword();
                if (password.length > 0 && new String(password).equals("Password")) {
                    passwordT.setText("");}}
            @Override
            public void focusLost(FocusEvent e) {
                char[] password = passwordT.getPassword();
                if (password.length == 0) {
                    passwordT.setText("Password");}}});
        panel.setBounds(0, 0, 430, 400); panel.setBackground(new Color(0, 130, 0));
        rightP.setBounds(431, 0, 300, 400); rightP.setBackground(Color.white);
        setTitle("Login"); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setSize(720, 390); setLayout(null);  setResizable(false);
        add(rightP); add(panel); setIconImage(icon.getImage()); setLocationRelativeTo(null); main.setButtonHoverCursor(loginB); usernameT.addKeyListener(this);
        passwordT.addKeyListener(this);
        panel.add(title); panel.add(usernameL); panel.add(usernameT); panel.add(passwordL); panel.add(passwordT); panel.add(showp); panel.add(loginB); setVisible(true);}
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginB) {
            login();    }}
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            login(); }}
    @Override
    public void keyReleased(KeyEvent e) {}
    private void login() { String username = usernameT.getText().trim(); String password = new String(passwordT.getPassword()).trim();
        try {
            Connection connection = Config.getConnection();
            String query = "SELECT username, password, admin FROM users WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            	boolean isAdmin = resultSet.getBoolean("admin"); UserSession.setAdmin(isAdmin); UserSession.setUsername(username); UserSession.setPassword(password);
                JOptionPane.showMessageDialog(this, "Welcome " + username+"!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                if (isAdmin) {
                    setVisible(false);
                    new admin();} else { dispose(); new user(); }  } else { JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Error",
                        JOptionPane.ERROR_MESSAGE);} resultSet.close(); preparedStatement.close(); } catch (SQLException ex) { ex.printStackTrace(); }}}
