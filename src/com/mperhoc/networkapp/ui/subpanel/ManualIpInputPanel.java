package com.mperhoc.networkapp.ui.subpanel;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ManualIpInputPanel extends JPanel {
	private static final long serialVersionUID = -7847816545811331828L;

	private TitledBorder border;
	private JTextField addressInput, maskInput;
	private JLabel addressLabel, maskLabel;

	public ManualIpInputPanel() {
		super(new GridLayout(2, 2));

		border = BorderFactory.createTitledBorder("Network input");

		addressInput = new JTextField(22);
		addressLabel = new JLabel("IP address:");

		maskInput = new JTextField(22);
		maskLabel = new JLabel("Subnet mask:");

		add(addressLabel);
		add(addressInput);

		add(maskLabel);
		add(maskInput);

		setBorder(border);
	}

	public void setIpAddressText(String t) {
		addressInput.setText(t);
	}

	public void setSubnetMaskText(String t) {
		maskInput.setText(t);
	}

	public String getIpAddress() {
		return addressInput.getText();
	}

	public String getSubnetMask() {
		return maskInput.getText();
	}

}
