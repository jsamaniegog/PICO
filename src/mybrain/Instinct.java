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

/**
 * Manage the primary instincts.
 * @author 12420165G
 */
public class Instinct {
    final static int CURIOSITY = 0;
    final static int IMITATION = 1;
    final static int ACTION    = 2;
    final static int EMPATHY   = 3;
    
    private static boolean isBetween(double x, double lower, double upper) {
        return lower <= x && x <= upper;
    }
    
    /**
     * Returns an instinct according to a percentage of similarity
     * @param percentage of similarity
     * @return 
     */
    public static int getInstinct(double percentage) {
        if (isBetween(percentage, 90, 100)) {
            return IMITATION;
        
        } else {
            if (percentage == 0) {
                return CURIOSITY;
            }
            
            // by default
            return IMITATION;
        }
    }    
}
