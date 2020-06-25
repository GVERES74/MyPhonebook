/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 *
 * @author VERESG
 */
public class Infodata {

Label oslabel, userlabel, datetimelabel;    
    
     private final ObservableList<Sysinfo> infodata = 
            FXCollections.observableArrayList(
            new Sysinfo("Operációs rendszer", getSysInfoOpSys()),
            new Sysinfo("OS Verzió", getSysInfoOsVer()),
            new Sysinfo("Felhasználó",getSysInfoUserName()),
            new Sysinfo("Coder","Gabor Veres - All Rights Reserved - 2018 - Dj Red Software"),
            new Sysinfo("Programverzió","v0.1a"),
            new Sysinfo("Release dátum","2018.08.05")
            );
    
    
     protected String getSysInfoOpSys(){
        String opsys = System.getProperty("os.name");
        return opsys;
    }
        
    protected String getSysInfoOsVer(){
        String osver = System.getProperty("os.version");
        return osver;
    }
    
    protected String getSysInfoUserName(){    
        String usrname = System.getProperty("user.name");
        return usrname;
    }
    
    
     public void setInfoHBox(Label oslabel, Label userlabel, Label datetimelabel, TableView tblviewmenuinfo){
        //Az alsó Infopanelhez
        String datenow = new DateCallFuncs().getCurrentdate();
        String timenow = new DateCallFuncs().getCurrenttime();
        oslabel.setText("Operációs rendszer: "+getSysInfoOpSys()+" / Verzió: "+getSysInfoOsVer());
        userlabel.setText("Felhasználó: "+getSysInfoUserName());    
        datetimelabel.setText("Dátum / idő: "+datenow+" "+timenow);
        
         //Az Infotáblához
        TableColumn keyCol = new TableColumn(""); keyCol.setMinWidth(200); keyCol.setCellFactory(TextFieldTableCell.forTableColumn()); keyCol.setCellValueFactory(new PropertyValueFactory<Sysinfo, String>("sysinfokey"));
        TableColumn valueCol = new TableColumn(""); valueCol.setMinWidth(320); valueCol.setCellFactory(TextFieldTableCell.forTableColumn()); valueCol.setCellValueFactory(new PropertyValueFactory<Sysinfo, String>("sysinfovalue"));
        tblviewmenuinfo.getColumns().addAll(keyCol,valueCol);
        tblviewmenuinfo.setEditable(false);
        tblviewmenuinfo.setItems(infodata);
        
    }
    
    
}
