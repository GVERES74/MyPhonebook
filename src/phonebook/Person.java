/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author VERESG
 */
public class Person {
    private final SimpleStringProperty id, firstname, lastname, division, jobposition, email, email2, phone, phone2, notes, profileimg;
    
    public Person(String fName, String lName, String dIvision, String jobPos, String eMail, String eMail2, String phoneNr, String phoneNr2, String cNotes, String profileImage){
        this.id = new SimpleStringProperty("");
        this.firstname = new SimpleStringProperty(fName);
        this.lastname = new SimpleStringProperty(lName);
        this.division = new SimpleStringProperty(dIvision);
        this.jobposition = new SimpleStringProperty(jobPos);
        this.email = new SimpleStringProperty(eMail);
        this.email2 = new SimpleStringProperty(eMail2);
        this.phone = new SimpleStringProperty(phoneNr);
        this.phone2 = new SimpleStringProperty(phoneNr2);
        this.notes = new SimpleStringProperty(cNotes);
        this.profileimg = new SimpleStringProperty(profileImage);
        
    }

    public Person(){
        this.id = new SimpleStringProperty("");
        this.firstname = new SimpleStringProperty("");
        this.lastname = new SimpleStringProperty("");
        this.division = new SimpleStringProperty("");
        this.jobposition = new SimpleStringProperty("");
        this.email = new SimpleStringProperty("");
        this.email2 = new SimpleStringProperty("");
        this.phone = new SimpleStringProperty("");
        this.phone2 = new SimpleStringProperty("");
        this.notes = new SimpleStringProperty("");
        this.profileimg = new SimpleStringProperty("");
    }

        
    public String getFirstname() {
        return firstname.get();
    }

    public String getLastname() {
        return lastname.get();
    }

             
    public String getFullname(){
        return firstname.get()+" "+lastname.get();
    }

    public String getDivision() {
        return division.get();
    }

    public String getJobposition() {
        return jobposition.get();
    }
    
    public String getEmail() {
        return email.get();
    }
    
    public String getEmail2() {
        return email2.get();
    }
    
    public String getPhone() {
        return phone.get();
    }
    
    public String getPhone2() {
        return phone2.get();
    }
    
    public String getNotes() {
        return notes.get();
    }

    public String getProfileimg() {
        return profileimg.get();
    }
        
    public void setFirstname(String fName) {
        firstname.set(fName);
    }

    public void setLastname(String lName) {
        lastname.set(lName);
    }
    
       
    public void setDivision(String dIvision) {
        division.set(dIvision);
    }

    public void setJobposition(String jobPos) {
        jobposition.set(jobPos);
    }
    
    public void setEmail(String eMail) {
        email.set(eMail);
    }
    
     public void setEmail2(String eMail2) {
        email2.set(eMail2);
    }
     
    public void setPhone(String phoneNr) {
        phone.set(phoneNr);
    }
     
    public void setPhone2(String phoneNr2) {
        phone2.set(phoneNr2);
    }
     
    public void setNotes(String cNotes) {
        notes.set(cNotes);
    }
    
    public void setProfileimg(String profileImage) {
        profileimg.set(profileImage);
    }

    public String getId() {
        return id.get();
    }
    
    public void setId(String cId) {
        id.set(cId);
    }
}
