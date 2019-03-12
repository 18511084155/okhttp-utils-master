package com.woodys.okserver.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;

public class ExternalStorageUtils {

    private final static String TAG = ExternalStorageUtils.class.getSimpleName();
    public static final String LOGS_TARGET_FOLDER =  "Logs" ; //日志文件夹
    public static final String DOWNLOADS_TARGET_FOLDER =  "Downloads" ; //下载文件夹


    /**
     * 获取app的根目录
     *
     * @return 文件缓存根路径
     */
    public static String getDiskCacheRootDir(Context context) throws IllegalArgumentException{
        File diskRootFile;
        if (existsSdcard()) {
            diskRootFile = context.getExternalCacheDir();
        } else {
            diskRootFile = context.getCacheDir();
        }
        String cachePath;
        if (diskRootFile != null) {
            cachePath = diskRootFile.getPath();
        } else {
            throw new IllegalArgumentException("disk is invalid");
        }
        return cachePath;
    }

    /**
     * 判断外置sdcard是否可以正常使用
     *
     * @return
     */
    public static Boolean existsSdcard() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable();
    }

    /**
     * 获取相关功能业务目录
     *
     * @return 文件缓存路径
     */
    public static String getDiskCacheDir(Context context,String dirName) {
        String dir = String.format("%s/%s/", getDiskCacheRootDir(context), dirName);
        File file = new File(dir);
        if (!file.exists()) {
            boolean isSuccess = file.mkdirs();
            if (isSuccess) {
                Log.d(TAG, "dir mkdirs success");
            }
        }
        return file.getPath();
    }

    /**
     * 获取log日志根目录
     * @return
     */
    public  static String getLogDir(Context context){
        return getDiskCacheDir(context,LOGS_TARGET_FOLDER);
    }

    /**
     * 根据logName 获取log文件全路径
     * @param logName
     * @return
     */
    public  static String getLogFilePath(Context context,String logName){
        return  getLogDir(context) + logName;
    }

    /**
     * 获取下载文件根目录
     * @return
     */
    public static String getDownloadDir(Context context){
        return getDiskCacheDir(context,DOWNLOADS_TARGET_FOLDER);
    }

    /**
     * 根据logName 获取下载文件全路径
     * @param fileName
     * @return
     */
    public  static String getDownloadFilePath(Context context,String fileName){
        return  getLogDir(context) + fileName;
    }
}
