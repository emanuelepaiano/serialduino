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

package serialduino.examples;
import java.util.logging.Logger;

import serialduino.arduino.ArduinoSerialMonitor;
import serialduino.drivers.ComLinkDevice;

/**
 * Serialduino Serial Port Example
 * Read/Write to Arduino Serial Port
 * 
 * @author Emanuele Paiano - https://emanuelepaiano.github.io
 * 
 * @see sketchs/serial_echo/serial_echo.ino
 * 
 */
public class ComSerialEcho {
	public static void main(String[] args)
	{	
		if (ComLinkDevice.isPortAvailable()){
			
			// COM Port for Linux
			//ComLinkDevice port=new ComLinkDevice(ComLinkDevice.getPortByName("ttyUSB0"), ComLinkDevice.BAUDRATE_9600);
			
			// COM Port for Windows/Dos
			//ComLinkDevice port=new ComLinkDevice(ComLinkDevice.getPortByName("COM1"), ComLinkDevice.BAUDRATE_9600);
			
			
			// First COM serial port
			ComLinkDevice port=new ComLinkDevice(ComLinkDevice.getPorts()[0], ComLinkDevice.BAUDRATE_9600);
			
			ArduinoSerialMonitor arduinoSerial=new ArduinoSerialMonitor(port);
			
			//open and wait for arduino reboot
			arduinoSerial.open();
			
			//if device ready, send "hello" string to Arduino
			arduinoSerial.send("hello");
			
			//Receive echo response: you should see "hello" on java console
			System.out.print(arduinoSerial.receiveString());
			
			//Close connection
			arduinoSerial.close();
		}else{
			Logger.getAnonymousLogger().severe("No serial ports on system. Connect Arduino to USB and try again.");
		}
	}
	
}

