package com.mperhoc.networkapp.net;

import com.mperhoc.networkapp.exception.IPFormatException;
import com.mperhoc.networkapp.exception.IPOutOfBoundsException;
import com.mperhoc.networkapp.net.SubnetMask.MaskFormat;

public class Subnetwork {
	// Important properties that determines the network
	private IpAddress networkAddress;
	private IpAddress firstAddress;
	private IpAddress lastAddress;
	private IpAddress broadcastAddress;

	public Subnetwork(String ipAddress, String subnetMask, MaskFormat maskFormat)
			throws IPFormatException, IPOutOfBoundsException {
		IpAddress address = new IpAddress(ipAddress);

		// TODO: make the mask format a parameter
		SubnetMask mask = new SubnetMask(subnetMask, maskFormat);

		int ip = IPv4Converter.addressToInteger(address);
		int maskValue = mask.getMaskAsInteger();

		final int networkIp = ip & maskValue;

		this.networkAddress = IPv4Converter.integerToAddress(networkIp);
		this.firstAddress = IPv4Converter.integerToAddress(networkIp + 1);

		int lastIp = networkIp + mask.getNumberOfAvailableAddresses();
		this.lastAddress = IPv4Converter.integerToAddress(lastIp);
		this.broadcastAddress = IPv4Converter.integerToAddress(lastIp + 1);

		// System.out.println(networkAddress);
		// System.out.println(firstAddress);
		// System.out.println(lastAddress);
		// System.out.println(broadcastAddress);
	}

	public IpAddress getNetworkAddress() {
		return networkAddress;
	}

	public IpAddress getFirstAddress() {
		return firstAddress;
	}

	public IpAddress getLastAddress() {
		return lastAddress;
	}

	public IpAddress getBroadcastAddress() {
		return broadcastAddress;
	}

}
