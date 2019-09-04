/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordmanager.Display;
import console.JSystem;
import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 *
 * @author JSarmiento
 */
public class HeaderMain {
   
   public void header(){
       Calendar calendario = new GregorianCalendar();
       int hora, minutos, segundos;
       hora =calendario.get(Calendar.HOUR_OF_DAY);
       minutos = calendario.get(Calendar.MINUTE);
       segundos = calendario.get(Calendar.SECOND);
       JSystem.out.printColorln(JSystem.Color.cyan,"____________________________________________________________________________________________");
       JSystem.out.printColorln(JSystem.Color.cyan, " ****DEVELOPERS -> Gerson Caballero, Victor Sandobal, Francisco Ayala, Jesus Sarmiento*****");
       System.out.println("********************************************************************************************"); 
       System.out.println("*                              PASSWORD MANAGER CEUTEC-2018©                               *");
       System.out.println("*******************************                             ********************************");
       System.out.println("*******************************                             ********************************");
       System.out.println("___________________________________________"+ hora + ":" + minutos + ":" + segundos + ""
               + "__________________________________________");
       JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.black,"                                                                                             ");
      
       }
   public void headerIniciarSesion(){
       JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
       JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
       System.out.println("\t\t\t\t\t**************");
       System.out.println("\t\t\t\t\tINICIAR SESIÓN");    
       System.out.println("\t\t\t\t\t**************");    
   }
    
   public void headerRegistro(){
       JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
       JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
       System.out.println("\t\t\t\t\t**************");
       System.out.println("\t\t\t\t\tCREAR TU CUENTA");    
       System.out.println("\t\t\t\t\t**************");   
   }
   public void headerPerfil(){
       JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
       JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
       System.out.println("\t\t\t\t\t**************");
       System.out.println("\t\t\t\t\tPERFIL USUARIO");    
       System.out.println("\t\t\t\t\t**************");  
   }
   
   public void headerRecoverPassword(){
       JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
       JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
       System.out.println("\t\t\t\t\t**************");
       System.out.println("\t\t\t\tRECUPERAR EL ACCESO A TU CUENTA");    
       System.out.println("\t\t\t\t\t**************");  
   }
   
   public void headerNewAcount(){
       JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
       JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
       System.out.println("\t\t\t\t\t**************");
       System.out.println("\t\t\t\t\t NUEVA CUENTA");    
       System.out.println("\t\t\t\t\t**************");  
   }
   
   public void headerMyAcount(){
       JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
       JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
       System.out.println("\t\t\t\t\t**************");
       System.out.println("\t\t\t\t      CUENTAS PERSONALES");    
       System.out.println("\t\t\t\t\t**************");  
   }
   
   
   public void EndSoftware(){
    JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white,"                                                                                            ");
    JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white,"                                                                                            ");
    JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white,"                                                                                            ");
    JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white,"                                                                                            ");
    JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white,"                                                                                            ");
    JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white,"                           PASSWORD MANAGER SE HA CERRADO CON ÉXITO                         ");
    JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white,"            Gracias por confiar en nuestros métodos de seguridad de contraseñas.            ");
    JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white,"                                                                                            ");
    JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white,"                                                                                            ");
    JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white,"            CRÉDITOS                                                                        ");
    JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.yellow,"                    DIEGO TURCIOS                                                           ");
    JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.yellow,"                    FRANCISCO AYALA                                                         ");
    JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.yellow,"                    VÍCTOR SANDOBAL                                                         ");
    JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.yellow,"                    GERSON CABALLERO                                                        ");
    JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.yellow,"                    JESÚS SARMIENTO                                                         ");
    JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.cyan,"                                                                                            ");
    JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.cyan,"                                                                                            ");
    JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white,"                     CEUTEC-NORTE, SAN PEDRO SULA, CORTÉS, HONDURAS C.A                     ");
    JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.cyan,"                                                                                            ");
    JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.cyan,"                                                                                            ");
    JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.cyan,"                                                                                            ");
    JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.cyan,"                                                                                            ");
    System.exit(0);
   }
}