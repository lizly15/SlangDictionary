import javax.swing.*;
import java.awt.*;

public class HistoryPanel extends JPanel {
    private JTextArea textArea; 

    public HistoryPanel(CardLayout cardLayout) {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("History", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH); 
        
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(getParent(), "MainMenu"));
        backPanel.add(backButton);
        
        add(backPanel, BorderLayout.SOUTH); 

        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        textArea.setLineWrap(true); 
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    public void addHistory(String slang) {
        textArea.append(slang + "\n\n");
    }
}