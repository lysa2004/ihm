import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class NationaliteD extends JFrame {
    private int score = 0;
    private int currentQuestionIndex = 0;
    private int remainingTime = 15; // Timer set to 15 seconds
    private JLabel scoreLabel;
    private JLabel questionLabel;
    private JLabel timerLabel;
    private JRadioButton option1, option2, option3, option4;
    private ButtonGroup group;
    private JButton nextButton;
    private Timer timer;
    private List<Question> questions;

    public NationaliteD() {
        setTitle("Quiz Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 740);
        setLocationRelativeTo(null);

        questions = new ArrayList<>();
        initializeQuestions();

        scoreLabel = new JLabel("Score: 0");
        questionLabel = new JLabel();
        timerLabel = new JLabel("Time remaining: 15 seconds");

        setUpUIComponents();
        setUpLayout();
        updateUI();

        setVisible(true);
    }

    private void initializeQuestions() {
        questions.add(new Question("<html> Question : <br> Quel est le nom donné aux personnes originaires de l'Affrique du sud ?", 
            new String[]{"sudafricains", "Allemands", "Danois", "Americains"}, "Sudafricains"));
        questions.add(new Question("<html> Question : <br> Quel est le nom donné aux personnes originaires du Pays-bas?", 
            new String[]{"Espagnoles", "Turques", "Néerlandais", "Francais"}, "Néerlandais"));
        questions.add(new Question("<html> Question : <br> Quel est le nom donné aux personnes originaires du Danemark?", 
            new String[]{"Russes", "Danois", "<html> Question : <br> Norvegiens", "Italiens"}, "Danois"));
        questions.add(new Question("<html> Question : <br> Quel est le nom donné aux personnes originaires des Etats-unis ?", 
            new String[]{"Arabes", "Anglais", "États-Uniens", "Americains"}, "États-Uniens"));
        questions.add(new Question("<html> Question : <br> Quel est le nom donné aux personnes originaires de Madagascar?", 
            new String[]{"Malaisiens", "Australiens", "Danois", "Malgaches"}, "Malgaches"));
    }

    private void setUpUIComponents() {
        scoreLabel = new JLabel("Your Score:");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 30));
        
        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        questionLabel.setHorizontalAlignment(JLabel.CENTER); // Centre le texte horizontalement
        questionLabel.setVerticalAlignment(JLabel.CENTER); // Centre le texte verticalement
        questionLabel.setVerticalTextPosition(JLabel.CENTER); // Positionne le texte au centre verticalement
        questionLabel.setHorizontalTextPosition(JLabel.CENTER); // Positionne le texte au centre horizontalement
        timerLabel = new JLabel();
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();

        group = new ButtonGroup();
        group.add(option1);
        group.add(option2);
        group.add(option3);
        group.add(option4);

        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleNextButtonAction();
            }
        });
        styleButton(nextButton, "Suivant",  new Color(250, 116, 97), Color.WHITE);
        styleChoice(option1, getForeground());
        styleChoice(option2, getForeground());
        styleChoice(option3, getForeground());
        styleChoice(option4, getForeground());

       
    }
    private JRadioButton createStyledRadioButton() {
        JRadioButton radioButton = new JRadioButton();
        radioButton.setFont(new Font("Arial", Font.PLAIN, 20));
        radioButton.setFocusPainted(false);
        radioButton.setBorderPainted(false);
        return radioButton;
    }
    private void setUpLayout() {
        setLayout(new BorderLayout());
        JPanel quizPanel = new JPanel();
        quizPanel.setLayout(new GridLayout(7, 1, 10, 10)); // Increased rows for timer and score
        quizPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 100, 20));
        quizPanel.setBackground(new Color(176, 224, 228));

        quizPanel.add(scoreLabel);
        quizPanel.add(questionLabel);
        quizPanel.add(timerLabel);
        quizPanel.add(option1);
        quizPanel.add(option2);
        quizPanel.add(option3);
        quizPanel.add(option4);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        buttonPanel.setBackground(new Color(176, 224, 228));
        buttonPanel.add(nextButton);

        
        add(quizPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
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
        scoreLabel.setText("Ton Score: " + score);
        questionLabel.setText("<html><div style='text-align: center;'>" + currentQuestion.getQuestion() + "</div></html>");

        String[] choices = currentQuestion.getChoices();
        option1.setText(choices[0]);
        option2.setText(choices[1]);
        option3.setText(choices[2]);
        option4.setText(choices[3]);

        group.clearSelection();
        startTimer();
    }

    private void handleNextButtonAction() {
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

    private void startTimer() {
        if (timer != null) {
            timer.stop();
        }
        remainingTime = 15; // Reset to 15 seconds for each question
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remainingTime--;
                timerLabel.setText("Temps restants: " + remainingTime + " seconds");
                if (remainingTime <= 0) {
                    timer.stop();
                    handleTimeOut();
                }
            }
        });
        timer.start();
    }
   
    private void handleTimeOut() {
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

    private Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    private String getSelectedAnswer() {
        return option1.isSelected() ? option1.getText() :
               option2.isSelected() ? option2.getText() :
               option3.isSelected() ? option3.getText() :
               option4.isSelected() ? option4.getText() : null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NationaliteD();
            }
        });
    }

    private static class Question {
        private String question;
        private String[] choices;
        private String correctAnswer;

        public Question(String question, String[] choices, String correctAnswer) {
            this.question = question;
            this.choices = choices;
            this.correctAnswer = correctAnswer;
        }

        public String getQuestion() {
            return question;
        }

        public String[] getChoices() {
            return choices;
        }

        public boolean isCorrectAnswer(String selectedAnswer) {
            return correctAnswer.equals(selectedAnswer);
        }
    }
}