package sample;

public class MenuGetter extends DataGetter {
    @Override
    public void setData(String u) {
        super.setMaxItem(12);
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

    public String getRecipe(){
        return getData().get(11);
    }
}
