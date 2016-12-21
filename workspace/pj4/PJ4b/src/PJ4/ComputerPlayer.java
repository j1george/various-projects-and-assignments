package PJ4;
public class ComputerPlayer extends Player {

	ComputerPlayer(int startingStack, String un) {
		super(startingStack, un);
	}

	public static String makeMove(ActionHandler handler) {
		
		int street = handler.getStreet();
		
		if (handler.toBetRaise() == null) {
			return handler.toCheckCall();
		} else {
			return handler.toBetRaise();

		}
	}

}
