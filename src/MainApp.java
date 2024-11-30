import java.awt.CardLayout;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Slang Dictionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        Trie slangTrie = new Trie(); 
        DictionaryDataStructure dict = new DictionaryDataStructure();
        dict.readFiles(slangTrie);

        HistoryPanel historyPanel = new HistoryPanel(cardLayout);
        SearchSlangPanel searchSlangPanel = new SearchSlangPanel(slangTrie, cardLayout, historyPanel);
        SearchSlangByDefPanel searchSlangByDefPanel = new SearchSlangByDefPanel(slangTrie, cardLayout, historyPanel);
        OnThisDaySlangPanel onThisDaySlangPanel = new OnThisDaySlangPanel(slangTrie, cardLayout);
        GamePanel gamePanel = new GamePanel(slangTrie, cardLayout);
        MainMenuPanel mainMenuPanel = new MainMenuPanel(cardLayout);
        AddSlangPanel addSlangPanel = new AddSlangPanel(slangTrie);
        
        mainPanel.add(mainMenuPanel, "MainMenu");
        mainPanel.add(searchSlangPanel, "SearchSlang");
        mainPanel.add(searchSlangByDefPanel, "SearchSlangByDef");
        mainPanel.add(historyPanel, "History");
        mainPanel.add(onThisDaySlangPanel, "OnThisDaySlang");
        mainPanel.add(gamePanel, "Game");
        mainPanel.add(addSlangPanel, "AddSlang");
        
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}