public class Currency {
    private int id;
    private String numCode;
    private String charCode;
    private int scale;
    private String name;
    private double rate;

    public Currency() {
    }

    public Currency(int id, String numCode, String charCode, int scale, String name, double rate) {
        this.id = id;
        this.numCode = numCode;
        this.charCode = charCode;
        this.scale = scale;
        this.name = name;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumCode() {
        return numCode;
    }

    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return id + ";" + numCode + ";" + charCode + ";" + scale + ";" + name + ";" + rate;
    }
}
