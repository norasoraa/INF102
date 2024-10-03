package no.uib.inf102.wordle.controller.AI;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import no.uib.inf102.wordle.model.word.AnswerType;
import no.uib.inf102.wordle.model.word.WordleCharacter;
import no.uib.inf102.wordle.model.word.WordleWord;
import no.uib.inf102.wordle.model.word.WordleWordList;

public class BetterAI implements IStrategy {

    private WordleWord feedback;
    private WordleWordList guesses;
    private List<String> allWords;
    private List<String> possibleWords;

    public BetterAI() {
        reset();
        this.allWords = guesses.getAllWords();
        this.possibleWords = guesses.possibleAnswers();
    }

    /* Lagre de grønne bokstavene, men ikke gjette videre fordi vi vil heller eliminere
     * flere bokstaver ved å få flere grå. Ikke velge ord med samme bokstav flere ganger 
     * dersom den første bokstaven allerede er grå. Vil ha tilgang til hjelpemetodene laget
     * i Frequency Strategy. 
     * Kan lagre de gule bokstavene vi får også, hvis vi d ahar til sammen 5 bokstaver med gul
     * og grønn, så kan vi teste ord med bare disse bokstavene i rekkefølgen med grønne og 
     * teste hvor de gule passer inn.
     * 
     * Bruke eliminatewords for possibleAnswers for hvert gjett, og lagre disse ordene i en liste, så tippe fra denne listen  
     */

    @Override
    public String makeGuess(WordleWord feedback) {
        this.feedback = feedback;
        if (feedback != null) {
            newStrat();
        }
        return guesses.bestStartWord(possibleWords);
    }

    private void wrongWords() {
        List<String> wrongWords = new LinkedList<>();
        for (String word : allWords) {
            int index = 0;
            boolean notGuess = false;
            for (WordleCharacter wordleChar : feedback) {
                if (twoInstances(word)) {
                    notGuess = true;
                    index++;
                    break;
                }
                else if (wordleChar.answerType == AnswerType.CORRECT && word.charAt(index) == wordleChar.letter) {
                    notGuess = true;
                }
                else if (word.indexOf(wordleChar.letter) == -1) {
                    notGuess = false;
                }
                index++;
            }
            if (notGuess) {
                wrongWords.add(word);
            }
        }
        allWords.removeAll(wrongWords);
    }

    private boolean twoInstances(String word) {
        HashMap<Character, Integer> letterCount = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            letterCount.put(word.charAt(i), letterCount.getOrDefault(word.charAt(i), 0) + 1);
        }
        for (int i : letterCount.values()) {
            if (i > 1) {
                return true;
            }
        }
        return false;
    }

    private String newStrat() {
        if (possibleWords.size() <= 2) {
            return guesses.bestStartWord(possibleWords);
        }
        else {
            wrongWords();
            guesses.eliminateWords(feedback);
            return guesses.bestStartWord(allWords);
        }
    }

    @Override
    public void reset() {
        guesses = new WordleWordList();
    }
}
