import javax.swing.*;
import java.awt.*;

public class OnThisDaySlangPanel extends JPanel {
    private JTextArea slangTextArea;
    private CardLayout cardLayout;
    private Trie trie;
	
	public void updateTrie(Trie trie) {
		this.trie = trie;
	}

    public OnThisDaySlangPanel(Trie trie, CardLayout cardLayout) {
        this.cardLayout = cardLayout;
        this.trie = trie;

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Today's slang", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH); 

        slangTextArea = new JTextArea(10, 30);
        slangTextArea.setEditable(false);
        slangTextArea.setLineWrap(true);
        slangTextArea.setWrapStyleWord(true);
        slangTextArea.setText(getRandomSlang()); 

        JScrollPane scrollPane = new JScrollPane(slangTextArea);
        add(scrollPane, BorderLayout.CENTER); 

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show((JPanel) getParent(), "MainMenu")); 
        backPanel.add(backButton);

        add(backPanel, BorderLayout.SOUTH); 
    }

    private String getRandomSlang() {
        return trie.randomSlang().getFullData(); 
    }
}