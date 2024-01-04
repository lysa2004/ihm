import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

//2EME PAGE DES NIVEAUX ( DES CAPITALES)

class OvalButton extends JButton {
    public OvalButton(String label) {
        super(label);
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(getBackground());
        }

        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }

    Shape shape;

    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }
}

public class Page2nat extends JFrame implements ActionListener{
	
	JButton rules, exit , nationalite , langues ,  back;
	JTextField tfname;
    JLabel myLabel ;
    static JLabel head;
    ImageIcon icon ;
    String fname ;
	
	Page2nat(String fname , JLabel myJLabel) {

        String cheminAbsolu = "C:\\Users\\User\\Downloads\\picpage1.jpg";
        icon = new ImageIcon(cheminAbsolu);
       myLabel = new JLabel(icon);
       myLabel.setSize(1400, 750);
       myLabel.setLayout(new BorderLayout());
       setTitle("GeoKids");
       //this.myLabel = myLabel;
		this.fname = fname;
		JLabel head = new JLabel("Bienvenu " + fname + " a Kiddy Quiz");
		head.setBounds(15, 5, 600, 100);
		head.setFont(new Font("Arial", Font.BOLD, 30));
		head.setForeground(Color.BLACK);
		add(head);
        ImageIcon windowIcon = new ImageIcon("C:\\Users\\User\\OneDrive\\Documents\\IHM\\pic.jpg");
        setIconImage(windowIcon.getImage());
 

        JLabel sujet = new JLabel("Choisisez le niveau qui vous convient :");
        sujet.setFont(new Font("Arial", Font.BOLD, 20));
        sujet.setForeground(Color.BLACK);
        sujet.setBounds(950, 300,600, 100);   //(lemplacment horizentale , verticale , tban ga3 el kelma)
        add (sujet);
		
		rules = new OvalButton("Niveau Facile");
		//rules.setBounds(718, 290, 120, 25);
		rules.setBackground(Color.YELLOW);
		rules.setForeground(Color.BLACK);
        rules.setFont(new Font("Arial", Font.BOLD, 18));
        rules.setBounds(1100,450,200,50);
		rules.addActionListener(this);
		add(rules);
		
		exit = new OvalButton("Niveau Difficile");
		//exit.setBounds(918, 290, 100, 25);
		exit.setBackground(Color.RED);
		exit.setForeground(Color.BLACK);
        exit.setBounds(1100,530,200,50);
        exit.setFont(new Font("Arial", Font.BOLD, 18));
		exit.addActionListener(this);
		add(exit);

        back = new OvalButton("Retourner");
		//exit.setBounds(918, 290, 100, 25);
		back.setBackground(Color.LIGHT_GRAY);
		back.setForeground(Color.BLACK);
        back.setBounds(20,592,125,85);
        back.setFont(new Font("Arial", Font.BOLD, 18));
		back.addActionListener(this);
		add(back);


       
		add(myLabel);
		setSize(1400, 750);
       
        add(myLabel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == rules ) {
           myLabel.removeAll();  // Supprimer les composants actuels de la fenêtre
           new NationaliteF();
           setVisible(false);  // Créer une instance de Page2 avec la même fenêtre
       
       }else {
           if (ae.getSource() == exit){
            myLabel.removeAll();  // Supprimer les composants actuels de la fenêtre
            new NationaliteD();
            setVisible(false);
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Page1();
        }
       }
   }
	public static void main(String[] args) {
	new Page2("User", head);
	}
}