import java.util.Random;

public class WordsBank {

    private String[] bank =
            {"elephant", "computer", "mountain",
                    "giraffe", "jazz", "xylophone",
                    "umbrella", "kangaroo", "vortex", "quasar"};


    public String getRandomWord() {
        Random r = new Random();
        return bank[r.nextInt(bank.length)];
    }

}
