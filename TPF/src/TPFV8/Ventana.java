package TPFV8;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Ventana extends JFrame{

	public Ventana()
	{
		setTitle("Programación Concurrente");
		setSize(1126,728);
		setLocationRelativeTo(null);
		add(new Panel());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Ventana();
	}
}
