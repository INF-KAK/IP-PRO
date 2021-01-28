import application.Task.Task;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddTaskTest {
    @Test
    public void shouldCreateTask() throws Exception{
        //arrange
        Task task = Task.create("Zadanie1");


        //assert
        assertEquals("Zadanie1",task.getNazwa());
    }

    @Test
    public void shouldThrowExceptionWhenEmptyFile() throws Exception {
        Task task = Task.create("");

        Assert.assertEquals("Empty file!", 0, task.getId());
    }
}
