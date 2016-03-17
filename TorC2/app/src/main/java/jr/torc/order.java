package jr.torc;

public class order {

    private String name;        //string for the persons name
    private String drink;       //string for what drink they want
    private int sugar;          //int for number of sugars wanted
    private int milkLevel;      //int for the f level of milk stored for the image

    public void setName(String name)        //setter for name
    {
        this.name = name;
    }
    public String getName()                 //getter for name
    {
        return name;
    }

    public void setDrink(String drink)        //setter for drink
    {
        this.drink = drink;
    }
    public String getDrink()                 //getter for drink
    {
        return drink;
    }

    public void setSugar(int sugar)        //setter for sugar
    {
        this.sugar = sugar;
    }
    public int getSugar()                 //getter for sugar
    {
        return sugar;
    }

    public void setMilkLevel(int milkLevel)        //setter for milk level
    {
        this.milkLevel = milkLevel;
    }
    public int getMilkLevel()                 //getter for milk level
    {
        return milkLevel;
    }
}
