import application.User.Report;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateReport {
        @Test
        public void shouldCreateTask() throws Exception{
            //act
            Report report = Report.create(1,2,"text");

            //assert
            assertEquals(1,report.getSenderR());
            assertEquals(2,report.getReceiverR());
            assertEquals("text",report.getText());
        }

        @Test
        public void shouldThrowExceptionWhenTextIsNull(){
            Report report = Report.create(1,2,null);

            Assert.assertNull("Text is null!", report.getText());
        }
    }