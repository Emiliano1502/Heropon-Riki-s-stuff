package Logic;

import java.util.*;

public class Area {
    private String nombreArea;
    private String descripcion;
    private String temario;
    private ArrayList<String> profesores;
    private double promedio;
    private ArrayList<String> eventos;

    public Area(String nombreArea, String descripcion, String temario) {
        this.nombreArea = nombreArea;
        this.descripcion = descripcion;
        this.temario = temario;
        this.profesores = new ArrayList<>();
        this.promedio = 0.0;
        this.eventos = new ArrayList<>();
    }

    public String getNombreArea() {
        return nombreArea;
    }
}