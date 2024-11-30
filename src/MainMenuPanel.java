import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel {
    public MainMenuPanel(CardLayout cardLayout) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton searchByWordButton = new JButton("Search by word");
        searchByWordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getParent(), "SearchSlang");
            }
        });

        JButton searchByDefinitionButton = new JButton("Search by definition");
        searchByDefinitionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getParent(), "SearchSlangByDef");
            }
        });
        
        JButton historyButton = new JButton("History");
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getParent(), "History");
            }
        });
        
        JButton onThisDayButton = new JButton("On this day slang word");
        onThisDayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getParent(), "OnThisDaySlang");
            }
        });
        JButton gameButton = new JButton("Game");
        gameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getParent(), "Game");
            }
        });
        JButton addButton = new JButton("Add/Modify slang");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getParent(), "AddSlang");
            }
        });
        
        JButton delButton = new JButton("Delete slang");
        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getParent(), "DelSlang");
            }
        });

        gbc.gridy = 0;
        add(searchByWordButton, gbc);
        
        gbc.gridy = 1;
        add(searchByDefinitionButton, gbc);
        
        gbc.gridy = 2;
        add(historyButton, gbc);
        
        gbc.gridy = 3;
        add(onThisDayButton, gbc);
        
        gbc.gridy = 4;
        add(gameButton, gbc);
        
        gbc.gridy = 5;
        add(addButton, gbc);
        
        gbc.gridy = 6;
        add(delButton, gbc);
    }
}