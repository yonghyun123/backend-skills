package jpql;

public class MemberDTO {
    private String usersname;
    private int age;

    public MemberDTO(String usersname, int age) {
        this.usersname = usersname;
        this.age = age;
    }

    public String getUsersname() {
        return usersname;
    }

    public void setUsersname(String usersname) {
        this.usersname = usersname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
