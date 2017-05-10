package onlyloveyd.com.gankioclient.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

import onlyloveyd.com.gankioclient.R;

/**
 * Created by yidong on 2015/4/1.
 */
public class PathTextView extends View {
    private static final float BASE_SQUARE_UNIT = 72f;
    private final int DEFAULT_COLOR = Color.WHITE;
    private final float DEFAULT_WIDTH = 8.0f;

    private int mPaintColor;
    private float mStrokeWidth;

    private String mText = "FUCK";
    private ArrayList<float[]> mDatas;
    private ArrayList<Path> mPaths = new ArrayList<Path>();
    private Paint mPaint = new Paint();
    private ObjectAnimator mSvgAnimator;
    private final Object mSvgLock = new Object();
    private float mPhase;
    private TYPE mType = TYPE.SINGLE;
    private float mScaleFactor = 1.0f;

    public enum TYPE {
        SINGLE, MULTIPY
    }


    public PathTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PathTextView);
        //mPaintColor = typedArray.getColor(R.styleable.PathTextView_ptv_color, DEFAULT_COLOR);
        //mStrokeWidth = typedArray.getFloat(R.styleable.PathTextView_ptv_color, DEFALUT_WIDTH);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setPathEffect(new CornerPathEffect(4));
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        mPaint.setColor(DEFAULT_COLOR);
        mPaint.setStrokeWidth(DEFAULT_WIDTH);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));

    }

    public void init(String text) {
        if (text == null || text.length() == 0)
            return;

        requestLayout();
        invalidate();

        mText = text;
        mDatas = MatchPath.getPath(mText);
        mSvgAnimator = ObjectAnimator.ofFloat(this, "phase", 0.0f, 1.0f).setDuration(3000);
        mSvgAnimator.start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPaths == null)
            return;
        synchronized (mSvgLock) {
            for (int i = 0; i < mPaths.size(); i++)
                canvas.drawPath(mPaths.get(i), mPaint);
        }
    }


    private void updatePathsPhaseLocked() {
        mPaths.clear();
        float singlefactor = mPhase * mDatas.size();
        for (int i = 0; i < mDatas.size(); i++) {
            Path path = new Path();
            path.moveTo(mDatas.get(i)[0], mDatas.get(i)[1]);
            path.lineTo(mDatas.get(i)[2], mDatas.get(i)[3]);

            if (mType == TYPE.MULTIPY) {
                PathMeasure measure = new PathMeasure(path, false);
                Path dst = new Path();
                measure.getSegment(0.0f, mPhase * measure.getLength(), dst, true);
                mPaths.add(dst);
            } else {
                //Fuck! can't compare float and int
                //Sometimes, at the end of animation , the value is  -9.5176697E-4 or other tiny value.
                if (singlefactor - (i + 1) >= -0.01)
                    mPaths.add(path);
                else if (i - Math.floor(singlefactor) < 0.0001) {
                    Path dst = new Path();
                    PathMeasure measure = new PathMeasure(path, false);
                    measure.getSegment(0.0f, (singlefactor % 1) * measure.getLength(), dst, true);
                    mPaths.add(dst);
                }
            }

        }
    }

    public float getPhase() {
        return mPhase;
    }

    public void setPhase(float phase) {
        mPhase = phase;
        synchronized (mSvgLock) {
            updatePathsPhaseLocked();
        }
        invalidate();
    }

    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the text
            result = (int) (mText.length() * BASE_SQUARE_UNIT * mScaleFactor + getPaddingLeft()
                    + getPaddingRight());
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by measureSpec
                result = Math.min(result, specSize);
            }
        }

        return result;
    }

    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the text (beware: ascent is a negative number)
            result = (int) (BASE_SQUARE_UNIT * mScaleFactor) + getPaddingTop()
                    + getPaddingBottom();
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by measureSpec
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

}
