package com.example.mils.demo.domain;

/**
 * マイルストーンのデスマーチ率を定めるEnum
 */
public enum DeathLevel {
    USUAL("通常運転", 1.0),
    LITTLE_URGENT("ちょい急ぎ", 1.1),
    URGENT("急ぎ", 1.2),
    EMERGENCY("ヤバイ", 1.3),
    CRITICAL("ガチヤバイ", 1.4),
    BUSTER_CALL("バスターコール", 1.6),
    HELL_MARCH("地獄のデスマーチ", 2.0);

    private final String label;
    private final double rate;

    DeathLevel(String label, double rate) {
        this.label = label;
        this.rate = rate;
    }

    public String getLabel() {
        return this.label;
    }

    public double getRate() {
        return this.rate;
    }
}
