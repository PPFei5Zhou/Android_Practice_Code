package com.zhou.myprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by zhou on 18-4-30.
 */

public class MyProvider extends ContentProvider {

    public static final int TABLE1_DIR = 0;

    public static final int TABLE1_ITEM = 1;

    public static final int TABLE2_DIR = 2;

    public static final int TABLE2_ITEM = 3;

    private static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.app.provider", "table1", TABLE1_DIR);
        uriMatcher.addURI("com.example.app.provider", "table1/#", TABLE1_ITEM);
        uriMatcher.addURI("com.example.app.provider", "table2", TABLE2_DIR);
        uriMatcher.addURI("com.example.app.provider", "table2/#", TABLE2_ITEM);
    }

    @Nullable
    @Override
    public boolean onCreate() {
        /**
         * 初始化内容提供器时调用
         * 一般在此完成对数据库的创建和升级
         */
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        /**
         * 从内容提供器中查询数据
         * @param uri: 确定查询哪张表
         * @param projection: 用于确定查询哪些列
         * @param selection: 用于约束查询哪些行
         * @param selectionArgs: 用于约束查询哪些行
         * @param sortOrder： 用于对结果进行排序
         * @return 查询结果存放在Cursor对象中返回
         */
        switch (uriMatcher.match(uri)) {
            case TABLE1_DIR:
                // 查询table1表中的所有数据
                break;
            case TABLE1_ITEM:
                // 查询table1表中的单条数据
                break;
            case TABLE2_DIR:
                // 查询table2表中的所有数据
                break;
            case TABLE2_ITEM:
                // 查询table2表中的单条数据
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        /**
         * 向内容提供器中添加一条数据
         * @param uri: 确定要添加到的表
         * @param values: 保存待添加的数据
         * @return 返回一个用于表示这条新纪录的URI
         */
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        /**
         * 从内容提供器中删除数据
         * @param uri: 确定删除哪一张表中的数据
         * @param selection: 约束删除哪些行
         * @param seletionArgs: 约束删除哪些行
         */
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        /**
         * 更新内容提供器中已有的数据
         * @param uri: 确定更新哪一张表中的数据
         * @param values: 保存新数据
         * @param selection: 约束更新哪些行
         * @param selectionArgs: 约束更新哪些行
         * @return 受影响的行数将作为返回值返回
         */
        return 0;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        /**
         * 根据传入的内容URI来返回相应的MIME类型
         */
        switch (uriMatcher.match(uri)) {
            case TABLE1_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.app.provider.table1";
            case TABLE1_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.app.provider.table1";
            case TABLE2_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.app.provider.table2";
            case TABLE2_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.app.provider.table2";
            default:
                break;
        }
        return null;
    }

}
