package Logic;

import java.util.ArrayList;
import java.util.Date;

//
public class Curso {
    //Atributos:
    private String titulo;
    private String descripcion;
    private String temario;
    private ArrayList<String> profesores;
    private double promedio;
    private double calificacionEvaluacion;
    private ArrayList<String> eventos;
    private ArrayList<String> materias; 
    private ArrayList<Evaluacion> evaluaciones;
    private String autor;
    
    //Métodos:
    //Constructores
    public Curso(String titulo, String descripcion, String temario, String autor) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.temario = temario;
        this.profesores = new ArrayList<>();
        this.promedio = 0.0;
        this.eventos = new ArrayList<>();
        this.materias = new ArrayList<>(); 
        this.evaluaciones = new ArrayList<>();
        this.autor = autor;
    }

    public void agregarEvaluacion(Evaluacion evaluacion) {
        evaluaciones.add(evaluacion);
    }
    
    //Getters y setters:
    public String gettitulo() {
        return titulo;
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }
    
    public ArrayList<String> getProfesores() {
        return profesores;
    }

    public ArrayList<String> getMaterias() {
        return materias;
    }

    public double getCalificacionEvaluacion() {
        return calificacionEvaluacion;
    }

    public void agregarMateria(String materia) {
        materias.add(materia); 
    }

    public void setCalificacionEvaluacion(double calificacionEvaluacion) {
        this.calificacionEvaluacion = calificacionEvaluacion;
    }
    
    public void agregarProfesor(String profesor) {
        profesores.add(profesor);
    }
}

class Evaluacion {
    private String titulo;
    private int puntajeMaximo;
    private ArrayList<String> preguntas;
    private ArrayList<String[]> opciones;
    private ArrayList<Integer> respuestasCorrectas;
    private String autor;

    public Evaluacion(String titulo, int puntajeMaximo, ArrayList<String> preguntas, ArrayList<String[]> opciones, ArrayList<Integer> respuestasCorrectas, String autor) {
        this.titulo = titulo;
        this.puntajeMaximo = puntajeMaximo;
        this.preguntas = preguntas;
        this.opciones = opciones;
        this.respuestasCorrectas = respuestasCorrectas;
        this.autor = autor;
    }

    //Getters:
    public String getTitulo() {
        return titulo;
    }

    public int getPuntajeMaximo() {
        return puntajeMaximo;
    }

    public ArrayList<String> getPreguntas() {
        return preguntas;
    }

    public ArrayList<String[]> getOpciones() {
        return opciones;
    }

    public ArrayList<Integer> getRespuestasCorrectas() {
        return respuestasCorrectas;
    }
}