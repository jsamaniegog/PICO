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
 *
 * @author 12420165G
 */
public class Organ {
    /**
     * @var Object contains a sense.
     */
    private Object sense = null;

    /**
     * Construct.
     */
    public Organ() {
        
    }
    
    /**
     * @return the sense
     */
    public Object getSense() {
        return this.sense;
    }

    /**
     * @param aSense the sense to set
     */
    public void setSense(Object aSense) {
        this.sense = aSense;
    }
    
    /**
     * Listen for a sense.
     */
    public void listen() throws Exception {
        if (this.getSense() == null) {
            throw new Exception("No sense...");
        }
        
        Thread t = new Thread((Runnable) this.getSense());
        t.start();
    }
}