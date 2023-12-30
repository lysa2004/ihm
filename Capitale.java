import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Capitale extends JFrame {
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

    public Capitale() {
        setTitle("Quiz Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
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
    questions.clear();

    questions.add(new Question("Quelle est la capitale du Canada ?", 
        new String[]{"Ottawa", "Toronto", "Vancouver", "Montréal"}, "Ottawa"));

    questions.add(new Question("Quelle est la capitale de la Turquie ?", 
        new String[]{ "Istanbul", "Izmir", "Bursa","Ankara"}, "Ankara"));

    questions.add(new Question("Quelle est la capitale de la Chine ?", 
        new String[]{"Shanghai","Pekin",  "Hong Kong", "Guangzhou"}, "Pekin"));

    questions.add(new Question("Quelle est la capitale du Japon ?", 
        new String[]{"Osaka", "Kyoto","Tokyo",  "Sapporo"}, "Tokyo"));

    questions.add(new Question("Quelle est la capitale de l'Arabie Saoudite ?", 
        new String[]{"Riyad", "Jeddah", "La Mecque", "Medina"}, "Riyad"));
}


    private void setUpUIComponents() {
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 16));

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
    }

    private void setUpLayout() {
        setLayout(new BorderLayout());
        JPanel quizPanel = new JPanel();
        quizPanel.setLayout(new GridLayout(7, 1, 10, 10)); // Increased rows for timer and score
        quizPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        quizPanel.add(scoreLabel);
        quizPanel.add(questionLabel);
        quizPanel.add(timerLabel);
        quizPanel.add(option1);
        quizPanel.add(option2);
        quizPanel.add(option3);
        quizPanel.add(option4);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(nextButton);

        add(quizPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void updateUI() {
        Question currentQuestion = getCurrentQuestion();
        scoreLabel.setText("Score: " + score);
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
                timerLabel.setText("Time remaining: " + remainingTime + " seconds");
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
        String message = score >= 3 ? "Great job! Your score: " + score : "Try again! Your score: " + score;
        JOptionPane.showMessageDialog(this, message);
        System.exit(0);
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
                new Capitale();
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
