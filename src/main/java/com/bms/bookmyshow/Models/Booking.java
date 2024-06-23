package com.bms.bookmyshow.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.print.Book;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
public class Booking extends BaseModel {
    @ManyToMany
    private List<ShowSeat> seats;

    @ManyToOne
    private Show show;

    @OneToMany
    private List<Payment> payments;

    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;

    @ManyToOne
    private User user;

    private int price;
    private Date timeOfBooking;

}
