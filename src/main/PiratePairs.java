public class PiratePairs {
    static Player[] players = new Player[4];
    static Deck deck = new Deck();
    public static void main(String[] args){
        deck.populateDeck();
        deck.shuffle();
        deck.printDeck();
        System.out.println("Card: " + deck.dealCard());
        deck.printDeck();
    }
}