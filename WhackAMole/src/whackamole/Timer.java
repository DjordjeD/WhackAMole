package whackamole;

import java.awt.Label;

public class Timer extends Thread {

	private int s, m;
	private boolean work;
	private Label label;

	public Timer(Label label) {
		this.label = label;
	}

	@Override
	public void run() {
		try {
			while (!isInterrupted()) {
				synchronized (this) {
					while (!work)
						wait();
				}

				label.setText(toString());
				label.revalidate();
				sleep(1000);
				s++;
				if (s % 60 == 0) {
					m++;
					s = 0;
				}
			}
		} catch (InterruptedException e) {
		}
	}

	public synchronized void go() {
		work = true;
		notify();
	}

	public synchronized void pause() {
		work = false;
	}

	public synchronized void reset() {
		m = s = 0;
	}

	@Override
	public synchronized String toString() {
		return String.format("%02d:%02d", m, s);
	}

//	private void showHelpDialog() {
//		Dialog help = new Dialog(this, ModalityType.APPLICATION_MODAL);
//		help.setTitle("Help");
//		help.add(new Label("Use a-s-w-d to move", Label.CENTER));
//		help.setBounds(700, 200, 100, 100);
//		help.setResizable(false);
//		help.addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowClosing(WindowEvent e) {
//				help.dispose();
//			}
//		});
//		help.setVisible(true);
//	}

//	private class MouseEventHandler extends MouseAdapter {
//
//		@Override
//		public void mouseClicked(MouseEvent e) {
//			adjust("mouse clicked", e);
//		}
//
//		@Override
//		public void mousePressed(MouseEvent e) {
//			adjust("mouse pressed", e);
//		}
//
//		@Override
//		public void mouseReleased(MouseEvent e) {
//			adjust("mouse released", e);
//		}
//	}
//
//	private class MouseMotionEventHandler extends MouseMotionAdapter {
//
//		@Override
//		public void mouseDragged(MouseEvent e) {
//			adjust("mouse dragged", e);
//		}
//
//		@Override
//		public void mouseMoved(MouseEvent e) {
//			adjust("mouse moved", e);
//		}
//	}
//
//	public MouseWindow() {
//
//		setBounds(700, 200, 400, 300);
//		setResizable(false);
//		setTitle("Mouse window");
//
//		addMouseListener(new MouseEventHandler());
//		addMouseMotionListener(new MouseMotionEventHandler());
//
//		addWindowListener(new WindowAdapter() {
//
//			@Override
//			public void windowClosing(WindowEvent e) {
//				new Diag(MouseWindow.this);
//			}
//		});
//		setVisible(true);
//	}
//
//	public static void main(String[] args) {
//
//		new MouseWindow();
//	}
}
