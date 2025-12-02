package tn.esprit.studentmanagement.Services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.studentmanagement.entities.Enrollment;
import tn.esprit.studentmanagement.repositories.EnrollmentRepository;
import tn.esprit.studentmanagement.services.EnrollmentService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnrollmentServiceTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    private EnrollmentService enrollmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEnrollments() {
        Enrollment e1 = new Enrollment();
        Enrollment e2 = new Enrollment();
        when(enrollmentRepository.findAll()).thenReturn(Arrays.asList(e1, e2));

        List<Enrollment> result = enrollmentService.getAllEnrollments();

        assertEquals(2, result.size());
        verify(enrollmentRepository).findAll();
    }

    @Test
    void testGetEnrollmentById_Found() {
        Enrollment e = new Enrollment();
        when(enrollmentRepository.findById(1L)).thenReturn(Optional.of(e));

        Enrollment result = enrollmentService.getEnrollmentById(1L);

        assertNotNull(result);
        verify(enrollmentRepository).findById(1L);
    }

    @Test
    void testGetEnrollmentById_NotFound() {
        when(enrollmentRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class,
                () -> enrollmentService.getEnrollmentById(99L));
    }

    @Test
    void testSaveEnrollment() {
        Enrollment e = new Enrollment();
        when(enrollmentRepository.save(e)).thenReturn(e);

        Enrollment result = enrollmentService.saveEnrollment(e);

        assertEquals(e, result);
        verify(enrollmentRepository).save(e);
    }

    @Test
    void testDeleteEnrollment() {
        enrollmentService.deleteEnrollment(10L);
        verify(enrollmentRepository).deleteById(10L);
    }
}
