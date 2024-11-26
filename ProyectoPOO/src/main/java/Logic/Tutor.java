package Logic;
import java.util.*;

class Tutor extends Usuario {
    private String especialidad;
    private String disponibilidad;
    private ArrayList<Evaluacion> evaluaciones;
    private ArrayList<Progreso<Evaluacion>> progresoEvaluaciones;

    public Tutor(String id, String nombre, String correo, String contraseña, String fechaNacimiento, Sexo sexo) {
        super(id, nombre, correo, contraseña, fechaNacimiento, sexo, Usuarios.Profesor);
        this.evaluaciones = new ArrayList<>();
        this.progresoEvaluaciones = new ArrayList<>();
        this.especialidad = especialidad;
        this.disponibilidad = disponibilidad;
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
            if (pregunta.equalsIgnoreCase("fin")) break;

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

        // Crear un nuevo progreso asociado a la evaluación
        Progreso<Evaluacion> progreso = new Progreso<>(titulo, evaluacion);
        progresoEvaluaciones.add(progreso);

        System.out.println("Evaluación y progreso creado con éxito.");
    }

    public void consultarProgreso() {
        System.out.println("Consultando progreso de las evaluaciones:");
        if (progresoEvaluaciones.isEmpty()) {
            System.out.println("No hay evaluaciones con progreso registrado.");
        } else {
            for (Progreso<Evaluacion> progreso : progresoEvaluaciones) {
                progreso.consultarProgreso();
            }
        }
    }
}


class Administrador extends Usuario {
    private String especialidad;

    public Administrador(String id, String nombre, String correo, String contraseña, String fechaNacimiento, Sexo sexo) {
        super(id, nombre, correo, contraseña, fechaNacimiento, sexo, Usuarios.Administrador);
        this.especialidad = "Por Definir";
    }

    public static boolean validarContraseñaAdmin(String contraseñaAdmin) {
        return "admin123".equals(contraseñaAdmin);
    }

    public void verEstadisticas() {}
}