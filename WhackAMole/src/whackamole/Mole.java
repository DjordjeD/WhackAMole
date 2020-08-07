package whackamole;

import java.awt.Color;
import java.awt.Graphics;

public class Mole extends Animal {

	public Mole(Hole hole_animal) {
		super(hole_animal);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		Graphics g = hole_animal.getGraphics();
		int steps = hole_animal.getSteps();
		g.setColor(Color.DARK_GRAY);
		g.fillOval(
				(hole_animal.getWidth() - hole_animal.getWidth() / hole_animal.getSteps()
						* (hole_animal.getSteps() - hole_animal.getCurrsteps())) / 2,
				(hole_animal.getHeight() - hole_animal.getHeight() / hole_animal.getSteps()
						* (hole_animal.getSteps() - hole_animal.getCurrsteps())) / 2,
				hole_animal.getWidth() / hole_animal.getSteps() * (hole_animal.getSteps() - hole_animal.getCurrsteps()),
				hole_animal.getHeight() / hole_animal.getSteps() * (hole_animal.getSteps() - hole_animal.getCurrsteps())

		);

	}

	@Override
	public void clickedEffect() {
		// TODO Auto-generated method stub
		synchronized (hole_animal) {
			hole_animal.getHole_thr().interrupt();
			hole_animal.setHasAnimal(false);
		}
		hole_animal.repaint();
		hole_animal.getGarden().holes_taken--;
		// notify();

	}

	@Override
	public void timeEffect() {
		// TODO Auto-generated method stub
		hole_animal.getGarden().holes_taken--;
		// notify();
		hole_animal.repaint();
		hole_animal.getGarden().timeRanOut();

	}

}
