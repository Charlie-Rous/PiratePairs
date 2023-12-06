public class PiratePairs {
    static Deck deck = new Deck();
    static boolean playing = true;
    static Dealer dealer = new Dealer(deck);

    public static void main(String[] args) {
        populateGame(5);
        dealer.StartGame();
        while (playing) {
            dealer.playOneRound();
        }

    }

    public static void setPlaying(boolean tf) {
        playing = tf;
    }

    public static boolean getPlaying() {
        return playing;
    }

    private static void populateGame(int numPlayers) {
        for (int i = 0; i < numPlayers; i++) {
            // give player random name and strategy
            int strategy = (int) (Math.random() * Player.NUM_STRATEGIES);
            dealer.addPlayer(new Player(Names.randomName(), deck, strategy, dealer));
        }
    }

}
