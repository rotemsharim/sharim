package com.sharim.sharim.entities;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "limitation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LimitationEntity {

    @Id
    @Column(name = "lim_num")
    @GeneratedValue
    Integer limId;

    String empId;

    Date startDate;

    Date endDate;

    String comment;

    boolean limStatus;

    Date insertDate;

    int limGroup;
}
