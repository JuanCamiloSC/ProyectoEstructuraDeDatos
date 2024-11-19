package Proceso.Controllers;

import Proceso.Model.Activity;
import Proceso.Utils.ShowMessage;
import Proceso.AppPrincipal;
import Proceso.Exception.ActivityAlreadyExistsException;
import Proceso.Exception.ActivityDoesntExistException;
import Proceso.Exception.IncompleteDataException;
import Proceso.Model.Process;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import  static Proceso.Controllers.AppController.INSTANCE;

import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.Collectors;

public class AdminActivitiesController {
    AppPrincipal aplicacion;

    Process process = INSTANCE.getProcesoActual();


    @FXML
    private TableColumn<Activity, String> columnNameActivity;

    @FXML
    private TextField txtDescriptionActivity;

    @FXML
    private Button btnUpdateActivity;

    @FXML
    private Button btnExportActivity;

    @FXML
    private TextField txtSearchActivity;

    @FXML
    private Button btnEliminateActivity;

    @FXML
    private ComboBox<Boolean> comboBoxMandatoryActivity;

    @FXML
    private TableView<Activity> tableActivities;

    @FXML
    private TableColumn<Activity, Integer> columnMiniumTimeActivity;

    @FXML
    private Label labelOpenTaskAdminActivities;

    @FXML
    private TableColumn<Activity, Integer> columnTotalTimeActivity;

    @FXML
    private TableColumn<Activity, Boolean> columnMandatoryActivity;

    @FXML
    private Button btnCrateActivity;

    @FXML
    private Button btnSingOutActivity;

    @FXML
    private TableColumn<Integer, String> columnDescriptionActivity;

    @FXML
    private TextField txtNameActivity;

    Object activitySelection;
    @FXML
    private Label nombreProceso;




    @FXML
    void clickedExportActivity(MouseEvent event) {
        FileChooser fileChooser= new FileChooser();
        fileChooser.setTitle("Guardar como archivo Excel");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo Excel (*.xlsx)", "*.xlsx"));
        File file= fileChooser.showSaveDialog(null);

        if(file!=null){
            exportarTablaAExcel(file);
        }

    }

    private void exportarTablaAExcel(File file) {
        try (Workbook workbook = new XSSFWorkbook(); FileOutputStream fileOut = new FileOutputStream(file)) {
            Sheet sheet = workbook.createSheet("Datos");

            // Encabezados de columna
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < tableActivities.getColumns().size(); i++) {
                headerRow.createCell(i).setCellValue(tableActivities.getColumns().get(i).getText());
            }

            // Datos de la tabla
            ObservableList<Activity> items = tableActivities.getItems();
            for (int i = 0; i < items.size(); i++) {
                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(items.get(i).getName());
                row.createCell(1).setCellValue(items.get(i).getDescription());
                row.createCell(2).setCellValue(items.get(i).getObligatory());
                row.createCell(3).setCellValue(items.get(i).getMinTime());
                row.createCell(4).setCellValue(items.get(i).getTotalTime());
            }

            workbook.write(fileOut);
            System.out.println("Exportación exitosa a Excel.");
        } catch (IOException e) {
            ShowMessage.mostrarMensaje("Error", "Error al exportar a Excel", "No se pudo exportar la tabla a Excel.");
        }
    }


    @FXML
    void clickedSignOutActivity(MouseEvent event) throws IOException {
        AppPrincipal.showTool();
    }

    @FXML
    void clickedCreateActivity(MouseEvent event) {

        if(process.getActivities().getSize()==0 || activitySelection==null ){
            try {
                if(!txtNameActivity.getText().isEmpty() && !txtDescriptionActivity.getText().isEmpty())
                    process.addActivity(new Activity(txtNameActivity.getText(), txtDescriptionActivity.getText(), comboBoxMandatoryActivity.getValue()));
                else
                    throw new IncompleteDataException();
                } catch (ActivityAlreadyExistsException e) {
                ShowMessage.mostrarMensaje("Error","Error agregando la actividad","la actividad ya existe");
                }catch (IncompleteDataException e){
                ShowMessage.mostrarMensaje("Error","Error agregando la actividad","actividad incompleta");
            }
        }else {
            createSequenceActivity();
        }

        rechargeTable();

    }

    void createSequenceActivity() {
        if(!txtNameActivity.getText().isEmpty() && txtDescriptionActivity.getText().isEmpty())
            process.addActivity(new Activity(txtNameActivity.getText(), txtDescriptionActivity.getText(), comboBoxMandatoryActivity.getValue()), ((Activity) activitySelection).getName());
        else
            ShowMessage.mostrarMensaje("Error","Error agrengando actividad","incompleta");
    }


    @FXML
    void clickedEliminateActivity(MouseEvent event) {
        if(activitySelection!= null){
            try {
                process.deleteActivity((Activity) activitySelection);
            }catch (ActivityDoesntExistException e){
                ShowMessage.mostrarMensaje("Error","Error al eliinar actividad", "La actividad no existe");
            }
        }else if(process.getActivities().getSize()==1){
            try {
                process.deleteActivity(process.getActivities().getFirstNode().getValue());
            }catch (ActivityDoesntExistException e){
                ShowMessage.mostrarMensaje("Error","Error al eliminar actividad","la actividad no existe");
            }
        }else if(!txtNameActivity.getText().isEmpty()){
            try {
                process.deleteActivity(txtNameActivity.getText());
            }catch (ActivityDoesntExistException e){
                ShowMessage.mostrarMensaje("Error","Error al eliminar actividad","La actividad no existe");
            }
        }

        rechargeTable();

    }

    @FXML
    void clickedUpdateActivity(MouseEvent event) {

        if(activitySelection!=null){
            Activity activity = (Activity) activitySelection;
            if(!txtNameActivity.getText().isEmpty())
                activity.setName(txtNameActivity.getText());
            if(!txtDescriptionActivity.getText().isEmpty())
                activity.setDescription(txtDescriptionActivity.getText());
            if(comboBoxMandatoryActivity.getValue()!=null)
                activity.setObligatory(comboBoxMandatoryActivity.getValue());
        }else
            ShowMessage.mostrarMensaje("Error","Error al actualizar","No se selecciono ninguna actividad");

        rechargeTable();
    }

    @FXML
    void OpenTaskAdminActivitiesAction(MouseEvent event) throws IOException {
        if(activitySelection!=null){
            INSTANCE.setActividadActual((Activity) activitySelection);
            AppPrincipal.showTaskView();
        }
    }

    @FXML
    void initialize(){
        comboBoxMandatoryActivity.getItems().addAll(true,false);

        nombreProceso.setText(process.getName());
        loadTable();

        process.getActivities().addObserver((o, arg) -> {
            ObservableList<Activity> updateTableData = FXCollections.observableArrayList(process.getActivities().getTableData());
            tableActivities.setItems(updateTableData);
        });
        tableActivities.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
            if(newSelection!=null){
                activitySelection= newSelection;
            }
        });

        txtSearchActivity.textProperty().addListener((observable, oldValue, newValue)->{
            searchActivity();
        });


    }

    private void loadTable() {
        columnNameActivity.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnDescriptionActivity.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        columnMandatoryActivity.setCellValueFactory(param -> new SimpleBooleanProperty(param.getValue().getObligatory()));
        columnMandatoryActivity.setCellFactory(CheckBoxTableCell.forTableColumn(columnMandatoryActivity));
        columnMiniumTimeActivity.setCellValueFactory(new PropertyValueFactory<>("Minimumtime"));
        columnTotalTimeActivity.setCellValueFactory(new PropertyValueFactory<>("Totaltime"));

        ObservableList<Activity> activitiesData= FXCollections.observableArrayList(process.getActivities().getTableData());
        tableActivities.setItems(activitiesData);
    }

    public void rechargeTable(){
        tableActivities.getItems().clear();
        ObservableList<Activity> activitiesData = FXCollections.observableArrayList(process.getActivities().getTableData());
        tableActivities.setItems(activitiesData);
        txtNameActivity.setText("");
        txtDescriptionActivity.setText("");
    }

    public void setAplicacion(AppPrincipal aplicacion){
        this.aplicacion= aplicacion;
    }

    public void searchActivity(){
        String text= txtSearchActivity.getText().toLowerCase();
        ObservableList<Activity> filteredList= process.getActivities().getTableData().stream()
                .filter(activity -> activity.getName().toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.collectingAndThen(Collectors.toList(), FXCollections::observableArrayList));

        tableActivities.setItems(filteredList);
    }

}
