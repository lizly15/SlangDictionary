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
        JButton historyButton = new JButton("History");
        JButton onThisDayButton = new JButton("On this day slang word");
        JButton gameButton = new JButton("Game");
        JButton settingButton = new JButton("Setting");

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
        add(settingButton, gbc);
    }
}