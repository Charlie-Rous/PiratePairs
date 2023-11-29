public class Player {
    private String name;
    private int[] hand = {};
    private int score;


    public Player(String _name) {
        name = _name;
        
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
}
