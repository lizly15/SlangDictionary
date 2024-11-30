import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        // Panel nhập slang
        JPanel inputPanel = new JPanel();
        slangInput = new JTextField(20); // Mở rộng chiều dài của ô nhập slang
        inputPanel.add(new JLabel("Enter Slang:"));
        inputPanel.add(slangInput);
        
        // Nút Search
        searchButton = new JButton("Search");
        inputPanel.add(searchButton);
        
        // Nút Back
        backButton = new JButton("Back");
        inputPanel.add(backButton); // Thêm nút Back vào cùng panel với nút Search

        add(inputPanel, BorderLayout.NORTH);

        // TextArea hiển thị định nghĩa - thu nhỏ kích thước
        definitionArea = new JTextArea(2, 30); // Chiều cao giảm xuống còn 2 dòng
        definitionArea.setEditable(false);
        add(new JScrollPane(definitionArea), BorderLayout.CENTER);

        // Panel nhập định nghĩa mới
        JPanel newDefinitionPanel = new JPanel();
        newDefinitionInput = new JTextField(20); // Mở rộng chiều dài của ô nhập định nghĩa mới
        newDefinitionPanel.add(new JLabel("New Definition:"));
        newDefinitionPanel.add(newDefinitionInput);
        saveButton = new JButton("Save");
        newDefinitionPanel.add(saveButton);
        add(newDefinitionPanel, BorderLayout.SOUTH);

        // Thêm ActionListener cho nút Search
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchSlang();
            }
        });

        // Thêm ActionListener cho nút Save
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDefinition();
            }
        });

        // Thêm ActionListener cho nút Back
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) getParent().getLayout();
                cardLayout.show((JPanel) getParent(), "MainMenu"); // Quay về menu chính
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
                    // Cập nhật định nghĩa cũ
                    searchNode.setMeanings(newDefinition);
                    JOptionPane.showMessageDialog(this, "Definition updated successfully.");
                } else {
                    // Thêm định nghĩa mới
                    searchNode.addMeanings(newDefinition);
                    JOptionPane.showMessageDialog(this, "New definition added successfully.");
                }
            } else {
                // Nếu slang chưa tồn tại, thêm slang mới vào trie
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