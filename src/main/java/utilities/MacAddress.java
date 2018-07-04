package utilities;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class MacAddress {

    public String getMacAddress() {
    	Enumeration<NetworkInterface> nics;
    	String s = "";
    	
		try {
			nics = NetworkInterface.getNetworkInterfaces();
			
			while(nics.hasMoreElements()) {
	    		NetworkInterface nic = nics.nextElement();
	    		byte[] hardwareAddress = nic.getHardwareAddress();
	    		if(hardwareAddress != null) {
	    			for(byte b : hardwareAddress) {
	    				s += String.format("%02X",  b);
	    			}
	    		}
	    	}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return s;
    }
}
