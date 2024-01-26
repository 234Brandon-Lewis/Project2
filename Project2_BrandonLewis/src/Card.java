import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//Holds-Information-For-Each-Card-In-The-Deck---
public class Card extends ImageView {
    //Data Fields----------------------------------
    String face;
    Image cardImage;
    int faceValue;
    //---------------------------------------------

    //Constructor----------------------------------
    Card(String face, int value, Image view){
        this.face = face;
        this.faceValue = value;
        this.cardImage = view;
    }
    //---------------------------------------------
}
