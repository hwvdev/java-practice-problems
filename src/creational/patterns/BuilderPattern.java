package creational.patterns;

public class BuilderPattern {
    private final String name;
    private final int age;
    private final String address;

    private BuilderPattern(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.address = builder.address;
    }

    public static class Builder {
        private String name;
        private int age;
        private String address;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        public Builder setAge(int age) {
            this.age = age;
            return this;
        }
        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }
        public BuilderPattern build() {
            return new BuilderPattern(this);
        }
    }

    @Override
    public String toString() {
        return "BuilderPattern{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }

    public static void main(String[] args) {
        BuilderPattern builderPattern = new Builder().setName("vijay").setAge(23).setAddress("New Delhi").build();
        System.out.println(builderPattern);
    }
}
