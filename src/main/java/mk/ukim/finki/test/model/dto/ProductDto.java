package mk.ukim.finki.test.model.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class ProductDto {
        private String name;
        private Double price;
        private Integer quantity;
        private Long manufacturer;
        private Long category;

        public ProductDto() {
        }

    public ProductDto(String name, Double price, Integer quantity, Long manufacturer, Long category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.manufacturer = manufacturer;
        this.category = category;
    }
}