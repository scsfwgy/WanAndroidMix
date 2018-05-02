package com.gy.wanandroidmix.data.model;

import java.util.List;

/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 更新时间 ：2018/05/02 15:18
 * 描 述 ：
 * ============================================================
 */
public class ApiArticle extends ApiBase {

    /**
     * apkLink :
     * author : xiangcman
     * chapterId : 314
     * chapterName : RV列表动效
     * collect : false
     * courseId : 13
     * desc : 快速利用RecyclerView的LayoutManager搭建流式布局
     * envelopePic : http://www.wanandroid.com/blogimgs/36badc79-fb1e-460e-8368-6898c16ba723.png
     * fresh : false
     * id : 2829
     * link : http://www.wanandroid.com/blog/show/2112
     * niceDate : 2018-04-18
     * origin :
     * projectLink : https://github.com/xiangcman/LayoutManager-FlowLayout
     * publishTime : 1524051620000
     * superChapterId : 294
     * superChapterName : 开源项目主Tab
     * tags : [{"name":"项目","url":"/project/list/1?cid=314"}]
     * title : 快速利用RecyclerView的LayoutManager搭建流式布局
     * type : 0
     * visible : 1
     * zan : 0
     */

    public String apkLink;
    public String author;
    public int chapterId;
    public String chapterName;
    public boolean collect;
    public int courseId;
    public String desc;
    public String envelopePic;
    public boolean fresh;
    public int id;
    public String link;
    public String niceDate;
    public String origin;
    public String projectLink;
    public long publishTime;
    public int superChapterId;
    public String superChapterName;
    public String title;
    public int type;
    public int visible;
    public int zan;
    public List<TagsBean> tags;

    public static class TagsBean {
        /**
         * name : 项目
         * url : /project/list/1?cid=314
         */

        public String name;
        public String url;

        @Override
        public String toString() {
            return "TagsBean{" +
                    "name='" + name + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ApiArticle{" +
                "apkLink='" + apkLink + '\'' +
                ", author='" + author + '\'' +
                ", chapterId=" + chapterId +
                ", chapterName='" + chapterName + '\'' +
                ", collect=" + collect +
                ", courseId=" + courseId +
                ", desc='" + desc + '\'' +
                ", envelopePic='" + envelopePic + '\'' +
                ", fresh=" + fresh +
                ", id=" + id +
                ", link='" + link + '\'' +
                ", niceDate='" + niceDate + '\'' +
                ", origin='" + origin + '\'' +
                ", projectLink='" + projectLink + '\'' +
                ", publishTime=" + publishTime +
                ", superChapterId=" + superChapterId +
                ", superChapterName='" + superChapterName + '\'' +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", visible=" + visible +
                ", zan=" + zan +
                ", tags=" + tags +
                '}';
    }
}
