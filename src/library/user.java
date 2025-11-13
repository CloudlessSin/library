package library;
import java.awt.*; import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; import javax.swing.*;
public class user extends JFrame implements ActionListener {
    JButton VBooks = new JButton("View Books"); JButton Logout = new JButton("Log out"); ImageIcon icon= new ImageIcon("title.png");
    public user() { JPanel body = new JPanel(); JPanel header = new JPanel(); JLabel Title = new JLabel("Library Management System");
        header.setBounds(0, 0, 600, 80); header.setLayout(null); header.setBackground(Color.WHITE);
        body.setBounds(0, 82, 600, 450); body.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30)); body.setBackground(Color.WHITE);
        Dimension buttonSize = new Dimension(200, 120); Font buttonFont = new Font("MV Boli", Font.BOLD, 30);
        VBooks.setPreferredSize(buttonSize); Logout.setPreferredSize(buttonSize); VBooks.setFont(buttonFont);
        Logout.setFont(buttonFont); VBooks.setFocusable(false); Logout.setFocusable(false);
        VBooks.setBackground(new Color(20, 160, 10)); Logout.setBackground(new Color(240, 10, 10));
        VBooks.setForeground(Color.WHITE); Logout.setForeground(Color.WHITE);
        Title.setBounds(0, 0, 600, 75); Title.setVerticalAlignment(SwingConstants.CENTER); Title.setHorizontalAlignment(SwingConstants.CENTER);
        Title.setFont(new Font("Segoe UI", Font.BOLD, 30)); Title.setForeground(new Color(0, 130, 0));
        add(header);
        header.add(Title);
        add(body); body.add(VBooks); body.add(Logout);
        main.setButtonHoverCursor(VBooks); main.setButtonHoverCursor(Logout);
        setLayout(null); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 350); setResizable(false);setIconImage(icon.getImage());setTitle("Home");setVisible(true);
        
        VBooks.addActionListener(this); Logout.addActionListener(this);
       }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == VBooks) {
        	setVisible(false);
        	new ViewBooks();
        }else if (e.getSource()==Logout){
        	setVisible(false);
        	UserSession.clearSession();
        	new login(); }	} }
