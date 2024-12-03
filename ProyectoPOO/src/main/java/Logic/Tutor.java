package Logic;

import java.util.ArrayList;
import java.util.Scanner;

//Clase derivada de Usuario
public class Tutor extends Usuario {
    
    //Atributos:
    private Usuario.Materia especialidad;
    private String disponibilidad;
    private ArrayList<Evaluacion> evaluaciones;

    //Constructor de tutor
    public Tutor(String id, String nombre, String correo, String contraseña, String fechaNacimiento, Usuario.Sexo sexo, Usuario.Materia mat) {
        super(id, nombre, correo, contraseña, fechaNacimiento, sexo, Usuarios.Tutor);
        this.evaluaciones = new ArrayList<>();
        this.especialidad = mat;
        this.disponibilidad = "Por Definir";
    }

    
    public void asignarEstudiante() {
    }

    public void consultarProgreso() {
    }

    public void agendarSesion() {
    }

    public void crearEvaluacion(Scanner scanner) {
        System.out.println("Ingrese el título de la evaluación:");
        String titulo = scanner.nextLine();
        System.out.println("Ingrese el puntaje máximo:");
        int puntajeMaximo = scanner.nextInt();
        scanner.nextLine();

        ArrayList<String> preguntas = new ArrayList<>();
        ArrayList<String[]> opciones = new ArrayList<>();
        ArrayList<Integer> respuestasCorrectas = new ArrayList<>();

        while (true) {
            System.out.println("Ingrese una pregunta (o 'fin' para terminar):");
            String pregunta = scanner.nextLine();
            if (pregunta.equalsIgnoreCase("fin")) {
                break;
            }

            preguntas.add(pregunta);
            String[] opcionesPregunta = new String[3];
            for (int i = 0; i < 3; i++) {
                System.out.println("Ingrese la opción " + (char) ('a' + i) + ":");
                opcionesPregunta[i] = scanner.nextLine();
            }
            opciones.add(opcionesPregunta);

            System.out.println("Ingrese el índice de la respuesta correcta (0=a, 1=b, 2=c):");
            int respuestaCorrecta = scanner.nextInt();
            scanner.nextLine();
            respuestasCorrectas.add(respuestaCorrecta);
        }

        Evaluacion evaluacion = new Evaluacion(titulo, puntajeMaximo, preguntas, opciones, respuestasCorrectas, this.getNombre());
        evaluaciones.add(evaluacion);
        System.out.println("Evaluación creada con éxito.");
    }
        
    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }
    
    public Usuario.Materia getMateria(){
        return this.especialidad;
    }
}