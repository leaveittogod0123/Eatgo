package com.playground.noyo0123.eatgo.application;

import com.playground.noyo0123.eatgo.domain.Reservation;
import com.playground.noyo0123.eatgo.domain.ReservationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class ReservationServiceTest {


    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        reservationService = new ReservationService(reservationRepository);
    }

    @Test
    public void addReservation() {

        Long userId = 1004L;
        String name = "John";
        String date = "2011-11-12";
        String time = "12:00";
        Integer partySize = 20;
        Long restaurantId = 369L;

        // will, willReturn 차이는?
        given(reservationRepository.save(any()))
                .will(invocation -> {
                   Reservation reservation = invocation.getArgument(0);
                   return reservation;
                });

        Reservation reservation = reservationService.addReservation(restaurantId, userId, name, date, time, partySize);

        assertThat(reservation.getName(), is(name));

        verify(reservationRepository).save(any(Reservation.class));
    }

}