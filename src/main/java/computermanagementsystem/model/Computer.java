package computermanagementsystem.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private String processor;
    private int RAM;
    private int storageCapacity;
    private double price;

    public Computer(){}
    public Computer(Long id, String brand, String model, String processor, int RAM, int storageCapacity, double price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.processor = processor;
        this.RAM = RAM;
        this.storageCapacity = storageCapacity;
        this.price = price;
    }


}
