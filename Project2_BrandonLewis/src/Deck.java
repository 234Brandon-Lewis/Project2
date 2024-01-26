import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Random;

//Holds-A-Deck-Of-52-Card-Objects---
public class Deck {
    //Deck-of-Cards---------------------
    ArrayList<Card> cards = new ArrayList<Card>(52);
    //----------------------------------

    //Constructor-----------------------
    Deck(){
        reset();
    }
    //----------------------------------

    //Methods---------------------------

    //Reset-To-Start-The-Game-With-Fresh-Cards---
    void reset(){
        if(!(cards.isEmpty())){
            cards.clear();
        }

        Card cardTwo = new Card("2", 2, new Image("2.png"));
        Card cardThree = new Card("3", 3, new Image("3.png"));
        Card cardFour = new Card("4", 4, new Image("4.png"));
        Card cardFive = new Card("5", 5, new Image("5.png"));
        Card cardSix = new Card("6", 6, new Image("6.png"));
        Card cardSeven = new Card("7", 7, new Image("7.png"));
        Card cardEight = new Card("8", 8, new Image("8.png"));
        Card cardNine = new Card("9", 9, new Image("9.png"));
        Card cardTen = new Card("10", 10, new Image("10.png"));
        Card cardJack = new Card("J", 10, new Image("J.png"));
        Card cardQueen = new Card("Q", 10, new Image("Q.png"));
        Card cardKing = new Card("K", 10, new Image("K.png"));
        Card cardAce = new Card("A", 11, new Image("A.png"));

        for (int i = 0; i <= 3; i++){
            cards.add(cardTwo);
            cards.add(cardThree);
            cards.add(cardFour);
            cards.add(cardFive);
            cards.add(cardSix);
            cards.add(cardSeven);
            cards.add(cardEight);
            cards.add(cardNine);
            cards.add(cardTen);
            cards.add(cardJack);
            cards.add(cardQueen);
            cards.add(cardKing);
            cards.add(cardAce);
        }
    }

    //Card-Dealing-Functionality---
    Card dealCard(){
        Random rand = new Random();
        int index = rand.nextInt(cards.size());
        Card card = cards.get(index);
        cards.remove(index);
        return card;
    }
    //----------------------------------
}
