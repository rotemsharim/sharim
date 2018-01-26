package com.sharim.sharim.entities;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "limitation")
@Data
public class LimitationEntity {

    @Id
    @Column(name = "lim_num")
    int limId;

    String empId;

    Date startDate;

    Date endDate;

    String comment;

    boolean limStatus;

    Date insertDate;

    int limGroup;
}
