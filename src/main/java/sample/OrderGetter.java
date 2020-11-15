package sample;

public class OrderGetter extends DataGetter {
    @Override
    public void setData(String u) {
        super.setMaxItem(12);
        super.setData(u);
    }

    public String getTableNo(){
        return super.getData().get(5);
    }

    public String getStatus(){
        return super.getData().get(7);
    }

    public String getTimeCreated(){
        String[] tmp = super.getData().get(9).split("T");
        String[] tmp2 = super.getData().get(11).split("\\.");
        String time = tmp[1] +":"+super.getData().get(10)+":"+ tmp2[0];
        System.out.println(time);
        return time;
    }
}
