package computermanagementsystem;

import computermanagementsystem.controller.ComputerController;
import computermanagementsystem.dto.ComputerDTO;
import computermanagementsystem.mapper.ComputerMapper;
import computermanagementsystem.service.ComputerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ComputerController.class)
public class ComputerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ComputerService computerService;

    @Mock
    private ComputerMapper computerMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllComputers() throws Exception {
        // Arrange
        ComputerDTO computerDTO1 = new ComputerDTO(1L, "Dell", "Latitude", "Intel Core i5", 8, 256, 999.99);
        ComputerDTO computerDTO2 = new ComputerDTO(2L, "HP", "Pavilion", "Intel Core i7", 16, 512, 1499.99);
        List<ComputerDTO> computerDTOs = Arrays.asList(computerDTO1, computerDTO2);

        when(computerService.getAll()).thenReturn(computerDTOs);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/computers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].brand").value("Dell"))
                .andExpect(jsonPath("$[0].model").value("Latitude"))
                .andExpect(jsonPath("$[0].processor").value("Intel Core i5"))
                .andExpect(jsonPath("$[0].ram").value(8))
                .andExpect(jsonPath("$[0].storageCapacity").value(256))
                .andExpect(jsonPath("$[0].price").value(999.99))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].brand").value("HP"))
                .andExpect(jsonPath("$[1].model").value("Pavilion"))
                .andExpect(jsonPath("$[1].processor").value("Intel Core i7"))
                .andExpect(jsonPath("$[1].ram").value(16))
                .andExpect(jsonPath("$[1].storageCapacity").value(512))
                .andExpect(jsonPath("$[1].price").value(1499.99));
    }


    @Test
    public void testAddComputer() throws Exception {
        // Arrange
        ComputerDTO computerDTO = new ComputerDTO(1L, "Dell", "Latitude", "Intel Core i5", 8, 256, 999.99);

        when(computerService.addComputer(computerDTO)).thenReturn(computerDTO);

        String computerJson = "{\"id\": 1,\"brand\":\"Dell\",\"model\":\"Latitude\",\"processor\":\"Intel Core i5\",\"ram\":8,\"storageCapacity\":256,\"price\":999.99}";

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/computers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(computerJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.brand").value("Dell"))
                .andExpect(jsonPath("$.model").value("Latitude"))
                .andExpect(jsonPath("$.processor").value("Intel Core i5"))
                .andExpect(jsonPath("$.ram").value(8))
                .andExpect(jsonPath("$.storageCapacity").value(256))
                .andExpect(jsonPath("$.price").value(999.99));
    }

    @Test
    public void testGetById() throws Exception {
        // Arrange
        Long computerId = 1L;
        ComputerDTO computerDTO = new ComputerDTO(computerId, "Dell", "Latitude", "Intel Core i5", 8, 256, 999.99);

        when(computerService.getById(computerId)).thenReturn(computerDTO);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/computers/{id}", computerId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.brand").value("Dell"))
                .andExpect(jsonPath("$.model").value("Latitude"))
                .andExpect(jsonPath("$.processor").value("Intel Core i5"))
                .andExpect(jsonPath("$.ram").value(8))
                .andExpect(jsonPath("$.storageCapacity").value(256))
                .andExpect(jsonPath("$.price").value(999.99));
    }

    @Test
    public void testUpdateComputer() throws Exception {
        // Arrange
        Long computerId = 1L;
        ComputerDTO updatedComputerDTO = new ComputerDTO(computerId, "HP", "Pavilion", "Intel Core i7", 16, 512, 1499.99);
        String computerJson = "{\"id\": 1,\"brand\":\"HP\",\"model\":\"Pavilion\",\"processor\":\"Intel Core i7\",\"ram\":16,\"storageCapacity\":512,\"price\":1499.99}";

        when(computerService.updateComputer(computerId, updatedComputerDTO)).thenReturn(updatedComputerDTO);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/computers/{id}", computerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(computerJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.brand").value("HP"))
                .andExpect(jsonPath("$.model").value("Pavilion"))
                .andExpect(jsonPath("$.processor").value("Intel Core i7"))
                .andExpect(jsonPath("$.ram").value(16))
                .andExpect(jsonPath("$.storageCapacity").value(512))
                .andExpect(jsonPath("$.price").value(1499.99));
    }


    @Test
    public void testDeleteComputer() throws Exception {
        // Arrange
        Long computerId = 1L;

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/computers/{id}", computerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
