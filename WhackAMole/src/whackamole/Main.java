package whackamole;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class Main extends Frame {

	Garden garden;
	public static Label vegetables_count = new Label();

	private static Main SINGLE_INSTANCE = null;

	public static Main getInstance() {
		if (SINGLE_INSTANCE == null) {
			synchronized (Main.class) {
				SINGLE_INSTANCE = new Main();
			}
		}
		return SINGLE_INSTANCE;
	}

	private Main() throws HeadlessException {
		super("Whack a mole");
		Button start = new Button("Start");
		Panel panel = new Panel();
		panel.setLayout(new GridLayout(0, 1));
		Label difficulty = new Label("Difficulty");
		Label vegetables = new Label("Vegetables");
		Panel north = new Panel();
		north.setLayout(new GridLayout(0, 1));
		Panel south = new Panel();
		south.setLayout(new GridLayout(0, 1));
		vegetables.setFont(new Font("Arial", Font.BOLD, 20));
		vegetables_count.setFont(new Font("Arial", Font.BOLD, 15));

		difficulty.setFont(new Font("Arial", Font.BOLD, 20));
		CheckboxGroup diff = new CheckboxGroup();

		Checkbox easy = new Checkbox("easy", false, diff);
		Checkbox medium = new Checkbox("medium", true, diff);
		Checkbox hard = new Checkbox("hard", false, diff);

		panel.setBackground(Color.WHITE);
		north.add(difficulty, BorderLayout.NORTH);
		south.add(vegetables);
		south.add(vegetables_count);
		north.add(start, BorderLayout.SOUTH);
		north.add(easy);
		north.add(medium);
		north.add(hard);
		panel.add(north);
		panel.add(south);

		garden = new Garden(4, 4);
		add(panel, BorderLayout.EAST);
		add(garden, BorderLayout.CENTER);
		setVisible(true);
		setSize(800, 500);

		start.addActionListener((ae) -> {

			String sign = start.getLabel();
			int wait = 0;
			int steps = 0;
			switch (diff.getSelectedCheckbox().getLabel()) {
			case "easy":
				wait = 1000;
				steps = 10;
				break;
			case "medium":
				wait = 750;
				steps = 8;
				break;
			case "hard":
				wait = 500;
				steps = 6;
				break;

			}
			if (sign == "Start") {
				garden.setWaitTime(wait);
				garden.setSteps(steps);
				garden.start();
				vegetables_count.setText("" + garden.getVegetables());
				start.setLabel("Stop");
			} else {
				garden.stop();
				start.setLabel("Start");
			}

		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				// garden.dispose();
				garden.stop();
				dispose();
			}
		});
	}

	public static void main(String[] args) {
		getInstance();
	}

}
