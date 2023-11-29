public class Deck {
    final int DECK_LENGTH = 55;
    int[] cards = new int[DECK_LENGTH];

    int index = 0;

    int[] discard = new int[DECK_LENGTH];

   public void populateDeck() {
    for (int i = 1; i <= 10; i++) {
        for (int j = 0; j < i; j++ ) {
            cards[index] = i;
            index++;
        }
    }
   }

   public void printDeck() {
    for (int i = 0; i < DECK_LENGTH; i++) {
        System.out.println(cards[i]);
    }
   }

   public void shuffle() {
    int[] shuffledCards = new int[DECK_LENGTH];
    int index = 0;
    for (int i = DECK_LENGTH - 1; i >= 0; i--) {
        int randomCardIndex = (int) (Math.random() * i);
        shuffledCards[index] = cards[randomCardIndex];

        int tempCard = cards[randomCardIndex];
        cards[randomCardIndex] = cards[i];
        cards[i] = tempCard;
        index++;
    }
   }
    

    
}
