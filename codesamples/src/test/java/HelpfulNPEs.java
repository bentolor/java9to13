class HelpfulNPEs {
    record Person(String name, String email) {}
    public static void main(String[] args) {
        var p = new Person("Peter", null);
        String s = p.email();
        var e = s.toLowerCase();
    }
}
