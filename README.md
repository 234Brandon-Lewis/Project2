# Blackjack Project

A digital blackjack game coded in Java. This project uses the JavaFX library, which implements various GUI elements into Java.

The dealer and player's hands are represented using Horizontal boxes, which are containers that act as arrays but for GUI elements. In this case, the cards. 

Each card is an object that stores information about the cards face, its corresponding card image, and its value.

Cards are pulled from a deck that is represented by an ArrayList of 52 Card object types. When the deck is made, each card is created with its name and associated attributes, and then added into the deck four times (Since there are 13 total cards with 4 copies of each)

Cards are dealt from the deck by using a Random integer from 0-x (x being the number of remaining cards in the deck)
The corresponding card is then removed from the deck by its index, and then returned to the Player class when it calls hit.
Aces are calculated automatically to represent the value that leaves the player at or under 21.

The dealer plays its turn out automatically after the player stands, deciding when to hit or when to stand based on the player's hand value and whether or not the dealer is at or above 16, until it busts or stands.

Each player (The user and the dealer) is represented by the Player class which stores information about their hand and shares information about the deck.

----------------------------------
Requires JavaFX

Run BlackjackTableGUI.java to play

If unable to run, various gameplay outcomes and scenarios are demonstrated in 'BlackjackGameExamples.pdf' in Project2_BrandonLewis
----------------------------------
