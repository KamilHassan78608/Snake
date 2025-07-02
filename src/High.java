import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class High extends JFrame {
    
    private int highScore;
    static JLabel label, title, footer;
    static JButton main, reset;

    High(){
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setResizable(false);

        showHigh();
        
        label = new JLabel("Score: " + highScore );
        label.setFont(new Font("Arial", Font.BOLD, 80));
        label.setForeground(Color.BLACK);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(Color.gray);

        title = new JLabel("The Highest Score");
        title.setBounds(0, 0, 1280, 100);
        title.setOpaque(true);
        title.setBackground(Color.BLACK);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("impact", Font.BOLD, 22));
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        main = new JButton("Menu");
        main.setBounds(430, 450, 180, 60);
        main.setFont(new Font("impact", Font.BOLD, 35));
        main.setForeground(Color.BLACK);
        main.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Main();
            }
            
        });

        reset = new JButton("Reset");
        reset.setBounds(670, 450, 180, 60);
        reset.setFont(new Font("impact", Font.BOLD, 35));
        reset.setForeground(Color.BLACK);
        reset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                resetData();
                dispose();
                new Main();
            }
            
        });

        footer = new JLabel("Beat it!");
        footer.setBounds(0, 620, 1280, 100);
        footer.setOpaque(true);
        footer.setBackground(Color.BLACK);
        footer.setForeground(Color.WHITE);
        footer.setFont(new Font("impact", Font.BOLD, 22));
        footer.setVerticalAlignment(SwingConstants.CENTER);
        footer.setHorizontalAlignment(SwingConstants.CENTER);



        label.add(title);
        label.add(footer);
        label.add(main);
        label.add(reset);
        this.add(label);
        this.setVisible(true);
    }

    public void resetData(){
        String url = "jdbc:mysql://localhost:3306/Game";
        String user = "root";
        String password = "kamilhassan786";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(true); // optional
        
            String q = "DELETE FROM Score"; // This will delete all records from the 'Score' table
            PreparedStatement stmt = conn.prepareStatement(q);
        
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println(rowsDeleted + " rows deleted from Score table.");
            } else {
                System.out.println("No rows to delete.");
            }
        
            stmt.close();
            conn.close();
        
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "MySQL JDBC Driver not found: " + e.getMessage());
        }
        
    }

    public void showHigh(){
        String url = "jdbc:mysql://localhost:3306/Game";
        String user = "root";
        String password = "kamilhassan786";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT MAX(value) AS highest FROM Score;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                highScore = rs.getInt("highest");
            } else {
                System.out.println("No scores found.");
            }

            rs.close();
            stmt.close();
            conn.close();

        }catch(SQLException e){
            JOptionPane alert = new JOptionPane("Error! " + e);
        } catch (ClassNotFoundException e) {
            JOptionPane alet = new JOptionPane("Error! " + e);
        }
    }
}
