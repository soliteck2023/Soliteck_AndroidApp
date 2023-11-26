package com.example.api_call;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

public class CheckView extends View {
    private static final long CHECK_ANIM_DURATION = 300;
    private static final boolean DEBUG = false;
    private static final int DEFAULT_STROKE_COLOR = -15029504;
    private static final float DEFAULT_STROKE_WIDTH = 8.0f;
    private static final long SCALE_ANIM_DELAY = 280;
    private static final long SCALE_ANIM_DURATION = 250;
    private static final float SCALE_MIN = 0.8f;
    private static final String TAG = CheckView.class.getSimpleName();
    private ValueAnimator mCheckAnimator;
    private PointF mCheckEnd;
    private Interpolator mCheckInterpolator;
    private PointF mCheckPivot;
    private PointF mCheckStart;
    private ValueAnimator mCircleAnimator;
    private RectF mCircleRect;
    private PointF mCircleStart;
    private RectF mDrawingRect;
    private float mMajorContourLength;
    private float mMinorContourLength;
    private Paint mPaint;
    private Path mPathCheck;
    private Path mPathCircle;
    private PathMeasure mPathMeasure;
    private float[] mPoint;
    private ValueAnimator mScaleAnimator;
    private float mStrokeWidth = DEFAULT_STROKE_WIDTH;
    private int mStrokeColor = DEFAULT_STROKE_COLOR;
    private boolean mChecked = false;
    private final ValueAnimator.AnimatorUpdateListener mCheckAnimatorListener = new ValueAnimator.AnimatorUpdateListener() { // from class: cdflynn.android.library.checkview.CheckView.1
        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator animation) {
            float fraction = animation.getAnimatedFraction();
            CheckView.this.setCheckPathPercentage(fraction);
            CheckView.this.invalidate();
        }
    };
    private final ValueAnimator.AnimatorUpdateListener mCircleAnimatorListener = new ValueAnimator.AnimatorUpdateListener() { // from class: cdflynn.android.library.checkview.CheckView.2
        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator animation) {
            float fraction = animation.getAnimatedFraction();
            CheckView.this.setCirclePathPercentage(fraction);
            CheckView.this.invalidate();
        }
    };



    private final ValueAnimator.AnimatorUpdateListener mScaleAnimatorListener = new ValueAnimator.AnimatorUpdateListener() { // from class: cdflynn.android.library.checkview.CheckView.3
        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator animation) {
            float value = ((Float) animation.getAnimatedValue()).floatValue();
            CheckView.this.setScaleX(value);
            CheckView.this.setScaleY(value);
            CheckView.this.invalidate();
        }
    };

    public CheckView(Context context) {
        super(context);
        init(context, null);
    }

    public CheckView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CheckView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public CheckView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }



    private void init(Context context, AttributeSet attrs) {
//        resolveAttributes(context, attrs);
        this.mPathCheck = new Path();
        this.mPathCircle = new Path();
        this.mDrawingRect = new RectF();
        this.mCircleRect = new RectF();
        this.mPathMeasure = new PathMeasure();
        this.mPoint = new float[2];
        this.mCheckStart = new PointF();
        this.mCheckPivot = new PointF();
        this.mCheckEnd = new PointF();
        this.mCircleStart = new PointF();
        this.mCheckAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.mCircleAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.mScaleAnimator = ValueAnimator.ofFloat(1.0f, SCALE_MIN, 1.0f);
        this.mCheckInterpolator = createCheckInterpolatorCompat();
        this.mPaint = createPaint(this.mStrokeColor, this.mStrokeWidth);
    }

//    private void resolveAttributes(Context c, AttributeSet attrs) {
//        if (attrs == null) {
//            return;
//        }
//        TypedArray a = c.getTheme().obtainStyledAttributes(attrs, R.styleable.CheckView, 0, 0);
//        try {
//            this.mStrokeWidth = a.getDimension(R.styleable.CheckView_checkView_strokeWidth, DEFAULT_STROKE_WIDTH);
//            this.mStrokeColor = a.getColor(R.styleable.CheckView_checkView_strokeColor, DEFAULT_STROKE_COLOR);
//        } finally {
//            a.recycle();
//        }
//    }

    @Override // android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            this.mDrawingRect.left = getPaddingLeft();
            this.mDrawingRect.top = getPaddingTop();
            this.mDrawingRect.right = getMeasuredWidth() - getPaddingRight();
            this.mDrawingRect.bottom = getMeasuredHeight() - getPaddingBottom();
            this.mCheckStart.x = this.mDrawingRect.left + (this.mDrawingRect.width() / 4.0f);
            this.mCheckStart.y = this.mDrawingRect.top + (this.mDrawingRect.height() / 2.0f);
            this.mCheckPivot.x = this.mDrawingRect.left + (this.mDrawingRect.width() * 0.426f);
            this.mCheckPivot.y = this.mDrawingRect.top + (this.mDrawingRect.height() * 0.66f);
            this.mCheckEnd.x = this.mDrawingRect.left + (this.mDrawingRect.width() * 0.75f);
            this.mCheckEnd.y = this.mDrawingRect.top + (this.mDrawingRect.height() * 0.3f);
            this.mMinorContourLength = distance(this.mCheckStart.x, this.mCheckStart.y, this.mCheckPivot.x, this.mCheckPivot.y);
            this.mMajorContourLength = distance(this.mCheckPivot.x, this.mCheckPivot.y, this.mCheckEnd.x, this.mCheckEnd.y);
            this.mCircleRect.left = this.mDrawingRect.left + (this.mStrokeWidth / 2.0f);
            this.mCircleRect.top = this.mDrawingRect.top + (this.mStrokeWidth / 2.0f);
            this.mCircleRect.right = this.mDrawingRect.right - (this.mStrokeWidth / 2.0f);
            this.mCircleRect.bottom = this.mDrawingRect.bottom - (this.mStrokeWidth / 2.0f);
            this.mCircleStart.x = this.mCircleRect.right;
            this.mCircleStart.y = this.mCircleRect.bottom / 2.0f;
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.mChecked) {
            return;
        }
        canvas.drawPath(this.mPathCheck, this.mPaint);
        canvas.drawPath(this.mPathCircle, this.mPaint);
    }

    public void check() {
        this.mChecked = true;
        this.mCheckAnimator.removeAllUpdateListeners();
        this.mCheckAnimator.setDuration(CHECK_ANIM_DURATION).setInterpolator(this.mCheckInterpolator);
        this.mCheckAnimator.addUpdateListener(this.mCheckAnimatorListener);
        this.mCircleAnimator.removeAllUpdateListeners();
        this.mCircleAnimator.setDuration(CHECK_ANIM_DURATION).setInterpolator(this.mCheckInterpolator);
        this.mCircleAnimator.addUpdateListener(this.mCircleAnimatorListener);
        this.mScaleAnimator.removeAllUpdateListeners();
        this.mScaleAnimator.setDuration(SCALE_ANIM_DURATION).setStartDelay(SCALE_ANIM_DELAY);
        this.mScaleAnimator.setInterpolator(new FastOutSlowInInterpolator());
        this.mScaleAnimator.addUpdateListener(this.mScaleAnimatorListener);
        this.mCheckAnimator.start();
        this.mCircleAnimator.start();
        this.mScaleAnimator.start();
    }

    public void uncheck() {
        this.mChecked = false;
        invalidate();
    }

    private Paint createPaint(int color, float strokeWidth) {
        Paint p = new Paint();
        p.setColor(color);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(strokeWidth);
        p.setStrokeJoin(Paint.Join.ROUND);
        p.setAntiAlias(true);
        p.setStrokeCap(Paint.Cap.ROUND);
        return p;
    }

    private Interpolator createCheckInterpolatorCompat() {
        if (Build.VERSION.SDK_INT >= 21) {
            return new PathInterpolator(0.755f, 0.05f, 0.855f, 0.06f);
        }
        return new AccelerateInterpolator();
    }

    private void setCheckPathFull() {
        this.mPathCheck.reset();
        this.mPathCheck.moveTo(this.mCheckStart.x, this.mCheckStart.y);
        this.mPathCheck.lineTo(this.mCheckPivot.x, this.mCheckPivot.y);
        this.mPathCheck.lineTo(this.mCheckEnd.x, this.mCheckEnd.y);
    }

    public void setCheckPathPercentage(float percent) {
        setCheckPathFull();
        float f = this.mMinorContourLength;
        float totalLength = this.mMajorContourLength + f;
        float pivotPercent = f / totalLength;
        if (percent > pivotPercent) {
            float remainder = percent - pivotPercent;
            float distance = totalLength * remainder;
            this.mPathCheck.reset();
            this.mPathCheck.moveTo(this.mCheckPivot.x, this.mCheckPivot.y);
            this.mPathCheck.lineTo(this.mCheckEnd.x, this.mCheckEnd.y);
            this.mPathMeasure.setPath(this.mPathCheck, false);
            this.mPathMeasure.getPosTan(distance, this.mPoint, null);
            this.mPathCheck.reset();
            this.mPathCheck.moveTo(this.mCheckStart.x, this.mCheckStart.y);
            this.mPathCheck.lineTo(this.mCheckPivot.x, this.mCheckPivot.y);
            Path path = this.mPathCheck;
            float[] fArr = this.mPoint;
            path.lineTo(fArr[0], fArr[1]);
        } else if (percent < pivotPercent) {
            float minorPercent = percent / pivotPercent;
            float distance2 = f * minorPercent;
            this.mPathMeasure.setPath(this.mPathCheck, false);
            this.mPathMeasure.getPosTan(distance2, this.mPoint, null);
            this.mPathCheck.reset();
            this.mPathCheck.moveTo(this.mCheckStart.x, this.mCheckStart.y);
            Path path2 = this.mPathCheck;
            float[] fArr2 = this.mPoint;
            path2.lineTo(fArr2[0], fArr2[1]);
        } else if (percent == pivotPercent) {
            this.mPathCheck.lineTo(this.mCheckPivot.x, this.mCheckPivot.y);
        }
    }

    public void setCirclePathPercentage(float percent) {
        this.mPathCircle.reset();
        this.mPathCircle.moveTo(this.mCircleStart.x, this.mCircleStart.y);
        this.mPathCircle.addArc(this.mCircleRect, 0.0f, 360.0f);
        this.mPathMeasure.setPath(this.mPathCircle, false);
        float distance = this.mPathMeasure.getLength() * percent;
        this.mPathMeasure.getPosTan(distance, this.mPoint, null);
        this.mPathCircle.reset();
        this.mPathCircle.moveTo(this.mCircleStart.x, this.mCircleStart.y);
        this.mPathCircle.arcTo(this.mCircleRect, 0.0f, 359.0f * percent);
    }

    private static float distance(float x1, float y1, float x2, float y2) {
        float xAbs = Math.abs(x1 - x2);
        float yAbs = Math.abs(y1 - y2);
        return (float) Math.sqrt((yAbs * yAbs) + (xAbs * xAbs));
    }
}
