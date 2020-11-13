package sample;

public class OrderGetter extends DataGetter {
    @Override
    public void setData(String u) {
        super.setMaxItem(8);
        super.setData(u);
    }

    public String getTableNo(){
        return super.getData().get(5);
    }

    public String getStatus(){
        return super.getData().get(7);
    }
}
