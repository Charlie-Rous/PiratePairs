public class Deck {
    int[] cards = new int[55];

    int index = 0;

    int[] discard = new int[55];

   public void populateDeck() {
    for (int i = 1; i <= 10; i++) {
        for (int j = 0; j < i; j++ ) {
            cards[index] = i;
            index++;
        }
    }
   }

   public void printDeck() {
    for (int i = 0; i < 55; i++) {
        System.out.println(cards[i]);
    }
   }

    

    
}
