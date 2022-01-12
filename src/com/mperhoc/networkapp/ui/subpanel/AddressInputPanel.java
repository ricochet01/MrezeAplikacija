package com.mperhoc.networkapp.ui.subpanel;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.mperhoc.networkapp.ui.JTextFieldLimit;

public class AddressInputPanel extends JPanel {
	private static final long serialVersionUID = -3351886085346036481L;

	private TitledBorder border;
	private JTextField input, output;
	private JLabel inputLabel, outputLabel;

	public AddressInputPanel() {
		super(new GridLayout(2, 2));

		// Creating all UI elements
		border = BorderFactory.createTitledBorder("Conversion");

		input = new JTextField(22);
		input.setDocument(new JTextFieldLimit(35));

		output = new JTextField(22);
		output.setEditable(false);

		inputLabel = new JLabel("Input:");
		outputLabel = new JLabel("Output:");

		// Adding all of the UI elements to the panel
		add(inputLabel);
		add(input);

		add(outputLabel);
		add(output);

		setBorder(border);
	}

	public String getInputText() {
		return input.getText();
	}

	public void setInputText(String text) {
		input.setText(text);
	}

	public String getOutputText() {
		return output.getText();
	}

	public void setOutputText(String text) {
		output.setText(text);
	}

}
