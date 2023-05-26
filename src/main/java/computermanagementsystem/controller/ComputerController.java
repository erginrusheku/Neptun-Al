package computermanagementsystem.controller;

import computermanagementsystem.model.Computer;
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
    public List<Computer> getAll(){
        return computerService.getAll();
    }

    @PostMapping
    public Computer addComputer(@RequestBody Computer computer){
        return computerService.addComputer(computer);
    }

    @GetMapping("/{id}")
    public Computer getById(@PathVariable Long id){
        return computerService.getById(id);
    }

    @PutMapping("/{id}")
    public Computer updateComputer(@PathVariable Long id, @RequestBody Computer computer){
        return computerService.updateComputer(id,computer);
    }

    @DeleteMapping("/{id}")
    public void deleteComputer(@PathVariable Long id){
        computerService.deleteComputer(id);
    }
}
