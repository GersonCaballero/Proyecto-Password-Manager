/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordmanager;

import passwordmanager.Display.Display;
import passwordmanager.Display.HeaderMain;

/**
 *
 * @author Alumnos
 */
public class PasswordManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        HeaderMain callHeader=new HeaderMain();
        Display callDisplay = new Display();
        callHeader.header();
        callDisplay.Login();
    }
    
}
