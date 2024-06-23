package com.bms.bookmyshow.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(name = "Shows")
public class Show extends BaseModel{

    @ManyToOne
    private Movie movie;

    private Date startTime;
    private Date endTime;

    @ManyToOne
    private Screen screen;

}
