import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GamePanel extends JPanel {
    private JTextArea questionArea;
    private JButton[] optionButtons;
    private int currentQuestionIndex;
    private int score;
    private List<Question> questions;
    private Trie trie;

    public GamePanel(Trie trie, CardLayout cardLayout) {
        this.trie = trie;
        setLayout(new BorderLayout());
        
        JLabel titleLabel = new JLabel("Slang Game", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        generateQuestions();
        currentQuestionIndex = 0;
        score = 0;

        questionArea = new JTextArea();
        questionArea.setEditable(false);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        questionArea.setFont(new Font("Arial", Font.PLAIN, 18));
        questionArea.setText(questions.get(currentQuestionIndex).getQuestion());
        
        add(new JScrollPane(questionArea), BorderLayout.CENTER);

        optionButtons = new JButton[4];
        JPanel optionsPanel = new JPanel(new GridLayout(2, 2));
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JButton();
            final int index = i;
            optionButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    checkAnswer(index);
                }
            });
            optionsPanel.add(optionButtons[i]);
        }
        add(optionsPanel, BorderLayout.SOUTH);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            cardLayout.show((JPanel) getParent(), "MainMenu");
            resetGame(); 
        });
        add(backButton, BorderLayout.EAST);

        displayQuestion();
    }

    private void generateQuestions() {
        questions = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 1; i++) {
            boolean isSlangQuestion = random.nextBoolean();
            List<Slang> options = new ArrayList<>();
            while (options.size() < 4) {
                Slang randomSlang = trie.randomSlang();
                if (!options.contains(randomSlang)) {
                    options.add(randomSlang);
                }
            }
            Collections.shuffle(options);
            int correctIndex = random.nextInt(options.size());
            Slang slang = options.get(correctIndex);
            if (isSlangQuestion) {
                List<String> definitions = new ArrayList<>();
                for (Slang s : options) {
                    definitions.add(s.getDefinition());
                }
                questions.add(new Question(slang.getWord(), definitions, correctIndex));
            } else {
                List<String> definitions = new ArrayList<>();
                for (Slang s : options) {
                    definitions.add(s.getWord());
                }
                questions.add(new Question(slang.getDefinition(), definitions, correctIndex));
            }
        }
    }

    private void resetGame() {
        currentQuestionIndex = 0;
        score = 0;
        generateQuestions(); 
        displayQuestion();
    }

    private void displayQuestion() {
        Question question = questions.get(currentQuestionIndex);
        questionArea.setText(question.getQuestion());
        List<String> options = question.getOptions();
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i].setText(options.get(i));
        }
    }

    private void checkAnswer(int selectedIndex) {
        Question question = questions.get(currentQuestionIndex);
        boolean isCorrect = question.getCorrectAnswerIndex() == selectedIndex;
        
        String resultMessage;
        if (isCorrect) {
            score++;
            resultMessage = "Correct! Your current score: " + score;
        } else {
            String correctAnswer = question.getOptions().get(question.getCorrectAnswerIndex());
            resultMessage = "Incorrect! The correct answer is: " + correctAnswer + ". Your current score: " + score;
        }
        
        JOptionPane.showMessageDialog(this, resultMessage);
        
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            displayQuestion();
        } else {
            JOptionPane.showMessageDialog(this, "Game over! Your final score: " + score);
            resetGame();
            CardLayout cardLayout = (CardLayout) getParent().getLayout();
            cardLayout.show((JPanel) getParent(), "MainMenu");
        }
    }

    private static class Question {
        private String question;
        private List<String> options;
        private int correctAnswerIndex;

        public Question(String question, List<String> options, int correctAnswerIndex) {
            this.question = question;
            this.options = options;
            this.correctAnswerIndex = correctAnswerIndex;
        }

        public String getQuestion() {
            return question;
        }

        public List<String> getOptions() {
            return options;
        }

        public int getCorrectAnswerIndex() {
            return correctAnswerIndex;
        }
    }
}