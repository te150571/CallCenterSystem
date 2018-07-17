package jp.te4a.zoo.spring.boot.CallCenterSystem.utilities;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;

public class IpAddress {

	// IPアドレスを取得するやつ
	public String getIpAddress() {
		try {
			return( getInetAddress4().getHostAddress() );
		}catch(SocketException e){

		}catch(UnknownHostException e){

		}
		return "UNKNOWN IP ADDRESS";
	}
	private InetAddress getInetAddress4() throws UnknownHostException,SocketException {
		InetAddress rtnInet = null;
		Enumeration<NetworkInterface>  netSet;//集合内の列挙操作用
		netSet = NetworkInterface.getNetworkInterfaces();
		while(netSet.hasMoreElements()){//すべてのインターフェイスを走査
			NetworkInterface nInterface = (NetworkInterface) netSet.nextElement();
			List<InterfaceAddress> list = nInterface.getInterfaceAddresses();
			if( list.size() == 0 ) continue;
			for (InterfaceAddress interfaceAdr : list){
				InetAddress inet = interfaceAdr.getAddress();
				if(inet.isLoopbackAddress() ) continue;
				if(inet.getClass() == Inet4Address.class) {
					rtnInet = inet;
				}
			}
		}
		return rtnInet;
	}
}
