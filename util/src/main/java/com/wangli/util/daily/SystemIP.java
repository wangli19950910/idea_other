package com.wangli.util.daily;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author:wangli
 * @Description: 获取当前电脑的Ip
 * @Date: created in 2018/6/29
 */
public class SystemIP {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        String hostAddress = localHost.getHostAddress();
        String hostName = localHost.getHostName();
        String canonicalHostName = localHost.getCanonicalHostName();
        System.out.println("网络地址信息:"+hostAddress);
        System.out.println("当前电脑机器名称:"+hostName);
        System.out.println("当前电脑机器名称:"+canonicalHostName);
    }
}

