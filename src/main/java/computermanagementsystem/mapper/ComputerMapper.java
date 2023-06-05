package computermanagementsystem.mapper;

import computermanagementsystem.dto.ComputerDTO;
import computermanagementsystem.model.Computer;
import org.springframework.stereotype.Component;

@Component
public class ComputerMapper {

    public ComputerDTO toDto(Computer computer){
        computermanagementsystem.dto.ComputerDTO computerDTO = new computermanagementsystem.dto.ComputerDTO();
        computerDTO.setId(computer.getId());
        computerDTO.setBrand(computer.getBrand());
        computerDTO.setModel(computer.getModel());
        computerDTO.setProcessor(computer.getProcessor());
        computerDTO.setRAM(computer.getRAM());
        computerDTO.setStorageCapacity(computer.getStorageCapacity());
        computerDTO.setPrice(computer.getPrice());
        return computerDTO;
    }

    public Computer toEntity(computermanagementsystem.dto.ComputerDTO computerDTO){
        Computer computer = new Computer();
        computer.setId(computerDTO.getId());
        computer.setBrand(computerDTO.getBrand());
        computer.setModel(computerDTO.getModel());
        computer.setProcessor(computerDTO.getProcessor());
        computer.setRAM(computerDTO.getRAM());
        computer.setStorageCapacity(computerDTO.getStorageCapacity());
        computer.setPrice(computerDTO.getPrice());
        return computer;
    }

}
