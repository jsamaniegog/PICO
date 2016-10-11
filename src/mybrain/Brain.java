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
import mysenses.*;

/**
 *
 * @author 12420165G
 */
public class Brain extends Organ {
    /**
     * @var String the data received
     */
    private byte[][] dataInput = null;
    
    /**
     * @var double percentage of similarity between data input and matrix of 
     * "neurons"
     */
    private double percentSimilarity;
    /**
     * @var zero-ones matrix result of comparation between data input and matrix 
     * of "neurons"
     */
    private byte[][] matrixSimilarity = null;
    
    /**
     * Constructor.
     */
    public Brain() {
        
    }

    /**
     * @return the data
     */
    public byte[][] getDataInput() {
        return dataInput;
    }
    
    /**
     * Returns the data input in string format.
     * @return 
     */
    public String getStringFromBytes(byte[] bytes) {
        return new String(bytes);
    }

    /**
     * Catch data as string, transform to bytes and process.
     * @param data the data to set
     */
    public void setData(String data) throws Exception {
        this.dataInput = new byte[1][];
        this.dataInput[0] = data.getBytes(); // transform input text into byte[]
        this.processData();
    }

    /**
     * Process the data (this.data) received and returns a response.
     */
    private void processData() throws Exception {
        Mouth mouth = new Mouth();
        
        try {
            // search a pattern in the memory
            this.compareNeuronPattern();
        } catch (Exception ex) {
            mouth.speak(ex.getMessage());
        }
        
        // action
        switch (Instinct.getInstinct(this.percentSimilarity)) {

            case Instinct.CURIOSITY:
                try {
                    MemoryIO.setMemory(dataInput);
                    
                    // CURIOSITY try things from obtains a response and if it's good save it
                    mouth.speak(getStringFromBytes(this.dataInput[0]));
                    Ear ear = new Ear();
                    ear.listen();
                    
                } catch (IOException ex) {
                    mouth.speak(ex.getMessage());
                }    
                break;

            case Instinct.IMITATION: 
                mouth.speak(getStringFromBytes(this.dataInput[0]));
                break;

            case Instinct.ACTION: break;

            case Instinct.EMPATHY: break;
        }
    }
    
    /**
     * Compare patterns of "neuron nets"(matrices).
     * @param n the neuron net to compare with the memory
     * @return null|byte[][] a matrix with the result of comparation with the memory
     * @throws IOException
     */
    private void compareNeuronPattern() throws IOException, Exception {
        // the memory is returned in a matrix of bidimensional matrices
        byte[][][] memory;
        
        memory = MemoryIO.getMemory(this.dataInput.length, this.dataInput[0].length);
        
        if (memory == null) {
            if(memory[0] == null) {
            throw new Exception("Memory unreachable");
        }}
        
        // contains the percentage of siliraty
        int percent = 0;
        
        // for each matrix of the "memory" compare with the data received
        for (byte[][] matrix : memory) {
            try {
                this.compareMatrixEquallyOperation(matrix, this.dataInput);
            } catch (Exception ex) {
                // the matrices are not the same dimensions
                continue;
            }
            
            // get out of iteration if we have 100% of similarity
            if (this.percentSimilarity == 100) {
                break;
            }
        }
    }
    
    /**
     * 
     * @param bytes
     * @return 
     */
    public static double[] bytesToDouble(byte[] bytes) {
        double[] doubles = new double[bytes.length];
        
        for (int i = 0; i < bytes.length; i++) {
            doubles[i] = (double) bytes[i];
        }
        
        return doubles;
    }
    
    /**
     * 
     * @param bytes
     * @return 
     */
    public static double[][] bytesToDouble(byte[][] bytes) {
        double[][] doubles = new double[bytes.length][];
        
        for (int i = 0; i < bytes.length; i++) {
            for (int j = 0; j < bytes[i].length; j++) {
                //doubles[i][j] = (double) bytes[i][j];
                doubles[i] = Brain.bytesToDouble(bytes[i]);
            }
        }
        
        return doubles;
    }
    
    /**
     * Get two matrix, compare both and return a matrix with zero-ones. Each one
     * represents that this position has the same value in both matrices.
     * @return a matrix zero-ones with the result
     * @return null if the dimensions not are equall
     */
    private void compareMatrixEquallyOperation(byte[][] a, byte[][] b) throws Exception {
        if (a.length != b.length || a[0].length != b[0].length) {
            throw new Exception("The matrices are not the same dimensions");
        }
        
        byte[][] result = new byte[a.length][a[0].length];
        
        int ones = 0;
        
        for (int i = 0; i < a.length; i++) {
            
            for (int j = 0; j < a[0].length; j++) {
                
                byte dato_a = a[i][j];
                byte dato_b = b[i][j];
                
                if (dato_a == dato_b) {
                    result[i][j] = 1;
                    ones++;
                } else {
                    result[i][j] = 0;
                }
                
            }
        }
        
        this.percentSimilarity = ones * 100 / (a.length * a[0].length);
        this.matrixSimilarity = result;
    }
}
