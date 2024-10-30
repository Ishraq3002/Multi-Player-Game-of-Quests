import java.util.*;

public class Player {
    private String id;
    private Deck hand;
    private int shieldCount;

    public Player(String id){
        if (id.isEmpty()){
            id = "No Name";
        }
        this.id = id;
        shieldCount = 0;
        hand = new Deck();
    }

    public void setHand (Deck deck){
        hand = deck;
    }

    public int getHandSize(){
        return hand.getSize();
    }

    public void updateShields(int count) {
        shieldCount += count;
        if (shieldCount < 0) {shieldCount = 0;}
    }

    public double getShields() {
        return shieldCount;
    }

    public String getID() {
        return id;
    }

    public String toString(){
        return "Player " + id;
    }

    public boolean addAllCards(Deck drawnCards){
        return hand.addAllCards(drawnCards);
    }

    public Deck getHand() {
        return hand;
    }

    public Card peakAtCard(int index) {
        return hand.peek(index);
    }

    public void removeCard(int index) {
        hand.removeCard(index);
    }

    // Checks if player can sponsor a quest of # stages
    public boolean canPlayerSponsor(int stages){
        // Stores the number of weapon cards with each card
        HashMap<Card, Integer> countCards = new HashMap<>();

        int totalFoeCount = 0;  // Counts the number of Foe cards in the player's hand
        int increasingFoeCount = 0;  // Counts the number of Foe cards that increasing
        int prevFoeValue = 0; //Keeps track of the previous Foe card's value

        ArrayList<Integer> values = new ArrayList<>(); // Keeps track of possible values each stage could have
        ArrayList<Card> duplicateFoeCards = new ArrayList<>();  //Stores the duplicate Foe cards
        ArrayList<Card> weaponCards = new ArrayList<>();


        // Count the number of Foe and Weapon cards in player's hand
        Comparator myComparator = new SortByCard(); // Sorts the player's hand
        Collections.sort(hand.getDeck(), myComparator);

        for (Card card : hand.getDeck()) {
            if (card.getType() == Card.CardType.Foe) {
                totalFoeCount++;

                if (card.getValue() > prevFoeValue) {
                    prevFoeValue = card.getValue();
                    increasingFoeCount++; // New Foe card
                    values.add(card.getValue());
                } else {
                    duplicateFoeCards.add(card); // Duplicate foe card
                }
            } else {
                if (countCards.containsKey(card)) { // Updating weapon counts
                    countCards.put(card, countCards.get(card) + 1);
                } else {
                    countCards.put(card, 1);
                    weaponCards.add(card);
                }
            }
        }


        if (increasingFoeCount >= stages){
            return true; // Enough increasing foe cards for all stages
            // Can form a basic quest where each stage is just a Foe card
        }

        if (totalFoeCount < stages) {
            return false; // Not enough Foe cards to sponsor each stage
        }

        // The number of stages left to make (that need to be of increasing value)
        int remainingStages = stages - increasingFoeCount;
        int validStages = 0;

        // Iterate through the duplicate foe cards to see if a weapon card can be added to it (and make a valid stage)
        // Checking if a Foe card can be combined with non-repeating weapon cards, or just a Foe card and a Weapon card on its own
        for (Card card: duplicateFoeCards){

            int totalStageValue = card.getValue(); // Initially the value of the stage == value of the Foe card
            ArrayList<Card> totalCard = new ArrayList<>();
            List<Card> toRemove = new ArrayList<>();

            for (Card weaponCard : weaponCards) {

                totalStageValue += weaponCard.getValue();
                totalCard.add(weaponCard);

                int currentStageValue = card.getValue() + weaponCard.getValue();

                if (!values.contains(currentStageValue)) {  // Checking if the foe card with the specific weapon card can give a unique value
                    values.add(currentStageValue);
                    validStages++;

                    int count = countCards.get(weaponCard) - 1;
                    // Updating the weapon counts
                    if (count == 0) {
                        toRemove.add(weaponCard);
                    } else {
                        countCards.put(weaponCard, count);
                    }
                    break;
                }

                if (!values.contains(totalStageValue)) { // Checking if the foe card combined with all the weapon cards gives a unique value
                    values.add(totalStageValue);
                    validStages++;

                    for (Card oldCard : new ArrayList<>(totalCard)) {
                        int count = countCards.get(oldCard) - 1;
                        if (count == 0) {
                            toRemove.add(oldCard);
                        } else {
                            countCards.put(oldCard, count);
                        }
                    }
                    break;
                }
            }
            weaponCards.removeAll(toRemove); // Removing the weapon cards for the next stage (as they are being used)

            if (validStages == remainingStages){  // If the number of valid stages equals the number of stages left to complete, it's possible to build a quest
                return true;
            }
        }
        return false;
    }
}
