package Logic;

import Logic.Usuario.Materia;
import Logic.Usuario.Sexo;
import Logic.Usuario.Usuarios;


public class CreadorDeUsuario {
    //Construye un usuario según su tipo
    public static Usuario crearUsuario(String id, String nombre, String correo, String contraseña, String fechaNacimiento, Sexo sexo, Usuarios user, Materia mat) {
        Usuario nuevoUsuario;
        nuevoUsuario = switch (user) {
            case Administrador -> new Administrador(id, nombre, correo, contraseña, fechaNacimiento, sexo);
            case Estudiante -> new Usuario(id, nombre, correo, contraseña, fechaNacimiento, sexo, Usuarios.Estudiante);
            default -> new Tutor(id, nombre, correo, contraseña, fechaNacimiento, sexo, mat);
        };
        nuevoUsuario.crearCuenta(); // Método genérico para inicializar al usuario
        return nuevoUsuario;
    }
}