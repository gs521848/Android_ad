package com.unity3d.start;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class NetUtils
{
    public static boolean isNetworkAvailable(Context paramContext)
    {
        boolean bool = false;
        ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
        if (localConnectivityManager != null)
        {
            NetworkInfo[] arrayOfNetworkInfo = localConnectivityManager.getAllNetworkInfo();
            if (arrayOfNetworkInfo != null) {
                for (int i = 0; i < arrayOfNetworkInfo.length; i++) {
                    if (arrayOfNetworkInfo[i].getState() == State.CONNECTED)
                    {
                        bool = true;
                        break;
                    }
                }
            }
        }
        return bool;
    }

    public static boolean isGpsEnabled(Context paramContext)
    {
        LocationManager localLocationManager = (LocationManager)paramContext.getSystemService("location");
        return localLocationManager.isProviderEnabled("gps");
    }

    public static boolean is3G(Context paramContext)
    {
        ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
        NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();

        return (localNetworkInfo != null) && (localNetworkInfo.getType() == 0);
    }

    public static boolean is4G(Context paramContext)
    {
        ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
        NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
        if ((localNetworkInfo != null) && (localNetworkInfo.isConnectedOrConnecting()) &&
                (localNetworkInfo.getType() == 13)) {
            return true;
        }
        return false;
    }

    public static boolean isWiFiOpen(Context paramContext)
    {
        ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");

        State localState = localConnectivityManager.getNetworkInfo(1).getState();
        return (localState == State.CONNECTED) || (localState == State.CONNECTING);
    }

    public static boolean isWifi(Context paramContext)
    {
        ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
        NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();

        return (localNetworkInfo != null) && (localNetworkInfo.getType() == 1);
    }

    public static boolean isIP(String pStr)
    {
        Pattern localPattern = Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
        Matcher localMatcher = localPattern.matcher(pStr);
        return localMatcher.matches();
    }

    public static int ipToInt(String pStr)
    {
        String[] arrayOfString = pStr.split("\\.");
        int i = 0;
        for (int j = 0; j < arrayOfString.length; j++)
        {
            int k = 3 - j;

            i = (int)(i + Integer.parseInt(arrayOfString[j]) % 256 * Math.pow(256.0D, k));
        }
        return i;
    }

    public static String getIP()
    {
        try
        {
            for (Enumeration localEnumeration1 = NetworkInterface.getNetworkInterfaces(); localEnumeration1.hasMoreElements();)
            {
                NetworkInterface localNetworkInterface = (NetworkInterface)localEnumeration1.nextElement();
                for (Enumeration localEnumeration2 = localNetworkInterface.getInetAddresses(); localEnumeration2.hasMoreElements();)
                {
                    InetAddress localInetAddress = (InetAddress)localEnumeration2.nextElement();
                    if ((!localInetAddress.isLoopbackAddress()) && ((localInetAddress instanceof Inet4Address))) {
                        return localInetAddress.getHostAddress();
                    }
                }
            }
        }
        catch (SocketException localSocketException)
        {
            Enumeration localEnumeration1;
            Enumeration localEnumeration2;
            localSocketException.printStackTrace();
            return "";
        }
        return "";
    }

    public static enum NetState
    {
        NET_NO,  NET_2G,  NET_3G,  NET_4G,  NET_WIFI,  NET_UNKNOWN;

        private NetState() {}
    }

    public NetState isConnected(Context paramContext)
    {
        NetState localNetState = NetState.NET_NO;

        ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
        NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
        if ((localNetworkInfo != null) && (localNetworkInfo.isConnectedOrConnecting())) {
            switch (localNetworkInfo.getType())
            {
                case 1:
                    localNetState = NetState.NET_WIFI;
                    break;
                case 0:
                    switch (localNetworkInfo.getSubtype())
                    {
                        case 1:
                        case 2:
                        case 4:
                        case 7:
                        case 11:
                            localNetState = NetState.NET_2G;
                            break;
                        case 3:
                        case 5:
                        case 6:
                        case 8:
                        case 9:
                        case 10:
                        case 12:
                        case 14:
                        case 15:
                            localNetState = NetState.NET_3G;
                            break;
                        case 13:
                            localNetState = NetState.NET_4G;
                            break;
                        default:
                            localNetState = NetState.NET_UNKNOWN;
                    }
                    break;
                default:
                    localNetState = NetState.NET_UNKNOWN;
            }
        }
        return localNetState;
    }

    public static Map<String, String> getUrlParams(String pStr)
    {
        HashMap localHashMap = null;
        try
        {
            String[] arrayOfString1 = pStr.split("\\?");
            if (arrayOfString1.length > 1)
            {
                localHashMap = new HashMap();
                String str1 = arrayOfString1[1];
                for (String str2 : str1.split("&"))
                {
                    String[] arrayOfString3 = str2.split("=");
                    String str3 = URLDecoder.decode(arrayOfString3[0], "UTF-8");
                    String str4 = "";
                    if (arrayOfString3.length > 1) {
                        str4 = URLDecoder.decode(arrayOfString3[1], "UTF-8");
                    }
                    localHashMap.put(str3, str4);
                }
            }
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException)
        {
            localUnsupportedEncodingException.printStackTrace();
        }
        return localHashMap;
    }

    public static boolean isUrl(String pStr)
    {
        try
        {
            URL localURL = new URL(pStr);
            return true;
        }
        catch (MalformedURLException localMalformedURLException)
        {
            localMalformedURLException.printStackTrace();
        }
        return false;
    }
}
