import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Alumno {

    private String nombre;
    private String apellido;
    private int edad;

     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        return edad == alumno.edad &&
                Objects.equals(nombre, alumno.nombre) &&
                Objects.equals(apellido, alumno.apellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido, edad);
    }
}
