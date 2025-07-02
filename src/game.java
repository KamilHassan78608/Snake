import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.*;

import javax.swing.*;


public class game extends JPanel implements ActionListener, KeyListener{
    
    private final int Unit_Size = 25;
    private final int Delay = 100;
    private int lenght = 5;
    public int score = 0;

    private final ArrayList<Point> snake = new ArrayList<>();
    private Point food;
    private boolean running;
    private char direction = 'R';
    private Timer timer;

    game(){
        this.setVisible(true);
        this.setPreferredSize(new Dimension(1280, 720));
        this.setFocusable(true);
        this.setOpaque(true);
        this.setBackground(Color.gray);
        this.addKeyListener(this);
        startGame();
    }

    public void startGame(){
        snake.clear();
        snake.add(new Point(Unit_Size * 4, Unit_Size));
        snake.add(new Point(Unit_Size * 3, Unit_Size));
        snake.add(new Point(Unit_Size * 2, Unit_Size));
        snake.add(new Point(Unit_Size, Unit_Size));
        newFood();
        running = true;
        timer = new Timer(Delay, this);
        timer.start();
    }

    public void newFood(){
        Random rand = new Random();
        int x = rand.nextInt(1280/Unit_Size) * Unit_Size;
        int y = rand.nextInt(720/Unit_Size) * Unit_Size;
        food = new Point(x, y);
    }

    public void paintComponent(Graphics g){
        super.paintComponents(g);
        draw(g);
    }

    public void draw(Graphics g){
        if (running) {

            g.setColor(Color.white); // Or another good color
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Score: " + score, 10, 20); // Draw score at top-left

            //drawing the food
            g.setColor(Color.RED);
            g.fillOval(food.x, food.y, Unit_Size, Unit_Size);

            //drawing the snake
            for(int i =0; i < snake.size(); i++){
                if (i==0) {
                    g.setColor(Color.GREEN);
                }else{
                    g.setColor(Color.LIGHT_GRAY);
                }
                Point p = snake.get(i);
                g.fillRect(p.x, p.y, Unit_Size, Unit_Size);
            }
        }else{
            new Game_Over(score);
        }
    }

    public void move(){
        Point head = snake.get(0);
        Point new_Point = new Point(head);

        switch (direction) {
            case 'U' -> new_Point.y -= Unit_Size;
            case 'D' -> new_Point.y += Unit_Size;
            case 'L' -> new_Point.x -= Unit_Size;
            case 'R' -> new_Point.x += Unit_Size;
        }

        if (new_Point.x < 0 || new_Point.x >= 1280 || new_Point.y < 0 || new_Point.y >= 720 || snake.contains(new_Point)) {
            running =false;
            timer.stop();
            return;
        }

        
        snake.add(0, new_Point);
        if (new_Point.equals(food)) {
            newFood();
            score++;
            for(int i = lenght; i<=lenght; i++){
                snake.add(new Point(Unit_Size * lenght, Unit_Size));
                lenght = i;
            }
        }else{
            snake.remove(snake.size()-1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
            if(direction != 'R') direction ='L';
            break;
            case KeyEvent.VK_RIGHT:
            if(direction != 'L') direction ='R';
            break;
            case KeyEvent.VK_UP:
            if(direction != 'D') direction ='U';
            break;
            case KeyEvent.VK_DOWN:
            if(direction != 'U') direction ='D';
            break;
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
