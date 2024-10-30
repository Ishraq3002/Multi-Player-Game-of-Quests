import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Deck {
    private ArrayList<Card> deck;

    public Deck(){
        deck = new ArrayList<>();
    }

    public int getSize() {
        return deck.size();
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public boolean isEmpty() {
        return deck.isEmpty();
    }

    public void addMultipleCards(int count, Card card){
        //adds multiple cards of the same kind to the deck
        for (int i=0; i<count; i++){
            addCard(card);
        }
    }

    public void shuffle (){
        Collections.shuffle(deck);
    }

    public void addCard(Card card){
        deck.add(card);
    }

    //draws the first # cards from the deck
    public Deck drawCards(int count){
        Deck drawnCards = new Deck();
        for (int i=0; i < count; i++){
            drawnCards.deck.add(drawCard());
        }
        return drawnCards;
    }

    public Card drawCard(){
        // Removes the first card of the deck
        return deck.removeFirst();
    }

    public boolean addAllCards(Deck discardDeck) {
        // Adds all the cards from discardDeck to the back of the main deck
        return deck.addAll(discardDeck.deck);
    }

    public Card peek(int index) {
        return deck.get(index);
    }

    public void removeCard(int index) {
        deck.remove(index);
    }

    public boolean checkForFoe(){
        if (isEmpty()){
            return false;
        }

        for (Card card: deck){
            if (Card.CardType.Foe == card.getType()){
                return true;
            }
        }
        return false;
    }

    public int getStageValue(){
        int stageValue = 0;
        for (Card card: deck){
            stageValue += card.getValue();
        }
        return stageValue;
    }

    public boolean checkForWeapon(Card card){
        if (isEmpty()){
            return false;
        }
        for (Card currentCard: deck) {
            if (currentCard.equals(card)){
                return true;
            }
        }
        return false;
    }

    public String toString(){
        if (isEmpty()) {
            return "No cards in the deck";
        }

        Comparator<Card> myComparator = new SortByCard();
        Collections.sort(deck, myComparator);

        StringBuilder text = new StringBuilder("[");
        for (int i = 0; i < deck.size(); i++) {
            text.append(deck.get(i));
            if (i < deck.size() - 1) {
                text.append(", ");
            }
        }

        text.append("]");
        return text.toString();
    }
}
