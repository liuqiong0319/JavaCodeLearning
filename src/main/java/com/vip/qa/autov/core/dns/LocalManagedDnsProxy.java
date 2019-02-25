/* 
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package com.vip.qa.autov.core.dns;

import sun.net.spi.nameservice.NameService;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Locally managed DNS.
 *
 * @author JunHo Yoon
 * @since 3.0
 */
@SuppressWarnings({ "restriction", "WeakerAccess" })
public class LocalManagedDnsProxy implements NameService {
	private final NameService defaultDnsImpl = new LocalManagedDnsImpl();
	private final NameStore instance = NameStore.getInstance();

	/**
	 * Get host name by address.
	 *
	 * @param ip
	 *            ip
	 * @return host name
	 * @throws UnknownHostException
	 *             occurs when hostname can not be found.
	 * @see NameService#getHostByAddr(byte[])
	 */
	@Override
	public String getHostByAddr(byte[] ip) throws UnknownHostException {
		String hostName = instance.getReveredHost(InetAddress.getByAddress(ip));
		if (hostName == null) {
			hostName = defaultDnsImpl.getHostByAddr(ip);
		}
		return hostName;
	}

	/**
	 * Get InetAddresses by hostname.
	 *
	 * @param name
	 *            hostname
	 * @return ip addresses
	 * @throws UnknownHostException
	 *             occurs when hostname can not be found.
	 * @see NameService#getHostByAddr(byte[])
	 */
	@Override
	public InetAddress[] lookupAllHostAddr(String name) throws UnknownHostException {
		InetAddress ipAddresses = instance.get(name);
		if (ipAddresses != null) {
			return DnsUtils.shuffle(new InetAddress[] { ipAddresses });
		} else {
			InetAddress[] addresses = defaultDnsImpl.lookupAllHostAddr(name);
			instance.put(name, addresses[0].getHostAddress());
			return addresses;
		}
	}
}
