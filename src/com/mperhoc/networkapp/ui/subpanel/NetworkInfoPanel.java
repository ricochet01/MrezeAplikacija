package com.mperhoc.networkapp.ui.subpanel;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class NetworkInfoPanel extends JPanel {
	private static final long serialVersionUID = -9098268523094608662L;

	private TitledBorder border;
	private JTextField network, first, last, broadcast;
	private JLabel networkLabel, firstLabel, lastLabel, broadcastLabel;

	public NetworkInfoPanel() {
		super(new GridLayout(4, 2));

		border = BorderFactory.createTitledBorder("Network information");

		network = new JTextField(22);
		network.setEditable(false);
		networkLabel = new JLabel("Network address");

		first = new JTextField(22);
		first.setEditable(false);
		firstLabel = new JLabel("First usable address");

		last = new JTextField(22);
		last.setEditable(false);
		lastLabel = new JLabel("Last usable address");

		broadcast = new JTextField(22);
		broadcast.setEditable(false);
		broadcastLabel = new JLabel("Broadcast address");

		add(networkLabel);
		add(network);

		add(firstLabel);
		add(first);

		add(lastLabel);
		add(last);

		add(broadcastLabel);
		add(broadcast);

		setBorder(border);
	}

	public void setNetworkText(String t) {
		network.setText(t);
	}

	public void setFirstText(String t) {
		first.setText(t);
	}

	public void setLastText(String t) {
		last.setText(t);
	}

	public void setBroadcastText(String t) {
		broadcast.setText(t);
	}

}
