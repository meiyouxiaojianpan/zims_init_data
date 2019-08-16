package com.helios.gao.domain.enumeration;

/**
 * @author : gaozhiwen
 * @date : 2019/1/1
 */
public enum RegionGroup {
    A(1078911850768302081L, "A组"),
    B(1078911850768302082L, "B组"),
    C(1078911850768302083L, "C组"),
    D(1078911850768302084L, "D组"),
    ONE(1078911850768302085L, "一组"),
    TWO(1078911850768302086L, "二组"),
    THREE(1078911850768302087L, "三组"),
    FOUR(1078911850768302088L, "四组"),
    FIVE(1078911850768302089L, "五组"),
    SIX(1078911850768302090L, "六组"),
    MANAGEMENT(1078911850768302091L, "管理组"),;

    private Long id;

    private String name;

    RegionGroup(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static RegionGroup parse(String name) {
        if (name == null) {
            return null;
        }
        for (RegionGroup regionGroup : RegionGroup.values()) {
            if (regionGroup.getName().equals(name)) {
                return regionGroup;
            }
        }
        return null;
    }
}
