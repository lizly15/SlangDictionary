import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteSlangPanel extends JPanel {
    private JTextField slangInput;
    private JTextArea definitionArea;
    private JButton searchButton;
    private JButton deleteButton;
    private JButton backButton;
    private Trie trie;

    public DeleteSlangPanel(Trie trie) {
        this.trie = trie;
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        slangInput = new JTextField(20); 
        inputPanel.add(new JLabel("Enter Slang:"));
        inputPanel.add(slangInput);

        searchButton = new JButton("Search");
        inputPanel.add(searchButton);

        deleteButton = new JButton("Delete");
        inputPanel.add(deleteButton);

        backButton = new JButton("Back");
        inputPanel.add(backButton); 

        add(inputPanel, BorderLayout.NORTH);

        definitionArea = new JTextArea(5, 30); 
        definitionArea.setEditable(false);
        add(new JScrollPane(definitionArea), BorderLayout.CENTER);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchSlang();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSlang();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) getParent().getLayout();
                cardLayout.show((JPanel) getParent(), "MainMenu");
            }
        });
    }

    private void searchSlang() {
        String slang = slangInput.getText().trim();
        if (!slang.isEmpty()) {
            TrieNode searchNode = trie.searchSlang(slang);
            
            if (searchNode != null) {
                Slang searchSlang = searchNode.getSlang();
                if (searchSlang != null) {
                    definitionArea.setText(searchSlang.getFullData());
                } else {
                    definitionArea.setText("No definition found.");
                }
            } else {
                definitionArea.setText("Slang does not exist.");
            }
        } else {
            definitionArea.setText("Please enter a slang.");
        }
    }

    private void deleteSlang() {
        String slang = slangInput.getText().trim();
        if (!slang.isEmpty()) {
            TrieNode searchNode = trie.searchSlang(slang);
            if (searchNode != null && searchNode.getSlang() != null) {
                int response = JOptionPane.showConfirmDialog(this, 
                        "Are you sure you want to delete the slang: " + slang + "?", 
                        "Confirm Delete", 
                        JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                	searchNode.setSlang(null);
                    definitionArea.setText("Slang '" + slang + "' has been deleted.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Slang does not exist.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a slang.");
        }
    }
}