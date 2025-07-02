import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Main extends JFrame {
    
    static JLabel title, footer;
    static JPanel mainpanel;
    static JButton newGame, continueGame, highScore, exit;

    Main(){
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(1280, 720);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Show confirmation or just block closing
                JOptionPane.showMessageDialog(rootPane, "Press Exit to close the game");
            }
        });

        title = new JLabel("Welcome to Snake game");
        title.setPreferredSize(new Dimension(400, 100));
        title.setOpaque(true);
        title.setBackground(Color.BLACK);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("impact", Font.BOLD, 22));
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        mainpanel = new JPanel();
        mainpanel.setBackground(Color.GRAY);
        mainpanel.setLayout(new GridBagLayout());
        

        newGame = new JButton("New Game");
        newGame.setPreferredSize(new Dimension(220, 70));
        newGame.setFont(new Font("arial", Font.PLAIN, 20));
        highScore = new JButton("High Score");
        highScore.setPreferredSize(new Dimension(220, 70));
        highScore.setFont(new Font("arial", Font.PLAIN, 20));
        exit = new JButton("Exit.");
        exit.setPreferredSize(new Dimension(220, 70));
        exit.setFont(new Font("arial", Font.PLAIN, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainpanel.add(newGame, gbc);
        gbc.gridy++;
        mainpanel.add(highScore, gbc);
        gbc.gridy++;
        mainpanel.add(exit, gbc);
        
        newGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new new_Game();
            }
            
        });

        highScore.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new High();
            }
            
        });

        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
            
        });

        footer = new JLabel("Click to Continue!");
        footer.setPreferredSize(new Dimension(400, 100));
        footer.setOpaque(true);
        footer.setBackground(Color.BLACK);
        footer.setForeground(Color.WHITE);
        footer.setFont(new Font("impact", Font.BOLD, 22));
        footer.setVerticalAlignment(SwingConstants.CENTER);
        footer.setHorizontalAlignment(SwingConstants.CENTER);


        this.add(title, BorderLayout.NORTH);
        this.add(mainpanel, BorderLayout.CENTER);
        this.add(footer, BorderLayout.SOUTH);
        
        this.setVisible(true);
    }
}
