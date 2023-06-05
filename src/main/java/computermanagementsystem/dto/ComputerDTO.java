package computermanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComputerDTO {
    private Long id;
    private String brand;
    private String model;
    private String processor;
    private int RAM;
    private int storageCapacity;
    private double price;

}
