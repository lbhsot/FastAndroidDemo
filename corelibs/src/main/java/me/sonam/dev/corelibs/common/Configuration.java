package me.sonam.dev.corelibs.common;

/**
 * Created by Administrator on 2017/2/23.
 */
public class Configuration {
    private static boolean debug;
    private static boolean isShowNetworkParams = false;
    private static boolean cacheResponse = false;

    public static void setIsShowNetworkParams(boolean isShowNetworkParams) {
        Configuration.isShowNetworkParams = isShowNetworkParams;
    }

    public static boolean isCacheResponse() {
        return cacheResponse;
    }

    public static void enableCacheResponse() {
        Configuration.cacheResponse = true;
    }

    public static void disableCacheResponse() {
        Configuration.cacheResponse = false;
    }

    /**
     * 是否是调试环境
     */
    public static boolean isDebug() {
        return debug;
    }

    /**
     * 设置调试环境
     */
    public static void setDebug(boolean debug) {
        Configuration.debug = debug;
    }

    /**
     * 开启打印网络请求参数
     */
    public static void enableLoggingNetworkParams() {
        isShowNetworkParams = true;
    }

    /**
     * 关闭打印网络请求参数
     */
    public static void disableLoggingNetworkParams() {
        isShowNetworkParams = false;
    }

    /**
     * 是否打印网络请求参数
     */
    public static boolean isShowNetworkParams() {
        return isShowNetworkParams;
    }
}
