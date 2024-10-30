import java.util.Comparator;

public class SortByCard implements Comparator<Card> {
    public int compare(Card a, Card b){

        int nameCompare = b.getName().compareTo(a.getName());
        int typeCompare = a.getType().compareTo(b.getType());
        int valueCompare = a.getValue().compareTo(b.getValue());

        if (typeCompare == 0){ // means a.type == b.type
            if (valueCompare == 0){
                return nameCompare;
            } else {
                return valueCompare;
            }
        } else {
            return typeCompare;
        }
    }
}