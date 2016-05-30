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

import java.util.ArrayList;

/**
 * A basic unity of process.
 * @author 12420165G
 */
public class Neuron {
    /**
     * @var ArrayList of conected neurons
     */
    private ArrayList<Neuron> neuronsConected = new ArrayList<>();
    
    /**
     * @var byte Level of excitation of the neuron. (byte goes -128 to 127)
     */
    private byte excitationLevel = 0;

    /**
     * Class constructor.
     * @param b A byte. Unity of information.
     */
    public Neuron(byte b) {
        this.setExcitationLevel(b);
    }
    
    /**
     * @return the neuronsConected
     */
    public ArrayList<Neuron> getNeuronsConected() {
        return neuronsConected;
    }

    /**
     * @param neuronsConected the neuronsConected to set
     */
    public void setNeuronsConected(ArrayList neuronsConected) {
        this.neuronsConected = neuronsConected;
    }

    /**
     * Conect a neuron to this neuron.
     * @param neuron 
     */
    public void addNeuronConnection(Neuron neuron) {
        this.neuronsConected.add(neuron);
    }
    
    /**
     * @return the excitationLevel
     */
    public byte getExcitationLevel() {
        return excitationLevel;
    }

    /**
     * @param excitationLevel the excitationLevel to set
     */
    public void setExcitationLevel(byte excitationLevel) {
        this.excitationLevel = excitationLevel;
    }
}
