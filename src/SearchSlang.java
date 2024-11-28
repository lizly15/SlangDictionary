import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.List;

public class SearchSlang {
    public static void main(String[] args) {
        Trie trie = new Trie();
        DictionaryDataStructure dict = new DictionaryDataStructure();
        dict.readFiles(trie);

        JFrame frame = new JFrame("Key Detection Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());

        JTextField textField = new JTextField(20);
        JComboBox<String> comboBox = new JComboBox<>();
        JTextArea textArea = new JTextArea(5, 30);
        textArea.setEditable(false);

        // Thêm DocumentListener vào JTextField
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	String curText = textField.getText();
            	TrieNode searchNode = trie.searchSlang(curText);
                updateComboBox(searchNode);
                updateTextArea(searchNode);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	String curText = textField.getText();
            	TrieNode searchNode = trie.searchSlang(curText);
                updateComboBox(searchNode);
                updateTextArea(searchNode);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	String curText = textField.getText();
            	TrieNode searchNode = trie.searchSlang(curText);
                updateComboBox(searchNode);
            }

            private void updateComboBox(TrieNode searchNode) {
                List<Slang> slangList = searchNode.getListHintSlang();

                comboBox.removeAllItems();
                for (Slang slang : slangList) {
                    comboBox.addItem(slang.getWord());
                }
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

        // Thêm các thành phần vào khung
        frame.add(textField);
        frame.add(comboBox);
        frame.add(new JScrollPane(textArea));

        // Hiển thị khung
        frame.setVisible(true);
    }
}