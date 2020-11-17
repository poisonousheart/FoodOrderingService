package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class MenuSlicer extends DataSlicer {
    @Override
    public void setData(String u) {
        super.setMaxItem(14);
        super.setData(u);
    }

    public String getMenuStatus(){
        return getData().get(5);
    }

    public String getMenuName(){
        return getData().get(7);
    }

    public String getPrice(){
        return getData().get(9);
    }

    public Image getImage() throws IOException {
        String s = getData().get(12);
        byte[] imageBytes = Base64.getMimeDecoder().decode(s);
        InputStream readedImage = new ByteArrayInputStream(imageBytes);
        BufferedImage bfImage = ImageIO.read(readedImage);
        Image image = SwingFXUtils.toFXImage(bfImage, null);
        return image;
    }
}
