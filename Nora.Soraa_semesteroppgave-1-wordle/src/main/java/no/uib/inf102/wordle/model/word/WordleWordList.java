package no.uib.inf102.wordle.model.word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import no.uib.inf102.wordle.resources.GetWords;

/**
 * This class describes a structure of two lists for a game of Wordle: The list
 * of words that can be used as guesses and the list of words that can be possible answers.
 */
public class WordleWordList {

	/**
	 * All words in the game. These words can be used as guesses.
	 */
	private List<String> allWords;

	/**
	 * A subset of <code>allWords</code>. <br>
	 * </br>
	 * These words can be the answer to a wordle game.
	 */
	private List<String> possibleAnswers;

	/**
	 * Create a WordleWordList that uses the full words and limited answers of the
	 * GetWords class.
	 */
	public WordleWordList() {
		this(GetWords.ALL_WORDS_LIST, GetWords.ANSWER_WORDS_LIST);
	}

	/**
	 * Create a WordleWordList with the given <code>words</code> as both guesses and
	 * answers.
	 * 
	 * @param words
	 */
	public WordleWordList(List<String> words) {
		this(words, words);
	}

	/**
	 * Create a WordleWordList with the given lists as guessing words and answers.
	 * <code>answers</code> must be a subset of <code>words</code>.
	 * 
	 * @param words   The list of words to be used as guesses
	 * @param answers The list of words to be used as answers
	 * @throws IllegalArgumentException if the given <code>answers</code> were not a
	 *                                  subset of <code>words</code>.
	 */
	public WordleWordList(List<String> words, List<String> answers) {
		HashSet<String> wordsSet = new HashSet<String>(words);
		if (!wordsSet.containsAll(answers))
			throw new IllegalArgumentException("The given answers were not a subset of the valid words.");

		this.allWords = new ArrayList<>(words);
		this.possibleAnswers = new ArrayList<>(answers);
	}

	/**
	 * Get the list of all guessing words.
	 * 
	 * @return all words
	 */
	public List<String> getAllWords() {
		return allWords;
	}

	/**
	 * Returns the list of possible answers.
	 * 
	 * @return
	 */
	public List<String> possibleAnswers() {
		return possibleAnswers;
	}

	/**
	 * Eliminates words from the possible answers list using the given
	 * <code>feedback</code>
	 * 
	 * @param feedback
	 */
	public void eliminateWords(WordleWord feedback) {
		List<String> notPossibleAnswer = new LinkedList<>();
		for (String word : possibleAnswers) {
			if (!WordleWord.isPossibleWord(word, feedback)) {
				notPossibleAnswer.add(word);
				
			}
		}
		possibleAnswers.removeAll(notPossibleAnswer);
	}

	// Fra oppgavegjennomgang
	private void eliminateWordsFasit(WordleWord feedback) {
		String guess = feedback.getWordString();
		ArrayList<String> newPossible = new ArrayList<>();
		for (String word : possibleAnswers()) {
			if (feedback.equals(WordleAnswer.matchWord(guess, word))) {
				newPossible.add(word);
			}
		}
		possibleAnswers = newPossible;
	}

	/**
	 * Returns the amount of possible answers in this WordleWordList
	 * 
	 * @return size of
	 */
	public int size() {
		return possibleAnswers.size();
	}

	/**
	 * Removes the given <code>answer</code> from the list of possible answers.
	 * 
	 * @param answer
	 */
	public void remove(String answer) {
		possibleAnswers.remove(answer);

	}

	/**
	 * Returns the word length in the list of valid guesses.
	 * @return
	 */
	public int wordLength() {
		return allWords.get(0).length();
	}

	/**
	 * Returns the best start word from a given list.
	 * @return a string
	 */
	public String bestStartWord(List<String> words) {
        HashMap<String, Integer> scoreHashMap = wordScore(words);
        List<String> keys = new ArrayList<>(scoreHashMap.keySet());
        String bestWord = keys.get(0);
        for (int i = 0; i < keys.size()-1; i++) {
            if (scoreHashMap.get(keys.get(i)) > scoreHashMap.get(bestWord)) {
                bestWord = keys.get(i);
            }
        }
        return bestWord;
    }

	/**
	 * Help method that computes the score that each word has based on
	 * the possibility for each letter to be correct.
	 * @param words
	 * @return a Hashmap with the words as keys and their score as values
	 */
	private HashMap<String, Integer> wordScore(List<String> words) {
        List<HashMap<Character, Integer>> letterHashMaps = letterPositionFrequency(words);
        HashMap<String, Integer> scoreHashMap = new HashMap<>();
        for (String word : words) {
            int totalScore = 0;
            for (int i = 0; i < wordLength(); i++) {
                int letterScore = letterHashMaps.get(i).get(word.charAt(i));
                totalScore += letterScore;
            }
            scoreHashMap.put(word, totalScore);
        }
        return scoreHashMap;
    }
    
	/**
	 * Help method that computes the number of words which has a letter on a given position
	 * @param words
	 * @return a list with five HashMaps which symbolizes the five positions in the words
	 * that can be guessed. The HashMap contains letters and their frequency at that position in the words.
	 */
    private List<HashMap<Character, Integer>> letterPositionFrequency(List<String> words) {
        List<HashMap<Character, Integer>> positionHashMaps = new ArrayList<>();
        for (String word : words) {
            for (int i = 0; i < wordLength(); i++) {
                positionHashMaps.add(new HashMap<>());
                HashMap<Character, Integer> currentHashMap = positionHashMaps.get(i);
                currentHashMap.put(word.charAt(i), currentHashMap.getOrDefault(word.charAt(i), 0) + 1);
            }
        }
        return positionHashMaps;
    }
}
