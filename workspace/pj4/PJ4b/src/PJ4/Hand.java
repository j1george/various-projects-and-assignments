package PJ4;
import java.util.ArrayList;
import java.util.List;

public class Hand {
	private ArrayList<ArrayList<String>> hand;
	private List<Player> players;

	public Hand(List<Player> players2) {
		hand = new ArrayList<ArrayList<String>>();
		hand.add(new ArrayList<String>());
		players = players2;
	}

	public int getStreet() {
		return hand.size() - 1;
	}

	public int getTurn() {
		return getAbsTurn() % 2;
	}

	public int getAbsTurn() {
		if (getStreet() == 0) {
			return hand.get(0).size();
		} else {
			return (hand.get(getStreet()).size() + 1);
		}
	}

	public void addAction(String act) {
		String action = act.split(" ")[0];
		if (action.equals("Check")) {
			if (getStreet() > 0 && getTurn() == 1) { // bb check
				_addAction(act);
			} else { // button check
				_addAction(act);
				hand.add(new ArrayList<String>());
			}
		} else if (action.equals("Call")) {
			if (getStreet() == 0 && getAbsTurn() == 0) {
				_addAction(act);
			} else {
				_addAction(act);
				hand.add(new ArrayList<String>());
			}
		} else { // Bet Raise Fold
			_addAction(act);
		}
	}

	private void _addAction(String action) {
		String username = players.get(getTurn()).getUsername();
		hand.get(getStreet()).add(username + " " + action);
	}

	public Integer[] _getLastAct() {
		int size = hand.get(getStreet()).size();
		Integer[] streetTurn = new Integer[2];
		streetTurn[0] = getStreet();
		streetTurn[1] = size - 1;
		return streetTurn;
	}

	public String getLastAct() {
		Integer[] streetTurn = _getLastAct();
		if (streetTurn[1] < 0) { // There was no last act in this street;
			return "";
		}
		return hand.get(streetTurn[0]).get(streetTurn[1]);
	}

	public String getSecLastAct() {
		Integer[] streetTurn = _getLastAct();
		if ((streetTurn[1] - 1) < 0) { // No second last act in this street
			return "";
		} else {
			return hand.get(streetTurn[0]).get(streetTurn[1] - 1);
		}
	}

	public String toString() {
		int i = 0;
		String result = "";

		for (ArrayList<String> street : hand) {

			if (i == 0) {
				result += "Preflop:\n";
			} else if (i == 1) {
				result += "Flop:\n";
			} else if (i == 2) {
				result += "Turn:\n";
			} else if (i == 3) {
				result += "River:\n";
			}

			for (String action : street) {
				result += action + "\n";
			}
			result += "\n\n";
			i++;
		}
		return result;
	}
}
