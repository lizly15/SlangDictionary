import java.util.Random;

public class Trie {
	private TrieNode root;
	
	public Trie() {
		root = new TrieNode();
	}
	
	public void addNewSlang(Slang s) {
		String word = s.getWord();
		TrieNode curNode = root;
		for(int i = 0; i < word.length(); ++i) {
			char curChar = word.charAt(i);
			boolean isAdd = curNode.addNextNode(curChar);
			TrieNode nextNode = curNode.getNextNode(curChar);
			nextNode.setParNode(curNode);
			nextNode.addHintSlang(s);
			curNode = nextNode;
		}
		
		curNode.setSlang(s);
	}
	
	public TrieNode searchSlang(String word) {
		TrieNode curNode = root;
		for(int i = 0; i < word.length(); ++i) {
			char curChar = word.charAt(i);
			TrieNode nextNode = curNode.getNextNode(curChar);
			if(nextNode == null) return null;
			curNode = nextNode;
		}
		return curNode;
	}
	
	public Slang randomSlang() {
        TrieNode curNode = root;
        
        while(true) {
        	TrieNode nextNode = curNode.randomNextNode();
        	if(nextNode == null) break;
        	curNode = nextNode;
        }
		
		return curNode.getSlang();
    }
}
