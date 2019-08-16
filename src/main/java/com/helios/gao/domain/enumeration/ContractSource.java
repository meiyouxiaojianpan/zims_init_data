package com.helios.gao.domain.enumeration;

/**
 * @author : gaozhiwen
 * @date : 2019/1/1
 */
public enum ContractSource {
    HAND(1012534806321364993L, "汉得信息"),
    HELIOS(1012534806321364994L, "甄汇"),
    OTHER(1032803835493527553L, "其他");

    private Long id;

    private String name;

    ContractSource(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static ContractSource parse(String name) {
        if (name == null) {
            return null;
        }
        for (ContractSource contractSource : ContractSource.values()) {
            if (contractSource.getName().equals(name)) {
                return contractSource;
            }
        }
        return ContractSource.OTHER;
    }
}
