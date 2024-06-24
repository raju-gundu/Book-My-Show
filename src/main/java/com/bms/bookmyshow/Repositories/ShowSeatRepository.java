package com.bms.bookmyshow.Repositories;

import com.bms.bookmyshow.Models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat,Long> {
    @Override
    List<ShowSeat> findAllById(Iterable<Long> showSeatId);

    @Override
    ShowSeat save(ShowSeat showSeat);

}
