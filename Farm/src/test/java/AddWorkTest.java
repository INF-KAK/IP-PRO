import application.FarmSystem;
import application.Task.Task;
import application.Task.Work;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddWorkTest {
    @Test
    public void shouldCreateWork() throws Exception {
        //arrange
        Work work = Work.create(1, 6, 3, "30-01-2021 10:00:00", "30-01-2021 19:00:00");


        //assert
        assertEquals(1, work.getId_task());
        assertEquals(6, work.getId_employee());
        assertEquals(3, work.getId_machine());
        assertEquals("30-01-2021 10:00:00", work.getDateFrom());
        assertEquals("30-01-2021 19:00:00", work.getDateTo());
    }

    @Test
    public void shouldThrowExceptionWhenEmployeeIsBusy() throws Exception {
        Work work = Work.create(1, 6, 3, "25-01-2021 10:00:00", "30-01-2021 19:00:00");

        FarmSystem fm = new FarmSystem();
        //fm.checkEmployee(work);

        Assert.assertEquals("Employee is Busy!", 0, fm.checkEmployee(work));
    }
}
