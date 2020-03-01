package moaad.klaib.com.data;


public class MyChild
{
    private String fatherName;
    private String fatherPhone,childPhone;

    public MyChild() {
        super();
    }

    public String getFatherPhone() {
        return fatherPhone;
    }

    public void setFatherPhone(String fatherPhone) {
        this.fatherPhone = fatherPhone;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getChildPhone() {
        return childPhone;
    }

    public void setChildPhone(String childPhone) {
        this.childPhone = childPhone;
    }

    @Override
    public String toString() {
        return "MyChild{" +
                "fatherName='" + fatherName + '\'' +
                ", fatherPhone=" + fatherPhone +
                ", childPhone=" + childPhone +
                '}';
    }
}
