import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quizz1 extends JFrame {
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

    public Quizz1() {
        // Set up the JFrame
        setTitle("Quiz Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        // Initialize questions list before adding questions
        questions = new ArrayList<>();

        // Create questions
        List<String> choices1 = new ArrayList<>();
        choices1.add("Égyptien");
        choices1.add("Italien");
        choices1.add("Chinois");
        choices1.add("Algerien");
        questions.add(new Question("Je suis l'Algérie, pays si beau, Mes habitants, un nom rigolo. Court et doux, c'est facile à attraper, Peux-tu deviner comment on les appelle ?", choices1, "Algerien"));

        List<String> choices2 = new ArrayList<>();
        choices2.add("Russe");
        choices2.add("Allemand");
        choices2.add("Mexicain");
        choices2.add("Portugais");
        questions.add(new Question("Nous vivons en Europe, pays charmant, Notre langue est allemande, c'est amusant. Si tu cherches, notre nom est tout près, Ressemble à 'Al...'. Qui sommes-nous, tu sais ?", choices2, "Allemand"));

        List<String> choices3 = new ArrayList<>();
        choices3.add("Indien");
        choices3.add("Afghan");
        choices3.add("Portugais");
        choices3.add("Suisse");
        questions.add(new Question("Nous sommes d'un pays en Asie, Notre nom commence par 'I'. Dans nos saris et nos turbans, Qui sommes-nous, c'est amusant ?", choices3, "Indien"));

        List<String> choices4 = new ArrayList<>();
        choices4.add("Suédois");
        choices4.add("Argentin");
        choices4.add("Chinois");
        choices4.add("Russe");
        questions.add(new Question("Nous venons d'un pays où l'hiver est roi, La neige danse et recouvre tout de son manteau. Notre langue, russe, sonne comme un chant, De quel pays magique venons-nous, sais-tu maintenant ?", choices4, "Russe"));

        List<String> choices5 = new ArrayList<>();
        choices5.add("Irakien");
        choices5.add("Mexicain");
        choices5.add("Turc");
        choices5.add("Marocain");
        questions.add(new Question("Nous sommes d'un pays en Amérique, Avec des tacos et des chapeaux fantastiques. Notre nom commence par 'M', devine!!", choices5, "Mexicain"));

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
        } else if (option4.isSelected()) {
            return option4.getText();
        }
        return null;
    }

    private void updateUI() {
        Question currentQuestion = getCurrentQuestion();
        scoreLabel.setText("Score: " + score);
        questionLabel.setText("<html><div style='font-size: 16px; color: blue; text-align: center;'>" +
                currentQuestion.getQuestion() + "</div></html>");
        List<String> choices = currentQuestion.getChoices();
        option1.setText(choices.get(0));
        option2.setText(choices.get(1));
        option3.setText(choices.get(2));
        option4.setText(choices.get(3));
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
        new Quizz1();
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
