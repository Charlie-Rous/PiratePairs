public class Player {
    private String name;
    private int[] hand = {};
    private int score;
    private Deck deck;
    private float maxMatchChance;

    public Player(String _name, Deck _deck, float _maxMatchChance) {
        name = _name;
        deck = _deck;
        maxMatchChance = _maxMatchChance;
    }

    public void increaseScore(int num) {
        score += num;
    }

    public void addToHand(int card) {
        int[] tempHand = new int[hand.length + 1];

        for (int i = 0; i < hand.length; i++) {
            tempHand[i] = hand[i];
        }

        tempHand[tempHand.length - 1] = card;
        hand = tempHand;
    }

    public void printHand() {
        for (int i = 0; i < hand.length; i ++) {
            System.out.println(hand[i]);
        }
    }

    public boolean wantsCard() {
        float matchChance = 0;
        for (int card : hand) {
            matchChance += CalcMatchChance(card);
        }
        System.out.println("TotalMatchChance: " + matchChance);
        if (matchChance <= maxMatchChance) {
            return true;
        } else {
            return false;
        }
    }

    private float CalcMatchChance(int card) {
        int[] numCards = deck.getNumCards();
        float matchChance = (float) numCards[card] / deck.getDeckLength() * 100;
        System.out.println("Match chance " + card + ": " + matchChance);
        return matchChance;
    }
}
