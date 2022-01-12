package com.mperhoc.networkapp.ui;

import javax.swing.JTabbedPane;

public class OptionsMenu extends JTabbedPane {
	private static final long serialVersionUID = 1L;

	public OptionsMenu(TabOption... tabs) {
		super(JTabbedPane.LEFT, JTabbedPane.WRAP_TAB_LAYOUT);

		// tabs[0].getPanel().add(new JButton("Hello"));
		// tabs[1].getPanel().add(new JButton("World"));

		for(TabOption t: tabs) {
			addTab(t.getText(), t.getPanel());
		}
	}

}
