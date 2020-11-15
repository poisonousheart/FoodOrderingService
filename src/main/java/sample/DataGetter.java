package sample;

import java.util.ArrayList;
import java.util.List;

public class DataGetter {
    private List<String> data;
    private int maxItem;

    public void setData(String u){
        //String tmp[] = ApiController.getMethod(u).split("[:,\"{}]");
        String tmp[] = u.split("[:,\"{}]");
        ArrayList<String> stmp = new ArrayList<>();
        for (int i = 0; i < tmp.length; i++) {
            if(tmp[i] != null && !tmp[i].trim().isEmpty()) {
                stmp.add(tmp[i]);
            }
        }
        this.data = stmp.subList(0, maxItem);

    }

    void setMaxItem(int maxItem) {
        this.maxItem = maxItem;
    }

    public int getMaxItem(){
        return maxItem;
    }

    public String getId(){
        return data.get(1);
    }

    public List<String> getData(){
        return data;
    }

    public String getMenuId(){
        return data.get(3);
    }

    //------------------------------debug-------------------------------
    public void debug(){
        for (String d:data) {
            System.out.println(d);
        }
    }
}
