import java.util.Random;

public class Trie {
	private TrieNode root;
	private TrieNode defroot;
	
	public Trie() {
		root = new TrieNode();
		defroot = new TrieNode();
	}
	
	private void addSlangByWord(Slang s) {
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
	
	private void addSlangByDef(Slang s) {
		for(String word: s.getMeanings()) {
			TrieNode curNode = defroot;
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
	}
	
	public void addNewSlang(Slang s) {
		addSlangByWord(s);
		addSlangByDef(s);
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
	
	public TrieNode searchSlangByDef(String word) {
		TrieNode curNode = defroot;
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
