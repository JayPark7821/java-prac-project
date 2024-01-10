package kr.jay.pilotproject.interfaces;

import com.itextpdf.html2pdf.HtmlConverter;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PdfController
 *
 * @author jaypark
 * @version 1.0.0
 * @since 1/8/24
 */

@RestController
public class PdfController {

    @PostMapping("/generatePdfFileUsingOpenPDF")
    public void generatePdfFile(HttpServletResponse response) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/testaa.html");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(getFileContent(fileInputStream, StandardCharsets.UTF_8.name()), outputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=file.pdf");
        IOUtils.copy(inputStream, response.getOutputStream());
    }


    public static String getFileContent(
        FileInputStream fis,
        String encoding) throws IOException {
        try (BufferedReader br =
            new BufferedReader(new InputStreamReader(fis, encoding))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
            return sb.toString();
        }
    }
}
