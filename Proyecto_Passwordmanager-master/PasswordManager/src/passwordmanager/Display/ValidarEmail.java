
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordmanager.Display;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jsarmiento
 */
public class ValidarEmail {

    public void validar(String email, String opt) {

        Display callDisplayMetods = new Display();
        // Patrón para validar el email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);
        if ("L".equalsIgnoreCase(opt)) {
            if (mather.find() == true) {
                //No le pondre nada para que continue la app.
            } else {
                callDisplayMetods.QuestionVerificationEmail();
            }
        } else if ("R".equalsIgnoreCase(opt)) {
            if (mather.find() == true) {
                //No le pondre nada para que continue la app.
            } else {
                callDisplayMetods.QuestionVerificationEmailRegister();
            }
        }else if("S".equalsIgnoreCase(opt)){
            if (mather.find() == true) {
                //No le pondre nada para que continue la app.
            } else {
                callDisplayMetods.QuestionVerificationEmailRecover();
            }
        }else if("NC".equalsIgnoreCase(opt)){
            if (mather.find() == true) {
                //No le pondre nada para que continue la app.
            } else {
                callDisplayMetods.QuestionVerificationNewAcount(email);
            }
        }
    }

}
