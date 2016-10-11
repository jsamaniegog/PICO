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
 * Organ to speak for a sense.
 * @author 12420165G
 */
public class Mouth extends Organ {
    /**
     * Construct.
     */
    public Mouth() {
        super();
        this.setSense(new Console());
    }
    
    public void speak(String text) throws Exception {
        if (this.getSense() == null) {
            throw new Exception("No sense...");
        }
        
        Console s = (Console) this.getSense();
        s.type(text);
    }
}
