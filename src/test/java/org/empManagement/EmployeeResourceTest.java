package org.empManagement;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeResourceTest {

    private EmployeeResource employeeResource = new EmployeeResource();

    @Test
    public void testPublicq_ReturnsExpectedString() {
        String result = employeeResource.publicq();
        assertEquals("Hello World ", result);
    }

    @Test
    public void testPublicq_ReturnsNonNullValue() {
        String result = employeeResource.publicq();
        assertNotNull(result);
    }

    @Test
    public void testPublicq_ReturnsStringWithCorrectLength() {
        String result = employeeResource.publicq();
        assertEquals(12, result.length());
    }
}