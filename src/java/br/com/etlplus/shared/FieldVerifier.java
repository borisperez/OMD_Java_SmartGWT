/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etlplus.shared;


public class FieldVerifier {

    public static boolean isValidName(String name) {
        if (name == null) {
            return false;
        }
        return name.length() > 3;
    }
}
