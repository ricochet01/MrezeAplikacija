package com.mperhoc.networkapp.net;

import com.mperhoc.networkapp.exception.IPFormatException;
import com.mperhoc.networkapp.exception.IPOutOfBoundsException;

public class SubnetMask {
	public enum MaskFormat {
		MASK_DECIMAL,
		MASK_BINARY,
		MASK_PREFIX;
	}

	private int numberOfHostBits;
	private int value;

	public SubnetMask(String mask, MaskFormat format) throws IPFormatException, IPOutOfBoundsException {
		boolean validInput = isMaskValid(mask, format);

		if(!validInput) throw new IllegalArgumentException("Invalid mask input.");
	}

	private boolean isMaskValid(String mask, MaskFormat format) throws IPFormatException, IPOutOfBoundsException {
		boolean isValid = false;
		IpAddress address;

		switch(format) {
			case MASK_DECIMAL:
				address = new IpAddress(mask);
				isValid = isAddressValid(address);
				break;
			case MASK_BINARY:
				address = new IpAddress(mask, true);
				isValid = isAddressValid(address);
				break;
			case MASK_PREFIX:
				isValid = isPrefixValid(mask);
				break;
		}

		return isValid;
	}

	private boolean isAddressValid(IpAddress address) {
		int ip = IPv4Converter.addressToInteger(address);
		int hostBits = 0;

		for(int i = 31; i >= 0; i--) {
			int bit = (ip >> i) & 1;

			if(i == 31 && bit == 0) { // The subnet mask cannot be 0.0.0.0
				System.err.println("first bit is 0");
				return false;
			} else if(i <= 1 && bit == 1) { // Subnet mask prefix must be <= 30
				System.err.println("subnet mask greater than 30");
				return false;
			} else {
				int index = Math.max(0, i - 1);
				int nextBit = (ip >> index) & 1;

				// The host bits are calculated at the end of the check
				if(bit == 1) hostBits++;

				// There cannot be a 0 before a 1 in the subnet mask (binary)
				if(bit == 0 && nextBit == 1) return false;
			}

		}

		this.numberOfHostBits = 32 - hostBits;
		this.value = ip;

		return true;
	}

	private boolean isPrefixValid(String prefix) {
		int value = Integer.parseInt(prefix.replace("/", ""));
		boolean inRange = value > 0 && value < 31;

		if(inRange) {
			this.numberOfHostBits = 32 - value;

			for(int i = 31; i >= numberOfHostBits; i--) {
				this.value += (1 << i);
			}

			return true;
		}

		return false;
	}

	protected int getNumberOfHostBits() {
		return numberOfHostBits;
	}

	protected int getNumberOfAvailableAddresses() {
		return (int) (Math.pow(2, numberOfHostBits) - 2);
	}

	protected int getMaskAsInteger() {
		return value;
	}

}
