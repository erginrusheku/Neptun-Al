package computermanagementsystem;

import computermanagementsystem.controller.ComputerController;
import computermanagementsystem.model.Computer;
import computermanagementsystem.service.ComputerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ComputerController.class)
public class ComputerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ComputerService computerService;

    private List<Computer> computers;

    @BeforeEach
    public void setup() {
        computers = Arrays.asList(
                new Computer(1L, "Dell", "Latitude", "Intel Core i5", 8, 256, 999.99),
                new Computer(2L, "HP", "Pavilion", "Intel Core i7", 16, 512, 1499.99)
        );
    }

    @Test
    public void testGetAllComputers() throws Exception {
        Mockito.when(computerService.getAll()).thenReturn(computers);

        mockMvc.perform(MockMvcRequestBuilders.get("/computers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].brand").value("Dell"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].brand").value("HP"));
    }

    @Test
    public void testAddComputer() throws Exception {
        Computer computer = new Computer(1L, "Dell", "Latitude", "Intel Core i5", 8, 256, 999.99);
        Mockito.when(computerService.addComputer(any(Computer.class))).thenReturn(computer);

        String computerJson = "{\"brand\":\"Dell\",\"model\":\"Latitude\",\"processor\":\"Intel Core i5\",\"ram\":8,\"storageCapacity\":256,\"price\":999.99}";

        mockMvc.perform(MockMvcRequestBuilders.post("/computers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(computerJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.brand").value("Dell"));
    }

    @Test
    public void testGetById() throws Exception {
        Long computerId = 1L;
        Computer computer = new Computer(1L, "Dell", "Latitude", "Intel Core i5", 8, 256, 999.99);
        Mockito.when(computerService.getById(computerId)).thenReturn(computer);

        mockMvc.perform(MockMvcRequestBuilders.get("/computers/{id}", computerId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.brand").value("Dell"));
    }

    @Test
    public void testUpdateComputer() throws Exception {
        Long computerId = 1L;
        Computer updatedComputer = new Computer(1L, "Dell", "Latitude", "Intel Core i7", 16, 512, 1299.99);
        Mockito.when(computerService.updateComputer(computerId, updatedComputer)).thenReturn(updatedComputer);

    }
    @Test
    public void testDeleteComputer() throws Exception {
        Long computerId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/computers/{id}", computerId))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(computerService, Mockito.times(1)).deleteComputer(computerId);
    }

}
