package Logic;
import java.util.Date;

public class Progreso<T> {
    //Clase Genericidad
    //Atributos:
    private String titulo;
    private int progreso; // Porcentaje de progreso (0-100)
    private T actividad; // Genérico para soportar diferentes tipos de actividades (Evaluación, Curso, etc.)
    private Date ultimaActividad; // Fecha de la última actividad

    //Constructor único
    public Progreso(String titulo, T actividad) {
        this.titulo = titulo;
        this.actividad = actividad;
        this.progreso = 0; 
        this.ultimaActividad = new Date(); // Fecha de creación
    }

    //Getters:
    public String getTitulo() {
        return titulo;
    }

    public int getProgreso() {
        return progreso;
    }

    //Métodos:
    public void actualizarProgreso(int progresoAdicional, String n) {
        if (progresoAdicional < 0) {
            System.out.println("El progreso adicional no puede ser negativo.");
            return;
        }
        this.progreso = Math.min(100, this.progreso + progresoAdicional); // Evitar que supere el 100%
        this.ultimaActividad = new Date(); // Actualiza la fecha de la última actividad
        this.titulo=n;
        System.out.println("El progreso de la actividad '" + titulo + "' se ha actualizado a " + this.progreso + "%.");
    }

    public void consultarProgreso() {
        System.out.println("Actividad: " + titulo);
        System.out.println("Progreso: " + progreso + "% completado.");
        System.out.println("Última actividad: " + ultimaActividad);
    }
}