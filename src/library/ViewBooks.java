package library;import java.awt.*;import java.awt.event.*;import java.sql.*;import javax.swing.*;import javax.swing.table.DefaultTableModel;
public class ViewBooks extends JFrame implements ActionListener {
    private ImageIcon icon = new ImageIcon("title.png"); private DefaultTableModel booksTableModel = new DefaultTableModel(); private JTable table=new JTable(booksTableModel);
    private JTextField searchField = new JTextField(); private JButton searchButton = new JButton("Search");
    JButton back = new JButton("Back"); JButton update = new JButton("Update"); JButton remove = new JButton("Delete");
    ViewBooks() { setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); setVisible(true); setSize(1000, 650); setLayout(null);
        setIconImage(icon.getImage()); setLocationRelativeTo(null); setResizable(false); getContentPane().setBackground(Color.white); setTitle("Book Record");
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 16)); searchField.addActionListener(this); searchField.setPreferredSize(new Dimension(180, 28));
        searchField.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(10, 160, 10)));
        searchButton.setFont(new Font("Segoe UI", Font.PLAIN, 15)); searchButton.addActionListener(this); searchButton.setFocusable(false); 
        searchButton.setPreferredSize(new Dimension(80, 27));searchButton.setBackground(new Color(10, 160, 10)); searchButton.setForeground(Color.white);
        JPanel title = new JPanel(); title.setBounds(0, 30, 1000, 60); title.setLayout(new FlowLayout(FlowLayout.CENTER)); title.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel("Book Record"); titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 35)); titleLabel.setForeground(new Color(0, 140, 0));    
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);title.add(titleLabel);
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(Color.white); searchPanel.add(searchField); searchPanel.add(searchButton); searchPanel.setBounds(0, 90, 1000, 35);
        JPanel BackPanel= new JPanel(); BackPanel.setBounds(0, 0, 1000, 40); BackPanel.add(back); BackPanel.add(update);
        BackPanel.add(remove); BackPanel.setBackground(Color.white); BackPanel.setLayout(null);
        back.setBounds(20 , 10, 68, 30); back.setForeground(Color.white); back.setBackground(new Color(0, 160, 10)); back.setFont(new Font("Segoe UI", Font.BOLD, 15));
        back.setFocusable(false); main.setButtonHoverCursor(back); back.addActionListener(this);        
        update.setBounds(750 , 10, 85, 30); update.setForeground(Color.white);update.setBackground(new Color(0, 160, 10)); update.setFont(new Font("Segoe UI", Font.BOLD, 15));
        update.setFocusable(false);main.setButtonHoverCursor(update);update.addActionListener(this);
        remove.setBounds(850 , 10, 85, 30); remove.setForeground(Color.white);remove.setBackground(new Color(0, 160, 10)); remove.setFont(new Font("Segoe UI", Font.BOLD, 15));
        remove.setFocusable(false); main.setButtonHoverCursor(remove);remove.addActionListener(this);
        if (!UserSession.isAdmin()) { update.setVisible(false);remove.setVisible(false); }
        JPanel panel1 = new JPanel();panel1.setBackground(Color.WHITE);panel1.setBounds(0, 130, 1000, 650);panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        booksTableModel.addColumn("Book ID"); booksTableModel.addColumn("Title");booksTableModel.addColumn("Genre");
        booksTableModel.addColumn("Author");booksTableModel.addColumn("Price PHP");
        JScrollPane scroll = new JScrollPane(table); scroll.setPreferredSize(new Dimension(800, 400));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));table.getTableHeader().setBackground(new Color(0, 160, 10));
        table.getTableHeader().setForeground(Color.white); table.getTableHeader().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0, 100, 10)));
        table.getTableHeader().setPreferredSize(new Dimension(0, 30));
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(220);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);
        table.getColumnModel().getColumn(4).setPreferredWidth(70);
        table.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 2, Color.black));
        table.setRowHeight(26);table.setFont(new Font("Segoe UI", Font.PLAIN, 15));table.setBackground(new Color(245, 250, 240));
        add(BackPanel); add(title); add(panel1); add(searchPanel); title.add(titleLabel); panel1.add(scroll); loadData();
    } @Override public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton || e.getSource() == searchField) {
            String query = searchField.getText().trim(); performSearch(query);} else if (e.getSource() == back) { if (UserSession.isAdmin()) {new admin();
           } else { new user();}dispose(); }else if (e.getSource()==update) {new UpdateBook();}else if (e.getSource()==remove) {new RemoveB(); } }
    private void loadData() { try {
            Connection connection = Config.getConnection(); String query = "SELECT BID, BookName, Price, Genre, Author FROM books";
            PreparedStatement preparedStatement = connection.prepareStatement(query);ResultSet resultSet = preparedStatement.executeQuery();booksTableModel.setRowCount(0);
            while (resultSet.next()) {
                int bid = resultSet.getInt("BID");
                String bookName = resultSet.getString("BookName");
                double price = resultSet.getDouble("Price");
                String genre = resultSet.getString("Genre");
                String author = resultSet.getString("Author");
                booksTableModel.addRow(new Object[]{bid, bookName, genre, author, price});
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
            booksTableModel.fireTableDataChanged();
        } catch (SQLException e) {
            e.printStackTrace(); } }
    private void performSearch(String query) {
        try {
            Connection connection = Config.getConnection();
            String searchQuery = "SELECT BID, BookName, Price, Genre, Author FROM books WHERE BookName LIKE ? OR Author LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
            preparedStatement.setString(1, "%" + query + "%");
            preparedStatement.setString(2, "%" + query + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            booksTableModel.setRowCount(0);
            while (resultSet.next()) {
                int bid = resultSet.getInt("BID");
                String bookName = resultSet.getString("BookName");
                String genre = resultSet.getString("Genre");
                String author = resultSet.getString("Author");
                double price = resultSet.getDouble("Price");
                booksTableModel.addRow(new Object[]{bid, bookName, genre, author, price});
            } resultSet.close(); preparedStatement.close(); connection.close(); booksTableModel.fireTableDataChanged(); } catch (SQLException ex) { ex.printStackTrace(); } }}
