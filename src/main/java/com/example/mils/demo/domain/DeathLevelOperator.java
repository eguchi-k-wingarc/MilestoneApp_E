package com.example.mils.demo.domain;

import java.time.Duration;
import java.time.LocalDateTime;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import lombok.Data;

@Component
@Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
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

    public LocalDateTime adjustDeadline(LocalDateTime deadline) {
        LocalDateTime today = LocalDateTime.now();
        Duration duration = Duration.between(today, deadline);

        // durationを秒単位でdouble型に変換してdouble型で計算できるようにする
        double seconds = duration.getSeconds();
        double nanoSeconds = duration.getNano();
        double totalSeconds = seconds + nanoSeconds / 1_000_000_000.0;
        double adjustedSeconds = totalSeconds / getDeathRate();

        // 調整されたdoubleをDurationに戻す
        long adjustedWholeSeconds = (long) adjustedSeconds;
        int adjustedNanoSeconds = (int) ((adjustedSeconds - adjustedWholeSeconds) * 1_000_000_000.0);

        // 調整された秒とナノ秒から新しいDurationを作成し、それを元の時刻に追加
        Duration adjustedDuration = Duration.ofSeconds(adjustedWholeSeconds, adjustedNanoSeconds);
        return today.plus(adjustedDuration);
    }

}