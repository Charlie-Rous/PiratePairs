public class PiratePairs {
    static Player[] players = new Player[2];
    static Player p1 = new Player("Dave");
    static Player p2 = new Player("Mark");
    
    static Deck deck = new Deck();
    public static void main(String[] args){
        players[0] = p1;
        players[1] = p2;
        Start();
        
    }

    public static void Start() {
        deck.populateDeck();
        deck.shuffle();
        for (Player player : players) {
            player.addToHand(deck.dealCard());
        }
    }
}