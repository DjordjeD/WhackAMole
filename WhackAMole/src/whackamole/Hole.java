package whackamole;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Hole extends Canvas implements Runnable {

	private Garden garden;
	private Animal animal;
	private Thread hole_thr;
	private boolean hasAnimal;
	private int steps;
	private int currsteps;

	public Hole(Garden garden) {
		this.garden = garden;

		setSize(new Dimension((this.garden.getWidth() / garden.number_of_holes) - 10,
				(this.garden.getHeight() / garden.number_of_holes) - 10));
		setBackground(new Color(150, 75, 0));
		setVisible(true);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if (hasAnimal) {
					animal.clickedEffect();
				}
			}
		});

	}

	public synchronized void start(Animal a) {
		garden.setWaitTime((int) (garden.getWaitTime() - garden.getWaitTime() * 0.01));
		animal = a;
		hole_thr = new Thread(this);
		hole_thr.start();
		this.hasAnimal = true;
		currsteps = steps;
		garden.setHoles_taken(garden.getHoles_taken() + 1);
	}

	public synchronized void stop() {
		hasAnimal = false;
		hole_thr.interrupt();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!Thread.interrupted()) {
			try {
				synchronized (this) {
					if (!hasAnimal)
						this.wait();
				}
				Thread.sleep(100);
				if (currsteps != 0) {
					System.out.println(garden.holes_taken);
					try {
						animal.draw();
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("s");
					}
					currsteps--;

				} else {
					Thread.sleep(2000);

					hasAnimal = false;
					hole_thr.interrupt();
//					synchronized (this) {
//						notify();
//					}
					animal.timeEffect();
					// animal = null;
				}

			} catch (InterruptedException e) {
				// TODO: handle exception
				hole_thr.interrupt();
			}
		}
	}

	public Garden getGarden() {
		return garden;
	}

	public void setGarden(Garden garden) {
		this.garden = garden;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Thread getHole_thr() {
		return hole_thr;
	}

	public void setHole_thr(Thread hole_thr) {
		this.hole_thr = hole_thr;
	}

	public boolean isHasAnimal() {
		return hasAnimal;
	}

	public void setHasAnimal(boolean hasAnimal) {
		this.hasAnimal = hasAnimal;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public int getCurrsteps() {
		return currsteps;
	}

	public void setCurrsteps(int currsteps) {
		this.currsteps = currsteps;
	}

}
