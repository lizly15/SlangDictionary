import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrieNode {
    private Character label;
    private List<TrieNode> listNextNode; 
    private List<Slang> listHintSlang;
    private TrieNode parNode;
    private Slang slang;

    public TrieNode() {
        this.listNextNode = new ArrayList<TrieNode>(); 
        this.listHintSlang = new ArrayList<Slang>();
    }

    public TrieNode(Character label) {
        this.label = label;
        this.listNextNode = new ArrayList<TrieNode>(); 
        this.listHintSlang = new ArrayList<Slang>();
    }

    public Character getLabel() {
        return label;
    }
    
    public Slang getSlang() {
        return slang;
    }
    
    public void addMeanings(String meaning) {
        this.slang.addMeanings(meaning);
    }
    
    public void setMeanings(String meaning) {
    	this.slang.setMeanings(meaning);
    }
    
    public void setParNode(TrieNode parNode) {
    	this.parNode = parNode;
    }
    
    public void setSlang(Slang s) {
    	slang = s;
    }
    
    public void addHintSlang(Slang s) {
    	if (!listHintSlang.contains(s))
    		listHintSlang.add(s);
    }
    
    public List<Slang> getListHintSlang() {
    	return listHintSlang;
    }

    public boolean addNextNode(Character nextChar) {
        if (getNextNode(nextChar) == null) {
            listNextNode.add(new TrieNode(nextChar));
            return true;
        }
        return false;
    }

    public TrieNode getNextNode(Character nextChar) {
        for (TrieNode nextNode : listNextNode) {
            if (nextNode.getLabel().equals(nextChar)) {
                return nextNode;
            }
        }
        return null;
    }
    
    public TrieNode randomNextNode() {
        Random random = new Random();
        
        if (slang != null) {
            if (random.nextInt(4) == 0) { 
                return null;
            }
        }

        if (listNextNode.isEmpty()) {
            return null; 
        }
        
        int randomIndex = random.nextInt(listNextNode.size());
        return listNextNode.get(randomIndex); 
    }
}