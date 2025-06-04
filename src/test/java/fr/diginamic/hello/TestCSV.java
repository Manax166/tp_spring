package fr.diginamic.hello;

import com.itextpdf.text.DocumentException;
import fr.diginamic.hello.controleur.VilleControleur;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class TestCSV {
    @Test
    void genereCSV() throws DocumentException, IOException {
        VilleControleur vc = new VilleControleur();
        HttpServletResponse response = new Response();
        vc.exportToCSVVilleswithNbHabitantsGreaterThan(100000, response);
    }
}
