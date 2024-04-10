/*
 * Copyright (c) 2010-2011, The MiCode Open Source Community (www.micode.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.micode.notes.gtask.data;

import android.database.Cursor;

import org.json.JSONObject;

// 单个节点类，用于表示Google任务（GTask）中的单个便签项
public abstract class Node {
    // 同步操作类型常量
    public static final int SYNC_ACTION_NONE = 0; // 无操作
    public static final int SYNC_ACTION_ADD_REMOTE = 1; // 添加到远程
    public static final int SYNC_ACTION_ADD_LOCAL = 2; // 添加到本地
    public static final int SYNC_ACTION_DEL_REMOTE = 3; // 从远程删除
    public static final int SYNC_ACTION_DEL_LOCAL = 4; // 从本地删除
    public static final int SYNC_ACTION_UPDATE_REMOTE = 5; // 更新远程
    public static final int SYNC_ACTION_UPDATE_LOCAL = 6; // 更新本地
    public static final int SYNC_ACTION_UPDATE_CONFLICT = 7; // 更新冲突
    public static final int SYNC_ACTION_ERROR = 8; // 同步错误

    // 节点属性
    private String mGid; // 节点在Google任务中的唯一标识符
    private String mName; // 节点名称
    private long mLastModified; // 最后修改时间
    private boolean mDeleted; // 是否已删除

    // 构造函数
    public Node() {
        mGid = null;
        mName = "";
        mLastModified = 0;
        mDeleted = false;
    }

    // 获取创建操作的JSON对象
    public abstract JSONObject getCreateAction(int actionId);

    // 获取更新操作的JSON对象
    public abstract JSONObject getUpdateAction(int actionId);

    // 通过远程JSON对象设置内容
    public abstract void setContentByRemoteJSON(JSONObject js);

    // 通过本地JSON对象设置内容
    public abstract void setContentByLocalJSON(JSONObject js);

    // 从内容中获取本地JSON对象
    public abstract JSONObject getLocalJSONFromContent();

    // 获取同步操作类型
    public abstract int getSyncAction(Cursor c);

    // 设置节点的Gid
    public void setGid(String gid) {
        this.mGid = gid;
    }

    // 设置节点的名称
    public void setName(String name) {
        this.mName = name;
    }

    // 设置节点的最后修改时间
    public void setLastModified(long lastModified) {
        this.mLastModified = lastModified;
    }

    // 设置节点的删除状态
    public void setDeleted(boolean deleted) {
        this.mDeleted = deleted;
    }

    // 获取节点的Gid
    public String getGid() {
        return this.mGid;
    }

    // 获取节点的名称
    public String getName() {
        return this.mName;
    }

    // 获取节点的最后修改时间
    public long getLastModified() {
        return this.mLastModified;
    }

    // 获取节点的删除状态
    public boolean getDeleted() {
        return this.mDeleted;
    }
}
