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

public class Page2 extends JFrame implements ActionListener{
	
	JButton rules, exit , nationalite , langues;
	JTextField tfname;
    JLabel myLabel , head ;
    ImageIcon icon ;
    String fname ;
	
	Page2(String fname) {

        String cheminAbsolu = "C:\\Users\\User\\Downloads\\childrens_day_3-wallpaper-1600x900.jpg";
        icon = new ImageIcon(cheminAbsolu);
       myLabel = new JLabel(icon);
       myLabel.setSize(1400, 750);
       myLabel.setLayout(new BorderLayout());
	
		this.fname = fname;
		JLabel head = new JLabel("Bienvenu " + fname + " a Kiddy Quiz");
		head.setBounds(50, 60, 600, 100);
		head.setFont(new Font("Arial", Font.BOLD, 30));
		head.setForeground(Color.BLACK);
		add(head);

        JLabel sujet = new JLabel("Choisisez le niveau qui vous convient");
        sujet.setFont(new Font("Arial", Font.BOLD, 20));
        sujet.setForeground(Color.BLACK);
        sujet.setBounds(500, 60,600, 100);   //(lemplacment horizentale , verticale , tban ga3 el kelma)
        add (sujet);
		
		rules = new OvalButton("Niveau Facile");
		//rules.setBounds(718, 290, 120, 25);
		rules.setBackground(Color.GREEN);
		rules.setForeground(Color.BLACK);
        rules.setFont(new Font("Arial", Font.BOLD, 18));
        rules.setBounds(610,220,200,60);
		rules.addActionListener(this);
		add(rules);
		
		exit = new OvalButton("Niveau Difficile");
		//exit.setBounds(918, 290, 100, 25);
		exit.setBackground(Color.RED);
		exit.setForeground(Color.BLACK);
        exit.setBounds(610,300,220,60);
        exit.setFont(new Font("Arial", Font.BOLD, 18));
		exit.addActionListener(this);
		add(exit);

       
		add(myLabel);
		setSize(800, 600);
        setLocationRelativeTo(null);
        add(myLabel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
		//setLocation(400, 100);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == rules) {
			//String name = tfname.getText();
			setVisible(false);
			//new Page2(name);
		}else if (ae.getSource() == exit) {
			setVisible(false);
		}
	}
	
	public static void main(String[] args) {
		new Page2("User");
	}
}