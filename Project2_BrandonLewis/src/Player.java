import java.util.ArrayList;
import java.util.Random;

//Controls-Each-Player's-Hand-And-Decision-Making---
public class Player {
    //Data-Fields----------------------------
    ArrayList<Card> hand = new ArrayList<Card>(12);
    public Deck deck = new Deck();

    int value = 0;
    //---------------------------------------

    //Constructor----------------------------
    //Initiate player's hand
    Player(){
        clearHand();
    }
    //---------------------------------------

    //Methods--------------------------------

    //Hand-Value-Calculator---
    int valueOfHand(){
        value = 0;
        int numAces = 0;
        for (Card card : hand){
            value += card.faceValue;
            if (card.face.equals("A")){
                numAces += 1;
            }
            if (value > 21 && numAces >= 1){
                for (;numAces > 0; numAces--){
                    if (value > 21){
                        value -= 10;
                    }
                }
            }
        }
        return value;
    }

    //Clears-Hand---
    void clearHand(){
        if (!(hand.isEmpty())){
            hand.clear();
            this.value = 0;
        }
    }

    //AI-Stand-Decision-Maker---
    boolean stand(int otherPlayerValue){
        if (valueOfHand() > otherPlayerValue) {
            return true;
        } else if (valueOfHand() >= 16){
            Random rand = new Random();
            int fifty = rand.nextInt(2) + 1;
            return fifty == 1;

        } else return valueOfHand() >= 19;
    }

    //Hit-Functionality---
    void hit(){
        Card card = deck.dealCard();
        hand.add(card);
    }

    //Bust-Checker---
    boolean bust(){
        int value = valueOfHand();
        return value > 21;
    }
    //---------------------------------------
}
