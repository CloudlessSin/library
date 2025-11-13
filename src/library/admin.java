package library;import java.awt.*;
import java.awt.event.*;import javax.swing.*;
public class admin extends JFrame implements ActionListener {
	ImageIcon icon= new ImageIcon("title.png");JPanel body = new JPanel();JPanel header = new JPanel();JLabel Title= new JLabel("Library Management System");
	JButton VBooks= new JButton("View Books");JButton VUsers= new JButton("View Users");JButton ABook= new JButton("Add Book");JButton AUser= new JButton("Add User");
	JButton IBook= new JButton("Issue Book");JButton VIBooks= new JButton("Issued Books");JButton RBooks= new JButton("Return Book");JButton Logout= new JButton("Log out");
	JButton RemoveBook = new JButton("Remove Book");private JFrame currentwindow= new JFrame();	
	admin(){
		VBooks.addActionListener(this);VUsers.addActionListener(this);ABook.addActionListener(this);AUser.addActionListener(this);
		IBook.addActionListener(this);VIBooks.addActionListener(this);RBooks.addActionListener(this);Logout.addActionListener(this);
		Dimension buttonSize = new Dimension(170, 100);
		VBooks.setPreferredSize(buttonSize); VUsers.setPreferredSize(buttonSize); ABook.setPreferredSize(buttonSize); 
		AUser.setPreferredSize(buttonSize); IBook.setPreferredSize(buttonSize);
		VIBooks.setPreferredSize(buttonSize); RBooks.setPreferredSize(buttonSize);  Logout.setPreferredSize(buttonSize);
        Font buttonFont = new Font("MV Boli", Font.BOLD, 21);
        VBooks.setFont(buttonFont);  VUsers.setFont(buttonFont); ABook.setFont(buttonFont); AUser.setFont(buttonFont);
        IBook.setFont(buttonFont); VIBooks.setFont(buttonFont); RBooks.setFont(buttonFont); Logout.setFont(buttonFont);
        VBooks.setFocusable(false); VUsers.setFocusable(false); ABook.setFocusable(false); AUser.setFocusable(false);
        IBook.setFocusable(false); VIBooks.setFocusable(false); RBooks.setFocusable(false);  Logout.setFocusable(false);
        Color buttonColor = (new Color(20, 160, 10));
        VBooks.setBackground(buttonColor); VUsers.setBackground(buttonColor); ABook.setBackground(buttonColor); AUser.setBackground(buttonColor);
        IBook.setBackground(buttonColor); VIBooks.setBackground(buttonColor); RBooks.setBackground(buttonColor); Logout.setBackground(new Color(240,10,10));
        VBooks.setForeground(Color.WHITE); ABook.setForeground(Color.WHITE); VUsers.setForeground(Color.WHITE); AUser.setForeground(Color.WHITE);
        IBook.setForeground(Color.WHITE); VIBooks.setForeground(Color.WHITE); RBooks.setForeground(Color.WHITE); Logout.setForeground(Color.WHITE);
        RemoveBook.setPreferredSize(buttonSize); RemoveBook.setFont(buttonFont); RemoveBook.setFocusable(false);
        RemoveBook.setBackground(buttonColor); RemoveBook.setForeground(Color.WHITE); RemoveBook.addActionListener(this);
        main.setButtonHoverCursor(VBooks); main.setButtonHoverCursor(ABook); main.setButtonHoverCursor(VUsers); main.setButtonHoverCursor(AUser);
        main.setButtonHoverCursor(IBook); main.setButtonHoverCursor(VIBooks); main.setButtonHoverCursor(RBooks); 
        main.setButtonHoverCursor(RemoveBook); main.setButtonHoverCursor(Logout);     
		header.setBounds(0, 0, 860, 80); header.setLayout(null); header.setBackground(Color.WHITE);
		body.setBounds(0,82,860,450); body.setLayout(new FlowLayout(FlowLayout.CENTER, 10,25));	body.setBackground(Color.WHITE);
		Title.setBounds(0,0,860,75); Title.setVerticalAlignment(SwingConstants.CENTER);Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setFont(new Font("Segoe UI",Font.BOLD,40)); Title.setForeground(new Color(0, 130, 0));				
		setTitle("Home"); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setSize(860,420); setLayout(null); setResizable(false);
		setLocationRelativeTo(null); setIconImage(icon.getImage()); add(header); header.add(Title);
		add(body); body.add(VBooks); body.add(VUsers); body.add(ABook); body.add(AUser); body.add(IBook); body.add(VIBooks); body.add(RBooks);
		//body.add(RemoveBook);
		body.add(Logout);setVisible(true);}
	@Override
    public void actionPerformed(ActionEvent e) {
		if (currentwindow!=null) {
			currentwindow.setVisible(false);
		}
		
		
        if (e.getSource() == VBooks) {
        	setVisible(false);
        	new ViewBooks();
        } else if (e.getSource() == VUsers) {
        	new ViewUser();
        	setVisible(false);
        } else if (e.getSource() == ABook) {
            currentwindow=new Addbook();
        } else if (e.getSource() == AUser) {
        	currentwindow=new AddUser();
        } else if (e.getSource() == IBook) {
        	currentwindow=new  IssueBook();
        } else if (e.getSource() == VIBooks) {
           setVisible(false);
        	new ViewIssuedBooks();
        } else if (e.getSource() == RBooks) {
        	currentwindow=new ReturnBooks();
        }else if(e.getSource()==RemoveBook) {
        	currentwindow=new RemoveB();
        }
        else if (e.getSource()== Logout) {
        	UserSession.clearSession();
        	dispose();
        	new login();
        }
    }
}