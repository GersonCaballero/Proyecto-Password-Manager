/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordmanager;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author gerso_000
 */
public class Sha512 {
    
    public String get_SHA_512_SecurePassword(byte[] inputBytes, String algorithm){
    String generatedPassword = "";
    
    try {
         MessageDigest md = MessageDigest.getInstance(algorithm);
         md.update(inputBytes);
         byte[] digestedBytes = md.digest();
         generatedPassword = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();
        } 
       catch (Exception e){
        e.printStackTrace();
       }
    return generatedPassword;
}   
    
}
