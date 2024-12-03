package Logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

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

//Clase Base Usuario, implementa del a interface User
public class Usuario implements User {
    //Atributos:
    private String id;
    private String contraseña;
    private String correo;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private Sexo sexo;
    private int Racha;
    private ArrayList<Progreso<?>> progresos;

    //Sobreescribimos los métodos de la interfaz
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
        return this.tipoUsuario.name();
    }

    //Enumeraciones
    public enum Sexo {
        Hombre, Mujer, NoEspecificar
    }

    Usuarios tipoUsuario;

    public enum Usuarios {
        Estudiante, Tutor, Administrador
    }
    
    public enum Materia{
        A1, A2, A3, A4, Na
    }

    private Suscripcion suscripcion;

    public enum Suscripcion {
        Basico, Medio, Premium
    }

    //Constructores sobrecargados.
    public Usuario(String id, String nombre, String correo, String contraseña, String fechaNacimiento, Sexo sexo, Usuarios tipoUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.tipoUsuario = tipoUsuario;
        this.suscripcion = Suscripcion.Basico;
        this.progresos = new ArrayList<>();
    }
    public Usuario (String correo, String contraseña){
        this.correo=correo;
        this.contraseña=contraseña;
    }
    
    //Getters:
    public String getNombre() {
        return nombre;
    }
    
    public ArrayList getProgresos() {
        return progresos;
    }

    public String getContraseña() {
        return contraseña;
    }
    
    //Métodos con ABC de cuenta:
    public void crearCuenta() {
        System.out.println("Cuenta creada exitosamente con los siguientes datos:");
        mostrarDatos();
    }

    public boolean iniciarSesion(Usuario u) {
        if (this.equals(u)) {
            System.out.println("Inicio de sesión exitoso.");
            mostrarDatos();
            return true;
        } else {
            System.out.println("Credenciales incorrectas. Inténtelo de nuevo.");
        }
        return false;
    }

    public void editarCuenta(String nuevoCorreo, String nuevaContraseña) {
        this.correo = nuevoCorreo;
        this.contraseña = nuevaContraseña;
        System.out.println("Cuenta editada con éxito.");
    }

    public boolean mejorarSuscripcion(String cuenta, String cvv, Suscripcion s) {
        if(s!=Suscripcion.Basico){
            if (validarDatosTarjeta(cuenta, "00/00/00", cvv)){
                this. suscripcion = s;
                System.out.println("Su suscripción ha sido actualizada a: " + this.suscripcion + ".");
                return true;
            }
        }
        else{
            this.suscripcion = s;
            return true;
        }
        return false;
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

    public List<String> consultarHistorial(ArrayList<Curso> Cursos) {
        System.out.println("Consultando historial del usuario...");
        System.out.println("Racha actual: " + Racha + " días.");

        List<String> historial = new ArrayList<>();

        System.out.println("Progreso en actividades:");
        if (progresos.isEmpty()) {
            System.out.println("Aún no se ha registrado progreso en ninguna actividad.");
            historial.add("Aún no se ha registrado progreso en ninguna actividad.");
        } else {
            for (Progreso<?> progreso : progresos) {
                String progresoInfo = "Actividad: " + progreso.getTitulo() + " - " + progreso.getProgreso() + "% completado";
                historial.add(progresoInfo);
                System.out.println(progresoInfo);
            }
        }

        return historial;
    }
    
    public void mostrarHistorialProgreso() {
        // Crear el JFrame para mostrar el historial
        JFrame frameHistorial = new JFrame("Historial de Progreso");
        frameHistorial.setSize(600, 400);
        frameHistorial.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Verificar si hay progresos registrados
        if (progresos.isEmpty()) {
            JLabel mensaje = new JLabel("No hay progreso registrado.", SwingConstants.CENTER);
            frameHistorial.add(mensaje);
        } else {
            // Crear tabla para mostrar el historial
            String[] columnas = {"Actividad", "Progreso (%)"};
            Object[][] datos = new Object[progresos.size()][2];
            for (int i = 0; i < progresos.size(); i++) {
                Progreso<?> progreso = progresos.get(i);
                datos[i][0] = progreso.getTitulo();
                datos[i][1] = progreso.getProgreso();
            }

            JTable tablaHistorial = new JTable(datos, columnas);
            JScrollPane scrollPane = new JScrollPane(tablaHistorial);
            frameHistorial.add(scrollPane);
        }

        // Hacer visible el frame
        frameHistorial.setVisible(true);
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
    
    //Redefinición de operadores (EQUAL)
    public boolean equals(Usuario u){
        return (this.correo.toUpperCase().equals(u.correo.toUpperCase()) && this.correo.equals(u.correo));
    }
    
    public void agregarProgreso(Progreso<?> progreso) {
        progresos.add(progreso);
        System.out.println(progresos);
    }
}
