public class Player {
    public static final int NUM_STRATEGIES = 5;
    private String name;
    private int[] hand = {};
    private int score = 0;
    private Deck deck;
    private boolean hasCards = false;
    private int strategy;
    private Dealer dealer;
    private boolean[] strategys = { strategyZero(), strategyOne(), strategyTwo(), strategyThree() };

    public Player(String name, Deck deck, int strategy, Dealer dealer) {
        this.name = name;
        this.deck = deck;
        this.strategy = strategy;
        this.dealer = dealer;
    }

    private void increaseScore(int num) {
        score += num;
    }

    public void takeCard(int card) {
        boolean containedInHand = false;
        String aOrAn = "a ";
        if (card == 8) {
            aOrAn = "an ";
        }

        for (int i = 0; i < hand.length; i++) {
            if (card == hand[i]) {
                containedInHand = true;
                System.out.println(name + " drew " + aOrAn + " " + card + ". Thats a match!");
                break;
            }
        }

        if (containedInHand) {
            increaseScore(card);
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

        if (hand.length > 0) {
            handToString += hand[0];
            for (int i = 1; i < hand.length; i++) {
                handToString += "," + hand[i];
            }
        }

        handToString += "]";
        return handToString;

    }

    public boolean wantsCard(int[] cardOnTable) {
        if (strategy != strategys.length) {
            return strategys[strategy];
        }
        return defaultStrategy(cardOnTable);

    }

    private float calcMatchChance(int card) {
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
        increaseScore(minimumCard);
        ;
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
        for (int i = 0; i < hand.length; i++) {
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

    private boolean defaultStrategy(int[] cardOnTable) {
        float matchChance = 0;
        int sum = 0;
        int minimumCard = minimumCard(cardOnTable);
        float expectedValue = 0;
        if (score + minimumCard >= Dealer.maxScore) {
            return true;
        }
        for (int card : hand) {
            matchChance += calcMatchChance(card);
            sum += card;
        }
        expectedValue = sum * matchChance;

        if (expectedValue < minimumCard) {
            return true;
        } else {
            return false;
        }
    }

    private boolean strategyZero() {
        if (Math.random() < .5) {
            return true;
        }
        return false;
    }

    private boolean strategyOne() {
        float matchChance = 0;
        for (int card : hand) {
            matchChance += calcMatchChance(card);
        }
        if (matchChance < .5) {
            return true;
        } else {
            return false;
        }
    }

    private boolean strategyTwo() {
        if (hand.length < 5) {
            return true;
        }
        return false;
    }

    private boolean strategyThree() {
        for (int card : hand) {
            if (card >= minimumCard(dealer.cardsOnTable())) {
                if (calcMatchChance(card) > .5) {
                    return true;
                }
            }
        }
        return false;

    }

    public int getStrategy() {
        if (strategy == strategys.length) {
            return -1;
        }
        return strategy;
    }
}
