package sample;

public class OrderSlicer extends DataSlicer {
    @Override
    public void setData(String u) {
        super.setMaxItem(14);
        super.setData(u);
    }

    public String getStatus(){
        int index = super.getData().indexOf("menu_status");
        return super.getData().get(index+1);
    }

    public String getTimeCreated(){
        int index = super.getData().indexOf("created_at");
        String[] tmp = super.getData().get(index+1).split("T");
        String[] tmp2 = super.getData().get(index+3).split("\\.");
        String time = tmp[1] +":"+super.getData().get(index+2)+":"+ tmp2[0];
        System.out.println(time);
        return time;
    }
}
