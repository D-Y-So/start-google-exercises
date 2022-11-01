public class Group implements Exportable{
    private final int id;
    private final int size;

    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    private Group(int id, int size) {
        this.id = id;
        this.size = size;
    }

    public static Group createGroup(int id, int size){
        return new Group(id,size);
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
