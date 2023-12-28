package tp3;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Rules extends JFrame implements ActionListener{
	
	String name;
	JButton start, back;
	
	Rules(String name) {
		this.name = name;
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel head = new JLabel("Welcome " + name + " To Kiddy Quiz");
		head.setBounds(50, 40, 700, 30);
		head.setFont(new Font("Kristen ITC", Font.BOLD, 28));
		head.setForeground(Color.BLACK);
		add(head);
		
		JLabel rules = new JLabel();
		rules.setBounds(20, 90, 700, 350);
		rules.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rules.setText(
				"<html>"+
		        "1. " + "<br></br>" +
				"2. " + "<br></br>" +
		        "<html>"
				);
		add(rules);
		
		start = new JButton("Start");
		start.setBounds(255, 400, 100, 30);
		start.setBackground(Color.GREEN);
		start.setForeground(Color.BLACK);
		start.addActionListener(this);
		add(start);
		
	    back = new JButton("Back");
		back.setBounds(455, 400, 100, 30);
		back.setBackground(Color.RED);
		back.setForeground(Color.BLACK);
		back.addActionListener(this);
		add(back);
		
		setSize(800, 550);
		setLocation(250, 100);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == start) {
			setVisible(false);
			new quiz(name);
		}else {
			setVisible(false);
			new Login();
		}
	}
	
	public static void main(String [] args) {
		new Rules("");
	}

}
