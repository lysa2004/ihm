package tp3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class quiz extends JFrame {
	
	String questions[][] = new String[10][5];
	String answers[][] = new String[10][2];
	String userans[][] = new String[10][1];
	JLabel N, Q;
	JRadioButton op1, op2, op3, op4;
	ButtonGroup grpoptions;
	
    static int timer = 15;
    static int ans_given = 0;
    static int count = 0;
    
	
	quiz() {
		
		setBounds(50, 0, 1140, 700);
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		
		Icon img = new ImageIcon("C:\\Users\\HP\\OneDrive\\Images\\Saved Pictures\\quiz time.jpg");
		JLabel image = new JLabel(img);
		image.setBounds(0, 30, 1140, 250);
		add(image);
		
		//AFFICHER NUM QUESTION
		N = new JLabel();
		N.setBounds(100, 350, 50, 30);
		N.setFont(new Font("", Font.BOLD, 15));
		add(N);
		
		//AFFICHER QUESTION
		Q = new JLabel();
		Q.setBounds(130, 350, 900, 30);
		Q.setFont(new Font("", Font.BOLD, 20));
		add(Q);
		
		//LES QSTS ET REPS
		questions[0][0] = "Which is used to find and fix bugs in the Java programs.?";
        questions[0][1] = "JVM";
        questions[0][2] = "JDB";
        questions[0][3] = "JDK";
        questions[0][4] = "JRE";

        questions[1][0] = "What is the return type of the hashCode() method in the Object class?";
        questions[1][1] = "int";
        questions[1][2] = "Object";
        questions[1][3] = "long";
        questions[1][4] = "void";

        questions[2][0] = "Which package contains the Random class?";
        questions[2][1] = "java.util package";
        questions[2][2] = "java.lang package";
        questions[2][3] = "java.awt package";
        questions[2][4] = "java.io package";

        questions[3][0] = "An interface with no fields or methods is known as?";
        questions[3][1] = "Runnable Interface";
        questions[3][2] = "Abstract Interface";
        questions[3][3] = "Marker Interface";
        questions[3][4] = "CharSequence Interface";

        questions[4][0] = "In which memory a String is stored, when we create a string using new operator?";
        questions[4][1] = "Stack";
        questions[4][2] = "String memory";
        questions[4][3] = "Random storage space";
        questions[4][4] = "Heap memory";

        questions[5][0] = "Which of the following is a marker interface?";
        questions[5][1] = "Runnable interface";
        questions[5][2] = "Remote interface";
        questions[5][3] = "Readable interface";
        questions[5][4] = "Result interface";

        questions[6][0] = "Which keyword is used for accessing the features of a package?";
        questions[6][1] = "import";
        questions[6][2] = "package";
        questions[6][3] = "extends";
        questions[6][4] = "export";

        questions[7][0] = "In java, jar stands for?";
        questions[7][1] = "Java Archive Runner";
        questions[7][2] = "Java Archive";
        questions[7][3] = "Java Application Resource";
        questions[7][4] = "Java Application Runner";

        questions[8][0] = "Which of the following is a mutable class in java?";
        questions[8][1] = "java.lang.StringBuilder";
        questions[8][2] = "java.lang.Short";
        questions[8][3] = "java.lang.Byte";
        questions[8][4] = "java.lang.String";

        questions[9][0] = "Which of the following option leads to the portability and security of Java?";
        questions[9][1] = "Bytecode is executed by JVM";
        questions[9][2] = "The applet makes the Java code secure and portable";
        questions[9][3] = "Use of exception handling";
        questions[9][4] = "Dynamic binding between objects";
        
        answers[0][1] = "JDB";
        answers[1][1] = "int";
        answers[2][1] = "java.util package";
        answers[3][1] = "Marker Interface";
        answers[4][1] = "Heap memory";
        answers[5][1] = "Remote interface";
        answers[6][1] = "import";
        answers[7][1] = "Java Archive";
        answers[8][1] = "java.lang.StringBuilder";
        answers[9][1] = "Bytecode is executed by JVM";
        
        //LES OPTIONS
        op1 = new JRadioButton();
        op1.setBounds(170, 400, 500, 30);
        op1.setBackground(Color.WHITE);
        op1.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(op1);
        
        op2 = new JRadioButton();
        op2.setBounds(170, 445, 500, 30);
        op2.setBackground(Color.WHITE);
        op2.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(op2);
        
        op3 = new JRadioButton();
        op3.setBounds(170, 485, 500, 30);
        op3.setBackground(Color.WHITE);
        op3.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(op3);
        
        op4 = new JRadioButton();
        op4.setBounds(170, 525, 500, 30);
        op4.setBackground(Color.WHITE);
        op4.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(op4);
        
        //UNE SEULE ET UNIQUE REPONSE
        grpoptions = new ButtonGroup();
        grpoptions.add(op1);
        grpoptions.add(op2);
        grpoptions.add(op3);
        grpoptions.add(op4);
        
        JButton next = new JButton("Next");
        next.setBounds(800, 410, 200, 35);
        next.setFont(new Font("", Font.PLAIN, 20));
		next.setBackground(Color.green);
		next.setForeground(Color.WHITE);
		//next.addActionListener(this);
		add(next);
		
		JButton ll = new JButton("Lifeline");
        ll.setBounds(800, 490, 200, 35);
        ll.setFont(new Font("", Font.PLAIN, 20));
		ll.setBackground(Color.green);
		ll.setForeground(Color.WHITE);
		//next.addActionListener(this);
		add(ll);
		
		JButton s = new JButton("Submit");
        s.setBounds(800, 570, 200, 35);
        s.setFont(new Font("", Font.PLAIN, 20));
		s.setBackground(Color.green);
		s.setForeground(Color.WHITE);
		//next.addActionListener(this);
		add(s);
		
		start(count);
		
		setVisible(true);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		String time = "Time Left: " + timer + "Seconds"; //15
		g.setColor(Color.RED);
		g.setFont(new Font("", Font.BOLD, 17));
		
		if (timer > 0) {
			g.drawString(time, 813, 404);
		} else {
			g.drawString("Times UP!!", 813, 404);
		}
		
		timer--;
		
		try {
			Thread.sleep(1000);
			repaint();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (ans_given == 1) {
			ans_given = 0;
			timer = 15;
		} else if (timer < 0) {
			timer = 15;
			
			//si il n'a pas choisi et n'a pas repondu a la question
			if (grpoptions.getSelection() == null) {
				userans[count][0] = "";
			} else {
				userans[count][0] = grpoptions.getSelection().getActionCommand();
			}
			count++;
			start(count);
		}
	}
	
	public void start(int count) {
		//incrementer le num de question
		N.setText("" + (count + 1) + ". ");
		//afficher la question correspendante au num
		Q.setText(questions[count][0]);
		//afficher les options
		op1.setText(questions[count][1]);
		op1.setActionCommand(questions[count][1]);
		
		op2.setText(questions[count][2]);
		op2.setActionCommand(questions[count][2]);
		
		op3.setText(questions[count][3]);
		op3.setActionCommand(questions[count][3]);
		
		op4.setText(questions[count][4]);
		op4.setActionCommand(questions[count][4]);
		
		//ne pas afficher le choix precedent
		grpoptions.clearSelection();
	}
	
	public static void main(String [] args) {
		new quiz();
	}
}
