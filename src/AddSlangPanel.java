import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddSlangPanel extends JPanel {
    private JTextField slangInput;
    private JTextArea definitionArea;
    private JTextField newDefinitionInput;
    private JButton searchButton;
    private JButton saveButton;
    private JButton backButton; // Nút Back
    private Trie trie;

    public AddSlangPanel(Trie trie) {
        this.trie = trie;
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        slangInput = new JTextField(20); 
        inputPanel.add(new JLabel("Enter Slang:"));
        inputPanel.add(slangInput);
        
        searchButton = new JButton("Search");
        inputPanel.add(searchButton);
        
        backButton = new JButton("Back");
        inputPanel.add(backButton);

        add(inputPanel, BorderLayout.NORTH);

        definitionArea = new JTextArea(2, 30);
        definitionArea.setEditable(false);
        add(new JScrollPane(definitionArea), BorderLayout.CENTER);

        JPanel newDefinitionPanel = new JPanel();
        newDefinitionInput = new JTextField(20); 
        newDefinitionPanel.add(new JLabel("New Definition:"));
        newDefinitionPanel.add(newDefinitionInput);
        saveButton = new JButton("Save");
        newDefinitionPanel.add(saveButton);
        add(newDefinitionPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchSlang();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDefinition();
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
                    definitionArea.setText("No definition");
                }
            } else {
                definitionArea.setText("No definition");
            }
        }
    }

    private void saveDefinition() {
        String slang = slangInput.getText().trim();
        String newDefinition = newDefinitionInput.getText().trim();

        if (!slang.isEmpty() && !newDefinition.isEmpty()) {
            TrieNode searchNode = trie.searchSlang(slang);
            if (searchNode != null && searchNode.getSlang() != null) {
                int response = JOptionPane.showConfirmDialog(this, 
                        "Slang already exists. Do you want to overwrite the existing definition or add a new one?", 
                        "Overwrite or Add", 
                        JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                	searchNode.getSlang().setMeanings(newDefinition);
                    JOptionPane.showMessageDialog(this, "Definition updated successfully.");
                } else {
                	searchNode.getSlang().addMeanings(newDefinition);
                    JOptionPane.showMessageDialog(this, "New definition added successfully.");
                }
            } else {
                Slang newSlang = new Slang(slang, newDefinition);
                trie.addNewSlang(newSlang);
                JOptionPane.showMessageDialog(this, "Slang added successfully.");
            }
            newDefinitionInput.setText(""); 
        } else {
            JOptionPane.showMessageDialog(this, "Please enter both slang and definition.");
        }
    }
}