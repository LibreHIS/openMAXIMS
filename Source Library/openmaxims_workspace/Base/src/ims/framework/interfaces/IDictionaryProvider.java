package ims.framework.interfaces;

public interface IDictionaryProvider
{
	String getName();
	String[] getWords();
	boolean addWord(String word);
	boolean isReadOnly();
	boolean isCorrect(String word);	
}
