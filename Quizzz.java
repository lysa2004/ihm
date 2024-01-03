import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.*;

public class Quizzz extends JFrame {

    private int score = 0;
    private int currentQuestionIndex = 0;
    private JLabel scoreLabel;
    private JLabel questionLabel;
    private JRadioButton option1;
    private JRadioButton option2;
    private JRadioButton option3;
    private JRadioButton option4;
    private ButtonGroup group;
    private JButton nextButton;
    private JButton helpButton;
    private Map<Question, List<String>> eliminatedChoicesMap;
    private List<Question> questions;

    public Quizzz() {
        setTitle("Quizz Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 740);
        setLocationRelativeTo(null);

        initializeQuestions();

        eliminatedChoicesMap = new HashMap<>();

        initComponents();
        setupLayout();
        updateUI();

        setVisible(true);
    }

    private void initializeQuestions() {
        questions = new ArrayList<>();
                 
                List<String> choices1 = List.of("Londres","Madrid" , "Moscou" ,"Alger");
                questions.add(new Question("Ma capitale a le même radical que mon nom de pays Algérie ."+
                "  Qui suis-je? ", choices1, "Alger"));
        
                 List<String> choices2 = List.of("Paris", "Berlin" , "Madrid" ,"Mexico");
                 questions.add(new Question("Quelle ville est la maison de la tour Eiffel ,"+
                " de délicieux croissants et de joyeux artistes portant des bérets? ", choices2, "Paris"));

                List<String> choices3 = List.of("Séoul", "Luxembourg", "Bruxelles", "Rabat");
                questions.add(new Question("Je suis la capitale du Luxembourg ,"+
                "Petite et charmante, un vrai joyau. " +
                "Trouve ma lettre initiale, c'est ta clé, " +
                " Qui suis-je ? " , choices3, "Luxembourg"));
        
                List<String> choices4 = List.of("Nairobi", "Riyadh", "Madrid", "Tunis");
                questions.add(new Question("Je suis la capitale de la Tunisie,"+
               " Sur la côte méditerranéenne, je brille." +
               " Bordée par la mer, une belle cité," +
               " Qui suis-je ? " , choices4, "Tunis"));
        
                List<String> choices5 = List.of("Brasilia", "Acraa", "Séoul", "Rio de Janeiro");
                questions.add(new Question("Je suis la capitale du Brésil , " +
                " Au cœur du pays, je prends mon éveil." +
                " Sur le plateau central, fière et subtile," +
                " Quelle ville suis-je, peux-tu le dire facilement ? ", choices5, "Brasilia"));
    }

    private void initComponents() {
        scoreLabel = new JLabel("Your Score:");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 30));
       
        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        option1 = createStyledRadioButton();
        option2 = createStyledRadioButton();
        option3 = createStyledRadioButton();
        option4 = createStyledRadioButton();

        group = new ButtonGroup();
        group.add(option1);
        group.add(option2);
        group.add(option3);
        group.add(option4);

        nextButton = new JButton("Suivant");
        helpButton = new JButton("Aide ?");

       // Add ActionListener to the next button
    nextButton.addActionListener(e -> {
    // Check if the user has selected an answer
    if (getSelectedAnswer() == null) {
        // Show a pop-up message if no answer is selected
        JOptionPane.showMessageDialog(this, "Veuillez choisir une reponse, Merci✨.",
                "Reponse Oblegatoire", JOptionPane.WARNING_MESSAGE);
    } else {
        // Check the selected answer
        if (getCurrentQuestion().isCorrectAnswer(getSelectedAnswer())) {
            score++;
        }

        // Move to the next question or show the final score
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            updateUI();
        } else {
            showFinalScore();
        }
    }
});
        helpButton.addActionListener(e -> help());
    }

    private JRadioButton createStyledRadioButton() {
        JRadioButton radioButton = new JRadioButton();
        radioButton.setFont(new Font("Arial", Font.PLAIN, 20));
        radioButton.setFocusPainted(false);
        radioButton.setBorderPainted(false);
        return radioButton;
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        JPanel quizPanel = createQuizPanel();
        JPanel buttonPanel = createButtonPanel();
        JPanel scorPanel = createScorePanel();
        add(scorPanel,BorderLayout.NORTH);
        add(quizPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createQuizPanel() {
        JPanel quizPanel = new JPanel();
       
        quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.Y_AXIS));
        quizPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 100, 20));

        quizPanel.setBackground(new Color(176, 224, 228));
        // quizPanel.add(scoreLabel);
    
        quizPanel.add(questionLabel);
        quizPanel.add(Box.createVerticalGlue());
    
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
      
        scoreTitleLabel.setForeground(Color.BLACK); // Set text color
    
        // // Use the existing scoreLabel instance variable
        // scoreLabel = new JLabel( ""+score );
        // scoreLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        // scoreLabel.setForeground(new Color(250, 116, 97)); // Set text color
        // scoreLabel.setText("<html><div>" "</div></html>");

        scorePanel.add(scoreTitleLabel);
        scorePanel.add(scoreLabel);
    
        return scorePanel;
    }
    
    
    //-----------------------------------

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        buttonPanel.setBackground(new Color(176, 224, 228));
    
       
        // Style the Help Button
        styleButton(helpButton, "Aide !",new Color(254, 214, 206), Color.DARK_GRAY);
     
        // Style the Next Button
        styleButton(nextButton, "Suivant", new Color(250, 116, 97), Color.WHITE);
        buttonPanel.add(helpButton);
        buttonPanel.add(nextButton);
       
        return buttonPanel;
    }
    
    private void styleButton(JButton button, String text, Color backgroundColor, Color foregroundColor) {
        button.setText(text);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        button.setFocusPainted(false); // Remove the focus border
        button.setPreferredSize(new Dimension(150, 50));

    }

    private void styleChoice(JRadioButton choice, Color textColor) {
        choice.setForeground(textColor);
        choice.setFont(new Font("Arial",Font.BOLD, 25));
        choice.setBackground( new Color(254, 214, 206)); 
        choice.setBorder(BorderFactory.createLineBorder(new Color(254, 214, 206), 4)); // Subtle border
        choice.setMargin(new Insets(10, 20, 50, 20)); // Add padding
          }

          private void StyleQuestion(JLabel questionLabel, Color textColor) {
            questionLabel.setFont(new Font("Arial", Font.BOLD, 20));

            questionLabel.setHorizontalAlignment(JLabel.CENTER); // Centre le texte horizontalement
            questionLabel.setVerticalAlignment(JLabel.CENTER); // Centre le texte verticalement
            questionLabel.setVerticalTextPosition(JLabel.CENTER); // Positionne le texte au centre verticalement
            questionLabel.setHorizontalTextPosition(JLabel.CENTER); // Positionne le texte au centre horizontalement
            questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Ajustez les marges ici
        }
        

    private void updateUI() {
        Question currentQuestion = getCurrentQuestion();
        // Style the score label
        scoreLabel.setText("<html><div style='color:rgb(250, 116, 97) ;font-weight:bold;text-align: center; font-size:30px;'>" +
        "  " + score + "</div></html>");
       
        
        questionLabel.setText("<html><div style='font-size: 16px; color: black; text-align: center;padding-right:150px; padding-top:40px;padding-left:50px;'>" +
        "Question:<br/>" + currentQuestion.getQuestion() + "</div></html>");
        StyleQuestion(questionLabel, Color.BLACK);
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
        SwingUtilities.invokeLater(Quizzz::new);
    }

    private static class Question {
        private String question;
        private List<String> choices;
        private String correctAnswer;

        public Question(String question, List<String> choices, String correctAnswer) {
            this.question = question;
            this.choices = choices;
            this.correctAnswer = correctAnswer;
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
    }
}
