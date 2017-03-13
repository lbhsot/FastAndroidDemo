package me.sonam.dev.corelibs.base;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by SonamLee on 2017/3/6.
 */
public class BaseInnerViewHolder {
    private View view;

    public View getView() {
        return view;
    }

    public BaseInnerViewHolder(View view) {
        this.view = view;
        ButterKnife.bind(this, view);
    }

    public void unBind() {
        ButterKnife.unbind(this);
        view = null;
    }
}
