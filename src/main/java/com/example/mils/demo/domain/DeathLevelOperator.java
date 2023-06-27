package com.example.mils.demo.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Scope("singleton") // NOTE: この記述は特になくてもシングルトンになるが明示的にするため記述している。
@Data
public class DeathLevelOperator {
    /**
     * マイルストーンのデスマーチ率を決めるグローバル変数
     * デフォルト値はUSUAL1.0でその場合マイルストーンに変化はない
     * 
     */
    private DeathLevel deathLevel = DeathLevel.USUAL;

    public double getDeathRate() {
        return deathLevel.getRate();
    }

    public void setDeathLevel(DeathLevel deathLevel) {
        this.deathLevel = deathLevel;
    }
}