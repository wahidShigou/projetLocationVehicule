package controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import model.Locataire;
import model.Location;
import model.Vehicule;
import service.*;
import utilitaires.Utils;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;


public class LocationController implements Initializable {

    public static Locataire l = null;
    public static Vehicule vehicule = null;

    @FXML
    private TextField couleurTfd;

    @FXML
    private TextField numCatTfd;

    @FXML
    private TextField nbCheveauxTfd;

    @FXML
    private TextField marqueTfd;

    @FXML
    private TextField modeleTfd;

    @FXML
    private TextField matriculeTfd;

    @FXML
    private TextField nomTfd;

    @FXML
    private TextField pnomTfd;

    @FXML
    private TextField telTfd;

    @FXML
    private TextField dateNaissTfd;

    @FXML
    private TextField numPieceTfd;

    @FXML
    private TextField montantTfd;

    @FXML
    private TextField dateTfd;

    @FXML
    private TextArea commentaireTa;

    @FXML
    private TableView<Location> LocationTab;

    @FXML
    private TableColumn<Location, String> NumPieceCol;

    @FXML
    private TableColumn<Location, String> NomCol;

    @FXML
    private TableColumn<Location, String> PnomCol;

    @FXML
    private TableColumn<Location, String> MatCol;

    @FXML
    private TableColumn<Location, String> MarqueCol;

    @FXML
    private TableColumn<Location, String> ModeleCol;

    @FXML
    private TableColumn<Location, LocalDate> DateCol;

    @FXML
    private TableColumn<Location, Float> MonatntCol;

    @FXML
    private Button NouveauBtn;

    @FXML
    private Button EnregistrerBtn;

    @FXML
    private Button modifierBtn;

    @FXML
    private Button supprimerBtn;

    @FXML
    void deleteHandler(ActionEvent event) {

    }

    @FXML
    void newHandler(ActionEvent event) {
        LocationTab.getSelectionModel().clearSelection();
        viderChamps();
    }

    private void selectElement()
    {
        LocationTab.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            matriculeTfd.setText(newValue.getVehicule().getMatricule());
            couleurTfd.setText(newValue.getVehicule().getCouleur());
            numCatTfd.setText(newValue.getVehicule().getNumCategorie());
            nbCheveauxTfd.setText(String.valueOf(newValue.getVehicule().getNbCheveaux()));
            marqueTfd.setText(newValue.getVehicule().getModele().getMarque().getLibelle());
            modeleTfd.setText(newValue.getVehicule().getModele().getLibelle());
            numPieceTfd.setText(newValue.getLocataire().getNumPiece());
            nomTfd.setText(newValue.getLocataire().getNom());
            pnomTfd.setText(newValue.getLocataire().getPnom());
            telTfd.setText(newValue.getLocataire().getTel());
            dateNaissTfd.setText(String.valueOf(newValue.getLocataire().getDateNaiss()));
            dateTfd.setText(String.valueOf(newValue.getDate()));
            montantTfd.setText(String.valueOf(newValue.getMontant()));
            commentaireTa.setText(newValue.getCommentaire());
        });
    }
    public void showTable(){
        try {
            ILocation iLocation = new LocationDao();
            List<Location> locations = iLocation.findAll();
            if(locations != null){
                NumPieceCol.setCellValueFactory(param -> {
                    String numPiece = param.getValue().getLocataire().getNumPiece();
                    ObservableValue<String> val = new SimpleObjectProperty<>(numPiece);
                    return val;
                });

                NomCol.setCellValueFactory(param -> {
                    String nom = param.getValue().getLocataire().getNom();
                    ObservableValue<String> val = new SimpleObjectProperty<>(nom);
                    return val;
                });

                PnomCol.setCellValueFactory(param -> {
                    String pnom = param.getValue().getLocataire().getPnom();
                    ObservableValue<String> val = new SimpleObjectProperty<>(pnom);
                    return val;
                });

                MatCol.setCellValueFactory(param -> {
                    String mat = param.getValue().getVehicule().getMatricule();
                    ObservableValue<String> val = new SimpleObjectProperty<>(mat);
                    return val;
                });

                MarqueCol.setCellValueFactory(param -> {
                    String marque = param.getValue().getVehicule().getModele().getMarque().getLibelle();
                    ObservableValue<String> val = new SimpleObjectProperty<>(marque);
                    return val;
                });

                ModeleCol.setCellValueFactory(param -> {
                    String modele = param.getValue().getVehicule().getModele().getLibelle();
                    ObservableValue<String> val = new SimpleObjectProperty<>(modele);
                    return val;
                });

                MonatntCol.setCellValueFactory(new PropertyValueFactory<>("montant"));

                DateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

                LocationTab.getItems().clear();
                LocationTab.setItems(FXCollections.observableArrayList(locations));
            }
        }catch (Exception ex){

        }
    }

    public void viderChamps(){
        matriculeTfd.setText("");
        couleurTfd.setText("");
        numCatTfd.setText("");
        nbCheveauxTfd.setText("");
        marqueTfd.setText("");
        modeleTfd.setText("");
        numPieceTfd.setText("");
        nomTfd.setText("");
        pnomTfd.setText("");
        telTfd.setText("");
        dateNaissTfd.setText("");
        dateTfd.setText("");
        montantTfd.setText("");
        commentaireTa.setText("");
    }

    @FXML
    void saveHandler(ActionEvent event) throws Exception {
        if(!(couleurTfd.getText().equals("") || numPieceTfd.getText().equals("") || nomTfd.getText().equals("") ||
                pnomTfd.getText().equals("") || telTfd.getText().equals("") || dateNaissTfd.getText().equals("") ||
                dateTfd.getText().equals("") || montantTfd.getText().equals("") || commentaireTa.getText().equals(""))){
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            Location location = new Location();
            location.setDate(LocalDate.parse(dateTfd.getText(),df));
            location.setMontant(Float.valueOf(montantTfd.getText()));
            location.setCommentaire(commentaireTa.getText());
            location.setVehicule(vehicule);

            ILocataire iLocataire = new LocataireDao();
            if(l != null){
                location.setLocataire(l);
                ILocation iLocation = new LocationDao();
                if (iLocation.add(location)) {
                    Utils.showMessage("Gestion de Location", "Ajout Location", "Ajout Effecué avec succes !!!");
                    showTable();
                    viderChamps();
                } else {
                    Utils.showMessage("Gestion de Location", "Ajout Location", "Erreur !!!");
                }
            }else {
                l = new Locataire();
                l.setNumPiece(numPieceTfd.getText());
                l.setNom(nomTfd.getText());
                l.setPnom(pnomTfd.getText());
                l.setTel(telTfd.getText());
                l.setDateNaiss(LocalDate.parse(dateNaissTfd.getText(),df));
                if (iLocataire.add(l) > 0) {
                    int id = iLocataire.last();
                    l.setId(id);
                    location.setLocataire(l);
                    ILocation iLocation = new LocationDao();
                    if (iLocation.add(location)) {
                        Utils.showMessage("Gestion de Location", "Ajout Location", "Ajout Effecué avec succes !!!");
                        viderChamps();
                    } else {
                        Utils.showMessage("Gestion de Location", "Ajout Location", "Erreur !!!");
                    }
                }
            }
        }else {
            Utils.showMessage("Gestion de Location", "Erreur", "Tous les champs sont obligatoires !!!");
        }
    }

    @FXML
    void updateHandler(ActionEvent event) {

    }

    public void verouillerChampsLocataire(){
        nomTfd.setEditable(false);
        pnomTfd.setEditable(false);
        telTfd.setEditable(false);
        dateNaissTfd.setEditable(false);
    }

    public void deverouillerChampsLocataire(){
        nomTfd.setEditable(true);
        pnomTfd.setEditable(true);
        telTfd.setEditable(true);
        dateNaissTfd.setEditable(true);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectElement();
        showTable();
        matriculeTfd.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    if(!matriculeTfd.getText().trim().equals("")){
                        IVehicule iv = new VehiculeDao();
                        try {
                            vehicule = iv.find(matriculeTfd.getText());
                            if(vehicule != null){
                                couleurTfd.setText(vehicule.getCouleur());
                                numCatTfd.setText(vehicule.getNumCategorie());
                                nbCheveauxTfd.setText(String.valueOf(vehicule.getNbCheveaux()));
                                marqueTfd.setText(vehicule.getModele().getMarque().getLibelle());
                                modeleTfd.setText(vehicule.getModele().getLibelle());
                            }else {
                                Utils.showMessage("Gestion de Location","Véhicule Inexistant !","Ce Véicule n'existe pas !!!");
                                matriculeTfd.setText("");
                                couleurTfd.setText("");
                                numCatTfd.setText("");
                                nbCheveauxTfd.setText("");
                                marqueTfd.setText("");
                                modeleTfd.setText("");
                            }
                        }catch (Exception ex){

                        }
                    }else {
                        matriculeTfd.setText("");
                    }
                }
            }
        });

        numPieceTfd.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    if(!numPieceTfd.getText().trim().equals("")){
                        try {
                            ILocataire iLocataire = new LocataireDao();
                            l = iLocataire.find(numPieceTfd.getText());
                            if(l != null){
                                verouillerChampsLocataire();
                                nomTfd.setText(l.getNom());
                                pnomTfd.setText(l.getPnom());
                                telTfd.setText(l.getTel());
                                dateNaissTfd.setText(l.getDateNaiss().toString());
                            }else {
                                deverouillerChampsLocataire();
                                nomTfd.setText("");
                                pnomTfd.setText("");
                                telTfd.setText("");
                                dateNaissTfd.setText("");
                            }
                        }catch (Exception ex){

                        }
                    }else {
                        numPieceTfd.setText("");
                    }
                }
            }
        });
    }
}
