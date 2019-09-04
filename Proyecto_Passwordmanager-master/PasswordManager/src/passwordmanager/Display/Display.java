/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordmanager.Display;

import console.JSystem;
import java.util.Scanner;
import passwordmanager.Queries;
import passwordmanager.Sha512;

/**
 *
 * @author jsarmiento
 */
public class Display {

    private String name;
    private String email;
    private String password, encryptedPass;
    private String nPassword;
    private String pSeguridad;
    private String rSeguridad;
    private String Proveedor;
    //Instancio Scanner dentro de la funcion Display principal para uso general.
    Scanner teclado = new Scanner(System.in);
    Sha512 encrypt = new Sha512();

    /**
     * **** Instancio de manera general las clases usadas.*****
     */
    HeaderMain callHeaderMain = new HeaderMain();
    //Display callDisplayMetodscallHeaderMain = new Display();
    ValidarEmail callValidator = new ValidarEmail();
    Queries callQueries = new Queries();
    //**************FIN INSTANCIA*****************//

    //Funcion de Inicio de Sesion de Usuarios.
    public void Login() {
        Display callDisplayMetods = new Display();
        int exit = 0;
        do {
            callHeaderMain.headerIniciarSesion();//llamo el menu de inicio de sesion.
            System.out.println("Ingrese Correo Electrónico:\t\t\t\t      (R) for Sign up - (S) for Exit");
            System.out.print(" ");
            email = teclado.nextLine();
            if ("R".equalsIgnoreCase(email)) {
                callDisplayMetods.registroUser();
            } else if ("S".equalsIgnoreCase(email)) {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                callHeaderMain.header();
                callHeaderMain.EndSoftware();
                System.exit(0);
            }
            callValidator.validar(email, "L");//ingreso el correo al validador.

            //ENVÌO EL CORREO A LA QUERY PARA VALIDACIÒN QUE EL USUARIO EXISTE.
            if (!callQueries.validarUser(email)) {
                callDisplayMetods.QuestionRecoverPassword();
            }
            System.out.println("Ingrese su contraseña");
            System.out.print(" ");
            password = teclado.nextLine();
            encryptedPass = encrypt.get_SHA_512_SecurePassword(password.getBytes(), "SHA-512");
            if ("R".equalsIgnoreCase(password)) {
                callDisplayMetods.registroUser();
            } else if ("S".equalsIgnoreCase(password)) {
                System.exit(0);
            }
            //ENVIO PASSWORD Y CORREO A LA QUERY PARA VALIDAR QUE LA CONTRASEÑA ES LA CORRECTA.
            if (callQueries.validarPassword(encryptedPass, email)) {
                callDisplayMetods.Perfil(email);
            } else {
                callDisplayMetods.QuestionRecoverPassword();
            }


            /*/CODIGO PROVICIONAL.
            String bien = "";
            System.out.println("Todo salio bien S/N");
            bien = teclado.nextLine();
            if ("S".equalsIgnoreCase(bien)) {
                //LLAMAR EL PERFIL DE USUARIO.
                callDisplayMetods.Perfil();
            } else if ("N".equalsIgnoreCase(bien)) {
                //LLAMAR CLASE RECUPERAR PASSWORD  
                callDisplayMetods.QuestionRecoverPassword();

            }*/
        } while (exit != 1);
    }

    //Funcion Registro de Usuarios.
    public void registroUser() {
        Display callDisplayMetods = new Display();
        int exit = 0;

        do {
            JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
            JSystem.out.printColorln(JSystem.Color.blue, "\n---------------PasswordManager, Registrate a Continuacion , ¡Siempre Gratis!--------------");
            JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
            //Llamo al menu de registro.
            callHeaderMain.headerRegistro();
            callDisplayMetods.dateregistroUser();
        } while (exit != 1);
    }

    public void dateregistroUser() {
        Display callDisplayMetods = new Display();
        System.out.println("Ingresa tu nombre:\t\t\t\t\t\t              (C) for Cancel");
        System.out.print(" ");
        name = teclado.nextLine();
        System.out.println();//space line.
        callDisplayMetods.cancelar(name, null, null, null, null);
        System.out.println("Ingrese su correo:");
        System.out.print(" ");
        email = teclado.nextLine();
        System.out.println();//space line.
        callDisplayMetods.cancelar(null, email, null, null, null);
        callValidator.validar(email, "R");//mando el correo al validador para la verificacion.
        System.out.println("Ingrese su Contraseña:");
        System.out.print(" ");
        password = teclado.nextLine();
        encryptedPass = encrypt.get_SHA_512_SecurePassword(password.getBytes(), "SHA-512");
        System.out.println();//space line.
        callDisplayMetods.cancelar(null, null, password, null, null);
        System.out.println("Seleccione una pregunta de seguridad:");
        System.out.println("1) ¿Cuál es el nombre de tu primer mascota?");
        System.out.println("2) ¿Cuál es el nombre de tu abuelo?");
        System.out.println("3) ¿Últimos dígitos de tu número de teléfono?");
        System.out.print(" ");
        int opt = teclado.nextInt();
        switch (opt) {
            case 1:
                pSeguridad = "¿Cuál es el nombre de tu primer mascota?";
                break;
            case 2:
                pSeguridad = "¿Cuál es el nombre de tu abuelo?";
                break;
            case 3:
                pSeguridad = "¿últimos dígitos de tu número de teléfono?";
                break;
            default:
                break;
        }
        teclado.nextLine();//limpio la cache de Scanner.
        callDisplayMetods.cancelar(null, null, null, pSeguridad, null);
        System.out.println();//space line.
        System.out.println("Ingrese la respuesta secreta:");
        System.out.print(" ");
        rSeguridad = teclado.nextLine();
        callDisplayMetods.cancelar(null, null, null, null, rSeguridad);

        //ENVIO DE LAS VARIABLES A LA QUERY QUE REGISTRARA LA INFORMACION DEL USUARIO NUEVO
        callQueries.insertarUsuario(name, email, encryptedPass, pSeguridad, rSeguridad);
    }

    public void cancelar(String name, String email, String password, String pSecreta, String rSecreta) {
        Display callDisplayMetods = new Display();
        if ("C".equalsIgnoreCase(name) || "C".equalsIgnoreCase(email) || "C".equalsIgnoreCase(password) || "C".equalsIgnoreCase(pSecreta) || "C".equalsIgnoreCase(rSecreta)) {
            JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
            JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "\n-----------------------------------SUSCRIPCIÓN CANCELADA------------------------------------");
            JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
            callDisplayMetods.Login();
        }
    }

    public void QuestionVerificationEmail() {
        Display callDisplayMetods = new Display();
        JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "\n-------------------El email ingresado no cumple los requisitos como correo------------------");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "\t\t\t\tDeseas Registrarte Ahora-->'S/N'\t\t\t    ");
        JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
        System.out.print(" ");
        String rsp = teclado.next();
        if (rsp.equalsIgnoreCase("S")) {
            teclado.nextLine();//limpiare la cache.
            callDisplayMetods.registroUser();//llamo a la pantalla registro de usuarios.    
        } else if (rsp.equalsIgnoreCase("N")) {
            teclado.nextLine();//limpiare la cache.  
            callDisplayMetods.Login();//llamo a login de usurios.
        } else {
            JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
            JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "\n------------------Ha ocurrido un error, dato introducido incorrectamente-------------------");
            JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
            callDisplayMetods.Login();//llamo a login de usurios.
        }
    }

    public void QuestionVerificationEmailRegister() {
        Display callDisplayMetods = new Display();
        JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "\n-------------------El email ingresado no cumple los requisitos como correo------------------");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "\t\t\tDeseas Continuar con el registro --> 'S/N'\t\t\t    ");
        JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
        System.out.print(" ");
        String rsp = teclado.next();
        if (rsp.equalsIgnoreCase("S")) {
            teclado.nextLine();//limpiare la cache.
            callDisplayMetods.dateregistroUser();
            //Contunuo con el registro. 
            callDisplayMetods.registroUser();
        } else if (rsp.equalsIgnoreCase("N")) {
            teclado.nextLine();//limpiare la cache.  
            JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
            JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
            System.out.println();
            callDisplayMetods.Login();//llamo a login de usurios.

        } else {
            JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
            JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "\n------------------Ha ocurrido un error, dato introducido incorrectamente-------------------");
            JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
            callDisplayMetods.Login();//llamo a login de usuarios.
        }
    }

    public void QuestionRecoverPassword() {
        Display callDisplayMetods = new Display();
        JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "\n-------------La información introducida no se encuentran en nuestra base de datos------------");
        JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
        JSystem.out.printColor(JSystem.Color.cyan, "¿TIENES PROBLEMAS PARA INGRESAR A TU CUENTA?");
        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.red, "S/N");
        String opt = teclado.nextLine();
        if ("S".equalsIgnoreCase(opt)) {
            callDisplayMetods.RecoverPassword();
        } else if ("N".equalsIgnoreCase(opt)) {
            callDisplayMetods.Login();//llamo a login de usuarios.  
        }
    }

    public void RecoverPassword() {
        Display callDisplayMetods = new Display();
        callHeaderMain.headerRecoverPassword();
        JSystem.out.printColorln(JSystem.Color.blue, "DEBES PROPORCIONAR LOS SIGUIENTES DATOS PARA RECUPERAR TU ACCESO.");
        JSystem.out.printColorln(JSystem.Color.cyan, "☻ Dirección de correo electrónico");
        JSystem.out.printColorln(JSystem.Color.cyan, "☻ Respuesta a la pregunta de seguridad");
        JSystem.out.printColor(JSystem.Color.blue, "CONTINUAR ");
        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.red, "S/N");
        System.out.print(" ");
        String opt = teclado.nextLine();
        if ("S".equalsIgnoreCase(opt)) {
            callDisplayMetods.DatesRecoverPassword();
        } else if ("N".equalsIgnoreCase(opt)) {
            callDisplayMetods.Login();//llamo a login de usuarios.  
        }
    }

    public void DatesRecoverPassword() {
        Display callDisplayMetods = new Display();
        System.out.println("Ingrese su Correo electrónico");
        System.out.print(" ");
        email = teclado.nextLine();
        callValidator.validar(email, "S");
        System.out.println();
        System.out.println("Ingrese la respuesta a la siguiente pregunta");

        //SACAR LA PREGUNTA DE LA BD.
        JSystem.out.printColor(JSystem.Color.blue, " ");
        System.out.println(callQueries.retornarPreguntaSeguridad(email));
        System.out.print(" ");
        rSeguridad = teclado.nextLine();

        /*REALIZAR LA CONSULATA A LA BD SI EXISTE LA RESPUESTA
            SI EXISTE CONTINUAR*/
        if (callQueries.validarRespuestaSeguridad(email, rSeguridad)) {
            System.out.println("Ingrese su nueva contraseña");
            System.out.print(" ");
            nPassword = teclado.nextLine();
            encryptedPass = encrypt.get_SHA_512_SecurePassword(nPassword.getBytes(), "SHA-512");
            //REALIZAR EL UPDATE DE LA NUEVA PASSWORD A LA BD.
            callQueries.insertarNuevaPassword(encryptedPass, email);

            JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
            JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.blue, "\n                          LA CUENTA SE HA RECUPERADO EXITOSAMENTE                           ");
            JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.white, "\t\t\t SE LE HA ENVIADO UN CORREO DE CONFIRMACIÓN\t\t\t    ");
            JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.blue, "\t\t\tYA PUEDES INICIAR SESIÓN CON TU NUEVA CUENTA\t\t\t    ");
            JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
            callDisplayMetods.Login();
        } else {
            JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
            JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "\n-----------------La respuesta introducida es incorrecta, intentalo de nuevo----------------");
            JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
            callDisplayMetods.DatesRecoverPassword();
        }

    }

    public void QuestionVerificationEmailRecover() {
        Display callDisplayMetods = new Display();
        JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "\n-------------------El email ingresado no cumple los requisitos como correo------------------");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "\t\t\tDeseas Continuar con la recuperacion --> 'S/N'\t\t\t    ");
        JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
        System.out.print(" ");
        String rsp = teclado.next();
        if (rsp.equalsIgnoreCase("S")) {
            teclado.nextLine();//limpiare la cache.
            callDisplayMetods.DatesRecoverPassword();
        } else if (rsp.equalsIgnoreCase("N")) {
            teclado.nextLine();//limpiare la cache.
            callDisplayMetods.Login();//llamo a login de usurios.
        } else {
            JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
            JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "\n------------------Ha ocurrido un error, dato introducido incorrectamente-------------------");
            JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
            callDisplayMetods.Login();//llamo a login de usuarios.
        }
    }

    public void Perfil(String email) {
        Display callDisplayMetods = new Display();
        int salir = 0;
        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.white, "\n\n\n");
        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.white, "                                                                                            ");
        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.black, "                          BIENVENIDO A SU CUENTA DE PASSWORD MANAGER                        ");
        //JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.black, "                 ID:" + callQueries.retornarID(email) + "                                    NOMBRE:" + callQueries.retornarNombre(email) + "         ");
        JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.black, "                                                                                            ");
        do {
            callHeaderMain.headerPerfil();
            System.out.println("Menú de opciones");
            System.out.println(" 1) Ingresar Nueva Cuenta\n 2) Ver Cuentas.\n 3) Cerrar Sesion");
            System.out.print(" ");
            String opt = teclado.nextLine();

            try {
                if ("S".equalsIgnoreCase(opt)) {
                    callHeaderMain.EndSoftware();
                }
                switch (opt) {
                    case "1":
                        System.out.println("Ingreso nueva cuenta");
                        callDisplayMetods.newAccount(email);
                        break;
                    case "2":
                        System.out.println("Ingreso ver cuentas");
                        callDisplayMetods.ViewAcount(email);
                        break;
                    case "3":
                        System.out.println("Ingreso cerrar sesion");
                        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "                                                                                              ");
                        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "                           LA SESIÓN SE HA CERRADO SATISFACTORIAMENTE                         ");
                        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "                                                                                              ");
                        callDisplayMetods.Login();
                        break;
                    default:;
                        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "______________________________NO HA ELEGIDO NINGUNA DE LAS OPCIONES_________________________");
                        callDisplayMetods.Perfil(email);
                        break;
                }
            } catch (Exception e) {
                System.out.println("Ha sucedido un error en el sistema");
            }
        } while (salir != 0);
    }

    public void newAccount(String userEmail) {
        Display callDisplayMetods = new Display();
        callHeaderMain.headerNewAcount();
        JSystem.out.printColorln(JSystem.Color.blue, "Ingrese los datos de su nueva cuenta");
        System.out.println("Proveedor de su cuenta");
        System.out.print(" ");
        Proveedor = teclado.nextLine();
        System.out.println("Correo Electrónico");
        System.out.print(" ");
        email = teclado.nextLine();
        //verificare si el correo es correcto.
        callValidator.validar(email, "NC");
        System.out.println("Clave de su cuenta");
        System.out.print(" ");
        password = teclado.nextLine();
        
        //INGRESAR ESTOS DATOS A LA BASE DE DATOS.
        callQueries.insertarCuenta(Proveedor, email, password, callQueries.retornarID(userEmail));
        /*//codigo provicionl.
        JSystem.out.printColorln(JSystem.ColorBg.blue, JSystem.Color.yellow, "\t\t\t        INFORMACIÓN INTRODUCIDA CORRECTAMENTE       \t\t");
        callDisplayMetods.Perfil();
        //Fin codigo provicional*/
    }

    public void QuestionVerificationNewAcount(String email) {
        Display callDisplayMetods = new Display();
        JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "\n-------------------El email ingresado no cumple los requisitos como correo------------------");
        JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "\t\t   Deseas Continuar introduciendo su nueva cuenta --> 'S/N'\t\t    ");
        JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
        System.out.print(" ");
        String rsp = teclado.next();
        if (rsp.equalsIgnoreCase("S")) {
            teclado.nextLine();//limpiare la cache.
            callDisplayMetods.newAccount(email);
        } else if (rsp.equalsIgnoreCase("N")) {
            teclado.nextLine();//limpiare la cache.
            callDisplayMetods.Perfil(email);
        } else {
            JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
            JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "\n------------------Ha ocurrido un error, dato introducido incorrectamente-------------------");
            JSystem.out.printColorln(JSystem.Color.blue, "____________________________________________________________________________________________");
            callDisplayMetods.Login();//llamo a login de usuarios.
        }
    }

    public void ViewAcount(String email) {
        Display callDisplayMetods = new Display();
        callHeaderMain.headerMyAcount();
        int salir = 0;
        
        do {
            JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
            JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.black, "Proveedor de cuenta\t" + "Correo Electronico\t\t\t" + "Clave de Acceso\t\t"+"    ");
            JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
            

            //AÑADIR AQUI LOS DATOS CONSULTADOS DE LA BASE DE DATOS.
            
            callQueries.mostrarCuentas(callQueries.retornarID(email));
            JSystem.out.printColorln(JSystem.Color.cyan, "____________________________________________________________________________________________");
            JSystem.out.printColor(JSystem.Color.blue, "CONTINUAR ");
            JSystem.out.printColorln(JSystem.ColorBg.yellow, JSystem.Color.red, "C");
            System.out.print(" ");
            String opt = teclado.nextLine();
            if ("C".equalsIgnoreCase(opt)) {
                callDisplayMetods.Perfil(email);
            } else {
                System.out.println();
                JSystem.out.printColorln(JSystem.ColorBg.red, JSystem.Color.white, "                                    INGRESO UNA OPCIÓN EQUIVOCADA                           ");
                System.out.println();
            }
        } while (salir != 2);

    }
}
