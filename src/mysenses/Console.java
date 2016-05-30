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

import java.util.Scanner;

/**
 * Create an object to manage IO by console.
 * @author 12420165G
 */
public class Console extends Sense implements Runnable {
    /**
     * @var object Scanner.
     */
    final private static Scanner s = new Scanner(System.in);
    
    public Console() {
        super();
    }
    
    /**
     * This method initializes this sense. In this class, the method initializes 
     * a listener for the console, sends a request and waits for the response 
     * all the time. When received is processing and continue listening for new
     * requests.
     */
    @Override
    public void run() {
        //this.ask(ResourceBundle.getBundle("language").getString("ask"));
        this.sendToBrain(this.ask(": "));
    }
    
    /**
     * @return the scanner object.
     */
    public static Scanner getS() {
        return s;
    }
    
    /**
     * Type a text into standard output.
     * @param text 
     */
    public void type(String text) {
        System.out.println(text);
    }
    
    /**
     * Ask for a request.
     * @param request
     * @return String response of user.
     */
    public String ask(String request) {
        System.out.println(request);
        return s.nextLine();
    }
}
