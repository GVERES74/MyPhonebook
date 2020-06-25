/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tooltip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author VERESG
 */
public class Dialogs {
    
    JFrame frame; String message; String title; JOptionPane dialog;
    
    
     public void showMessage(JFrame frame, String message, String title, JOptionPane dialog){
        frame = null;
        this.frame = frame;
        this.message = message;
        this.title = title;
        this.dialog = dialog;
        JOptionPane.showMessageDialog(frame,message,title,JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    public Boolean showAlertBox(Alert.AlertType alerttype, String title, String alerttext){
        Boolean answer = false;
        Alert alert = new Alert(alerttype, alerttext);
        alert.setTitle(title);
        alert.setHeaderText(null);
        Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
             answer = true;   
            }
        return answer;
    }
       
    public Tooltip showToolTip(String tiptext){
        Tooltip tooltip = new Tooltip();
        tooltip.setText(tiptext);
        return tooltip;
    }
}
