package ru.job4j.cars.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "price_history")
public class PriceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private Long before;

    private Long after;

    @Column(name = "created")
    private Timestamp startTime = Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

    @ManyToOne
    @JoinColumn(name = "auto_post_id")
    private Post post;

}
