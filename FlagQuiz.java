import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlagQuiz extends JFrame {
    private int score = 0;
    private int currentQuestionIndex = 0;
    private int remainingTime; // Ajout de la variable remainingTime
    private JLabel scoreLabel;
    private JLabel questionLabel;
    private JLabel flagLabel;
    private JLabel timerLabel;
    private JRadioButton option1;
    private JRadioButton option2;
    private ButtonGroup group;
    private JButton nextButton;
    private Timer timer;

    // Map to store eliminated choices for each question
    private Map<Question, List<String>> eliminatedChoicesMap;

    private List<Question> questions;

    public FlagQuiz() {
        // Set up the JFrame
        setTitle("Quiz Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        // Initialize questions list before adding questions
        questions = new ArrayList<>();

        // Create questions
        List<String> choices1 = new ArrayList<>();
        choices1.add("turquie");
        choices1.add("tunis");

        questions.add(new Question("a quel pays appartient ce drapeau ?", choices1, "tunis", "tunis.png"));

        List<String> choices2 = new ArrayList<>();
        choices2.add("senegal");
        choices2.add("cameroun");

        questions.add(new Question(" a quel pays appartient ce drapeau ?", choices2, "senegal", "senegal.png"));

        List<String> choices3 = new ArrayList<>();
        choices3.add("soudan");
        choices3.add("palestine");

        questions.add(new Question("a quel pays appartient ce drapeau ?", choices3, "palestine","palestine.png"));

        List<String> choices4 = new ArrayList<>();
        choices4.add("egypte");
        choices4.add("irak");

        questions.add(new Question("a quel pays appartient ce drapeau ?", choices4, "egypte","egypte.png"));

        List<String> choices5 = new ArrayList<>();
        choices5.add("croatie");
        choices5.add("pays bas");

        questions.add(new Question("a quel pays appartient ce drapeau ?", choices5, "pays bas","pays-bas.png"));

        // Initialize the eliminatedChoicesMap
        eliminatedChoicesMap = new HashMap<>();

        // Create components
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        flagLabel = new JLabel();
        flagLabel.setPreferredSize(new Dimension(200, 150)); // Set the preferred size for the flag image
        timerLabel = new JLabel();
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        option1 = new JRadioButton();
        option2 = new JRadioButton();

        nextButton = new JButton("Next");

        // Group the radio buttons
        group = new ButtonGroup();
        group.add(option1);
        group.add(option2);

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
        quizPanel.setLayout(new GridLayout(6, 1, 10, 10)); // Increased rows for the timer
        quizPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        quizPanel.add(scoreLabel);
        quizPanel.add(questionLabel);
        quizPanel.add(flagLabel);
        quizPanel.add(timerLabel);
        quizPanel.add(option1);
        quizPanel.add(option2);

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
        }
        return null;
    }

    private void startTimer() {
        int delay = 1000; // Délai d'une seconde
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remainingTime -= 1;
                timerLabel.setText("Temps restant : " + remainingTime + " secondes");

                if (remainingTime <= 0) {
                    // Timer action: Move to the next question
                    timer.stop();
                    currentQuestionIndex++;
                    if (currentQuestionIndex < questions.size()) {
                        updateUI();
                    } else {
                        showFinalScore();
                    }
                }
            }
        });
        remainingTime = 15; // Remettre le temps à 15 secondes
        timerLabel.setText("Temps restant : " + remainingTime + " secondes");
        timer.start();
    }

    private void updateUI() {
        Question currentQuestion = getCurrentQuestion();
        scoreLabel.setText("Score: " + score);
        questionLabel.setText("<html><div style='font-size: 16px; color: blue; text-align: center;'>" +
                currentQuestion.getQuestion() + "</div></html>");

        // Load the image using the specified file name
        String imagePath = "chemin/vers/le/dossier/des/images/" + currentQuestion.getFlagFileName();
        flagLabel.setIcon(new ImageIcon(imagePath));

        option1.setText(currentQuestion.getChoices().get(0));
        option2.setText(currentQuestion.getChoices().get(1));

        group.clearSelection();
        enableAllOptions(); // Re-enable all options for the new question

        // Restart the timer for the new question
        startTimer();
    }

    private void showFinalScore() {
        // Stop the timer
        timer.stop();

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

    private void enableAllOptions() {
        option1.setEnabled(true);
        option2.setEnabled(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FlagQuiz();
            }
        });
    }

    private static class Question {
        private String question;
        private List<String> choices;
        private String correctAnswer;
        private String flagFileName;

        public Question(String question, List<String> choices, String correctAnswer, String flagFileName) {
            this.question = question;
            this.choices = choices;
            this.correctAnswer = correctAnswer;
            this.flagFileName = flagFileName;
        }

        public String getFlagFileName() {
            return flagFileName;
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
