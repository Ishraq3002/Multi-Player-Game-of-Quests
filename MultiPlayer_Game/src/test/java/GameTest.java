import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game;
    private Deck adventureDeck;
    private Deck eventDeck;

    Player player1;
    Player player2;
    Player player3;
    Player player4;

    StringWriter output;
    PrintWriter printWriter;


    @BeforeEach
    void setUp() {
        game = new Game();
        game.setUpDecks();
        adventureDeck = game.getAdventureDeck();
        eventDeck = game.getEventDeck();

        // Assuming players are added
        addingPlayers(game);

        output = new StringWriter();
        printWriter = new PrintWriter(output);
    }

    private void addingPlayers(Game game){
        player1 = new Player("P1");
        player2 = new Player("P2");
        player3 = new Player("P3");
        player4 = new Player("P4");

        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
        game.addPlayer(player4);

    }

    @Test
    @DisplayName("Checking the total number of cards in the Adventure deck")
    void RESP_01_TEST_01(){
        //Total number of cards in Adventure Deck
        assertEquals(100, adventureDeck.getSize(), "Adventure deck should have 100 cards in total");
    }

    @Test
    @DisplayName("Checking correct number of Foe cards in Adventure deck")
    void RESP_01_TEST_02() {
        //Foe Cards
        assertEquals(8, countCards(new Card(Card.CardType.Foe, 5), adventureDeck), "There should be 8 Foe cards with value 5");
        assertEquals(7, countCards(new Card(Card.CardType.Foe, 10), adventureDeck), "There should be 7 Foe cards with value 10");
        assertEquals(8, countCards(new Card(Card.CardType.Foe, 15), adventureDeck), "There should be 8 Foe cards with value 15");
        assertEquals(7, countCards(new Card(Card.CardType.Foe, 20), adventureDeck), "There should be 7 Foe cards with value 20");
        assertEquals(7, countCards(new Card(Card.CardType.Foe, 25), adventureDeck), "There should be 7 Foe cards with value 25");
        assertEquals(4, countCards(new Card(Card.CardType.Foe, 30), adventureDeck), "There should be 4 Foe cards with value 30");
        assertEquals(4, countCards(new Card(Card.CardType.Foe, 35), adventureDeck), "There should be 4 Foe cards with value 35");
        assertEquals(2, countCards(new Card(Card.CardType.Foe, 40), adventureDeck), "There should be 2 Foe cards with value 40");
        assertEquals(2, countCards(new Card(Card.CardType.Foe, 50), adventureDeck), "There should be 2 Foe cards with value 50");
        assertEquals(1, countCards(new Card(Card.CardType.Foe, 70), adventureDeck), "There should be 1 Foe cards with value 70");
    }

    @Test
    @DisplayName("Checking correct number of Weapon cards in Adventure deck")
    void RESP_01_TEST_03() {
        //Weapon Cards
        assertEquals(6, countCards(new Card(Card.CardType.Weapon, "Dagger", 5), adventureDeck), "There should be 6 Weapon Cards: Dagger with value 5");
        assertEquals(16, countCards(new Card(Card.CardType.Weapon, "Sword", 10), adventureDeck), "There should be 16 Weapon Cards: Sword with value 10");
        assertEquals(12, countCards(new Card(Card.CardType.Weapon, "Horse", 10), adventureDeck), "There should be 12 Weapon Cards: Horse with value 10");
        assertEquals(8, countCards(new Card(Card.CardType.Weapon, "Battle-Axe", 15), adventureDeck), "There should be 8 Weapon Cards: Battle-Axe with value 15");
        assertEquals(6, countCards(new Card(Card.CardType.Weapon, "Lance", 20), adventureDeck), "There should be 6 Weapon Cards: Lance with value 20");
        assertEquals(2, countCards(new Card(Card.CardType.Weapon, "Excalibur", 30), adventureDeck), "There should be 2 Weapon Cards: Excalibur with Value 30");
    }


    @Test
    @DisplayName("Checking the total number of cards in the Event deck")
    void RESP_01_TEST_04() {
        //Total number of cards in Event Deck
        assertEquals(17, eventDeck.getSize(), "Event deck should have 17 cards in total");
    }

    @Test
    @DisplayName("Checking correct number of Quest cards in Event deck")
    void RESP_01_TEST_05(){
        //Quest Cards
        assertEquals(3, countCards(new Card(Card.CardType.Quest, 2), eventDeck), "There should be 3 Quest cards with 2 stages");
        assertEquals(4, countCards(new Card(Card.CardType.Quest, 3), eventDeck), "There should be 4 Quest cards with 3 stages");
        assertEquals(3, countCards(new Card(Card.CardType.Quest, 4), eventDeck), "There should be 3 Quest cards with 4 stages");
        assertEquals(2, countCards(new Card(Card.CardType.Quest, 5), eventDeck), "There should be 2 Quest cards with 5 stages");
    }

    @Test
    @DisplayName("Checking correct number of E cards in Event deck")
    void RESP_01_TEST_06(){
        //E Cards
        assertEquals(1, countCards(new Card(Card.CardType.E, "Plague"), eventDeck), "There should be 1 E card: Plague");
        assertEquals(2, countCards(new Card(Card.CardType.E, "Prosperity"), eventDeck), "There should be 2 E cards: Prosperity");
        assertEquals(2, countCards(new Card(Card.CardType.E, "Queen's Favor"), eventDeck), "There should be 2 E card: Queen's Favor");
    }

    @Test
    @DisplayName("Checking that the discard decks exists and are initially empty")
    void RESP_01_TEST_07(){
        assertNotNull(game.getEventDiscardDeck(), "The Event discard deck should be initialized");
        assertNotNull(game.getAdventureDiscardDeck(), "The Adventure discard deck should be initialized");
        assertTrue(game.getEventDiscardDeck().isEmpty(), "The Event discard deck should be initially empty");
        assertTrue(game.getAdventureDiscardDeck().isEmpty(), "The Adventure discard deck should be initially empty");

    }

    @Test
    @DisplayName("Checking the decks for cards that should not exist")
    void RESP_01_TEST_08(){
        //Negative Tests (checking cards that should not exist in the Adventure Deck)
        assertEquals(0, countCards(new Card(Card.CardType.Quest, 6), eventDeck), "There should be no Quest cards with 6 stages");
        assertEquals(0, countCards(new Card(Card.CardType.E, "Invalid"), eventDeck), "There should be no E cards named 'Invalid'");

        //Negative Tests (checking cards that should not exist in the Event Deck)
        assertEquals(0, countCards(new Card(Card.CardType.Foe, 60), adventureDeck), "There should be no Foe cards with value 60");
        assertEquals(0, countCards(new Card(Card.CardType.Weapon, "Invalid", 15), adventureDeck), "There should be no Weapon Cards named 'Invalid'");
    }

    private int countCards (Card card, Deck deck) {
        int count = 0;
        for (Card currentCard: deck.getDeck()){
            if (currentCard.equals(card)) {count++;}
        }
        return count;
    }

    @Test
    @DisplayName("Each player should receive 12 cards")
    void RESP_02_TEST_01() {
        // Checking if each player received 12 cards
        game.distributeAdventureCardsToPlayers(12);
        assertEquals(12, player1.getHandSize(), "Each player should receive 12 cards");
        assertEquals(12, player2.getHandSize(), "Each player should receive 12 cards");
        assertEquals(12, player3.getHandSize(), "Each player should receive 12 cards");
        assertEquals(12, player4.getHandSize(), "Each player should receive 12 cards");
    }

    @Test
    @DisplayName("Adventure deck size should decrease correctly after distributing cards")
    void RESP_02_TEST_02() {
        // Checking if the adventure deck is reduced correctly
        game.distributeAdventureCardsToPlayers(12);
        assertEquals(52, adventureDeck.getSize(), "Adventure deck should have 52 cards left after distributing to 4 players"); // 100 - (12 * 4)
    }

    @Test
    @DisplayName("Deck replenishment should handle edge case when adventure deck runs out")
    void RESP_02_TEST_03() {
        //Adding a random card to the adventure discard deck so it can be used
        game.getAdventureDiscardDeck().addCard(new Card(Card.CardType.E, "Extra Card"));

        //Drawing 100 cards from the adventure deck and then needing an additional card from the discard deck
        Deck deck = game.drawAdventureCards(101, player1);

        assertEquals(0, game.getAdventureDiscardDeck().getSize(), "Adventure discard deck should be 0 as it has transferred to the main deck");
        assertEquals(101, deck.getSize(), "Drawn cards should equal 101"); // 100 - (12 * 4)
        assertEquals(0, adventureDeck.getSize(), "Adventure deck should be 0 as all the cards have been drawn out");
    }

    @Test
    @DisplayName("Checking if game correctly determines if no player has 7 shields")
    void RESP_03_TEST_01(){
        player1.updateShields(3); //adding 3 shields to player 1's count
        player3.updateShields(6); //adding 6 shields to player 3's count

        ArrayList<Player> winners = game.checkForWinners();  //game is checking for winners

        assertEquals(3, player1.getShields(), "Player 1 should have 3 shields ");
        assertEquals(0, player2.getShields(), "Player 2 should have a default of 0 shields");
        assertEquals(6, player3.getShields(), "Player 3 should have 6 shields");
        assertTrue(winners.isEmpty(), "There should be no winners");
    }

    @Test
    @DisplayName("Checking if game correctly determines if one player has exactly 7 shields")
    void RESP_03_TEST_02(){
        player1.updateShields(3); //adding 3 shields to player 1's count
        player1.updateShields(4); //adding 4 shields to player 1's count - updating player 1's count to 7
        player2.updateShields(5); //adding 5 shields to player 2's count
        player3.updateShields(6); //adding 6 shields to player 3's count
        player4.updateShields(3); //adding 3 shields to player 4's count

        ArrayList<Player> winners = game.checkForWinners();  //game is checking for winners

        assertEquals(7, player1.getShields(), "Player 1 should have 7 shields in total");
        assertEquals(5, player2.getShields(), "Player 2 should have 5 shields");
        assertEquals(6, player3.getShields(), "Player 3 should have 6 shields");
        assertEquals(3, player4.getShields(), "Player 3 should have 3 shields");
        assertEquals(1, winners.size(), "There should be 1 winner");
        assertTrue(winners.contains(player1), "Player 1 should be the winner");
    }

    @Test
    @DisplayName("Checking if game correctly determines if two players have 7 or more shields")
    void RESP_03_TEST_03(){
        player1.updateShields(3); //adding 3 shields to player 1's count
        player1.updateShields(4); //adding 4 shields to player 1's count - updating player 1's count
        player2.updateShields(5); //adding 5 shields to player 2's count
        player3.updateShields(8); //adding 8 shields to player 3's count
        player4.updateShields(3); //adding 3 shields to player 4's count

        ArrayList<Player> winners = game.checkForWinners();  //game is checking for winners

        assertEquals(7, player1.getShields(), "Player 1 should have 7 shields ");
        assertEquals(5, player2.getShields(), "Player 2 should have 5 shields");
        assertEquals(8, player3.getShields(), "Player 3 should have 8 shields");
        assertEquals(3, player4.getShields(), "Player 3 should have 3 shields");
        assertEquals(2, winners.size(), "There should be 2 winners");
        assertTrue(winners.containsAll(Arrays.asList(player1, player3)), "Player 1 and Player 3 should be the winners");
    }

    @Test
    @DisplayName("Checking that the game displays the winners")
    void RESP_04_TEST_01() {
        player1.updateShields(7); // Set shields high enough to trigger a win
        player2.updateShields(8);

        game.checkForWinners(); // There will be 2 winners: Player 1 & Player 2

        game.displayWinners(printWriter);  // Displays the winners' ids
        printWriter.flush();  // Ensure all data is written to the StringWriter

        String results = output.toString();
        assertTrue(results.contains(player1.getID()), "Player 1's ID should be displayed as a winner");
        assertTrue(results.contains(player2.getID()), "Player 2's ID should be displayed as a winner");
    }

    @Test
    @DisplayName("Checking that the game terminates after displaying winners")
    void RESP_04_TEST_02() {
        player1.updateShields(7); // Set shields high enough to trigger a win

        game.checkForWinners(); // There will be 1 winner, Player 1

        game.displayWinners(printWriter);
        printWriter.flush();  // Ensure all data is written to the StringWriter

        assertFalse(game.isGameRunning(), "Game is supposed to terminate after displaying the winners' IDs");
    }

    @Test
    @DisplayName("Check behavior when no players meet the winning condition")
    void RESP_04_TEST_03() {
        player1.updateShields(3); // Below winning threshold
        player2.updateShields(4); // Below winning threshold

        game.checkForWinners();  //There will be no winners

        game.displayWinners(printWriter);
        printWriter.flush();  // Ensure all data is written to the StringWriter

        assertTrue(game.isGameRunning(), "Game should continue with no winners.");

        String results = output.toString();
        assertTrue(results.isEmpty(), "Nothing should be displayed to the user as there are no winners");
    }

    @Test
    @DisplayName("Checking if game ‘draws’ (i.e., displays) the Event card")
    void RESP_05_TEST_01(){
        //Verifies that the drawn card is displayed
        for (Player player: game.getPlayers()){

            // Draw an event card for the current player
            Card drawnCard = game.drawEventCard(player, printWriter);

            // Flush the writer to ensure all content is written
            printWriter.flush();

            String expectedOutput = player + " has drawn " + drawnCard;
            assertTrue(output.toString().contains(expectedOutput),  "Output is expected to contain: " + expectedOutput);
        }
    }

    @Test
    @DisplayName("Ensure player's hand is correctly trimmed")
    void RESP_06_TEST_01() {
        // Setup a new game and clear the existing adventure deck
        adventureDeck.getDeck().clear();

        // Populate the adventure deck with 14 Foe cards with a value of 10
        adventureDeck.addMultipleCards(14, new Card(Card.CardType.Foe, 10));

        // Distribute 14 cards to Player 1; this should deplete the adventure deck
        game.drawAdventureCards(14, player1);

        // Trigger hand trimming to Player 1 (the maximum allowed size is 12 cards)
        game.trimPlayerHand(player1, new Scanner("1\n1"), printWriter);

        // Expected outputs after hand trimming
        String prompt1 = "Enter a card position (1 to 14) to remove:"; // Prompted twice to remove two cards
        String prompt2 = "Enter a card position (1 to 13) to remove:";
        String displayHand = "Player P1's hand: [F10, F10, F10, F10, F10, F10, F10, F10, F10, F10, F10, F10, F10, F10]";
        String trimmedHand = "Player P1's updated hand: [F10, F10, F10, F10, F10, F10, F10, F10, F10, F10, F10, F10]";

        // Capture the output to assert against
        String results = output.toString();
        assertTrue(results.contains("Player P1's hand is too large. It needs to be trimmed to a maximum of 12 cards:"));
        assertTrue(results.contains(displayHand), "Game did not display Player 1's hand correctly");
        assertTrue(results.contains(prompt1), "Game did not prompt Player 1 for the first card removal position");
        assertTrue(results.contains(prompt2), "Game did not prompt Player 1 for the second card removal position");
        assertTrue(results.contains(trimmedHand), "Game did not display Player 1's trimmed hand correctly");

        // Assert the conditions of discard and remaining decks
        assertEquals(2, game.getAdventureDiscardDeck().getSize(), "Adventure discard deck should have 2 cards as 2 adventure cards have been discarded by Player 1");
        assertTrue(adventureDeck.isEmpty(), "Adventure deck should be empty as it has all been used");
    }

    @Test
    @DisplayName("Checking if the game processes the drawing of a 'Plague' event card correctly")
    void RESP_07_TEST_01() {
        // Setup: Create a new game and add an event card 'Plague' to the event deck
        Game game = new Game();
        game.getEventDeck().addMultipleCards(1, new Card(Card.CardType.E, "Plague"));

        // Adding the 4 players to the new game
        addingPlayers(game);

        // Give Player 1 a shield
        player1.updateShields(1);

        // Draw and process the 'Plague' card for Player 1
        Card drawnCard = game.drawEventCard(player1, printWriter);
        game.processCardActions(drawnCard, player1, null, printWriter);

        // Assertions
        assertEquals(0, player1.getShields(), "Player 1 should have no shields after drawing a Plague card");
        assertEquals(drawnCard, game.getEventDiscardDeck().getDeck().getLast(), "The discard deck should contain the drawn Plague card");

        // Verify that the other players' shields remain unchanged
        assertEquals(0, player2.getShields(), "Player 2 should have 0 shields");
        assertEquals(0, player3.getShields(), "Player 3 should have 0 shields");
        assertEquals(0, player4.getShields(), "Player 4 should have 0 shields");

        // Update player 1's shield count to 3 and re-draw the Plague card
        player1.updateShields(3);
        Card reDrawnCard = game.drawEventCard(player1, printWriter);
        game.processCardActions(reDrawnCard, player1, null, printWriter);

        assertEquals(1, player1.getShields(), "Player 1 should have 1 shield left after re-drawing a Plague card");
        assertEquals(reDrawnCard, game.getEventDiscardDeck().getDeck().getLast(), "The discard deck should contain the re-drawn Plague card");
    }

    @Test
    @DisplayName("Checking if the game processes the drawing of a 'Queen's Favor' event card correctly")
    void RESP_07_TEST_02() {
        // Setup: Create a new game and add two 'Queen's Favor' event cards to the event deck
        Game game = new Game();
        game.getEventDeck().addMultipleCards(2, new Card(Card.CardType.E, "Queen's Favor"));

        // Set up the adventure deck with 100 cards
        game.setUpAdventureDeck();

        // Adding 4 players to the game
        addingPlayers(game);

        // Distribute 10 adventure cards to each player
        game.distributeAdventureCardsToPlayers(10);

        // Draw and process a 'Queen's Favor' card for player 1
        Card drawnCard = game.drawEventCard(player1, printWriter);
        game.processCardActions(drawnCard, player1, null, printWriter);

        // Assertions to verify initial draw effects
        assertEquals(12, player1.getHandSize(), "Player 1's hand should have 12 cards after drawing the Queen's Favor card");
        assertEquals(10, player2.getHandSize(), "Player 2's hand should remain unchanged");
        assertEquals(10, player3.getHandSize(), "Player 3's hand should remain unchanged");
        assertEquals(10, player4.getHandSize(), "Player 4's hand should remain unchanged");
        assertEquals(drawnCard, game.getEventDiscardDeck().getDeck().getLast(), "The discard deck should contain the drawn Queen's Favor card");
        assertEquals(58, game.getAdventureDeck().getSize(), "Adventure deck should have 58 cards after the draw");

        // Re-draw a 'Queen's Favor' card for player 1 and simulate trimming
        Card reDrawnCard = game.drawEventCard(player1, printWriter);
        game.processCardActions(reDrawnCard, player1, new Scanner("1\n1"), printWriter); // Simulate player discarding the first card twice

        // Assertions to verify re-draw effects and trimming
        assertEquals(12, player1.getHandSize(), "Player 1's hand should revert to 12 cards after re-drawing and trimming");
        assertEquals(10, player2.getHandSize(), "Player 2's hand should remain unchanged");
        assertEquals(10, player3.getHandSize(), "Player 3's hand should remain unchanged");
        assertEquals(10, player4.getHandSize(), "Player 4's hand should remain unchanged");

        assertEquals(reDrawnCard, game.getEventDiscardDeck().getDeck().getLast(), "The Event discard deck should contain the re-drawn Queen's Favor card");
        assertEquals(56, game.getAdventureDeck().getSize(), "Adventure deck should have 56 cards after the second draw");
        assertEquals(2, game.getAdventureDiscardDeck().getSize(), "Adventure discard deck should have 2 cards from Player 1's discarded cards");
    }

    @Test
    @DisplayName("Checking if the game processes the drawing of an E card: Prosperity")
    void RESP_07_TEST_03() {
        // Setting up a new Game with an Event Deck containing one Event card: Prosperity
        Game game = new Game();
        game.getEventDeck().addMultipleCards(1, new Card(Card.CardType.E, "Prosperity"));

        // Setting up the adventure deck with a standard 100 cards
        game.setUpAdventureDeck();

        // Adding 4 players to the game
        addingPlayers(game);

        // Distributing 10 cards to each player initially, leaving 60 cards in the adventure deck (100 - (10 * 4))
        game.distributeAdventureCardsToPlayers(10);

        // Drawing the Prosperity card and processing its action
        Card drawnCard = game.drawEventCard(player1, printWriter);
        game.processCardActions(drawnCard, player1, null, printWriter);

        // Verifying that each player’s hand has increased to 12 cards after the Prosperity card is drawn
        assertEquals(12, player1.getHandSize(), "Player 1's hand should now have 12 adventure cards after drawing the Prosperity card.");
        assertEquals(12, player2.getHandSize(), "Player 2's hand should now have 12 adventure cards after drawing the Prosperity card.");
        assertEquals(12, player3.getHandSize(), "Player 3's hand should now have 12 adventure cards after drawing the Prosperity card.");
        assertEquals(12, player4.getHandSize(), "Player 4's hand should now have 12 adventure cards after drawing the Prosperity card.");

        // Verifying the Event discard deck contains the drawn Prosperity card and the adventure deck has 52 cards remaining
        assertEquals(drawnCard, game.getEventDiscardDeck().getDeck().getLast(), "The Event discard deck should contain the drawn Prosperity card.");
        assertEquals(52, game.getAdventureDeck().getSize(), "The adventure deck should have 52 cards remaining (60 - 8).");
        assertEquals(0, game.getAdventureDiscardDeck().getSize(), "The adventure discard deck should have 0 cards since none have been discarded.");

        // Re-drawing another Prosperity card to test hand trimming (since Player 1 now has 14 cards)
        Card reDrawnCard = game.drawEventCard(player1, printWriter);
        game.processCardActions(reDrawnCard, player1, new Scanner("1\n1\n1\n1\n1\n1\n1\n1"), printWriter); // Simulating trimming of cards from Player 1's hand

        // Verifying that each player's hand is trimmed back to 12 cards
        assertEquals(12, player1.getHandSize(), "Player 1's hand should have 12 adventure cards after redrawing the Prosperity card and trimming.");
        assertEquals(12, player2.getHandSize(), "Player 2's hand should have 12 adventure cards after redrawing the Prosperity card and trimming.");
        assertEquals(12, player3.getHandSize(), "Player 3's hand should have 12 adventure cards after redrawing the Prosperity card and trimming.");
        assertEquals(12, player4.getHandSize(), "Player 4's hand should have 12 adventure cards after redrawing the Prosperity card and trimming.");

        // Verifying the Event discard deck and updated adventure deck sizes after the second draw
        assertEquals(reDrawnCard, game.getEventDiscardDeck().getDeck().getLast(), "The Event discard deck should contain the re-drawn Prosperity card.");
        assertEquals(44, game.getAdventureDeck().getSize(), "The adventure deck should now have 44 cards remaining (52 - 8).");
        assertEquals(8, game.getAdventureDiscardDeck().getSize(), "The adventure discard deck should have 8 cards (2 discarded by each of the 4 players).");
    }

    @Test
    @DisplayName("Checking if the game displays the player's hand and prompts for a card position")
    void RESP_08_TEST_01() {
        // Setting up a new Game with an Event Deck containing one Quest card with 2 stages
        Game game = new Game();
        game.getEventDeck().addMultipleCards(1, new Card(Card.CardType.Quest, 2));

        // Adding 14 F10 cards (Foe cards with a strength of 10) to the Adventure Deck
        game.getAdventureDeck().addMultipleCards(14, new Card(Card.CardType.Foe, 10));

        // Drawing 12 cards into Player 1's hand, all being F10 cards
        game.drawAdventureCards(12, player1);

        // Defining the prompt message to be shown to the sponsor (Player 1)
        String prompt = "Enter a card position (1 to " + player1.getHandSize() + ") to add to stage, or type 'quit' to finish stage setup:";

        // Simulating user input as '1' (selecting the first card in the hand)
        Scanner scanner = new Scanner("1");

        // Capturing the response from the displayHandAndPrompt method (assuming Player 1 is the sponsor)
        String response = game.displayHandAndPrompt(player1, prompt, scanner, printWriter);

        // Converting the captured output to a string for verification
        String results = output.toString();

        // Verifying the player's hand is displayed correctly
        assertTrue(results.contains("[F10, F10, F10, F10, F10, F10, F10, F10, F10, F10, F10, F10]"), "Player 1's hand should be displayed with all F10 cards.");

        // Verifying that the prompt message is displayed correctly
        assertTrue(results.contains(prompt), "The prompt message should ask the player for a valid position or to 'quit'.");

        // Verifying that the input '1' is correctly returned as the selected card position
        assertEquals("1", response, "The response should correctly return the input '1'.");
    }

    @Test
    @DisplayName("Checking if game processes a yes or no response from player")
    // If player is asked to sponsor a quest
    void RESP_09_TEST_01() {
        // Test a valid 'YES' response
        boolean response = game.yesOrNoResponse(new Scanner("YES"), printWriter);
        assertTrue(response, "The response should be true for input 'YES'.");

        // Test a valid 'No' response
        response = game.yesOrNoResponse(new Scanner("No"), printWriter);
        assertFalse(response, "The response should be false for input 'No'.");

        // Test an invalid input followed by a valid response 'yes'
        game.yesOrNoResponse(new Scanner("invalid\nyes"), printWriter);

        // Verify that the game outputs an error message for invalid input
        assertTrue(output.toString().contains("Invalid response. Please enter 'Yes' or 'No'."), "The output should contain an error message after receiving invalid input.");
    }

    @Test
    @DisplayName("Game prompts each player if they want to sponsor a quest: All players say no")
    void RESP_10_TEST_01() {
        // Setting up a new Game with an Event Deck containing one Quest card with 2 stages
        Game game = new Game();
        game.getEventDeck().addMultipleCards(1, new Card(Card.CardType.Quest, 2));

        // Adding 4 players to the game
        addingPlayers(game);

        // Simulating input where all players say "No" to sponsoring the quest
        String input = "No\nno\nno\nno";
        Scanner scanner = new Scanner(input);

        // Drawing the Quest card for player 2 and prompting players to sponsor the quest
        Card drawnCard = game.drawEventCard(player2, printWriter);
        game.findASponsor(drawnCard, player2, scanner, printWriter); // Game asks each player if they want to sponsor

        // Verifying that each player is prompted to sponsor the quest
        String results = output.toString();
        for (Player player : game.getPlayers()) {
            String expectedPrompt = player + ": Do you want to sponsor a quest with 2 stages? (Yes/No)";
            assertTrue(results.contains(expectedPrompt), "The prompt should ask " + player + " to sponsor the quest.");
        }

        // Ensuring all data is written to the StringWriter
        printWriter.flush();

        // Verifying that the Quest card was discarded since no player chose to sponsor it
        assertEquals(1, game.getEventDiscardDeck().getSize(), "Event discard deck should contain 1 card after no players chose to sponsor.");
        assertEquals(drawnCard, game.getEventDiscardDeck().getDeck().getLast(), "Event discard deck should contain the drawn Quest card.");
    }

    @Test
    @DisplayName("Game prompts each player if they want to sponsor a quest: One player says yes")
    void RESP_10_TEST_02() {
        // Setting up a new Game with an Event Deck containing one Quest card with 2 stages
        Game game = new Game();
        game.getEventDeck().addMultipleCards(1, new Card(Card.CardType.Quest, 2));

        // Adding 4 players to the game and shuffling the Adventure Deck
        addingPlayers(game);
        game.setUpAdventureDeck();
        game.getAdventureDeck().shuffle();

        // Player 1 has a set hand of 12 cards, others have empty hands
        game.drawAdventureCards(12, player1);

        // Input simulating the players' responses: "No" for Player 2 & 3, "Yes" for Player 4 (but denied), "Yes" for Player 1 (accepted)
        String input = "No\nno\nyes\nyes";
        Scanner scanner = new Scanner(input);

        // Player 2 draws the event card
        Card drawnCard = game.drawEventCard(player2, printWriter);
        game.findASponsor(drawnCard, player2, scanner, printWriter);

        String results = output.toString();

        // Verify that each player was prompted and responded appropriately
        assertTrue(results.contains(player2 + ": Do you want to sponsor a quest with 2 stages? (Yes/No)"), "Player 2 should be prompted to sponsor the quest.");
        assertTrue(results.contains(player3 + ": Do you want to sponsor a quest with 2 stages? (Yes/No)"), "Player 3 should be prompted to sponsor after Player 2 declines.");
        assertTrue(results.contains(player4 + ": Do you want to sponsor a quest with 2 stages? (Yes/No)"), "Player 4 should be prompted to sponsor after Player 3 declines.");

        // Verify that Player 4 was denied due to an insufficient hand
        assertTrue(results.contains(player4 + " cannot sponsor a valid quest. Hand is not sufficient enough"), "Player 4 should be denied sponsorship due to an empty hand.");

        // Verify that Player 1 was prompted and accepted
        assertTrue(results.contains(player1 + ": Do you want to sponsor a quest with 2 stages? (Yes/No)"), "Player 1 should be prompted after Player 4 is denied.");
        assertTrue(results.contains(player1 + " would like to sponsor a quest with " + drawnCard.getValue() + " stages"), "Player 1 should be approved to sponsor the quest.");

        // Verify that the quest card is being sponsored, not discarded
        assertTrue(game.getEventDiscardDeck().isEmpty(), "The Event discard deck should be empty as the Quest card is being sponsored.");
    }


    @Test
    @DisplayName("Game prompts each player if they want to sponsor a quest: invalid user input")
    void RESP_10_TEST_03() {
        // Setting up a new Game with an Event Deck containing one Quest card with 2 stages
        Game game = new Game();
        game.getEventDeck().addMultipleCards(1, new Card(Card.CardType.Quest, 2));

        // Adding 4 players to the game and shuffling the Adventure Deck
        addingPlayers(game);
        game.setUpAdventureDeck();
        game.getAdventureDeck().shuffle();

        // Player 3 has a set hand of 12 cards, others have empty hands
        game.drawAdventureCards(12, player3);

        // Input simulating invalid input, followed by valid responses: "randomInput" for Player 2, "no" for Player 2, "yes" for Player 3
        String input = "randomInput\nno\nYes";
        Scanner scanner = new Scanner(input);

        // Player 2 draws the event card
        Card drawnCard = game.drawEventCard(player2, printWriter);
        game.findASponsor(drawnCard, player2, scanner, printWriter);

        String results = output.toString();

        // Verify that Player 2 was prompted to sponsor
        assertTrue(results.contains(player2 + ": Do you want to sponsor a quest with 2 stages? (Yes/No)"), "Player 2 should be prompted to sponsor the quest.");

        // Verify that the invalid input was handled correctly
        assertTrue(results.contains("Invalid response. Please enter 'Yes' or 'No'."), "The game should prompt the user to enter a valid response after 'randomInput'.");

        // Verify that Player 3 was prompted after Player 2 declines
        assertTrue(results.contains(player3 + ": Do you want to sponsor a quest with 2 stages? (Yes/No)"), "Player 3 should be prompted after Player 2 declines.");

        // Verify that Player 3 accepted to sponsor the quest
        assertTrue(results.contains(player3 + " would like to sponsor a quest with 2 stages" + "\n"), "Player 3 should have accepted to sponsor the quest.");

        // Verify that the quest card is being sponsored, not discarded
        assertTrue(game.getEventDiscardDeck().isEmpty(), "The Event discard deck should be empty as the quest card is being sponsored.");
        assertTrue(game.getEventDeck().isEmpty(), "The Event deck should be empty since the Quest card is in play.");

        // Verify the size of the Adventure Deck
        assertEquals(88, game.getAdventureDeck().getSize(), "The Adventure deck should have 88 cards after distributing 12 cards to Player 3.");
    }

    @Test
    @DisplayName("Selected card for stage is another Foe card")
    void RESP_11_TEST_01() {
        // Creating a stage with one Foe card already in it
        Deck stage = new Deck();
        Card foeCard = new Card(Card.CardType.Foe, 5);
        stage.addCard(foeCard);

        // Attempting to add another Foe card to the stage, checking for validity
        boolean validCard = game.isValidCardForStage(stage, foeCard, printWriter);

        // Flushing the output to capture any error messages
        printWriter.flush();
        String results = output.toString();

        // Expected error message
        String error = "Invalid: Only one Foe card allowed per stage.";

        // Verifying that the error message is displayed and the card is deemed invalid
        assertTrue(results.contains(error), "The error message should be displayed when trying to add a second Foe card to the stage.");
        assertFalse(validCard, "The method should return false as the selected card was invalid for the stage.");
    }

    @Test
    @DisplayName("Selected card for stage is another Weapon card of the same name")
    void RESP_11_TEST_02() {
        // Creating a stage with a Foe card and a Weapon card (Horse) already in it
        Deck stage = new Deck();
        Card weaponCard = new Card(Card.CardType.Weapon, "Horse", 10);
        Card foeCard = new Card(Card.CardType.Foe, 5);

        stage.addCard(foeCard);
        stage.addCard(weaponCard);

        // Attempting to add another Horse card to the stage
        boolean validCard = game.isValidCardForStage(stage, weaponCard, printWriter);

        // Flushing the output to capture any error messages
        printWriter.flush();
        String results = output.toString();

        // Expected error message when trying to add a duplicate Horse card
        String error = "Invalid: Only one Horse card allowed per stage.";

        // Verifying that the error message is displayed and the card is deemed invalid
        assertTrue(results.contains(error), "The error message should be displayed when trying to add a duplicate Horse card to the stage.");
        assertFalse(validCard, "The method should return false as the selected card was invalid for the stage.");
    }

    @Test
    @DisplayName("Selected card for stage is valid")
    void RESP_11_TEST_03() {
        // Creating a stage with a Horse card already in it
        Deck stage = new Deck();
        Card weaponCard = new Card(Card.CardType.Weapon, "Horse", 10);
        stage.addCard(weaponCard);  // Stage contains a Horse card

        // Attempting to add a Sword card to the stage
        boolean validCard = game.isValidCardForStage(stage, new Card(Card.CardType.Weapon, "Sword", 10), printWriter);

        // Flushing the output to capture any messages
        printWriter.flush();
        String results = output.toString();

        // Verifying the Sword card is valid for the stage and no error messages are printed
        assertTrue(validCard, "Should return true as adding a Sword card to the stage is valid.");
        assertTrue(results.isEmpty(), "No error messages should be printed for valid card additions.");

        // Attempting to add a Foe card to the stage
        Card foeCard = new Card(Card.CardType.Foe, 5);
        validCard = game.isValidCardForStage(stage, foeCard, printWriter);

        // Verifying the Foe card is valid for the stage
        assertTrue(validCard, "Should return true as adding a Foe card to the stage is valid.");
    }

    @Test
    @DisplayName("Stage validation error messages")
    void RESP_12_TEST_01() {
        // Test case 1: Empty stage
        String response = game.formatStageError(new Deck(), 0, 0);
        assertTrue(response.contains("A stage cannot be empty"),
                "Player should receive an error message when trying to 'quit' with an empty stage.");

        // Test case 2: Stage with insufficient value
        Deck stage = new Deck();
        stage.addCard(new Card(Card.CardType.Foe, 10)); // Adding a Foe card with value 10

        response = game.formatStageError(stage, 10, 10);
        assertTrue(response.contains("Insufficient value for this stage. Current stage value: 10, required to exceed: 10"),
                "Player should receive an error message for a stage with insufficient value when trying to 'quit'.");

        // Test case 3: Stage without a Foe card
        stage = new Deck();
        stage.addCard(new Card(Card.CardType.Weapon, "Horse", 10)); // Adding a Weapon card

        response = game.formatStageError(stage, 10, 10);
        assertTrue(response.contains("Stage needs one Foe Card"),
                "Player should receive an error message when trying to 'quit' for a stage with no Foe card.");
    }

    @Test
    @DisplayName("Valid stage set-up")
    void RESP_13_TEST_01() {
        // Setting up the player's hand to include [F20, Sword, Horse]
        Deck stage = new Deck();
        game.getAdventureDeck().getDeck().clear(); // Clearing the adventure deck

        // Adding cards [F20, Sword, Horse] to the adventure deck
        game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 20));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Sword", 10));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Horse", 10));

        // Player 1 draws 3 cards from the adventure deck: [F20, Sword, Horse]
        game.drawAdventureCards(3, player1);

        // Player 1 is the sponsor of this quest and is setting up a stage
        int stageValue = game.setUpStage(player1, stage, new Scanner("1\n1\nquit"), printWriter, 0);
        String results = output.toString();

        // Verifying the stage setup process
        assertTrue(results.contains("Enter a card position (1 to 3) to add to stage, or type 'quit' to finish stage setup:"),
                "Player 1 should be prompted to enter a valid card position.");

        assertTrue(results.contains("Added F20 to the current stage: [F20]"),
                "F20 is a valid card and should be added to the current stage.");

        assertTrue(results.contains("Player P1's hand: [Sword, Horse]"),
                "Player 1's hand should be updated to reflect the removal of F20.");

        assertTrue(results.contains("Enter a card position (1 to 2) to add to stage, or type 'quit' to finish stage setup:"),
                "Player 1 should be prompted again to enter a valid card position.");

        assertTrue(results.contains("Added Sword to the current stage: [F20, Sword]"),
                "Sword is a valid card and should be added to the current stage.");

        assertTrue(results.contains("Enter a card position (1 to 1) to add to stage, or type 'quit' to finish stage setup:"),
                "Player 1 should be prompted again to enter a valid card position.");

        assertTrue(results.contains("Player P1's hand: [Horse]"),
                "Player 1's hand should be updated to reflect the removal of Sword.");

        assertTrue(results.contains("Stage setup complete. Value: 30. Stage: [F20, Sword]"),
                "The stage setup should be complete with a total value of 30.");

        // Final assertions
        assertEquals(30, stageValue, "The value of the stage should be 30.");
        assertEquals(1, player1.getHandSize(), "Player 1 should have one card left in their hand after setting up the stage.");
    }

    @Test
    @DisplayName("Valid stage set-up: Entering 'quit' for an empty stage + Selecting another Foe Card")
    void RESP_13_TEST_02() {
        // Setting up the player's hand to include [F20, F10, Horse]
        Deck stage = new Deck();
        game.getAdventureDeck().getDeck().clear(); // Clearing the adventure deck

        // Adding [F20, F10, Horse] to the adventure deck
        game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 20));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 10));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Horse", 10));

        // Player 1 draws 3 cards from the adventure deck: [F10, F20, Horse]
        game.drawAdventureCards(3, player1);

        // Player 1 is the sponsor of the quest and is setting up a stage
        int stageValue = game.setUpStage(player1, stage, new Scanner("quit\n1\n1\nquit"), printWriter, 0);
        String results = output.toString();

        // Verifying the sequence of prompts and actions during stage setup
        assertTrue(results.contains("Enter a card position (1 to 3) to add to stage, or type 'quit' to finish stage setup:"),
                "Player 1 should be prompted to enter a valid card position.");

        assertTrue(results.contains("A stage cannot be empty"),
                "Player 1 should receive an error message after entering 'quit' for an empty stage.");

        assertTrue(results.contains("Player P1's hand: [F10, F20, Horse]"),
                "Player 1's hand should be displayed after the initial quit attempt.");

        assertTrue(results.contains("Enter a card position (1 to 2) to add to stage, or type 'quit' to finish stage setup:"),
                "Player 1 should be prompted again to enter a valid card position.");

        assertTrue(results.contains("Added F10 to the current stage: [F10]"),
                "F10 is a valid card and should be added to the current stage.");

        assertTrue(results.contains("Player P1's hand: [F20, Horse]"),
                "Player 1's hand should be updated after adding F10 to the stage.");

        assertTrue(results.contains("Invalid: Only one Foe card allowed per stage"),
                "Player 1 should receive an error message after attempting to add another Foe card to the stage.");

        assertTrue(results.contains("Stage setup complete. Value: 10. Stage: [F10]"),
                "The stage setup should be complete with a value of 10, and only F10 should be included in the stage.");

        // Final assertions
        assertEquals(10, stageValue, "The value of this stage should be 10.");
        assertEquals(2, player1.getHandSize(), "Player 1 should have two cards left in their hand after setting up the stage.");
    }

    @Test
    @DisplayName("Valid stage set-up: insufficient stage value + Selecting another Weapon Card")
    void RESP_13_TEST_03() {
        // Setting up the player's hand to include [F10, Dagger, Sword, Sword]
        Deck stage = new Deck();
        game.getAdventureDeck().getDeck().clear(); // Clearing the adventure deck

        // Adding cards [F10, Dagger, Sword, Sword] to the adventure deck
        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Sword", 10));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Sword", 10));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 10));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Dagger", 5));

        // Player 1 draws 4 cards from the adventure deck: [F10, Dagger, Sword, Sword]
        game.drawAdventureCards(4, player1);

        // Player 1 is setting up a stage with a value target of 20
        int stageValue = game.setUpStage(player1, stage, new Scanner("five\n5\n3\n3\nquit\n1\nquit\n1\nquit"), printWriter, 20);
        String results = output.toString();

        // Verifying each step of the stage setup process

        // input = "five" -> Invalid input
        assertTrue(results.contains("Invalid input. Please enter a valid card position."),
                "Player 1 should receive an error message for entering invalid input 'five'.");

        // input = 5 -> Invalid input: out of bounds
        assertTrue(results.contains("Invalid input. Please enter a valid card position."),
                "Player 1 should receive an error message for selecting an out-of-bounds card index '5'.");

        // input = 3 -> Valid input, adding Sword to the stage
        assertTrue(results.contains("Added Sword to the current stage: [Sword]"),
                "Sword is a valid card and should be added to the stage.");
        assertTrue(results.contains("Player P1's hand: [F10, Dagger, Sword]"),
                "Player 1's hand should be updated to reflect the removal of Sword.");

        // input = 3 -> Attempting to add another Sword, which is invalid
        assertTrue(results.contains("Invalid: Only one Sword card allowed per stage."),
                "Player 1 should receive an error message after attempting to add another Sword card to the stage.");

        // input = quit -> Attempting to quit with no Foe card in the stage
        assertTrue(results.contains("Stage needs one Foe Card"),
                "Player 1 should receive an error message for trying to quit with no Foe card in the stage.");

        // input = 1 -> Adding F10 (Foe card) to the stage
        assertTrue(results.contains("Added F10 to the current stage: [F10, Sword]"),
                "F10 is a valid card and should be added to the stage.");
        assertTrue(results.contains("Player P1's hand: [Dagger, Sword]"),
                "Player 1's hand should be updated to reflect the removal of F10.");

        // input = quit -> Insufficient stage value
        assertTrue(results.contains("Insufficient value for this stage. Current stage value: 20, required to exceed: 20"),
                "Player 1 should receive an error message for quitting with insufficient stage value.");

        // input = 1 -> Adding Dagger to the stage
        assertTrue(results.contains("Added Dagger to the current stage: [F10, Dagger, Sword]"),
                "Dagger is a valid card and should be added to the stage.");
        assertTrue(results.contains("Player P1's hand: [Sword]"),
                "Player 1's hand should be updated to reflect the removal of Dagger.");

        // Final quit -> Stage setup complete
        assertTrue(results.contains("Stage setup complete. Value: 25. Stage: [F10, Dagger, Sword]"),
                "The stage setup should be complete with a value of 25 and contain [F10, Dagger, Sword].");

        // Final assertions
        assertEquals(25, stageValue, "The value of the stage should be 25.");
        assertEquals(1, player1.getHandSize(), "Player 1 should have 1 card left in their hand after the stage setup.");
    }

    @Test
    @DisplayName("Asking players if they want to participate in the quest")
    void RESP_14_TEST_01() {

        // Player 1 is the sponsor of the quest
        ArrayList<Player> participants = game.checkForParticipants(player2, new Scanner("yes\nno\nyes"), printWriter); // 'yes' for Player 3, 'no' for Player 4, 'yes' for Player 1
        String results = output.toString();

        assertTrue(results.contains("Player P3: Do you want to participate in the quest? (Yes/No)"), "Game should ask Player P3 if they want to participant in the quest");
        assertTrue(results.contains("Player P4: Do you want to participate in the quest? (Yes/No)"), "Game should ask Player P4 if they want to participant in the quest");
        assertTrue(results.contains("Player P1: Do you want to participate in the quest? (Yes/No)"), "Game should ask Player P1 if they want to participant in the quest");
        assertFalse(results.contains("Player P2: Do you want to participate in the quest? (Yes/No)"), "Game should not have asked Player P2 if they want to participant in the quest (they are the sponsor)");

        assertEquals(2, participants.size(), "There should be 2 participants for the quest");
        assertEquals("[Player P3, Player P1]", participants.toString(), "Participants should be Player P3 and Player P1");
    }

    @Test
    @DisplayName("Foe card selected for attack")
    void RESP_15_TEST_01() {
        Deck attack = new Deck();

        // Player tries to use a Foe card for an attack
        boolean response = game.validateCardForAttack(new Card(Card.CardType.Foe, 5), attack, printWriter);

        // Assertions
        assertFalse(response, "Should return false as a Foe card cannot be used for an attack.");

        String results = output.toString();
        assertTrue(results.contains("Only weapon cards can be used for attacks."),
                "Player should receive an error message after choosing a Foe card for an attack.");
    }

    @Test
    @DisplayName("Duplicate weapon card selected for attack")
    void RESP_15_TEST_02() {
        Deck attack = new Deck();

        // Adding an initial Horse card to the attack
        attack.addCard(new Card(Card.CardType.Weapon, "Horse", 10));

        // Player tries to use another Horse card for an attack
        boolean response = game.validateCardForAttack(new Card(Card.CardType.Weapon, "Horse", 10), attack, printWriter);

        // Assertions
        assertFalse(response, "Should return false as a duplicate Horse card cannot be used for the attack.");

        String results = output.toString();
        assertTrue(results.contains("Invalid: Only one Horse card allowed per attack."),
                "Player should receive an error message after choosing a duplicate Horse card for the attack.");
    }

    @Test
    @DisplayName("Valid weapon card selected for attack")
    void RESP_15_TEST_03() {
        Deck attack = new Deck();

        // Adding an initial Horse card to the attack
        attack.addCard(new Card(Card.CardType.Weapon, "Horse", 10));

        // Player tries to use a different Weapon card (Sword) for the attack
        boolean response = game.validateCardForAttack(new Card(Card.CardType.Weapon, "Sword", 10), attack, printWriter);

        // Assertions
        assertTrue(response, "Should return true as the selected Sword card is valid for the attack.");

        String results = output.toString();
        assertTrue(results.contains("Added Sword to the current attack: [Sword, Horse]"),
                "Sword is a valid card and should be added to the current attack.");
    }

    @Test
    @DisplayName("Game indicates that a player's turn has ended, prompts User to press 'enter'")
    void RESP_16_TEST_01() {
        // Assuming Player P2's turn has ended
        game.endPlayerTurn(player2, new Scanner("jnw\n\n"), printWriter);
        String results = output.toString();

        assertTrue(results.contains("Player P2's turn has ended. Press Enter to continue..."));
        assertTrue(results.contains("Invalid input. Please press Enter to continue..."), "Player P2 entered invalid input, 'jnw'");
    }

    @Test
    @DisplayName("Increasing shield count of quest winners: no winners")
    void RESP_17_TEST_01() {
        // Assuming Player P2's turn has ended
        int stageNumber = 3;
        ArrayList<Player> participants = new ArrayList<>();
        game.questWinnersPrize(participants, stageNumber, printWriter);
        String results = output.toString();

        assertTrue(results.contains("No one won the quest"), "Game should display if no players won the quest");

        // All players had a default of 0 shields
        assertEquals(0, player1.getShields(), "Player 1 should still have 0 shields");
        assertEquals(0, player2.getShields(), "Player 2 should still have 0 shields");
        assertEquals(0, player3.getShields(), "Player 3 should still have 0 shields");
        assertEquals(0, player4.getShields(), "Player 4 should still have 0 shields");
    }

    @Test
    @DisplayName("Increasing shield count of quest winners")
    void RESP_17_TEST_02() {
        // Assuming Player P2's turn has ended
        int stageNumber = 3;
        ArrayList<Player> questWinners = new ArrayList<>();

        //Assuming Player P1 and Player P3 won the quest
        questWinners.add(player1);
        questWinners.add(player3);

        game.questWinnersPrize(questWinners, stageNumber, printWriter);
        String results = output.toString();

        assertTrue(results.contains("Player P1 won the quest"), "Game should display the winners of the quest");
        assertTrue(results.contains("Player P3 won the quest"), "Game should display the winners of the quest");

        // Player 1 & Player 3 had a default of 0 shields, both gained 3 shields
        assertEquals(3, player1.getShields(), "Quest winners earn shields equal to the number of stages in the quest, in this case 3 shields");
        assertEquals(3, player3.getShields(), "Quest winners earn shields equal to the number of stages in the quest, in this case 3 shields");

        // Player 2 and Player 4's shield counts should remain unchanged
        assertEquals(0, player2.getShields(), "Player 2 should still have 0 shields");
        assertEquals(0, player4.getShields(), "Player 4 should still have 0 shields");
    }

    @Test
    @DisplayName("Discarding sponsor's card at the end of the quest + trimming")
    void RESP_18_TEST_01() {
        // Creating the first stage of the quest with only F10
        Deck stage1 = new Deck();
        stage1.addCard(new Card(Card.CardType.Foe, 10));

        // Creating the second stage of the quest with [F10, Dagger]
        Deck stage2 = new Deck();
        stage2.addCard(new Card(Card.CardType.Foe, 10));
        stage2.addCard(new Card(Card.CardType.Weapon, "Dagger", 5));

        // Creating the third stage of the quest with [F15, Sword]
        Deck stage3 = new Deck();
        stage3.addCard(new Card(Card.CardType.Weapon, "Sword", 10));
        stage3.addCard(new Card(Card.CardType.Foe, 15));

        // Creating the quest: [[F10], [F10, Dagger], [F15, Sword]]
        ArrayList<Deck> quest = new ArrayList<>();
        quest.add(stage1);
        quest.add(stage2);
        quest.add(stage3);

        // Clearing the adventure deck
        game.getAdventureDeck().getDeck().clear();

        // Making the adventure deck only include 13 cards: [F5, F5, F5, F5, F5, F10, Sword, F15, Lance, Horse, Excalibur, F20, F25]
        for (int i=0; i < 5; i++){
            game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 5));
        }

        game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 10));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Sword", 10));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 15));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Lance", 20));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Horse", 10));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Excalibur", 30));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 20));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 25));

        // Player 2 draws the first 5 cards from the adventure deck, After: Player 2's Hand: [F5, F5, F5, F5, F5]
        game.drawAdventureCards(5, player2);

        // Assuming player 2 sponsored the Quest
        game.discardingSponsorCards(quest, player2, new Scanner("1"), printWriter);

        String results = output.toString();

        // Player 2 drawing as cards as used to build the quest + the number of stages in the quest
        // Player 2's hand becomes 13 cards, triggering the trimming
        assertTrue(results.contains("Player P2's hand is too large. It needs to be trimmed to a maximum of 12 cards:"), "Player 2 (as the sponsor) should draw as many as cards as used to build the quest + the number of stages in the quest (at the end), triggering the trimming");
        assertTrue(results.contains("Enter a card position (1 to 13) to remove:"), "Player P2 should be prompted to enter a card position to remove");
        assertTrue(results.contains("Removed F5 from your hand. Player P2's updated hand: [F5, F5, F5, F5, F10, F15, F20, F25, Sword, Horse, Lance, Excalibur]"), "Player P2's updated hand after trimming the first card");

        assertEquals(12, player2.getHandSize(), "Player 2's hand should have 12 cards after getting the compensated cards + trimming");
        assertEquals(0, game.getAdventureDeck().getSize(), "Adventure deck should have 0 cards left as they were drawn by Player 2 to compensate for their hand (Adventure deck started out with 13 cards, 5 was drawn by Player P2 initially)");

        // Discarding all the cards used to build the quest
        assertEquals(6, game.getAdventureDiscardDeck().getSize(), "The 5 cards used by Player P2 to build the quest should be discarded + 1 card from Player P2's hand from trimming");
        assertEquals("[F5, F10, F10, F15, Dagger, Sword]", game.getAdventureDiscardDeck().toString(), "The cards that were discarded should be the ones used to build the Quest + 1 card from Player P2's hand from trimming");
    }

    @Test
    @DisplayName("Discarding sponsor's card at the end of the quest + trimming")
    void RESP_18_TEST_02() {
        // Creating the first stage of the quest with only F10
        Deck stage1 = new Deck();
        stage1.addCard(new Card(Card.CardType.Foe, 10));

        // Creating the second stage of the quest with [F10, Dagger]
        Deck stage2 = new Deck();
        stage2.addCard(new Card(Card.CardType.Foe, 10));
        stage2.addCard(new Card(Card.CardType.Weapon, "Dagger", 5));

        // Creating the third stage of the quest with [F15, Sword]
        Deck stage3 = new Deck();
        stage3.addCard(new Card(Card.CardType.Weapon, "Sword", 10));
        stage3.addCard(new Card(Card.CardType.Foe, 15));

        // Creating the quest: [[F10], [F10, Dagger], [F15, Sword]]
        ArrayList<Deck> quest = new ArrayList<>();
        quest.add(stage1);
        quest.add(stage2);
        quest.add(stage3);

        // Assuming player 2 sponsored the Quest
        game.discardingSponsorCards(quest, player2, null, printWriter);
        String results = output.toString();

        // Discarding all the cards used to build the quest
        assertEquals(5, game.getAdventureDiscardDeck().getSize(), "The cards used by Player 2 to build the quest should be discarded");
        assertEquals("[F10, F10, F15, Dagger, Sword]", game.getAdventureDiscardDeck().toString(), "The cards that were discarded should be the ones used to build the Quest");

        // Player 2 had no cards in their hand
        assertEquals(8, player2.getHandSize(), "Player 2 (as the sponsor) should draw as many as cards as used to build the quest + the number of stages in the quest (at the end)");
        assertEquals(92, game.getAdventureDeck().getSize(), "Adventure deck should have 8 cards less as they were drawn by Player 2 to compensate for their hand");
        assertTrue(results.isEmpty(), "Trimming should not have been triggered so nothing should be displayed");
    }

    @Test
    @DisplayName("Game resolves the attack by one player against the current stage: Successful attack + No Trimming")
    void RESP_19_TEST_01() {
        // Creating a stage with only F30
        Deck stage = new Deck();
        stage.addCard(new Card(Card.CardType.Foe, 30));

        // No participant is ineligible
        ArrayList<Player> ineligibleParticipants = new ArrayList<>();

        game.getAdventureDeck().getDeck().clear(); // Clearing the adventure deck

        // Making the adventure deck only include [F5, F10, Dagger, Sword, Sword, Lance]
        game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 5));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Sword", 10));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 10));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Sword", 10));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Lance", 20));

        game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 5));

        // Player 1 draws the first 5 cards from the adventure deck, After: Player 1's Hand: [F5, F10, Sword, Sword, Lance]
        game.drawAdventureCards(5, player1);

        // Assuming Player 1 agreed to participant in the quest
        game.performStageAttack(player1, stage, ineligibleParticipants, new Scanner("4\n5\nquit"), printWriter);
        String results = output.toString();

        assertTrue(results.contains("Player P1 has drawn an adventure card: F5"), "An eligible participant who chooses to participate should draw 1 adventure card");
        assertTrue(results.contains("Setting up Player P1's attack: "), "Game should indicate Player P1 is building the attack");

        assertTrue(results.contains("Player P1's hand: [F5, F5, F10, Sword, Sword, Lance]"), "Game should display Player P1's hand with the drawn Adventure card");
        assertTrue(results.contains("Enter a card position (1 to 6) to add to the attack, or type 'quit' to finish attack setup:"), "Game should prompt Player P1 to enter the position of the next card to include in the attack or ‘quit’");
        assertTrue(results.contains("Added Sword to the current attack: [Sword]"), "Sword is a valid card & should be included in the set of cards for the current attack, which is displayed");

        assertTrue(results.contains("Enter a card position (1 to 5) to add to the attack, or type 'quit' to finish attack setup:"), "Game should prompt Player P1 to enter the position of the next card to include in the attack or ‘quit’");
        assertTrue(results.contains("Added Lance to the current attack: [Sword, Lance]"), "Lance is a valid card & should be included in the set of cards for the current attack, which is displayed");
        assertTrue(results.contains("Player P1 built an attack with the following cards: [Sword, Lance]"), "'Quit' was entered so the cards used for this attack should be displayed");

        assertTrue(results.contains("Player P1's attack was successful. Attack: 30 vs Stage: 30"), "The game should resolve the attack against the current stage: attack was successful");
        assertTrue(ineligibleParticipants.isEmpty(), "Player P1 should be eligible for the next stage attack");
        assertEquals(2, game.getAdventureDiscardDeck().getSize(), "The cards used for the attack of the current stage should be discarded");
    }

    @Test
    @DisplayName("Game resolves the attack by one player against the current stage: Failed attack + No Trimming")
    void RESP_19_TEST_02() {
        // Creating a stage with only F15
        Deck stage = new Deck();
        stage.addCard(new Card(Card.CardType.Foe, 15));

        // No participant is ineligible
        ArrayList<Player> ineligibleParticipants = new ArrayList<>();

        game.getAdventureDeck().getDeck().clear(); // Clearing the adventure deck

        // Making the adventure deck only include [F5, F10, Dagger, Sword, Sword, Lance]
        game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 5));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Sword", 10));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 10));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Sword", 10));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Lance", 20));

        game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 5));

        // Player 1 draws the first 5 cards from the adventure deck, After: Player 1's Hand: [F5, F10, Sword, Sword, Lance]
        // Making Player 1's hand under 12 cards to not trigger the trimming
        game.drawAdventureCards(5, player1);

        // Assuming Player 1 agreed to participant in the quest
        game.performStageAttack(player1, stage, ineligibleParticipants, new Scanner("4\nquit"), printWriter);
        String results = output.toString();

        assertTrue(results.contains("Player P1 has drawn an adventure card: F5"), "An eligible participant who chooses to participate should draw 1 adventure card");
        assertTrue(results.contains("Setting up Player P1's attack: "), "Game should indicate Player P1 is building the attack");

        assertTrue(results.contains("Player P1's hand: [F5, F5, F10, Sword, Sword, Lance]"), "Game should display Player P1's hand with the drawn Adventure card");
        assertTrue(results.contains("Enter a card position (1 to 6) to add to the attack, or type 'quit' to finish attack setup:"), "Game should prompt Player P1 to enter the position of the next card to include in the attack or ‘quit’");
        assertTrue(results.contains("Added Sword to the current attack: [Sword]"), "Sword is a valid card & should be included in the set of cards for the current attack, which is displayed");

        assertTrue(results.contains("Player P1 built an attack with the following cards: [Sword]"), "'Quit' was entered so the card used for this attack should be displayed");

        assertTrue(results.contains("Player P1's attack failed. Attack: 10 vs Stage: 15"), "The game should resolve the attack against the current stage: attack failed");
        assertEquals(1, ineligibleParticipants.size(), "Player P1 should be ineligible for the next stage attack");
        assertEquals(1, game.getAdventureDiscardDeck().getSize(), "The cards used for the attack of the current stage should be discarded");
    }

    @Test
    @DisplayName("Game resolves the attack by one player against the current stage: Successful attack + Trimming")
    void RESP_19_TEST_03() {
        // Creating a stage with only F15
        Deck stage = new Deck();
        stage.addCard(new Card(Card.CardType.Foe, 15));

        // No participant is ineligible
        ArrayList<Player> ineligibleParticipants = new ArrayList<>();

        game.getAdventureDeck().getDeck().clear(); // Clearing the adventure deck

        // Making the adventure deck only include [F5, F5, F5, F10, F10, F10, Sword, Sword, Sword, Lance, Lance, Lance, Horse]
        for (int i=0; i < 3; i++) {
            game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 5));
            game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 10));
            game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Sword", 10));
            game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Lance", 20));
        }
        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Horse", 10));

        // Player 1 draws the first 5 cards from the adventure deck, After: Player 1's Hand:  [F5, F5, F5, F10, F10, F10, Sword, Sword, Sword, Lance, Lance, Lance]
        // Making Player 1's hand 12 cards to trigger the trimming
        game.drawAdventureCards(12, player1);

        // Assuming Player 1 agreed to participant in the quest
        game.performStageAttack(player1, stage, ineligibleParticipants, new Scanner("1\n10\nquit"), printWriter);
        String results = output.toString();

        assertTrue(results.contains("Player P1 has drawn an adventure card: Horse"), "An eligible participant who chooses to participate should draw 1 adventure card");
        assertTrue(results.contains("Player P1's hand is too large. It needs to be trimmed to a maximum of 12 cards:"), "After drawing an adventure card, Player P1's hand needs to be trimmed");
        assertTrue(results.contains("Enter a card position (1 to 13) to remove:"), "Player P1 should be prompted to enter a card position to remove");
        assertTrue(results.contains("Removed F5 from your hand. Player P1's updated hand: [F5, F5, F10, F10, F10, Sword, Sword, Sword, Horse, Lance, Lance, Lance]"), "Player P1's updated hand after trimming the first card");

        assertTrue(results.contains("Setting up Player P1's attack: "), "Game should indicate Player P1 is building the attack");
        assertTrue(results.contains("Enter a card position (1 to 12) to add to the attack, or type 'quit' to finish attack setup:"), "Game should prompt Player P1 to enter the position of a card to include in the attack or ‘quit’");
        assertTrue(results.contains("Added Lance to the current attack: [Lance]"), "Lance is a valid card & should be included in the set of cards for the current attack, which is displayed");
        assertTrue(results.contains("Player P1 built an attack with the following cards: [Lance]"), "'Quit' was entered so the card used for this attack should be displayed");

        assertTrue(results.contains("Player P1's attack was successful. Attack: 20 vs Stage: 15"), "The game should resolve the attack against the current stage: attack was successful");
        assertTrue(ineligibleParticipants.isEmpty(), "Player P1 should be eligible for the next stage attack");
        assertEquals(2, game.getAdventureDiscardDeck().getSize(), "The cards used for the attack of the current stage should be discarded");
    }










}