package chap2;

class Greeter {
    private String format;
    String greet(String guest) {
        return String.format(format, guest);
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
