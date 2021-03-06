/**
 * Copyright 2017 Emanuele Paiano
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * */

package io.github.emanuelepaiano.serialduino.drivers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import io.github.emanuelepaiano.serialduino.settings.DefaultLinkDevice;

/**
 * SerialDuino TCP/IP Port driver
 * @author Emanuele Paiano 
 * https://emanuelepaiano.github.io
 * */
public class TcpLinkDevice implements LinkDevice{
	private String host;
	private int port;
	private Socket socket;
	private BufferedReader reader;
	private PrintWriter writer;
	private boolean isConnected=false;

	/**
	 * Constructor.
	 * @param host remote ipaddress or hostname
	 * @param port remote tcp port
	 * */
	public TcpLinkDevice(String host, int port)
	{
		this.setHost(host);
		this.setPort(port);
	}
	
	
	/**
	 * Write data on buffer
	 * @param data data to sent
	 * @return sent bytes number
	 * */
	public int write(String data) {
		writer.write(data);
		return data.length();
	}

	/**
	 * Read data from buffer
	 * @param bytes bytes number to read
	 * @return read string
	 * */
	
	public String read(int bytes) {
		String str=null;
		char[] chList=new char[bytes];
		for(int i=0;i<bytes;i++)
		{
			try {
				chList[i]=(char)reader.read();
			} catch (IOException e) {
				if(DefaultLinkDevice.VERBOSE_MODE)
					logger.severe(e.getMessage());
				break;
			}
		}
		str=String.valueOf(chList);
		return str;
	}

	/**
	 * Read data from buffer
	 * 
	 * @return read string
	 * */
	public String read() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			if(DefaultLinkDevice.VERBOSE_MODE)
				logger.severe(e.getMessage());
		}
		return null;
	}

	/**
	 * Open connection to host:port
	 * 
	 * @return true if success, false otherwise
	 * */
	public boolean open() {
		try {
			this.socket = new Socket(host, port);
			this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.writer = new PrintWriter(socket.getOutputStream());
			this.isConnected=true;
			return true;
		} catch (UnknownHostException e) {
			if(DefaultLinkDevice.VERBOSE_MODE)
				logger.severe(e.getMessage());
		} catch (IOException e) {
			if(DefaultLinkDevice.VERBOSE_MODE)
				logger.severe(e.getMessage());
		}
		this.isConnected=false;
		return false;
	}

	/**
	 * Close connection from remote host
	 * 
	 * @return true if success, false otherwise
	 * */
	
	public boolean close() {
		try {
			this.reader.close();
			this.writer.close();
			this.socket.close();
			this.isConnected=false;
			return true;
		} catch (IOException e) {
			if(DefaultLinkDevice.VERBOSE_MODE)
				logger.severe(e.getMessage());
		}
		return false;
	}

	
	public boolean isReady() {
		
		return this.isConnected;
	}

	/**
	 * Return true if there available data into buffer
	 * 
	 * */
	public boolean bufferAvailable() {
		
		try {
			return reader.ready();
		} catch (IOException e) {
			if(DefaultLinkDevice.VERBOSE_MODE)
				logger.severe(e.getMessage());
		}
		return false;
	}

	/**
	 * Get TCP Socket
	 * */
	
	public Socket getDevice() {
		return socket;
	}

	/**
	 * get TCP port
	 * @return port number
	 * */
	public int getPort() {
		return port;
	}

	/**
	 * set TCP port
	 * @param port port
	 * */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * get TCP host
	 * @return host
	 * */
	public String getHost() {
		return host;
	}

	/**
	 * set TCP host
	 * @param host tcp ip address or hostname
	 * */
	public void setHost(String host) {
		this.host = host;
	}

}
