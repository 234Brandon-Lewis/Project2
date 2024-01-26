import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//Blackjack-Game-GUI---
public class BlackjackTableGUI extends Application{
    //Global-Variables------------------------
    static int HEIGHT = 130;
    Player user = new Player();
    Player dealer = new Player();
    int playerScore = 0;
    int dealerScore = 0;

    Button btnStart = new Button("Start");
    Button btnHit = new Button("Hit");
    Button btnStand = new Button("Stand");
    Label lblDealerValue = new Label("Value: 0");
    Label lblPlayerValue = new Label("Value: 0");
    Label lblPlayerScore = new Label("Player Score: 0");
    Label lblDealerScore = new Label("Dealer Score: 0");
    Label lblResult = new Label("");

    HBox hbDealerHand = new HBox();
    HBox hbPlayerHand = new HBox();
    //----------------------------------------

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage table) {
        //Controls---------------------------------------------
        Label lblDealerHand = new Label("Dealer Hand");
        Label lblPlayerHand = new Label("Player Hand");

        VBox vbScores = new VBox(lblPlayerScore, lblDealerScore);

        HBox hbDealerDisplay = new HBox(100, lblDealerHand, lblDealerValue);
        HBox hbPlayerDisplay = new HBox(100, lblPlayerHand, lblPlayerValue);
        HBox hbButtons = new HBox(10, btnStart, btnHit, btnStand);
        HBox hbResult = new HBox(400, vbScores, lblResult);

        VBox vbDisplay = new VBox(10, hbDealerDisplay, hbDealerHand, hbPlayerDisplay, hbPlayerHand, hbButtons, hbResult);
        //-----------------------------------------------------

        //Styling----------------------------------------------
        hbDealerHand.setPrefSize(600,150);
        hbPlayerHand.setPrefSize(600,150);
        hbDealerDisplay.setAlignment(Pos.CENTER);
        hbPlayerDisplay.setAlignment(Pos.CENTER);
        hbButtons.setAlignment(Pos.CENTER);

        btnHit.setDisable(true);
        btnStand.setDisable(true);

        btnStart.setPrefWidth(50);
        btnHit.setPrefWidth(50);
        btnStand.setPrefWidth(50);

        vbDisplay.setPadding(new Insets(20));
        vbDisplay.setStyle("-fx-background-color:forestgreen");
        //-----------------------------------------------------

        //Display----------------------------------------------
        Scene blackjack = new Scene(vbDisplay,750,510);
        blackjack.getStylesheets().add("BlackjackStyle.css");

        table.setTitle("Blackjack");
        table.setScene(blackjack);
        table.show();
        //-----------------------------------------------------

        //Button-Actions---------------------------------------

        //Starts-the-Game---
        btnStart.setOnAction(event -> startGame());

        //Adds-A-Card-To-The-Hand---
        btnHit.setOnAction(event -> {
            user.hit();
            updateHand(user, hbPlayerHand, lblPlayerValue);
            boolean bust = user.bust();
            if (bust){
                endGame();
            }
        });

        //Stops-Drawing-Cards-And-Moves-Action-To-Dealer---
        btnStand.setOnAction(event -> {
            boolean gameEnding = true;
            while(gameEnding){
                boolean standCheck = dealer.stand(user.valueOfHand());
                if (standCheck){
                    endGame();
                    gameEnding = false;
                } else {
                    dealer.hit();
                    updateHand(dealer, hbDealerHand, lblDealerValue);
                    boolean bust = dealer.bust();
                    if (bust){
                        endGame();
                        gameEnding = false;
                    }
                }
            }
        });
        //-----------------------------------------------------
    }
    //Methods----------------------------------------------

    //Starts-a-New-Game---
    void startGame(){
        hbDealerHand.getChildren().clear();
        hbPlayerHand.getChildren().clear();

        btnStart.setDisable(true);
        btnHit.setDisable(false);
        btnStand.setDisable(false);
        lblResult.setText("");
        lblDealerValue.setText("Value: 0");
        lblPlayerValue.setText("Value: 0");

        user = new Player();
        dealer = new Player();
        user.clearHand();
        dealer.clearHand();
        dealer.hit();
        updateHand(dealer, hbDealerHand, lblDealerValue);
    }

    //Updates-The-Hand-Of-The-Given-Player-And-Their-Hand-Value---
    void updateHand(Player p, HBox box, Label handValue){
        if (p.equals(user)) {
            this.user = p;
            this.hbPlayerHand = box;
            this.lblPlayerValue = handValue;
        }
        else if (p.equals(dealer)) {
            this.dealer = p;
            this.hbDealerHand = box;
            this.lblDealerValue = handValue;
        }

        int index = p.hand.toArray().length;
        Card card = p.hand.get(index - 1);
        Image imgCard = card.cardImage;

        ImageView ivCard = new ImageView(imgCard);
        ivCard.setFitHeight(HEIGHT);
        ivCard.setPreserveRatio(true);

        box.getChildren().add(ivCard);
        handValue.setText("Value: " + p.valueOfHand());
        }

    //Ends-The-Game-And-Displays-The-Result-Depending-On-Value-Of-The-Player-and-Dealer's-Decks---
    void endGame(){
        int playerValue = user.valueOfHand();
        int dealerValue = dealer.valueOfHand();

        String dealerWin = "The house wins.";
        String playerWin = "You win!";
        String draw = "Push. No one wins.";

        String playerBust = "Value: " + playerValue + " BUST!";
        String dealerBust = "Value: " + dealerValue + " BUST!";

        String dealerLabel = "Dealer Score: ";
        String playerLabel = "Player Score: ";

        if (playerValue > dealerValue){
            if (playerValue > 21){
                dealerScore += 1;
                lblDealerScore.setText(dealerLabel + dealerScore);
                lblResult.setText(dealerWin);
                lblPlayerValue.setText(playerBust);
            } else {
                playerScore += 1;
                lblPlayerScore.setText(playerLabel + playerScore);
                lblResult.setText(playerWin);
            }

        }
        else if (dealerValue > playerValue){
            if (dealerValue > 21) {
                playerScore += 1;
                lblPlayerScore.setText(playerLabel + playerScore);
                lblResult.setText(playerWin);
                lblDealerValue.setText(dealerBust);
            }
            else {
                dealerScore += 1;
                lblDealerScore.setText(dealerLabel + dealerScore);
                lblResult.setText(dealerWin);
            }

        }
        else {
            lblResult.setText(draw);
        }

        btnStart.setDisable(false);
        btnHit.setDisable(true);
        btnStand.setDisable(true);
    }
    //-----------------------------------------------------
}
