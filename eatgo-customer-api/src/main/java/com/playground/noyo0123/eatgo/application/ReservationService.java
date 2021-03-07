package com.playground.noyo0123.eatgo.application;

import com.playground.noyo0123.eatgo.domain.Reservation;
import com.playground.noyo0123.eatgo.domain.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReservationService {

    private ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation addReservation(Long restaurantId, Long userId, String name, String date, String time, Integer partySize) {
        Reservation reservation = Reservation.builder()
                .restaurantId(restaurantId)
                .userId(userId)
                .name(name)
                .date(date)
                .time(time)
                .partySize(partySize)
                .build();

        return reservationRepository.save(reservation);
    }
}
