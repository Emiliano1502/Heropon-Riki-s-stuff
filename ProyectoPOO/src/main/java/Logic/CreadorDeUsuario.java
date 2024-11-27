package Logic;

import Logic.Usuario.Sexo;
import Logic.Usuario.Usuarios;


public class CreadorDeUsuario {
    public static Usuario crearUsuario(String id, String nombre, String correo, String contraseña, String fechaNacimiento, Sexo sexo, Usuarios user) {
        Usuario nuevoUsuario;
        nuevoUsuario = switch (user) {
            case Administrador -> new Administrador(id, nombre, correo, contraseña, fechaNacimiento, sexo);
            case Estudiante -> new Usuario(id, nombre, correo, contraseña, fechaNacimiento, sexo, Usuarios.Estudiante);
            default -> new Tutor(id, nombre, correo, contraseña, fechaNacimiento, sexo);
        };

        nuevoUsuario.crearCuenta(); // Método genérico para inicializar al usuario
        return nuevoUsuario;
    }
    
    public static Usuario editarUsuario(Usuario us, String correo, String contraseña) {
        Usuario nuevoUsuario;
        Usuarios u = Usuarios.valueOf(us.getTipoUsuario());
        Sexo s = Sexo.valueOf(us.getSexo());
        nuevoUsuario = switch (u) {
            case Administrador -> new Administrador(us.getId(), us.getNombre(), correo, contraseña, us.getFechaNacimiento(), s);
            case Estudiante -> new Usuario(us.getId(), us.getNombre(), correo, contraseña, us.getFechaNacimiento(), s, Usuarios.Estudiante);
            default -> new Tutor(us.getId(), us.getNombre(), correo, contraseña, us.getFechaNacimiento(), s);
        };

        nuevoUsuario.crearCuenta(); // Método genérico para inicializar al usuario
        return nuevoUsuario;
    }
}