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

package io.github.emanuelepaiano.serialduino.examples;

import io.github.emanuelepaiano.serialduino.arduino.ArduinoSerialMonitor;
import io.github.emanuelepaiano.serialduino.drivers.TcpLinkDevice;

/**
 * Serialduino Serial Port Example using ESP8266 Wifi Monitor
 * Read/Write to Arduino Serial Port. 
 * For test, upload sketchs/serial_echo/serial_echo.ino
 * to your board.
 * 
 * @author Emanuele Paiano - https://emanuelepaiano.github.io
 * 
 * 
 * 
 */
public class TcpSerialEcho {
	public static void main(String[] args)
	{				
		
		// Connect to 10.0.0.5:80 on your ESP8266 Wifi
		TcpLinkDevice port=new TcpLinkDevice("10.0.0.5", 80);
		
		ArduinoSerialMonitor arduinoSerial=new ArduinoSerialMonitor(port);
		
		//open and wait for arduino reboot
		arduinoSerial.open();
		
		//if device ready, send "hello" string to Arduino
		arduinoSerial.send("hello");
		
		//Receive echo response: you should see "hello" on java console
		System.out.print(arduinoSerial.receiveString());
		
		//Close connection
		arduinoSerial.close();
	
	}
	
}

