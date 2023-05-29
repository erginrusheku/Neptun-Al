package computermanagementsystem;

import computermanagementsystem.model.Computer;
import computermanagementsystem.repository.ComputerRepository;
import computermanagementsystem.service.ComputerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ComputerServiceTest {

    private ComputerService computerService;

    @Mock
    private ComputerRepository computerRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        computerService = new ComputerService(computerRepository);
    }

    @Test
    public void testGetAllComputers() {
        // Arrange
        List<Computer> computers = new ArrayList<>();
        computers.add(new Computer(1L,"LENOVO", "ThinkCenter", "Intel Core i5", 16, 256, 455.99));
        Mockito.when(computerRepository.findAll()).thenReturn(computers);

        // Act
        List<Computer> result = computerService.getAll();

        // Assert
        Assertions.assertEquals(computers, result);
    }

    @Test
    public void testAddComputer() {
        // Arrange
        Computer computer = new Computer(2L,"Dell", "Latitude", "Intel Core i5", 8, 256, 999.99);
        Mockito.when(computerRepository.save(computer)).thenReturn(computer);

        // Act
        Computer result = computerService.addComputer(computer);

        // Assert
        Assertions.assertEquals(computer, result);
    }

    @Test
    public void testGetComputerById() {
        // Arrange
        Long computerId = 1L;
        Computer computer = new Computer(computerId, "Philip", "TRS", "Intel Core i6", 32, 1024, 1299.99);
        Mockito.when(computerRepository.findById(computerId)).thenReturn(Optional.of(computer));

        // Act
        Computer result = computerService.getById(computerId);

        // Assert
        Assertions.assertEquals(computer, result);
    }

    @Test
    public void testUpdateComputer() {
        // Arrange
        Long computerId = 1L;
        Computer existingComputer = new Computer(computerId, "Dell", "Latitude", "Intel Core i5", 8, 256, 999.99);
        Computer updatedComputer = new Computer(computerId, "Dell", "Latitude", "Intel Core i7", 16, 512, 1299.99);
        Mockito.when(computerRepository.findById(computerId)).thenReturn(Optional.of(existingComputer));
        Mockito.when(computerRepository.save(existingComputer)).thenReturn(updatedComputer);

        // Act
        Computer result = computerService.updateComputer(computerId, updatedComputer);

        // Assert
        Assertions.assertEquals(updatedComputer, result);
    }

    @Test
    public void testDeleteComputer() {
        // Arrange
        Long computerId = 1L;

        // Act
        Assertions.assertDoesNotThrow(() -> computerService.deleteComputer(computerId));

        // Assert
        Mockito.verify(computerRepository, Mockito.times(1)).deleteById(computerId);
    }
}