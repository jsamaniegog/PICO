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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Manage input/output data in the brain.
 * @author 12420165G
 */
public class MemoryIO {
    
    private final static String MEMORYPATH = "brain";
    
    public MemoryIO() {
        
    }
    
    /**
     * Generate a string from a byte array. The string contains square brackets to open and close the string.
     * @param data
     * @return Data in string format.
     */
    private static String getStringFromData(byte[] data) {
        String dataToString = Arrays.toString(data);
        dataToString = dataToString.replaceAll("\\s+",""); //elimina espacios en blanco
        
        return dataToString;
    }
    
    /**
     * save the data (in string format, see MemoryIO.getStringFromData) to a file.
     */
    private static void saveMemory(String dataToString) throws IOException, FileNotFoundException {
        FileWriter file;
        file = new FileWriter(MemoryIO.MEMORYPATH, true);
        file.write(dataToString);
        file.close();
    }
    
    /**
     * Escribe información en los ficheros de memoria.
     * @param data
     * @throws IOException 
     * @throws java.io.FileNotFoundException 
     */
    public static void setMemory(byte[] data) throws IOException, FileNotFoundException {
        // la cadena resultante guardada tendrá formato de matriz encerrada por corchetes {[matriz]}.
        String dataToString = MemoryIO.getStringFromData(data);
        MemoryIO.saveMemory("{" + dataToString + "}");
    }
    
    /**
     * Escribe información en los ficheros de memoria.
     * @param data Bidimensional array.
     * @param control
     * @throws IOException 
     * @throws java.io.FileNotFoundException 
     */
    public static void setMemory(byte[][] data) throws IOException, FileNotFoundException {
        // la cadena resultante guardada tendrá formato de matriz encerrada por corchetes {[matriz1],[matriz2]}.
        String dataToString = "{";
        for (int i = 0; i < data.length; i++) {
            if (i > 1) {
                dataToString += ",";
            }
            dataToString += MemoryIO.getStringFromData(data[i]);
        }
        dataToString += "}";
        
        MemoryIO.saveMemory(dataToString);
    }
    
    /**
     * Accede a los ficheros de la "memoria" del cerebro y retorna la estructura.
     * @param columns size of columns to search in the matrix of memory, set to 
     * -1 if you want any size
     * @param rows size of rows to search in the matrix of memory, set to 
     * -1 if you want any size
     * @return byte[matrix][vector][number]
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static byte[][][] getMemory(int columns, int rows) throws FileNotFoundException, IOException {
        final Charset ENCODING = StandardCharsets.UTF_8;
        
        String result = "";
        
        Path path = Paths.get(MemoryIO.MEMORYPATH);
        try (BufferedReader reader = Files.newBufferedReader(path, ENCODING)){
            String line = null;
            while ((line = reader.readLine()) != null) {
                //process each line in some way
                result += line;
            }      
        }
        
        // split in each matrix
        String[] resultSplited = result.split(Pattern.quote("}{"));
        resultSplited[0] = resultSplited[0].replace("{", "");
        resultSplited[resultSplited.length - 1] = resultSplited[resultSplited.length - 1].replace("}", "");
        
        byte[][][] datainbytes = new byte[resultSplited.length][][];
        
        int numArrays = 0; // count the number of valid arrays to return
        
        for (int i = 0; i < resultSplited.length; i++) {
            
            // split in each vector of the matrix
            String[] matrixSplited = resultSplited[i].split(Pattern.quote("],["));
            matrixSplited[0] = matrixSplited[0].replace("[", "");
            matrixSplited[matrixSplited.length - 1] = matrixSplited[matrixSplited.length - 1].replace("]", "");
            
            datainbytes[i] = new byte[matrixSplited.length][];
            
            for (int j = 0; j < matrixSplited.length; j++) {
                // split in each number of the vector
                String[] numbersSplited = matrixSplited[j].split(Pattern.quote(","));
                
                datainbytes[i][j] = new byte[numbersSplited.length];
                
                if ((columns != -1 && rows != -1)
                        && (columns != matrixSplited.length 
                        || rows != numbersSplited.length)) {
                    // if the matrix hasn't the dimensions required pass to the next
                    continue;
                }
                
                numArrays++;
                
                for (int k = 0; k < numbersSplited.length; k++) {
                    datainbytes[i][j][k] = Byte.parseByte(numbersSplited[k]);
                }
            }
        }
        
        byte[][][] datainbytesfinal = new byte[numArrays][][];
        // reindex the array
        for (int i = 0, j = 0; i < datainbytes.length; i++) {
            if (datainbytes[i][0][0] != 0) {
                datainbytesfinal[j] = datainbytes[i];
                j++;
            }
        }
        
        return datainbytesfinal;
    }
}
