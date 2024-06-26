package demo.h2database.model;

public class Department {
    private int deptno;
    private String name;
    private String loc;

    public Department(int deptno, String name, String loc) {
        this.deptno = deptno;
        this.name = name;
        this.loc = loc;
    }

    public Department(String dname, String loc) {
        this.name = name;
        this.loc = loc;
    }

    public int getDeptno() {
        return deptno;
    }

    public String getName() {
        return name;
    }

    public String getLoc() {
        return loc;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }
}

