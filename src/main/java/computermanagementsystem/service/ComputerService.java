package computermanagementsystem.service;

import computermanagementsystem.dto.ComputerDTO;
import computermanagementsystem.mapper.ComputerMapper;
import computermanagementsystem.model.Computer;
import computermanagementsystem.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ComputerService {

    private final ComputerRepository computerRepository;
    
    private final ComputerMapper computerMapper;

    @Autowired
    public ComputerService(ComputerRepository computerRepository, ComputerMapper computerMapper){
        this.computerRepository = computerRepository;
        this.computerMapper = computerMapper;
    }

    public List<ComputerDTO> getAll() {
        List<Computer> computers = computerRepository.findAll();
        List<ComputerDTO> computerDTOs = new ArrayList<>();

        for (Computer computer : computers ) {
            ComputerDTO carDTO = computerMapper.toDto(computer);
            computerDTOs.add(carDTO);
        }

        return computerDTOs;
    }

    public ComputerDTO addComputer(ComputerDTO computerDTO) {
        Computer computer = computerMapper.toEntity(computerDTO);
        Computer savedComputer = computerRepository.save(computer);
        return computerMapper.toDto(savedComputer);
    }

    public ComputerDTO getById(Long id) throws IllegalArgumentException {
        Computer computer = computerRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Computer not found with id"));
        return computerMapper.toDto(computer);
    }

    public ComputerDTO updateComputer(Long id, ComputerDTO computer) throws  IllegalArgumentException {
       Computer existingComputer = computerRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Computer not found with id" + id));
        existingComputer.setBrand(computer.getBrand());
        existingComputer.setModel(computer.getModel());
        existingComputer.setRAM(computer.getRAM());
        existingComputer.setStorageCapacity(computer.getStorageCapacity());
        existingComputer.setProcessor(computer.getProcessor());
        existingComputer.setPrice(computer.getPrice());

        Computer savedComputer = computerRepository.save(existingComputer);

        return computerMapper.toDto(savedComputer);


    }

    public void deleteComputer(Long id) {
        computerRepository.deleteById(id);
    }
}
