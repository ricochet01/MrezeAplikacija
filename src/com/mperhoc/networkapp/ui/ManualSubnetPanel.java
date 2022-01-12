package com.mperhoc.networkapp.ui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.mperhoc.networkapp.exception.IPFormatException;
import com.mperhoc.networkapp.exception.IPOutOfBoundsException;
import com.mperhoc.networkapp.net.IPv4Converter;
import com.mperhoc.networkapp.net.SubnetMask.MaskFormat;
import com.mperhoc.networkapp.net.Subnetwork;
import com.mperhoc.networkapp.ui.subpanel.ManualIpInputPanel;
import com.mperhoc.networkapp.ui.subpanel.NetworkInfoPanel;

public class ManualSubnetPanel extends JPanel {
	private static final long serialVersionUID = -7503788151306003356L;

	private ManualIpInputPanel inputPanel = new ManualIpInputPanel();
	private NetworkInfoPanel netInfoPanel = new NetworkInfoPanel();
	private JButton generate;

	private Subnetwork subNetwork;

	public ManualSubnetPanel() {
		super(new GridLayout(4, 2));

		addTitle();

		add(inputPanel);

		addGenerateButton();

		add(netInfoPanel);
	}

	private void addTitle() {
		final Font titleFont = new Font("Dialog", Font.BOLD, 18);
		JLabel title = new JLabel("Manual subnet generation", SwingConstants.CENTER);
		title.setFont(titleFont);

		add(title);
	}

	private void addGenerateButton() {
		generate = new JButton("Generate!");

		generate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				String ip = inputPanel.getIpAddress();
				String subnetMask = inputPanel.getSubnetMask();

				// Invalid input checks

				// Check if the ip address is invalid (it will also create an error popup
				// window)
				if(IPv4Converter.decimalToBinary(ip).equals(IPv4Converter.INVALID_ADDRESS_MESSAGE)) {
					inputPanel.setIpAddressText("");
					inputPanel.setSubnetMaskText("");
					return;
				}

				try {
					subNetwork = new Subnetwork(ip, subnetMask, MaskFormat.MASK_DECIMAL);
				} catch(IPFormatException | IPOutOfBoundsException | IllegalArgumentException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					inputPanel.setIpAddressText("");
					inputPanel.setSubnetMaskText("");
					subNetwork = null;

					// We could add a return statement here, but it isn't necessary
				}

				if(subNetwork != null) {
					netInfoPanel.setNetworkText(getNetworkAddress());
					netInfoPanel.setFirstText(getFirstAddress());
					netInfoPanel.setLastText(getLastAddress());
					netInfoPanel.setBroadcastText(getBroadcastAddress());
				}
			}

		});

		add(generate);
	}

	private String getNetworkAddress() {
		return subNetwork.getNetworkAddress().toString();
	}

	private String getFirstAddress() {
		return subNetwork.getFirstAddress().toString();
	}

	private String getLastAddress() {
		return subNetwork.getLastAddress().toString();
	}

	private String getBroadcastAddress() {
		return subNetwork.getBroadcastAddress().toString();
	}

}
