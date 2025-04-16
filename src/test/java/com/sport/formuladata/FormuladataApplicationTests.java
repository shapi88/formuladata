package com.sport.formuladata;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(properties = {"spring.main.allow-bean-definition-overriding=true"})
@ActiveProfiles("test")
class FormulaDataApplicationTests {

    @Test
    void contextLoads() {
        // Verifies application context loads
    }
}