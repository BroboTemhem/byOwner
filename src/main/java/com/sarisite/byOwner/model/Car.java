package com.sarisite.byOwner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    @JsonIgnoreProperties("carBrandList")
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "model_id")
    @JsonIgnoreProperties("carModelList")
    private Model model;
    @ManyToOne
    @JoinColumn(name = "color_id")
    @JsonIgnoreProperties("carColorList")
    private Color color;
    private Integer year;
    private Double km;
    private Double price;
    @CreationTimestamp
    private LocalDateTime dateTime;


}
