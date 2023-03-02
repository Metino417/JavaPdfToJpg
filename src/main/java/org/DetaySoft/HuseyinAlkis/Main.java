package org.DetaySoft.HuseyinAlkis;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import javax.swing.JFileChooser;
import java.io.File;
public class Main {
    public static void main(String[] args) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(null);
        if (result != JFileChooser.APPROVE_OPTION) {
            // Retrieve the selected file
            throw new ArithmeticException("Dosya seçmediniz");
        }
        String KullaniciDizini=System.getenv("USERPROFILE");
        System.out.println(KullaniciDizini);
        String destinationDir = KullaniciDizini+"/Desktop/YeniKlasorPdfToJPG/"; // resimlerin kaydedileceği dizin

        try {
            PDDocument document = PDDocument.load(fileChooser.getSelectedFile());
            PDFRenderer renderer = new PDFRenderer(document);

            for (int pageIndex = 0; pageIndex < document.getNumberOfPages(); pageIndex++) {
                BufferedImage image = renderer.renderImage(pageIndex);

                String fileName = String.format("page-%d.jpg",pageIndex + 1);
                File YeniKlasor = new File(destinationDir+fileName);
                YeniKlasor.mkdirs();
                ImageIO.write(image, "jpg", YeniKlasor);
            }

            document.close();
        } catch (IOException e) {
            System.err.println("Hata: " + e.getMessage());
        }
    }
}
