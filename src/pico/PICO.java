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
package pico;

import java.util.logging.Level;
import java.util.logging.Logger;
import mybrain.*;
import mysenses.*;

/**
 * Clase para iniciar a PICO.
 * @author 12420165G
 * @version 1.0
 */
public class PICO {
    /**
     * @var Brain The PICO's brain.
     */
    private Brain brain = new Brain();
    
    /**
     * @var Ear The PICO's ear.
     */
    private Ear ear = new Ear();
    
    /**
    * @var string Nombre del programa.
    */
    private static String name = "PICO";
    
    public static void main(String[] args) {
        
        PICO pico = new PICO();
        
        try {
            pico.ear.listen();
        } catch (Exception ex) {
            Logger.getLogger(PICO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the name
     */
    public static String getName() {
        return name;
    }
    
}
