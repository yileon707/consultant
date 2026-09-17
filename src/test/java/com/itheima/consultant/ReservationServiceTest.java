package com.itheima.consultant;

import com.itheima.consultant.pojo.Reservation;
import com.itheima.consultant.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ReservationServiceTest {
    @Autowired
    private ReservationService reservationService;
    //测试查询
    @Test
    void testFindByPhone(){
        String phone = "TEST_PHONE";
        List<Reservation> reservation = reservationService.findByPhone(phone);
        System.out.println(reservation);
    }
}
