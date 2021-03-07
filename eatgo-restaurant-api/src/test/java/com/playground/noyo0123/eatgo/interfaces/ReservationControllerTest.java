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
        import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    public void list() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJKb2huIiwicmVzdGF1cmFudElkIjoxMDA0fQ.Y5uTIWl-4PVcN_1KKWlCGSleQdb651oL7w_3y3wywX8";

        mvc.perform(get("/reservations")
                .header("Authorization", "Bearer " + token)
        ).andExpect(status().isOk());


        // 실행되는지 확인
        verify(reservationService).getReservations(1004L);

    }
}