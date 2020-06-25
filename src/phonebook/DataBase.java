/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author VERESG
 */
public class DataBase {
    
    final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    final String URL = "jdbc:derby:sampleDB; create = true";
    final String BENUTZERNAME = "";
    final String KENNWORT = "";
    
    Dialogs msgbox = new Dialogs();
       
    Connection cntn = null;
    Statement createStmt = null;
    DatabaseMetaData dbmetadata = null;
    
    
    public ArrayList<Person> getAllContacts(){
            String sqlquery = "select * from contacts";
            ArrayList<Person> persons = null;
            
            try{
                ResultSet rs = createStmt.executeQuery(sqlquery);
                persons = new ArrayList<>();
                while (rs.next()){
                    Person actualPerson = new Person(rs.getString("dbfirstname"),rs.getString("dblastname"),rs.getString("dbdivision"), rs.getString("dbjobpos"),
                            rs.getString("dbemailpriv"),rs.getString("dbphonepriv"),rs.getString("dbemailoff"),rs.getString("dbphoneoff"),rs.getString("dbremarks"),rs.getString("dbprofilepicurl"));
                            persons.add(actualPerson);
                }
        } catch (SQLException e){
            msgbox.showAlertBox(Alert.AlertType.ERROR, "ExecuteQuery", "Adatbázis lekérdezéshiba:\n"+e);
        }
        return persons;
}
 
    public Person getContact(){
            String sqlquery = "select * from contacts WHERE id = ?";
            Person actualPerson = null;            
            try{
                ResultSet rs = createStmt.executeQuery(sqlquery);
            
                while (rs.next()){
                    actualPerson = new Person(rs.getString("dbfirstname"),rs.getString("dblastname"),rs.getString("dbdivision"), rs.getString("dbjobpos"),
                            rs.getString("dbemailpriv"),rs.getString("dbphonepriv"),rs.getString("dbemailoff"),rs.getString("dbphoneoff"),rs.getString("dbremarks"),rs.getString("dbprofilepicurl"));
                            
                }
        } catch (SQLException e){
            msgbox.showAlertBox(Alert.AlertType.ERROR, "ExecuteQuery", "Adatbázis lekérdezéshiba:\n"+e);
        }
        return actualPerson;
}
    
    
     public void addNewContact(Person persons){
         
        try{
         String sqlquery = "insert into contacts (dbfirstname, dblastname, dbdivision, dbjobpos, dbemailpriv, dbphonepriv, dbemailoff, dbphoneoff, dbremarks, dbprofilepicurl) values (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStmt = cntn.prepareStatement(sqlquery);
            preparedStmt.setString(1, persons.getFirstname());
            preparedStmt.setString(2, persons.getLastname());
            preparedStmt.setString(3, persons.getDivision());
            preparedStmt.setString(4, persons.getJobposition());
            preparedStmt.setString(5, persons.getEmail());
            preparedStmt.setString(6, persons.getPhone());
            preparedStmt.setString(7, persons.getEmail2());
            preparedStmt.setString(8, persons.getPhone2());
            preparedStmt.setString(9, persons.getNotes());
            preparedStmt.setString(10, persons.getProfileimg());
            preparedStmt.execute();
            msgbox.showMessage(null, "Új kontakt: "+persons.getFirstname()+" "+persons.getLastname()+" sikeresen hozzáadva!", "Adatbázis üzenete",  null);
        } catch (SQLException e){
            msgbox.showAlertBox(Alert.AlertType.ERROR, "Új kontakt hozzáadása", "Adatbázishiba:\n"+e);
        }
        
}
    
    public void updateContacts(Person persons){
         
        try{
         String sqlquery = "update contacts set dbfirstname = ?, dblastname = ?, dbdivision = ?, dbjobpos = ?, dbemailpriv = ?, dbphonepriv = ?,"
                 + "dbemailoff = ?, dbphoneoff = ?, dbremarks = ?, dbprofilepicurl = ? where id = ?";
            
         PreparedStatement preparedStmt = cntn.prepareStatement(sqlquery);
            preparedStmt.setString(1, persons.getFirstname());
            preparedStmt.setString(2, persons.getLastname());
            preparedStmt.setString(3, persons.getDivision());
            preparedStmt.setString(4, persons.getJobposition());
            preparedStmt.setString(5, persons.getEmail());
            preparedStmt.setString(6, persons.getPhone());
            preparedStmt.setString(7, persons.getEmail2());
            preparedStmt.setString(8, persons.getPhone2());
            preparedStmt.setString(9, persons.getNotes());
            preparedStmt.setString(10, persons.getProfileimg());
            preparedStmt.setInt(11, Integer.parseInt(persons.getId()));
            preparedStmt.execute();
            msgbox.showMessage(null, "Rekord módosítása: "+persons.getFirstname()+" "+persons.getLastname()+" sikeres!", "Adatbázis üzenete",  null);
                        
        } catch (SQLException e){
            msgbox.showAlertBox(Alert.AlertType.ERROR, "Adatbázis frissítése", "Adatbázishiba:\n"+e);
        }
        
}
    
    public void deleteContact(Person persons){
         
        try{
         String sqlquery = "delete FROM contacts WHERE id = ?";
            
         PreparedStatement preparedStmt = cntn.prepareStatement(sqlquery);
            preparedStmt.setInt(1, Integer.parseInt(persons.getId()));
            
            preparedStmt.execute();
            msgbox.showMessage(null, "Kontakt törlése: "+persons.getFirstname()+" "+persons.getLastname()+" sikeres!", "Adatbázis üzenete",  null);            
        } catch (SQLException e){
            msgbox.showAlertBox(Alert.AlertType.ERROR, "Kontakt törlése", "Adatbázishiba:\n"+e);
        }
        
}
    
    
    public DataBase(){
           
        
        try{
            cntn = DriverManager.getConnection(URL);
            JOptionPane.showMessageDialog(new JFrame(),"Adatbáziskapcsolat létrejött:\n"+URL,"GetConnection",JOptionPane.INFORMATION_MESSAGE);
        }catch (SQLException e){
            msgbox.showAlertBox(Alert.AlertType.ERROR, "GetConnection", "Adatbáziskapcsolat nem jött létre:\n"+e);
        }
        
        if (cntn != null){
            try{
                createStmt = cntn.createStatement();
               }catch (SQLException e){
            msgbox.showAlertBox(Alert.AlertType.ERROR, "CreateStatement", "Adatbáziskapcsolat nem jött létre:\n"+e);
        }
        }
        
        try{
            dbmetadata = cntn.getMetaData();
        }catch (SQLException e){
            msgbox.showAlertBox(Alert.AlertType.ERROR, "GetMetaData", "Adatbáziskapcsolat nem jött létre:\n"+e);
    }
        
        try{
            ResultSet rs = dbmetadata.getTables(null,"APP", "CONTACTS", null);
            if (!rs.next()){
                createStmt.execute("create table contacts(id INT not null primary key GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),dbfirstname varchar(20), dblastname varchar(20),"+
                        "dbdivision varchar(30), dbjobpos varchar(30), dbemailpriv varchar(30), dbphonepriv varchar(20), dbemailoff varchar(30), dbphoneoff varchar(20), dbremarks varchar(255), dbprofilepicurl varchar(255))");
            }
            }catch (SQLException e){
            msgbox.showAlertBox(Alert.AlertType.ERROR, "ResultSet", "Adatbázishiba:\n"+e);
        }
    
    }
}
