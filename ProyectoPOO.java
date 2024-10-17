import java.util.ArrayList;
import java.util.Scanner;

class Opciones {
    public static final int INGRESAR_SISTEMA = 1;
    public static final int CREAR_CUENTA = 2;
    public static final int EDITAR_CUENTA = 3;
    public static final int MEJORAR_SUSCRIPCION = 4;
    public static final int SALIR = 5;
}

class Usuario {
    private String id;
    private String contraseña;
    private String correo;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private Sexo sexo;
    enum Sexo {Hombre, Mujer, NoEspecificar}
    private Usuarios tipoUsuario;
    enum Usuarios {Estudiante, Profesor}
    private Suscripcion suscripcion;
    enum Suscripcion {Basico, Medio, Premium}
    private boolean sesionActiva;

    class Tutor extends Usuario {
    // Atributos
    private String idTutor;
    private String estudiantesAsignados;
    private String especialidad;
    private String disponibilidad;
    private double calificacionTutor;
    private double salario;

    // Métodos
    public void asignarEstudiante() { }
    public void consultarProgreso() { }
    public void agendarSesion() { }
    public void agregarActividad() { }
    public void modificarActividad() { }
    public void editarActividad() { }
}

    class Administrador extends Usuario {
    // Atributos
    private String idAdmin;
    private String especialidad;
    private String verProgreso;
    private String contacto;

    // Métodos
    public void consultarProgreso() { }
    public void agregarActividad() { }
    public void modificarActividad() { }
    public void editarActividad() { }
    public void verEstadisticas() { }
}

    // Este es un usuario predeterminado 
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
//Nada mas escribe el mensaje, el metodo se dedica a toda la funcionalidad de la clase
    public void crearCuenta() {
        System.out.println("Cuenta creada exitosamente con los siguientes datos:");
        mostrarDatos();
    }
//Mas que nada estético
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
        System.out.println("3. Regresar al plan Básico, tenga en cuenta que perdera todos sus beneficios");
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
                return; // Te saca para evitar problemas 
        }

        if (nuevaSuscripcion != Suscripcion.Basico) {
            System.out.println("Ingrese el número de su tarjeta:");
            String numeroTarjeta = scanner.nextLine();
            System.out.println("Ingrese la fecha de expiración de su tarjeta de la siguiente forma: (MM/AAAA):");
            String fechaExpiracion = scanner.nextLine();
            System.out.println("Ingrese el CVV de su tarjeta:");
            String cvv = scanner.nextLine();

            // Obviamente no se paga nada, solo se actualiza si detecta una tarjeta valida
            if (!validarDatosTarjeta(numeroTarjeta, fechaExpiracion, cvv)) {
                System.out.println("Datos de tarjeta no válidos. Inténtelo nuevamente.");
                return;
                                //Igual para evitar problemas con el buffer
            }
        }

        suscripcion = nuevaSuscripcion;
        System.out.println("Su suscripción ha sido actualizada a: " + suscripcion + ".");
    }

    private boolean validarDatosTarjeta(String numeroTarjeta, String fechaExpiracion, String cvv) {
        // Comprueba que mida lo mismo que una tarjeta convencional, osea 16 digitos para la tarjeta y 3 para el cvv
        return numeroTarjeta.length() == 16 && cvv.length() == 3; 
    }
//Caso 2
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

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Usuario> usuarios = new ArrayList<>(); // Lista para almacenar los usuarios

        int opcion;
        String nombre, contraseña; // Variables declaradas fuera del switch para reutilizarlas
        do {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Ingresar al sistema");
            System.out.println("2. Crear cuenta");
            System.out.println("3. Editar cuenta");
            System.out.println("4. Mejorar suscripción");
            System.out.println("5. Salir");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case Opciones.INGRESAR_SISTEMA:
                    if (usuarios.isEmpty()) {
                        System.out.println("No hay ninguna cuenta creada. Cree una cuenta primero.");
                    } else {
                        System.out.println("Ingrese su nombre:");
                        nombre = scanner.nextLine(); //Se reutiliza la variable nombre
                        System.out.println("Ingrese su contraseña:"); //Igual se reutiliza contraseña
                        contraseña = scanner.nextLine(); 

                        // Buscar usuario por nombre y contraseña
                        Usuario usuario = buscarUsuarioPorCredenciales(usuarios, nombre, contraseña);
                        if (usuario != null) {
                            usuario.iniciarSesion(nombre, contraseña);
                        } else {
                            System.out.println("Usuario no encontrado. Verifique sus credenciales.");
                        }
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
                        nombre = scanner.nextLine(); // Reutilizamos nombre
                        System.out.println("Ingrese su contraseña actual:");
                        contraseña = scanner.nextLine(); // Reutilizamos de nuevo contraseña

                        // Buscar usuario por nombre y contraseña
                        Usuario usuario = buscarUsuarioPorCredenciales(usuarios, nombre, contraseña);
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
                        System.out.println("Ingrese su nombre con el que se registro para mejorar su suscripción:");
                      
                        nombre = scanner.nextLine();
                        System.out.println("Ahora ingrese su contraseña:");
                        contraseña = scanner.nextLine();

                        Usuario usuario = buscarUsuarioPorCredenciales(usuarios, nombre, contraseña);
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
