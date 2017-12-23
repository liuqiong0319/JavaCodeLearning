package learning.self.day06.duoTaiDemo2;

public class NoteBook {
	public void run() {
		System.out.println("noteBook run");

	}

	public void useUSB(Equipment eq) {
		if (eq instanceof USB) {
			USB a = (USB) eq;
			a.open();
			a.close();
		} else if (eq instanceof DVI) {
			DVI a = (DVI) eq;
			a.open();
			a.close();
		}

	}

}
