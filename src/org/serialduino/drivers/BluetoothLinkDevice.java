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
 

package org.serialduino.drivers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;

/**
 * SerialDuino Bluetooth Port driver.
 * @author Emanuele Paiano 
 * https://emanuelepaiano.github.io
 * */
public class BluetoothLinkDevice implements LinkDevice{
	private String url;
	private StreamConnection connection;
	private InputStream in;
	private OutputStream out;
	private boolean isConnected=false;
	
	/**
	 * Create Bluetooth Link
	 * @param url URL bluetooth service like btspp://[ADDRESS]:[CHANNEL | UUID]/[PARAMS]
	 * 
	 * */
	public BluetoothLinkDevice(String url)
	{
		this.url=url;
	}

	public void finalize()
	{
		this.close();
	}
	
	@Override
	public int write(String data) {
		if(isReady())
		{
			for(int i=0;i<data.length();i++)
			{
		        try {
					out.write(data.charAt(i));
				} catch (IOException e) {
					return 0;
				}		       
			}
		}
		return data.length();
	}

	@Override
	public String read(int bytes) {
		String buffer="";
		if (isReady())
			for (int i=0;i<bytes;i++)
			{
				try {
					buffer+=String.valueOf((char)in.read());
				} catch (IOException e) {
					buffer=null;
					break;
				}
			}
		
		return buffer;
	}

	@Override
	public String read() {
		String ch="";
		String buffer="";
		
		if (this.isReady())
		{
			while(ch!="\n")
			{
				ch=read(1);
				buffer+=ch;
			}
			return buffer;
		}
		return null;
	}

	@Override
	public boolean open() {
		try {
			connection=(StreamConnection) Connector.open(this.url);
			this.isConnected=true;
			this.in=connection.openInputStream();
			this.out=connection.openOutputStream();
		} catch (IOException e) {
			this.isConnected=false;
			logger.severe("Error while connect to "+this.url);
			logger.severe(e.getMessage());
		}
		return isConnected;
	}

	@Override
	public boolean close() {
		try {
			in.close();
			out.close();
			connection.close();
			isConnected=false;
		} catch (IOException e) {
			logger.severe("Error while closing connection: "+e.getMessage());
			return false;
		}
		
		return !isConnected;
	}

	@Override
	public boolean isReady() {
		return this.isConnected;
	}

	@Override
	public boolean bufferAvailable() {
		try {
			return this.in.available()>0;
		} catch (IOException e) {
			return false;
		}
	}

	@Override
	public Object getDevice() {
		return this.connection;
	}

}
