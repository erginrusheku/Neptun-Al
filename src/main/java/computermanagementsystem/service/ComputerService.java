package computermanagementsystem.service;

import computermanagementsystem.model.Computer;
import computermanagementsystem.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ComputerService {

    private final ComputerRepository computerRepository;

    @Autowired
    public ComputerService(ComputerRepository computerRepository){
        this.computerRepository = computerRepository;
    }

    public List<Computer> getAll() {
        return computerRepository.findAll();
    }

    public Computer addComputer(Computer computer) {
        return computerRepository.save(computer);
    }

    public Computer getById(Long id) {
        return computerRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("\"Computer not found with id: \"+id"));
    }

    public Computer updateComputer(Long id, Computer computer) {
        return computerRepository.findById(id).map(computer1 -> {
            computer1.setBrand(computer.getBrand());
            computer1.setModel(computer.getModel());
            computer1.setRAM(computer.getRAM());
            computer1.setStorageCapacity(computer.getStorageCapacity());
            computer1.setProcessor(computer.getProcessor());
            computer1.setPrice(computer.getPrice());
            return computerRepository.save(computer);

        } ).orElseThrow(()-> new IllegalArgumentException("Computer not found with id" + id));
    }

    public void deleteComputer(Long id) {
        computerRepository.deleteById(id);
    }
}
