import java.awt.CardLayout;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Slang Dictionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        
     // Hộp thoại nhập đường dẫn file
        String filePath = JOptionPane.showInputDialog(frame, 
                "Please enter the path to slang data:", 
                "File Path Input", 
                JOptionPane.PLAIN_MESSAGE);

        // Kiểm tra nếu người dùng đã nhập đường dẫn
        if (filePath == null || filePath.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "File path cannot be empty. Exiting.");
            System.exit(1);
        }

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        Trie slangTrie = new Trie(); 
        DictionaryDataStructure dict = new DictionaryDataStructure(filePath);
        dict.readFiles(slangTrie);

        HistoryPanel historyPanel = new HistoryPanel(cardLayout);
        SearchSlangPanel searchSlangPanel = new SearchSlangPanel(slangTrie, cardLayout, historyPanel);
        SearchSlangByDefPanel searchSlangByDefPanel = new SearchSlangByDefPanel(slangTrie, cardLayout, historyPanel);
        OnThisDaySlangPanel onThisDaySlangPanel = new OnThisDaySlangPanel(slangTrie, cardLayout);
        GamePanel gamePanel = new GamePanel(slangTrie, cardLayout);
        MainMenuPanel mainMenuPanel = new MainMenuPanel(cardLayout);
        AddSlangPanel addSlangPanel = new AddSlangPanel(slangTrie);
        DeleteSlangPanel delSlangPanel = new DeleteSlangPanel(slangTrie);
        
        mainPanel.add(mainMenuPanel, "MainMenu");
        mainPanel.add(searchSlangPanel, "SearchSlang");
        mainPanel.add(searchSlangByDefPanel, "SearchSlangByDef");
        mainPanel.add(historyPanel, "History");
        mainPanel.add(onThisDaySlangPanel, "OnThisDaySlang");
        mainPanel.add(gamePanel, "Game");
        mainPanel.add(addSlangPanel, "AddSlang");
        mainPanel.add(delSlangPanel, "DelSlang");
        
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}