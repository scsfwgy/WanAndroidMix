package wgyscsf.quicklib.uiutils.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import wgyscsf.quicklib.R;

/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 创建日期 ：2017/12/8 11:57
 * 描 述 ：
 * ============================================================
 **/
public class GlideUtil {
    private static GlideUtil mInstance;

    private GlideUtil() {
    }

    public static GlideUtil getInstance() {
        if (mInstance == null) {
            synchronized (GlideUtil.class) {
                if (mInstance == null) {
                    mInstance = new GlideUtil();
                }
            }
        }
        return mInstance;
    }

    /**
     * 默认常量
     */
    static class Contants {
        public static final int BLUR_VALUE = 20; //模糊
        public static final int CORNER_RADIUS = 10; //圆角
        public static final int CORNER_RADIUS_MARGIN = 0; //圆角的margin
        public static final float THUMB_SIZE = 0.1f; //0-1之间  10%原图的大小
    }

    /**
     * 默认参数设置,所有默认参数在这里设置
     */

    private DrawableRequestBuilder getDefGlide(Context context, Object imgUrl) {
        return Glide.with(context)
                .load(imgUrl)
                .error(R.mipmap.error).placeholder(R.mipmap.loading)
                .crossFade()
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .thumbnail(Contants.THUMB_SIZE);
    }

    /**
     * 常规加载图片
     *
     * @param context
     * @param imageView 图片容器
     * @param imgUrl    图片地址
     */
    public void loadImage(Context context, ImageView imageView,
                          Object imgUrl) {
        loadImage(context, imageView, imgUrl, true);
    }

    /**
     * 常规加载图片
     *
     * @param context
     * @param imageView 图片容器
     * @param imgUrl    图片地址
     * @param isFade    是否需要动画
     */
    public void loadImage(Context context, ImageView imageView,
                          Object imgUrl, boolean isFade) {
        if (isFade) {
            getDefGlide(context, imgUrl).into(imageView);
        } else {
            getDefGlide(context, imgUrl).dontAnimate().into(imageView);
        }
    }

    /**
     * 加载图片并设置为指定大小
     *
     * @param context
     * @param imageView
     * @param imgUrl
     * @param withSize
     * @param heightSize
     */
    public void loadOverrideImage(Context context, ImageView imageView,
                                  Object imgUrl, int withSize, int heightSize) {
        getDefGlide(context, imgUrl)
                .override(withSize, heightSize)
                .into(imageView);
    }

    /**
     * 加载图片并对其进行模糊处理
     *
     * @param context
     * @param imageView
     * @param imgUrl
     */
    public void loadBlurImage(Context context, ImageView imageView, Object imgUrl) {
        loadBlurImage(context, imageView, imgUrl, Contants.BLUR_VALUE);
    }

    /**
     * 加载图片并对其进行模糊处理
     *
     * @param context
     * @param imageView
     * @param imgUrl
     * @param radius    模糊程度
     */
    public void loadBlurImage(Context context, ImageView imageView, Object imgUrl, int radius) {
        getDefGlide(context, imgUrl)
                .bitmapTransform(new BlurTransformation(context, radius))
                .into(imageView);
    }

    /**
     * 加载圆图
     *
     * @param context
     * @param imageView
     * @param imgUrl
     */
    public void loadCircleImage(Context context, ImageView imageView, Object imgUrl) {
        getDefGlide(context, imgUrl).bitmapTransform(new CropCircleTransformation(context))
                .into(imageView);
    }

    /**
     * 加载模糊的圆角的图片
     *
     * @param context
     * @param imageView
     * @param imgUrl
     */
    public void loadBlurCircleImage(Context context, ImageView imageView, Object imgUrl) {
        loadBlurCircleImage(context, imageView, imgUrl, Contants.BLUR_VALUE);
    }

    /**
     * 加载模糊的圆角的图片
     *
     * @param context
     * @param imageView
     * @param imgUrl
     * @param radius
     */
    public void loadBlurCircleImage(Context context, ImageView imageView, Object imgUrl, int radius) {
        getDefGlide(context, imgUrl)
                .bitmapTransform(
                        new BlurTransformation(context, radius),
                        new CropCircleTransformation(context))
                .into(imageView);
    }

    /**
     * 加载圆角图片
     *
     * @param context
     * @param imageView
     * @param imgUrl
     */
    public void loadCornerImage(Context context, ImageView imageView, Object imgUrl) {
        loadCornerImage(context, imageView, imgUrl, Contants.CORNER_RADIUS);
    }

    /**
     * 加载圆角图片
     *
     * @param context
     * @param imageView
     * @param imgUrl
     * @param radius    圆角
     */
    public void loadCornerImage(Context context, ImageView imageView, Object imgUrl, int radius) {
        getDefGlide(context, imgUrl)
                .bitmapTransform(
                        new RoundedCornersTransformation(
                                context, radius, Contants.CORNER_RADIUS_MARGIN))
                .into(imageView);
    }

    /**
     * 加载模糊的圆角图片
     *
     * @param context
     * @param imageView
     * @param imgUrl
     */
    public void loadBlurCornerImage(Context context, ImageView imageView, Object imgUrl) {
        loadBlurImage(context, imageView, imgUrl, Contants.CORNER_RADIUS);
    }

    /**
     * 加载模糊的圆角图片
     *
     * @param context
     * @param imageView
     * @param imgUrl
     * @param radius    圆角
     */
    public void loadBlurCornerImage(Context context, ImageView imageView, Object imgUrl, int radius) {
        getDefGlide(context, imgUrl)
                .bitmapTransform(
                        new BlurTransformation(context, Contants.BLUR_VALUE),
                        new RoundedCornersTransformation(
                                context, radius, Contants.CORNER_RADIUS))
                .into(imageView);
    }

    /**
     * 加载gif
     *
     * @param context
     * @param imageView
     * @param imgUrl
     * @param sizeMultiplier 缩放大小
     */
    public void loadGifImage(Context context, ImageView imageView, Object imgUrl, float sizeMultiplier) {
        if (sizeMultiplier < 0) {
            sizeMultiplier = 0.1f;
        }
        if (sizeMultiplier > 1) {
            sizeMultiplier = 1;
        }
        getDefGlide(context, imgUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE) //缓存策略
                .thumbnail(sizeMultiplier)
                .into(imageView);
    }

    /**
     * 加载gif
     *
     * @param context
     * @param imageView
     * @param imgUrl
     */
    public void loadGifImage(Context context, ImageView imageView, Object imgUrl) {
        loadGifImage(context, imageView, imgUrl, 0.1f);
    }


    /**
     * 恢复请求，一般在停止滚动的时候
     *
     * @param context
     */
    public void resumeRequests(Context context) {
        Glide.with(context).resumeRequests();
    }

    /**
     * 暂停请求 正在滚动的时候
     *
     * @param context
     */
    public void pauseRequests(Context context) {
        Glide.with(context).pauseRequests();
    }

    /**
     * 清除磁盘缓存
     *
     * @param context
     */
    public void clearDiskCache(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(context).clearDiskCache();//清理磁盘缓存 需要在子线程中执行
            }
        }).start();
    }

    /**
     * 清除内存缓存
     *
     * @param context
     */
    public void clearMemory(Context context) {
        Glide.get(context).clearMemory();//清理内存缓存  可以在UI主线程中进行
    }
}
