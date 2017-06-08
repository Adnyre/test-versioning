package test.dto;

import java.util.List;

public class EntityDto {
    private int id;

    private String nic;

    private DependencyDto dependency;

    private List<SomethingDto> something;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public DependencyDto getDependency() {
        return dependency;
    }

    public void setDependency(DependencyDto dependency) {
        this.dependency = dependency;
    }

    public List<SomethingDto> getSomething() {
        return something;
    }

    public void setSomething(List<SomethingDto> something) {
        this.something = something;
    }
}
