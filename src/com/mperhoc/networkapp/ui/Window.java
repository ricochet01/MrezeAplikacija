package com.mperhoc.networkapp.ui;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;

	// Window constants
	public static final String TITLE = "Networking application";
	public static final int DEFAULT_WIDTH = 512;
	public static final int DEFAULT_HEIGHT = 512;
	private static final Dimension DEFAULT_SIZE = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);

	public Window() {
		super(TITLE);

		// Add UI components here
		OptionsMenu menu = new OptionsMenu(new TabOption("Convert IP", new IPConversionPanel()),
				new TabOption("Automatic subnet", new AutoSubnetPanel()),
				new TabOption("Manual subnet", new ManualSubnetPanel()));

		add(menu);

		pack();

		// Window properties
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setMinimumSize(DEFAULT_SIZE);
		setPreferredSize(DEFAULT_SIZE);
		setMaximumSize(DEFAULT_SIZE);

		setResizable(false);

		setLocationRelativeTo(null);
		setVisible(true);
	}

}
