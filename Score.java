package tp3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Score extends JFrame implements ActionListener{
	
	Score(String name, int score) {
		setBounds(400, 100, 650, 550);
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		
		Icon img = new ImageIcon("C:\\Users\\HP\\OneDrive\\Images\\Saved Pictures\\quiz time.jpg");
		//Image i2 = img.getImage().getScaledInstance(300, 250, Image.SCALE_DEFAULT);
		//ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(img);
		image.setBounds(0, 200, 400, 250);
		add(image);
		
		//AFFICHER NUM QUESTION
		JLabel heading = new JLabel("Thank you " + name + " for playing Kiddy quiz");
		heading.setBounds(45, 30, 700, 30);
		heading.setFont(new Font("Tahoma", Font.PLAIN, 26));
		add(heading);
		
		JLabel lscore = new JLabel("Your score is " + score);
		lscore.setBounds(420, 200, 300, 30);
		lscore.setFont(new Font("Tahoma", Font.PLAIN, 26));
		add(lscore);
		
		JButton s = new JButton("Play again");
        s.setBounds(450, 270, 120, 30);
		s.setBackground(Color.green);
		s.setForeground(Color.WHITE);
		s.addActionListener(this);
		add(s);
		
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		setVisible(false);
		new Login();
	}
	
	public static void main(String [] args) {
		new Score("user", 0);
	}
}
