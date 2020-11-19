package sample;

public class OrderSlicer extends DataSlicer {
    @Override
    public void setData(String u) {
        super.setMaxItem(18);
        super.setData(u);
    }

    public String getStatus(){
        int index = super.getData().indexOf("status")+1;
        return super.getData().get(index);
    }

    public String getTimeCreated(){
        int index = super.getData().indexOf("updated_at");
        String[] tmp = super.getData().get(index+1).split("T");
        String[] tmp2 = super.getData().get(index+3).split("\\.");
        String time = tmp[1] +":"+super.getData().get(index+2)+":"+ tmp2[0];
        System.out.println(time);
        return time;
    }
}
