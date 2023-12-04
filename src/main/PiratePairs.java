public class PiratePairs {
    static Player[] players = new Player[4];
    static Deck deck = new Deck();
    static Player p1 = new Player("Dave", deck);
    static Player p2 = new Player("Mark", deck);
    static Player p3 = new Player("Henrey", deck);
    static Player p4 = new Player("Chris", deck);
    static boolean playing = true;
    static Dealer dealer = new Dealer(deck);

    public static void main(String[] args) {
        dealer.players[0] = p1;
        dealer.players[1] = p2;
        dealer.players[2] = p3;
        dealer.players[3] = p4;
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

}
