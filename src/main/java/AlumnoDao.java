import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AlumnoDao {

    private static Database db = Database.getInstance();
    private int ID = 0;


   public void setAlumno(Alumno alumno) {
        db.getTablaAlumno().put(ID, alumno);
        ID++;
    }

    public Alumno getAlumno(int ID) {
        return db.getTablaAlumno().get(ID);
    }
}
