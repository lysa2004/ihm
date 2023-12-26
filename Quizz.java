import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Quizz extends JFrame {
    private int score = 0;
    private int currentQuestionIndex = 0;
    private JLabel scoreLabel;
    private JLabel questionLabel;
    private JRadioButton option1;
    private JRadioButton option2;
    private JRadioButton option3;
    private ButtonGroup group;
    private JButton nextButton;

    private List<Question> questions;

    public Quizz() {
        // Set up the JFrame
        setTitle("Quizz Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        // Create questions
        questions = new ArrayList<>();
        List<String> choices1 = new ArrayList<>();
        choices1.add("Paris");
        choices1.add("Berlin");
        choices1.add("Madrid");
        questions.add(new Question("What is the capital of France?", choices1, "Paris"));

        List<String> choices2 = new ArrayList<>();
        choices2.add("Mars");
        choices2.add("Venus");
        choices2.add("Jupiter");
        questions.add(new Question("Which planet is known as the Red Planet?", choices2, "Mars"));

        List<String> choices3 = new ArrayList<>();
        choices3.add("Elephant");
        choices3.add("Giraffe");
        choices3.add("Blue Whale");
        questions.add(new Question("What is the largest mammal?", choices3, "Blue Whale"));

        // Create components
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        nextButton = new JButton("Next");

        // Group the radio buttons
        group = new ButtonGroup();
        group.add(option1);
        group.add(option2);
        group.add(option3);

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

        // Set up layout with padding
        setLayout(new BorderLayout());
        JPanel quizPanel = new JPanel();
        quizPanel.setLayout(new GridLayout(5, 1, 10, 10));
        quizPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        quizPanel.add(scoreLabel);
        quizPanel.add(questionLabel);
        quizPanel.add(option1);
        quizPanel.add(option2);
        quizPanel.add(option3);

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
        }
        return null;
    }

    private void updateUI() {
        Question currentQuestion = getCurrentQuestion();
        scoreLabel.setText("Score: " + score);
        questionLabel.setText(currentQuestion.getQuestion());
        option1.setText(currentQuestion.getChoices().get(0));
        option2.setText(currentQuestion.getChoices().get(1));
        option3.setText(currentQuestion.getChoices().get(2));
        group.clearSelection();
    }

    private void showFinalScore() {
        JOptionPane.showMessageDialog(this, "Quiz completed!\nYour final score is: " + score, "Quiz Completed", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
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
