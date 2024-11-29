import javax.swing.*;
import java.awt.*;

public class HistoryPanel extends JPanel {
    private JTextArea textArea; 

    public HistoryPanel(CardLayout cardLayout) {
        setLayout(new BorderLayout());

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(getParent(), "MainMenu"));
        backPanel.add(backButton);
        
        add(backPanel, BorderLayout.NORTH);

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