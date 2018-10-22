package org.demobox.general.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Log;

/**
 * 图片压缩工具类
 */
public class ImageCompress {
//    public static final String CONTENT = "content";
//    public static final String FILE = "file";
//
//    /**
//     * 图片压缩参数
//     */
//    public static class CompressOptions {
//        public static final int DEFAULT_WIDTH = 400;
//        public static final int DEFAULT_HEIGHT = 800;
//
//        public int maxWidth = DEFAULT_WIDTH;
//        public int maxHeight = DEFAULT_HEIGHT;
//        /**
//         * 压缩后图片保存的文件
//         */
//        public File destFile;
//        /**
//         * 图片压缩格式,默认为jpg格式
//         */
//        public CompressFormat imgFormat = CompressFormat.JPEG;
//
//        /**
//         * 图片压缩比例 默认为30
//         */
//        public int quality = 30;
//
//        public Uri uri;
//    }
//
//    
//    
//    
//    public Bitmap compressFromUri(Context context, CompressOptions compressOptions) {
//
//        // uri指向的文件路径
//        String filePath = getFilePath(context, compressOptions.uri);
//        
//        if (null == filePath) {
//            return null;
//        }
//
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//
//        Bitmap temp = BitmapFactory.decodeFile(filePath, options);
//
//        int actualWidth = options.outWidth;
//        int actualHeight = options.outHeight;
//
//        int desiredWidth = getResizedDimension(compressOptions.maxWidth,
//                compressOptions.maxHeight, actualWidth, actualHeight);
//        int desiredHeight = getResizedDimension(compressOptions.maxHeight,
//                compressOptions.maxWidth, actualHeight, actualWidth);
//
//        options.inJustDecodeBounds = false;
//        options.inSampleSize = findBestSampleSize(actualWidth, actualHeight,
//                desiredWidth, desiredHeight);
//
//        Bitmap bitmap = null;
//
//        Bitmap destBitmap = BitmapFactory.decodeFile(filePath, options);
//
//        // If necessary, scale down to the maximal acceptable size.
//        if (destBitmap.getWidth() > desiredWidth
//                || destBitmap.getHeight() > desiredHeight) {
//            bitmap = Bitmap.createScaledBitmap(destBitmap, desiredWidth,
//                    desiredHeight, true);
//            destBitmap.recycle();
//        } else {
//            bitmap = destBitmap;
//        }
//
//        // compress file if need
//        if (null != compressOptions.destFile) {
//            compressFile(compressOptions, bitmap);
//        }
//
//        return bitmap;
//    }
//
//    /**
//     * compress file from bitmap with compressOptions
//     * 
//     * @param compressOptions
//     * @param bitmap
//     */
//    private void compressFile(CompressOptions compressOptions, Bitmap bitmap) {
//        OutputStream stream = null;
//        try {
//            stream = new FileOutputStream(compressOptions.destFile);
//        } catch (FileNotFoundException e) {
//            Log.e("ImageCompress", e.getMessage());
//        }
//
//        bitmap.compress(compressOptions.imgFormat, compressOptions.quality, stream);
//    }
//
//    private static int findBestSampleSize(int actualWidth, int actualHeight, int desiredWidth, int desiredHeight) {
//        double wr = (double) actualWidth / desiredWidth;
//        double hr = (double) actualHeight / desiredHeight;
//        double ratio = Math.min(wr, hr);
//        float n = 1.0f;
//        while ((n * 2) <= ratio) {
//            n *= 2;
//        }
//
//        return (int) n;
//    }
//
//    private static int getResizedDimension(int maxPrimary, int maxSecondary, int actualPrimary, int actualSecondary) {
//        // If no dominant value at all, just return the actual.
//        if (maxPrimary == 0 && maxSecondary == 0) {
//            return actualPrimary;
//        }
//
//        // If primary is unspecified, scale primary to match secondary's scaling
//        // ratio.
//        if (maxPrimary == 0) {
//            double ratio = (double) maxSecondary / (double) actualSecondary;
//            return (int) (actualPrimary * ratio);
//        }
//
//        if (maxSecondary == 0) {
//            return maxPrimary;
//        }
//
//        double ratio = (double) actualSecondary / (double) actualPrimary;
//        int resized = maxPrimary;
//        if (resized * ratio > maxSecondary) {
//            resized = (int) (maxSecondary / ratio);
//        }
//        return resized;
//    }
//
//    /**
//     * 获取文件的路径
//     * 
//     * @param scheme
//     * @return
//     */
//    private String getFilePath(Context context, Uri uri) {
//
//        String filePath = null;
//
//        if (CONTENT.equalsIgnoreCase(uri.getScheme())) {
//
//            Cursor cursor = context.getContentResolver().query(uri,
//                    new String[] { Images.Media.DATA }, null, null, null);
//
//            if (null == cursor) {
//                return null;
//            }
//
//            try {
//                if (cursor.moveToNext()) {
//                    filePath = cursor.getString(cursor
//                            .getColumnIndex(Images.Media.DATA));
//                }
//            } finally {
//                cursor.close();
//            }
//        }
//
//        // 从文件中选择
//        if (FILE.equalsIgnoreCase(uri.getScheme())) {
//            filePath = uri.getPath();
//        }
//
//        return filePath;
//    }
    
    
    /**
     * 读取图片的旋转的角度
     * @param path 图片绝对路径
     * @return 图片的旋转角度
     */
    public static int getBitmapDegree(String path) {
    	int degree = 0;//被旋转的角度
    	try {
    		// 从指定路径下读取图片，并获取其EXIF信息
    		ExifInterface exifInterface = new ExifInterface(path);
    		// 获取图片的旋转信息
    		int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
    		switch (orientation) {
    		case ExifInterface.ORIENTATION_ROTATE_90:
    			degree = 90;
    			break;
    		case ExifInterface.ORIENTATION_ROTATE_180:
    			degree = 180;
    			break;
    		case ExifInterface.ORIENTATION_ROTATE_270:
    			degree = 270;
    			break;
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	Log.i("TAG","degree="+degree);
    	return degree;
    }
    
    /**
     * rotate the bitmap
     * @param bitmap
     * @param degress
     * @return
     */
    public static Bitmap rotateBitmap(Bitmap bitmap, int degress) {
        if (bitmap != null) {
            Matrix m = new Matrix();
            m.postRotate(degress);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
            return bitmap;
        }
        return bitmap;
    }
    
    /**
     * calculate the bitmap sampleSize
     * @param path
     * @return
     */
    public final static int caculateInSampleSize(Options options, int rqsW, int rqsH) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (rqsW == 0 || rqsH == 0)
        	return 1;
        
        if (height > rqsH || width > rqsW) {
            final int heightRatio = Math.round((float) height/ (float) rqsH);
            final int widthRatio = Math.round((float) width / (float) rqsW);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        
        return inSampleSize;
    }
    
    /**
     * 压缩指定路径的图片，并得到图片对象
     * @param context
     * @param path bitmap source path
     * @return Bitmap {@link Bitmap}
     */
    public final static Bitmap compressBitmap(String path, int rqsW, int rqsH) {
        final Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inSampleSize = caculateInSampleSize(options, rqsW, rqsH);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }
     
    /**
     * 压缩指定路径图片，并将其保存在缓存目录中，通过isDelSrc判定是否删除源文件，并获取到缓存后的图片路径
     * @param context
     * @param srcPath
     * @param rqsW
     * @param rqsH
     * @param isDelSrc
     * @return
     */
    public final static String compressBitmap(Context context, String srcPath, int rqsW, int rqsH, boolean isDelSrc) {
        Bitmap bitmap = compressBitmap(srcPath, rqsW, rqsH);
        File srcFile = new File(srcPath);
        String desPath = getImageCacheDir(context) + srcFile.getName();
        int degree = getBitmapDegree(srcPath);
        try {
            if (degree != 0)
            	bitmap = rotateBitmap(bitmap, degree);
            
            File file = new File(desPath);
            FileOutputStream  fos = new FileOutputStream(file);
            bitmap.compress(CompressFormat.PNG, 70, fos);
            fos.close();
            
            if (isDelSrc)
            	srcFile.deleteOnExit();
        } catch (Exception e) {
            Log.e("BitmapHelper-->compressBitmap", e.getMessage()+"");
        }
        return desPath;
    }
     
    /**
     * 压缩某个输入流中的图片，可以解决网络输入流压缩问题，并得到图片对象
     * @param context
     * @param path bitmap source path
     * @return Bitmap {@link Bitmap}
     */
    public final static Bitmap compressBitmap(InputStream is, int reqsW, int reqsH) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ReadableByteChannel channel = Channels.newChannel(is);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (channel.read(buffer) != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) baos.write(buffer.get());
                buffer.clear();
            }
            byte[] bts = baos.toByteArray();
            Bitmap bitmap = compressBitmap(bts, reqsW, reqsH);
            is.close();
            channel.close();
            baos.close();
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     
    /**
     * 压缩指定byte[]图片，并得到压缩后的图像
     * @param bts
     * @param reqsW
     * @param reqsH
     * @return
     */
    public final static Bitmap compressBitmap(byte[] bts, int reqsW, int reqsH) {
        final Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bts, 0, bts.length, options);
        options.inSampleSize = caculateInSampleSize(options, reqsW, reqsH);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(bts, 0, bts.length, options);
    }
     
    /**
     * 压缩已存在的图片对象，并返回压缩后的图片
     * @param bitmap
     * @param reqsW
     * @param reqsH
     * @return
     */
    public final static Bitmap compressBitmap(Bitmap bitmap, int reqsW, int reqsH) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.PNG, 100, baos);
            byte[] bts = baos.toByteArray();
            Bitmap res = compressBitmap(bts, reqsW, reqsH);
            baos.close();
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            return bitmap;
        }
    }
     
    /**
     * 压缩资源图片，并返回图片对象
     * @param res {@link Resources}
     * @param resID
     * @param reqsW
     * @param reqsH
     * @return
     */
    public final static Bitmap compressBitmap(Resources res, int resID, int reqsW, int reqsH) {
        final Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resID, options);
        options.inSampleSize = caculateInSampleSize(options, reqsW, reqsH);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resID, options);
    }
     
     
     
    /**
     * 基于质量的压缩算法， 此方法未 解决压缩后图像失真问题
     * <br> 可先调用比例压缩适当压缩图片后，再调用此方法可解决上述问题
     * @param bts
     * @param maxBytes 压缩后的图像最大大小 单位为byte
     * @return
     */
    public final static Bitmap compressBitmap(Bitmap bitmap, long maxBytes) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.PNG, 100, baos);
            int options = 90;
            while (baos.toByteArray().length > maxBytes) {
                baos.reset();
                bitmap.compress(CompressFormat.PNG, options, baos);
                options -= 10;
            }
            byte[] bts = baos.toByteArray();
            Bitmap bmp = BitmapFactory.decodeByteArray(bts, 0, bts.length);
            baos.close();
            return bmp;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
     
    /**
     * 得到指定路径图片的options
     * @param srcPath
     * @return Options {@link Options}
     */
    public final static Options getBitmapOptions(String srcPath) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(srcPath, options);
        return options;
    }
     
    /**
     * 获取图片缓存路径
     * @param context
     * @return
     */
    private static String getImageCacheDir(Context context) {
//        String dir = FileHelper.getCacheDir(context) + "Image" + File.separator;
        String dir = context.getCacheDir() + "Image" + File.separator;
        File file = new File(dir);
        if (!file.exists()) file.mkdirs();
        return dir;
    }
    
    
}
