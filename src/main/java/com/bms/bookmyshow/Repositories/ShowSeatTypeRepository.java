package com.bms.bookmyshow.Repositories;

import com.bms.bookmyshow.Models.Show;
import com.bms.bookmyshow.Models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository {
    List<ShowSeatType> findAllByShow(Show show);
}
