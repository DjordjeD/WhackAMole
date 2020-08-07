package whackamole;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.Random;

public class Garden extends Panel implements Runnable {

	Hole[][] matrix;
	private int waitTime;
	private int steps;
	private int vegetables = 100;
	private int rows_y;
	private int columns_x;
	Thread garden_thr;
	public int number_of_holes;
	public int holes_taken;
	boolean startFlag = false;

	public Garden(int i, int j) {
		setBackground(Color.GREEN);
		setVisible(true);
		setLayout(new GridLayout(i, j, 10, 10));
		this.rows_y = i;
		this.columns_x = j;
		this.number_of_holes = i * j;
		matrix = new Hole[i][j];

		for (int j2 = 0; j2 < rows_y; j2++) {
			for (int k = 0; k < columns_x; k++) {
				matrix[j2][k] = new Hole(this);
				add(matrix[j2][k]);
			}
		}
	}

	public synchronized void start() {
		garden_thr = new Thread(this);
		garden_thr.start();
		vegetables = 100;
		startFlag = true;
		for (int j2 = 0; j2 < rows_y; j2++) {
			for (int k = 0; k < columns_x; k++) {

				matrix[j2][k].repaint();

			}
		}
	}

	public synchronized void stop() {
		for (int j2 = 0; j2 < rows_y; j2++) {
			for (int k = 0; k < columns_x; k++) {
				if (matrix[j2][k].isHasAnimal())
					matrix[j2][k].stop();
			}
		}
		if (startFlag)
			garden_thr.interrupt();

	}

	public void stepOnHole(Hole h) {
		h.getAnimal().clickedEffect();

	}

	public void timeRanOut() {
		vegetables--;
		if (vegetables == 0)
			this.stop();
		Main.vegetables_count.setText("" + vegetables);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!Thread.interrupted()) {
			try {
				synchronized (this) {
					if (!startFlag)
						garden_thr.wait();
				}

				Random rand = new Random();
				boolean flag = false;
				// if (holes_taken < number_of_holes - 1) {
				while (true) {
					int temp = rand.nextInt(number_of_holes);
					for (int i = 0; i < rows_y; i++) {
						for (int j = 0; j < columns_x; j++) {
							if ((temp == 0) && !matrix[i][j].isHasAnimal()) {
								Random rand1 = new Random();
								int randint1 = rand.nextInt(5) + 1;
								if (randint1 == 1)
									matrix[i][j].start(new Bomb(matrix[i][j]));
								else
									matrix[i][j].start(new Mole(matrix[i][j]));
								flag = true;
								break;
							} else
								temp--;

						}
						if (flag == true)
							break;
					}
					if (flag)
						break;
				}
//				} else {
//					synchronized (this) {
//						garden_thr.wait();
//					}
//				}

				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				// TODO: handle exception
				garden_thr.interrupt();
			}
		}

	}

	public Thread getGarden_thr() {
		return garden_thr;
	}

	public void setGarden_thr(Thread garden_thr) {
		this.garden_thr = garden_thr;
	}

	public int getNumber_of_holes() {
		return number_of_holes;
	}

	public void setNumber_of_holes(int number_of_holes) {
		this.number_of_holes = number_of_holes;
	}

	public int getHoles_taken() {
		return holes_taken;
	}

	public void setHoles_taken(int holes_taken) {
		this.holes_taken = holes_taken;
	}

	public boolean isStartFlag() {
		return startFlag;
	}

	public void setStartFlag(boolean startFlag) {
		this.startFlag = startFlag;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				matrix[i][j].setSteps(steps);
			}
		}
		this.steps = steps;
	}

	public int getVegetables() {
		return vegetables;
	}

	public void setVegetables(int vegetables) {
		this.vegetables = vegetables;
	}

	public int getRows_y() {
		return rows_y;
	}

	public void setRows_y(int rows_y) {
		this.rows_y = rows_y;
	}

	public int getColumns_x() {
		return columns_x;
	}

	public void setColumns_x(int columns_x) {
		this.columns_x = columns_x;
	}

	public void updateLabels() {
		// TODO Auto-generated method stub

	}

}
