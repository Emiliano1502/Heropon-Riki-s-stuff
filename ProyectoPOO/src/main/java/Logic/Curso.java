
package Logic;

import java.util.ArrayList;
import java.util.Date;

class Curso {
    private String titulo;
    private String descripcion;
    private String temario;
    private ArrayList<String> profesores;
    private double promedio;
    private double calificacionEvaluacion;
    private ArrayList<String> eventos;
    private ArrayList<String> materias; 
private ArrayList<Evaluacion> evaluaciones;
    
    public Curso(String titulo, String descripcion, String temario) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.temario = temario;
        this.profesores = new ArrayList<>();
        this.promedio = 0.0;
        this.eventos = new ArrayList<>();
        this.materias = new ArrayList<>(); 
        this.evaluaciones = new ArrayList<>();
    }

    public String gettitulo() {
        return titulo;
    }

 public void agregarEvaluacion(Evaluacion evaluacion) {
        evaluaciones.add(evaluacion);
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
    
    public void agregarProfesor(String profesor) {
        profesores.add(profesor);
    }

    public void agregarMateria(String materia) {
        materias.add(materia); 
    }

    public void setCalificacionEvaluacion(double calificacionEvaluacion) {
        this.calificacionEvaluacion = calificacionEvaluacion;
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
class Progreso<T> {
    private String titulo;
    private int progreso; // Porcentaje de progreso (0-100)
    private Date ultimaActividad;
    private T actividad; // Genérico para soportar diferentes tipos de actividades (Evaluación, Curso, etc.)

    public Progreso(String titulo, T actividad) {
        this.titulo = titulo;
        this.actividad = actividad;
        this.progreso = 0; // Inicia con 0% de progreso
        this.ultimaActividad = new Date(); // Fecha de creación como última actividad
    }

    public String getTitulo() {
        return titulo;
    }

    public int getProgreso() {
        return progreso;
    }

    public Date getUltimaActividad() {
        return ultimaActividad;
    }

    public void actualizarProgreso(int progresoAdicional) {
        if (progresoAdicional < 0) {
            System.out.println("El progreso adicional no puede ser negativo.");
            return;
        }
        this.progreso = Math.min(100, this.progreso + progresoAdicional); // Evitar que supere el 100%
        this.ultimaActividad = new Date(); // Actualiza la fecha de la última actividad
        System.out.println("El progreso de la actividad '" + titulo + "' se ha actualizado a " + this.progreso + "%.");
    }

    public void consultarProgreso() {
        System.out.println("Actividad: " + titulo);
        System.out.println("Progreso: " + progreso + "% completado.");
        System.out.println("Última actividad registrada: " + ultimaActividad);
    }
}