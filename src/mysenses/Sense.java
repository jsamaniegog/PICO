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

import mybrain.Brain;

/**
 * Abstract Class for senses.
 * @author 12420165G
 */
public abstract class Sense {

    /**
     * @var String the name of the sense.
     */
    private String senseName = null;

    private final Brain brain;

    /**
     * Constructor.
     */
    public Sense() {
        this.brain = new Brain();
        this.setSenseName(this);
    }

    /**
     * Inicializate the sense.
     */
    //abstract public void initSense();
    
    /**
     * @return the senseName
     */
    public String getSenseName() {
        return this.senseName;
    }

    /**
     * Sets the name of the sense.
     * @param object A class.
     */
    public void setSenseName(Object object) {
        this.senseName = object.getClass().getSimpleName();
    }
    
    /**
     * Send information (and the control) to the brain.
     * @param data 
     */
    public void sendToBrain(String data) throws Exception {
        this.brain.setData(data);
    }
}
