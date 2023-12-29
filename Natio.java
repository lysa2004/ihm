
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Natio extends JFrame implements ActionListener {

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

    Natio(String name) {
        this.name = name;
        setBounds(50, 0, 1140, 700);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        Icon img = new ImageIcon("C:\\Users\\HP\\OneDrive\\Images\\Saved Pictures\\quiz time.jpg");
        JLabel image = new JLabel(img);
        image.setBounds(0, 30, 1140, 250);
        add(image);

        // AFFICHER NUM QUESTION
        N = new JLabel();
        N.setBounds(100, 350, 50, 30);
        N.setFont(new Font("", Font.BOLD, 15));
        add(N);

        // AFFICHER QUESTION
        Q = new JLabel();
        Q.setBounds(130, 350, 900, 30);
        Q.setFont(new Font("", Font.BOLD, 20));
        add(Q);

        // LES QSTS ET REPS
        questions[0][0] = "Quelle est la nationalité des habitants de l'Afrique du Sud?";
        questions[0][1] = "Française";
        questions[0][2] = "Sud-Africaine";
        questions[0][3] = "Anglaise";
        questions[0][4] = "Espagnole";

        questions[1][0] = "Quelle est la nationalité des habitants des Pays-Bas ?";
        questions[1][1] = "Néerlandaise";
        questions[1][2] = "Japonaise";
        questions[1][3] = "Américaine";
        questions[1][4] = "Palestinienne";

        questions[2][0] = "Quelle est la nationalité des habitants du Danemark ?";
        questions[2][1] = "Allemande";
        questions[2][2] = "Émiratie";
        questions[2][3] = "Américaine";
        questions[2][4] = "Danoise";

        questions[3][0] = "Quelle est la nationalité des habitants des États-Unis ?";
        questions[3][1] = "Brésilienne";
        questions[3][2] = "Américaine";
        questions[3][3] = "Colombienne";
        questions[3][4] = "Saoudienne";

        questions[4][0] = "Quelle est la nationalité des habitants de Madagascar ?";
        questions[4][1] = "Turque";
        questions[4][2] = "Italienne";
        questions[4][3] = "Malgache";
        questions[4][4] = "Anglaise";

        
       
        
        answers[0][1] = "Sud-Africaine";
        answers[1][1] = "Néerlandaise";
        answers[2][1] = "Danoise";
        answers[3][1] = "Américaine";
        answers[4][1] = "Malgache";
        

        // LES OPTIONS
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

        // UNE SEULE ET UNIQUE REPONSE
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
        if (ae.getSource() == next) {
            repaint();
            // les options tjrs s'affichent mm apres appuyer next ou ll
            op1.setEnabled(true);
            op2.setEnabled(true);
            op3.setEnabled(true);
            op4.setEnabled(true);

            // si appuyer sur next afficher prochaine question
            ans_given = 1;
            if (grpoptions.getSelection() == null) {
                userans[count][0] = "";
            } else {
                userans[count][0] = grpoptions.getSelection().getActionCommand();
            }

            // quand on arrive a question10 on peut pas appuyer next puisque ya plus de
            // questions
            // 8 parceque on appuie 8fois pour arriver a la 10eme question
            if (count == 4) {
                next.setEnabled(false);
                s.setEnabled(true);
            }

            count++;
            start(count);

        } else if (ae.getSource() == ll) {
            // pour aider l'utilisateur juste dans les questions precisees avec les reponses
            // precisees
            if (count == 2 || count == 4 || count == 6 || count == 8 || count == 9) {
                op2.setEnabled(false);
                op3.setEnabled(false);
            } else {
                op1.setEnabled(false);
                op4.setEnabled(false);
            }
            // utiliser une fois
            ll.setEnabled(false);
        } else if (ae.getSource() == s) {
            ans_given = 1;
            if (grpoptions.getSelection() == null) {
                userans[count][0] = "";
            } else {
                userans[count][0] = grpoptions.getSelection().getActionCommand();
            }

            for (int i = 0; i < userans.length; i++) {
                if (userans[i][0].equals(answers[i][1])) {
                    score += 10;
                } else {
                    score += 0;
                }
            }
            setVisible(false); // score
            new Score(name, score);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);

        String time = "Time Left: " + timer + "Seconds"; // 15
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
            // apres appuyer ll tt les reponses s'affichent apres mykhlas lw9t snn yb9aw op1
            // et 4 false
            op1.setEnabled(true);
            op2.setEnabled(true);
            op3.setEnabled(true);
            op4.setEnabled(true);

            if (count == 8) {
                next.setEnabled(false);
                s.setEnabled(true);
            }
            if (count == 9) { // submit button
                if (grpoptions.getSelection() == null) {
                    userans[count][0] = "";
                } else {
                    userans[count][0] = grpoptions.getSelection().getActionCommand();
                }

                for (int i = 0; i < userans.length; i++) {
                    if (userans[i][0].equals(answers[i][1])) {
                        score += 10;
                    } else {
                        score += 0;
                    }
                }
                setVisible(false);
                new Score(name, score);
            } else { // next button
                // avoir les choix du user
                if (grpoptions.getSelection() == null) {
                    // si il n'a pas choisi et n'a pas repondu a la question
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
        // incrementer le num de question
        N.setText("" + (count + 1) + ". ");
        // afficher la question correspendante au num
        Q.setText(questions[count][0]);
        // afficher les options
        op1.setText(questions[count][1]);
        op1.setActionCommand(questions[count][1]);

        op2.setText(questions[count][2]);
        op2.setActionCommand(questions[count][2]);

        op3.setText(questions[count][3]);
        op3.setActionCommand(questions[count][3]);

        op4.setText(questions[count][4]);
        op4.setActionCommand(questions[count][4]);

        // ne pas afficher le choix precedent
        grpoptions.clearSelection();
    }

    public static void main(String[] args) {
        new Natio("user");
    }
}
