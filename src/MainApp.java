import java.awt.CardLayout;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Slang Dictionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        Trie slangTrie = new Trie(); 
        DictionaryDataStructure dict = new DictionaryDataStructure();
        dict.readFiles(slangTrie);
        
        slangTrie.randomSlang().printData();

        MainMenuPanel mainMenuPanel = new MainMenuPanel(cardLayout);
        HistoryPanel historyPanel = new HistoryPanel(cardLayout);
        SearchSlangPanel searchSlangPanel = new SearchSlangPanel(slangTrie, cardLayout, historyPanel);
        
        mainPanel.add(mainMenuPanel, "MainMenu");
        mainPanel.add(searchSlangPanel, "SearchSlang");
        mainPanel.add(historyPanel, "History");
        
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}