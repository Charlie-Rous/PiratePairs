public class Player {
    private String name;
    private int[] hand = {};
    private int score = 0;
    private Deck deck;
    private float maxMatchChance;
    private boolean hasCards = false;
    

    public Player(String _name, Deck _deck, float _maxMatchChance) {
        name = _name;
        deck = _deck;
        maxMatchChance = _maxMatchChance;
    }

    public void increaseScore(int num) {
        score += num;
    }


    public void takeCard(int card) {
        boolean containedInHand = false;
        for (int i = 0; i < hand.length; i++) {
            if (card == hand[i]) {
                containedInHand = true;
            }
        }
        if (containedInHand) {
            score += card;
            System.out.println(name + " drew a " + card);
            discardHand();
        } else {
            addToHand(card);
        }
        
    }
    
    private void addToHand(int card) {
        int[] tempHand = new int[hand.length + 1];

        for (int i = 0; i < hand.length; i++) {
            tempHand[i] = hand[i];
        }

        tempHand[tempHand.length - 1] = card;
        hasCards = true;
        hand = tempHand;
    }

    public String handToString() {
        String handToString = "[";
        
        if (hand.length > 0){
            handToString += hand[0];
            for (int i = 1; i < hand.length; i ++) {
                handToString += "," + hand[i];
            }
        }
 
        
        handToString += "]";
        return handToString;
        
    }

    public boolean wantsCard(int[] cardOnTable) {
        float matchChance = 0;
        int sum = 0;
        int minimumCard = minimumCard(cardOnTable);
        float expectedValue = 0;
        for (int card : hand) {
            matchChance += CalcMatchChance(card);
            sum += card;
        }
        expectedValue = sum * matchChance;
        System.out.println(name + " expextedVal: " + expectedValue);
        System.out.println("minimum card: " + minimumCard);
        if (expectedValue < minimumCard) {
            return true;
        } else {
            return false;
        }
        
    }

    private float CalcMatchChance(int card) {
        int[] numCards = deck.getNumCards();
        float matchChance = (float) numCards[card] / deck.getDeckLength();
        return matchChance;
    }

    private void discardHand() {
        deck.discard(hand);
        hand = new int[0];
        hasCards = false;

    }

    public String getName() {
        return name;
    }

    public boolean hasCards() {
        return hasCards;
    }

    public int getScore() {
        return score;
    }

    public int[] getHand() {
        return hand;
    }

    private int minimumCard(int[] cards) {
        int minimum = cards[0];
        for (int i = 1; i < cards.length; i++) {
            if (cards[i] < minimum) {
                minimum = cards[i];
            }
        }
        return minimum;
    }

}


