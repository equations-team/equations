package localhostgame;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.*;

public class GameView implements ActionListener, DropTargetListener {
	private JFrame mainFrame;
	private JPanel titlePanel;
	private JPanel gamePanel;
	private ImageIcon[] diceImages = new ImageIcon[24];
	private JLabel[] dice = new JLabel[24];
	private JButton menuButton;
	private JLabel gameLabel;
	private JPanel resources;
	private JPanel forbidden;
	private JPanel permitted;
	private JPanel required;
	private JPanel workingSolution;
	private JPanel gameSolution;
	private DropTarget res;
	private DropTarget fbd;
	private DropTarget req;
	private EquationsController equationsController;
	private DieIcon[] dieIcon = new DieIcon[24];

	public GameView(EquationsController controller) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		equationsController = controller;
		dieIcon = controller.getDieImages();
		// Establishes the main frame
		mainFrame = new JFrame("Equations");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(screenSize.width, screenSize.height);
		mainFrame.setLayout(new BorderLayout());

		// Makes the menu button and the game title
		titlePanel = new JPanel();
		gameLabel = new JLabel("Equations");
		gameLabel.setFont(new Font("Times New Roman", Font.BOLD, 36));
		gameLabel.setHorizontalAlignment(JLabel.CENTER);
		menuButton = new JButton("Menu");
		menuButton.addActionListener(this);
		menuButton.setSize(500, 200);
		menuButton.setHorizontalAlignment(JButton.LEFT);
		titlePanel.add(menuButton);
		titlePanel.add(gameLabel);

		// Makes the panel for the game itself
		gamePanel = new JPanel(new GridLayout(3, 1, 0, 0));

		// Makes the resources mat
		resources = new JPanel();
		resources.setBackground(Color.MAGENTA);
		resources.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// This add the dice. Will change in future builds
		String[] dieFaces = new String[24];
		for (int i = 0; i < dieFaces.length - 1; i++) {
			dieFaces[i] = "images/Black7.svg";
		}
		dieFaces[dieFaces.length - 1] = "images/RedPlus.svg";
		makeDice(dieFaces);
		for (JLabel die : dice) {
			resources.add(die);
		}

		// Makes the forbidden, permitted, and required mats.
		JPanel green = new JPanel();
		GridLayout diceDrops = new GridLayout(1, 3, 0, 0);
		green.setLayout(diceDrops);
		forbidden = new JPanel();
		forbidden.setBackground(Color.GREEN);
		forbidden.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		forbidden.setTransferHandler(new TransferHandler("icon"));
		permitted = new JPanel();
		permitted.setBackground(Color.GREEN);
		permitted.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		permitted.setTransferHandler(new TransferHandler("icon"));
		required = new JPanel();
		required.setBackground(Color.GREEN);
		required.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		required.setTransferHandler(new TransferHandler("icon"));
		green.add(forbidden);
		green.add(permitted);
		green.add(required);

		// Makes the workingSolution and gameSolution mats
		JPanel purple = new JPanel();
		GridLayout solutions = new GridLayout(1, 2, 0, 0);
		purple.setLayout(solutions);
		workingSolution = new JPanel();
		workingSolution.setBackground(Color.MAGENTA);
		workingSolution.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		workingSolution.setTransferHandler(new TransferHandler("icon"));
		gameSolution = new JPanel();
		gameSolution.setBackground(Color.MAGENTA);
		gameSolution.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		gameSolution.setTransferHandler(new TransferHandler("icon"));
		purple.add(workingSolution);
		purple.add(gameSolution);

		// Makes the parts of the mat droppable targets for the dice
		DropTarget res = new DropTarget(resources, this);
		DropTarget fbd = new DropTarget(forbidden, this);
		DropTarget pmt = new DropTarget(permitted, this);
		DropTarget req = new DropTarget(required, this);
		DropTarget ws = new DropTarget(workingSolution, this);
		DropTarget gs = new DropTarget(gameSolution, this);

		// Add the mats to the game panel
		gamePanel.add(resources);
		gamePanel.add(green);
		gamePanel.add(purple);

		// Adds everything to the frame
		mainFrame.add(titlePanel, BorderLayout.PAGE_START);
		mainFrame.add(gamePanel, BorderLayout.CENTER);
		mainFrame.setVisible(true);
		for(int i = 0; i < 24; i++) {
			resources.add(new JLabel(equationsController.getDieImages()[i]));
		}
	}

	private void makeDice(String[] dieFaces) {
		DragMouseAdapter listener = new DragMouseAdapter();
		for (int i = 0; i < diceImages.length; i++) {
			diceImages[i] = new ImageIcon(dieFaces[i]);
			dice[i] = new JLabel(diceImages[i]);
			dice[i].addMouseListener(listener);
			dice[i].setTransferHandler(new TransferHandler("icon"));
		}
	}

	private class DragMouseAdapter extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			JComponent c = (JComponent) e.getSource();
			TransferHandler handler = c.getTransferHandler();
			handler.exportAsDrag(c, e, TransferHandler.COPY);
		}
	}

	// Handles the event for the button
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Opens menu or goes back to home page");
	}

	// Handles the Drag and Drop events
	@Override
	public void dragEnter(DropTargetDragEvent arg0) {
		System.out.println("Initiate drag enter");
	}

	@Override
	public void dragExit(DropTargetEvent arg0) {
		System.out.println("Initiate drag exit");
	}

	@Override
	public void dragOver(DropTargetDragEvent arg0) {
		System.out.println("Initiate drag over");
	}

	@Override
	public void drop(DropTargetDropEvent arg0) {
		if (arg0.getSource() == resources) {
			System.out.println("Drop Not Allowed");
		} else {
			System.out.println("Drop Allowed");

			try {
				Transferable t = arg0.getTransferable();
				ImageIcon transfer = new ImageIcon((String) t.getTransferData(DataFlavor.stringFlavor));
				JLabel transferLabel = new JLabel(transfer);

			} catch (UnsupportedFlavorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent arg0) {
		System.out.println("Drop Action Changed");
	}

	// Main is just for testing purposes to see how the view looks
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//new GameView();
			}
		});
	}
}