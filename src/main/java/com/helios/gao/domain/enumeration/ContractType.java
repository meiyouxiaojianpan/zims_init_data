package com.helios.gao.domain.enumeration;

/**
 * @author : gaozhiwen
 * @date : 2018/12/31
 */
public enum ContractType {
    NEW_CONTRACT(1012534806321364995L,"新签合同"),
    RENEW(1012534806321364996L, "软件续约"),
    INCREASE(1012534806321364997L, "软件增订"),
    ADD_PURCHASE(1012534806321364998L, "实施增购");

    private Long id;

    private String name;

    ContractType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static ContractType parse(String name) {
        if (name == null) {
            return null;
        }
        for (ContractType type : ContractType.values()) {
            if (type.name.equals(name)) {
                return type;
            }
        }
        return null;
    }
}
