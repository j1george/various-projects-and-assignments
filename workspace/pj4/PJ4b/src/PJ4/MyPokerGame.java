package PJ4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Ref: http://en.wikipedia.org/wiki/Video_poker
 *      http://www.google.com/ig/directory?type=gadgets&url=www.labpixies.com/campaigns/videopoker/videopoker.xml
 *
 *
 * Short Description and Poker rules:
 *
 * Video poker is also known as draw poker.
 * The dealer uses a 52-card deck, which is played fresh after each currentHand.
 * The player is dealt one five-card poker currentHand.
 * After the first draw, which is automatic, you may hold any of the cards and draw
 * again to replace the cards that you haven't chosen to hold.
 * Your cards are compared to a table of winning combinations.
 * The object is to get the best possible combination so that you earn the highest
 * payout on the bet you placed.
 *
 * Winning Combinations
 *  
 * 1. Jacks or Better: a pair pays out only if the cards in the pair are Jacks,
 *      Queens, Kings, or Aces. Lower pairs do not pay out.
 * 2. Two Pair: two sets of pairs of the same card denomination.
 * 3. Three of a Kind: three cards of the same denomination.
 * 4. Straight: five consecutive denomination cards of different suit.
 * 5. Flush: five non-consecutive denomination cards of the same suit.
 * 6. Full House: a set of three cards of the same denomination plus
 *      a set of two cards of the same denomination.
 * 7. Four of a kind: four cards of the same denomination.
 * 8. Straight Flush: five consecutive denomination cards of the same suit.
 * 9. Royal Flush: five consecutive denomination cards of the same suit,
 *      starting from 10 and ending with an ace
 *
 */
/* This is the main poker game class.
 * It uses Decks and Card objects to implement poker game.
 * Please do not modify any data fields or defined methods
 * You may add new data fields and methods
 * Note: You must implement defined methods
 */
public class MyPokerGame {

	// default constant values
	private static final int startingBalance = 100;
	private static final int numberOfCards = 5;
	// default constant payout value and currentHand types
	private static final int[] multipliers = { 1, 2, 3, 5, 6, 9, 25, 50, 250 };
	private static final String[] goodHandTypes = { "Royal Pair", "Two Pairs",
			"Three of a Kind", "Straight", "Flush        ", "Full House",
			"Four of a Kind", "Straight Flush", "Royal Flush" };
	// must use only one deck
	private static final Decks oneDeck = new Decks(1);
	// holding current poker 5-card hand, balance, bet
	private List<Card> currentHand;
	private int balance;
	private int bet;

	/**
	 * default constructor, set balance = startingBalance
	 */
	public MyPokerGame() {
		this(startingBalance);
	}

	/**
	 * constructor, set given balance
	 */
	public MyPokerGame(int balance) {
		this.balance = balance;
	}

	/**
	 * This display the payout table based on multipliers and goodHandTypes
	 * arrays
	 */
	private void showPayoutTable() {
		System.out.println("\n\n");
		System.out.println("Payout Table              Multiplier   ");
		System.out.println("=======================================");
		int size = multipliers.length;
		for (int i = size - 1; i >= 0; i--) {
			System.out.println(goodHandTypes[i] + "\t|\t" + multipliers[i]);
		}
		System.out.println("\n\n");
	}

	/**
	 * Check current currentHand using multipliers and goodHandTypes arrays Must
	 * print yourHandType (default is "Sorry, you lost") at the end of function.
	 * This can be checked by testCheckHands() and main() method.
	 */
	private void checkHands() {
		// implement this method!
		int rank = 0;
		String ranks;

		if (isRoyalPair() == true) {
			rank = 1;
		} else if (isTwoPair() == true) {
			rank = 2;
		}
		if (isThreeOfAKind() == true) {
			rank = 3;
		}

		if (isStraight() == true) {
			rank = 4;
		}
		if (isFlush() == true) {
			rank = 5;
		}
		if (isFullHouse() == true) {
			rank = 6;
		}
		if (isFourOfAKind() == true) {
			rank = 7;
		}
		if (isStraightFlush() == true) {
			rank = 8;
		}
		if (isRoyalFlush() == true) {
			rank = 9;
		}

		rank -= 1;
		if (rank < 0) {
			ranks = "Sorry, you lost!";
		} else {
			ranks = goodHandTypes[rank];
		}

		System.out.println("" + ranks);

		switch (ranks) {
		case "1":
			this.balance += (this.bet * multipliers[0]);
			break;
		case "2":
			this.balance += (this.bet * multipliers[1]);
			break;
		case "3":
			this.balance += (this.bet * multipliers[2]);
			break;
		case "4":
			this.balance += (this.bet * multipliers[3]);
			break;
		case "5":
			this.balance += (this.bet * multipliers[4]);
			break;
		case "6":
			this.balance += (this.bet * multipliers[5]);
			break;
		case "7":
			this.balance += (this.bet * multipliers[6]);
			break;
		case "8":
			this.balance += (this.bet * multipliers[7]);
			break;
		case "9":
			this.balance += (this.bet * multipliers[8]);
			break;
		default:
			break;
		}

	}

	/**
	 * *********************************************** add new private methods
	 * here ....
	 * 
	 ************************************************ 
	 */
	private boolean isStraight() {
		return false;

	}

	private boolean isFlush() {

		for (int i = 0; i < numberOfCards; i++) {
			if (currentHand.get(i).getSuit() != currentHand.get(i + 1)
					.getSuit()) {
				return false;
			}
		}

		return true;
	}

	private boolean isStraightFlush() {
		if (isStraight() == true && isFlush() == true) {
			return true;
		}
		return false;
	}

	private boolean isRoyalFlush() {
		if (isFlush() == false || isStraight() == false) {
			return false;
		} else {
			if (currentHand.get(0).getRank() == 10) {
				return true;
			}
			return false;
		}
	}

	private boolean isFourOfAKind() {
		return false;
	}

	private boolean isFullHouse() {
		if (isThreeOfAKind() == true && isOnePair() == true) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isThreeOfAKind() {
		return false;
	}

	private boolean isTwoPair() {
		// check if it is four of a kind or two pair))
		if (isFourOfAKind() == true) {
			return false;
		}
		int numberOfPair = 0;
		int counter = 1;

		// implement moore
		return false;
	}

	private boolean isOnePair() {
		return false;
	}

	public boolean isRoyalPair() {
		return false;
	}

	@SuppressWarnings("resource")
	public void play() {
		/**
		 * The main algorithm for single player poker game
		 * 
		 * Steps: showPayoutTable()
		 * 
		 * ++ show balance, get bet verify bet value, update balance reset deck,
		 * shuffle deck, deal cards and display cards ask for position of cards
		 * to keep get positions in one input line update cards check hands,
		 * display proper messages update balance if there is a payout if
		 * balance = O: end of program else ask if the player wants to play a
		 * new game if the answer is "no" : end of program else :
		 * showPayoutTable() if user wants to see it goto ++
		 */
		// implement this method!

		Scanner input = null;
		try {
			input = new Scanner(System.in);

			List<Card> keepCard = new ArrayList<Card>();
			int counter = 0;
			boolean newGame = true;
			boolean rightBet = false;
			while (newGame) {
				oneDeck.shuffle();

				showPayoutTable();
				System.out.println("Balance:" + balance + "\n");
				while (!rightBet) {
					System.out.println("Enter bet:");
					bet = Integer.parseInt(input.nextLine());
					if (bet > balance) {
						System.out.println("insufficient fund!");
						rightBet = false;
					} else {
						rightBet = true;
					}

				}

				balance = balance - bet;
				try {
					currentHand = oneDeck.deal(5);
				} catch (PlayingCardException e) {
					System.out.println("Exception dealing a new hand"
							+ e.getMessage());
				}
				System.out.println("" + currentHand.toString());
				System.out.println("Enter positions to keep:");
				if (input.hasNext()) {

					String s = input.nextLine();
					if (!(input.nextLine() == "0")) {
						input = new Scanner(s);
						input = input.useDelimiter("\\s*");

						while (input.hasNext()) {
							keepCard.add(currentHand.get((input.nextInt()) - 1));
							counter++;
						}
					}
				}
				currentHand = keepCard;
				try {
					currentHand.addAll(oneDeck.deal(5 - counter));
				} catch (PlayingCardException e) {
					System.out.println("Exception dealing the second hand"
							+ e.getMessage());
				}

				System.out.println("" + currentHand.toString());
				// checkHands();
				System.out.println("Your balance: " + balance
						+ " you want another game y/n ?");

				String s = input.nextLine();

				if (balance == 0) {
					newGame = false;
					break;
				}
				if (s == "y") {
					newGame = true;
				} else {
					newGame = false;
				}

				System.out.println("Want to see payout table ? (y/n)");
				s = input.nextLine();
				if (s == "y") {
					showPayoutTable();
				}
				oneDeck.reset();
			}
		} finally {
			if (input != null)
				input.close();
		}
		System.out.println("Bye");

	}

	public void testCheckHands() {
		try {
			currentHand = new ArrayList<Card>();

			// set Royal Flush
			currentHand.add(new Card(3, 2));
			currentHand.add(new Card(4, 3));
			currentHand.add(new Card(5, 3));
			currentHand.add(new Card(6, 3));
			currentHand.add(new Card(7, 3));
			System.out.println(currentHand);
			checkHands();
			System.out.println("-----------------------------------");

			// set Straight Flush
			currentHand.set(0, new Card(9, 3));
			System.out.println(currentHand);
			checkHands();
			System.out.println("-----------------------------------");

			// set Straight
			currentHand.set(4, new Card(8, 1));
			System.out.println(currentHand);
			checkHands();
			System.out.println("-----------------------------------");

			// set Flush
			currentHand.set(4, new Card(5, 3));
			System.out.println(currentHand);
			checkHands();
			System.out.println("-----------------------------------");

			// "Royal Pair" , "Two Pairs" , "Three of a Kind", "Straight",
			// "Flush       ",
			// "Full House", "Four of a Kind", "Straight Flush", "Royal Flush"
			// };

			// set Four of a Kind
			currentHand.clear();
			currentHand.add(new Card(8, 3));
			currentHand.add(new Card(8, 0));
			currentHand.add(new Card(12, 3));
			currentHand.add(new Card(8, 1));
			currentHand.add(new Card(8, 2));
			System.out.println(currentHand);
			checkHands();
			System.out.println("-----------------------------------");

			// set Three of a Kind
			currentHand.set(4, new Card(11, 3));
			System.out.println(currentHand);
			checkHands();
			System.out.println("-----------------------------------");

			// set Full House
			currentHand.set(2, new Card(11, 1));
			System.out.println(currentHand);
			checkHands();
			System.out.println("-----------------------------------");

			// set Two Pairs
			currentHand.set(1, new Card(9, 1));
			System.out.println(currentHand);
			checkHands();
			System.out.println("-----------------------------------");

			// set Royal Pair
			currentHand.set(0, new Card(3, 1));
			System.out.println(currentHand);
			checkHands();
			System.out.println("-----------------------------------");

			// non Royal Pair
			currentHand.set(2, new Card(3, 3));
			System.out.println(currentHand);
			checkHands();
			System.out.println("-----------------------------------");
		} catch (Exception e) {
			System.out.println("exception" + e.getMessage());
		}
	}

	/* Quick testCheckHands() */
	public static void main(String args[]) {
		MyPokerGame mypokergame = new MyPokerGame();
		mypokergame.testCheckHands();
	}
}

/**
 * Action handler handles the actions taken on by players (ex. check, raise,
 * fold)
 * 
 */
class ActionHandler {

	private Hand hand;
	private Table table;
	private List<Player> players;
	private int pot;
	private Integer[] amountInvested;
	private boolean isGameOver, isFolded;

	public ActionHandler(Table newTable) {
		table = newTable;
		players = table.getPlayers();
		hand = new Hand(players);
		pot = table.getSb() + table.getBb();
		amountInvested = new Integer[2];
		amountInvested[0] = table.getSb();
		amountInvested[1] = table.getBb();
		isGameOver = false;
		isFolded = false;
	}

	public void handleAction(String act) {
		String[] action = act.split(" ");

		if (action[0].equals("Fold")) {
			handleFold();
		} else if (action[0].equals("Call")) {
			handleCall(Integer.parseInt(action[1]));
		} else if (action[0].equals("Bet")) {
			handleBet(Integer.parseInt(action[1]));
		} else if (action[0].equals("Raise")) {
			handleRaise(Integer.parseInt(action[1]));
		} else if (action[0].equals("Check")) {
			handleCheck();
		}
		hand.addAction(act);
		handleShowDownAndNewRound();
	}

	public void handleCheck() {

		amountInvested[0] = 0;
		amountInvested[1] = 0;
	}

	public void handleFold() {

		// The other player gets the pot
		players.get(nextTurn()).add(pot);
		isFolded = true;
	}

	public void handleCall(int amount) {
		Player plyrWhoCalled = players.get(hand.getTurn());
		pot += amount;
		plyrWhoCalled.take(amount);
		if (hand.getStreet() == 0 && hand.getAbsTurn() == 0) {
			amountInvested[0] = table.getBb();
		} else {
			amountInvested[0] = 0;
			amountInvested[1] = 0;
		}
	}

	public void handleBet(int amount) {
		Player plyrWhoBet = players.get(hand.getTurn());
		pot += amount;
		plyrWhoBet.take(amount);
		amountInvested[hand.getTurn()] = amount;
	}

	public void handleRaise(int amount) {
		Player plyrWhoRaised = players.get(hand.getTurn());
		pot += amount - amountInvested[hand.getTurn()];
		plyrWhoRaised.take(amount - amountInvested[hand.getTurn()]);
		amountInvested[hand.getTurn()] = amount;
	}

	public boolean isOver() {
		return isGameOver;
	}

	public boolean isAllIn() {
		return players.get(0).hasNoChips() || players.get(1).hasNoChips();
	}

	private void handleShowDownAndNewRound() {
		String lastAct = hand.getLastAct();
		if (hand.getStreet() == 4) {
			handleShowDown();
			if (isAllIn()) {
				isGameOver = true;
			} else {
				table.setUpNewRound(false);
			}
		} else if (isFolded) {
			table.setUpNewRound(true);
			// Someone went all in and other player called
		} else if (lastAct == "" && isAllIn()) {
			handleShowDown();
			if (isAllIn()) {
				isGameOver = true;
			} else {
				table.setUpNewRound(false);
			}
		}
	}

	private void handleShowDown() {
		Card[] board = table.getBoard();
		int betterHand = HandRank.compareHands(board,
				players.get(hand.getTurn()).getCards(), players.get(nextTurn())
						.getCards());
		if (betterHand == 1) {
			players.get(nextTurn()).add(pot);
		} else if (betterHand == -1) {
			players.get(hand.getTurn()).add(pot);
		} else { // Split pot
			players.get(nextTurn()).add(pot / 2);
			players.get(hand.getTurn()).add(pot / 2);
		}
		if (players.get(0).getStack() == 0 || players.get(1).getStack() == 0) {
			isGameOver = true;
			pot = 0;
		}
	}

	public String toCheckCall() {
		String last = getSlicedString(hand.getLastAct());
		String[] lstAry = last.split(" ");
		if (hand.getStreet() == 0 && hand.getAbsTurn() == 0) {
			return "Call " + table.getSb();
		} else {
			if (last.startsWith("Call") || last.startsWith("Check")
					|| last.equals("")) {
				return "Check";
			} else if (last.startsWith("Bet")) {
				return "Call " + Integer.parseInt(lstAry[1]);
			} else {
				return "Call "
						+ (Integer.parseInt(lstAry[1]) - amountInvested[hand
								.getTurn()]);
			}
		}
	}

	public String toBetRaise() {
		String last = getSlicedString(hand.getLastAct());

		if (hand.getStreet() == 0
				&& (hand.getAbsTurn() == 0 || (last.split(" ")[0]
						.equals("Call") && hand.getAbsTurn() == 1))) {
			return "Raise " + (table.getBb() * 2);
		} else {
			if (amountInvested[nextTurn()] == 0) {
				return "Bet " + (table.getBb());
			} else { // Previously raised pot
				int diff = Integer.parseInt(last.split(" ")[1])
						- amountInvested[hand.getTurn()];
				int amount = Integer.parseInt(last.split(" ")[1]); // raise amnt

				if (amount == getPlayerInitialStack(hand.getTurn())
						|| amount == getPlayerInitialStack(nextTurn())) {
					return null;
				} else if ((amount + diff > (getPlayerInitialStack(hand
						.getTurn())) || amount + diff > (getPlayerInitialStack(nextTurn())))) {
					return "Raise "
							+ Math.min(getPlayerInitialStack(hand.getTurn()),
									getPlayerInitialStack(nextTurn()));
				} else {
					return "Raise " + (amount + diff);
				}
			}
		}
	}

	public int maxRaiseAmount() {
		if (toBetRaise() == null) {
			return 0;
		}
		return Math.min(getPlayerInitialStack(0), getPlayerInitialStack(1));
	}

	public int getPlayerInitialStack(int i) {
		return table.getPlayer(i).getStack()
				+ table.handler().playerAmountInvested(i);
	}

	public int playerAmountInvested(int i) {
		return amountInvested[i];
	}

	private String getSlicedString(String s) {
		if (s.length() == 0) {
			return "";
		}
		return s.substring(s.indexOf(" ") + 1);
	}

	public int nextTurn() {
		return (hand.getTurn() + 1) % 2;
	}

	public int pot() {
		return pot;
	}

	public int playerInvestment() {

		return amountInvested[table.getPlayerPos()];
	}

	public int computerInvestment() {
		return amountInvested[table.getComputerPos()];
	}

	public int getTurn() {
		return hand.getTurn();
	}

	public int getAbsTurn() {
		return hand.getAbsTurn();
	}

	public int getStreet() {
		return hand.getStreet();
	}

}
