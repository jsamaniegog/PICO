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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import mysenses.*;

/**
 *
 * @author 12420165G
 */
public class Brain extends Organ {
    /**
     * @var String data
     */
    private byte[] data = null;
    
    /**
     * @var String response of the brain
     */
    private String response = null;
    
    /**
     * Constructor.
     */
    public Brain() {
        
    }

    /**
     * @return the data
     */
    public byte[] getData() {
        return data;
    }

    /**
     * Catch data, transform and process.
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data.getBytes();
        this.processData();
    }

    /**
     * @return the response
     */
    public String getResponse() {
        return response;
    }

    /**
     * @param response the response to set
     */
    private void setResponse(String response) {
        this.response = response;
    }
    
    /**
     * Process the data received and returns a response.
     */
    private void processData() {
        ArrayList<Neuron> n = Brain.getNeuronNet(this.data);
        
        // search a pattern in the memory
        ArrayList<Neuron> result = this.compareNeuronPattern(n);
        
        // TODO: diferentes tipos de salida según la acción
        this.setSense(new Console());
        Console s = (Console) this.getSense();

        // if matches a pattern
        if (result != null) {
            // action
            s.type(Arrays.toString(this.data));
        } else {
            // action if not have a result
            this.setResponse(Arrays.toString(this.data));
            s.type(Arrays.toString(this.data));
        }
    }
    
    /**
     * Compare patterns of neuron nets.
     * @param n the neuron net to compare with the memory
     * @return null|ArrayList<Neuron> memory neuron net matches or null if error
     */
    private ArrayList<Neuron> compareNeuronPattern(ArrayList<Neuron> n) {
        // get memory neuron net
        ArrayList<Neuron> memoryNeuronNet = new ArrayList<>();
        try {
            byte[] memory = MemoryIO.getMemory();
            
            // create de the neuron net of the memory
            memoryNeuronNet = Brain.getNeuronNet(memory);
            
        } catch (IOException ex) {
            return null;
        
        } finally {
            

            // compare memory with neuron pattern got of the data received
            if (!n.equals(memoryNeuronNet)) {
                // if not equals save the new data in memory
                try {
                    MemoryIO.setMemory(this.data);
                } catch (IOException ex) {
                    return null;
                }
            }
        }
        
        return n;
    }
    
    /**
     * Create a net of neurons with data received.
     * @param data
     * @return ArrayList<Neuron> Neuron net.
     */
    public static ArrayList<Neuron> getNeuronNet(byte[] data) {
        // array of conected neurons
        ArrayList<Neuron> n = new ArrayList<Neuron>();
        
        // Add a neuron to the array setting the portion(byte) of data
        for (byte b : data) {
            boolean add = n.add(new Neuron(b));
        }
        
        // Add net of neurons to all neurons for each one knows about his conections
        n.stream().forEach((next) -> {
            next.setNeuronsConected(n);
        });
        
        return n;
    }
}
