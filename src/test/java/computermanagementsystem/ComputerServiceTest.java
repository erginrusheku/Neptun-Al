package computermanagementsystem;

import computermanagementsystem.dto.ComputerDTO;
import computermanagementsystem.mapper.ComputerMapper;
import computermanagementsystem.model.Computer;
import computermanagementsystem.repository.ComputerRepository;
import computermanagementsystem.service.ComputerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ComputerServiceTest {
    @InjectMocks
    private ComputerService computerService;

    @Mock
    private ComputerRepository computerRepository;

    @Mock

    private ComputerMapper computerMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllComputers() {
        // Arrange
        Computer computer1 = new Computer(1L,"Dell", "Latitude", "Intel Core i5", 8, 256, 999.99);
        Computer computer2 = new Computer(2L,"Dell", "Latitude", "Intel Core i5", 8, 256, 999.99);
        List<Computer> computers = Arrays.asList(computer1,computer2);

        Mockito.when(computerRepository.findAll()).thenReturn(computers);
        Mockito.when(computerMapper.toDto(computer1)).thenReturn(new ComputerDTO(1L,"Dell", "Latitude", "Intel Core i5", 8, 256, 999.99));
        Mockito.when(computerMapper.toDto(computer2)).thenReturn(new ComputerDTO(2L,"Dell", "Latitude", "Intel Core i5", 8, 256, 999.99));

        // Act
        List<ComputerDTO> result = computerService.getAll();

        // Assert
        assertEquals(computers.size(), result.size());
    }

    @Test
    public void testAddComputer() {
        // Arrange
        ComputerDTO computerDTO = new ComputerDTO(1L,"Dell", "Latitude", "Intel Core i5", 8, 256, 999.99);
        Computer computer = new Computer(1L,"Dell", "Latitude", "Intel Core i5", 8, 256, 999.99);

        Mockito.when(computerMapper.toEntity(computerDTO)).thenReturn(computer);
        Mockito.when(computerMapper.toDto(computer)).thenReturn(computerDTO);
        Mockito.when(computerRepository.save(computer)).thenReturn(computer);

        // Act
        ComputerDTO result = computerService.addComputer(computerDTO);

        // Assert
        assertEquals(computerDTO, result);
    }

    @Test
    public void testGetComputerById() {
        // Arrange
        Long computerId = 1L;
        Computer computer = new Computer(computerId, "Dell", "Latitude", "Intel Core i5", 8, 256, 999.99);
        ComputerDTO computerDTO = new ComputerDTO(computerId, "Dell", "Latitude", "Intel Core i5", 8, 256, 999.99);

        Mockito.when(computerRepository.findById(computerId)).thenReturn(Optional.of(computer));
        Mockito.when(computerMapper.toDto(computer)).thenReturn(computerDTO);

        // Act
        ComputerDTO result = computerService.getById(computerId);

        // Assert
        assertEquals(computerDTO, result);
    }

    @Test
    public void testUpdateComputer() {
        // Arrange
        Long computerId = 1L;
        ComputerDTO updatedComputerDTO = new ComputerDTO(computerId, "Dell", "Latitude", "Intel Core i5", 8, 256, 999.99);
        Computer updatedComputer = new Computer(computerId, "Dell", "Latitude", "Intel Core i7", 16, 512, 1299.99);
        Mockito.when(computerRepository.findById(computerId)).thenReturn(Optional.of(new Computer()));
        Mockito.when(computerRepository.save(any(Computer.class))).thenReturn(updatedComputer);
        Mockito.when(computerMapper.toDto(updatedComputer)).thenReturn(updatedComputerDTO);

        // Act
        ComputerDTO result = computerService.updateComputer(computerId, updatedComputerDTO);

        // Assert
        assertEquals(updatedComputerDTO, result);
    }

    @Test
    public void testDeleteComputer() {
        // Arrange
        Long computerId = 1L;

        // Act
        computerService.deleteComputer(computerId);

        // Assert
        verify(computerRepository, times(1)).deleteById(computerId);
    }
}