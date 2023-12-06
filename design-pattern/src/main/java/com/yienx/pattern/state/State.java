package com.yienx.pattern.state;

/**
 * @Author wangyanbo29
 * @Date 2023/11/22
 * @Description
 */
public abstract class State {
    Player player;

    State(Player player) {
        this.player = player;
    }

    public abstract String onLock();
    public abstract String onPlay();
    public abstract String onNext();
    public abstract String onPrevious();
}
