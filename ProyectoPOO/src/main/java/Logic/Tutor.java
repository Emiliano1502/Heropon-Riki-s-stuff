package Logic;
import java.util.*;
public class Tutor extends Usuario {
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