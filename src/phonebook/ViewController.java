/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author VERESG
 */
public class ViewController implements Initializable {
    
    @FXML
    protected Label label, userlabel, oslabel, datetimelabel, nodeitemlabel, namelabel, profileimgpathlabel, numofuserslabel;
    @FXML
    private Button btn_felvitel, infopanelclosebutton, btn_saveaspdf, btn_saveasxls, btn_picfileupload, bigimgbtn, replaceimgbtn, btn_printdatasheet, btn_changedatasheet, btn_savedatasheet;
    @FXML
    public TextField inputfirstname, inputlastname, inputdivision, inputjobpos, inputemail, inputemail2, inputphone, inputphone2, tf_filenamexls, tf_filenamepdf;
    @FXML
    public TextField dsfirstnametf, dslastnametf, dsdivtf, dsjobpostf, dsemailtf, dsemail2tf, dsphonetf, dsphone2tf;
    @FXML
    private TextArea textareanotes, dsnotestextarea;
    @FXML
    protected TableView tableview, tblviewmenuinfo;
    @FXML
    private StackPane menupane;
    @FXML
    private SplitPane splitpane;
    @FXML
    private Pane exportpane, contactpane, searchpane, menuinfopanel, newcontactpane, exportofile, datasheetpane;
    @FXML
    private AnchorPane anchorpane, bkgpane;
    @FXML
    private HBox infohbox;
    @FXML
    private MenuItem menu_info_sysinfo, menu_info_coderinfo, menu_info_proginfo, menu_setup_background, MenuItem_Delete;
    @FXML
    private ImageView profilepicuploadimgview, newuserimgview;  
      
    Person someone = new Person();
    DataBase db = new DataBase();
    TableColumn firstNameCol, lastNameCol, divisionCol, jobpositionCol, emailCol, email2Col, phoneCol, phone2Col, notesCol, profileimgCol;
    
    Dialogs msgbox = new Dialogs();
    
    
    //addNodePic() metódushoz
    Node nodepic; String picfile;
    
    private Background bkgforbuttons = new Background(new BackgroundFill(Color.AQUA, null, null));

    String pojoprofileimgurl = "file:/C:/Downloads/ciges.jpg";
    String dbprofileimg = "";
    
    
    
        ArrayList<TextField> dstextfields = new ArrayList<>();
    
    //Osztályváltozóként hozzuk létre, később elég itt (1x) módosítani a stringeket
    private final String MENUA_CONTACTS = "Kontaktok";
    private final String MENU_NEW = "Új kontakt";
    private final String MENU_SEARCH = "Keresés";
    private final String MENU_LIST = "Lista";
    
    private final String MENUB_ACTIONS = "Műveletek";
    private final String MENU_EXPORT = "Exportálás";
    private final String MENU_PRINT = "Nyomtatás";
    
    private final String CONTACT_MAIN_IMG = "/img/cmain.jpg";
    private final String CONTACT_NEW_IMG = "/img/newuser.jpg";
    private final String CONTACT_SEARCH_IMG = "/img/finduser.png";
    private final String CONTACT_LIST_IMG = "/img/contacts.png";
    private final String ACTIONS_MAIN_IMG = "/img/actions.jpg";
    private final String EXP_EXC_IMG = "/img/exportexcel.png";
    private final String PRINT_IMG = "/img/print.png";
    
    //A jó öreg kamu tesztrekordokra már nincs szükség, ha működik az adatbázisunk. RIP.
    /*private final ObservableList<Person> data = 
            FXCollections.observableArrayList(
            new Person("Veres", "Gábor", "Minőségirányítás", "SQE vezető", "djveresg@freemail.hu", "gabor.veres@te.com", "06-30-274-1072", "06-66-286-472", "...a talented software developer..", userprofileimgurl),
            new Person("Uhrin", "Zoltán", "Minőségirányítás", "MIR mérnök", "zuhrin@freemail.hu", "zoltan.uhrin@te.com", "06-30-487-3365", "06-66-286-440", "...Zolcsi..", userprofileimgurl)
                    );*/
    private final ObservableList<Person> data = FXCollections.observableArrayList();
    
        
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        anchorpane.setDisable(true);
        Object[] options = {"Igen", "Mégsem"};
        JFrame framex = null;
           int z = JOptionPane.showOptionDialog(framex,
                                    "Biztosan kilépsz",
                                    "Figyelem!",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    options,
                                    options[1]);
                    if (z == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    } else if (z == JOptionPane.NO_OPTION) {
                        anchorpane.setDisable(false);
                    }
             
    }
    
    @FXML //datasheetpane -n adatok megváltoztatása
    private void changeContactsData(){
        btn_printdatasheet.setVisible(false); btn_changedatasheet.setVisible(false); btn_savedatasheet.setVisible(true);
        
         dstextfields.add(dsfirstnametf); dstextfields.add(dslastnametf); dstextfields.add(dsdivtf); dstextfields.add(dsjobpostf); dstextfields.add(dsemailtf); 
         dstextfields.add(dsemail2tf); dstextfields.add(dsphonetf); dstextfields.add(dsphone2tf); 
         for (TextField tf: dstextfields){tf.setEditable(true); tf.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, new CornerRadii(1), new Insets(1))));} dsnotestextarea.setEditable(true);
    }
    
    @FXML
    private void saveContactsData(){
        if (msgbox.showAlertBox(AlertType.CONFIRMATION,"Figyelem!", "Biztosan rögíted az adatokat?") == true){
        btn_printdatasheet.setVisible(true); btn_changedatasheet.setVisible(true); btn_savedatasheet.setVisible(false);
        
         dstextfields.add(dsfirstnametf); dstextfields.add(dslastnametf); dstextfields.add(dsdivtf); dstextfields.add(dsjobpostf); dstextfields.add(dsemailtf); 
         dstextfields.add(dsemail2tf); dstextfields.add(dsphonetf); dstextfields.add(dsphone2tf); 
         for (TextField tf: dstextfields){tf.setEditable(false); tf.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(1), new Insets(1))));} 
         dsnotestextarea.setEditable(false); dsnotestextarea.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(1), new Insets(1))));
         msgbox.showMessage(null,"Az adatokat mentettük","Információ",null);
    }
        else return;
    }
    
    @FXML
    private void closeMenuInfoPanel(){
        menuinfopanel.setVisible(false);
        bkgpane.setDisable(false);
        bkgpane.setOpacity(1.0);
        splitpane.setDisable(false);
        splitpane.setOpacity(1.0);
                
    }
    
    @FXML
    private void showFullProfileImg(){
        Pane bigimgpane = new Pane();
        Stage bigimgstage = new Stage();
        bigimgstage.setTitle("Profilkép nagyítása");
        bigimgstage.setHeight(400); bigimgstage.setWidth(500);
        bigimgstage.setMinHeight(400); bigimgstage.setMinWidth(500);
        ImageView bigimgview = new ImageView(new Image(dbprofileimg));
        
        bigimgview.setFitWidth(bigimgstage.getWidth()); bigimgview.setFitHeight(bigimgstage.getHeight());
        bigimgview.fitWidthProperty().bind(bigimgstage.widthProperty()); bigimgview.fitHeightProperty().bind(bigimgstage.heightProperty()); //képméret bindelése az ablakmérethez
        bigimgview.setPreserveRatio(true); //képarányt is megőrízzük, ne legyen torzítás
        
        bigimgpane.getChildren().add(bigimgview);
        Scene scene = new Scene(bigimgpane);        
        bigimgstage.setScene(scene);
        bigimgstage.initModality(Modality.APPLICATION_MODAL);
        bigimgstage.show();
        
    }
    
    @FXML
    private void showMenuSysInfo(){
        String allsysinfo = ("Operációs rendszer: "+new Infodata().getSysInfoOpSys()+"\nVerzió: "+new Infodata().getSysInfoOsVer()+"\nFelhasználó: "+new Infodata().getSysInfoUserName());    
        msgbox.showMessage(null,allsysinfo,"Rendszer info",null);
    }
   
    
    @FXML
    private void showMenuCoderInfo(){
        
        msgbox.showMessage(null,"Készítette: Veres Gábor\n Minden jog fenntartva\n DjRed Software - 2018","Készítő info",null);
        
    }
       
    @FXML
    private void showMenuInfoPanel(){
               
        //Párbeszédablak középre...saját találmány. ;)
        double xpos = (anchorpane.getWidth()/2)-(menuinfopanel.getWidth()/2);
        double ypos = (anchorpane.getHeight()/2)-(menuinfopanel.getHeight()/2);
        
        bkgpane.setDisable(true);
        bkgpane.setOpacity(0.3);
        splitpane.setDisable(true);
        splitpane.setOpacity(0.3);
       
        menuinfopanel.setLayoutX(xpos); menuinfopanel.setLayoutY(ypos);
        menuinfopanel.setVisible(true);
        
    }
    
    @FXML
    private void setupBackground(){
        Stage bkgstage = new Stage(); 
        bkgstage.setMinWidth(200); bkgstage.setMinHeight(100);
        bkgstage.setTitle("Háttérszín kiválasztása");
        Pane cppane = new Pane();
        Scene cpscene = new Scene(cppane);
        ColorPicker bkgcolorpicker = new ColorPicker();              
        cppane.getChildren().add(bkgcolorpicker);
        bkgstage.setScene(cpscene);
        bkgstage.show();
        
        bkgcolorpicker.setOnAction((ActionEvent t) -> {
            bkgpane.setBackground(new Background(new BackgroundFill(bkgcolorpicker.getValue(), new CornerRadii(1), new Insets(1))));
    });
                      
    }
    
    @FXML
    private void addNewContact(){
        
        setProfilePane();
        if (msgbox.showAlertBox(AlertType.CONFIRMATION,"Figyelem!", "Biztosan rögíted az adatokat?") == true){
            
            Person newContact = new Person(inputfirstname.getText(), inputlastname.getText(), inputdivision.getText(), inputjobpos.getText(), inputemail.getText(), inputphone.getText(), inputemail2.getText(), inputphone2.getText(), textareanotes.getText(), dbprofileimg);
            data.addAll(newContact);
            db.addNewContact(newContact);
        
            clearInputFields();
        getNumOfContacts();
    }
        else clearInputFields(); 
            return;
    }
    
    
    private void removeContact(){
        
        setProfilePane();
        if (msgbox.showAlertBox(AlertType.CONFIRMATION,"Figyelem!", "Biztosan törlöd a rekordot?") == true){
            
            Object selectedPerson = tableview.getSelectionModel().getSelectedItem();
            tableview.getItems().remove(selectedPerson);
                        
        
            clearInputFields();
        getNumOfContacts();
    }
        else clearInputFields(); 
            return;
    }

    
    @FXML
    private void uploadProfileImg() throws MalformedURLException{
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Képfájlok (*.jpg, *.png","*.jpg","*.png"));
        File profileimgfile = fc.showOpenDialog(new Stage());
        
        Image image = new Image(profileimgfile.toURI().toString());
        String imageurl = profileimgfile.toURI().toString();
        profilepicuploadimgview.setImage(image);
        profileimgpathlabel.setText(imageurl);
        
        newuserimgview.setImage(new Image(imageurl));
        dbprofileimg = saveImage(profileimgfile.getAbsolutePath(), "C:/MyPhonebook/userdata/img/"+imageurl.hashCode()+".jpg");
        
        namelabel.setText(namelabel.getText() != "Kontaktszemély" ? inputfirstname.getText()+" "+inputlastname.getText() : "Kontaktszemély");
        
    }
    
    
    private String saveImage(String sourceimgpath, String targetimgpath) {
        BufferedImage image = null;
        File srcfile = null;
        File outfile = null;

        //read image file
        try{
            srcfile = new File(sourceimgpath);
            image = ImageIO.read(srcfile);
        }catch(IOException e){
            System.out.println("Error: "+e+srcfile);
    }

        //write image
        try{
            outfile = new File(targetimgpath);
            ImageIO.write(image, "jpg", outfile);
        }catch(IOException e){
            System.out.println("Error: "+e+outfile);
    }
    
    return outfile.toURI().toString();
 } 


    
    public void setTableData(){                
            
        ArrayList<TableColumn> tablecols = new ArrayList<>();
            tablecols.add(firstNameCol); tablecols.add(lastNameCol); tablecols.add(divisionCol); tablecols.add(jobpositionCol); tablecols.add(emailCol); tablecols.add(email2Col); tablecols.add(phoneCol); tablecols.add(phone2Col); tablecols.add(notesCol); tablecols.add(profileimgCol);
        
        ArrayList<String> cellvalues = new ArrayList<String>();
            cellvalues.add("firstname"); cellvalues.add("lastname"); cellvalues.add("division"); cellvalues.add("jobposition"); cellvalues.add("email"); cellvalues.add("email2"); cellvalues.add("phone"); cellvalues.add("phone2"); cellvalues.add("notes"); cellvalues.add("profileimg");
                        
        ArrayList<TextField> listoftextfields = new ArrayList<>();
            listoftextfields.add(dsfirstnametf); listoftextfields.add(dslastnametf); listoftextfields.add(dsdivtf); listoftextfields.add(dsjobpostf); listoftextfields.add(dsemailtf); 
            listoftextfields.add(dsemail2tf); listoftextfields.add(dsphonetf); listoftextfields.add(dsphone2tf); 
        
        
            
        TableColumn firstNameCol = addTableColumn("Vezetéknév", 100, "firstname");
        TableColumn lastNameCol = addTableColumn("Keresztnév", 100, "lastname");
        TableColumn divisionCol = addTableColumn("Osztály", 100, "division");
        TableColumn jobpositionCol = addTableColumn("Beosztás", 100, "jobposition");
        TableColumn emailCol = addTableColumn("Privát e-mail", 200, "email");
        TableColumn email2Col = addTableColumn("Munkahelyi e-mail", 200, "email2");
        TableColumn phoneCol = addTableColumn("Privát telefon", 100, "phone");
        TableColumn phone2Col = addTableColumn("Munkahelyi telefon", 100, "phone2");
        TableColumn notesCol = addTableColumn("Megjegyzések", 300, "notes");
        TableColumn profileimgCol = addTableColumn("Profilkép", 100, "profileimg");
        
        
        
       
        //Cellák szerkeszthetősége
        emailCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Person, String>>() {
            @Override
                public void handle(CellEditEvent<Person, String> t) {
                    ((Person) t.getTableView().getItems().get(t.getTablePosition().getRow())).setEmail(t.getNewValue());
        }
    }
);
        
        firstNameCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Person, String>>() {
            @Override
                public void handle(CellEditEvent<Person, String> t) {
                    ((Person) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFirstname(t.getNewValue());
        }
    }
);
        
        lastNameCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Person, String>>() {
            @Override
                public void handle(CellEditEvent<Person, String> t) {
                    ((Person) t.getTableView().getItems().get(t.getTablePosition().getRow())).setLastname(t.getNewValue());
        }
    }
);    
        
        phoneCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Person, String>>() {
            @Override
                public void handle(CellEditEvent<Person, String> t) {
                    ((Person) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPhone(t.getNewValue());
        }
    }
);
        
        tableview.getColumns().addAll(firstNameCol,lastNameCol,divisionCol,jobpositionCol,emailCol,email2Col,phoneCol,phone2Col,notesCol,profileimgCol);
        
        data.addAll(db.getAllContacts());
        tableview.setItems(data);
        
        getNumOfContacts();
        setProfilePane();
        
        tableview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            public void changed(ObservableValue observable, Object oldvalue, Object newvalue){
 
            TablePosition pos = (TablePosition) tableview.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                
                Object selectedRow = tableview.getItems().get(row);
                
                TableColumn col = pos.getTableColumn(); //ez az alap, ha cellaĂ©rtĂ©ket akarsz kivĂˇlasztani...

               
                String firstname = (String) firstNameCol.getCellObservableValue(selectedRow).getValue(); //....de nekĂĽnk a kivĂˇlasztott sorok bizonyos oszlopainak Ă©rtĂ©ke kell
                String lastname = (String) lastNameCol.getCellObservableValue(selectedRow).getValue();
                String division = (String) divisionCol.getCellObservableValue(selectedRow).getValue();
                String jobposition = (String) jobpositionCol.getCellObservableValue(selectedRow).getValue();
                String email = (String) emailCol.getCellObservableValue(selectedRow).getValue();
                String email2 = (String) email2Col.getCellObservableValue(selectedRow).getValue();
                String phone = (String) phoneCol.getCellObservableValue(selectedRow).getValue();
                String phone2 = (String) phone2Col.getCellObservableValue(selectedRow).getValue();
                String notes = (String) notesCol.getCellObservableValue(selectedRow).getValue();
                String profileimg = (String) profileimgCol.getCellObservableValue(selectedRow).getValue();
                dsfirstnametf.setText(firstname); dslastnametf.setText(lastname); dsdivtf.setText(division); dsjobpostf.setText(jobposition);
                dsemailtf.setText(email); dsemail2tf.setText(email2); dsphonetf.setText(phone); dsphone2tf.setText(phone2); dsnotestextarea.setText(notes);
                profilepicuploadimgview.setImage(new Image(profileimg)); dbprofileimg = profileimg; //na, itt adjuk át a kijelölt rekordhoz tartozó profilkép url -t az imédzsvjúnak
            }           
    });    
    }
    
    
    private void setMenuData(){
        
        TreeItem<String> treeItemRoot1 = new TreeItem<>("Kontaktmenü");
            TreeView<String> treeView = new TreeView<>(treeItemRoot1);
            treeView.setShowRoot(false);
                //TreeItem<String> nodeItemA = new TreeItem<>(MMENU_CONTACTS); nodeItemA.setExpanded(true); 
                    
                    //Node newContactNodePic = new ImageView(new Image(getClass().getResourceAsStream(CONTACT_NEW_IMG))); De inkább saját metódussal csináljuk. ;)
                    Node newContactNodePic = addNodePic(CONTACT_NEW_IMG);
                    Node findContactNodePic = addNodePic(CONTACT_SEARCH_IMG); 
                    Node contactListNodePic = addNodePic(CONTACT_LIST_IMG); 
                    Node expToExcelNodePic = addNodePic(EXP_EXC_IMG); 
                    Node printNodePic = addNodePic(PRINT_IMG); 
                    
                    //Csináljunk ágakat az addNodeItem (makeBranch) metódussal..(listaelemszöveg,szülő,kép)...vagy a kép helyett "null", ha nem akarsz képet
                    TreeItem<String> nodeItemA = addNodeItem(MENUA_CONTACTS,treeItemRoot1, new ImageView(new Image(getClass().getResourceAsStream(CONTACT_MAIN_IMG))));
                    TreeItem<String> nodeItemB = addNodeItem(MENUB_ACTIONS,treeItemRoot1, new ImageView(new Image(getClass().getResourceAsStream(ACTIONS_MAIN_IMG))));
                                     
                                                 addNodeItem(MENU_NEW,nodeItemA, newContactNodePic);
                                                 addNodeItem(MENU_SEARCH,nodeItemA, findContactNodePic);
                                                 addNodeItem(MENU_LIST,nodeItemA, contactListNodePic);
                                                 addNodeItem(MENU_EXPORT,nodeItemB, expToExcelNodePic);
                                                 addNodeItem(MENU_PRINT,nodeItemB, printNodePic);
                    
                    
                    /*TreeItem<String> nodeItemA1 = new TreeItem<>(MENU_LIST, contactListNodePic); //nodeItemA1.setGraphic(contactsNode); Kétféleképpen is lehet képet hozzárendelni a menüponthoz
                    TreeItem<String> nodeItemA2 = new TreeItem<>(MENU_EXPORT, expToExcelNodePic); //nodeItemA2.setGraphic(expToExcelNode);
                    TreeItem<String> nodeItemA3 = new TreeItem<>(MENU_PRINT, printNodePic); //nodeItemA3.setGraphic(printNode);
                    nodeItemA.getChildren().addAll(nodeItemA1, nodeItemA2, nodeItemA3);       
                    treeItemRoot1.getChildren().add(nodeItemA);*/
        
        menupane.getChildren().add(treeView);
        
        treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            public void changed(ObservableValue observable, Object oldvalue, Object newvalue){
                TreeItem<String> selectedItem = (TreeItem<String>) newvalue;
                    String selectedMenu = selectedItem.getValue();
                    String selectedParent = (selectedItem.getParent().getValue() != null ? selectedItem.getParent().getValue() : selectedItem.getValue());
                    nodeitemlabel.setText("Listaelem: "+selectedParent+"\\"+selectedMenu);
                    
                    if (selectedMenu != null){
                    switch (selectedMenu){
                        case MENUA_CONTACTS: try {selectedItem.setExpanded(true);} catch (Exception ex){} break;
                        case MENU_NEW: try {setPanesDisabled(); newcontactpane.setVisible(true);} catch (Exception ex){} break;
                        case MENU_SEARCH: try {setPanesDisabled(); searchpane.setVisible(true);} catch (Exception ex){} break;
                        case MENU_LIST: try {setPanesDisabled(); datasheetpane.setVisible(true);} catch (Exception ex){} break;
                        case MENUB_ACTIONS: try {selectedItem.setExpanded(true);} catch (Exception ex){} break;
                        case MENU_EXPORT: try {setPanesDisabled(); exportofile.setVisible(true);} catch (Exception ex){} break;
                        case MENU_PRINT: try {setPanesDisabled();} catch (Exception ex){} break;
                    }
                }    
            }
        
    });
}
    
    private TableColumn addTableColumn(String colTitle, int minWidth, String cellValue){
        
        TableColumn colName = new TableColumn(colTitle); colName.setMinWidth(minWidth); colName.setCellFactory(TextFieldTableCell.forTableColumn()); colName.setCellValueFactory(new PropertyValueFactory<Person, String>(cellValue));
        return colName;
    }
    
    
    private void getNumOfContacts(){
        numofuserslabel.setText("Kontaktok száma: "+data.size());
        }
            
    private void clearInputFields(){
        ArrayList<TextField> inputfields = new ArrayList<>();
        inputfields.add(inputfirstname); inputfields.add(inputlastname); inputfields.add(inputdivision); inputfields.add(inputjobpos); inputfields.add(inputemail);
        inputfields.add(inputemail2); inputfields.add(inputphone); inputfields.add(inputphone2);
        for (TextField tf: inputfields){tf.clear();} textareanotes.clear();
        profilepicuploadimgview.setImage(new Image(CONTACT_SEARCH_IMG));
        namelabel.setText("Kontaktszemély");
    }

    
    
    public void setProfilePane(){
        
        profilepicuploadimgview.setImage(new Image(pojoprofileimgurl));
        //namelabel.setText(!inputfirstname.getText().isEmpty() ? inputfirstname.getText()+" "+inputlastname.getText() : "Kontaktszemély");
        bigimgbtn.setTooltip(msgbox.showToolTip("Profilkép nagyítása"));
        replaceimgbtn.setTooltip(msgbox.showToolTip("Profilkép megváltoztatása"));
        tableview.setTooltip(msgbox.showToolTip("Dupla kattintás a szerkesztéshez"));
    }
    
    
    public void setPanesDisabled(){
        ArrayList<Pane> mypanes = new ArrayList<Pane>();
        mypanes.add(searchpane); mypanes.add(newcontactpane); mypanes.add(exportofile); mypanes.add(datasheetpane);
        for(Pane pane:mypanes){
        pane.setVisible(false);
        }        
    }
    
    
        
    public TreeItem<String> addNodeItem(String title, TreeItem<String> parent, Node nodeimage){
        TreeItem<String> nodeitem = new TreeItem<>(title);
        nodeitem.setGraphic(nodeimage);
        nodeitem.setExpanded(true);
        parent.getChildren().add(nodeitem);
        return nodeitem;
    }
    
    
    private Node addNodePic(String picfile){
    this.picfile = picfile;
    this.nodepic = new ImageView(new Image(getClass().getResourceAsStream(picfile)));
    return nodepic;
    }
    
    
    //Rendszerinfó metódus
    
    public void startInfoData(){
       Infodata id = new Infodata();
       id.setInfoHBox(oslabel, userlabel, datetimelabel, tblviewmenuinfo);
   }

    public void exportToPdfFile(){
        ArrayList<String> userdatatopdf = new ArrayList<>();
        userdatatopdf.add("Vezetéknév: "+dsfirstnametf.getText()); userdatatopdf.add("Keresztnév: "+dslastnametf.getText()); userdatatopdf.add("Osztály: "+dsdivtf.getText()); 
        userdatatopdf.add("Beosztás: "+dsjobpostf.getText()); userdatatopdf.add("Privát e-mail: "+dsemailtf.getText()); userdatatopdf.add("Munkahelyi e-mail: "+dsemail2tf.getText()); 
        userdatatopdf.add("Privát telefonszám: "+dsphonetf.getText()); userdatatopdf.add("Munkahelyi telefonszám: "+dsphone2tf.getText()); userdatatopdf.add("Megjegyzés: "+dsnotestextarea.getText()); userdatatopdf.add(dbprofileimg);
        ExportToFile exptopdf = new ExportToFile();
        exptopdf.exportToPdf(userdatatopdf, dbprofileimg);
        
    }
    
   
    public void addActionListeners(){
     
        btn_changedatasheet.setOnMouseMoved(action -> {
            btn_changedatasheet.setBackground(bkgforbuttons);
        });
        
        btn_savedatasheet.setOnMouseMoved(action -> {
            btn_savedatasheet.setBackground(bkgforbuttons);
        });
        
        btn_printdatasheet.setOnMouseMoved(action -> {
            btn_printdatasheet.setBackground(bkgforbuttons);
        });
        
        bigimgbtn.setOnMouseMoved(action -> {
            bigimgbtn.setScaleX(1.2); bigimgbtn.setScaleY(1.2);
        });
        
        replaceimgbtn.setOnMouseMoved(action -> {
            replaceimgbtn.setScaleX(1.2); replaceimgbtn.setScaleY(1.2);
        });
        
        bigimgbtn.setOnMouseExited(action -> {
            bigimgbtn.setScaleX(1.0); bigimgbtn.setScaleY(1.0);
        });
        
        replaceimgbtn.setOnMouseExited(action -> {
            replaceimgbtn.setScaleX(1.0); replaceimgbtn.setScaleY(1.0);
        });
        
        btn_picfileupload.setOnMouseMoved(action -> {
            btn_picfileupload.setBackground(bkgforbuttons);
        });
        
        btn_felvitel.setOnMouseMoved(action -> {
            btn_felvitel.setBackground(bkgforbuttons);
        });
        
        MenuItem_Delete.setOnAction(action -> {
            removeContact();
        });
    
    }
        
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        setTableData();
        setMenuData();
        startInfoData();
        addActionListeners();
        
}
    
}
