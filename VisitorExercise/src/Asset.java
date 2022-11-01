public class Asset implements Exportable{
    private final int serialNumber;
    private final String owner;
    private final double rating;

    public int getSerialNumber() {
        return serialNumber;
    }

    public String getOwner() {
        return owner;
    }

    public double getRating() {
        return rating;
    }

    private Asset(int serialNumber, String owner, double rating) {
        this.serialNumber = serialNumber;
        this.owner = owner;
        this.rating = rating;
    }

    public static Asset createAsset(int serialNumber, String owner, double rating){
        return new Asset(serialNumber, owner, rating);
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
