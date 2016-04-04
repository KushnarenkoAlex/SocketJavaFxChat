package sample;

public class UserHolder {

    private String name;

    @Override
    public String toString() {
        return "UserHolder{" +
                "name='" + name + '\'' +
                '}';
    }

    public UserHolder() {
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserHolder(String name) {

        this.name = name;
    }
}
