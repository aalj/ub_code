package net.lll0.bus.exception;

/**
 * Created by liangjun on 2017/4/2.
 * 没有网络的异常类
 */

public class NotNetwork extends RuntimeException {


    public NotNetwork(String message) {
        super(message);
    }
}
