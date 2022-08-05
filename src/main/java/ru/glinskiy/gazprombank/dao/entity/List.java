package ru.glinskiy.gazprombank.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class List {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "product_list",
            joinColumns = @JoinColumn(name = "list_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products;
}
