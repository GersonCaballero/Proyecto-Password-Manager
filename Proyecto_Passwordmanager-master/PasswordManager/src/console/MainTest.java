package console;
/**
 * @see http://www.jc-mouse.net/
 * @author mouse
 */
public class MainTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //texto aburrido
        System.out.println("Text normal..... aburrido\n");
        
        /**
         * Texto coloreado
        */
        
        //Texto de color azul
        JSystem.out.printColorln(JSystem.Color.blue, "Hola Mundo...");
        
        //texto en una sola linea
        JSystem.out.printColor(JSystem.Color.cyan, "Amar es… ");
        JSystem.out.printColor(JSystem.Color.red, " borrar el Windows del disco de ella \n\n");
        
        //Texto y fondo
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "Mouse no encontrado. ¿Usted tiene gato? (S/N)\n");
        
        JSystem.out.printColor(JSystem.ColorBg.blue, JSystem.Color.yellow, "Hay dos formas de escribir programas sin errores. ");
        JSystem.out.printColor(JSystem.ColorBg.yellow, JSystem.Color.blue, "Sólo la tercera funciona.\n\n");
        
        //combinando 
        JSystem.out.printColor(JSystem.Color.green, "Sabes que es amor cuando memorizas su numero de ");
        JSystem.out.printColor(JSystem.ColorBg.red, JSystem.Color.white, " IP ");        
        JSystem.out.printColor(JSystem.Color.green, " para saltar el ");
        JSystem.out.printColor(JSystem.ColorBg.yellow, JSystem.Color.black, " DNS \n\n");
        
        JSystem.out.printColorln(JSystem.ColorBg.magenta, JSystem.Color.white, " BYE ");       
        
    }
    
}
