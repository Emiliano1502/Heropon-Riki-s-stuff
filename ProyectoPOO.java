import java.util.ArrayList;
import java.util.Scanner;

class Opciones {
    public static final int INGRESAR_SISTEMA = 1;
    public static final int CREAR_CUENTA = 2;
    public static final int EDITAR_CUENTA = 3;
    public static final int MEJORAR_SUSCRIPCION = 4;
    public static final int SALIR = 5;
}

class Curso {
    private String titulo;
    private String descripcion;
    private String temario;
    private ArrayList<String> profesores;
    private double promedio;
    private double calificacionEvaluacion;
    private ArrayList<String> eventos;
    private ArrayList<String> materias; 

    public Curso(String titulo, String descripcion, String temario) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.temario = temario;
        this.profesores = new ArrayList<>();
        this.promedio = 0.0;
        this.eventos = new ArrayList<>();
        this.materias = new ArrayList<>(); 
    }

    public String gettitulo() {
        return titulo;
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


class Usuario {
    private String id;
    private String contraseña;
    private String correo;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private Sexo sexo;
    private int Racha;

    enum Sexo {Hombre, Mujer, NoEspecificar}

    private Usuarios tipoUsuario;

    enum Usuarios {Estudiante, Profesor, Administrador}

    private Suscripcion suscripcion;

    enum Suscripcion {Basico, Medio, Premium}

    private boolean sesionActiva;

    public Usuario(String id, String nombre, String apellido, String correo, String contraseña, String fechaNacimiento, Sexo sexo, Usuarios tipoUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contraseña = contraseña;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.tipoUsuario = tipoUsuario;
        this.suscripcion = Suscripcion.Basico;
        this.sesionActiva = false;

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
            Curso CursoSeleccionada = Cursos.get(seleccionCurso - 1);
            System.out.println("Iniciando evaluación en el área: " + CursoSeleccionada.gettitulo());

            int aciertos = 0;
            switch (CursoSeleccionada.gettitulo()) {
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
            CursoSeleccionada.setCalificacionEvaluacion(calificacion);
            System.out.println("Ha completado la evaluación con una calificación de: " + calificacion);
        } else {
            System.out.println("Opción de área no válida.");
        }
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

        boolean evaluacionesRealizadas = false;
        System.out.println("Calificaciones en las evaluaciones:");

        for (Curso Curso : Cursos) {
            double calificacion = Curso.getCalificacionEvaluacion();
            if (calificacion > 0) {
                evaluacionesRealizadas = true;
                System.out.println("- Área: " + Curso.gettitulo() + " | Calificación: " + calificacion);
            }
        }

        if (!evaluacionesRealizadas) {
            System.out.println("Aún no se han realizado evaluaciones.");
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
                    System.out.println("Tu racha de dias ha aumentado a " + (Racha+1) + ".");
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
}

class Tutor extends Usuario {
    private String especialidad;
    private String disponibilidad;

    public Tutor(String id, String nombre, String apellido, String correo, String contraseña, String fechaNacimiento, Sexo sexo) {
        super(id, nombre, apellido, correo, contraseña, fechaNacimiento, sexo, Usuarios.Profesor);
        this.especialidad = "Por Definir";
        this.disponibilidad = "Por Definir";
    }

    public void asignarEstudiante() {}
    public void consultarProgreso() {}
    public void agendarSesion() {}
}

class Administrador extends Usuario {
    private String especialidad;

    public Administrador(String id, String nombre, String apellido, String correo, String contraseña, String fechaNacimiento, Sexo sexo) {
        super(id, nombre, apellido, correo, contraseña, fechaNacimiento, sexo, Usuarios.Administrador);
        this.especialidad = "Por Definir";
    }

    public static boolean validarContraseñaAdmin(String contraseñaAdmin) {
        return "admin123".equals(contraseñaAdmin);
    }

    public void verEstadisticas() {}
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<Curso> Cursos = new ArrayList<>();

        Curso Curso1 = new Curso("Ciencias físico-matemáticas y de la ingeniería", "Ciencias exactas y uso de las matemáticas", "Matemáticas, Física, Cálculo, Química, Programación");
        Curso1.agregarProfesor("Juan Pérez");
        Curso1.agregarProfesor("María López");
        Curso1.agregarMateria("Matemáticas");
        Curso1.agregarMateria("Física");
        Curso1.agregarMateria("Cálculo");
        Curso1.agregarMateria("Programación");
        Cursos.add(Curso1);

        Curso Curso2 = new Curso("Ciencias biológicas, químicas y de la salud", "Uso de las ciencias exactas para el mundo de la biología", "Biología, Química Orgánica, Fisiología, Laboratorio");
        Curso2.agregarProfesor("Ismael Rojas");
        Curso2.agregarProfesor("Gabriela Guzmán");
        Curso2.agregarMateria("Biología");
        Curso2.agregarMateria("Química Orgánica");
        Curso2.agregarMateria("Fisiología");
        Cursos.add(Curso2);

        Curso Curso3 = new Curso("Ciencias Sociales", "Disciplinas dedicadas al estudio del ser humano y sus interacciones en sociedad", "Derecho, Sociologia, Filosofia");
        Curso3.agregarProfesor("Domingo Zapata");
        Curso3.agregarProfesor("Pilar Rosales");
        Curso3.agregarMateria("Sociologia y Humanismo");
        Curso3.agregarMateria("RI en Asia y el Pacifico");
        Cursos.add(Curso3);
        Curso Curso4 = new Curso("Humanidades y Artes", "Disciplina de la expresion fisica, visual y sonora del ser humano", "Musica, Literatura, Diseño de Interiores");
        Curso4.agregarProfesor("Ludwig Aaron");
        Curso4.agregarProfesor("Fabiola Fuentes");
        Curso4.agregarMateria("Diseño de Interiores");
        Curso4.agregarMateria("Dibujo y puntos de fuga");
        Cursos.add(Curso4);
        int opcion;
        do {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Ingresar al sistema");
            System.out.println("2. Crear cuenta");
            System.out.println("3. Editar cuenta");
            System.out.println("4. Mejorar suscripción");
            System.out.println("5. Salir");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case Opciones.INGRESAR_SISTEMA:
                    System.out.println("Ingrese su nombre:");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingrese su contraseña:");
                    String contraseña = scanner.nextLine();
                    Usuario usuario = buscarUsuarioPorCredenciales(usuarios, nombre, contraseña);
                    if (usuario != null) {
                        usuario.iniciarSesion(nombre, contraseña);
                        System.out.println("Seleccione una opción adicional:");
                        System.out.println("1. Evaluación");
                        System.out.println("2. Mi historial");
                        System.out.println("3. Tomar asesorías");
                        int opcionAdicional = scanner.nextInt();
                        scanner.nextLine();
                        if (opcionAdicional == 1) {//Evaluación
                            usuario.completarEvaluacion(scanner, Cursos);
                        }
                        if (opcionAdicional == 2) { // Mi historial
                            usuario.consultarHistorial(Cursos);
                        }
                        if (opcionAdicional == 3) {//Asesorias
                            usuario.tomarAsesoria(scanner, Cursos);
                        }
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                    break;

case Opciones.CREAR_CUENTA:
    System.out.println("Ingrese su nombre:");
    nombre = scanner.nextLine();
    System.out.println("Ingrese su apellido:");
    String apellido = scanner.nextLine();
    System.out.println("Ingrese su correo:");
    String correo = scanner.nextLine();
    System.out.println("Ingrese su contraseña:");
    contraseña = scanner.nextLine();
    System.out.println("Ingrese su fecha de nacimiento (dd/mm/aaaa):");
    String fechaNacimiento = scanner.nextLine();

    System.out.println("Seleccione su sexo:");
    System.out.println("1. Hombre");
    System.out.println("2. Mujer");
    System.out.println("3. No especificar");
    int opcionSexo = scanner.nextInt();
    scanner.nextLine();
    Usuario.Sexo sexo = opcionSexo == 1 ? Usuario.Sexo.Hombre : (opcionSexo == 2 ? Usuario.Sexo.Mujer : Usuario.Sexo.NoEspecificar);

    System.out.println("Seleccione su tipo de usuario:");
    System.out.println("1. Estudiante");
    System.out.println("2. Profesor");
    System.out.println("3. Administrador");
    int opcionTipoUsuario = scanner.nextInt();
    scanner.nextLine();

    String id = "U" + System.currentTimeMillis();
    if (opcionTipoUsuario == 1) {
    Usuario nuevoUsuario = new Usuario(id, nombre, apellido, correo, contraseña, fechaNacimiento, sexo, Usuario.Usuarios.Estudiante);
    usuarios.add(nuevoUsuario);
    nuevoUsuario.crearCuenta();
    } else if (opcionTipoUsuario == 2) {
    Tutor nuevoTutor = new Tutor(id, nombre, apellido, correo, contraseña, fechaNacimiento, sexo);
    usuarios.add(nuevoTutor);
    nuevoTutor.crearCuenta();
    } else if (opcionTipoUsuario == 3) {
    System.out.println("Ingrese la contraseña especial para administrador:");
    String contraseñaAdmin = scanner.nextLine();
    if (Administrador.validarContraseñaAdmin(contraseñaAdmin)) {
    Administrador nuevoAdmin = new Administrador(id, nombre, apellido, correo, contraseña, fechaNacimiento, sexo);
    usuarios.add(nuevoAdmin);
    nuevoAdmin.crearCuenta();
    } else {
    System.out.println("Contraseña especial incorrecta. No se puede crear la cuenta de administrador.");
    }
    }
    break;

    case Opciones.EDITAR_CUENTA:
    if (usuarios.isEmpty()) {
    System.out.println("No hay ninguna cuenta creada. Cree una cuenta primero.");
    } else {
    System.out.println("Ingrese su nombre para editar la cuenta:");
    nombre = scanner.nextLine();
    System.out.println("Ingrese su contraseña actual:");
    contraseña = scanner.nextLine();

    usuario = buscarUsuarioPorCredenciales(usuarios, nombre, contraseña);
    if (usuario != null) {
    System.out.println("Ingrese su nuevo correo:");
    String nuevoCorreo = scanner.nextLine();
    System.out.println("Ingrese su nueva contraseña:");
    String nuevaContraseña = scanner.nextLine();
    usuario.editarCuenta(nuevoCorreo, nuevaContraseña);
    } else {
    System.out.println("Usuario no encontrado. Verifique sus credenciales.");
    }
    }
    break;

    case Opciones.MEJORAR_SUSCRIPCION:
    if (usuarios.isEmpty()) {
    System.out.println("No hay ninguna cuenta creada. Cree una cuenta primero para poder suscribirse.");
    } else {
    System.out.println("Ingrese su nombre con el que se registró para mejorar su suscripción:");
    nombre = scanner.nextLine();
    System.out.println("Ahora ingrese su contraseña:");
    contraseña = scanner.nextLine();

    usuario = buscarUsuarioPorCredenciales(usuarios, nombre, contraseña);
    if (usuario != null) {
    usuario.mejorarSuscripcion(scanner);
    } else {
    System.out.println("Usuario no encontrado. Verifique que haya escrito sus datos correctamente.");
    }
    }
    break;

    case Opciones.SALIR:
    System.out.println("Saliendo del sistema...");
    break;

    default:
    System.out.println("Opción no válida. Intente nuevamente.");
    break;
    }
    } while (opcion != Opciones.SALIR);

    scanner.close();
    }

    // Método auxiliar para buscar un usuario por nombre y contraseña
    private static Usuario buscarUsuarioPorCredenciales(ArrayList<Usuario> usuarios, String nombre, String contraseña) {
    for (Usuario usuario : usuarios) {
    if (usuario.getNombre().equals(nombre) && usuario.getContraseña().equals(contraseña)) {
    return usuario;
    }
    }
    return null;
    }
    }
