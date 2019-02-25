package java.learning.self.day06.duoTaiDemo2;

public class DuoTaiDemo2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NoteBook nb = new NoteBook();
		nb.run();
		nb.useUSB(null);
		nb.useUSB(new Mouse());
		nb.useUSB(new KeyBoard());
		nb.useUSB(new Audio());
	}

}
