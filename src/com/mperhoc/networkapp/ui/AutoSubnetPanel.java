package com.mperhoc.networkapp.ui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.mperhoc.networkapp.exception.IPFormatException;
import com.mperhoc.networkapp.exception.IPOutOfBoundsException;
import com.mperhoc.networkapp.net.IPv4Converter;
import com.mperhoc.networkapp.net.IpAddress;
import com.mperhoc.networkapp.net.SubnetMask;
import com.mperhoc.networkapp.net.SubnetMask.MaskFormat;
import com.mperhoc.networkapp.net.Subnetwork;
import com.mperhoc.networkapp.ui.subpanel.AutomaticIpInputPanel;
import com.mperhoc.networkapp.ui.subpanel.NetworkInfoPanel;

public class AutoSubnetPanel extends JPanel {
	private static final long serialVersionUID = 7159698095262997971L;

	private AutomaticIpInputPanel inputPanel = new AutomaticIpInputPanel();
	private NetworkInfoPanel netInfoPanel = new NetworkInfoPanel();
	private JButton generate;

	private Subnetwork subNetwork;

	public AutoSubnetPanel() {
		super(new GridLayout(4, 2));

		// Title on the panel
		addTitle();

		// Input fields for the IP address and the number of network devices
		add(inputPanel);

		// Generate button
		addGenerateButton();

		// Network information panel
		add(netInfoPanel);
	}

	private void addTitle() {
		final Font titleFont = new Font("Dialog", Font.BOLD, 18);
		JLabel title = new JLabel("Automatic subnet generation", SwingConstants.CENTER);
		title.setFont(titleFont);

		add(title);
	}

	private int parseNumberOfHosts(String hosts) {
		try {
			return Integer.parseInt(hosts);
		} catch(NumberFormatException e) {
			return -1;
		}
	}

	private void addGenerateButton() {
		generate = new JButton("Generate!");

		generate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				String ip = inputPanel.getIpAddress();
				String numberOfHosts = inputPanel.getNumberOfHosts();

				int numHosts = parseNumberOfHosts(numberOfHosts);

				// Invalid input checks

				// Check if the ip address is invalid
				if(IPv4Converter.decimalToBinary(ip).equals(IPv4Converter.INVALID_ADDRESS_MESSAGE)) {
					inputPanel.setIpAddressText("");
					inputPanel.setHostNumberText("");
					return;
				}
				// Then check if the number of hosts is < 2
				// TODO: check for the upper limit of hosts
				if(numHosts < 2) {
					JOptionPane.showMessageDialog(null, "Invalid number of hosts!", "Error", JOptionPane.ERROR_MESSAGE);
					inputPanel.setIpAddressText("");
					inputPanel.setHostNumberText("");
					return;
				}

				String subnetMask = "";
				for(int i = 2; i < 32; i++) {
					int maxNumberOfHosts = (int) Math.pow(2, i) - 2;

					// We found out subnet mask
					if(numHosts <= maxNumberOfHosts) {
						subnetMask = "" + (32 - i);
						break;
					}
				}

				try {
					subNetwork = new Subnetwork(ip, subnetMask, MaskFormat.MASK_PREFIX);
				} catch(IPFormatException | IPOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					inputPanel.setIpAddressText("");
					inputPanel.setHostNumberText("");
					subNetwork = null;
				}

				boolean isPrivateNetwork = false;
				String privateMask = "";

				for(Map.Entry<String, String> entry: IpAddress.privateAddressLimits.entrySet()) {
					// We found a matching class
					if(ip.startsWith(entry.getKey())) {
						isPrivateNetwork = true;
						privateMask = entry.getValue();

						break;
					}
				}

				// We are checking if the IP address the user has typed in is a private network
				// address (classes A, B and C).
				if(isPrivateNetwork) {
					SubnetMask m = null;

					try {
						m = new SubnetMask(privateMask, MaskFormat.MASK_DECIMAL);
					} catch(IPFormatException | IPOutOfBoundsException e) {
						e.printStackTrace();
					}

					if(numHosts > m.getNumberOfAvailableAddresses()) {
						JOptionPane.showMessageDialog(null, "Too many hosts for the specified private address!",
								"Error", JOptionPane.ERROR_MESSAGE);
						subNetwork = null;
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"The IP address you typed in is NOT a private address.\nThe calculations will not include the private network class limits.",
							"Warning", JOptionPane.WARNING_MESSAGE);
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
