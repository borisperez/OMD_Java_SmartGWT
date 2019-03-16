/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.etlplus.client.objects;

import com.nkdata.gwt.streamer.client.Streamable;
import java.util.ArrayList;

public final class User implements Streamable {

    private String UserName, Password, ConfPassword, CourtesyTitle, FirstName, LastName, JobTitle, OfficePhone, MobilePhone, Email, Role;
    private boolean consistencia;
    private int UserId;

    public User() {
        super();
        setUserName("");
        setPassword("");
        setConfPassword("");
        setCourtesyTitle("");
        setFirstName("");
        setLastName("");
        setJobTitle("");
        setOfficePhone("");
        setMobilePhone("");
        setEmail("");
        setRole("");
    }

//    public User(String UserName, String Password, String ConfPassword, String CourtesyTitle, String FirstName, String LastName, String JobTitle, String OfficePhone, String MobilePhone, String Email) {
//        super();
//        setUserName(UserName);
//        setPassword(Password);
//        setConfPassword(ConfPassword);
//        setCourtesyTitle(CourtesyTitle);
//        setFirstName(FirstName);
//        setLastName(LastName);
//        setJobTitle(JobTitle);
//        setOfficePhone(OfficePhone);
//        setMobilePhone(MobilePhone);
//        setEmail(Email);
//        setRole("");
//        setScreens(null);
//    }

    public void setConsistencia(boolean Consistencia) {
        this.consistencia = Consistencia;
    }

    public boolean getConsistencia() {
        return consistencia;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        if (UserName.length() > 0) {
            this.UserName = UserName;
        } else {
            this.UserName = "";
            setConsistencia(false);
            return;
        }
        setConsistencia(true);
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        if (Password.length() > 0) {
            this.Password = Password;
        } else {
            this.Password = "";
            setConsistencia(false);
            return;
        }
        setConsistencia(true);
    }

    public String getConfPassword() {
        return ConfPassword;
    }

    public void setConfPassword(String ConfPassword) {
        if (ConfPassword.length() > 0) {
            this.ConfPassword = ConfPassword;
        } else {
            this.ConfPassword = "";
            setConsistencia(false);
            return;
        }
        setConsistencia(true);
    }

    public String getCourtesyTitle() {
        return CourtesyTitle;
    }

    public void setCourtesyTitle(String CourtesyTitle) {
        this.CourtesyTitle = CourtesyTitle;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        if (FirstName.length() > 0) {
            this.FirstName = FirstName;
        } else {
            this.FirstName = "";
            setConsistencia(false);
            return;
        }
        setConsistencia(true);
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        if (LastName.length() > 0) {
            this.LastName = LastName;
        } else {
            this.LastName = "";
            setConsistencia(false);
            return;
        }
        setConsistencia(true);
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(String JobTitle) {
        this.JobTitle = JobTitle;
    }

    public String getOfficePhone() {
        return OfficePhone;
    }

    public void setOfficePhone(String OfficePhone) {
        this.OfficePhone = OfficePhone;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String MobilePhone) {
        this.MobilePhone = MobilePhone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
            this.Role = role;
    }

    public ArrayList<String> getCampos() {
        ArrayList<String> Campos;
        Campos = new ArrayList<String>();
        Campos.add("UserId");
        Campos.add("UserName");
        Campos.add("Password");
        Campos.add("ConfPassword");
        Campos.add("CourtesyTitle");
        Campos.add("FirstName");
        Campos.add("LastName");
        Campos.add("JobTitle");
        Campos.add("OfficePhone");
        Campos.add("MobilePhone");
        Campos.add("Email");
        return Campos;
    }
}
