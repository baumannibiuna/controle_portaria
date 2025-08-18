package com.controleportaria;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // Usa o perfil de teste
class ControlePortariaApplicationTests {

    @Test
    void contextLoads() {
        // Teste vazio apenas para verificar se o contexto Spring carrega
    }
}