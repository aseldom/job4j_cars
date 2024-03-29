package ru.job4j.cars.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "of")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"car", "owner"})
@Entity
@Table(name = "history_owner")
public class HistoryOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @ManyToOne()
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    private Timestamp startAt;

    private Timestamp endAt = Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS));

}
