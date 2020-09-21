package ahjz.edu.entity;

public class Banner {
    private int id;
    private String imgPath;

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Banner(int id, String imgPath) {
        this.id = id;
        this.imgPath = imgPath;
    }
}
