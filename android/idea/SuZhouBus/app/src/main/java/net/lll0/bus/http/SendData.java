package net.lll0.bus.http;

/**
 * Created by liang on 2017/1/1.
 */
public interface SendData {
    void success(String data);

    void fail(Exception e);

}
