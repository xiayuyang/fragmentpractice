package com.example.administrator.fragmentpractice.View;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.fragmentpractice.R;
import com.example.administrator.fragmentpractice.Util.PhotoUtils;

import java.io.File;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Administrator on 2018/6/17.
 */

public class WoFragment extends BaseFragment implements View.OnClickListener {

    /**
     * @author Donkor
     * 关于我：
     * CSDN博客：http://blog.csdn.net/donkor_
     * Android开发交流QQ群：537891203
     * 邮箱：donkor@yeah.net
     */
        private ImageView photo;
        private static final int CODE_GALLERY_REQUEST = 0xa0;
        private static final int CODE_CAMERA_REQUEST = 0xa1;
        private static final int CODE_RESULT_REQUEST = 0xa2;
        private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
        private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
        private Uri imageUri;
        private Uri cropImageUri;

        private PopupWindow mPopWindow;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wo,container,false);
        photo = (ImageView) view.findViewById(R.id.photo);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
            }
        });
        return view;
    }


            // Button btnTakePhoto = (Button) findViewById(R.id.take_photo);
            // Button btnTakeGallery = (Button) findViewById(R.id.take_gallery);

            //btnTakePhoto.setOnClickListener(this);
            //btnTakeGallery.setOnClickListener(this);

        private void showPopupWindow() {
            //设置contentView
            View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popuwindow, null);
            mPopWindow = new PopupWindow(contentView,
                    WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
            mPopWindow.setContentView(contentView);
            //设置各个控件的点击响应
            TextView tv1 = (TextView)contentView.findViewById(R.id.pop_computer);
            TextView tv2 = (TextView)contentView.findViewById(R.id.pop_financial);
            TextView tv3 = (TextView)contentView.findViewById(R.id.pop_manage);
            tv1.setOnClickListener(this);
            tv2.setOnClickListener(this);
            tv3.setOnClickListener(this);
            //显示PopupWindow
            View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_wo, null);
            mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);

        }




        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.pop_computer:
                    requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, new RequestPermissionCallBack() {
                        @Override
                        public void granted() {
                            if (hasSdcard()) {
                                imageUri = Uri.fromFile(fileUri);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                                    //通过FileProvider创建一个content类型的Uri
                                    imageUri = FileProvider.getUriForFile(getActivity(), "com.example.administrator.fragmentpractice.fileprovider", fileUri);
                                PhotoUtils.takePicture(WoFragment.this, imageUri, CODE_CAMERA_REQUEST);
                            } else {
                                Toast.makeText(getActivity(), "设备没有SD卡！", Toast.LENGTH_SHORT).show();
                                Log.e("asd", "设备没有SD卡");
                            }
                        }

                        @Override
                        public void denied() {
                            Toast.makeText(getActivity(), "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
                        }
                    });
                    mPopWindow.dismiss();
                    break;
                case R.id.pop_financial:
                    requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, new RequestPermissionCallBack() {
                        @Override
                        public void granted() {
                            PhotoUtils.openPic(WoFragment.this, CODE_GALLERY_REQUEST);
                        }

                        @Override
                        public void denied() {
                            Toast.makeText(getActivity(), "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
                        }
                    });
                    mPopWindow.dismiss();
                    break;
                case R.id.pop_manage:
                    mPopWindow.dismiss();
                    break;
            }


        }


        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            int output_X = 480, output_Y = 480;
            if (resultCode == RESULT_OK) {
                switch (requestCode) {
                    case CODE_CAMERA_REQUEST://拍照完成回调
                        cropImageUri = Uri.fromFile(fileCropUri);
                        PhotoUtils.cropImageUri(this, imageUri, cropImageUri, 1, 1, output_X, output_Y, CODE_RESULT_REQUEST);
                        break;
                    case CODE_GALLERY_REQUEST://访问相册完成回调
                        if (hasSdcard()) {
                            cropImageUri = Uri.fromFile(fileCropUri);
                            Uri newUri = Uri.parse(PhotoUtils.getPath(getActivity(), data.getData()));
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                                newUri = FileProvider.getUriForFile(getActivity(), "com.example.administrator.fragmentpractice.fileprovider", new File(newUri.getPath()));
                            PhotoUtils.cropImageUri(this, newUri, cropImageUri, 1, 1, output_X, output_Y, CODE_RESULT_REQUEST);
                        } else {
                            Toast.makeText(getActivity(), "设备没有SD卡!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case CODE_RESULT_REQUEST:
                        Bitmap bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, getActivity());
                        if (bitmap != null) {
                            showImages(bitmap);
                        }
                        break;
                }
            }
        }


        private void showImages(Bitmap bitmap) {
            photo.setImageBitmap(bitmap);
        }

        /**
         * 检查设备是否存在SDCard的工具方法
         */
        public static boolean hasSdcard() {
            String state = Environment.getExternalStorageState();
            return state.equals(Environment.MEDIA_MOUNTED);
        }
    }


