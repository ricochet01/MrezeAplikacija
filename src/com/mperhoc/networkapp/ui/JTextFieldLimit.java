package com.mperhoc.networkapp.ui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldLimit extends PlainDocument {
	private static final long serialVersionUID = 4564238157438611718L;

	private int limit;

	public JTextFieldLimit(int limit) {
		super();
		this.limit = limit;
	}

	@Override
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		if(str == null) return;

		if((getLength() + str.length()) <= limit) {
			super.insertString(offset, str, attr);
		}
	}

}
