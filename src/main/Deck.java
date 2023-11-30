public class Deck {
    private int deckLength = 55;
    private int[] cards = new int[deckLength];
    private int index = 0;
    private int[] discard = new int[0];
    private int[] numCards = {0 , 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
   

   public void populateDeck() {
    for (int i = 1; i <= 10; i++) {
        for (int j = 0; j < i; j++ ) {
            cards[index] = i;
            index++;
        }
    }
   }

   public void printDeck() {
    for (int i = 0; i < deckLength; i++) {
        System.out.println(cards[i]);
    }
   }

   public void shuffle() {
    int[] shuffledCards = new int[deckLength];
    int index = 0;
    for (int i = deckLength - 1; i >= 0; i--) {
        int randomCardIndex = (int) (Math.random() * i);
        shuffledCards[index] = cards[randomCardIndex];

        int tempCard = cards[randomCardIndex];
        cards[randomCardIndex] = cards[i];
        cards[i] = tempCard;
        index++;
    }
   }
    
   public int dealCard() {
    int card = cards[0];
    deckLength -= 1;
    
    int[] newCards = new int[deckLength]; 
    for (int i = 0; i < deckLength; i++) {
        newCards[i] = cards[i + 1];
    }

    cards = newCards;
    numCards[card] -= 1;

    if (cards.length == 0) {
        reShuffle();
    }

    
    return card;
   }
    
   public int[] getNumCards() {
    return numCards;
   }
   
   public int getDeckLength() {
    return deckLength;
   }

   public void discard(int[] hand) {
        int[] tempDiscard = new int[discard.length + hand.length];
        int index = 0;
        for(int i = 0; i < discard.length; i++) {
            tempDiscard[i] = discard[i];
            index++;
        }
        for (int i = 0; i < hand.length; i++) {
            tempDiscard[index] = hand[i];
            index++;
        }
        discard = tempDiscard;
   }

   public void reShuffle() {
        System.out.println("Deck Reset");
        cards = discard;
        for (int i = 0; i < cards.length; i++) {
            numCards[cards[i]] += 1;
        }
        deckLength = cards.length;
        shuffle();
   }
}

