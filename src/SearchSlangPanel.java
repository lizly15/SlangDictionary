import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchSlangPanel extends JPanel {
    public SearchSlangPanel(Trie trie, CardLayout cardLayout) {
        setLayout(new BorderLayout());

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(getParent(), "MainMenu"));
        backPanel.add(backButton);
        
        add(backPanel, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout()); 

        JTextField textField = new JTextField(20);
        JComboBox<String> comboBox = new JComboBox<>();
        JTextArea textArea = new JTextArea(7, 30);
        textArea.setEditable(false);

        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateComboBox();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateComboBox();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateComboBox();
            }

            private void updateComboBox() {
                String curText = textField.getText();
                TrieNode searchNode = trie.searchSlang(curText);
                comboBox.removeAllItems();
                if (searchNode != null) {
                    List<Slang> slangList = searchNode.getListHintSlang();
                    for (Slang slang : slangList) {
                        comboBox.addItem(slang.getWord());
                    }
                }
            }
        });

        // Thêm nút Search
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String curText = textField.getText();
                TrieNode searchNode = trie.searchSlang(curText);
                updateTextArea(searchNode);
            }

            private void updateTextArea(TrieNode searchNode) {
                if (searchNode != null) {
                    Slang searchSlang = searchNode.getSlang();
                    if (searchSlang != null) {
                        textArea.setText(searchSlang.getFullData());
                    } else {
                        textArea.setText("No definition");
                    }
                } else {
                    textArea.setText("No definition");
                }
            }
        });

        searchPanel.add(textField);
        searchPanel.add(comboBox);
        searchPanel.add(searchButton);

        add(searchPanel, BorderLayout.CENTER); 
        add(new JScrollPane(textArea), BorderLayout.SOUTH);
    }
}