package sample;

public class MenuSlicer extends DataSlicer {
    @Override
    public void setData(String u) {
        super.setMaxItem(14);
        super.setData(u);
    }
}
