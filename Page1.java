import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

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

public class Page1 extends JFrame implements ActionListener{
	
	JButton rules, exit , nationalite , langues;
	JTextField tfname;
    JLabel myLabel , head ;
    ImageIcon icon ;
	
	Page1() {
        String cheminAbsolu = "C:\\Users\\User\\Downloads\\picpage1.jpg";
        icon = new ImageIcon(cheminAbsolu);
       myLabel = new JLabel(icon);
       myLabel.setSize(1400, 750);
       myLabel.setLayout(new BorderLayout());
       myLabel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
	
		
		JLabel head = new JLabel("Kiddy Quiz");
		head.setBounds(10, 20, 600, 60);
		head.setFont(new Font("Arial", Font.BOLD, 50));
		head.setForeground(Color.BLACK);
		add(head);

         // JLabel sujet = new JLabel(": TOUS LES SECRETS DU MONDE ENFIN RÉVÉLÉS !");
         // sujet.setFont(new Font("Kristen ITC", Font.BOLD, 25));
         // sujet.setForeground(Color.BLACK);
         // sujet.setBounds(400, 60,950, 45);   //(lemplacment horizentale , verticale , tban ga3 el kelma)
         // add (sujet);
		

        JLabel name = new JLabel("Entrez votre prénom : ");
        name.setFont(new Font("Arial", Font.BOLD, 20));
        name.setForeground(Color.BLACK);
        name.setBounds(1090,325,250,50); 
        add(name);
    
        tfname = new JTextField();
        tfname.setFont(new Font("Arial", Font.BOLD, 17));
        //tfname.setMaximumSize(new Dimension(500,100)); // 3erd
        //tfname.setPreferredSize(new Dimension(500,500)); //toul
        tfname.setBounds(1100,380,190,28);
        add(tfname);
        
     JLabel categorie = new JLabel("Sélectionner une catégorie pour votre quiz:");
        categorie.setFont(new Font("Arial", Font.BOLD, 16));
        categorie.setForeground(Color.BLACK);
        categorie.setBounds(1035,410,500,50); 
        add(categorie);
		
		rules = new OvalButton("Capitales");
		rules.setBackground(Color.ORANGE);
		rules.setForeground(Color.BLACK);
        rules.setFont(new Font("Arial", Font.BOLD, 18));
       rules.setBounds(1050,470,137,50);
		rules.addActionListener(this);
		add(rules);
		
		exit = new OvalButton("Drapeaux");
		//exit.setBounds(918, 290, 100, 25);
		exit.setBackground(Color.GREEN);
		exit.setForeground(Color.BLACK);
       exit.setBounds(1200,470,137,50);
        exit.setFont(new Font("Arial", Font.BOLD, 18));
		exit.addActionListener(this);
		add(exit);

        nationalite = new OvalButton("Nationalités");
		//nationalite .setBounds(18, 90, 120, 25);
		nationalite .setBackground(Color.PINK);
		nationalite .setForeground(Color.BLACK);
        nationalite .setBounds(1050,550,137,50);
        nationalite.setFont(new Font("Arial", Font.BOLD, 18));
		nationalite .addActionListener(this);
		add(nationalite );
       // myLabel.add(nationalite);


        langues = new OvalButton("Langues");
		//langues.setBounds(718, 290, 120, 25);
		langues.setBackground(Color.CYAN);
		langues.setForeground(Color.BLACK);
        langues.setBounds(1200,550,137,50);
        langues.setFont(new Font("Arial", Font.BOLD, 18));
		langues.addActionListener(this);
		add(langues);

		add(myLabel);
		setSize(1500, 740);
        setLocationRelativeTo(null);
        add(myLabel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setLayout(new BorderLayout());
		//setLocation(400, 100);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
        String fname = tfname.getText();
		if(ae.getSource() == rules ) {
			
			setVisible(false);
            myLabel.removeAll();  // Supprimer les composants actuels de la fenêtre
            new Page2(fname, myLabel);  // Créer une instance de Page2 avec la même fenêtre
		}else{
            if( ae.getSource() == exit ){
             setVisible(false);
             myLabel.removeAll();  // Supprimer les composants actuels de la fenêtre
             new Page2drap(fname, myLabel); 
            } else{
                if(ae.getSource() == nationalite){
                 setVisible(false);
                 myLabel.removeAll();  // Supprimer les composants actuels de la fenêtre
                 new Page2nat(fname, myLabel);
                }else if(ae.getSource() == langues){
                 setVisible(false);
                 myLabel.removeAll();  // Supprimer les composants actuels de la fenêtre
                 new Page2lang(fname, myLabel);
                }
            }
        }
	}
	public static void main(String[] args) {
		new Page1();
	}
}