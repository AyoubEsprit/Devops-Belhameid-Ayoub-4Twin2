package tn.esprit.studentmanagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.studentmanagement.entities.Department;
import tn.esprit.studentmanagement.repositories.DepartmentRepository;
import tn.esprit.studentmanagement.services.DepartmentService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDepartments() {
        // Arrange
        Department d1 = new Department();
        Department d2 = new Department();
        when(departmentRepository.findAll()).thenReturn(Arrays.asList(d1, d2));

        // Act
        List<Department> result = departmentService.getAllDepartments();

        // Assert
        assertEquals(2, result.size());
        verify(departmentRepository, times(1)).findAll();
    }

    @Test
    void testGetDepartmentById_Found() {
        Department d = new Department();
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(d));

        Department result = departmentService.getDepartmentById(1L);

        assertNotNull(result);
        verify(departmentRepository).findById(1L);
    }

    @Test
    void testGetDepartmentById_NotFound() {
        when(departmentRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class,
                () -> departmentService.getDepartmentById(99L));
    }

    @Test
    void testSaveDepartment() {
        Department d = new Department();
        when(departmentRepository.save(d)).thenReturn(d);

        Department result = departmentService.saveDepartment(d);

        assertEquals(d, result);
        verify(departmentRepository).save(d);
    }

    @Test
    void testDeleteDepartment() {
        departmentService.deleteDepartment(10L);
        verify(departmentRepository).deleteById(10L);
    }
}
