import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.*;
public class FlagQuizf extends JFrame {

    private int score = 0;
    private int currentQuestionIndex = 0;
    private JLabel scoreLabel;
    private JLabel questionLabel;
    private JRadioButton option1;
    private JRadioButton option2;
    private JRadioButton option3;
    private JRadioButton option4;
    private JLabel flagLabel;
    private ButtonGroup group;
    private JButton nextButton;
    private JButton helpButton;
    private Map<Question, List<String>> eliminatedChoicesMap;
    private List<Question> questions;

    public FlagQuizf() {
        //set up the JFrame
        setTitle("Quizz Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 740);
        setLocationRelativeTo(null);

        // Initialize questions list before adding questions
         questions = new ArrayList<>();
  
     // Create questions
         List<String> choices1 = new ArrayList<>();
            choices1.add("Algerie");
            choices1.add("Arabie Saoudite");
            choices1.add("Espagne");
            choices1.add("Brésil");
        
           questions.add(new Question( "A quel pays appartient ce drapeau ?", choices1,"Algerie", "algerie.jpg"));
        
            List<String> choices2 = new ArrayList<>();
            choices2.add("Arabie Saoudite");
            choices2.add("Allemagne");
            choices2.add("Mexique");
            choices2.add("Portugal");
        
            questions.add(new Question( "A quel pays appartient ce drapeau ?",choices2, "Arabia Saoudite", "saudi.jpg"));
        
            List<String> choices3 = new ArrayList<>();
            choices3.add("Italie");
            choices3.add("Chine");
            choices3.add("Espangne");
            choices3.add("Portugal");
        
           questions.add(new Question( "A quel pays appartient ce drapeau ?", choices3,"Espangne", "espagne.jpg"));
        
            List<String> choices4 = new ArrayList<>();
            choices4.add("Arabie Saoudite");
            choices4.add("Brésil");
            choices4.add("Chine");
            choices4.add("Russie");
        
            questions.add(new Question( "A quel pays appartient ce drapeau ?",choices4, "Bresil", "bresil.jpg"));
        
            List<String> choices5 = new ArrayList<>();
            choices5.add("Canada");
            choices5.add("Irak");
            choices5.add("Mexique");
            choices5.add("tunis");
        
            questions.add(new Question( "A quel pays appartient ce drapeau ?", choices5,"Canada", "Flag_of_Canada.png"));
    

         // Create components
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 30));
        //questionLabel = new JLabel();
        //questionLabel.setFont(new Font("Arial", Font.BOLD, 40));
        flagLabel = new JLabel();
      // flagLabel.setAlignmentX(1000);
        //flagLabel.setPreferredSize(new Dimension(10, 10)); // Set the preferred size for the flag image
        eliminatedChoicesMap = new HashMap<>();
        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();
        
       
        helpButton = new JButton("Aide ?");
        nextButton = new JButton("Suivant");
        // Group the radio buttons
        group = new ButtonGroup();
        group.add(option1);
        group.add(option2);
        group.add(option3);
        group.add(option4);


        initComponents();
        setupLayout();
        updateUI();

        setVisible(true);
    }



    private void initComponents() {
        scoreLabel = new JLabel("Ton Score:");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 30));
    
        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        questionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        questionLabel.setHorizontalAlignment(JLabel.LEFT); // Centre le texte horizontalement
            questionLabel.setVerticalAlignment(JLabel.CENTER); // Centre le texte verticalement
            questionLabel.setVerticalTextPosition(JLabel.CENTER); // Positionne le texte au centre verticalement
            questionLabel.setHorizontalTextPosition(JLabel.LEFT); 
            questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100)); 
      
        option1 = createStyledRadioButton();
        option2 = createStyledRadioButton();
        option3 = createStyledRadioButton();
        option4 = createStyledRadioButton();
    
        group = new ButtonGroup();
        group.add(option1);
        group.add(option2);
        group.add(option3);
        group.add(option4);
    
       
        helpButton = createStyledButton("Aide ?");
        nextButton = createStyledButton("Suivant");
         helpButton.addActionListener(e -> help());
        nextButton.addActionListener(e -> handleNextButtonClick());
       
    }

    private JRadioButton createStyledRadioButton() {
        JRadioButton radioButton = new JRadioButton();
        radioButton.setFont(new Font("Arial", Font.PLAIN, 20));
        radioButton.setFocusPainted(false);
        radioButton.setBorderPainted(false);
        return radioButton;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(150, 50));
        return button;
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        JPanel quizPanel = createQuizPanel();
        JPanel buttonPanel = createButtonPanel();
        JPanel scorePanel = createScorePanel();
        add(scorePanel, BorderLayout.NORTH);
        add(quizPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createQuizPanel() {
        JPanel quizPanel = new JPanel();
        quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.Y_AXIS));
       // quizPanel.setLayout(new GridLayout(7, 1, 10, 10));
        quizPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 100, 20));
        quizPanel.setBackground(new Color(176, 224, 228));

        quizPanel.add(questionLabel);
        quizPanel.add(Box.createVerticalGlue());
        quizPanel.add(flagLabel);
        JPanel choicesPanel = createChoicesPanel();
        quizPanel.add(choicesPanel);

        return quizPanel;
    }

    private JPanel createChoicesPanel() {
        JPanel choicesPanel = new JPanel(new GridLayout(4, 1, 0, 5));
        choicesPanel.setBackground(new Color(176, 224, 228));

        styleChoice(option1, Color.BLACK);
        styleChoice(option2, Color.BLACK);
        styleChoice(option3, Color.BLACK);
        styleChoice(option4, Color.BLACK);

        choicesPanel.add(option1);
        choicesPanel.add(option2);
        choicesPanel.add(option3);
        choicesPanel.add(option4);

        return choicesPanel;
    }

    private JPanel createScorePanel() {
        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        scorePanel.setBackground(new Color(176, 224, 228));

        JLabel scoreTitleLabel = new JLabel("Ton Score:");
        scoreTitleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        scoreTitleLabel.setForeground(Color.BLACK);

        scorePanel.add(scoreTitleLabel);
        scorePanel.add(scoreLabel);

        return scorePanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        buttonPanel.setBackground(new Color(176, 224, 228));

       styleButton(helpButton, "Aide ?", new Color(254, 214, 206), Color.DARK_GRAY);
        styleButton(nextButton, "Suivant", new Color(250, 116, 97), Color.WHITE);
       

        buttonPanel.add(helpButton);
        buttonPanel.add(nextButton);

        return buttonPanel;
    }

    private void styleChoice(JRadioButton choice, Color textColor) {
        choice.setForeground(textColor);
        choice.setFont(new Font("Arial", Font.BOLD, 25));
        choice.setBackground(new Color(254, 214, 206));
        choice.setBorder(BorderFactory.createLineBorder(new Color(254, 214, 206), 9));
        choice.setMargin(new Insets(10, 20, 50, 20));
    }

    private void styleButton(JButton button, String text, Color backgroundColor, Color foregroundColor) {
        button.setText(text);
        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
    }

  
    
    private void updateUI() {

        Question currentQuestion = getCurrentQuestion();
        scoreLabel.setText("<html><div style='color: rgb(250, 116, 97);font-weight:bold;text-align:left; font-size:30px;'>" +
                "  " + score + "</div></html>");

        questionLabel.setText("<html><div style='font-size: 16px; color: black; text-align: center;padding-right:10px;padding-left:100px;'>" +
                "Question:<br/>" + currentQuestion.getQuestion() + "</div></html>");
            // Load the image using the specified file name
            String imagePath = "C:\\Users\\User\\OneDrive\\Desktop\\projectihm\\" + currentQuestion.getFlagImagePath();
            ImageIcon originalIcon = new ImageIcon(imagePath);

            // Resize the image to fit the preferred size
            Image resizedImage = originalIcon.getImage().getScaledInstance( 150, 80, Image.SCALE_SMOOTH);
           
            // Create a new ImageIcon with the resized image
            ImageIcon resizedIcon = new ImageIcon(resizedImage);

            // Set the resized icon to the flagLabel
            flagLabel.setIcon(resizedIcon);
            flagLabel.setHorizontalTextPosition(JLabel.CENTER);
            flagLabel.setVerticalTextPosition(JLabel.CENTER);
          flagLabel.setHorizontalAlignment(JLabel.CENTER); 
           flagLabel.setVerticalAlignment(JLabel.CENTER); 

                
        List<String> choices = currentQuestion.getChoices();
        option1.setText(choices.get(0));
        option2.setText(choices.get(1));
        option3.setText(choices.get(2));
        option4.setText(choices.get(3));

        group.clearSelection();
        enableAllOptions();
        helpButton.setEnabled(true);
    }

    private void handleNextButtonClick() {
        if (getCurrentQuestion().isCorrectAnswer(getSelectedAnswer())) {
            score++;
        }

        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            updateUI();
        } else {
            showFinalScore();
        }
    }

    private void showFinalScore() {
        String message;
        String title = "Fin du Quizz";

        if (score >= 3) {
            message = "<html><div style='text-align: center;'>" +
                    "<p style='font-size: 25px; color: rgb(250, 116, 97);'> 🎉 Bravo le petit champion ! 🎉 </p>" +
                    "<p style='font-size: 20px;'>Tu as réussi avec un super score de : " + score + " 🌟 <br/>" +
                    "Continue comme ça, tu es incroyable !</p></div></html>";
        } else {
            message = "<html><div style='text-align: center;'>" +
                    "<p style='font-size: 25px; color: black;'> Ne sois pas triste😢 , tu as fait de ton mieux! </p>" +
                    "<br>" +
                    "<p style='font-size: 20px;'>Continue d'apprendre et tu deviendras un champion 💪.<br/>" +
                    "Ton score est : " + score + "</p></div></html>";
        }

        JButton goToPage1Button = new JButton("Jouer a nouveau");
        JButton quitButton = new JButton("Quitter");
         goToPage1Button.setFont(new Font("Arial", Font.PLAIN, 15));
         quitButton.setFont(new Font("Arial", Font.PLAIN, 15));

         goToPage1Button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            openPage1();
        }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Quitte l'application
            }
        });

        Object[] options;
        if (score >= 3) {
            options = new Object[]{ quitButton};
        } else {
            options = new Object[]{ goToPage1Button,quitButton};
        }
     
    JOptionPane.showOptionDialog(this, message, title, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
}
private void openPage1() {
   
    Page1 page1 = new Page1();
    page1.setVisible(true);
    dispose();
    // Ferme la fenêtre actuelle 
}
    private void help() {
       Question currentQuestion = getCurrentQuestion();
        List<String> choices = currentQuestion.getChoices();

        if (!eliminatedChoicesMap.containsKey(currentQuestion)) {
            eliminatedChoicesMap.put(currentQuestion, new ArrayList<>());
        }

        List<String> eliminatedChoices = eliminatedChoicesMap.get(currentQuestion);

        String correctAnswer = currentQuestion.getCorrectAnswer();
        List<String> remainingChoices = new ArrayList<>(choices);
        remainingChoices.remove(correctAnswer);
        remainingChoices.removeAll(eliminatedChoices);

        for (int i = 0; i < 2; i++) {
            if (!remainingChoices.isEmpty()) {
                int randomIndex = (int) (Math.random() * remainingChoices.size());
                String choiceToRemove = remainingChoices.remove(randomIndex);

                if (choiceToRemove.equals(option1.getText())) {
                    option1.setEnabled(false);
                } else if (choiceToRemove.equals(option2.getText())) {
                    option2.setEnabled(false);
                } else if (choiceToRemove.equals(option3.getText())) {
                    option3.setEnabled(false);
                } else if (choiceToRemove.equals(option4.getText())) {
                    option4.setEnabled(false);
                }

                eliminatedChoices.add(choiceToRemove);
            }
        }

        helpButton.setEnabled(false);
    }

    private void enableAllOptions() {
        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        option4.setEnabled(true);
    }

    private Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    private String getSelectedAnswer() {
        if (option1.isSelected()) {
            return option1.getText();
        } else if (option2.isSelected()) {
            return option2.getText();
        } else if (option3.isSelected()) {
            return option3.getText();
        } else if (option4.isSelected()) {
            return option4.getText();
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
             public void run() {
                new FlagQuizf();
            }
        });
    }

    private static class Question {

        private String question;
        private List<String> choices;
        private String correctAnswer;
        private String flagImagePath;

        public Question(String question, List<String> choices, String correctAnswer,String flagImagePath) {
            this.question = question;
            this.choices = choices;
            this.correctAnswer = correctAnswer;
            this.flagImagePath = flagImagePath;
        }

        public String getQuestion() {
            return question;
        }
        public List<String> getChoices() {
            return choices;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        public boolean isCorrectAnswer(String selectedAnswer) {
            return correctAnswer.equals(selectedAnswer);
        }

        public String getFlagImagePath() {  // Add this method
            return flagImagePath;
        }
    }
    }