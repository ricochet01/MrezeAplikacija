package com.mperhoc.networkapp.ui.subpanel;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class AutomaticIpInputPanel extends JPanel {
	private static final long serialVersionUID = -8072093077027731175L;

	private TitledBorder border;
	private JTextField addressInput, userNumberInput;
	private JLabel addressLabel, userNumberLabel;

	public AutomaticIpInputPanel() {
		super(new GridLayout(2, 2));

		border = BorderFactory.createTitledBorder("Network input");

		addressInput = new JTextField(22);
		userNumberInput = new JTextField(22);

		addressLabel = new JLabel("IP Address:");
		userNumberLabel = new JLabel("Number of devices:");

		add(addressLabel);
		add(addressInput);

		add(userNumberLabel);
		add(userNumberInput);

		setBorder(border);
	}

	public String getIpAddress() {
		return addressInput.getText();
	}

	public String getNumberOfHosts() {
		return userNumberInput.getText();
	}

	public void setIpAddressText(String text) {
		addressInput.setText(text);
	}

	public void setHostNumberText(String text) {
		userNumberInput.setText(text);
	}

}
