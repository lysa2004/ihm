package tp3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class quiz extends JFrame implements ActionListener{
	
	String questions[][] = new String[10][5];
	String answers[][] = new String[10][2];
	String userans[][] = new String[10][1];
	JLabel N, Q;
	JRadioButton op1, op2, op3, op4;
	ButtonGroup grpoptions;
	
    static int timer = 15;
    static int ans_given = 0;
    static int count = 0;
    
    JButton next, ll, s;
    
    static int score = 0;
    String name;
	
	quiz(String name) {
		this.name = name;
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
		questions[0][0] = "Quelle est la capitale de l'Algérie ?";
        questions[0][1] = "Alger";
        questions[0][2] = "Paris";
        questions[0][3] = "Londres";
        questions[0][4] = "Oran";

        questions[1][0] = "Quelle est la capitale de la Tunisie ?";
        questions[1][1] = "Tokyo";
        questions[1][2] = "New York";
        questions[1][3] = "Tunis";
        questions[1][4] = "Gaza";

        questions[2][0] = "Quelle est la capitale de Luxembourg ?";
        questions[2][1] = "Berlin";
        questions[2][2] = "Luxembourg";
        questions[2][3] = "Dubai";
        questions[2][4] = "Chicago";

        questions[3][0] = "Quelle est la capitale du Brésil ?";
        questions[3][1] = "Brasilia";
        questions[3][2] = "Nairobi";
        questions[3][3] = "Bogota";
        questions[3][4] = "Riyad";

        questions[4][0] = "Quelle est la capitale de France ?";
        questions[4][1] = "Istanbul";
        questions[4][2] = "Paris";
        questions[4][3] = "Rome";
        questions[4][4] = "Liverpool";

        questions[5][0] = "Quelle est la capitale de Canada ?";
        questions[5][1] = "Milan";
        questions[5][2] = "Boston";
        questions[5][3] = "Moscou";
        questions[5][4] = "Ottawa";

        questions[6][0] = "Quelle est la capitale de la Turkie ?";
        questions[6][1] = "Istanbul";
        questions[6][2] = "Alger";
        questions[6][3] = "Ankara";
        questions[6][4] = "Toronto";

        questions[7][0] = "Quelle est la capitale de la Chine ?";
        questions[7][1] = "Oran";
        questions[7][2] = "Pékin";
        questions[7][3] = "Manchester-city";
        questions[7][4] = "Barcelone";

        questions[8][0] = "Quelle est la capitale du Japon ?";
        questions[8][1] = "Tokyo";
        questions[8][2] = "Marseille";
        questions[8][3] = "Kyoto";
        questions[8][4] = "Londres";

        questions[9][0] = "Quelle est la capitale de l'Arabie Saoudite ?";
        questions[9][1] = "Alger";
        questions[9][2] = "Los-Angeles";
        questions[9][3] = "Riyad";
        questions[9][4] = "Madrid";
        
        answers[0][1] = "Alger";
        answers[1][1] = "Tunis";
        answers[2][1] = "Luxembourg";
        answers[3][1] = "Brasilia";
        answers[4][1] = "Paris";
        answers[5][1] = "Ottawa";
        answers[6][1] = "Ankara";
        answers[7][1] = "Pékin";
        answers[8][1] = "Tokyo";
        answers[9][1] = "Riyad";
        
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
        
        next = new JButton("Next");
        next.setBounds(800, 410, 200, 35);
        next.setFont(new Font("", Font.PLAIN, 20));
		next.setBackground(Color.green);
		next.setForeground(Color.WHITE);
		next.addActionListener(this);
		add(next);
		
		ll = new JButton("Lifeline");
        ll.setBounds(800, 490, 200, 35);
        ll.setFont(new Font("", Font.PLAIN, 20));
		ll.setBackground(Color.green);
		ll.setForeground(Color.WHITE);
		ll.addActionListener(this);
		add(ll);
		
		s = new JButton("Submit");
        s.setBounds(800, 570, 200, 35);
        s.setFont(new Font("", Font.PLAIN, 20));
		s.setBackground(Color.green);
		s.setForeground(Color.WHITE);
		s.addActionListener(this);
		add(s);
		
		start(count);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == next) {
			repaint();
			//les options tjrs s'affichent mm apres appuyer next ou ll
			op1.setEnabled(true);
			op2.setEnabled(true);
			op3.setEnabled(true);
			op4.setEnabled(true);
			
			//si appuyer sur next afficher prochaine question
			ans_given = 1;
			if (grpoptions.getSelection() == null) {
				userans[count][0] = "";
			} else {
				userans[count][0] = grpoptions.getSelection().getActionCommand();
			}
			
			//quand on arrive a question10 on peut pas appuyer next puisque ya plus de questions
			// 8 parceque on appuie 8fois pour arriver a la 10eme question
			if(count == 8) {
				next.setEnabled(false);
				s.setEnabled(true);
			}
			
			count++;
			start(count);
			
		}else if (ae.getSource() == ll) {
			//pour aider l'utilisateur juste dans les questions precisees avec les reponses precisees
			if (count == 2 || count == 4 || count == 6 || count == 8 || count == 9) {
				op2.setEnabled(false);
				op3.setEnabled(false);
			}else {
				op1.setEnabled(false);
				op4.setEnabled(false);
			}
			//utiliser une fois
			ll.setEnabled(false);
		}else if (ae.getSource() == s){
			ans_given = 1;
			if (grpoptions.getSelection() == null) {
				userans[count][0] = "";
			} else {
				userans[count][0] = grpoptions.getSelection().getActionCommand();
			}
			
			for (int i = 0; i < userans.length; i++) {
				if (userans[i][0].equals(answers[i][1])) {
					score += 10;
				}else {
					score += 0;
				}
			}
			setVisible(false); //score
			new Score(name, score);
		}
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
			//apres appuyer ll tt les reponses s'affichent apres mykhlas lw9t snn yb9aw op1 et 4 false
			op1.setEnabled(true);
			op2.setEnabled(true);
			op3.setEnabled(true);
			op4.setEnabled(true);
			
			if(count == 8) {
				next.setEnabled(false);
				s.setEnabled(true);
			}
			if (count == 9) { //submit button
				if (grpoptions.getSelection() == null) {
					userans[count][0] = "";
				} else {
					userans[count][0] = grpoptions.getSelection().getActionCommand();
				}
				
				for (int i = 0; i < userans.length; i++) {
					if (userans[i][0].equals(answers[i][1])) {
						score += 10;
					}else {
						score += 0;
					}
				}
				setVisible(false); 
				new Score(name, score);
			}else { //next button
			//avoir les choix du user
			    if (grpoptions.getSelection() == null) {
			    	//si il n'a pas choisi et n'a pas repondu a la question
				    userans[count][0] = "";
		     	} else {
				    userans[count][0] = grpoptions.getSelection().getActionCommand();
			}
			count++;
			start(count);
			}
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
		new quiz("user");
	}
}
