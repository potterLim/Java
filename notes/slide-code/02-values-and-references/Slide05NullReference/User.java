class User {
    private String name;

    User(String name) {
        this.name = name;
    }

    void changeName(String newName) {
        name = newName;
    }

    String getName() {
        return name;
    }
}
