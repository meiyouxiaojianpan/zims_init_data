package com.helios.gao.domain.enumeration;

/**
 * @author : gaozhiwen
 * @date : 2019/1/1
 */
public enum CustomerSuccessManager {
    WEI(1067389508980424705L, "魏晓晨"),
    WANG(1067389508980424706L, "王波"),;

    private Long id;

    private String name;

    CustomerSuccessManager(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static CustomerSuccessManager parse(String name) {
        if (name == null) {
            return null;
        }
        for (CustomerSuccessManager customerSuccessManager : CustomerSuccessManager.values()) {
            if (customerSuccessManager.name.equals(name)) {
                return customerSuccessManager;
            }
        }
        return null;
    }
}
