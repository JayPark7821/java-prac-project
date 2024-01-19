package kr.jay.pilotproject.interfaces;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.layout.font.FontProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ConvertHtmlToPdfSample
 *
 * @author jaypark
 * @version 1.0.0
 * @since 1/8/24
 */
public class ConvertHtmlToPdfSample {

    public static void main(String[] args) throws IOException {
        // IO
        File htmlSource = new File("src/main/resources/testaa.html");
        File pdfDest = new File("output.pdf");

        FontProvider fontProvider = new DefaultFontProvider(false, false, false);
        fontProvider.addFont(FontProgramFactory.createFont("src/main/resources/NanumGothic-Regular.ttf"));

        ConverterProperties properties = new ConverterProperties();
        properties.setFontProvider(fontProvider);

        // pdfHTML specific code
        HtmlConverter.convertToPdf(
            new FileInputStream(htmlSource),
            new FileOutputStream(pdfDest),
            properties
        );
    }
}
