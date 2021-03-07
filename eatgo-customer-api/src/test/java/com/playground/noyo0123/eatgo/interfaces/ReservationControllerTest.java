package com.playground.noyo0123.eatgo.interfaces;

import com.playground.noyo0123.eatgo.application.ReservationService;
import com.playground.noyo0123.eatgo.domain.Reservation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReservationService reservationService;

    @Test
    public void create() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJKb2huIn0.8hm6ZOJykSINHxL-rf0yV882fApL3hyQ9-WGlJUyo2A";

        Reservation mockReservation = Reservation.builder().id(12L).build();
        given(reservationService.addReservation(any(), any(), any(), any(), any(), any()))
                .willReturn(mockReservation);

        mvc.perform(post("/restaurants/369/reservations")
           .header("Authorization", "Bearer " + token)
           .contentType(MediaType.APPLICATION_JSON)
           .content("{\"date\": \"2011-11-12\", \"time\": \"12:00\", \"partySize\": 20}")
        ).andExpect(status().isCreated());

//        {"userId":1, "name": "Tester", "date": "2011-11-12", "time": "12:00", "partySize": 20}

        Long userId = 1004L;
        String name = "John";
        String date = "2011-11-12";
        String time = "12:00";
        Integer partySize = 20;
        Long restaurantId = 369L;
        verify(reservationService).addReservation(eq(restaurantId), eq(userId), eq(name), eq(date), eq(time), eq(partySize));
    }
}