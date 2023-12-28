import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quizz extends JFrame {
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

    // Map to store eliminated choices for each question
    private Map<Question, List<String>> eliminatedChoicesMap;

    private List<Question> questions;

    public Quizz() {
        // Set up the JFrame
        setTitle("Quiz Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        // Initialize questions list before adding questions
        questions = new ArrayList<>();

        // Create questions
        List<String> choices1 = new ArrayList<>();
        choices1.add("Londres");
        choices1.add("Madrid");
        choices1.add("Moscou");
        choices1.add("Alger");
        questions.add(new Question("Ma capitale a le même radical que mon nom de pays Algérie .  Qui suis-je?", choices1, "Alger"));

        List<String> choices2 = new ArrayList<>();
        choices2.add("Paris");
        choices2.add("Berlin");
        choices2.add("Madrid");
        choices2.add("Mexico");
        questions.add(new Question("Quelle ville est la maison de la tour Eiffel, de délicieux croissants et de joyeux artistes portant des bérets?", choices2, "Paris"));

        List<String> choices3 = new ArrayList<>();
        choices3.add("Séoul");
        choices3.add("Luxambourg");
        choices3.add("Bruxelles");
        choices3.add("Rabat");
        questions.add(new Question("Je suis une capitale pas très grande de Luxambourg Mon nom commence par 'L' et a neuf lettres en bande.", choices3, "Luxambourg"));

        List<String> choices4 = new ArrayList<>();
        choices4.add("Nairobi");
        choices4.add("Riyadh");
        choices4.add("Madrid");
        choices4.add("Tunis");
        questions.add(new Question("Je suis une ville sous le soleil brillant, On m'appelle <T> comme un éléphant. Au cœur de la Tunisie, des palais, des histoires sans fin, Devine ma capitale, tu seras malin !", choices4, "Tunis"));

        List<String> choices5= new ArrayList<>();
        choices5.add("Brasilia");
        choices5.add("Accra");
        choices5.add("Séoul");
        choices5.add("Mexico");
        questions.add(new Question("Je suis la capitale du Brésil, On m'appelle <B> et c'est génial. C'est là que le carnaval danse avec zèle, Quelle est ma ville,  peux-tu deviner?", choices5, "Brasilia"));

        // Initialize the eliminatedChoicesMap
        eliminatedChoicesMap = new HashMap<>();

        // Create components
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();
        nextButton = new JButton("Next");
        helpButton = new JButton("Help");

        // Group the radio buttons
        group = new ButtonGroup();
        group.add(option1);
        group.add(option2);
        group.add(option3);
        group.add(option4);

        // Add ActionListener to the next button
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

        // Add ActionListener to the help button
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                help();
            }
        });

        // Set up layout with padding
        setLayout(new BorderLayout());
        JPanel quizPanel = new JPanel();
        quizPanel.setLayout(new GridLayout(6, 1, 10, 10)); // Increased rows for Help button
        quizPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        quizPanel.add(scoreLabel);
        quizPanel.add(questionLabel);
        quizPanel.add(option1);
        quizPanel.add(option2);
        quizPanel.add(option3);
        quizPanel.add(option4);
        quizPanel.add(helpButton); // Added Help button

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        buttonPanel.add(nextButton);

        add(quizPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Display the first question
        updateUI();

        // Make the frame visible
        setVisible(true);
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
        }else if (option4.isSelected()) {
            return option4.getText();
        }
        return null;
    }

    private void updateUI() {
        Question currentQuestion = getCurrentQuestion();
        scoreLabel.setText("Score: " + score);
        questionLabel.setText("<html><div style='font-size: 16px; color: blue;''text-align: center;'>" +
            currentQuestion.getQuestion() + "</div></html>");
        option1.setText(currentQuestion.getChoices().get(0));
        option2.setText(currentQuestion.getChoices().get(1));
        option3.setText(currentQuestion.getChoices().get(2));
        option4.setText(currentQuestion.getChoices().get(3));
        group.clearSelection();
        enableAllOptions(); // Re-enable all options for the new question
        helpButton.setEnabled(true); // Re-enable the Help button for the new question
    }

  
    private void showFinalScore() {
        String message;
        String title = "Fin du Quiz";
        
        if (score >= 3) {
            message = "<html><div style='text-align: center;'>" +
                    "<p style='font-size: 25px; color: red;'> 🎉 Bravo le petit champion ! 🎉 </p>" +
                    "<p style='font-size: 20px;'>Tu as réussi avec un super score de : " + score + " 🌟 <br/>" +
                    "Continue comme ça, tu es incroyable !</p></div></html>";
        } else {
           message = "<html><div style='text-align: center;'>" +
            "<p style='font-size: 25px; color: black;'> Ne sois pas triste😢 , tu as fait de ton mieux! </p>" +
            "<br>" +  
            "<p style='font-size: 20px;'>Continue d'apprendre et tu deviendras un champion 💪.<br/>" +
            "Ton score est : " + score + "</p></div></html>";
        }
        
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
    
    
    private void help() {
        Question currentQuestion = getCurrentQuestion();
        List<String> choices = currentQuestion.getChoices();

        // Check if choices have been eliminated for the current question
        if (!eliminatedChoicesMap.containsKey(currentQuestion)) {
            eliminatedChoicesMap.put(currentQuestion, new ArrayList<>());
        }

        List<String> eliminatedChoices = eliminatedChoicesMap.get(currentQuestion);

        // Identify the correct and remaining incorrect answers
        String correctAnswer = currentQuestion.getCorrectAnswer();
        List<String> remainingChoices = new ArrayList<>(choices);
        remainingChoices.remove(correctAnswer);
        remainingChoices.removeAll(eliminatedChoices);

        // Randomly select two incorrect choices to eliminate
        for (int i = 0; i < 2; i++) {
            if (!remainingChoices.isEmpty()) {
                int randomIndex = (int) (Math.random() * remainingChoices.size());
                String choiceToRemove = remainingChoices.remove(randomIndex);

                // Disable the corresponding radio button
                if (choiceToRemove.equals(option1.getText())) {
                    option1.setEnabled(false);
                } else if (choiceToRemove.equals(option2.getText())) {
                    option2.setEnabled(false);
                } else if (choiceToRemove.equals(option3.getText())) {
                    option3.setEnabled(false);
                } else if (choiceToRemove.equals(option4.getText())) {
                    option4.setEnabled(false);
                }

                // Add the eliminated choice to the list
                eliminatedChoices.add(choiceToRemove);
            }
        }

        // Disable the help button after using it
        helpButton.setEnabled(false);
    }

    private void enableAllOptions() {
        // Enable all radio buttons for the new question
        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        option4.setEnabled(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Quizz();
            }
        });
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


