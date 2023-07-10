package net.codejava;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "device")
public class Device {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "device_name", unique = true, nullable = false, length = 40)
	private String device_name;
	
	@Column(name = "location", nullable = false, length = 50)
	private String location;
	
	@Column(name = "ip_address", nullable = false, length = 50)
	private String ip_address;
	
	@Column(name = "network_provider", nullable = false, length = 50)
	private String network_provider;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the device_name
	 */
	public String getDevice_name() {
		return device_name;
	}

	/**
	 * @param device_name the device_name to set
	 */
	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the ip_address
	 */
	public String getIp_address() {
		return ip_address;
	}

	/**
	 * @param ip_address the ip_address to set
	 */
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	/**
	 * @return the network_provider
	 */
	public String getNetwork_provider() {
		return network_provider;
	}

	/**
	 * @param network_provider the network_provider to set
	 */
	public void setNetwork_provider(String network_provider) {
		this.network_provider = network_provider;
	}
	
	 
}
