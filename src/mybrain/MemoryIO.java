/*
 * Copyright (C) 2016 12420165G
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package mybrain;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.*;

/**
 * Manage input/output data in the brain.
 * @author 12420165G
 */
public class MemoryIO {
    public MemoryIO() {
        
    }
    
    /**
     * Escribe información en los ficheros de memoria.
     * @param data
     * @throws IOException 
     */
    public static void setMemory(byte[] data) throws IOException {
        
        FileWriter file;
        file = new FileWriter("brain", false);
        
        // convert to neuron net
        ArrayList<Neuron> n = new ArrayList<>();
        n = Brain.getNeuronNet(data);
        
        for (Neuron neuron : n) {
            file.write(neuron.toString());
        }
        
        file.close();
    }
    
    /**
     * Accede a los ficheros de la "memoria" del cerebro y retorna la estructura.
     * @return ArrayList\<Neuron\> Neuron net.
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public static byte[] getMemory() throws FileNotFoundException, IOException {
        ArrayList<Byte> data = new ArrayList<>();
        
        FileReader file;
        file = new FileReader("brain");
        
        int c = file.read();
        data.add((byte) c);
        
        while (c != -1) {
            c = file.read();
            data.add((byte) c);
        }
           
        file.close();
        
        int i = 0;
        byte[] datainbytes = new byte[data.size()-1];
        for (i = 0; i < data.size()-1; i++) {
            datainbytes[i] = (byte) data.get(i);
        }
        
        return datainbytes;
    }
}
