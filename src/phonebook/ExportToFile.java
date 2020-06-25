/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;




/**
 *
 * @author VERESG
 */
public class ExportToFile {

    Dialogs msgbox = new Dialogs();

    public void exportToPdf(ArrayList<String> content, String profilepic){ 
        
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD);
        Font chapterFont = FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD);
        Font normalFont = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD);
        
        
        
        Document doc = new Document();
        try {
            
            FileChooser savefc = new FileChooser();
            savefc.setTitle("Mentés PDF fájlként");
            File filetopdf = savefc.showSaveDialog(new Stage());
            
            PdfWriter.getInstance(doc, new FileOutputStream(filetopdf+".pdf"));
            
            doc.open();
            Image profilepictopdf = Image.getInstance(profilepic); //abszolút útvonalhoz!!
            profilepictopdf.scaleToFit(400, 200); //kép méretezése!!
                        
            doc.add(new Paragraph("Adatlap",titleFont)); //1. Fejléc
            doc.add(profilepictopdf); //2. Profilkép
            doc.add(new Paragraph("\n\n"+"Személyi adatok",chapterFont)); //3. Fejléc 2
            
            //for (int i=0; i<content.size(); i++){
            for (String next:content){
            doc.add(new Paragraph("\n"+next,normalFont)); //4. Adatok
            }
            
            doc.close();
            
        } catch (Exception ex) {
            msgbox.showAlertBox(AlertType.ERROR,"Hiba az exportálás során!", ex.toString());
            }
        //finally {}
   } 
}
