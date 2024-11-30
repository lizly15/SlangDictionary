import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Slang {
	private String word;
	private List<String> meanings;
	
	public Slang() {
		this.meanings = new ArrayList<String>(); 
	}
	
	public Slang(String data) {
        String[] parts = data.split("`", 2);
        if (parts.length == 2) {
            word = parts[0].trim();
            this.meanings = new ArrayList<String>(); 
            List<String> tem = splitMeanings(parts[1].trim());
            for(String def: tem) {
            	this.meanings.add(def);
            }
        }
    }
	
	public Slang(String word, String meaning) {
		this.meanings = new ArrayList<String>(); 
        this.word = word;
        this.meanings.add(meaning);
    }
	
	public Slang(String word, List<String> meanings) {
        this.word = word;
        this.meanings = meanings;
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
    
    public void addMeanings(String meaning) {
        this.meanings.add(meaning);
    }
    
    public void setMeanings(String meaning) {
    	this.meanings.clear();
        this.meanings.add(meaning);
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
