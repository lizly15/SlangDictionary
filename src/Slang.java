import java.util.Arrays;
import java.util.List;

public class Slang {
	private String word;
	private List<String> meanings;
	
	public Slang(String data) {
        String[] parts = data.split("`", 2);
        if (parts.length == 2) {
            word = parts[0].trim();
            meanings = splitMeanings(parts[1].trim());
        }
    }
	
	private List<String> splitMeanings(String data) {
		return Arrays.asList(data.split("\\|"));
	}
	
	public String getWord() {
        return word;
    }

    public List<String> getMeanings() {
        return meanings;
    }
    
    public String getDefinition() {
        return String.join(", ", meanings);
    }
    
    public String getFullData() {
    	return "Word: " + word + "\n" + "Meanings: " + String.join(", ", meanings);
    }
    
    public void printData() {
        System.out.println("Word: " + word);
        System.out.println("Meanings: " + String.join(", ", meanings));
    }
}
