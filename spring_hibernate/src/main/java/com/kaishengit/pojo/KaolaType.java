package com.kaishengit.pojo;


import javax.persistence.*;

@Entity
@Table(name = "kaola_type")
public class KaolaType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "type_name")
    private String typeName;

    @Column(name = "parent_id")
    private Integer parentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "KaolaType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
