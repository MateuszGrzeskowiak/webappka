package pl.sda.mg.webappka.model;

public enum GradeSubject {
    ENGLISH("English"),
    POLISH("Polish"),
    COMPUTER_SCIENCE("Computer Sciene"),
    MATHEMATICS("Math"),
    PE("WF"),
    MUSIC_CLASS("Music");

    private String commonName;

    GradeSubject(String commonName) {
        this.commonName = commonName;
    }

    public String getCommonName() {
        return commonName;
    }
}
