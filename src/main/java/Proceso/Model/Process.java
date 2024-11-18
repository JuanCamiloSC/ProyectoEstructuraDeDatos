package Proceso.Model;

import java.util.Objects;

import Proceso.Exception.ActivityAlreadyExistsException;
import Proceso.Exception.ActivityDoesntExistException;
import Proceso.Utils.ActivityList;
import Proceso.Utils.ShowMessage;

public class Process {

    private String name;
    private String id;
    private int minTime;
    private int maxTime;
    private ActivityList<Activity> activities;

    public Process(String name, String id){
        super();
        this.name= name;
        this.id= id;
        activities= new ActivityList<>();

    }
    //Metodos-----------------------------------------------------------------------------------
    /**
     *Metodo que agrega actividades al proceso
     */
    public void addActivity(Activity activity)throws ActivityAlreadyExistsException {
        if(!activities.contains(activity)){
            activities.add(activity);
        }else {
            try {
                throw new ActivityAlreadyExistsException();
            }catch (ActivityAlreadyExistsException e){
                ShowMessage.mostrarMensaje("error","error al agregar una actividad", "la actividad ya existe");
            }
        }
        calculateTimes();
    }

    public void addActivity(Activity actividad, String nombreActividadAnterior){
        if(!activities.contains(actividad)){
            try {
                activities.add(actividad, searchActivityByName(nombreActividadAnterior));
            } catch (ActivityDoesntExistException e) {
                ShowMessage.mostrarMensaje("Error", "Error al agregar actividad", "La actividad anterior no existe");
            } catch (ActivityAlreadyExistsException e) {
                ShowMessage.mostrarMensaje("Error", "Error al agregar actividad", "La actividad ya existe");
            }
        }else {
            try {
                throw new ActivityAlreadyExistsException();
            } catch (ActivityAlreadyExistsException e) {
                ShowMessage.mostrarMensaje("Error", "Error al agregar actividad", "La actividad ya existe");
            }
        }
        calculateTimes();
    }

    /**
     *Metodo que busca actividades por el nombre
     */

    public Activity searchActivityByName(String name){
        for (Activity activity: activities){
            if(activity.getName().equals(name)){
                return activity;
            }
        }
        return null;
    }
    /**
     * Calcular los tiempos
     */

    public int calculateMinTime(){
        int time=0;
        for (Activity activity: activities){
            time+= activity.calculateTotalTime();
        }
        return time;
    }
    public int calculateTotalTime(){
        int time=0;
        for (Activity activity: activities){
            time+=activity.calculateTotalTime();
        }
        return time;
    }

    /**
     *Metodo que elimina una actividad
     */

    public void deleteActivity(Activity activity) throws ActivityDoesntExistException{
        activities.remove(activity);
        calculateTimes();
    }
    public void deleteActivity(String name) throws ActivityDoesntExistException{
        activities.remove(searchActivityByName(name));
        calculateTimes();
    }

    /**
     *Metodo que actualiza una actividad
     */

    public void refreshActivity(String nombre, String descripcion) {
        Activity actividad = searchActivityByName(nombre);
        if(actividad != null){
            actividad.setDescription(descripcion);
        }
    }

    //-------------------------------------------------------------------------------------------
    //Getter y setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMinTime() {
        return minTime;
    }

    public void setMinTime(int minTime) {
        this.minTime = minTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public ActivityList<Activity> getActivities() {
        return activities;
    }

    public void setActivities(ActivityList<Activity> activities) {
        this.activities = activities;
    }

    public void calculateTimes(){
        minTime= calculateMinTime();
        maxTime= calculateTotalTime();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Process proceso = (Process) o;
        return calculateMinTime() == proceso.calculateMinTime() && maxTime == proceso.maxTime && Objects.equals(name, proceso.name) && Objects.equals(id, proceso.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, minTime, maxTime, activities);
    }

    @Override
    public String toString() {
        return "Process{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", minTime=" + minTime +
                ", maxTime=" + maxTime +
                ", activities=" + activities +
                '}';
    }
}
