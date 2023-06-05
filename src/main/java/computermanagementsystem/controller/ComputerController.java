package computermanagementsystem.controller;

import computermanagementsystem.dto.ComputerDTO;
import computermanagementsystem.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/computers")
public class ComputerController {
    private final ComputerService computerService;

    @Autowired
    public ComputerController(ComputerService computerService){
        this.computerService = computerService;
    }

    @GetMapping
    public List<ComputerDTO> getAll(){
        return computerService.getAll();
    }


    @PostMapping
    public ComputerDTO addComputer(@RequestBody ComputerDTO computerDTO){
        return computerService.addComputer(computerDTO);
    }

    @GetMapping("/{id}")
    public ComputerDTO getById(@PathVariable Long id){
        return computerService.getById(id);
    }

    @PutMapping("/{id}")
    public ComputerDTO updateComputer(@PathVariable Long id, @RequestBody ComputerDTO computer){
        return computerService.updateComputer(id,computer);
    }

    @DeleteMapping("/{id}")
    public void deleteComputer(@PathVariable Long id){
        computerService.deleteComputer(id);
    }
}
