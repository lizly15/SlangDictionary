import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class DictionaryDataStructure {
    private String filePath;
    
    public DictionaryDataStructure(String filePath) {
    	this.filePath = filePath;
    }

    public void readFiles(Trie trie) {
    	readFile(filePath, trie);
    }

    private void readFile(String filePath, Trie trie) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                int countBackticks = countBackticks(line);
                if (countBackticks == 1) {
                    Slang s = new Slang(line);
//                    s.printData();
                    trie.addNewSlang(s);
                }
            }
            return;
        } catch (IOException e) {
            System.err.println("Error when reading file: " + filePath);
        }
        
        try (InputStream inputStream = getClass().getResourceAsStream("/slang.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                int countBackticks = countBackticks(line);
                if (countBackticks == 1) {
                    Slang s = new Slang(line);
//                    s.printData();
                    trie.addNewSlang(s);
                }
            }
        } catch (IOException e) {
            System.err.println("Error when reading file: " + filePath);
        }
    }

    private int countBackticks(String line) {
        int count = 0;
        for (char ch : line.toCharArray()) {
            if (ch == '`') {
                count++;
            }
        }
        return count;
    }
}