class User {
    private String name;
    private int level;

    User(String name, int level) {
        this.name = name;
        this.level = level;
    }

    void changeLevel(int newLevel) {
        level = newLevel;
    }

    @Override
    public String toString() {
        return name + ", level = " + level;
    }
}
