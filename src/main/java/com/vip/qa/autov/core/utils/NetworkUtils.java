package com.vip.qa.autov.core.utils;

import org.apache.commons.io.IOUtils;
import org.testng.Assert;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import static java.net.NetworkInterface.getNetworkInterfaces;

public class NetworkUtils {

	public static final int TIMEOUT = 10000;

	public static void checkPortIsRunning(int port) {
		while (!isPortUsing(port)) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
				Assert.fail(e.getMessage());
			}
		}
	}

	public static void waitForPortAvailable(int port) {
		long limit = System.currentTimeMillis() + TIMEOUT;
		while (System.currentTimeMillis() < limit && isPortUsing(port)) {
			Threads.sleep(500);
		}
		if (isPortUsing(port)) {
			throw new RuntimeException("timeout waiting for port:" + port + " avaliable");
		}
	}

	public static int getAvaliablePort(int port) {
		while (true) {
			if (!isPortUsing(port)) {
				return port;
			}
			port++;
		}
	}

	public static boolean isPortUsing(int port) {
		Socket socket = null;
		boolean reachable = false;
		try {
			socket = new Socket("127.0.0.1", port);
			reachable = true;
		} catch (Exception e) {
		} finally {
			IOUtils.closeQuietly(socket);
		}
		return reachable;
	}

	/***
	 * true:already in using false:not using
	 */
	public static boolean isPortUsing(String host, int port) {
		Socket socket = null;
		try {
			InetAddress theAddress = InetAddress.getByName(host);
			socket = new Socket(theAddress, port);
			IOUtils.closeQuietly(socket);
			return true;
		} catch (Exception e) {// NOSONAR
		}
		finally {
			IOUtils.closeQuietly(socket);
		}
		return false;
	}

	public static int getRandomPort() {

		int port = 0;
		try {
			ServerSocket server = new ServerSocket(0);
			port = server.getLocalPort();
			server.close();
		} catch (IOException e) {
			Exceptions.checked(e);
		}
		return port;
	}

	/**
	 * Get local host name. On some platform,
	 * InetAddress.getLocalHost().getHostName() will return "localhost". If the
	 * /etc/hosts file is not set properly, it will return "localhost" or throw
	 * exception. So, at this circumstance, we will get the address by
	 * connecting a network address.
	 *
	 * @return local host name
	 */
	public static String getLocalHostName() {
		String hostName = null;
		try {
			hostName = InetAddress.getLocalHost().getHostName();
		} catch (Exception e) {
			throw new RuntimeException("Error while get localhost name", e);
		}
		if (hostName != null && !"localhost".equals(hostName)) {
			return hostName;
		}
		return getLocalHostName("www.google.com", 80);

	}

	/**
	 * Get local host name by connecting to a server.
	 *
	 * @param byConnecting
	 *            the server address to connect.
	 * @param port
	 *            the port to connect
	 * @return localhost name. if fails, return "localhost"
	 */
	static String getLocalHostName(String byConnecting, int port) {
		InetAddress addr = getLocalInetAddress(byConnecting, port);
		if (addr != null) {
			return addr.getHostName();
		} else {
			return "localhost";
		}
	}

	static String getLocalHostAddress(String byConnecting, int port) {
		InetAddress addr = getLocalInetAddress(byConnecting, port);
		if (addr != null) {
			return addr.getHostAddress();
		} else {
			// It's final...
			return "127.0.0.1";
		}
	}

	static InetAddress getLocalInetAddress(String byConnecting, int port) {
		InetAddress addr = getAddressWithSocket(byConnecting, port);
		if (addr == null) {
			addr = getAddressWithSocket("www.baidu.com", 80);
		}
		if (addr == null) {
			try {
				addr = getFirstNonLoopbackAddress(true, false);
			} catch (SocketException e2) {
				addr = null;
			}
		}
		return addr;
	}

	static InetAddress getAddressWithSocket(String byConnecting, int port) {
		Socket s = new Socket();
		try {
			if (tryConnection(byConnecting, port, s)) {
				return s.getLocalAddress();
			}
		} finally {
			IOUtils.closeQuietly(s);
		}
		return null;
	}

	static boolean tryConnection(String byConnecting, int port, Socket socket) {
		try {
			socket.connect(new InetSocketAddress(byConnecting, port), 2000); // 2
																				// seconds
																				// timeout
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	static InetAddress getFirstNonLoopbackAddress(boolean preferIpv4, boolean preferIPv6) throws SocketException {
		Enumeration<?> en = getNetworkInterfaces();
		while (en.hasMoreElements()) {
			NetworkInterface i = (NetworkInterface) en.nextElement();
			if (!i.isUp()) {
				continue;
			}
			for (Enumeration<?> en2 = i.getInetAddresses(); en2.hasMoreElements();) {
				InetAddress addr = (InetAddress) en2.nextElement();
				if (!addr.isLoopbackAddress()) {
					if (addr instanceof Inet4Address) {
						if (preferIPv6) {
							continue;
						}
						return addr;
					}
					if (addr instanceof Inet6Address) {
						if (preferIpv4) {
							continue;
						}
						return addr;
					}
				}
			}
		}
		return null;
	}

	public static List<String> getDnsServers() throws NamingException {
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
		DirContext ctx = null;
		List<String> dnsServers = new ArrayList<String>();
		try {
			ctx = new InitialDirContext(env);
			String dnsString = (String) ctx.getEnvironment().get("java.naming.provider.url");
			for (String each : dnsString.split(" ")) {
				dnsServers.add(each.replace("dns://", ""));
			}
		} catch (Exception e) {
		} finally {
			if (ctx != null) {
				ctx.close();
			}
		}
		return dnsServers;
	}

}
