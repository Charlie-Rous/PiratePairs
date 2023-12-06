public class PiratePairs {
    static Deck deck = new Deck();
    static boolean playing = true;
    static Dealer dealer = new Dealer(deck);
    public static int numPlayers = 5;
    public static int rounds = 50;

    public static void main(String[] args) {
        populateGame(numPlayers);
        for (int i = 0; i < rounds; i++) {
            dealer.resetGame();
            while (playing) {
                dealer.playOneRound();
            }
            
        }
        dealer.printStrategies();
        

    }

    public static void setPlaying(boolean tf) {
        playing = tf;
    }

    public static boolean getPlaying() {
        return playing;
    }

    public static void populateGame(int numPlayers) {
        for (int i = 0; i < numPlayers; i++) {
            // give player random name and strategy
            int strategy = (int) (Math.random() * Player.NUM_STRATEGIES);
            dealer.addPlayer(new Player(Names.randomName(), deck, strategy, dealer));
        }
    }

}
