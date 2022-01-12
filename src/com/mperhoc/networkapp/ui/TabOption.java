package com.mperhoc.networkapp.ui;

import javax.swing.JPanel;

public class TabOption {
	private String text;
	private JPanel panel;

	public TabOption(String text, JPanel panel) {
		this.text = text;
		this.panel = panel;
	}

	public String getText() {
		return text;
	}

	public JPanel getPanel() {
		return panel;
	}

}
