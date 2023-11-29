public class Deck {
    int deckLength = 55;
    int[] cards = new int[deckLength];

    int index = 0;

    int[] discard = new int[deckLength];

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

    return card;
   }
    
}
