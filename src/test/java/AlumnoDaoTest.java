import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AlumnoDaoTest {

    private static final Alumno alumno = new Alumno("Juan", "Perez", 20);
    private static final AlumnoDao alumnoDao = new AlumnoDao();

    @BeforeAll
    public static void setUp() {
        alumnoDao.setAlumno(alumno);
    }

    @Test
    public void getAlumnoTestCorrect() {
        Alumno retrievedAlumno = alumnoDao.getAlumno(0);
        assertEquals(alumno, retrievedAlumno);
    }

    @Test
    public void getAlumnoTestWrong() {
        Alumno retrievedAlumno = alumnoDao.getAlumno(10);
        assertNull(retrievedAlumno);
    }

    @Test
    public void setAlumnoTest() {
        Alumno newAlumno = new Alumno("Maria", "Gomez", 22);
        alumnoDao.setAlumno(newAlumno);
        Alumno retrievedAlumno = alumnoDao.getAlumno(1);
        assertEquals(newAlumno, retrievedAlumno);
    }
}
