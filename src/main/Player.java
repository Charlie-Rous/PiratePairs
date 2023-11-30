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

    private void increaseScore(int num) {
        score += num;
        if (score >= PiratePairs.getMaxScore()) {
            System.out.println(name + "'s score is " + score + " " + name + " is out");
            PiratePairs.removePlayer(this);
        }
    }


    public void takeCard(int card) {
        boolean containedInHand = false;
        String aOrAn = "a ";
        if (card == 8) {
            aOrAn = "an ";
        } 
        String message = name + " drew " + aOrAn + card;
        for (int i = 0; i < hand.length; i++) {
            if (card == hand[i]) {
                containedInHand = true;
                message += " Thats a match!";
            }
        }
        System.out.println(message);
        if (containedInHand) {
            increaseScore(card);;
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
        if (score + minimumCard >= PiratePairs.maxScore) {
            return true;
        }

        for (int card : hand) {
            matchChance += CalcMatchChance(card);
            sum += card;
        }
        expectedValue = sum * matchChance;
        // System.out.println(name + " expextedVal: " + expectedValue);
        
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
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] < minimum) {
                minimum = cards[i];
            }
        }
        return minimum;
    }

    public void takeMininimum(int[] cardsOnTable, Player player) {
        int minimumCard = minimumCard(cardsOnTable);
        increaseScore(minimumCard);;
        if (player != this) {
            player.loseCard(minimumCard);
        }
        discardHand();
        System.out.println(name + " took " + player.name + "'s " + minimumCard(cardsOnTable));
        
    }
 
    public void loseCard(int card) {
        int[] newHand = new int[hand.length - 1];
        int[] discard = new int[1];
        int index = 0;
        for (int i = 0; i < hand.length; i ++) {
            if (hand[i] != card) {
                newHand[index] = hand[i];
                index++;
            } else {
                discard[0] = hand[i];
            }
        }
        deck.discard(discard);
        hand = newHand;
        if (hand.length == 0) {
            hasCards = false;
        }
    }

}


