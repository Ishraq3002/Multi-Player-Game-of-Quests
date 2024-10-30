import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
class ATest {
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
    @DisplayName("The compulsary A1 test")
    // I was only able to place input until the third stage of the resolving (at which point I said that P1 and P4 said no to participating)
    // Sorry I was running out of time
    void ATEST_JP_Scenario(){
        // Setting Player 1's hand
        player1.getHand().addCard(new Card(Card.CardType.Foe, 5));
        player1.getHand().addCard(new Card(Card.CardType.Foe, 5));
        player1.getHand().addCard(new Card(Card.CardType.Foe, 15));
        player1.getHand().addCard(new Card(Card.CardType.Foe, 15));
        player1.getHand().addCard(new Card(Card.CardType.Weapon, "Dagger", 5));
        player1.getHand().addCard(new Card(Card.CardType.Weapon, "Sword", 10));
        player1.getHand().addCard(new Card(Card.CardType.Weapon, "Sword", 10));
        player1.getHand().addCard(new Card(Card.CardType.Weapon, "Horse", 10));
        player1.getHand().addCard(new Card(Card.CardType.Weapon, "Horse", 10));
        player1.getHand().addCard(new Card(Card.CardType.Weapon, "Battle-Axe", 15));
        player1.getHand().addCard(new Card(Card.CardType.Weapon, "Battle-Axe", 15));
        player1.getHand().addCard(new Card(Card.CardType.Weapon, "Lance", 20));

        // Setting Player 2's hand
        player2.getHand().addCard(new Card(Card.CardType.Foe, 5));
        player2.getHand().addCard(new Card(Card.CardType.Foe, 5));
        player2.getHand().addCard(new Card(Card.CardType.Foe, 15));
        player2.getHand().addCard(new Card(Card.CardType.Foe, 15));
        player2.getHand().addCard(new Card(Card.CardType.Foe, 40));
        player2.getHand().addCard(new Card(Card.CardType.Weapon, "Dagger", 5));
        player2.getHand().addCard(new Card(Card.CardType.Weapon, "Sword", 10));
        player2.getHand().addCard(new Card(Card.CardType.Weapon, "Horse", 10));
        player2.getHand().addCard(new Card(Card.CardType.Weapon, "Horse", 10));
        player2.getHand().addCard(new Card(Card.CardType.Weapon, "Battle-Axe", 15));
        player2.getHand().addCard(new Card(Card.CardType.Weapon, "Battle-Axe", 15));
        player2.getHand().addCard(new Card(Card.CardType.Weapon, "Excalibur", 30));

        // Setting Player 3's hand
        player3.getHand().addCard(new Card(Card.CardType.Foe, 5));
        player3.getHand().addCard(new Card(Card.CardType.Foe, 5));
        player3.getHand().addCard(new Card(Card.CardType.Foe, 5));
        player3.getHand().addCard(new Card(Card.CardType.Foe, 15));
        player3.getHand().addCard(new Card(Card.CardType.Weapon, "Dagger", 5));
        player3.getHand().addCard(new Card(Card.CardType.Weapon, "Sword", 10));
        player3.getHand().addCard(new Card(Card.CardType.Weapon, "Sword", 10));
        player3.getHand().addCard(new Card(Card.CardType.Weapon, "Sword", 10));
        player3.getHand().addCard(new Card(Card.CardType.Weapon, "Horse", 10));
        player3.getHand().addCard(new Card(Card.CardType.Weapon, "Horse", 10));
        player3.getHand().addCard(new Card(Card.CardType.Weapon, "Battle-Axe", 15));
        player3.getHand().addCard(new Card(Card.CardType.Weapon, "Lance", 20));

        // Setting Player 4's hand
        player4.getHand().addCard(new Card(Card.CardType.Foe, 5));
        player4.getHand().addCard(new Card(Card.CardType.Foe, 15));
        player4.getHand().addCard(new Card(Card.CardType.Foe, 15));
        player4.getHand().addCard(new Card(Card.CardType.Foe, 40));
        player4.getHand().addCard(new Card(Card.CardType.Weapon, "Dagger", 5));
        player4.getHand().addCard(new Card(Card.CardType.Weapon, "Dagger", 5));
        player4.getHand().addCard(new Card(Card.CardType.Weapon, "Sword", 10));
        player4.getHand().addCard(new Card(Card.CardType.Weapon, "Horse", 10));
        player4.getHand().addCard(new Card(Card.CardType.Weapon, "Horse", 10));
        player4.getHand().addCard(new Card(Card.CardType.Weapon, "Battle-Axe", 15));
        player4.getHand().addCard(new Card(Card.CardType.Weapon, "Lance", 20));
        player4.getHand().addCard(new Card(Card.CardType.Weapon, "Excalibur", 30));

        eventDeck.getDeck().clear(); // Clearing the event deck
        eventDeck.addCard(new Card(Card.CardType.Quest, 4));

        adventureDeck.getDeck().clear(); // Clearing the adventure deck
        adventureDeck.addCard(new Card(Card.CardType.Foe, 30));
        adventureDeck.addCard(new Card(Card.CardType.Weapon, "Sword", 10));
        adventureDeck.addCard(new Card(Card.CardType.Weapon, "Battle-Axe", 15));
        adventureDeck.addCard(new Card(Card.CardType.Foe, 10));
        adventureDeck.addCard(new Card(Card.CardType.Weapon, "Lance", 20));
        adventureDeck.addCard(new Card(Card.CardType.Weapon, "Lance", 20));
        adventureDeck.addCard(new Card(Card.CardType.Weapon, "Battle-Axe", 15));
        adventureDeck.addCard(new Card(Card.CardType.Weapon, "Sword", 10));
        adventureDeck.addCard(new Card(Card.CardType.Foe, 30));
        adventureDeck.addCard(new Card(Card.CardType.Weapon, "Lance", 20));

        Deck randomAdventureDeck = new Deck();  // Making a deck with 12 random cards
        for (int i=0; i<3; i++) {
            randomAdventureDeck.addCard(new Card(Card.CardType.Foe, 15));
            randomAdventureDeck.addCard(new Card(Card.CardType.Foe, 10));
            randomAdventureDeck.addCard(new Card(Card.CardType.Weapon, "Sword", 10));
            randomAdventureDeck.addCard(new Card(Card.CardType.Weapon, "Battle-Axe", 15));
        }

        randomAdventureDeck.shuffle();
        adventureDeck.addAllCards(randomAdventureDeck); // Adding it to the back of main adventure deck
        System.out.println(adventureDeck.getSize());

        Card card = game.drawEventCard(player2, printWriter);
        System.out.println(card);

        ArrayList<Deck> quest = game.buildQuest(card, player2, new Scanner("1\n7\nquit\n2\n5\nquit\n2\n3\n4\nquit\n2\n3\nquit"), printWriter);

        //game.processQuestTurn(card, player1, new Scanner("no\nyes\n1\n7\nquit\n2\n5\nquit\n2\n3\n4\nquit\n2\n3\nquit"), printWriter);
        ArrayList<Player> participants = new ArrayList<>();
        participants.add(player1);
        participants.add(player3);
        participants.add(player4);
        game.resolveQuest(participants, quest, new Scanner("1\n5\n5\nquit\n1\n5\n4\nquit\n1\n5\n6\nquit\nyes\n8\n6\nquit\nyes\n9\n4\nquit\nyes\n6\n6\nquit\nno\nno"), printWriter); // Resolving the Quest


        //game.turn(player1, new Scanner("no\nyes\n1\n7\nquit\n2\n5\nquit\n2\n3\n4\nquit\n2\n3\nquit\nyes\nyes\nyes\n1\n5\n5\nquit\n1\n6\n5\nquit\n1\n4\n7\nquit\nyes\n7\n6\nquit\nyes\n10\n4\nquit\nyes\n6\n8\nquit\nyes\n1\n2\n3\nquit\nyes\n1\n2\n3\nquit\nyes\n1\n2\n3\nquit\nyes\n1\n2\n3\nquit\n\n"), printWriter);
        String results = output.toString();
        System.out.println(results);

        assertTrue(results.contains("Player P1's attack failed. Attack: 20 vs Stage: 25"));
        assertEquals("[F5, F10, F15, F15, F30, Horse, Battle-Axe, Battle-Axe, Lance]", player1.getHand().toString());
        assertEquals(0, player1.getShields());
    }

    @Test
    @DisplayName("Building a valid quest: all valid inputs")
    void ATEST_01_TEST_01() {
        adventureDeck.getDeck().clear(); // Clearing the adventure deck

        // Making the adventure deck only include [F10, Dagger, Sword, Sword]
        adventureDeck.addCard(new Card(Card.CardType.Foe, 5));
        adventureDeck.addCard(new Card(Card.CardType.Weapon, "Dagger", 5));
        adventureDeck.addCard(new Card(Card.CardType.Foe, 10));
        adventureDeck.addCard(new Card(Card.CardType.Foe, 5));

        // Player 1 draws 4 cards from the adventure deck, After: Player 1's Hand: [F5, F5, F10, Dagger]
        game.drawAdventureCards(4, player1);

        // Building a quest of 2 stages by Player 1
        ArrayList<Deck> quest = game.buildQuest(new Card(Card.CardType.Quest, 2), player1, new Scanner("1\nquit\n1\n2\nquit"), printWriter);

        String results = output.toString();

        assertTrue(results.contains("Setting up stage 1 for the quest."), "Game should display the stage of the quest");
        assertTrue(results.contains("Added F5 to the current stage: [F5]"));
        assertTrue(results.contains("Stage setup complete. Value: 5. Stage: [F5]"), "Player P1 successfully set up stage 1 of the quest: The cards used for this stage should be displayed");

        assertTrue(results.contains("Setting up stage 2 for the quest."), "Game should display the stage of the quest");
        assertTrue(results.contains("Added Dagger to the current stage: [F5, Dagger]"));
        assertTrue(results.contains("Stage setup complete. Value: 10. Stage: [F5, Dagger]"), "Player P1 successfully set up a stage 2 of the quest: The cards used for this stage should be displayed");

        assertTrue(results.contains("Quest setup complete. The quest is ready to be resolved."), "Game should display that the quest setup is complete");

        assertEquals("[[F5], [F5, Dagger]]", quest.toString(), "Based on the inputs, the quest built should be [[F5], [F5, Dagger]]");
        assertEquals(2, quest.size(), "The quest should have 2 stages");
        assertEquals(1, player1.getHandSize(), "Player P1 should have one card left after building the quest");
    }

    //A-TEST
    @Test
    @DisplayName("Building a valid quest: Checking if quest keeps track of each stage value")
    void ATEST_01_TEST_02() {
        game.getAdventureDeck().getDeck().clear(); // Clearing the adventure deck

        // Making the adventure deck only include [F10, Dagger, Sword, Sword]
        game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 5));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Sword", 10));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 10));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 5));

        // Player 1 draws 4 cards from the adventure deck, After: Player 1's Hand: [F5, F5, F10, Sword]
        game.drawAdventureCards(4, player1);

        // Building a quest of 2 stages by Player 1
        ArrayList<Deck> quest = game.buildQuest(new Card(Card.CardType.Quest, 2), player1, new Scanner("3\nquit\nquit\n1\nquit\n2\nquit"), printWriter);

        String results = output.toString();

        assertTrue(results.contains("Setting up stage 1 for the quest."), "Game should display the stage of the quest");
        assertTrue(results.contains("Added F10 to the current stage: [F10]"));
        assertTrue(results.contains("Stage setup complete. Value: 10. Stage: [F10]"), "Player P1 successfully set up stage 1 of the quest: The cards used for this stage should be displayed");

        assertTrue(results.contains("Setting up stage 2 for the quest."), "Game should display the stage of the quest");
        assertTrue(results.contains("A stage cannot be empty"), "Player P1 should receive an error message after entering 'quit' for an empty stage");
        assertTrue(results.contains("Added F5 to the current stage: [F5]"));
        // Make sure game is keeping track of each stage value
        assertTrue(results.contains("Insufficient value for this stage. Current stage value: 5, required to exceed: 10"), "Player P1 should receive an error message after entering 'quit' for a stage with insufficient value");

        assertTrue(results.contains("Added Sword to the current stage: [F5, Sword]"));
        assertTrue(results.contains("Stage setup complete. Value: 15. Stage: [F5, Sword]"), "Player P1 successfully set up stage 2 of the quest: The cards used for this stage should be displayed");

        assertTrue(results.contains("Quest setup complete. The quest is ready to be resolved."), "Game should display that the quest setup is complete");

        assertEquals("[[F10], [F5, Sword]]", quest.toString(), "Based on the inputs, the quest built should be [[F10], [F5, Sword]]");
        assertEquals(2, quest.size(), "The quest should have 2 stages");
        assertEquals(1, player1.getHandSize(), "Player P1 should have one card left after building the quest");
    }

    @Test
    @DisplayName("Game fails to resolve quest of 3 stages: Participant failed their attack in the last stage of the Quest")
    void ATEST_02_TEST_01() {

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

        // Participants: Player P1, Player P3
        ArrayList<Player> participants = new ArrayList<>();
        participants.add(player1);
        participants.add(player3);

        // Clearing the adventure deck
        game.getAdventureDeck().getDeck().clear();

        // Making the adventure deck only include [F5, Sword, F10, Lance, Horse, Excalibur]
        game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 5));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Sword", 10));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 10));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Lance", 20));

        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Horse", 10));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Excalibur", 30));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 10));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 5));

        // Player 1 draws the first 2 cards from the adventure deck, After: Player 1's Hand: [F5, Sword]
        game.drawAdventureCards(2, player1);
        // Player 1 draws the next 2 cards from the adventure deck, After: Player 1's Hand: [F10, Lance]
        game.drawAdventureCards(2, player3);

        // Game resolving the quest
        boolean questResolvedFailed = game.resolveQuest(participants, quest, new Scanner("2\nquit\n2\nquit\nno\nyes\n3\nquit\nyes\nquit"), printWriter);
        String results = output.toString();

        // Game displays the eligible participants for stage 1
        assertTrue(results.contains("Participants for stage 1 of the Quest: [Player P1, Player P3]"), "Game should display the participants for each stage (1st) of the Quest");

        // Player 1 setting up the attack for stage 1
        assertTrue(results.contains("Player P1's turn to attack in stage 1 of the Quest:"));
        assertTrue(results.contains("Player P1 has drawn an adventure card: Horse"), "An eligible participant who chooses to participate should draw 1 adventure card");
        assertTrue(results.contains("Setting up Player P1's attack: "), "Game should indicate Player P1 is building the attack");
        assertTrue(results.contains("Player P1's hand: [F5, Sword, Horse]"), "Game should display Player P1's hand with the drawn Adventure card: Horse");
        assertTrue(results.contains("Enter a card position (1 to 3) to add to the attack, or type 'quit' to finish attack setup:"), "Game should prompt Player P1 to enter the position of a card to include in the attack or ‘quit’");
        assertTrue(results.contains("Added Sword to the current attack: [Sword]"), "Sword is a valid card & should be included in the set of cards for the current attack, which is displayed");
        assertTrue(results.contains("Player P1 built an attack with the following cards: [Sword]"), "'Quit' was entered so the card used for this attack should be displayed");
        assertTrue(results.contains("Player P1's attack was successful. Attack: 10 vs Stage: 10"), "The game should resolve the attack against the current stage: attack was successful");

        // Player 3 setting up the attack for stage 1
        assertTrue(results.contains("Player P3's turn to attack in stage 1 of the Quest:"));
        assertTrue(results.contains("Setting up Player P3's attack: "), "Game should indicate Player P3 is building the attack");
        assertTrue(results.contains("Player P3 has drawn an adventure card: Excalibur"), "An eligible participant who chooses to participate should draw 1 adventure card");
        assertTrue(results.contains("Player P3's hand: [F10, Lance, Excalibur]"), "Game should display Player P3's hand with the drawn Adventure card: Excalibur");
        assertTrue(results.contains("Enter a card position (1 to 3) to add to the attack, or type 'quit' to finish attack setup:"), "Game should prompt Player P3 to enter the position of a card to include in the attack or ‘quit’");
        assertTrue(results.contains("Added Lance to the current attack: [Lance]"), "Lance is a valid card & should be included in the set of cards for the current attack, which is displayed");
        assertTrue(results.contains("Player P3 built an attack with the following cards: [Lance]"), "'Quit' was entered so the card used for this attack should be displayed");
        assertTrue(results.contains("Player P3's attack was successful. Attack: 20 vs Stage: 10"), "The game should resolve the attack against the current stage: attack was successful");

        // Game displays the eligible participants for stage 2
        assertTrue(results.contains("Participants for stage 2 of the Quest: [Player P1, Player P3]"), "Game should display the participants for each stage (2nd) of the Quest");

        // Player 1 is asked if they want to continue with the quest
        assertTrue(results.contains("Player P1's turn to attack in stage 2 of the Quest:"));
        assertTrue(results.contains("Player P1: Do you want to continue participating in the quest? (Yes/No)"), "An eligible participant should be asked if want to withdraw or continue participating in the quest");
        assertTrue(results.contains("Player P1 has chosen not to participate further."), "Game should indicate if a participant has withdrawn from the quest");

        // After Player 1 withdraws, goes to Player P3
        // Player 3 is asked if they want to continue with the quest
        assertTrue(results.contains("Player P3's turn to attack in stage 2 of the Quest:"));
        assertTrue(results.contains("Player P3: Do you want to continue participating in the quest? (Yes/No)"), "An eligible participant should be asked if want to withdraw or continue participating in the quest");

        // Player 3 setting up the attack for stage 2
        assertTrue(results.contains("Player P3 has drawn an adventure card: F10"), "An eligible participant who chooses to participate should draw 1 adventure card");
        assertTrue(results.contains("Setting up Player P3's attack: "), "Game should indicate Player P3 is building the attack");
        assertTrue(results.contains("Player P3's hand: [F10, F10, Excalibur]"), "Game should display Player P3's updated hand with the drawn Adventure card: F10");
        assertTrue(results.contains("Enter a card position (1 to 3) to add to the attack, or type 'quit' to finish attack setup:"), "Game should prompt Player P3 to enter the position of a card to include in the attack or ‘quit’");
        assertTrue(results.contains("Added Excalibur to the current attack: [Excalibur]"), "Excalibur is a valid card & should be included in the set of cards for the current attack, which is displayed");
        assertTrue(results.contains("Player P3 built an attack with the following cards: [Excalibur]"), "'Quit' was entered so the card used for this attack should be displayed");
        assertTrue(results.contains("Player P3's attack was successful. Attack: 30 vs Stage: 15"), "The game should resolve the attack against the current stage: attack was successful");

        // Player 1 is ineligible after withdrawing
        // Game displays the eligible participants for stage 3
        assertTrue(results.contains("Participants for stage 3 of the Quest: [Player P3]"), "Game should display the participants for each stage (3rd) of the Quest");

        // Player 3 is asked if they want to continue with the quest
        assertTrue(results.contains("Player P3's turn to attack in stage 3 of the Quest:"));
        assertTrue(results.contains("Player P3: Do you want to continue participating in the quest? (Yes/No)"), "An eligible participant should be asked if want to withdraw or continue participating in the quest");

        // Player 3 setting up the attack for stage 3
        assertTrue(results.contains("Setting up Player P3's attack: "), "Game should indicate Player P3 is building the attack");
        assertTrue(results.contains("Player P3 has drawn an adventure card: F5"), "An eligible participant who chooses to participate should draw 1 adventure card");
        assertTrue(results.contains("Player P3's hand: [F5, F10, F10]"), "Game should display Player P3's updated hand with the drawn Adventure card: F5");
        assertTrue(results.contains("Enter a card position (1 to 3) to add to the attack, or type 'quit' to finish attack setup:"), "Game should prompt Player P3 to enter the position of a card to include in the attack or ‘quit’");
        assertTrue(results.contains("Player P3 built no attack"), "'Quit' was entered so the card used for this attack should be displayed");
        assertTrue(results.contains("Player P3's attack failed. Attack: 0 vs Stage: 25"), "The game should resolve the attack against the current stage: attack failed");

        assertTrue(questResolvedFailed, "Participants failed to resolve the quest");
        assertEquals(3, game.getAdventureDiscardDeck().getSize(), "The cards used for the attack of the quest should be discarded (by both Player P1 and Player P3)");
    }

    @Test
    @DisplayName("Game fails to resolve quest of 3 stages: All participants withdrew from the Quest")
    void ATEST_02_TEST_02() {

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

        // Participants: Player P1, Player P3
        ArrayList<Player> participants = new ArrayList<>();
        participants.add(player1);
        participants.add(player3);

        // Clearing the adventure deck
        game.getAdventureDeck().getDeck().clear();

        // Making the adventure deck only include [F5, Sword, F10, Lance, Horse, Excalibur, F10, F5]
        game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 5));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Sword", 10));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 10));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Lance", 20));

        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Horse", 10));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Excalibur", 30));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 10));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 5));

        // Player 1 draws the first 2 cards from the adventure deck, After: Player 1's Hand: [F5, Sword]
        game.drawAdventureCards(2, player1);
        // Player 1 draws the next 2 cards from the adventure deck, After: Player 1's Hand: [F10, Lance]
        game.drawAdventureCards(2, player3);

        // Game resolving the quest
        boolean questResolvedFailed = game.resolveQuest(participants, quest, new Scanner("2\nquit\n2\nquit\nno\nno"), printWriter);
        String results = output.toString();

        // Game displays the eligible participants for stage 1
        assertTrue(results.contains("Participants for stage 1 of the Quest: [Player P1, Player P3]"), "Game should display the participants for each stage (1st) of the Quest");

        // Player 1 setting up the attack for stage 1
        assertTrue(results.contains("Player P1's turn to attack in stage 1 of the Quest:"));
        assertTrue(results.contains("Player P1 has drawn an adventure card: Horse"), "An eligible participant who chooses to participate should draw 1 adventure card");
        assertTrue(results.contains("Setting up Player P1's attack: "), "Game should indicate Player P1 is building the attack");
        assertTrue(results.contains("Player P1's hand: [F5, Sword, Horse]"), "Game should display Player P1's hand with the drawn Adventure card: Horse");
        assertTrue(results.contains("Enter a card position (1 to 3) to add to the attack, or type 'quit' to finish attack setup:"), "Game should prompt Player P1 to enter the position of a card to include in the attack or ‘quit’");
        assertTrue(results.contains("Added Sword to the current attack: [Sword]"), "Sword is a valid card & should be included in the set of cards for the current attack, which is displayed");
        assertTrue(results.contains("Player P1 built an attack with the following cards: [Sword]"), "'Quit' was entered so the card used for this attack should be displayed");
        assertTrue(results.contains("Player P1's attack was successful. Attack: 10 vs Stage: 10"), "The game should resolve the attack against the current stage: attack was successful");

        // Player 3 setting up the attack for stage 1
        assertTrue(results.contains("Player P3's turn to attack in stage 1 of the Quest:"));
        assertTrue(results.contains("Setting up Player P3's attack: "), "Game should indicate Player P3 is building the attack");
        assertTrue(results.contains("Player P3 has drawn an adventure card: Excalibur"), "An eligible participant who chooses to participate should draw 1 adventure card");
        assertTrue(results.contains("Player P3's hand: [F10, Lance, Excalibur]"), "Game should display Player P3's updated hand with the drawn Adventure card: Excalibur");
        assertTrue(results.contains("Enter a card position (1 to 3) to add to the attack, or type 'quit' to finish attack setup:"), "Game should prompt Player P3 to enter the position of a card to include in the attack or ‘quit’");
        assertTrue(results.contains("Added Lance to the current attack: [Lance]"), "Lance is a valid card & should be included in the set of cards for the current attack, which is displayed");
        assertTrue(results.contains("Player P3 built an attack with the following cards: [Lance]"), "'Quit' was entered so the card used for this attack should be displayed");
        assertTrue(results.contains("Player P3's attack was successful. Attack: 20 vs Stage: 10"), "The game should resolve the attack against the current stage: attack was successful");

        // Game displays the eligible participants for stage 2
        assertTrue(results.contains("Participants for stage 2 of the Quest: [Player P1, Player P3]"), "Game should display the participants for each stage (2nd) of the Quest");

        // Player 1 is asked if they want to continue with the quest
        assertTrue(results.contains("Player P1's turn to attack in stage 2 of the Quest:"));
        assertTrue(results.contains("Player P1: Do you want to continue participating in the quest? (Yes/No)"), "An eligible participant should be asked if want to withdraw or continue participating in the quest");
        assertTrue(results.contains("Player P1 has chosen not to participate further."), "Game should indicate if a participant has withdrawn from the quest");

        // After Player 1 withdraws, goes to Player P3
        // Player 3 is asked if they want to continue with the quest
        assertTrue(results.contains("Player P3's turn to attack in stage 2 of the Quest:"));
        assertTrue(results.contains("Player P3: Do you want to continue participating in the quest? (Yes/No)"), "An eligible participant should be asked if want to withdraw or continue participating in the quest");
        assertTrue(results.contains("Player P3 has chosen not to participate further."), "Game should indicate if a participant has withdrawn from the quest");

        // Player 3 withdraw from the Quest
        // Game displays there are no participants left
        assertTrue(results.contains("No participants left for the quest."), "Game should display if participants have decided to withdraw before all stages could be resolved");

        assertTrue(questResolvedFailed, "All participants withdrew from the Quest. Failed to resolve the quest");
        assertEquals(2, game.getAdventureDiscardDeck().getSize(), "The cards used for the attack of the quest should be discarded (by both Player P1 and Player P3)");
    }

    @Test
    @DisplayName("Game resolves a quest of 3 stages")
    void ATEST_02_TEST_03() {

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

        // Participants: Player P1, Player P3
        ArrayList<Player> participants = new ArrayList<>();
        participants.add(player1);
        participants.add(player3);

        // Clearing the adventure deck
        game.getAdventureDeck().getDeck().clear();

        // Making the adventure deck only include [F5, Sword, F10, Lance, Horse, Excalibur]
        game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 5));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Sword", 10));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 10));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Lance", 20));

        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Horse", 10));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Excalibur", 30));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Foe, 10));
        game.getAdventureDeck().addCard(new Card(Card.CardType.Weapon, "Excalibur", 30));

        // Player 1 draws the first 2 cards from the adventure deck, After: Player 1's Hand: [F5, Sword]
        game.drawAdventureCards(2, player1);
        // Player 1 draws the next 2 cards from the adventure deck, After: Player 1's Hand: [F10, Lance]
        game.drawAdventureCards(2, player3);

        // Game resolving the quest
        boolean questResolvedFailed = game.resolveQuest(participants, quest, new Scanner("2\nquit\n2\nquit\nno\nyes\n3\nquit\nyes\n3\nquit"), printWriter);
        String results = output.toString();

        // Game displays the eligible participants for stage 1
        assertTrue(results.contains("Participants for stage 1 of the Quest: [Player P1, Player P3]"), "Game should display the participants for each stage (1st) of the Quest");

        // Player 1 setting up the attack for stage 1
        assertTrue(results.contains("Player P1's turn to attack in stage 1 of the Quest:"));
        assertTrue(results.contains("Player P1 has drawn an adventure card: Horse"), "An eligible participant who chooses to participate should draw 1 adventure card");
        assertTrue(results.contains("Setting up Player P1's attack: "), "Game should indicate Player P1 is building the attack");
        assertTrue(results.contains("Player P1's hand: [F5, Sword, Horse]"), "Game should display Player P1's hand with the drawn Adventure card: Horse");
        assertTrue(results.contains("Enter a card position (1 to 3) to add to the attack, or type 'quit' to finish attack setup:"), "Game should prompt Player P1 to enter the position of a card to include in the attack or ‘quit’");
        assertTrue(results.contains("Added Sword to the current attack: [Sword]"), "Sword is a valid card & should be included in the set of cards for the current attack, which is displayed");
        assertTrue(results.contains("Player P1 built an attack with the following cards: [Sword]"), "'Quit' was entered so the card used for this attack should be displayed");
        assertTrue(results.contains("Player P1's attack was successful. Attack: 10 vs Stage: 10"), "The game should resolve the attack against the current stage: attack was successful");

        // Player 3 setting up the attack for stage 1
        assertTrue(results.contains("Player P3's turn to attack in stage 1 of the Quest:"));
        assertTrue(results.contains("Setting up Player P3's attack: "), "Game should indicate Player P3 is building the attack");
        assertTrue(results.contains("Player P3 has drawn an adventure card: Excalibur"), "An eligible participant who chooses to participate should draw 1 adventure card");
        assertTrue(results.contains("Player P3's hand: [F10, Lance, Excalibur]"), "Game should display Player P3's updated hand with the drawn Adventure card: Excalibur");
        assertTrue(results.contains("Enter a card position (1 to 3) to add to the attack, or type 'quit' to finish attack setup:"), "Game should prompt Player P3 to enter the position of a card to include in the attack or ‘quit’");
        assertTrue(results.contains("Added Lance to the current attack: [Lance]"), "Lance is a valid card & should be included in the set of cards for the current attack, which is displayed");
        assertTrue(results.contains("Player P3 built an attack with the following cards: [Lance]"), "'Quit' was entered so the card used for this attack should be displayed");
        assertTrue(results.contains("Player P3's attack was successful. Attack: 20 vs Stage: 10"), "The game should resolve the attack against the current stage: attack was successful");

        // Game displays the eligible participants for stage 2
        assertTrue(results.contains("Participants for stage 2 of the Quest: [Player P1, Player P3]"), "Game should display the participants for each stage (2nd) of the Quest");

        // Player 1 is asked if they want to continue with the quest
        assertTrue(results.contains("Player P1's turn to attack in stage 2 of the Quest:"));
        assertTrue(results.contains("Player P1: Do you want to continue participating in the quest? (Yes/No)"), "An eligible participant should be asked if want to withdraw or continue participating in the quest");
        assertTrue(results.contains("Player P1 has chosen not to participate further."), "Game should indicate if a participant has withdrawn from the quest");

        // After Player 1 withdraws, goes to Player P3
        // Player 3 is asked if they want to continue with the quest
        assertTrue(results.contains("Player P3's turn to attack in stage 2 of the Quest:"));
        assertTrue(results.contains("Player P3: Do you want to continue participating in the quest? (Yes/No)"), "An eligible participant should be asked if want to withdraw or continue participating in the quest");

        // Player 3 setting up the attack for stage 2
        assertTrue(results.contains("Player P3 has drawn an adventure card: F10"), "An eligible participant who chooses to participate should draw 1 adventure card");
        assertTrue(results.contains("Setting up Player P3's attack: "), "Game should indicate Player P3 is building the attack");
        assertTrue(results.contains("Player P3's hand: [F10, F10, Excalibur]"), "Game should display Player P3's updated hand with the drawn Adventure card: F10");
        assertTrue(results.contains("Enter a card position (1 to 3) to add to the attack, or type 'quit' to finish attack setup:"), "Game should prompt Player P3 to enter the position of a card to include in the attack or ‘quit’");
        assertTrue(results.contains("Added Excalibur to the current attack: [Excalibur]"), "Excalibur is a valid card & should be included in the set of cards for the current attack, which is displayed");
        assertTrue(results.contains("Player P3 built an attack with the following cards: [Excalibur]"), "'Quit' was entered so the card used for this attack should be displayed");
        assertTrue(results.contains("Player P3's attack was successful. Attack: 30 vs Stage: 15"), "The game should resolve the attack against the current stage: attack was successful");

        // Player 1 is ineligible after withdrawing
        // Game displays the eligible participants for stage 3
        assertTrue(results.contains("Participants for stage 3 of the Quest: [Player P3]"), "Game should display the participants for each stage (3rd) of the Quest");

        // Player 3 is asked if they want to continue with the quest
        assertTrue(results.contains("Player P3's turn to attack in stage 3 of the Quest:"));
        assertTrue(results.contains("Player P3: Do you want to continue participating in the quest? (Yes/No)"), "An eligible participant should be asked if want to withdraw or continue participating in the quest");

        // Player 3 setting up the attack for stage 3
        assertTrue(results.contains("Setting up Player P3's attack: "), "Game should indicate Player P3 is building the attack");
        assertTrue(results.contains("Player P3 has drawn an adventure card: Excalibur"), "An eligible participant who chooses to participate should draw 1 adventure card");
        assertTrue(results.contains("Player P3's hand: [F10, F10, Excalibur]"), "Game should display Player P3's updated hand with the drawn Adventure card: Excalibur");
        assertTrue(results.contains("Enter a card position (1 to 3) to add to the attack, or type 'quit' to finish attack setup:"), "Game should prompt Player P3 to enter the position of a card to include in the attack or ‘quit’");
        assertTrue(results.contains("Player P3 built an attack with the following cards: [Excalibur]"), "'Quit' was entered so the card used for this attack should be displayed");
        assertTrue(results.contains("Player P3's attack was successful. Attack: 30 vs Stage: 25"), "The game should resolve the attack against the current stage: attack was successful");

        assertFalse(questResolvedFailed, "Participant(s) succeeded to resolve the quest");
        assertEquals(4, game.getAdventureDiscardDeck().getSize(), "The cards used for the attack of the quest should be discarded (by both Player P1 and Player P3)");
    }
}