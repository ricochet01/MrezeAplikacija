package com.mperhoc.networkapp.ui.subpanel;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

public class RadioButtonPanel extends JPanel {
	private static final long serialVersionUID = 9203189091550999090L;

	private JRadioButton decimal, binary;
	private ButtonGroup formats = new ButtonGroup();
	private TitledBorder border;

	public RadioButtonPanel() {
		border = BorderFactory.createTitledBorder("Input IP address format");

		decimal = new JRadioButton("Decimal", true);
		binary = new JRadioButton("Binary");

		formats.add(decimal);
		formats.add(binary);

		BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);

		setLayout(layout);
		add(decimal);
		add(binary);

		setBorder(border);
	}

	public int getSelectedFormat() {
		return decimal.isSelected() ? 10 : 2;
	}

}
