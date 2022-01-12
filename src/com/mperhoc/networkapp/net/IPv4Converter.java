package com.mperhoc.networkapp.net;

import javax.swing.JOptionPane;

import com.mperhoc.networkapp.exception.IPFormatException;
import com.mperhoc.networkapp.exception.IPOutOfBoundsException;

public class IPv4Converter {
	public static final String INVALID_ADDRESS_MESSAGE = "INVALID";

	public static String decimalToBinary(String ip) {
		IpAddress address = null;

		try {
			address = new IpAddress(ip);
		} catch(IPFormatException | IPOutOfBoundsException e) {
			// Creating an error dialog window
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return INVALID_ADDRESS_MESSAGE;
		}
		String binaryAddress = "";

		int[] octets = address.getOctets();

		for(int i = 0; i < octets.length; i++) {
			String binaryNumber = String.format("%8s", Integer.toBinaryString(octets[i])).replace(" ", "0");

			binaryAddress += binaryNumber;
			if(i < 3) binaryAddress += ".";
		}

		return binaryAddress;
	}

	public static String binaryToDecimal(String binaryIp) {
		IpAddress address = null;
		try {
			address = new IpAddress(binaryIp, true);
		} catch(IPFormatException | IPOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return INVALID_ADDRESS_MESSAGE;
		}

		String decimalAddress = "";

		int[] octets = address.getOctets();

		for(int i = 0; i < octets.length; i++) {
			decimalAddress += octets[i];
			if(i < 3) decimalAddress += ".";
		}

		return decimalAddress;
	}

	public static int addressToInteger(IpAddress address) {
		int[] octets = address.getOctets();
		return (octets[0] << 24) + (octets[1] << 16) + (octets[2] << 8) + (octets[3]);
	}

	public static IpAddress integerToAddress(int ip) {
		String a = "" + (ip >> 24 & 0xff) + "." + (ip >> 16 & 0xff) + "." + (ip >> 8 & 0xff) + "." + (ip & 0xff);

		IpAddress address = null;
		try {
			address = new IpAddress(a);
		} catch(IPFormatException | IPOutOfBoundsException e1) {
			e1.printStackTrace();
		}

		return address;
	}

}
