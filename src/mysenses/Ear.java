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
package mysenses;

/**
 * Organ to listen for a sense.
 * @author 12420165G
 */
public class Ear extends Organ {
    
    /**
     * Construct.
     */
    public Ear() {
        super();
        this.setSense(new Console());
    }
    
    /**
     * Listen.
     */
    public void listen() throws Exception {
        if (this.getSense() == null) {
            throw new Exception("No sense...");
        }
        
        Thread t = new Thread((Runnable) this.getSense());
        t.start();
    }
}
