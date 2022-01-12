package com.mperhoc.networkapp.ui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.mperhoc.networkapp.net.IPv4Converter;
import com.mperhoc.networkapp.ui.subpanel.AddressInputPanel;
import com.mperhoc.networkapp.ui.subpanel.RadioButtonPanel;

public class IPConversionPanel extends JPanel {
	private static final long serialVersionUID = 6497493621116035104L;

	private RadioButtonPanel formatPicker = new RadioButtonPanel();
	private AddressInputPanel inputPanel = new AddressInputPanel();
	private JButton convert;

	public IPConversionPanel() {
		super(new GridLayout(5, 2));

		addTitle();

		add(formatPicker);
		add(inputPanel);

		addConvertButton();
	}

	private void addTitle() {
		final Font titleFont = new Font("Dialog", Font.BOLD, 18);
		JLabel title = new JLabel("Convert IPv4 addresses", SwingConstants.CENTER);
		title.setFont(titleFont);

		add(title);
	}

	private void addConvertButton() {
		convert = new JButton("Convert!");

		convert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				String ip = inputPanel.getInputText();
				String result;
				int base = formatPicker.getSelectedFormat();

				if(base == 2) result = IPv4Converter.binaryToDecimal(ip);
				else result = IPv4Converter.decimalToBinary(ip);

				// If the IP converted successfully
				if(!result.equals(IPv4Converter.INVALID_ADDRESS_MESSAGE)) {
					inputPanel.setOutputText(result);
				} else {
					inputPanel.setInputText("");
					inputPanel.setOutputText("");
				}
			}
		});

		add(convert);
	}

}
