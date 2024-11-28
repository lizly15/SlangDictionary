import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class DictionaryDataStructure {
    private static final List<String> FILE_PATHS = Arrays.asList(
        "D:\\Java\\SlangDictionary\\src\\slang.txt" // Đường dẫn tệp cụ thể
    );

    public void readFiles(Trie trie) {
        for (String filePath : FILE_PATHS) {
            readFile(filePath, trie);
        }
    }

    private void readFile(String filePath, Trie trie) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                int countBackticks = countBackticks(line);
                if (countBackticks == 1) {
                    Slang s = new Slang(line);
                    s.printData();
                    trie.addNewSlang(s);
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc tệp: " + filePath);
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