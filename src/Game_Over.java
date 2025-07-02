import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.sql.*;

public class Game_Over extends JFrame {

    static JLabel label;
    static JButton main, restart;

    Game_Over(int i) {
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        label = new JLabel("Score: " + i);
        label.setBounds(0, 100, 1280, 620);
        label.setFont(new Font("Arial", Font.BOLD, 80));
        label.setForeground(Color.BLACK);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(Color.GRAY);

        show(i);

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

        restart = new JButton("Restart");
        restart.setBounds(670, 450, 180, 60);
        restart.setFont(new Font("impact", Font.BOLD, 35));
        restart.setForeground(Color.BLACK);
        restart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new new_Game();
            }
            
        });


        label.add(main);
        label.add(restart);
        this.add(label);
        this.setVisible(true);
    }


    public void show(int ind){
        String url = "jdbc:mysql://localhost:3306/Game";
        String user = "root";
        String password = "kamilhassan786";

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        
            // Establish connection
            Connection conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(true); // Optional, but ensures changes are committed
        
            // Prepare SQL query
            String q1 = "INSERT INTO Score(value) VALUES (?)";
            PreparedStatement stmt = conn.prepareStatement(q1);
        
            // Set the value to insert (make sure 'i' is declared and holds a valid value)
            stmt.setInt(1, ind);
        
            // Execute insertion
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Score inserted successfully!");
            }
        
            // Close resources
            stmt.close();
            conn.close();
        
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "MySQL JDBC Driver not found: " + e.getMessage());
        }
    }
}
