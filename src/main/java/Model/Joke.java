package Model;

public class Joke {
    private String type;
    private String setup;
    private String punchline;

    @Override
    public String toString() {
        return setup + "\n" + punchline;
    }
}
