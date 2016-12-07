import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;

public class gui extends JPanel {

	/**
	 * Create the panel.
	 */
	public gui() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 11, 552, 466);
		add(panel);
		
		JButton btnNearestNeighbour = new JButton("Nearest Neighbour");
		btnNearestNeighbour.setBounds(559, 60, 98, 60);
		add(btnNearestNeighbour);

	}
}
