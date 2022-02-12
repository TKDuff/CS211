
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Wordle {
	static Random rand = new Random();
	static Dictionary dict = new Dictionary();
	
	static String word = dict.getWord(rand.nextInt(2315) + 12974);
	static int y = 350;
	int count = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                new Wordle();
            }
        });
    }

    public Wordle() {
        JFrame frame = new JFrame();
        frame.add(panel);
        frame.add(createButton(), BorderLayout.PAGE_END);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private final DrawingPanel panel = new DrawingPanel();
    
    
    private JTextField createButton() {
        JTextField text = new JTextField();
        text.addActionListener(new ActionListener(){
        	
            public void actionPerformed(ActionEvent e) {
            	String input = text.getText();
            	text.setText("");
            	if(!valid(input)) {
            		System.out.println(input + " is not valid");
            	} else {
                char[] boxValues = generateRandomValues(input); 
                Circle circle = new Circle(boxValues, input, y);
                panel.addCircle(circle);
                count++; 
            	}
            	
            	if(count == 6 || input.equals(word)) {
            		text.setEnabled(false);
            	}
            	
            }
            
            private boolean valid(String input) {
        		if(input.length() != 5) {
        			return false;
        		}
        		for(int i = 0; i < 12972; i++) {
        			if(input.equals(dict.getWord(i))) {
        				return true;
        			}
        		}
        		return false;
        	}
            
        });
        
        return text; 
    }
    

    private char[] generateRandomValues(String input) {
        char[] values = {'b','b','b','b','b'};
        for(int i = 0; i < 5; i++) {
        	for(int j = 0; j < 5; j++) {
        		if(input.charAt(i) == word.charAt(j)) {
        			if(i == j)
        				values[i] = 'g';
        			else
        				values[i] = 'y';
        		}
        	}
        }
        return values;
    }

    class Circle {

       char[] boxValues;
       int x = 88;
       int y;
       String input;

        public Circle(char[] boxValues, String input, int y) {
            this.boxValues = boxValues;
            this.y = y;
            this.input = input;
        }
        
       public void draw(Graphics g) {
    	   
    	   g.setFont(new Font ("Courier New", 1, 17));
    	   for(int i = 0; i < 5; i++) {
    		   g.setColor(Color.BLACK);
    		   if(boxValues[i] == 'g')
    			  g.setColor(Color.GREEN); 
    		   else if(boxValues[i] == 'y') {
    			  g.setColor(Color.YELLOW); 
    		   }
    		
    		   g.fillRect(x, y, 40, 40);
    		   g.setColor(Color.LIGHT_GRAY);
    		   g.drawString(String.valueOf(input.charAt(i)), x+5, y+35);
    		   x += 45;
    	   }
    	   x = 88;
    	   y-=50;
       } 
       
       public void drawWarning(Graphics g) {
    	   g.setColor(Color.BLACK);
    	   g.setFont(new Font ("Courier New", 1, 25));
    	   g.drawString(word, 150, 20);
       }
    }

    class DrawingPanel extends JPanel {

        List<Circle> circles = new ArrayList<>();
        
       
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (Circle circle : circles) {
                circle.draw(g);
                if(circles.size() == 6) {
                	circle.drawWarning(g);
                }
            }

        }

        public void addCircle(Circle circle) {
            circles.add(circle);
            repaint();
        }
        

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(400, 400);
        }
    }
} 