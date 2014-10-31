package Graphics;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import Controllers.MainController;

import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow implements Observer, Runnable {
	private MainController controller;
	private JFrame frame;
	private JTextArea textArea;
	private JButton btnStop;
	private JButton btnPaused;

	/**
	 * Create the application.
	 */
	public MainWindow(MainController controller) {
		this.controller = controller;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 11, 293, 99);
		frame.getContentPane().add(textArea);

		JButton btnNewCost = new JButton("new Cost");
		btnNewCost.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
                controller.addCustomer();
			}
		});
		btnNewCost.setBounds(10, 121, 91, 23);
		frame.getContentPane().add(btnNewCost);
		
		btnStop = new JButton("stop");
		btnStop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		btnStop.setBounds(212, 121, 91, 23);
		frame.getContentPane().add(btnStop);
		
		btnPaused = new JButton(getPauseBtnText());
		btnPaused.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		btnPaused.setBounds(111, 121, 91, 23);
		frame.getContentPane().add(btnPaused);
	}

	public void show() {
		frame.setVisible(true);
	}

	public String getPauseBtnText() {
		 return controller.isPaused() ? "Unpause": "Pause";
	}

	@Override
	public void update(Observable model, Object arg) {
		textArea.setText(controller.getStats());
		btnPaused.setText(getPauseBtnText());
	}
	
	@Override
	public void run() {
		this.show();
	}
}
