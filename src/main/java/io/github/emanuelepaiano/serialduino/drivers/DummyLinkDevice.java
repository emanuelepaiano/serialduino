/*
 * Copyright 2018 Emanuele Paiano
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.emanuelepaiano.serialduino.drivers;

/**
 * This is fake device for debug. It prints messages on console
 * @author Emanuele Paiano
 */
public class DummyLinkDevice implements LinkDevice{

    private boolean connected=true;
    
    
    
    public int write(String data) {
        if(!connected)
            return -1;
        System.out.println("write(): "+data);
        return data.length();
    }

    
    public String read(int bytes) {
        if(!connected)
            return "read(): not connected";
        System.out.println("read(): "+bytes);
        return "read(): "+bytes;
    }

    
    public String read() {
        if(!connected)
            return "read(): not connected";
        System.out.println("read()");
        return "read()";
    }

    
    public boolean open() {
       System.out.println("open()");
       this.connected=true;
       return connected; 
    }

    
    public boolean close() {
       System.out.println("close()");
       this.connected=false;
       return !connected; 
    }

    
    public boolean isReady() {
       System.out.println("isReady(): "+connected);
       return connected; 
    }

    
    public boolean bufferAvailable() {
        return this.connected;
    }

    
    public Object getDevice() {
        return null;
    }
    
}
