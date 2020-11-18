package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class DataSlicer {
    private List<String> data;
    private int maxItem;

    public void setData(String u){
        //String tmp[] = ApiController.getMethod(u).split("[:\\]\\[,\"{}]");
        String tmp[] = u.split("[:\\]\\[,\"{}]");
        ArrayList<String> stmp = new ArrayList<>();
        for (int i = 0; i < tmp.length; i++) {
            if(tmp[i] != null && !tmp[i].trim().isEmpty()) {
                stmp.add(tmp[i]);
            }
        }
        this.data = stmp.subList(0, maxItem);
        System.out.println("---Slicer debug---");
        this.debug();
    }

    void setMaxItem(int maxItem) {
        this.maxItem = maxItem;
    }

    public int getMaxItem(){
        return maxItem;
    }

    public String getId(){
        int index = data.indexOf("id")+1;
        return data.get(index);
    }

    public List<String> getData(){
        return data;
    }

    public String getMenuId(){
        int index = data.indexOf("menu_id")+1;
        return data.get(index);
    }
    public String getMenuStatus(){
        int index = getData().indexOf("menu_status")+1;
        return getData().get(index);
    }

    public String getMenuName(){
        int index = getData().indexOf("menu_name")+1;
        return getData().get(index);
    }

    public String getPrice(){
        int index = getData().indexOf("price")+1;
        return getData().get(index);
    }

    public Image getImage() throws IOException {
        int w = getData().indexOf("image")+1;
        String s = getData().get(w);

        byte[] imageBytes = Base64.getMimeDecoder().decode(s);
        InputStream readedImage = new ByteArrayInputStream(imageBytes);
        BufferedImage bfImage = ImageIO.read(readedImage);
        Image image = SwingFXUtils.toFXImage(bfImage, null);
        return image;
    }
    //------------------------------debug-------------------------------
    public void debug(){
        for (String d:data) {
            System.out.println(d);
        }
    }
}
