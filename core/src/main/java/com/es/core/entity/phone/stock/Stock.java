package com.es.core.entity.phone.stock;


import com.es.core.entity.phone.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phoneId")
    private Long phoneId;

    @OneToOne
    @JoinColumn(name = "phoneId", referencedColumnName = "id", insertable = false, updatable = false)
    private Phone phone;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "reserved", nullable = false)
    private Integer reserved;
}
