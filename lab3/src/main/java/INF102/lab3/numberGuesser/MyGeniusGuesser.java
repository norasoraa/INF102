package INF102.lab3.numberGuesser;


public class MyGeniusGuesser implements IGuesser {

    private RandomNumber number;
    private int upperbound;
    private int lowerbound;

	@Override
    public int findNumber(RandomNumber number) {
        this.number = number;
        this.lowerbound = number.getLowerbound();
        this.upperbound = number.getUpperbound();
        return recursiveFun(lowerbound, upperbound);
    }

    private int recursiveFun(int lowerbound, int upperbound) {
        int numberGuess = lowerbound + (upperbound - lowerbound) / 2;
        int guessResult = this.number.guess(numberGuess);
        
        if (guessResult == 0) {
            return numberGuess;
        }
        else if (guessResult == -1) {
            return recursiveFun(numberGuess, upperbound);
        }
        else {
            return recursiveFun(lowerbound, numberGuess);
        }
    }

    //fra oppgavegjennomgang 
    public int fasitFindNumber(RandomNumber number) {
        this.lowerbound = number.getLowerbound();
        this.upperbound = number.getUpperbound();
        while (lowerbound < upperbound) {
            int nextGuess = makeGuess();
            int queryAnswer = number.guess(nextGuess);
            if (updateBounds(nextGuess, queryAnswer)) {
                return nextGuess;
            }
        }
        return lowerbound;
    }

    //fra oppgavegjennomgang 
    private boolean updateBounds(int numberGuess, int queryAnswer) {
        if (queryAnswer == 0) {
            lowerbound = numberGuess;
            upperbound = numberGuess;
            return true;
        }
        if (queryAnswer == 1) {
            upperbound = numberGuess - 1;
        }
        else {
            lowerbound = numberGuess + 1;
        }
        return false;
    }

    //fra oppgavegjennomgang 
    private int makeGuess() {
		return (upperbound + lowerbound) / 2;
	}
}
