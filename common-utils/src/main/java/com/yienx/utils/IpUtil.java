package com.yienx.utils;

import org.apache.commons.lang.StringUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @author chenjianyang4
 * @description IpUtil
 * @date 2020-08-07
 */
public class IpUtil {

    /**
     * 获取本机Ip
     */
    public static String getLocalIp() {

        // 本地IP，如果没有配置外网IP则返回它
        String localIp = null;

        // 外网IP
        String outerIp = null;

        // 获取网络端口列表
        Enumeration<NetworkInterface> netInterfaces = null;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        // 查找外网IP
        if(netInterfaces != null) {
            InetAddress ip;

            // 枚举网络端口
            while (netInterfaces.hasMoreElements()) {

                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> address = ni.getInetAddresses();

                while (address.hasMoreElements()) {

                    ip = address.nextElement();
                    if (!ip.isSiteLocalAddress()
                            && !ip.isLoopbackAddress()
                            && !ip.getHostAddress().contains(":")) {

                        outerIp = ip.getHostAddress();
                        break;
                    } else if (ip.isSiteLocalAddress()
                            && !ip.isLoopbackAddress()
                            && !ip.getHostAddress().contains(":")) {

                        localIp = ip.getHostAddress();
                    }
                }
            }
        }

        return StringUtils.isBlank(outerIp) ? localIp : outerIp;
    }

}
