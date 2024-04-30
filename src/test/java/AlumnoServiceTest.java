import exceptions.DataAccessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AlumnoServiceTest {

    private AlumnoDao alumnoDaoMock;
    private AlumnoService alumnoService;

    @BeforeEach
    public void setUp() {
        alumnoDaoMock = Mockito.mock(AlumnoDao.class);
        alumnoService = new AlumnoService(alumnoDaoMock);
    }

    @Test
    public void postTest() throws DataAccessException {
        Alumno alumno = new Alumno("Juan", "Perez", 20);
        doNothing().when(alumnoDaoMock).add(alumno);

        alumnoService.post(alumno);

        verify(alumnoDaoMock, times(1)).add(alumno);
    }

    @Test
    public void getOneTest() throws DataAccessException {
        Alumno alumno = new Alumno("Juan", "Perez", 20);
        when(alumnoDaoMock.findByAlumnoId(0)).thenReturn(alumno);

        Alumno result = alumnoService.getOne(0);

        assertEquals(alumno, result);
    }

    @Test
    public void getOneTestThrowsException() throws DataAccessException {
        when(alumnoDaoMock.findByAlumnoId(0)).thenThrow(NoSuchElementException.class);

        assertThrows(ResponseStatusException.class, () -> alumnoService.getOne(0));
    }

    @Test
    public void getAllTest() throws DataAccessException {
        HashMap<Integer, Alumno> alumnos = new HashMap<>();
        alumnos.put(0, new Alumno("Juan", "Perez", 20));
        when(alumnoDaoMock.findAll()).thenReturn(alumnos);

        HashMap<Integer, Alumno> result = alumnoService.getAll();

        assertEquals(alumnos, result);
    }

    @Test
    public void putTest() throws DataAccessException {
        Alumno alumno = new Alumno("Juan", "Perez", 20);
        when(alumnoDaoMock.update(0, alumno)).thenReturn(alumno);

        Alumno result = alumnoService.put(alumno, 0);

        assertEquals(alumno, result);
    }

    @Test
    public void putTestThrowsException() throws DataAccessException {
        Alumno alumno = new Alumno("Juan", "Perez", 20);
        when(alumnoDaoMock.update(0, alumno)).thenThrow(NoSuchElementException.class);

        assertThrows(ResponseStatusException.class, () -> alumnoService.put(alumno, 0));
    }

    @Test
    public void deleteTest() throws DataAccessException {
        doNothing().when(alumnoDaoMock).delete(0);

        alumnoService.delete(0);

        verify(alumnoDaoMock, times(1)).delete(0);
    }

    @Test
    public void deleteTestThrowsException() throws DataAccessException {
        doThrow(NoSuchElementException.class).when(alumnoDaoMock).delete(0);

        assertThrows(ResponseStatusException.class, () -> alumnoService.delete(0));
    }
}
