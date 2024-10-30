import java.util.Objects;

public class Card {
    private String name;
    private Integer value;
    private CardType type;

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public enum CardType{
        Foe, Weapon, Quest, E
    }

    //Constructor for weapon cards
    public Card(CardType type, String name, int value){
        this.type = type;
        this.name = name;
        this.value = value;
    }

    //Constructor for Foe & Quest cards (they have name == type)
    public Card(CardType type, int value){
        this(type, String.valueOf(type), value);
    }

    //Constructor for cards that do not have a value (i.e. E cards)
    public Card(CardType type, String name){
        this(type, name, 0);
    }

    public CardType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;  // Check if the two references are the same
        if (o == null || getClass() != o.getClass()) return false;  // Check for null or different classes
        Card card = (Card) o;  // Cast the object to a Card
        return type == card.type && name.equals(card.name) && value.equals(card.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, value);
    }

    public String toString(){
        if (type == CardType.E || type == CardType.Weapon){
            return String.format("%s", name);
        }
        return String.format("%s%d", name.charAt(0), value);
    }
}
