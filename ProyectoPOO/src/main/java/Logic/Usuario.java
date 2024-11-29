package Logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

interface User {

    String getId();

    String getNombre();

    String getCorreo();

    String getContraseña();

    String getFechaNacimiento();

    String getSexo();

    String getTipoUsuario();

    void crearCuenta();
}

class Progreso<T> {

    private String titulo;
    private int progreso; // Porcentaje de progreso (0-100)
    private T actividad; // Genérico para soportar diferentes tipos de actividades (Evaluación, Curso, etc.)
    private Date ultimaActividad; // Fecha de la última actividad

    public Progreso(String titulo, T actividad) {
        this.titulo = titulo;
        this.actividad = actividad;
        this.progreso = 0; // Inicia con 0% de progreso
        this.ultimaActividad = new Date(); // Fecha de creación
    }

    public String getTitulo() {
        return titulo;
    }

    public int getProgreso() {
        return progreso;
    }

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

public class Usuario implements User {

    private String id;
    private String contraseña;
    private String correo;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    public Sexo sexo;
    private int Racha;
    private ArrayList<Progreso<?>> progresos;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getCorreo() {
        return this.correo;
    }

    @Override
    public String getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    @Override
    public String getSexo() {
        return this.sexo.name();
    }

    @Override
    public String getTipoUsuario() {
        return Usuarios.Estudiante.name();
    }

    public enum Sexo {
        Hombre, Mujer, NoEspecificar
    }

    Usuarios tipoUsuario;

    public enum Usuarios {
        Estudiante, Profesor, Administrador
    }

    private Suscripcion suscripcion;

    enum Suscripcion {
        Basico, Medio, Premium
    }

    private boolean sesionActiva;

    public Usuario(String id, String nombre, String correo, String contraseña, String fechaNacimiento, Sexo sexo, Usuarios tipoUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.tipoUsuario = Usuarios.Estudiante;
        this.suscripcion = Suscripcion.Basico;
        this.sesionActiva = false;
        this.progresos = new ArrayList<>();

    }

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void crearCuenta() {
        System.out.println("Cuenta creada exitosamente con los siguientes datos:");
        mostrarDatos();
    }

    public void iniciarSesion(String nombre, String contraseña) {
        if (this.nombre.equals(nombre) && this.contraseña.equals(contraseña)) {
            sesionActiva = true;
            System.out.println("Inicio de sesión exitoso.");
            mostrarDatos();
        } else {
            System.out.println("Credenciales incorrectas. Inténtelo de nuevo.");
        }
    }

    public void editarCuenta(String nuevoCorreo, String nuevaContraseña) {
        this.correo = nuevoCorreo;
        this.contraseña = nuevaContraseña;
        System.out.println("Cuenta editada con éxito.");
    }

    public void mejorarSuscripcion(Scanner scanner) {
        System.out.println("Seleccione la acción que desea realizar:");
        System.out.println("1. Mejorar mi suscripción a Medio");
        System.out.println("2. Mejorar mi suscripción a Premium");
        System.out.println("3. Regresar al plan Básico, tenga en cuenta que perderá todos sus beneficios");
        int opcionSuscripcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Suscripcion nuevaSuscripcion;
        switch (opcionSuscripcion) {
            case 1:
                nuevaSuscripcion = Suscripcion.Medio;
                break;
            case 2:
                nuevaSuscripcion = Suscripcion.Premium;
                break;
            case 3:
                nuevaSuscripcion = Suscripcion.Basico;
                break;
            default:
                System.out.println("Opción no válida. Intente nuevamente.");
                return;
        }

        if (nuevaSuscripcion != Suscripcion.Basico) {
            System.out.println("Ingrese el número de su tarjeta:");
            String numeroTarjeta = scanner.nextLine();
            System.out.println("Ingrese la fecha de expiración de su tarjeta de la siguiente forma: (MM/AAAA):");
            String fechaExpiracion = scanner.nextLine();
            System.out.println("Ingrese el CVV de su tarjeta:");
            String cvv = scanner.nextLine();

            if (!validarDatosTarjeta(numeroTarjeta, fechaExpiracion, cvv)) {
                System.out.println("Datos de tarjeta no válidos. Inténtelo nuevamente.");
                return;
            }
        }

        suscripcion = nuevaSuscripcion;
        System.out.println("Su suscripción ha sido actualizada a: " + suscripcion + ".");
    }

    private boolean validarDatosTarjeta(String numeroTarjeta, String fechaExpiracion, String cvv) {
        return numeroTarjeta.length() == 16 && cvv.length() == 3;
    }

    public void completarEvaluacion(Scanner scanner, ArrayList<Curso> Cursos) {
        System.out.println("Seleccione el área en la que desea realizar la evaluación:");
        for (int i = 0; i < Cursos.size(); i++) {
            System.out.println((i + 1) + ". " + Cursos.get(i).gettitulo());
        }

        int seleccionCurso = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        if (seleccionCurso > 0 && seleccionCurso <= Cursos.size()) {
            Curso cursoSeleccionado = Cursos.get(seleccionCurso - 1);
            System.out.println("Iniciando evaluación en el área: " + cursoSeleccionado.gettitulo());

            // Obtener evaluaciones personalizadas del curso
            ArrayList<Evaluacion> evaluaciones = cursoSeleccionado.getEvaluaciones();

            if (!evaluaciones.isEmpty()) {
                System.out.println("Seleccione una evaluación para realizar:");
                for (int i = 0; i < evaluaciones.size(); i++) {
                    System.out.println((i + 1) + ". " + evaluaciones.get(i).getTitulo());
                }
                System.out.println((evaluaciones.size() + 1) + ". Evaluaciones predeterminadas");

                int seleccionEvaluacion = scanner.nextInt();
                scanner.nextLine();

                if (seleccionEvaluacion > 0 && seleccionEvaluacion <= evaluaciones.size()) {
                    Evaluacion evaluacionSeleccionada = evaluaciones.get(seleccionEvaluacion - 1);
                    realizarEvaluacion(scanner, evaluacionSeleccionada);
                } else if (seleccionEvaluacion == evaluaciones.size() + 1) {
                    realizarEvaluacionesPredeterminadas(scanner, cursoSeleccionado);
                } else {
                    System.out.println("Opción no válida.");
                }
            } else {
                realizarEvaluacionesPredeterminadas(scanner, cursoSeleccionado);
            }
        } else {
            System.out.println("Opción de área no válida.");
        }
    }

    private void realizarEvaluacion(Scanner scanner, Evaluacion evaluacion) {
        System.out.println("Evaluación: " + evaluacion.getTitulo());
        ArrayList<String> preguntas = evaluacion.getPreguntas();
        ArrayList<String[]> opciones = evaluacion.getOpciones();
        ArrayList<Integer> respuestasCorrectas = evaluacion.getRespuestasCorrectas();

        int aciertos = 0;
        for (int i = 0; i < preguntas.size(); i++) {
            System.out.println(preguntas.get(i));
            for (int j = 0; j < opciones.get(i).length; j++) {
                System.out.println((j + 1) + ". " + opciones.get(i)[j]);
            }
            System.out.print("Seleccione la opción correcta: ");
            int respuesta = scanner.nextInt() - 1; // Convertir opción 1=a, 2=b, etc.
            if (respuesta == respuestasCorrectas.get(i)) {
                aciertos++;
            }
        }
        double calificacion = (aciertos / (double) preguntas.size()) * 10;
        System.out.println("Ha completado la evaluación con una calificación de: " + calificacion);
        Progreso<Evaluacion> progreso = new Progreso<>(evaluacion.getTitulo(), evaluacion);
        progreso.actualizarProgreso(100, "Evaluación");
        progresos.add(progreso);
    }

    private void realizarEvaluacionesPredeterminadas(Scanner scanner, Curso cursoSeleccionado) {
        System.out.println("Realizando evaluación predeterminada...");
        int aciertos = 0;
        switch (cursoSeleccionado.gettitulo()) {
            case "Ciencias físico-matemáticas y de la ingeniería":
                aciertos += realizarCuestionario(scanner, new String[]{
                    "¿Cuál es el resultado de 2+2?",
                    "¿Qué estudia la física?",
                    "¿Cual de estos es un lenguaje de programación?"
                }, new String[][]{
                    {"a) 3", "b) 4", "c) 5"},
                    {"a) El universo", "b) La sociedad", "c) La vida"},
                    {"a) HTML", "b) Java", "c) C--"}
                }, new int[]{1, 0, 1});
                break;
            case "Ciencias biológicas, químicas y de la salud":
                aciertos += realizarCuestionario(scanner, new String[]{
                    "¿Qué molécula lleva oxígeno en la sangre?",
                    "¿Cuál es el órgano más grande del cuerpo humano?",
                    "¿Qué define la fisiología?"
                }, new String[][]{
                    {"a) ADN", "b) Hemoglobina", "c) Insulina"},
                    {"a) Hígado", "b) Piel", "c) Corazón"},
                    {"a) La estructura", "b) La función", "c) La enfermedad"}
                }, new int[]{1, 1, 1});
                break;
            case "Ciencias Sociales":
                aciertos += realizarCuestionario(scanner, new String[]{
                    "¿Qué estudia la sociología?",
                    "¿Cuál es el poder judicial en México?",
                    "¿Qué significa RI en ciencias políticas?"
                }, new String[][]{
                    {"a) Individuos", "b) Sociedad", "c) Economía"},
                    {"a) Presidencia", "b) Congreso", "c) Suprema Corte"},
                    {"a) Relaciones Internacionales", "b) Relaciones Industriales", "c) Redes Internacionales"}
                }, new int[]{1, 2, 0});
                break;
            case "Humanidades y Artes":
                aciertos += realizarCuestionario(scanner, new String[]{
                    "¿Qué autor escribió 'Don Quijote'?",
                    "¿Qué significa la proporción áurea?",
                    "¿Quién es conocido como el 'padre de la música clásica'?"
                }, new String[][]{
                    {"a) Shakespeare", "b) Cervantes", "c) Homero"},
                    {"a) Patrón estético", "b) Color dorado", "c) Escala musical"},
                    {"a) Mozart", "b) Beethoven", "c) Bach"}
                }, new int[]{1, 0, 2});
                break;
            default:
                System.out.println("Área no válida.");
                return;
        }

        double calificacion = (aciertos / 3.0) * 10;
        cursoSeleccionado.setCalificacionEvaluacion(calificacion);
        System.out.println("Ha completado la evaluación con una calificación de: " + calificacion);
        // Actualizar progreso a 100%
        Progreso<Curso> progreso = new Progreso<>(cursoSeleccionado.gettitulo(), cursoSeleccionado);
        progreso.actualizarProgreso(100, "Evaluación");
        progresos.add(progreso);
    }

    private int realizarCuestionario(Scanner scanner, String[] preguntas, String[][] opciones, int[] respuestasCorrectas) {
        int aciertos = 0;
        for (int i = 0; i < preguntas.length; i++) {
            System.out.println(preguntas[i]);
            for (int j = 0; j < opciones[i].length; j++) {
                System.out.println(opciones[i][j]);
            }
            System.out.print("Seleccione la opción correcta (0=a, 1=b, 2=c): ");
            int respuesta = scanner.nextInt();
            if (respuesta == respuestasCorrectas[i]) {
                aciertos++;
            }
        }
        return aciertos;
    }

    public void consultarHistorial(ArrayList<Curso> Cursos) {
        System.out.println("Consultando historial del usuario...");
        System.out.println("Racha actual: " + Racha + " días.");

        System.out.println("Progreso en actividades:");
        if (progresos.isEmpty()) {
            System.out.println("Aún no se ha registrado progreso en ninguna actividad.");
        } else {
            for (Progreso<?> progreso : progresos) {
                progreso.consultarProgreso();
            }
        }
    }

    public void tomarAsesoria(Scanner scanner, ArrayList<Curso> Cursos) {
        System.out.println("Seleccione el área en la que desea tomar asesoría:");
        for (int i = 0; i < Cursos.size(); i++) {
            System.out.println((i + 1) + ". " + Cursos.get(i).gettitulo());
        }

        int seleccionCurso = scanner.nextInt();
        scanner.nextLine();

        if (seleccionCurso > 0 && seleccionCurso <= Cursos.size()) {
            Curso CursoSeleccionada = Cursos.get(seleccionCurso - 1);

            ArrayList<String> profesores = CursoSeleccionada.getProfesores();
            ArrayList<String> materias = CursoSeleccionada.getMaterias();

            if (profesores.isEmpty()) {
                System.out.println("No hay profesores disponibles en esta área.");
                return;
            }

            System.out.println("Seleccione un profesor disponible:");
            for (int i = 0; i < profesores.size(); i++) {
                System.out.println((i + 1) + ". " + profesores.get(i));
            }

            int seleccionProfesor = scanner.nextInt();
            scanner.nextLine();

            if (seleccionProfesor > 0 && seleccionProfesor <= profesores.size()) {
                String profesorSeleccionado = profesores.get(seleccionProfesor - 1);

                System.out.println("El profesor " + profesorSeleccionado + " imparte las siguientes materias:");
                for (int i = 0; i < materias.size(); i++) {
                    System.out.println((i + 1) + ". " + materias.get(i));
                }

                System.out.println("Seleccione una materia:");
                int seleccionMateria = scanner.nextInt();
                scanner.nextLine();

                if (seleccionMateria > 0 && seleccionMateria <= materias.size()) {
                    String materiaSeleccionada = materias.get(seleccionMateria - 1);
                    System.out.println("Iniciando la clase de " + materiaSeleccionada + " con el profesor " + profesorSeleccionado + "...");
                    System.out.println("Bienvenidos a la clase de " + materiaSeleccionada + ". ¡Espero que sea de gran utilidad!");
                    System.out.println("Tu racha de dias ha aumentado a " + (Racha + 1) + ".");
                    Racha++;
                    System.out.println("¿Qué desea hacer ahora?");
                    System.out.println("1. Volver al menú principal");
                    System.out.println("2. Tomar otra asesoría");

                    int opcion = scanner.nextInt();
                    scanner.nextLine();

                    if (opcion == 2) {
                        tomarAsesoria(scanner, Cursos);
                    } else {
                        System.out.println("Regresando al menú principal...");
                    }
                } else {
                    System.out.println("Opción de materia no válida.");
                }
            } else {
                System.out.println("Opción de profesor no válida.");
            }
        } else {
            System.out.println("Opción de área no válida.");
        }
    }

    public void mostrarDatos() {
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre + " " + apellido);
        System.out.println("Correo: " + correo);
        System.out.println("Fecha de Nacimiento: " + fechaNacimiento);
        System.out.println("Sexo: " + sexo);
        System.out.println("Tipo de Usuario: " + tipoUsuario);
        System.out.println("Suscripción: " + suscripcion);
    }
    
    //Redefinición de operadores
    public boolean equals(Usuario u){
        return (this.correo.toUpperCase().equals(u.correo.toUpperCase()) && this.id.equals(u.id));
    }
    
    public void agregarProgreso(Progreso<?> progreso) {
        progresos.add(progreso);
    }
}

class Tutor extends Usuario {

    private String especialidad;
    private String disponibilidad;
    private ArrayList<Evaluacion> evaluaciones;

    public Tutor(String id, String nombre, String correo, String contraseña, String fechaNacimiento, Sexo sexo) {
        super(id, nombre, correo, contraseña, fechaNacimiento, sexo, Usuarios.Profesor);
        this.evaluaciones = new ArrayList<>();
        this.especialidad = "Por Definir";
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

    public void verEstadisticas() {
    }
}
