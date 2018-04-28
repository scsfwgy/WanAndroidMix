package wgyscsf.quicklib.uiutils;

import android.os.Bundle;

import com.blankj.utilcode.util.ToastUtils;
import com.github.chrisbanes.photoview.PhotoView;

import wgyscsf.quicklib.R;
import wgyscsf.quicklib.base.BaseActivity;
import wgyscsf.quicklib.uiutils.glide.GlideUtil;

public class ImageViewActivity extends BaseActivity {
    public final static String KEY_INTENT = "KEY_INTENT";
    Object imgUrl;
    PhotoView mAivIvImg;

    @Override
    protected void getBundleExtras(Bundle extras) {
        super.getBundleExtras(extras);
        imgUrl = extras.get(KEY_INTENT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        mAivIvImg = (PhotoView) findViewById(R.id.aiv_iv_img);

        try {
            loadImg();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadImg() {
        if (imgUrl == null) {
            ToastUtils.showShort("异常");
            finish();
        }
        GlideUtil.getInstance().loadImage(mContext, mAivIvImg, imgUrl);
    }
}
