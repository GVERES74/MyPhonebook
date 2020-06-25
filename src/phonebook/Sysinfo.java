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
public class Sysinfo {
    
    private final SimpleStringProperty sysinfokey, sysinfovalue;
    
    public Sysinfo(String infoKey, String infoValue){
        this.sysinfokey = new SimpleStringProperty(infoKey);
        this.sysinfovalue = new SimpleStringProperty(infoValue);
        
    }
    
    public Sysinfo(){
        this.sysinfokey = new SimpleStringProperty("");
        this.sysinfovalue = new SimpleStringProperty("");
        
    }
    //A getter -ekre nagyon figyelj!! Átírtad a metódusneveket. Emiatt csesztél el órákat, mert hibásak voltak a nevek, és nem jelenítette meg az értékeket a cellákban!!
    public String getSysinfokey() {
        return sysinfokey.get();
    }

    public String getSysinfovalue() {
        return sysinfovalue.get();
    }

    
   
}
