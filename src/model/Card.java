package model;

/**
 * Class Card - this class represents the object Card
 *
 * @author BGS Team
 */
public class Card implements java.io.Serializable {

    /**
     * Card\'s number (Also, Ace-1, Jack-11, Queen-12 and King-13).
     */
    private final int value;
    /**
     * Card\'s suit.
     */
    private final Suit suit;//
    /**
     * Serializable object version number.
     */
    private static final long serialVersionUID = 41L;

    /**
     * Constructor of Card.
     *
     * @param value
     * @param suit
     */
    public Card(int value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    /**
     * @return the value
     */
    protected int getValue() {
        return this.value;
    }

    /**
     * @return the suit
     */
    protected Suit getSuit() {
        return this.suit;
    }

    /**
     * @return Card's String representation.
     */
    @Override
    public String toString() {
        String str = "";

        switch (value) {
            case 1:
                str = "Ace";
                break;
            case 2:
                str = "Two";
                break;
            case 3:
                str = "Three";
                break;
            case 4:
                str = "Four";
                break;
            case 5:
                str = "Five";
                break;
            case 6:
                str = "Six";
                break;
            case 7:
                str = "Seven";
                break;
            case 8:
                str = "Eight";
                break;
            case 9:
                str = "Nine";
                break;
            case 10:
                str = "Ten";
                break;
            case 11:
                str = "Jack";
                break;
            case 12:
                str = "Queen";
                break;
            case 13:
                str = "King";
                break;
        }
        if (value > 1 && value < 11) {
            return value + " of " + suit.name();
        } else {
            return str + " of " + suit.name();
        }
    }
}
