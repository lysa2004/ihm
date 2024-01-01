package flagEZ;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Flagez extends JFrame {
    private static final String IMAGE_PATH = "C:\\Users\\DELL\\Desktop\\flag\\"; // Chemin des images

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
    private Map<FlagQuestion, List<String>> eliminatedChoicesMap;
    private List<FlagQuestion> questions;

    public Flagez() {
        setTitle("Quizz Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLocationRelativeTo(null);

        initializeFlagQuestions();

        eliminatedChoicesMap = new HashMap<>();

        initComponents();
        setupLayout();
        updateUI();

        setVisible(true);
    }
    public ImageIcon getResizedFlagImage(String flagImagePath, int width, int height) {
        String fullPath = IMAGE_PATH + flagImagePath;
        ImageIcon originalIcon = new ImageIcon(fullPath);
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        
        return new ImageIcon(resizedImage);
    }
    
    ImageIcon resizedFlagAlgeria = getResizedFlagImage("Flag_of_Algeria.png", 20, 20);
    ImageIcon resizedFlagCanada = getResizedFlagImage("Flag_of_Canada.png", 20, 20);
    ImageIcon resizedFlagSpain = getResizedFlagImage("Flag_of_Spain.png", 20, 20);
    ImageIcon resizedFlagBrezil = getResizedFlagImage("Flag_of_Brezil.png", 20, 20);
    ImageIcon resizedFlagKsa = getResizedFlagImage("Flag-Saudi-Arabia.png", 20, 20);
    

    private void initializeFlagQuestions() {

        questions = new ArrayList<>();
        questions.add(new FlagQuestion("Quel pays correspond à ce drapeau  ?", "C:\\Users\\DELL\\Desktop\\flag\\Flag_of_Algeria.png", "Algerie", "Arabia Saoudite", "Espagne", "Bresil"));
        questions.add(new FlagQuestion("Quel pays correspond à ce drapeau  ?", "C:\\Users\\DELL\\Desktop\\flag\\Flag-Saudi-Arabia.png", "Arabia Saoudite", "Allemagne", "Mexique", "Portugal"));
        questions.add(new FlagQuestion("Quel pays correspond à ce drapeau  ?", "C:\\Users\\DELL\\Desktop\\flag\\Flag_of_Spain.png", "Espangne", "Indien", "Afghan", "Portugal"));
        questions.add(new FlagQuestion("Quel pays correspond à ce drapeau  ?", "C:\\Users\\DELL\\Desktop\\flag\\Flag_of_Brezil.png", "Bresil", "Suédois", "Chinois", "Russe"));
        questions.add(new FlagQuestion("Quel pays correspond à ce drapeau  ?", "C:\\Users\\DELL\\Desktop\\flag\\Flag_of_Canada.png", "Canada", "Irakien", "Mexicain", "Marocain"));

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

        nextButton = createStyledButton("Next");
        helpButton = createStyledButton("Help");

        nextButton.addActionListener(e -> handleNextButtonClick());
        helpButton.addActionListener(e -> help());
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
        quizPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 100, 20));
        quizPanel.setBackground(new Color(135, 206, 235));

        quizPanel.add(questionLabel);
        quizPanel.add(Box.createVerticalGlue());

        JPanel choicesPanel = createChoicesPanel();
        quizPanel.add(choicesPanel);

        return quizPanel;
    }

    private JPanel createChoicesPanel() {
        JPanel choicesPanel = new JPanel(new GridLayout(4, 1, 0, 5));
        choicesPanel.setBackground(new Color(135, 206, 235));

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
        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        scorePanel.setBackground(new Color(135, 206, 235));

        JLabel scoreTitleLabel = new JLabel("Your Score:");
        scoreTitleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        scoreTitleLabel.setForeground(Color.BLACK);

        scorePanel.add(scoreTitleLabel);
        scorePanel.add(scoreLabel);

        return scorePanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        buttonPanel.setBackground(new Color(135, 206, 235));

        styleButton(helpButton, "Help", new Color(254, 214, 206), Color.DARK_GRAY);
        styleButton(nextButton, "Next", new Color(250, 116, 97), Color.WHITE);

        buttonPanel.add(helpButton);
        buttonPanel.add(nextButton);

        return buttonPanel;
    }

    private void styleChoice(JRadioButton choice, Color textColor) {
        choice.setForeground(textColor);
        choice.setFont(new Font("Arial", Font.BOLD, 25));
        choice.setBackground(new Color(254, 214, 206));
        choice.setBorder(BorderFactory.createLineBorder(new Color(254, 214, 206), 4));
        choice.setMargin(new Insets(10, 20, 50, 20));
    }

    private void styleButton(JButton button, String text, Color backgroundColor, Color foregroundColor) {
        button.setText(text);
        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
    }

  
    
    private void updateUI() {
        FlagQuestion currentQuestion = getCurrentFlagQuestion();
        scoreLabel.setText("<html><div style='color: rgb(250, 116, 97);font-weight:bold;text-align: center; font-size:30px;'>" +
                "  " + score + "</div></html>");

        questionLabel.setText("<html><div style='font-size: 16px; color: black; text-align: center;padding-right:150px; padding-top:40px;padding-left:50px;'>" +
                "Question:<br/>" + currentQuestion.getQuestion() + "</div></html>");

      
        ImageIcon flagIcon = getResizedFlagImage(currentQuestion.getFlagImagePath(), 100, 100); // Adjust width and height as needed
        questionLabel.setIcon(flagIcon);
                
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
        if (getCurrentFlagQuestion().isCorrectAnswer(getSelectedAnswer())) {
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
        FlagQuestion currentQuestion = getCurrentFlagQuestion();
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

    private FlagQuestion getCurrentFlagQuestion() {
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
        SwingUtilities.invokeLater(() -> {
            Flagez flagez = new Flagez();
            flagez.setVisible(true);
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

    private static class FlagQuestion extends Question {
        private String flagImagePath;

        public FlagQuestion(String question, String flagImagePath, String correctAnswer, String... choices) {
            super(question, createChoicesList(correctAnswer, choices), correctAnswer);
            this.flagImagePath = IMAGE_PATH + flagImagePath;
        }

        public String getFlagImagePath() {
            return flagImagePath;
        }

        private static List<String> createChoicesList(String correctAnswer, String... choices) {
            List<String> choicesList = new ArrayList<>();
            choicesList.add(correctAnswer);
            for (String choice : choices) {
                choicesList.add(choice);
            }
            return choicesList;
        }
    }
}