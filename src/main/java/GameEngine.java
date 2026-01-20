public class GameEngine {
    private static final int MAX_ATTEMPTS = 10;

    private final int min;
    private final int max;
    private int target;
    private int attempts;
    private boolean gameWon;

    private boolean userQuit;
    private boolean gameOver;


    private boolean hintsEnabled;


    public GameEngine(int min, int max) {
        this.min = min;
        this.max = max;
        this.attempts = 0;
        this.gameWon = false;

        this.userQuit = false;

        this.gameOver = false;

        this.hintsEnabled = true;

        reset();
    }

    public GuessResult makeGuess(int guess) {
        // Quit
        if (guess < 0) {
            userQuit = true;
            return new GuessResult(false, "Exiting game...", attempts);
        }

        attempts++;

        // Win
        if (guess == target) {
            gameWon = true;

            return new GuessResult(true,
                    "Correct! You guessed it in " + attempts + " attempts.",
                    attempts);
        }

        // Game over
        if (attempts >= MAX_ATTEMPTS) {
            gameOver = true;
            return new GuessResult(false,
                    "Game Over! You've used all " + MAX_ATTEMPTS + " attempts. The number was " + target + ".",
                    attempts);
        }

        // Normal wrong guess + remaining attempts message
        int remaining = MAX_ATTEMPTS - attempts;

        GuessResult result;
        if (guess < target) {
            result = new GuessResult(false, "Too low! " + remaining + " attempts remaining", attempts);
        } else {
            result = new GuessResult(false, "Too high! " + remaining + " attempts remaining", attempts);
        }

        if (hintsEnabled && attempts >= 3) {
            result.setHint(getHint(guess));
        }

        return result;

    }



    public void reset() {
        target = Utils.randomInt(min, max);
        attempts = 0;
        gameWon = false;

        userQuit = false;
        gameOver = false;

    }

    public boolean isGameWon() {
        return gameWon;
    }

    public boolean hasUserQuit() {
        return userQuit;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getAttempts() {
        return attempts;
    }

    public int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public boolean isHintsEnabled() {
        return hintsEnabled;
    }

    public void setHintsEnabled(boolean enabled) {
        this.hintsEnabled = enabled;
    }

    private String getHint(int guess) {
        if (!hintsEnabled) {
            return "";
        }

        int diff = Math.abs(target - guess);
        if (attempts >= 3 && diff <= 10) {
            return " HINT: You're very close!";
        } else if (attempts >= 5 && diff <= 20) {
            return " HINT: Getting warmer!";
        }
        return "";
    }

    // For testing purposes only
    protected void setTarget(int target) {
        this.target = target;
    }

    protected int getTarget() {
        return target;
    }
}
