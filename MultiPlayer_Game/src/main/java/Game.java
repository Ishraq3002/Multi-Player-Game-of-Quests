import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Deck adventureDeck;
    private Deck eventDeck;
    private Deck adventureDiscardDeck;
    private Deck eventDiscardDeck;
    private ArrayList<Player> players;
    private ArrayList<Player> winners;
    private boolean gameRunning; //game state field

    public Game(){
        adventureDeck = new Deck();
        eventDeck = new Deck();
        adventureDiscardDeck = new Deck();
        eventDiscardDeck = new Deck();
        players = new ArrayList<>();
        winners = new ArrayList<>();
        gameRunning = true;  //game starts in a running state
    }

    //Setting up the adventure and event decks
    public void setUpDecks(){
        setUpAdventureDeck();
        setUpEventDeck();

        //shuffling the decks
        eventDeck.shuffle();
        adventureDeck.shuffle();
    }

    //Setting up the adventure deck
    public void setUpAdventureDeck() {
        int[] foeCardValues = {5, 10, 15, 20, 25, 30, 35, 40, 50, 70};
        int[] foeCardCounts = {8, 7, 8, 7, 7, 4, 4, 2, 2, 1};
        String[] weaponNames = {"Dagger", "Sword", "Horse", "Battle-Axe", "Lance", "Excalibur"};
        int[] weaponValues = {5, 10, 10, 15, 20, 30};
        int[] weaponCounts = {6, 16, 12, 8, 6, 2};

        for (int i = 0; i < foeCardValues.length; i++) {
            adventureDeck.addMultipleCards(foeCardCounts[i], new Card(Card.CardType.Foe, foeCardValues[i]));
        }
        for (int i = 0; i < weaponNames.length; i++) {
            adventureDeck.addMultipleCards(weaponCounts[i], new Card(Card.CardType.Weapon, weaponNames[i], weaponValues[i]));
        }
    }


    //Setting up the event deck
    public void setUpEventDeck() {
        int[] questCardValues = {2, 3, 4, 5};
        int[] questCardCounts = {3, 4, 3, 2};

        for (int i = 0; i < questCardValues.length; i++) {
            eventDeck.addMultipleCards(questCardCounts[i], new Card(Card.CardType.Quest, questCardValues[i]));
        }

        eventDeck.addMultipleCards(1, new Card(Card.CardType.E, "Plague"));
        eventDeck.addMultipleCards(2, new Card(Card.CardType.E, "Queen's Favor"));
        eventDeck.addMultipleCards(2, new Card(Card.CardType.E, "Prosperity"));
    }

    public Deck getEventDeck() {
        return eventDeck;
    }

    public Deck getAdventureDeck() {
        return adventureDeck;
    }

    public Deck getEventDiscardDeck() {
        return eventDiscardDeck;
    }

    public Deck getAdventureDiscardDeck() {
        return adventureDiscardDeck;
    }


    // Asks user to input ids for the players
    public void setUpPlayerIDs(){
        Scanner scanner = new Scanner(System.in);
        for (int i = 1; i <= 4; i++) {
            System.out.println("Enter username for Player " + i + ":");
            String userName = scanner.nextLine();
            addPlayer(new Player(userName));
        }
        scanner.close();
    }

    public void addPlayer(Player newPlayer){
        players.add(newPlayer);
    }

    // Distributes 12 cards to players at the beginning of the game
    public void distributeAdventureCardsToPlayers(int count){
        for (Player player: players){
            drawAdventureCards(count, player);
        }
    }

    public Deck drawAdventureCards (int count, Player player){
        //checks if adventure deck is empty
        replenishDeckIfNeeded (adventureDeck, adventureDiscardDeck);

        // Draw the necessary amount of cards, considering deck size
        Deck drawnCards = new Deck();  // Temporary deck to hold drawn cards
        int availableCardsInDeck = adventureDeck.getSize();

        if (availableCardsInDeck < count) {
            // Draw as many as possible from the current deck
            drawnCards.addAllCards(adventureDeck.drawCards(availableCardsInDeck));

            // Replenish deck and draw the remaining cards needed
            replenishDeckIfNeeded(adventureDeck, adventureDiscardDeck);
            drawnCards.addAllCards(adventureDeck.drawCards(count - availableCardsInDeck));
        } else {
            // Draw all cards in one go if enough are available
            drawnCards.addAllCards(adventureDeck.drawCards(count));
        }

        // Add the drawn cards to the player's hand
        player.addAllCards(drawnCards);
        return drawnCards;
    }

    public void replenishDeckIfNeeded (Deck deck, Deck discardDeck) {
        //The respective discard card is reshuffled and becomes the main deck
        if (deck.isEmpty() && !discardDeck.isEmpty()) {
            discardDeck.shuffle();
            deck.addAllCards(discardDeck);
            discardDeck.getDeck().clear(); // Reset discard deck
        }
    }

    public ArrayList<Player> checkForWinners() {
        winners.clear();
        for (Player player: players){
            if (player.getShields() >= 7){
                winners.add(player);
            };
        }
        return winners;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrintWriter output = new PrintWriter(System.out, true);

        Game game = new Game();
        game.setUpDecks();
        //game.setUpPlayerIDs();

        Player player1 = new Player("P1");
        Player player2 = new Player("P2");
        Player player3 = new Player("P3");
        Player player4 = new Player("P4");

        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
        game.addPlayer(player4);

        game.distributeAdventureCardsToPlayers(12);

        while (game.isGameRunning()) {
            for (Player player: game.players) {
                game.turn(player, scanner, output);
            }
        }
    }

    public void displayWinners(PrintWriter output) {
        if (!winners.isEmpty()) {
            if (winners.size() == 1) {
                output.println("1 Player has won the game!");
            } else {
                output.println(winners.size() + " Players have won the game!");
            }
            for (Player player: winners){
                output.println("Winner: " + player);
            }
            setGameRunning(false);
        }
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void setGameRunning(boolean running){
        gameRunning = running;
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    public Card drawEventCard(Player player, PrintWriter output) {
        // Check if the event deck has enough cards
        replenishDeckIfNeeded(eventDeck, eventDiscardDeck);

        Card drawnCard = eventDeck.drawCard();
        output.println(player + " has drawn " + drawnCard);
        return drawnCard;
    }

    public String displayHandAndPrompt(Player player, String prompt, Scanner input, PrintWriter output) {
        output.println("\n" + player + "'s hand: " + player.getHand());
        output.println(prompt);
        return input.nextLine().trim().toLowerCase();
    }

    public void trimPlayerHand(Player player, Scanner input, PrintWriter output){
        if (player.getHandSize() <= 12) {
            return; // If the hand size is already within limits, no need to trim.
        }

        output.println("\n" + player + "'s hand is too large. It needs to be trimmed to a maximum of 12 cards:");
        while (player.getHandSize() > 12) {
            String prompt = "Enter a card position (1 to " + player.getHandSize() + ") to remove:";
            String response = displayHandAndPrompt(player, prompt, input, output);

            int index = parseCardIndex(response, player, output); //checking if user entered a valid index
            if (index != -1) {
                Card selectedCard = player.peakAtCard(index);
                player.removeCard(index); // Removing from player's hand
                adventureDiscardDeck.addCard(selectedCard); // Adding to the adventure discard deck
                output.println("Removed " + selectedCard + " from your hand. " + player + "'s updated hand: " + player.getHand());
            }
        }
    }

    private int parseCardIndex(String response, Player player, PrintWriter output) {
        try {
            int index = Integer.parseInt(response) - 1;
            if (index >= 0 && index < player.getHandSize()) {
                return index;
            }
        } catch (NumberFormatException e) {
            output.println("Invalid input. Please enter a valid card position.");
        }
        return -1;
    }

    public void processCardActions(Card card, Player player, Scanner input, PrintWriter output){
        if (card.getType() == Card.CardType.E) {
            processECard(card, player, input, output);   // Processing the actions by the specific E card
            eventDiscardDeck.addCard(card);   // Adding the card to the event discard deck
        } else if (card.getType() == Card.CardType.Quest) {
            processQuestTurn(card, player, input, output);
        }
    }

    void processECard(Card card, Player currentPlayer, Scanner input, PrintWriter output){
        switch (card.getName()) {
            case "Plague":
                // Player loses 2 shields
                currentPlayer.updateShields(-2);
                output.println(currentPlayer + " has lost 2 shields\n");
                break;
            case "Queen's Favor":
                // Player draws 2 adventure cards
                output.println(currentPlayer + " has drawn 2 adventure cards\n");
                drawAdventureCards(2, currentPlayer);
                trimPlayerHand(currentPlayer, input, output); // In case, hand needs to be trimmed
                break;

            case "Prosperity":
                // All players draw 2 adventure cards
                output.println("All players have drawn 2 adventure cards\n");
                distributeAdventureCardsToPlayers(2);
                trimAllPlayers(currentPlayer, input, output); // In case, all the hands needs to be trimmed
                break;
            default:
                //System.out.println("Unexpected error: " + card.getName());
                break;
        }
    }

    public void trimAllPlayers(Player currentPlayer, Scanner scanner, PrintWriter output) {
        int index = players.indexOf(currentPlayer);
        int originalIndex = index;  // Asking all players to trim their hand, starting with one who drew the Prosperity card
        do {
            Player player = players.get(index);
            trimPlayerHand(player, scanner, output);
            index = (index + 1) % players.size();

            if (index == originalIndex) {  // If we've come full circle, stop asking
                break;
            }
        } while (true);  // Continue until we loop back to the original index
    }

    public boolean yesOrNoResponse(Scanner input, PrintWriter output) {
        while (true) {
            String response = input.nextLine().trim().toLowerCase();

            switch (response) {
                case "yes":
                    return true;
                case "no":
                    return false;
                default:
                    output.println("Invalid response. Please enter 'Yes' or 'No'.");
                    break; // Continue looping for valid input
            }
        }
    }

    public Player findASponsor(Card card, Player currentPlayer, Scanner input, PrintWriter output){
        int stagesNum = card.getValue(); // The number of stages in the Quest
        int startIndex = players.indexOf(currentPlayer); // Index of the player who drew the Quest Card
        int currentIndex = startIndex;

        do {
            Player player = players.get(currentIndex);
            output.println("\n" + player + ": Do you want to sponsor a quest with " + stagesNum + " stages? (Yes/No)");
            boolean wantsToSponsor = yesOrNoResponse(input, output);

            if (wantsToSponsor) { // Player has agreed to sponsor
                if (player.canPlayerSponsor(stagesNum)) { // Check if the player can effectively sponsor
                    output.println("\nA sponsor has been found!");
                    output.println(player + " would like to sponsor a quest with " + stagesNum + " stages" + "\n");
                    return player; // This player becomes the sponsor of the quest
                } else {
                    output.println(player + " cannot sponsor a valid quest. Hand is not sufficient enough");
                }
            }
            currentIndex = (currentIndex + 1) % players.size();
        } while (startIndex != currentIndex);  // Asking every player starting from the one who drew the Quest card

        output.println("No player has agreed to sponsor the quest. The quest card will be discarded.");
        eventDiscardDeck.addCard(card); // Discard the quest card if no sponsor is found
        return null; // Return null if no sponsor is found
    }

    public boolean isValidCardForStage(Deck stage, Card card, PrintWriter output) {
        if (card.getType() == Card.CardType.Foe && stage.checkForFoe()) {
            output.println("Invalid: Only one Foe card allowed per stage.");
            return false;
        } else if (card.getType() == Card.CardType.Weapon && stage.checkForWeapon(card)) {
            output.println("Invalid: Only one " + card.getName() + " card allowed per stage.");
            return false;
        }
        return true;
    }

    public String formatStageError(Deck stage, int stageValue, int maxStageValue) {
        if (stage.isEmpty()) {
            return "A stage cannot be empty.\n";
        } else if (!stage.checkForFoe()) {
            return "Stage needs one Foe Card.\n";
        } else if (stageValue <= maxStageValue) {
            return "Insufficient value for this stage. Current stage value: " + stageValue + ", required to exceed: " + maxStageValue + ".\n";
        }
        return "Invalid stage configuration.\n";
    }

    public ArrayList<Player> checkForParticipants(Player sponsor, Scanner input, PrintWriter output) {
        ArrayList <Player> participants = new ArrayList<>();
        int startIndex = players.indexOf(sponsor);
        int currentIndex = (startIndex + 1) % players.size();  // Start from the next player immediately

        while (currentIndex != startIndex) {  // Loop until we come back to the sponsor
            Player currentPlayer = players.get(currentIndex);

            // Avoid asking the sponsor if they want to participate
            if (!currentPlayer.equals(sponsor)) {
                output.println(currentPlayer + ": Do you want to participate in the quest? (Yes/No)");
                if (yesOrNoResponse(input, output)) {  // Player has agreed to participate
                    participants.add(currentPlayer);
                }
            }

            currentIndex = (currentIndex + 1) % players.size();  // Move to the next player
        }
        return participants;
    }

    public int setUpStage(Player player, Deck stage, Scanner input, PrintWriter output, int maxStageValue) {
        int stageValue = 0;

        while (true) {
            String prompt = "Enter a card position (1 to " + player.getHandSize() + ") to add to stage" + ", or type 'quit' to finish stage setup:";
            String response = displayHandAndPrompt(player, prompt, input, output);

            if (response.equals("quit")) {
                if (stage.checkForFoe() && stageValue > maxStageValue && !stage.isEmpty()) {
                    output.println("Stage setup complete. Value: " + stageValue + ". Stage: " + stage +"\n");
                    break; // Stage setup complete
                } else {
                    output.println(formatStageError(stage, stageValue, maxStageValue));
                }
            } else {
                int index = parseCardIndex(response, player, output); //checking if user entered a valid index
                if (index != -1) {
                    Card selectedCard = player.peakAtCard(index);
                    // Validate and add card to stage
                    if (isValidCardForStage(stage, selectedCard, output)) {
                        stageValue += selectedCard.getValue(); // Increasing the value of the stage
                        stage.addCard(selectedCard);
                        player.removeCard(index);
                        output.println("Added " + selectedCard + " to the current stage: " + stage);
                    }
                }
            }
        }
        return stageValue;
    }

    public ArrayList<Deck> buildQuest(Card card, Player player, Scanner input, PrintWriter output) {
        int stages = card.getValue();
        output.println("For " + player + ":");

        // Creating an empty quest
        ArrayList<Deck> quest = new ArrayList<>();

        int maxStageValue = 0;

        for (int i = 0; i < stages; i++){
            Deck currentStage = new Deck();
            output.println("Setting up stage " + (i + 1) + " for the quest.");
            int stageValue = setUpStage(player, currentStage, input, output, maxStageValue);

            // If a valid stage is set, update the maxStageValue
            if (stageValue > maxStageValue) {
                maxStageValue = stageValue;
            } else {
                output.println("Failed to set up stage " + (i + 1) + ". Restarting the stage setup.\n");
                i--; // Retry setting up the same stage if invalid
            }
            quest.add(currentStage);
        }
        output.println("Quest setup complete. The quest is ready to be resolved.\n");
        return quest;
    }

    public boolean resolveQuest (ArrayList<Player> participants, ArrayList<Deck> quest, Scanner scanner, PrintWriter output) {
        int stageNumber = 1;
        for (Deck stage: quest) {
            if (participants.isEmpty()) {
                output.println("No participants left for the quest.");
                break;
            }
            output.println("\nParticipants for stage " + stageNumber + " of the Quest: " + participants);
            ArrayList <Player> ineligibleParticipants = new ArrayList<>();

            for (Player player: participants){
                output.println("\n" + player + "'s turn to attack in stage " + stageNumber + " of the Quest:");
                boolean continues = true;
                if (stageNumber != 1) {
                    output.println(player + ": Do you want to continue participating in the quest? (Yes/No)");
                    continues = yesOrNoResponse(scanner, output);
                }

                if (continues) {  // Player has agreed to participate
                    performStageAttack(player, stage, ineligibleParticipants, scanner, output);
                } else {
                    ineligibleParticipants.add(player);
                    output.println(player + " has chosen not to participate further.");
                }
            }
            participants.removeAll(ineligibleParticipants); // Removing the ineligible participants for the next stage
            stageNumber++;
        }

        return participants.isEmpty(); // if participants is empty, no winners of the quest
    }

    public boolean validateCardForAttack(Card card, Deck attack, PrintWriter output) {
        if (card.getType() == Card.CardType.Foe) {
            output.println("Only weapon cards can be used for attacks.");
            return false;
        }
        if (attack.checkForWeapon(card)) {
            output.println("Invalid: Only one " + card + " card allowed per attack.");
            return false;
        }
        attack.addCard(card); // Adding the card to the attack
        output.println("Added " + card + " to the current attack: " + attack);
        return true;
    }

    public void turn (Player player, Scanner input, PrintWriter output) {
        output.println("It is " + player + "'s turn:");
        Card card = drawEventCard(player, output); // Drawing an event card

        //can make this into its own function, processEventCard
        processCardActions(card, player, input, output);

        ArrayList<Player> winners = checkForWinners(); // Checking if any players won the game
        if (!winners.isEmpty()) {
            displayWinners(output);
            return;  // Exiting the method if there are winners, as the game ends or some logic stops here
        } else {
            endPlayerTurn(player, input, output);
        }
    }

    public void processQuestTurn(Card questCard, Player player, Scanner input, PrintWriter output) {
        //int stages = questCard.getValue();
        Player sponsor = findASponsor(questCard, player, input, output); // Attempt to find a sponsor for the Quest
        //rename this

        if (sponsor != null) {  // If a sponsor is found
            ArrayList<Deck> quest = buildQuest(questCard, sponsor, input, output); // The sponsor creates the quest
            ArrayList<Player> participants = checkForParticipants(sponsor, input, output); // Gather participants

            resolveQuest(participants, quest, input, output); // Resolving the Quest
            questCompleted(participants, sponsor, quest, input, output); // Displaying if there are winners

            adventureDiscardDeck.addCard(questCard); // Quest is finished, discarding the card
        }
    }

    public void endPlayerTurn(Player player, Scanner input, PrintWriter output) {
        output.println(player + "'s turn has ended. Press Enter to continue...");
        while (!input.nextLine().trim().isEmpty()) {
            output.println("Invalid input. Please press Enter to continue...");
        }
        clearConsole(output);
    }

    public void clearConsole(PrintWriter output) {
        for(int i = 0; i < 100; i++) {
            output.println();
        }
    }

    public void questWinnersPrize(ArrayList<Player> participants, int stageNumber, PrintWriter output){
        if (participants.isEmpty()) {
            output.println("\nNo one won the quest");
        } else {
            for (Player questWinner: participants){
                output.println(questWinner + " won the quest");
                questWinner.updateShields(stageNumber);
            }
        }
    }

    public void discardingSponsorCards(ArrayList<Deck> quest, Player sponsor, Scanner input, PrintWriter output) {
        int stageNumber = quest.size();
        int totalCards = 0;
        for (Deck stage: quest){
            totalCards += stage.getSize();
            adventureDiscardDeck.addAllCards(stage); // Discarding the cards used for the sponsor's quest
        }
        drawAdventureCards((totalCards+stageNumber), sponsor); // Compensating the sponsor with new cards
        trimPlayerHand(sponsor, input, output);
    }

    public Deck setUpAttack(Player player, Scanner input, PrintWriter output) {

        Deck attack = new Deck();
        output.println("\nSetting up " + player + "'s attack: ");

        while (true) {
            String prompt = "Enter a card position (1 to " + player.getHandSize() + ") to add to the attack, or type 'quit' to finish attack setup:";
            String response = displayHandAndPrompt(player, prompt, input, output);

            if ("quit".equals(response)) {
                if (attack.isEmpty()) {
                    output.println("\n" + player + " built no attack.");
                } else {
                    output.println("\n" + player + " built an attack with the following cards: " + attack);
                }
                break; // Break the loop when the user decides to finish the setup
            } else {
                int index = parseCardIndex(response, player, output); // Check if user entered a valid index
                if (index != -1) {
                    Card selectedCard = player.peakAtCard(index);
                    if (validateCardForAttack(selectedCard, attack, output)) {
                        player.removeCard(index);     // Removing the card from the player's hand
                    }
                }
            }
        }

        return attack;
    }

    public void performStageAttack(Player player, Deck stage, ArrayList<Player> ineligibleParticipants, Scanner scanner, PrintWriter output) {
        // Drawing an adventure card, trim the player's hand if needed
        Deck drawnCard = drawAdventureCards(1, player);
        output.println(player + " has drawn an adventure card: " + drawnCard.getDeck().getFirst());
        trimPlayerHand(player, scanner, output);

        Deck attack = setUpAttack(player, scanner, output); // Set up the attack
        int stageValue = stage.getStageValue();   //Value of the stage
        int attackValue = attack.getStageValue(); //Value of the quest

        if (attackValue < stageValue) {
            output.println(player + "'s attack failed. Attack: " + attackValue + " vs Stage: " + stageValue);
            ineligibleParticipants.add(player);
        } else {
            output.println(player + "'s attack was successful. Attack: " + attackValue + " vs Stage: " + stageValue);
        }

        adventureDiscardDeck.addAllCards(attack); // Discard the cards used in the attack
    }

    public void questCompleted(ArrayList<Player> participants, Player sponsor, ArrayList<Deck> quest, Scanner input, PrintWriter output) {
        int stageNumber = quest.size();
        questWinnersPrize(participants, stageNumber, output);

        discardingSponsorCards(quest, sponsor, input, output);
    }
}
