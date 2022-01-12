package com.mperhoc.networkapp.net;

import com.mperhoc.networkapp.exception.IPFormatException;
import com.mperhoc.networkapp.exception.IPOutOfBoundsException;

public class IpAddress {
	private String ip;
	private final int base;

	private int[] octets = new int[4];

	public IpAddress(String ip) throws IPFormatException, IPOutOfBoundsException {
		this(ip, false);
	}

	public IpAddress(String ip, boolean isBinary) throws IPFormatException, IPOutOfBoundsException {
		this.ip = ip;
		this.base = isBinary ? 2 : 10;

		initializeOctets(ip, base);
	}

	private void initializeOctets(String ip, int base) throws IPFormatException, IPOutOfBoundsException {
		// Splitting the IP into multiple strings
		String[] ipValues = ip.split("\\.");

		if(ipValues.length != 4) {
			throw new IPFormatException("IP format is invalid! There must be excactly 4 octets.");
		}

		// Converting each string of (hopefully) number characters to integers.
		for(int i = 0; i < ipValues.length; i++) {
			int num = -1;

			try {
				num = Integer.parseInt(ipValues[i], base);
			} catch(NumberFormatException e) {
				throw new IPFormatException("Invalid IP value!");
			}

			if(num >= 0 && num <= 255) {
				octets[i] = num;
			} else {
				throw new IPOutOfBoundsException("Invalid IP octet range! Each octet must be in range [0-255]");
			}
		}

		// IP address can't start with a 0
		if(octets[0] == 0) throw new IPFormatException("IP address cannot start with a 0!");
	}

	public int[] getOctets() {
		return octets;
	}

	@Override
	public String toString() {
		return ip;
	}

}
