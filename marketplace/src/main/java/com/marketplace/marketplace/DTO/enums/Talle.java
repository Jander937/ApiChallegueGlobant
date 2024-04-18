package com.marketplace.marketplace.DTO.enums;

public enum Talle {
    TALLE_45(45),
    TALLE_46(46),
    TALLE_47(47),
    TALLE_48(48),
    TALLE_49(49),
    TALLE_50(50),
    TALLE_55(55);

    private final int valorNumerico;

    Talle(int valorNumerico) {
        this.valorNumerico = valorNumerico;
    }

    public int getValorNumerico() {
        return valorNumerico;
    }
}
