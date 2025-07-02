import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class new_Game extends JFrame{

    static game g1;

    new_Game(){
        this.setTitle("Snake Game");
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        
         // Add custom window close behavior
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                new Main();
            }
        });

        g1 = new game();
        
        this.add(g1);
        this.setVisible(true);
    }
}
