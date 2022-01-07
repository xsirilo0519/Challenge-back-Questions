package co.com.sofka.questions.model;

public class UsuarioDTO {
    private String id;
    private String nombre;
    private String apellido;
    private String path;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String id, String nombre, String apellido, String path) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
