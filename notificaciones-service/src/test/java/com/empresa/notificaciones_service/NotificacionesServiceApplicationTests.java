package com.empresa.notificaciones_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "MONGO_URI=mongodb://localhost:27017/testdb",
    "RABBIT_HOST=localhost",
    "RABBIT_USER=guest",
    "RABBIT_PASS=guest"
})
class NotificacionesServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
