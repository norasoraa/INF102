# Runtime Analysis
For each method of the tasks give a runtime analysis in Big-O notation and a description of why it has this runtime.

**If you have implemented new methods not listed you must add these as well, e.g. any helper methods. You need to show how you analyzed any methods used by the methods listed below.**

The runtime should be expressed using these three parameters:
   * `n` - number of words in the list allWords
   * `m` - number of words in the list possibleWords
   * `k` - number of letters in the wordleWords


## Task 1 - matchWord
* `WordleAnswer::matchWord`: O(k)
    * The runtime is O(k) because the method iterates through the characters in the string answer. It does this several times, but because they are seperated for-loops it will be O(k) for each one of them and we only care for the largest loop with the slowest runtime. The calculations and if-sentences inside the for-loops are O(1), as weel as the initialization of the wordlength, feedback and lettersAnswer HashMap. The expected runtime for put(), get() and remove() in a HashMap is O(1), but it could be O(n) in worst-case. Using O(1) for these methods makes the analysis more similar to practical runtime and because we do not include the constants, the runtime will be O(k).

## Task 2 - EliminateStrategy
* `WordleWordList::eliminateWords`: O(m * k)
    * The runtime is O(m * k) because we iterate through all the words in the list possibleWords and we iterate through the characters in the word. The method isPossibleWord() calls on the method matchWord() which was O(k), and then it compares the result which is O(1). Therefore, isPossibleWord() has a runtime of O(k). The initialization of the LinkedList notPossibleAnswer and LinkedList.add() is O(1). removeAll() depends on the length of the two lists, O(m+length of notPossibleAnswer). Since we iterate through all the words in possibleAnswers and then call on isPossibleWord(), we will have a runtime of O(m * k) beacuse it is larger than O(m + length of notPossibleAnswer).

## Task 3 - FrequencyStrategy
* `FrequencyStrategy::makeGuess`: O(m * k)
    * The runtime is O(m * k) because both eliminateWords() and bestStartWord() has runtime O(m * k). bestStartWord() calls on wordScore() and wordScore() calls on letterPositionFrequency(). In letterPositionFrequency() the initialization of positionHashMap and positionHashMap.get() is O(1). The expected runtime for positionHashMaps.add() is O(1), but could be O(n) in worst case. The same is for currentHashMap.put(), is O(1), but O(n) in worst case. Because we first iterate through all the words in possibleWords and then iterate through the characters in each word, the runtime of the outer for-loop is O(m * k). Therefore, letterPositionFrequency is O(m * k). wordScore() is also O(m * k), beacuse we iterate trough all the words in possibleWords and all the characters in the words. In bestStartWord() we iterate through all the keys in the HashMap from wordScore so this depends on the size of the list of keys. Inside the for-loop, all the calculations are O(1). And since the call for wordScore is O(m * k), it will be bigger than the for-loop with O(size of keys-list) and therefore bestStartWord has runtime O(m * k). Since the runtime of bestStartWord() is the same as eliminateWords() and it is only called for once in makeGuess(), it will not matter against the runtime of eliminateWords(). Therefore the runtime for makeGuess() is O(m * k).



# Task 4 - Make your own (better) AI
For this task you do not need to give a runtime analysis. 
Instead, you must explain your code. What was your idea for getting a better result? What is your strategy?

I was not able to finish my own AI.

My strategy was to build on Frequency Strategy. For the first guess I used bestStartWord() from the list of possibleWords. When I got this feedback I called on newStrat(). From the feedback of the first guess, I wanted to remove all the words that had the same letters as the guess if the feedback on the letter was grey so that I only guessed new letters to gain more information. If the letter in the guess turned green, I also wanted to remove all the words that had the same letter on the same position as the green one. I also wanted to remove all the words that had duplicates in it. Then I removed all these types of words from the list of allWords. After this I called on eliminateWords() to remove the words that did not match with the feedback from possibleWords. And then I would make a guess from allWords with the bestStartWord() method. I will continue this until the the list of possibleWords was small enough to guess from. And then I will choose the bestStartWord() from possibleWords.
