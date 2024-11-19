package Proceso.Controllers;
import Proceso.AppPrincipal;
import Proceso.Model.Activity;
import Proceso.Model.Task;
import Proceso.Utils.ShowMessage;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.Collectors;

import  static Proceso.Controllers.AppController.INSTANCE;

public class TaskController {


    Activity actividad = INSTANCE.getActividadActual();

    @FXML
    private Button btnCreateTask;

    @FXML
    private Button btnEliminateTask;

    @FXML
    private Button btnExportTask;

    @FXML
    private Button btnSignOutTask;

    @FXML
    private Button btnUpdateTask;

    @FXML
    private TableColumn<Task, Integer> columnTimeTask;

    @FXML
    private TableColumn<Task, String> columnDescriptionTask;

    @FXML
    private TableColumn<Task, Boolean> columnMandatoryTask;

    @FXML
    private TableColumn<Task, String> columnNameTask;

    @FXML
    private ComboBox<String> comboBoxMandatoryTask;

    @FXML
    private TableView<Task> tableTask;

    private final Label userName= new Label();

    @FXML
    private TextField txtDescriptionTask;

    @FXML
    private TextField txtNameTask;

    @FXML
    private TextField txtSearchTask;

    @FXML
    private TextField txtTimeTask;

    Object tareaSelection;

    @FXML
    void ClickedSignOutTask(ActionEvent event) throws IOException {
        AppPrincipal.showAdminProcessView();
        btnSignOutTask.getScene().getWindow().hide();
    }
    @FXML
    void clickedCreateTask(ActionEvent event) {
        crearTarea();
    }

    @FXML
    void clickedEliminateTask(ActionEvent event) {
        eliminarTarea();
    }


    public void crearTarea(){
        try {
            Boolean isObligatoria = comboBoxMandatoryTask.getValue().equals("Si");
            String nombre = txtNameTask.getText();
            String descripcion = txtDescriptionTask.getText();
            int tiempo = Integer.parseInt(txtTimeTask.getText());
            actividad.createTask(nombre, descripcion,isObligatoria, tiempo);
            rechargeTable();
        } catch (NumberFormatException e) {
            ShowMessage.mostrarMensaje("Error", "Error al crear tarea", "El tiempo debe ser un numero");
        }
        INSTANCE.getProcesoActual().calculateTimes();
    }

    public void eliminarTarea(){
        Task tarea = tableTask.getSelectionModel().getSelectedItem();

        if(tarea != null){
            actividad.deleteTask(tarea);
            rechargeTable();
        }
        INSTANCE.getProcesoActual().calculateTimes();
    }


    @FXML
    public void clickedExportTask(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar como archivo Excel");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo Excel (*.xlsx)", "*.xlsx"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            exportarTablaAExcel(file);
        }
    }


    private void exportarTablaAExcel(File file) {
        try (Workbook workbook = new XSSFWorkbook(); FileOutputStream fileOut = new FileOutputStream(file)) {
            Sheet sheet = workbook.createSheet("Datos");

            // Encabezados de columna
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < tableTask.getColumns().size(); i++) {
                headerRow.createCell(i).setCellValue(tableTask.getColumns().get(i).getText());
            }

            // Datos de la tabla
            ObservableList<Task> items = tableTask.getItems();
            for (int i = 0; i < items.size(); i++) {
                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(items.get(i).getName());
                row.createCell(1).setCellValue(items.get(i).getDescription());
                row.createCell(2).setCellValue(items.get(i).getTime());
                row.createCell(3).setCellValue(items.get(i).getObligatory());
            }

            workbook.write(fileOut);
            System.out.println("Exportación exitosa a Excel.");
        } catch (IOException e) {
            ShowMessage.mostrarMensaje("Error", "Error al exportar a Excel", "No se pudo exportar la tabla a Excel.");
        }
    }

    @FXML
    void initialize() {
        userName.setText(actividad.getName());
        loadTable();

        comboBoxMandatoryTask.getItems().addAll( "Si", "No");

        actividad.getTasks().addObserver((o, arg) -> {
            ObservableList<Task> updatedTableData = FXCollections.observableArrayList(actividad.getTasks().getTableData());
            tableTask.setItems(updatedTableData);
        });
        tableTask.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if(newSelection != null){
                tareaSelection = newSelection;
            }
        });

        txtSearchTask.textProperty().addListener((observable, oldValue, newValue) -> {
            searchTask();
        });
    }

    @FXML
    private void searchTask() {
        String text = txtSearchTask.getText();
        ObservableList<Task> filteredList = actividad.getTasks().getTableData().stream()
                .filter(tarea -> tarea.getName().toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.collectingAndThen(Collectors.toList(), FXCollections::observableArrayList));

        tableTask.setItems(filteredList);
    }
    private void loadTable(){
        columnNameTask.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnDescriptionTask.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnTimeTask.setCellValueFactory(new PropertyValueFactory<>("time"));
        columnMandatoryTask.setCellValueFactory(param -> new SimpleBooleanProperty(param.getValue().getObligatory()));
        columnMandatoryTask.setCellFactory(CheckBoxTableCell.forTableColumn(columnMandatoryTask));

        ObservableList<Task> tareas = FXCollections.observableArrayList(actividad.getTasks().getTableData());
        tableTask.setItems(tareas);
    }

    private void rechargeTable(){
        tableTask.getItems().clear();
        ObservableList<Task> updatedTableData = FXCollections.observableArrayList(actividad.getTasks().getTableData());
        tableTask.setItems(updatedTableData);
    }

    public void clickedUpdateTask (ActionEvent mouseEvent) {
        if(tareaSelection != null){
            Task tarea = (Task) tareaSelection;
            if(!txtNameTask.getText().isEmpty())
                tarea.setName(txtNameTask.getText());
            if(!txtDescriptionTask.getText().isEmpty())
                tarea.setDescription(txtDescriptionTask.getText());
            if(!txtTimeTask.getText().isEmpty())
                tarea.setTime(Integer.parseInt(txtTimeTask.getText()));
            if(comboBoxMandatoryTask.getValue() != null)
                tarea.setObligatory(comboBoxMandatoryTask.getValue().equals("Si"));
            rechargeTable();
        }
        INSTANCE.getProcesoActual().calculateTimes();
    }
}

