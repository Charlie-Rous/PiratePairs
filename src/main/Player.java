public class Player {
    private String name;
    private int[] hand;
    private int score;


    public Player(String _name, int[] _hand) {
        name = _name;
        hand = _hand;
    }

    public void increaseScore(int num) {
        score += num;
    }
}
