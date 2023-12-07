
public class TheChosenWord {

    private WordsBank b = new WordsBank();
    private String word = b.getRandomWord();

    private String underscore = underscoreBuild();

    public void setUnderscore(String underscore) {
        this.underscore = underscore;
    }

    public String getUnderscores() {
        return underscore;
    }

    // Build the initial underscore representation based on the length of the word
    private String underscoreBuild() {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            str.append("_");
        }

        return str.toString();
    }

    public TheChosenWord() {
        word = b.getRandomWord();
        underscore = underscoreBuild();
    }

    public String getWord() {
        return word;
    }

}
