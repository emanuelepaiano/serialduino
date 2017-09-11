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

/**
 * SerialDuino Bluetooth Port driver. Work in Progress!
 * @author Emanuele Paiano 
 * https://emanuelepaiano.github.io
 * */
public class BluetoothLinkDevice implements LinkDevice{

	@Override
	public int write(String data) {
		logger.severe("Not implemented yet!");
		return 0;
	}

	@Override
	public String read(int bytes) {
		logger.severe("Not implemented yet!");
		return null;
	}

	@Override
	public String read() {
		logger.severe("Not implemented yet!");
		return null;
	}

	@Override
	public boolean open() {
		logger.severe("Not implemented yet!");
		return false;
	}

	@Override
	public boolean close() {
		logger.severe("Not implemented yet!");
		return false;
	}

	@Override
	public boolean isReady() {
		logger.severe("Not implemented yet!");
		return false;
	}

	@Override
	public boolean bufferAvailable() {
		logger.severe("Not implemented yet!");
		return false;
	}

	@Override
	public Object getDevice() {
		logger.severe("Not implemented yet!");
		return null;
	}

}
